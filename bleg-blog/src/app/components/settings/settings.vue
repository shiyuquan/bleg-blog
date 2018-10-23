<template>
    <div class="setting-contanter">
        <el-row class="settings">
            <el-col class="" :span="6">
                <el-menu default-active="1" class="menu" router>
                    <el-menu-item index="/settings/user-setting" >
                        <i class="el-icon-menu"></i>
                        <span slot="title">个人信息</span>
                    </el-menu-item>
                    <el-menu-item index="/settings/blog-list">
                        <i class="el-icon-menu"></i>
                        <span slot="title">博客列表</span>
                    </el-menu-item>
                    <el-menu-item index="/settings/handle-blog" @click="toHandleBlog">
                        <i class="el-icon-menu"></i>
                        <span slot="title">新增博客</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-menu"></i>
                        <span slot="title">草稿箱</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-menu"></i>
                        <span slot="title">垃圾箱</span>
                    </el-menu-item>
                </el-submenu>
                </el-menu>
            </el-col>
            <el-col :span="18">
                <router-view/>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts" src="./settings.ts"></script>
