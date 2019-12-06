import {environment} from './../../../environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {AccountInfo, Role} from '../model/account-info';

const API_URL = environment.apiUrl;
export const AUTHORIZATION_HEADER = 'Authorization';
const AUTHORIZATION_KEY = 'authorization';
const USERNAME_KEY = 'username';
const ROLE_KEY = 'role';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(login: string, password: string, onSuccess?: () => void, onFail?: () => void) {
    console.log('x');
    this.http.post<AccountInfo>(
      `${API_URL}/login`,
      {username: login, password: password},
      {observe: 'response'}
    ).subscribe(
      (response: HttpResponse<AccountInfo>) => {
        const token = response.headers.get(AUTHORIZATION_HEADER);
        this.storeAuthorization(token);

        const body = response.body;
        this.storeAccountInfo(body);

        if (onSuccess) {
          onSuccess();
        }

      }, (error: HttpResponse<Object>) => {
        if (onFail) {
          onFail();
        }
      }
    );
  }

  getAuthorization(): string {
    return localStorage.getItem(AUTHORIZATION_KEY);
  }

  private storeAuthorization(authToken: string) {
    localStorage.setItem(AUTHORIZATION_KEY, authToken);
  }

  private storeAccountInfo(accountInfo: AccountInfo) {
    localStorage.setItem(USERNAME_KEY, accountInfo.username);
    localStorage.setItem(ROLE_KEY, accountInfo.role.toString());
  }

  logout() {
    localStorage.removeItem(AUTHORIZATION_KEY);
    localStorage.removeItem(USERNAME_KEY);
    localStorage.removeItem(ROLE_KEY);
  }

  isLogged() {
    return localStorage.getItem(AUTHORIZATION_KEY) !== null;
  }

  isAdmin(): boolean {
    return this.hasRole(Role.ADMIN);
  }

  isClient(): boolean {
    return this.hasRole(Role.CLIENT);
  }

  isRestaurateur(): boolean {
    return this.hasRole(Role.RESTAURATEUR);
  }

  private hasRole(role: Role): boolean {
    return localStorage.getItem('role') === role.toString();
  }


  register(login: string, password: string, email: string, userType: string ,onFail?: () => void, onSuccess?: () => void) {
    let url = `${API_URL}/users/signup`;
    if (userType === 'RESTAURATEUR') {
      url = url + 'Restaurateur';
    } else {
      url = url + 'Client';
    }

    this.http.post(url, {username: login, password: password})
      .subscribe(success => {
        this.login(login, password);

        if (onSuccess) {
          onSuccess();
        }
      }, error => {
        if (onFail) {
          if (error.message === 'USERNAME_ALREADY_EXISTS') {
            onFail();
          }
        }
      });

    console.log(login);
    console.log(password);
    console.log(email);
  }
}
