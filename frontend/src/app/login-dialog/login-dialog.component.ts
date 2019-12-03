import {AuthService} from './../shared/auth/auth.service';
import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router';
import {DialogService} from '../shared/services/dialog.service';
import {MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  loginControl: FormGroup;
  loginFailed: boolean;

  constructor(private authService: AuthService, public dialogRef: MatDialogRef<LoginDialogComponent>) {
  }

  ngOnInit() {
    this.loginFailed = false;
    this.loginControl = new FormGroup({
        login: new FormControl(null),
        password: new FormControl(null),
      },
    );
  }

  login(loginControl: FormGroup) {
    this.authService.login(
      loginControl.value.login,
      loginControl.value.password,
      this.onSuccess,
      this.onFail
    );
  }

  private onSuccess = () => {
    this.loginFailed = false;
    this.dialogRef.close();
  }

  private onFail = () => {
    this.loginFailed = true;
  }
}

