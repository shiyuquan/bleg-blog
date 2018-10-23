import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import './settings.less';
import { Http } from '../../../shared/Http';
import { Page } from '../../../shared/model/pagination';
import { User } from '../../../shared/model/user.model';

@Component
export default class Settings extends Vue {
  private http = new Http();
  private user: User = {};

  private toHandleBlog() {
    this.$router.go(0);
    this.$router.push({path: '/settings/handle-blog'});
  }
}
