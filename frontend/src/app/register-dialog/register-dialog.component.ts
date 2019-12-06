import { AuthService } from './../shared/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { Validators, FormControl, FormGroup } from '@angular/forms';
import {MatDialogRef} from '@angular/material';
import {LoginDialogComponent} from '../login-dialog/login-dialog.component';


@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent implements OnInit {

  hidingPassword = true;
  hidingPasswordConfirm = true;

  registerControl: FormGroup;

  constructor(private authService: AuthService, public dialogRef: MatDialogRef<RegisterDialogComponent>) { }

  ngOnInit() {
    this.registerControl = new FormGroup({
      login: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      userType: new FormControl('CLIENT')
      },
    );
    console.log(this.registerControl);
  }
  register(registerControl: FormGroup) {
    console.log(registerControl.value.userType);
    if (registerControl.valid) {
      this.authService.register(
        registerControl.value.login,
        registerControl.value.password,
        registerControl.value.email,
        registerControl.value.userType,
        this.onFail, this.onSuccess);
    }
  }

  onFail = () => {
    console.log('fail');
  }

  onSuccess = () => {
    this.dialogRef.close();
  }
}
