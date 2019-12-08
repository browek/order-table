import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy, ChangeDetectorRef, Input } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { ThemePalette } from '@angular/material';
import { SidenavService } from '../../services';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit, OnDestroy {

  @Input()
  toolbarColor: ThemePalette; // default primary
  homeIconColor: ThemePalette;

  sidenavOpened: boolean;
  subscription: Subscription;

  mobileQuery: MediaQueryList;

  private mobileQueryListener: () => void;

  constructor(private changeDetectorRef: ChangeDetectorRef, private media: MediaMatcher, private sidenavService: SidenavService) {
  }

  ngOnInit(): void {
    this.mobileQuery = this.media.matchMedia('(max-width: 600px)');
    this.mobileQueryListener = () => this.changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this.mobileQueryListener);

    this.homeIconColor = this.toolbarColor === 'primary' ? 'accent' : 'primary';
    this.subscription =  this.sidenavService.sidenavOpened.subscribe(opened => this.sidenavOpened = opened);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this.mobileQueryListener);
    this.subscription.unsubscribe();
  }
}
