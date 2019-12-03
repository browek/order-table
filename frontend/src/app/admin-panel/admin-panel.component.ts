import { Component, OnInit } from '@angular/core';
import { User } from '../shared/model/user';
import { UserService } from '../shared/services/user. service';

export interface Users {
  id: number;
  username: String;
}

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})

export class AdminPanelComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'ban', 'delete'];
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.userService.getUsers().subscribe((users: any) => {
      this.users = users._embedded.users;
    });
  }

}
