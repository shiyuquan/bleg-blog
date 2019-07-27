package syq.bleg.base.db.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shiyuquan
 * Create Time: 2019/6/27 11:27
 */
public class DataTable<T> implements Serializable {

    private static final long serialVersionUID = 2252240868205663450L;

    private static final String CREATE_DATE = "createDate";
    private static final String ORDER_DESC = "DESC";

    /** 搜索条件 */
    private Object searchParams;

    /** 表的全文检索内容 */
    private String fulltext;

    /** 表的全文检索字段 */
    private String[] ftCols;

    /** 返回列表 */
    private List<T> rows = new ArrayList<>();

    /** 排序条件 */
    private Map<String, Object> sorts;

    /** 页码 */
    private int pageNum;

    /** 页大小 */
    private int pageSize;

    /** 总数 */
    private int total;

    @JsonIgnore
    public Object getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(Object searchParams) {
        this.searchParams = searchParams;
    }

    @JsonIgnore
    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    @JsonIgnore
    public String[] getFtCols() {
        return ftCols;
    }

    public void setFtCols(String[] ftCols) {
        this.ftCols = ftCols;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @JsonIgnore
    public Map<String, Object> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, Object> sorts) {
        this.sorts = sorts;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
