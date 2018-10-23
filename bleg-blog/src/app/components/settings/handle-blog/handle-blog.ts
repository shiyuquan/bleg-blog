import { Prop, Vue, Watch } from 'vue-property-decorator';
import { Route, RawLocation } from 'vue-router';
import localForage from 'localforage';
import Component from 'vue-class-component';

import { Http } from '../../../../shared/Http';
import { ArticleService } from '../../../../shared/service/article.service';
import { TagService } from '../../../../shared/service/tag.service';
import { User } from '../../../../shared/model/user.model';
import { Article } from '../../../../shared/model/article.model';

import './handle-blog.less';

Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate', // for vue-router 2.2+
]);

@Component
export default class HandleBlog extends Vue {
  private http = new Http();
  private articleService = new ArticleService();
  private tagService = new TagService();
  private isSave = true;
  private isAdd = true;
  private isfirst = true;
  private md = '';
  private html = '';
  private value10 = [];
  private article: Article = {};
  private tags = [];
  private totaltags = [
    {value: 'tag1', label: 'tag1'},
    {value: 'tag2', label: 'tag2'},
  ];


  private created() {
    console.log(this.isAdd);
    if (this.$route.query.articleId) {
      this.isAdd = false;
      this.articleService.getArticle(this.$route.query.articleId)
      .then((res) => {
        this.article = res.data;
        this.md = String(this.article.md);
      });
    } else {
      this.initParameter();
    }
  }

  private initParameter() {
    this.isAdd = true;
    this.md = '';
    this.html = '';
    this.value10 = [];
    this.article = {};
    this.tags = [];
  }

  private mounted() {
    localForage.getItem('USER')
    .then((value: User) => {
      this.article.userId = String(value.id);
    });
  }

  @Watch('md')
  private lookhtml() {
    if (this.isfirst) {
      // this.article.html = this.article.html;
      this.isfirst = false;
    } else {
      this.article.html = this.$refs.md._data.d_render;
    }
  }

  private saveTag(articleId: string) {
    this.tagService.saveTag(this.tags, articleId)
    .then();
  }

  private saveArticle(state: number) {
    this.article.state = state;
    this.article.md = this.md;
    this.articleService.saveArticle(this.article)
    .then((res) => {
      if (res.msg === 'success') {
        this.saveTag(res.data.id);
        this.article = res.data;
        this.$notify({
          title: '成功',
          message: '发布成功',
          type: 'success',
          duration: 2000,
        });
      } else {
        this.$notify({
          title: '失败',
          message: res.msg,
          type: 'error',
          duration: 2000,
        });
      }
    });
  }

  private beforeRouteLeave(to: Route, from: Route, next: Function) {
    if (this.isSave === true) {
      next();
    } else {
      if (confirm('检测您有未保存数据，退出将不会保存，是否退出')) {
        next();
      } else {
        next(false);
      }
    }
  }

}
