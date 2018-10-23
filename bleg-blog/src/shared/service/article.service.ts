import { Http } from '../../shared/Http';
import { Article } from '../model/article.model';

export class ArticleService {
    private http = new Http();

    // id获取博客
    public getArticle(id: string) {
        return this.http.get('/article', {id})
        .then((res) => res );
    }

    // 保存博客
    public saveArticle(article: Article) {
        return this.http.post('/article', article)
        .then((res) => res );
    }

    // 分页查询博客
    public queryForPage(page: number, size: number) {
        const params = {
            size,
            page,
        };
        return this.http.get('/articles', params)
        .then((res) =>  res );
    }
}
