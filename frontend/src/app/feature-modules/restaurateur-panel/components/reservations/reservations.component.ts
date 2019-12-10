import { Component, OnInit } from '@angular/core';
import { ReservationResponse, ReservationsRange, ReservationStatus } from '@app/shared/models';
import { ReservationService } from '@app/shared/services/reservation.service';
import { RestaurateurService } from '@shared/services/restaurateur.service';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { endOfMonth, format, startOfDay, startOfMonth, isSameMonth, isSameDay } from 'date-fns';
import { map } from 'rxjs/operators';
import { Restaurant } from '../../models';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  green: {
    primary: '#11c123',
    secondary: '#a5e5ab'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};

interface BaseCalendarEvent {
  start: Date;
  title: string;
  color: any;
}

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent implements OnInit {

  private readonly DATE_FORMAT = 'YYYY-MM-DD';
  private readonly TIME_FORMAT = 'HH:MM';
  restaurants: Restaurant[] = [];
  viewDate = new Date();
  activeDayIsOpen = false;

  view: CalendarView = CalendarView.Month;

  events: CalendarEvent[] = [];

  restaurantId: number;

  constructor(
    private restaurantService: RestaurateurService,
    private reservationService: ReservationService
  ) { }

  ngOnInit() {
    this.fetchRestaurants();
  }

  fetchRestaurants = () => {
    this.restaurantService.getRestaurants()
      .subscribe((response: any) => {
        this.restaurants = response._embedded.activatedRestaurants || [];
      });
  }

  switchDate($event: Date): void {
    this.activeDayIsOpen = false;

    if (!!this.restaurantId) {
      this.fetchReservations($event);
    }
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;

      if ((isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) || events.length === 0) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
    }
  }

  handleChangeRestaurant() {
    this.fetchReservations(this.viewDate);
  }

  fetchReservations(date: Date): void {
    const dateFrom = startOfMonth(date);
    const dateTo = endOfMonth(date);

    this.fetchReservationsByRange(dateFrom, dateTo);
  }

  fetchReservationsByRange(dateFrom: Date, dateTo: Date): void {
    const reservationsRange: ReservationsRange = {
      dateFrom: format(dateFrom, this.DATE_FORMAT),
      dateTo: format(dateTo, this.DATE_FORMAT),
      restaurantId: this.restaurantId + ''
    };

    this.reservationService.getReservationsByRange(reservationsRange)
      .pipe(
        map((reservations: ReservationResponse[]): BaseCalendarEvent[] => {
          return reservations.map(reservation => {
            return {
              title: this.getReservationTitle(reservation),
              start: startOfDay(reservation.reservationDateTime),
              color: this.getReservationColor(reservation)
            };
          });
        })
      )
      .subscribe((calendarEvents: BaseCalendarEvent[]) => {
        this.events = calendarEvents;
      });
  }

  private getReservationTitle = (reservation: ReservationResponse): string => {
    return `Liczba osób: ${reservation.numberOfPersons}, godzina: ${format(reservation.reservationDateTime, this.TIME_FORMAT)},
      wiadomość użytkownika: ${reservation.clientMessage || 'Brak'}`;
  }

  private getReservationColor = (reservation: ReservationResponse): any => {
    switch (reservation.status) {
      case ReservationStatus.REJECTED_BY_RESTAURANT:
        return colors.red;

      case ReservationStatus.ACCEPTED_BY_RESTAURANT:
        return colors.green;

      case ReservationStatus.SEND:
        return colors.yellow;
    }
  }
}
