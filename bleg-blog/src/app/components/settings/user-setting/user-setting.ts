import { Component, Prop, Vue, Watch } from 'vue-property-decorator';
import './user-setting.less';
import { Http } from '../../../../shared/Http';
import { UserService } from '../../../../shared/service/user.service';
import localForage from 'localforage';
import { User } from '../../../../shared/model/user.model';

@Component
export default class UserSetting extends Vue {
  private http = new Http();
  private userService = new UserService();
  private imageUrl = '';
  private birthday = '';
  private file: File;
  private formdate = {username: 'a', pasword: 'b'};
  private user: User = {};

  private mounted() {
    localForage.getItem('USER')
    .then((value) => {
      this.user = value;
      this.imageUrl = 'http://localhost:9000/' + this.user.id + '/' + this.user.profilePicture;
    });
  }

  private updateUser() {
    this.userService.updateUser(this.user)
    .then( (res) => {
      if (res.msg === 'success') {
        localForage.setItem('USER', res.data);
      } else {
        this.$message({
          message: res.msg,
          type: 'warning',
        });
      }
    });
  }

  // 头像上传前处理
  private beforeAvatarUpload(file: File) {
    const isJPG = file.type === 'image/jpeg';
    const isLt2M = file.size / 1024 / 1024 < 2;

    if (!isJPG) {
      this.$message.error('上传头像图片只能是 JPG 格式!');
    }
    if (!isLt2M) {
      this.$message.error('上传头像图片大小不能超过 2MB!');
    }
    return isJPG && isLt2M;
  }

  private uploadFile() {
    const form = new FormData();
    form.append('file', this.file);
    form.append('userName', String(this.user.userName));
    form.append('password', String(this.user.password));
    this.userService.uploadFile(form)
    .then((res) => {
      localForage.setItem('USER', res.data);
    });
  }

  private fileChange(file: File) {
    this.file = file.raw;
    this.imageUrl = URL.createObjectURL(file.raw);
  }

}
