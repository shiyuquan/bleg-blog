<template>
    <div class="home">
        <form class="login-register-form">
            <h3>注册</h3>
            
            <div class="ll">
                <label for="userName">用户名<br>
                    <input type="text" id="userName" size="20" v-model="user.userName">
                </label>
                <div class="tip" v-show="showUserNameTip">
                    {{ userNameTip }}
                </div>
            </div>
            <div class="ll">
                <label for="nickName">昵称<br>
                    <input type="text" id="nickName" size="20" v-model="user.nickName">
                </label>
                <div class="tip" v-show="showNickNameTip">
                    {{ nickNameTip }}
                </div>
            </div>
            <div class="ll">
                <label for="password">密码<br>
                    <input type="password" id="password" size="25" v-model="user.password">
                </label>
                <div class="tip" v-show="showPasswordTip">
                    {{ passwordTip }}
                </div>
            </div>
            <div class="ll">
                <label for="repwd">确认密码<br>
                    <input type="password" id="repwd" size="25" v-model="repwd">
                </label>
                <div class="tip" v-show="showRepwdTip">
                    {{ repwdTip }}
                </div>
            </div>
            <div class="ll">
                <label for="phone">手机号码<br>
                    <input type="text" id="phone" size="25" v-model="user.phone">
                </label>
                <div class="tip" v-show="showphoneTip">
                    {{ phoneTip }}
                </div>
            </div>
            <div class="ll">
                <label for="checkNum">验证码<br>
                    <input type="text" style="width: 50%" v-model="checkNum">
                    <button @click="getCode" type="button">{{btnContent}}</button>
                </label>
                <div class="tip" v-show="showCheckNumTip">
                    {{ checkNumTip }}
                </div>
            </div>
            <div class="ll">
                <a v-on:click="toLogin">登录</a>
                <button style="margin-left: 50px" v-on:click="register" type="button">注册</button>
            </div>
        </form>
    </div>
</template>

<script lang="ts" src="./register.ts"></script>
