import { Component, Prop, Vue } from 'vue-property-decorator';
import './home.less';
import { Http } from '../../shared/Http';

@Component
export default class Home extends Vue {
  private http = new Http();

  // private beforeCreate() {
  //   this.$router.push('/home');
  // }

  private toHome() {
    this.$router.push('/home');
  }

  private toSettings() {
    this.$router.push('/settings');
  }
}
