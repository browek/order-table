import { environment } from './../../../environments/environment';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../model/user";
import { Injectable } from "@angular/core";

const API_URL = environment.apiUrl;

@Injectable()
export class UserService {

  public user = new User();
  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(API_URL + '/users');
  }

  deleteUser(id: Number) {
    return this.http.delete(`${API_URL}/users/${id}`);
  }

  banUser(user: User) {
    return this.http.put(`${API_URL}/users/${user.id}`, user);
  }

}
