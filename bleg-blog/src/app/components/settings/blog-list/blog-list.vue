<template>
    <div class="blog-setting">
        <el-row>
            <el-col class="" :span="24">
                <div class="blog-list">
                    <el-form class="" label-width="80px">
                        <el-form-item label="内容">
                            <el-input v-model="content"></el-input>
                        </el-form-item>
                        <el-form-item label="发布时间">
                            <el-date-picker v-model="publishTimes" type="daterange" align="right" unlink-panels range-separator="至"
                                start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="修改时间">
                            <el-date-picker v-model="editTimes" type="daterange" align="right" unlink-panels range-separator="至"
                                start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="标签">
                            <el-select v-model="tags" multiple filterable allow-create default-first-option placeholder="请选择文章标签" style="width: 50%">
                                <el-option v-for="item in totaltags" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="">查询</el-button>
                        </el-form-item>
                    </el-form>
                    <div class="blog" v-for="article in articlePage">
                        <a @click="toBlog(article.id)">
                            <h3>{{ article.title }}</h3>
                            <div>
                                {{ article.summary }}
                            </div>
                        </a>
                        <p>创建时间:<a>{{ article.publishTime | timeFormate }}</a> / 修改时间:<a>{{ article.editTime | timeFormate }}</a></p>
                        <div class="tags">
                            <div class="tags-in">
                                <span>
                                    <a>spring</a>
                                    <a>spring</a>
                                    <a>spring</a>
                                </span>
                                <a style="float: right"><i class="fa fa-trash" style=""></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="block">
                        <el-pagination @current-change="getPageNumber"
                            :current-page="pageNum" 
                            :page-size="pagesize"
                            layout="total, prev, pager, next, jumper"
                            :total="totalElements">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts" src="./blog-list.ts"></script>
