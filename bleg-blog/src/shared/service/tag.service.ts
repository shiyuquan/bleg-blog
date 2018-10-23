import { Http } from '../../shared/Http';

export class TagService {
    private http = new Http();

    public saveTag(list: string[], articleId: string) {
        return this.http.post('/tags', list, {articleId})
        .then((res) => res );
    }

    public getTags() {
        return this.http.get('/tag')
        .then((res) => res );
    }
}
