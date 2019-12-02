import { AuthService } from './../shared/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  loginControl: FormGroup;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.loginControl = new FormGroup({
      login: new FormControl(null),
      password: new FormControl(null),
      },
    );
  }

  login(loginControl: FormGroup) {
      this.authService.login(loginControl.value.login, loginControl.value.password);
  }
}
