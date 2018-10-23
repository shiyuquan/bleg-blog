<template>
    <div class="home">
        <form class="login-register-form">
            <h3>登录</h3>
            <div class="ll">
                <label for="userName">用户名<br>
                    <input type="text" id="userName" class="" size="20" v-model="userName">
                </label>
                <div class="tip" v-show="showUserNameTip">
                    {{ userNameTip }}
                </div>
            </div>
            <div class="ll">
                <label for="password">密码<br>
                    <input type="password" id="password" class="" size="25" v-model="password">
                </label>
                <div class="tip" v-show="showPasswordTip">
                    {{ passwordTip }}
                </div>
            </div>
            <div class="ll">
                <label for="checkNum">验证码: {{jisuan}}<br>
                    <input type="text" id="checkNum" v-model="checkNum">
                </label>
                <div class="tip" v-show="showCheckNumTip">
                    {{ checkNumTip }}
                </div>
            </div>
            <div class="ll">
                <a v-on:click="toRegister">注册</a>
                <button style="margin-left: 50px" type="button" @click="login">登录</button>
            </div>
        </form>
    </div>
</template>

<script lang="ts" src="./login.ts"></script>
