package syq.bleg.base.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author shiyuquan
 * @Date 2018/10/10 15:43
 * @Description TODO
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "idCardNo")
    private String idCardNo;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "description")
    private String description;
    @Column(name = "lastLoginIp")
    private String lastLoginIp;
    @Column(name = "registerTime")
    private Date registerTime;
    @Column(name = "qq")
    private String qq;
    @Column(name = "profilePicture")
    private String profilePicture;
    @Column(name = "nickname")
    private String nickName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User() {
    }

    public User(String id, String userName, String password, String phone, String address, String sex, String email, String idCardNo, Date birthday, String description, String lastLoginIp, Date registerTime, String qq, String profilePicture, String nickName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.idCardNo = idCardNo;
        this.birthday = birthday;
        this.description = description;
        this.lastLoginIp = lastLoginIp;
        this.registerTime = registerTime;
        this.qq = qq;
        this.profilePicture = profilePicture;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", registerTime=" + registerTime +
                ", qq='" + qq + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
