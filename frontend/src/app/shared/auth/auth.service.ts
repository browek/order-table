import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {console.log(environment); }

  login(login: string, password: string) {
    // TODO http.post(`${API_URL}/login`)...
    console.log(login);
    console.log(password);
  }
  register(login: string, password: string, email: string) {
    // TODO http.post(`${API_URL}/users/signup`)...
    console.log(login);
    console.log(password);
    console.log(email);
  }
}
