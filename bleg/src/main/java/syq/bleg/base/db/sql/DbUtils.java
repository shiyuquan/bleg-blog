package syq.bleg.base.db.sql;

import org.springframework.util.StringUtils;
import syq.bleg.base.exception.MyException;

import java.util.*;

/**
 * @author shiyuquan
 * Create Time: 2019/6/24 17:32
 */
public class DbUtils {
    private DbUtils() {}

    /**
     * 根据连接池判断数据库类型
     * @param jdbcUrl
     * @return
     */
    public static String getDbtype(String jdbcUrl) {
        if (StringUtils.isEmpty(jdbcUrl)) {
            throw new MyException("Error: The jdbcUrl is Null, Cannot read database type");
        }
        if (jdbcUrl.startsWith("jdbc:mysql:") || jdbcUrl.startsWith("jdbc:cobar")
                || jdbcUrl.startsWith("jdbc:log4jdbc:mysql:")) {
            return DatabaseType.mysql.name();
        } else if (jdbcUrl.startsWith("jdbc:oracle:") || jdbcUrl.startsWith("jdbc:log4jdbc:oracle:")) {
            return DatabaseType.oracle.name();
        } else if (jdbcUrl.startsWith("jdbc:postgresql:") || jdbcUrl.startsWith("jdbc:log4jdbc:postgresql:")) {
            return DatabaseType.postgres.name();
        } else {
            throw new MyException("The jdbcUrl is " + jdbcUrl + ", Cannot Read Database type or The Database's Not Supported!");
        }
    }

    /**
     * 加载搜索条件，返回where子句
     * @param sqlParam
     * @param searchParams
     * @return
     */
    public static String loadSearchParam(List<Object> sqlParam, String searchParams) {
        if (StringUtils.isEmpty(searchParams)) {
            return "";
        }

        Object jsonObject = null;
        if (!StringUtils.isEmpty(searchParams)) {
            jsonObject = FastjsonHelper.fromJson(searchParams, Object.class);
        }
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" where 1 = 1 ");
        if (jsonObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonMap = (Map) jsonObject;
            for (Object o : jsonMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                sqlBuilder.append("and ");
                sqlBuilder.append((String) entry.getKey()).append(" = ? ");
                sqlParam.add(entry.getValue());
            }
        }

        else if (jsonObject instanceof List) {
            List jsonList = (List) jsonObject;
            for (Object o : jsonList) {
                Map<String, Object> jsonMap = (Map) o;
                // and 连接
                if (jsonMap.containsKey(DbContains.AND_LOWER) || jsonMap.containsKey(DbContains.AND)) {
                    List subMapList = new ArrayList();
                    if (jsonMap.containsKey(DbContains.AND_LOWER)) {
                        subMapList = (List) jsonMap.get(DbContains.AND_LOWER);
                    } else if (jsonMap.containsKey(DbContains.AND)) {
                        subMapList = (List) jsonMap.get(DbContains.AND);
                    }
                    if (!subMapList.isEmpty()) {
                        sqlBuilder.append(DbContains.AND_LOWER);

                        // 组装每组里的字段参数条件
                        String sqlGroupStr = buildGroupSql(subMapList, sqlParam);
                        if (!StringUtils.isEmpty(sqlGroupStr)) {
                            sqlBuilder.append(" (").append(sqlGroupStr).append(") ");
                        }
                    }
                }
                // or 连接
                else if (jsonMap.containsKey(DbContains.OR_LOWER) || jsonMap.containsKey(DbContains.OR)) {
                    List subMapList = new ArrayList();
                    if (jsonMap.containsKey(DbContains.OR_LOWER)) {
                        subMapList = (List) jsonMap.get(DbContains.OR_LOWER);
                    } else if (jsonList.contains(DbContains.OR)) {
                        subMapList = (List) jsonMap.get(DbContains.OR);
                    }
                    if (!subMapList.isEmpty()) {
                        sqlBuilder.append(DbContains.OR_LOWER);

                        String sqlGroupStr = buildGroupSql(subMapList, sqlParam);
                        if (StringUtils.isEmpty(sqlGroupStr)) {
                            sqlBuilder.append(" (").append(sqlGroupStr).append(") ");
                        }
                    }
                }
            }
        }
        return sqlBuilder.toString();
    }

    /**
     * 组合查询 每组中的sql组装
     * @param jsArray
     * @param sqlParams
     * @return
     */
    public static String buildGroupSql(List jsArray, List<Object> sqlParams) {
        StringBuilder stringBuilder = new StringBuilder();
        int currentIndex = 0;
        for (Object jsObject : jsArray) {
            Map jsonMap = (Map) jsObject;
            String retStr = groupSqlDeal(jsonMap, currentIndex, sqlParams);
            if (!StringUtils.isEmpty(retStr)) {
                stringBuilder.append(retStr);
            }
            currentIndex++;
        }
        return stringBuilder.toString();
    }

    /**
     * 每组中的字段间的连接符是否为or
     * @param jsonObject
     * @return
     */
    public static boolean orJsonCondition(Map jsonObject) {
        if (jsonObject.containsKey(DbContains.JN)) {
            String join = jsonObject.get(DbContains.JN).toString();
            if (join.equals(DbContains.OR_LOWER) || join.equals(DbContains.OR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接where后的sql列
     * @param jsonObject
     * @param currentIndex
     * @param sqlParams
     * @return
     */
    private static String groupSqlDeal(Map jsonObject, int currentIndex, List<Object> sqlParams) {
        // 默认like
        String mt = DbMatchEnum.DEFAULT.getValue();

        if (jsonObject.containsKey(DbContains.MT)) {
            mt = jsonObject.get(DbContains.MT).toString();
        }

        if (jsonObject.containsKey(DbContains.KEY) && jsonObject.containsKey(DbContains.VAL)) {
            String key = jsonObject.get(DbContains.KEY).toString();
            Object val = jsonObject.get(DbContains.VAL);

            if (!StringUtils.isEmpty(key)) {
                StringBuilder stringBuilder = new StringBuilder();

                if (currentIndex != 0) {
                    if (orJsonCondition(jsonObject)) {
                        stringBuilder.append(" ").append(DbContains.OR_LOWER).append(" ");
                    } else {
                        stringBuilder.append(" ").append(DbContains.AND_LOWER).append(" ");
                    }
                }

                String placeHolder = "?";
                if (mt.equals(DbMatchEnum.EQ.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.EQ.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.NOT_EQ.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.NOT_EQ.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.LIKE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.LIKE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add("%" + val + "%");
                } else if (mt.equals(DbMatchEnum.NOTLIKE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.NOTLIKE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add("%" + val + "%");
                } else if (mt.equals(DbMatchEnum.LLIKE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.LLIKE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add("%" + val);
                } else if (mt.equals(DbMatchEnum.RLIKE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.RLIKE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val + "%");
                } else if (mt.equals(DbMatchEnum.GT.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.GT.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.GE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.GE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.LT.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.LT.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.LE.getValue())) {
                    stringBuilder.append(key).append(" ").append(DbMatchEnum.LE.getDb()).append(" ").append(placeHolder);
                    sqlParams.add(val);
                } else if (mt.equals(DbMatchEnum.IN.getValue())) {
                    handlerInSql(val, sqlParams, stringBuilder, key, DbMatchEnum.IN.getDb());
                } else if (mt.equals(DbMatchEnum.NOTIN.getValue())) {
                    handlerInSql(val, sqlParams, stringBuilder, key, DbMatchEnum.NOTIN.getDb());
                } else if (mt.equals(DbMatchEnum.BETWEEN.getValue())) {
                    handlerBtSlq(val, sqlParams, stringBuilder, key, DbMatchEnum.BETWEEN.getDb());
                } else if (mt.equals(DbMatchEnum.NOTBETWEEN.getValue())) {
                    handlerBtSlq(val, sqlParams, stringBuilder, key, DbMatchEnum.NOTBETWEEN.getDb());
                }

                return stringBuilder.toString();
            }
        }
        return "";
    }

    /**
     * 加载 排序条件
     * @param sorts
     * @return
     */
    public static String loadSort(String[] sorts) {
        Map<String, String> sortMap = createSorts(sorts);

        if (null != sortMap && sortMap.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" order by ");
            sortMap.forEach((k, v) -> {
                stringBuilder.append(k);
                stringBuilder.append(DbContains.ASC_LOWER.equals(v.toLowerCase()) ? " " +
                        DbContains.ASC + " , " : " " + DbContains.DESC + " ,");
            });
            return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        }
        return "";
    }

    private static void handlerInSql(Object val, List<Object> sqlParam, StringBuilder stringBuilder, String key, String dbMatch) {
        if (val instanceof List) {
            List valArray = (List) val;
            if (valArray.size() > 0) {
                stringBuilder.append(key).append(dbMatch).append("(");
                for (int i = 0; i < valArray.size(); i++) {
                    if (i == 0) {
                        stringBuilder.append("?");
                    } else {
                        stringBuilder.append(". ?");
                    }
                    sqlParam.add(valArray.get(i));
                }
                stringBuilder.toString();
            }
        }
    }

    private static void handlerBtSlq(Object val, List<Object> sqlParams, StringBuilder stringBuilder, String key, String dbMatch) {
        if (val instanceof List) {
            List valArray = (List) val;
            if (valArray.size() > 0) {
                stringBuilder.append(key).append(dbMatch).append("? and ?");
                sqlParams.add(valArray.get(0));
                sqlParams.add(valArray.get(1));
            }
        }
    }

    public static Map<String, String> createSorts(String[] sorts) {
        Map<String, String> sortsMap = new HashMap<>();

        Optional.ofNullable(sorts).ifPresent(s -> {
            for (String sort : s) {
                if (sort.startsWith(DbContains.SORT_MARK)) {
                    // 降序
                    sortsMap.put(sort.replaceFirst(DbContains.SORT_MARK, ""), DbContains.DESC);
                } else {
                    sortsMap.put(sort, DbContains.ASC);
                }
            }
        });
        return sortsMap;
    }

    public static void parseLimit(StringBuilder sql, int pageNum, int pageSize, String databaseType) {
        if (DatabaseType.postgres.name().equals(databaseType)) {
            parseLimitPostgres(sql, pageNum, pageSize);
        } else if (DatabaseType.mysql.name().equals(databaseType)) {
            parseLimitMySQL(sql, pageNum, pageSize);
        } else if (DatabaseType.oracle.name().equals(databaseType)) {
            parseLimitOracle(sql, pageNum, pageSize);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static void parseLimitPostgres(StringBuilder sql, int pageNum, int pageSize) {
        sql.append(" LIMIT ").append(pageSize).append(" OFFSET ")
                .append((pageNum -1) * pageSize);
    }

    private static void parseLimitMySQL(StringBuilder sql, int pageNum, int pageSize) {
        sql.append(" LIMIT ").append(pageSize * pageNum).append(", ")
                .append(pageSize);
    }

    private static void parseLimitOracle(StringBuilder sql, int pageNum, int pageSize) {
        long end = pageNum * pageSize;
        long start = end - pageSize;
        sql.insert(0, "SELECT * FROM (SELECT _t.*, rownum _rn FROM (")
                .append(") _t WHERE _rn <").append(end).append(")").append(" WHERE _rn >=").append(start);
    }

}
