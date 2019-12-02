import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { faSearch, faWrench } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  // @Input()
  // homeSidenav;


  @Output()
  homeSidenav = new EventEmitter<Boolean>();

  backgroundImg = [
    '/assets/background/1.jpg',
    '/assets/background/2.jpg',
    '/assets/background/3.jpg',
    '/assets/background/4.jpg'
  ];
  randomNumber = Math.floor(Math.random() * this.backgroundImg.length);
  bgImg = this.backgroundImg[this.randomNumber];


  faSearch = faSearch;
  faWrench = faWrench;

  constructor() { }

  ngOnInit() {
  }

  sidenavToggle() {
    this.homeSidenav.emit();
  }

}
