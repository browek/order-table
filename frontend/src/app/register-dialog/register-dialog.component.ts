import { AuthService } from './../shared/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { Validators, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent implements OnInit {

  hidingPassword = true;
  hidingPasswordConfirm = true;

  registerControl: FormGroup;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.registerControl = new FormGroup({
      login: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      },
    );
    console.log(this.registerControl);
  }
  register(registerControl: FormGroup) {
    if (registerControl.valid) {
      this.authService.register(registerControl.value.login, registerControl.value.password, registerControl.value.email);
    }
  }
}
