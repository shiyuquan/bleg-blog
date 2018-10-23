export interface Article {
    id?: string;
    title?: string;
    md?: string;
    html?: string;
    summary?: string;
    publishTime?: Date;
    views?: number;
    state?: number; // 0表示草稿箱，1表示已发表，2表示已删除

    // 编辑时间相关
    editTime?: Date;
    editYeat?: string;
    editMonth?: string;
    editDay?: string;
    //
    categoryId?: string;
    // 用户信息
    userId?: string;
}
