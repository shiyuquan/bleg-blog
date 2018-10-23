<template>
    <div class="article-contanter" aos-easing="ease-out-back" aos-duration="1000" aos-delay="0">
        <el-row>
            <el-col class=""  :span="16">
                <div class="article"> 
                    <div style="display: block; border-bottom: 1px solid #eee;">
                        <div class="title-article">
                            <h1>
                                <a href="https://www.mygalgame.com/gengxinrizhi.html" target="_blank">
                                    <span class="animated_h1"> {{ article.title }} </span>
                                </a>
                            </h1>
                        </div>
                        <div class="tag-article">
                            <span class="tag">
                                <i class="fa fa-tags"></i> tag1
                            </span>
                            <span class="tag">
                                <i class="fa fa-eye"></i>{{ article.views }}
                            </span>
                            <span class="tag">
                                <i class="fa fa-comments"></i>
                                <a href="https://www.mygalgame.com/gengxinrizhi.html"> 65 </a>
                            </span>
                        </div>
                    </div>
                    <div class="article-content" v-html="article.html">
                    </div>
                </div>
            </el-col>
            <el-col :span="8">
                <aside id="aside-search">
                    <div class="search aos-animate" aos="fade-up" aos-duration="2000" name="items">
                        <form>
                            <input class="input-search" type="text" placeholder="收不到?">
                            <button type="submit" class="btn-search">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                </aside>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts" src="./article-page.ts"></script>
