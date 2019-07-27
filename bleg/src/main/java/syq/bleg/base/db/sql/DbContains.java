package syq.bleg.base.db.sql;

/**
 * @author shiyuquan
 * Create Time: 2019/5/24 15:21
 */
public class DbContains {

    private DbContains() {}

    public static final String DB_SCHEMA = "";
    public static final String DB_TABLE_SCHEMA = DB_SCHEMA + ".";

    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String AND = "AND";
    public static final String AND_LOWER = "and";
    public static final String OR = "OR";
    public static final String OR_LOWER = "or";

    public static final String ASC = "ASC";
    public static final String ASC_LOWER = "asc";
    public static final String DESC = "DESC";
    public static final String DESC_LOWER = "desc";

    public static final String SORT_MARK = "-";

    public static final String KEY = "key";
    public static final String VAL = "val";
    public static final String MT = "mt";
    public static final String JN = "jn";
}
