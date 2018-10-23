import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import './register.less';
import { Http } from '../../../shared/Http';
import { UserService } from '../../../shared/service/user.service';
import { Page } from '../../../shared/model/pagination';
import { User } from '../../../shared/model/user.model';

@Component
export default class Register extends Vue {
  private http = new Http();
  private user: User = {};
  private checkNum: string = '';
  private repwd: string = '';
  private userService = new UserService();
  private btnContent = '获取验证码';

  private visible = true;

  // 提示显示判断
  private showUserNameTip = false;
  private showNickNameTip = false;
  private showPasswordTip = false;
  private showRepwdTip = false;
  private showphoneTip = false;
  private showCheckNumTip = false;
  // 提示信息
  private userNameTip = '';
  private nickNameTip = '';
  private passwordTip = '';
  private repwdTip = '';
  private phoneTip = '';
  private checkNumTip = '';


  private toLogin() {
    this.$router.push({
        path: `/login`,
      });
  }

  private register() {
    // this.checkInput();

    this.userService.register(this.user, this.checkNum)
    .then((res) => {
      if (res.msg === 'success') {
        this.$router.push('/login');
      } else {
        this.checkInput(res.code, res.msg);
      }
    });
  }

  @Watch('user.userName')
  private watchUserName() {
    if (this.user.userName != null && this.user.userName !== '') {
      this.showUserNameTip = false;
    }
  }

  private checkInput(code: number, msg: string) {
    if (code === 10001 || code === 10002) {
      this.userNameTip = msg;
      this.showUserNameTip = true;
    }
    if (code === 10003) {
      this.nickNameTip = msg;
      this.showNickNameTip = true;
    }
    if (code === 10004) {
      this.phoneTip = msg;
      this.showphoneTip = true;
    }
    if (code === 10005) {
      this.passwordTip = msg;
      this.showPasswordTip = true;
    } else {
      if (this.repwd == null || this.repwd === '') {
        this.repwdTip = '请确密码';
        this.showRepwdTip = true;
      }
      if (this.user.password !== this.repwd) {
        this.repwdTip = '密码不匹配';
        this.showRepwdTip = true;
      }
    }
    if (code === 10006 || code === 10007 || code === 10008) {
      this.checkNumTip = msg;
      this.showCheckNumTip = true;
    }
  }

  private getCode() {
    alert('todo');
  }

}
