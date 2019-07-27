package syq.bleg.sample.Article;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 19:52
 * @Description TODO
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "md", columnDefinition="CLOB")
    private String md;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "html", columnDefinition="CLOB")
    private String html;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "summary", columnDefinition="CLOB")
    private String summary;

    @Column(name = "publishTime")
    private Date publishTime;

    @Column(name = "views")
    private Long views;

    @Column(name = "categoryId")
    private String categoryId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "state")
    private Long state;

    @Column(name = "editTime")
    private Date editTime;

    public Article(String id, String title, String md, String html, String summary, Date publishTime, Long views, String categoryId, String userId, Long state, Date editTime) {
        this.id = id;
        this.title = title;
        this.md = md;
        this.html = html;
        this.summary = summary;
        this.publishTime = publishTime;
        this.views = views;
        this.categoryId = categoryId;
        this.userId = userId;
        this.state = state;
        this.editTime = editTime;
    }

    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
