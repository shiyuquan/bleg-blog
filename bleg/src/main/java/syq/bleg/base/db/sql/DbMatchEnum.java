package syq.bleg.base.db.sql;

/**
 * @author shiyuquan
 * Create Time: 2019/6/24 18:50
 */
public enum DbMatchEnum {

    DEFAULT("like", " like ", "包含"),
    EQ("eq", "=", "等于"),
    NOT_EQ("ne", "!=", "不等于"),
    LIKE("like", " like ", "包含"),
    NOTLIKE("nlike", " not like ", "不包含"),
    LLIKE("llike", " like ", "左匹配"),
    RLIKE("rlike", " like ", "右匹配"),
    GT("gt", " > ", "大于"),
    GE("ge", " >= ", "大于等于"),
    LT("lt", " < ", "小于"),
    LE("le", " <= ", "小于等于"),
    IN("in", " in ", "包括"),
    NOTIN("nin", " not in ", "不包括"),
    BETWEEN("bt", " between ", "介于"),
    NOTBETWEEN("nbt", " not between ", "不介于")

    ;

    String value;
    String db;
    String desp;

    DbMatchEnum(String value, String db, String desp) {
        this.value = value;
        this.db = db;
        this.desp = desp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
