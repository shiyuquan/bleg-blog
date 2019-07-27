package syq.bleg.sample.Article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import syq.bleg.base.MyResult;

import java.util.Date;
import java.util.UUID;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 20:03
 * @Description TODO
 */

@RestController
@RequestMapping("/api")
@Api(description = "文章  Controller")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章", notes = "新增文章")
    @PostMapping(value = "/article")
    public MyResult<Article> addArticle(@RequestBody Article article) {
        if (StringUtils.isEmpty(article.getTitle())) {
            return new MyResult<Article>().error(null, 10013, "标题不可为空");
        }
        if (StringUtils.isEmpty(article.getId())) {
            article.setId(UUID.randomUUID().toString());
            article.setViews(0L);
            article.setPublishTime(new Date());
        }
        article.setEditTime(new Date());
        Article article1 = articleService.addArticle(article);
        return new MyResult<Article>().success(article1, 200, "success");
    }

    @ApiOperation(value = "查询文章", notes = "查询文章")
    @GetMapping(value = "/articles")
    public Page<Article> queryByPage(@RequestParam Integer page,@RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size, null);
        return articleService.getArticleByPage(pageable);
    }

    @ApiOperation(value = "获取文章", notes = "获取文章")
    @GetMapping(value = "/article")
    public MyResult<Article> getArticle(@RequestParam String id) {
        return new MyResult<Article>().success(articleService.getArticle(id), 200, "success");
    }
}
