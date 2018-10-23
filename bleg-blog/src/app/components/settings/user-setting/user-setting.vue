<template>
    <div class="user-setting">
        <div>
            <div class="sub-head">
                <h2>个人信息</h2>
            </div>
            <form class="user-setting-from">
                <el-row class="">
                    <el-col class="column" :span="16">
                        <dl class="form-group">
                            <dt><label for="user-name"><font>用户名</font></label></dt>
                            <dd><input class="form-input" id="user-name" v-model="user.userName"></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-nickName"><font>昵称</font></label></dt>
                            <dd><input class="form-input" id="user-nickName" v-model="user.nickName"></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-description"><font>个性签名</font></label></dt>
                            <dd><textarea class="form-input" id="user-description" v-model="user.description"></textarea></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-qq"><font>qq</font></label></dt>
                            <dd><input class="form-input" id="user-qq" v-model="user.qq"></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-email"><font>邮箱</font></label></dt>
                            <dd><input class="form-input" id="user-email" v-model="user.email"></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-birthday"><font>生日</font></label></dt>
                            <dd>
                                <el-date-picker v-model="user.birthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
                            </dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-phone"><font>手机号码</font></label></dt>
                            <dd><input class="form-input" id="user-phone" v-model="user.phone"></dd>
                        </dl>
                        <dl class="form-group">
                            <dt><label for="user-address"><font>住址</font></label></dt>
                            <dd><textarea class="form-input" id="user-address" v-model="user.address"></textarea></dd>
                        </dl>
                        <dl class="form-group">
                            <dd><el-button @click="updateUser">保存修改</el-button></dd>
                        </dl>

                    </el-col>
                    <el-col class="" :span="8">
                        <dl class="form-group">
                            <dt><label for="user-profilePicture"><font>头像</font></label></dt>
                            <el-upload class="avatar-uploader" :show-file-list="false" action=""
                                :on-change="fileChange" :before-upload="beforeAvatarUpload" :auto-upload="true" :http-request="uploadFile">
                                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>

                        </dl>
                    </el-col>
                </el-row>
            </form>
        </div>
    </div>
</template>

<script lang="ts" src="./user-setting.ts"></script>
