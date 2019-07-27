import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import localForage from 'localforage';
import moment from 'moment';

import { Http } from '../../../shared/Http';
import { ArticleService } from '../../../shared/service/article.service';
import { UserService } from '../../../shared/service/user.service';
import { Page } from '../../../shared/model/pagination';
import { User } from '../../../shared/model/user.model';
import { Article } from '../../../shared/model/article.model';
import './home-page.less';

@Component
export default class HomePage extends Vue {
  private http = new Http();
  private articleService = new ArticleService();
  private userService = new UserService();
  private fullHeight = document.documentElement.clientHeight; // 屏幕高度px
  private isShowAnnouncement = true; // 公告div显示判断值
  private PageSizes: number[] = [5, 7, 9]; // 页大小配置；
  private articlePageDate: Article[] = [];
  private user = {};

  private totalPages: number = 1;
  private totalrows: number = 0;
  private pageNumber: number = 1;
  private totalElements: number = 1;

  private showPagesize: number = 7; // 分页栏显示页数
  private showPageList: any[] = [];
  private imageUrl = '';
  private userName = '';
  private password = '';

  private isLogin = false;

  private closeAnnouncement() {
    this.isShowAnnouncement = true;
  }

  private created() {
    this.queryForPage(1);
  }

  private mounted() {
    window.addEventListener('scroll', this.handleScroll);
    localForage.getItem('isLogin')
    .then((value) => {
      this.isLogin = Boolean(value);
    });
    localForage.getItem('USER')
    .then((value) => {
      this.user = value;
      this.imageUrl = 'http://localhost:9000/' + String(this.user.id) + '/' + String(this.user.profilePicture);
    });
  }

  @Watch('articlePageDate')
  private watchArticlePageDate() {
    this.$nextTick(() => {
      this.handleScroll();
    });
  }

  @Watch('showPageList')
  private watchShowPageList() {
    this.$nextTick(() => {
      this.pageItemActive();
    });
  }

  private handleScroll() {
    const articleItems = document.getElementsByName('items');
    const scrollTop =  window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
    for (const item of articleItems) {
      const offsetTop = item.offsetTop;
      if (offsetTop - scrollTop <= (this.fullHeight * 0.8) ) {
        item.classList.add('aos-animate');
      } else {
        item.classList.remove('aos-animate');
      }
    }
  }

  private queryForPage(pageNum: number) {
    if (pageNum === -2) {
      this.pageNumber = this.pageNumber - 1;
    } else if (pageNum === -1) {
      this.pageNumber = this.pageNumber + 1;
    } else {
      this.pageNumber = pageNum;
    }
    this.articleService.queryForPage(this.pageNumber - 1, this.PageSizes[2])
    .then((res) => {
      this.articlePageDate = res.content;
      console.log(this.articlePageDate);
      this.totalPages = res.totalPages;
      this.totalElements = res.totalElements;
      this.changePages();
    });
  }

  private changePages() {
    this.showPageList = [];
    const x = Math.floor(this.showPagesize / 2);
    if (this.pageNumber - x > 1 ) {
      this.showPageList.push(-2);
      this.showPageList.push(1);
      this.showPageList.push(0);
    }
    for (let i = this.pageNumber - x; i <= this.pageNumber + x; i++) {
      if (i >= 1 && i <= this.totalPages) {
        this.showPageList.push(i);
      }
    }
    if (this.pageNumber + x < this.totalPages ) {
      this.showPageList.push(0);
      this.showPageList.push(this.totalPages);
      this.showPageList.push(-1);
    }
  }

  private pageItemActive() {
    const pageItems = document.getElementsByName('pageItems');
    for (const item of pageItems) {
      item.classList.remove('active');
    }
    for (const item of pageItems) {
      if (item.innerHTML.replace(/\s+/g, '') === this.pageNumber.toString()) {
        item.classList.add('active');
      }
    }
  }

  private login() {
      this.userService.login(this.userName, this.password)
      .then((res) => {
        if (res.msg === 'success') {
          localForage.setItem('isLogin', true);
          localForage.setItem('USER', res.data);
          this.$router.go(0);
        } else {
          this.$message({
            message: res.msg,
            type: 'warning',
          });
        }
    });
  }
  
  private loginOut() {
    localForage.removeItem('isLogin');
    localForage.removeItem('USER');
    this.$router.go(0);
  }

  private toRegister() {
    this.$router.push({
      path: '/register',
    });
  }

  private linkTo(articleId: string) {
    this.$router.push({path: '/article-page', query: {articleId} });
  }
}

