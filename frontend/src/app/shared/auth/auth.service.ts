import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  login(login: string, password: string) {
    console.log(login);
    console.log(password);
  }
  register(login: string, password: string, email: string) {
    console.log(login);
    console.log(password);
    console.log(email);
  }
}
