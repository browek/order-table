import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Restaurant } from '../../models';
import { MatPaginator, MatSort } from '@angular/material';
import { merge, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';
import { RestaurateurService } from '@app/shared/services';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.scss']
})
export class RestaurantsComponent implements OnInit {

  restaurants: Restaurant[] = [];
  resultsLength = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private restaurantService: RestaurateurService) { }

  ngOnInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.paginator.pageSize = 5;
    this.handleTableChanges();
  }

  handleTableChanges = () => {
    merge(this.sort.sortChange, this.paginator.page, this.paginator.pageSize)
      .pipe(
        startWith({}),
        switchMap(() => {
          const params = {
            sort: `${this.sort.active},${this.sort.direction}`,
            page: this.paginator.pageIndex + '',
            size: this.paginator.pageSize + ''
          };

          return this.restaurantService.getRestaurants(params);
        }),
        map((data: any) => {
          this.resultsLength = data.page.totalElements;

          return data._embedded.restaurants;
        }),
        catchError(() => {
          return of([]);
        })
      )
      .subscribe(data => this.restaurants = data);
  }

}
