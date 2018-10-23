import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import localForage from 'localforage';

import { Http } from '../../../../shared/Http';
import { User } from '../../../../shared/model/user.model';
import { Article } from '../../../../shared/model/article.model';
import { ArticleService } from '../../../../shared/service/article.service';
import { TagService } from '../../../../shared/service/tag.service';

import './blog-list.less';

@Component
export default class BlogList extends Vue {
  private http = new Http();
  private articleService = new ArticleService();
  private tagService = new TagService();
  private article = {};
  private articlePage: Article[] = [];

  // 查询参数
  private content = '';
  private publishTimes = [];
  private editTimes = [];
  private tags = [];

  // 自定义日期选择器
  private pickerOptions2 = {
    shortcuts: [{
      text: '最近一周',
      onClick(picker: any) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
        picker.$emit('pick', [start, end]);
      },
    }, {
      text: '最近一个月',
      onClick(picker: any) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
        picker.$emit('pick', [start, end]);
      },
    }, {
      text: '最近三个月',
      onClick(picker: any) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
        picker.$emit('pick', [start, end]);
      },
    }],
  };

  // 分页
  private pageNum = 1;
  private pagesize = 2;
  private totalPages = 2;
  private totalElements = 2;
  private totaltags: any[] = [];

  private created() {
    this.queryPage();
    this.queryTags();
  }

  private queryTags() {
    this.tagService.getTags().then((res) => {
      res.data.forEach( (item: any) => {
        this.totaltags.push({ value: item.id, label: item.tagName});
      });
    });
  }

  private queryPage() {
    this.articleService.queryForPage(this.pageNum - 1, this.pagesize)
    .then((res) => {
      this.articlePage = res.content;
      this.totalPages = res.totalPages;
      this.totalElements = res.totalElements;
    });
  }

  private toBlog(articleId: string) {
    this.$router.push({path: '/settings/handle-blog', query: {articleId} });
  }

  //
  private getPageNumber(val: number) {
    this.pageNum = val;
    this.queryPage();
  }
}
