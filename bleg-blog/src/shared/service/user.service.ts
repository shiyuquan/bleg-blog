import { Http } from '../../shared/Http';
import { User } from '../model/user.model';
export class UserService {
    private http = new Http();

    public login(userName: string, password: string) {
        return this.http.get('/login', {userName, password})
        .then((res) => res );
    }

    public register(user: User, checkNum: string) {
        return this.http.post('/register', user, {checkNum})
        .then((res) => res );
    }

    public uploadFile(form: FormData) {
        return this.http.post('/upload-profilePicture', form)
        .then((res) => res );
    }

    public updateUser(user: User) {
        return this.http.put('/user', user)
        .then((res) => res );
    }
}
