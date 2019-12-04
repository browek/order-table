import { Component, OnInit } from '@angular/core';
import { UserService } from '@features/admin-panel/services/user.service';
import { User } from '@shared/models/user';

export interface Users {
  id: number;
  username: String;
}

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.scss']
})

export class AdminLayoutComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'ban', 'delete'];
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.userService.getUsers().subscribe((users: any) => {
      this.users = users._embedded.users;
    });
  }

}
