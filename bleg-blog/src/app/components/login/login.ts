import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import './login.less';
import { Http } from '../../../shared/Http';
import { UserService } from '../../../shared/service/user.service';
import { Page } from '../../../shared/model/pagination';
import localForage from 'localforage';

@Component
export default class Login extends Vue {
  private http = new Http();
  private userService = new UserService();
  private btnContent = '获取验证码';
  private jisuan = '';
  private userName = '';
  private password = '';
  private checkNum = null;

  private num1 = 0;
  private num2 = 0;

    // 提示显示判断
    private showUserNameTip = false;
    private showPasswordTip = false;
    private showCheckNumTip = false;
    // 提示信息
    private userNameTip = '';
    private passwordTip = '';
    private checkNumTip = '';

  private mounted() {
    this.createJisuan();
  }

  private login() {
    const sum = this.num1 + this.num2;
    if (this.checkNum === null || this.checkNum === '') {
      this.checkNumTip = '验证码不可为空';
      this.showCheckNumTip = true;
    } else if (this.checkNum != sum) {
      this.checkNumTip = '20以内的运算都不会？可以去死了';
      this.showCheckNumTip = true;
      this.createJisuan();
    } else {
      this.userService.login(this.userName, this.password)
      .then((res) => {
        if (res.msg === 'success') {
          localForage.setItem('isLogin', true);
          localForage.setItem('USER', res.data);
          this.$router.push('/home');
        } else {
          this.checkInput(res.code, res.msg);
        }
      });
    }
  }

  private createJisuan() {
    this.num1 = Math.ceil(Math.random() * 10);
    this.num2 = Math.ceil(Math.random() * 10);
    this.jisuan = this.num1 + '+' + this.num2 + '= ?';
  }

  private checkInput(code: number, msg: string) {
    if (code === 10001 || code === 10010) {
      this.userNameTip = msg;
      this.showUserNameTip = true;
    }
    if (code === 10005 || code === 10011) {
      this.passwordTip = msg;
      this.showPasswordTip = true;
    }
  }

  @Watch('userName')
  private watchUserName() {
    if (this.userName != null && this.userName !== '') {
      this.showUserNameTip = false;
    }
  }

  @Watch('password')
  private watchPassword() {
    if (this.password != null && this.password !== '') {
      this.showPasswordTip = false;
    }
  }

  @Watch('checkNum')
  private watchCheckNum() {
    if (this.checkNum != null && this.checkNum !== '') {
      this.showCheckNumTip = false;
    }
  }

  private toRegister() {
    this.$router.push({
      path: '/register',
    });
  }
}
