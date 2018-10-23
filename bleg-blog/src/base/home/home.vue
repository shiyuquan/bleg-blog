<template>
    <div class="home">
        <div class="header">
            <div class="contanter">
                <el-col :span="4">
                    <a href="https://shiyuquan.github.io/">
                        <div class="logo"></div>
                    </a>
                </el-col>
                <el-col :span="20">
                    <el-menu class="module-menu" mode="horizontal" background-color="#34393E" text-color="#fff" active-text-color="#419BEB">
                        <el-menu-item index="1" @click="toHome">首页</el-menu-item>
                        <el-menu-item index="8" @click="toSettings">个人中心</el-menu-item>
                    </el-menu>
                </el-col>
            </div>
        </div>
        <div class="content">
            <router-view/>
        </div>
    </div>
</template>

<script lang="ts" src="./home.ts"></script>
