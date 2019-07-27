package syq.bleg.base.db.sql;

import java.io.Serializable;

import static syq.bleg.base.db.sql.DbContains.DEFAULT_PAGE_NUM;
import static syq.bleg.base.db.sql.DbContains.DEFAULT_PAGE_SIZE;

/**
 * @author shiyuquan
 * Create Time: 2019/5/24 10:28
 */
public class SearchCondition implements Serializable {

    private int pageNum = DEFAULT_PAGE_NUM;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private String params;

    private String[] sorts;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String[] getSorts() {
        return sorts;
    }

    public void setSorts(String[] sorts) {
        this.sorts = sorts;
    }
}
