import { Component, OnInit } from '@angular/core';
import { User } from '@app/shared/models';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'status', 'actions'];
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  private getUsers = () => {
    this.userService.getUsers().subscribe((users: any) => {
      this.users = users._embedded.users;
    });
  }

  disableAccount(user: User) {
    this.userService.disableUser(user.id)
      .subscribe((resp: any) => {
        this.getUsers();
      });
  }

  deleteAccount(userId: number) {
    this.userService.deleteUser(userId)
      .subscribe((resp: any) => {
        this.getUsers();
      });
  }
}
