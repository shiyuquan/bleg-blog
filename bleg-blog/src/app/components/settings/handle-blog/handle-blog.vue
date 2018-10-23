<template>
    <div class="handle-blog">
        <div>
            <h2> {{ isAdd ? '新增': '修改'}}博客</h2>
        </div>
        <div class="blog-set">
            <div class="group">
                标题: <el-input v-model="article.title" placeholder="请输入内容" clearable style="width: 70%;"></el-input>
            </div>
            <div>
            标签: <el-select v-model="tags" multiple filterable allow-create default-first-option placeholder="请选择文章标签" style="width: 50%">
                    <el-option v-for="item in totaltags" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </div>
        </div>
        <div class="editor">
            <mavon-editor ref=md v-model="md" codeStyle="vs2015" :scrollStyle="true" :subfield="false" style="height: 200px"></mavon-editor>
            <div class="save-btns">
                <el-button @click="saveArticle(1)">发布</el-button>
                <el-button @click="saveArticle(0)">保存草稿</el-button>
            </div>
        </div>

        <div class="article"> 
            <div style="display: block; border-bottom: 1px solid #eee;" v-if="article.title">
                <div class="title-article">
                    <h1>
                        <a href="https://www.mygalgame.com/gengxinrizhi.html" target="_blank">
                            <span class="animated_h1"> {{ article.title }} </span>
                        </a>
                    </h1>
                </div>
                <div class="tag-article">
                    <span class="tag" >
                        <i class="fa fa-tags"></i> 
                        <label v-for="tag in tags"> #{{ tag }} </label>
                    </span>
                    <span class="tag">
                        <i class="fa fa-eye"></i> {{ article.views }}
                    </span>
                    <span class="tag">
                        <i class="fa fa-comments"></i>
                        <a href="https://www.mygalgame.com/gengxinrizhi.html"> comment </a>
                    </span>
                </div>
            </div>

            <div class="article-content" v-html="article.html">
            </div>
        </div>
    </div>

    
</template>

<script lang="ts" src="./handle-blog.ts"></script>

