import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import './article-page.less';
import { Http } from '../../../shared/Http';
import { Page } from '../../../shared/model/pagination';
import { ArticleService } from '../../../shared/service/article.service';
import { Article } from '../../../shared/model/article.model';

@Component
export default class ArticlePage extends Vue {

    private articleService = new ArticleService();
    private article: Article = {};

    private created() {
        this.articleService.getArticle(this.$route.query.articleId)
        .then((res) => {
            this.article = res.data;
        });
    }

}
