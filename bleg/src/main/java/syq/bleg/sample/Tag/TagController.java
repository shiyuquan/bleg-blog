package syq.bleg.sample.Tag;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import syq.bleg.intermediatetable.article2tag.Article2TagService;
import syq.bleg.base.MyResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 10:38
 * @Description TODO
 */
@RestController
@RequestMapping("/api")
@Api(description = "标签  Controller")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private Article2TagService article2TagService;

    @PostMapping("/tags")
    public MyResult<Tag> batchSaveTags(@RequestBody List<String> tagNames,@RequestParam String articleId) {
        if(tagNames.size() <= 0) {
            return null;
        }
        List<String> tagIds = new ArrayList<>();
        for (String name: tagNames) {
            Tag tag = tagService.findByName(name);
            if (tag == null) {
                Tag tag1 = new Tag();
                tag1.setId(UUID.randomUUID().toString());
                tag1.setTagName(name);
                tagIds.add(tagService.saveTag(tag1).getId());
            } else {
                tagIds.add(tag.getId());
            }
        }

        if(!StringUtils.isEmpty(articleId)) {
            article2TagService.addArticle2tag(tagIds, articleId);
        }
        return new MyResult<Tag>().success(null, 200, "success");
    }

    @GetMapping(value = "tag")
    public MyResult getTagList() {
        List<Tag> tags = tagService.getAll();
        return new MyResult().success("[]".getBytes(), 200, "success");
    }
}
