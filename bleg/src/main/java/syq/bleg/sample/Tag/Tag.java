package syq.bleg.sample.Tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 19:59
 * @Description TODO
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "tagName")
    private String tagName;

    public Tag(String id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Tag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
