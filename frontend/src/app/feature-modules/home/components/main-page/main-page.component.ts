import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  public isOpen = false;

  backgroundImg = [
    '/assets/background/1.jpg',
    '/assets/background/2.jpg',
    '/assets/background/3.jpg',
    '/assets/background/4.jpg'
  ];
  randomNumber = Math.floor(Math.random() * this.backgroundImg.length);
  bgImg = this.backgroundImg[this.randomNumber];

  constructor() { }

  ngOnInit() {
  }

  sidenavToggle() {
    this.isOpen = !this.isOpen;
  }

}
