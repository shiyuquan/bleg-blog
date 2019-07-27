<template>
    <div class="contanter" aos-easing="ease-out-back" aos-duration="1000" aos-delay="0">
        <el-row>
            <el-col class=""  :span="16">
                <!-- 公告 -->
                <div class="announcement" v-show="isShowAnnouncement"> 
                    <button type="button" class="close" v-on:click="closeAnnouncement">×</button>
                     官方Q群：280648831
                     <br>
                     <br>
                     <b>禁止直接复制粘贴或直接盗用本人百度盘链接在站外分享，请自己转存后自己创建分享链接
                        <br>
                        <br>
                        浏览网站卡顿请使用官方Chrome浏览器或切换主题
                    </b> 
                </div>
                <!-- 主体 -->
                <div class="article-list">
                    <div v-for="article in articlePageDate" class="article-list-item clearfix" aos="flip-up" name="items">
                        <i class="fa fa-bookmark bookmark"></i>
                        <div class="date-article">
                            <span class="month">{{ article.editTime | timeToMonth }}月</span>
                            <span class="day">{{ article.editTime | timeToDay }}</span>
                        </div>
                        <div style="display: block">
                            <div class="title-article">
                                <h1>
                                    <a v-on:click="linkTo(article.id)" target="_blank">
                                        <span class="animated_h1">{{ article.title }}</span>
                                    </a>
                                </h1>
                            </div>
                            <div class="tag-article">
                                <span class="tag">
                                    <i class="fa fa-eye"></i> {{ article.views }}
                                </span>
                                <span class="tag">
                                    <i class="fa fa-comments"></i>
                                    <a v-on:click="linkTo(article.id)">{{ article.comment }}</a>
                                </span>
                            </div>
                            <div class="preface-article">
                                <div class="img-info">
                                    <a v-on:click="linkTo(article.id)" target="_blank">
                                        <div class="img">
                                            <img src="https://ws1.sinaimg.cn/large/005ODKsIgy1fip1i5u5v7j30iw0c7myf.jpg" alt="">
                                        </div>
                                        <div class="info">
                                            <p>
                                                {{ article.preface}}
                                            </p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div>
                    <ul class="ul-page">
                        <li v-for="n in showPageList">
                            <a v-on:click="queryForPage(n)" name="pageItems" v-if="n === -2">
                                «
                            </a>
                            <a v-on:click="queryForPage(n)" name="pageItems" v-else-if="n === -1">
                                »
                            </a>
                            <a v-on:click="" name="pageItems" v-else-if="n === 0">
                                 ...
                            </a>
                            <a v-on:click="queryForPage(n)" name="pageItems" v-else>
                                {{ n }}
                            </a>
                        </li> 
                    </ul>
                </div>
            </el-col>
            <el-col :span="8">
                <aside id="aside-search">
                    <div class="search" aos="fade-up" aos-duration="2000" name="items">
                        <form>
                            <input class="input-search" type="text" placeholder="收不到?">
                            <button type="submit" class="btn-search">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                </aside>
                <aside>
                    <div class="panel" aos="fade-right" aos-duration="2000" name="items">
                        <div v-if="!isLogin">
                            <div class="panel-header">请登录</div>
                            <form class="login-form">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-font">
                                            <i class="fa fa-user"></i>
                                        </div>
                                        <input class="form-control" type="text" size="20" v-model="userName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-font">
                                            <i class="fa fa-lock"></i>
                                        </div>
                                        <input class="form-control" type="password" size="20" v-model="password">
                                    </div>
                                </div>
                                <el-row>
                                    <el-col :span="24" style="margin-bottom: 8px">
                                        <label for="rememberme">
                                            <input type="checkbox" class="rememberme">记住密码</label>
                                        <a class="pull-right">忘记密码</a>
                                    </el-col>
                                    <el-col :span="24">
                                        <button class="login-form-btn pull-left" type="button" @click="login">登录</button>
                                        <button class="login-form-btn pull-right" v-on:click="toRegister" type="button">注册</button>
                                        <button class="login-form-btn pull-right" v-on:click="mapces" type="button">map</button>
                                    </el-col>
                                </el-row>
                            </form>
                        </div>
                        <div v-if="isLogin">
                            <div class="panel-header">
                            <i class="fa fa-quote-left"></i>欢迎!<i class="fa fa-quote-right"></i>
                            </div>
                            <div class="panel-log-in">
                                <el-row>
                                    <el-col :span="6">
                                        <a>
                                            <img v-if="imageUrl" :src="imageUrl" class="panel-img">
                                        </a>
                                    </el-col>
                                    <el-col :span="10">
                                        <a style="padding-top: 10px;">{{ user.nickName }}</a>
                                    </el-col>
                                    <el-col :span="8">
                                        <a class="loging-out" @click="loginOut">退出登录</a>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                    </div>
                </aside>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts" src="./home-page.ts"></script>
