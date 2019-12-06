import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app';

  backgroundImg = [
    // '/assets/background/1.jpg',
    // '/assets/background/2.jpg',
    // '/assets/background/3.jpg',
    // '/assets/background/4.jpg',
    '/assets/background/5.jpg'
  ];
  randomNumber = Math.floor(Math.random() * this.backgroundImg.length);
  bgImg = this.backgroundImg[this.randomNumber];

  isOpen = false;
  nav;

  // sidenav(event) {
  //   console.log(event);
  //   return this.nav = event;
  // }

  checkStatus() {
    console.log(this.isOpen);
    this.isOpen = !this.isOpen;
    return this.isOpen;
  }
}
