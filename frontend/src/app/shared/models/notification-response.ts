import { ReservationStatus } from './reservation-response';

export interface NotificationResponse {
  id: number;
  displayed: boolean;
  reservationDate: Date;
  restaurant: {
    name: string,
    city: string,
    street: string
  };
  restaurantMessage: string;
  notificationDate: Date;
  reservationStatus: ReservationStatus;
  reservationId: number;
}
