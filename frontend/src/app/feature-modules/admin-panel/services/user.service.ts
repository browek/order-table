import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { User } from '@shared/models/user';

const API_URL = environment.apiUrl;

@Injectable()
export class UserService {

  public user: User;
  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  deleteUser(id: Number) {
    return this.http.delete(`${API_URL}/users/${id}`);
  }

  banUser(user: User) {
    return this.http.put(`${API_URL}/users/${user.id}`, user);
  }

}
