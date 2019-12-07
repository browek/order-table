import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '@shared/models';

@Injectable()
export class UserService {

  public user: User;
  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/users');
  }

  deleteUser(id: Number) {
    return this.http.delete(`/api/users/${id}`);
  }

  banUser(user: User) {
    return this.http.put(`/api/users/${user.id}`, user);
  }

}
