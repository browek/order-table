import { Restaurant } from '@features/restaurateur-panel/models';

export interface ReservationResponse {
  id: number;
  numberOfPersons: number;
  reservationDateTime: Date;
  message: string;
  status: ReservationStatus;
  active: boolean;
  restaurant?: Restaurant;
  notifications: any[];
}

export enum ReservationStatus {
  SEND = 'SEND',
  ACCEPTED_BY_RESTAURANT = 'ACCEPTED_BY_RESTAURANT',
  ACCEPTED_BY_CLIENT = 'ACCEPTED_BY_CLIENT',
  REJECTED_BY_CLIENT = 'REJECTED_BY_CLIENT',
  REJECTED_BY_RESTAURANT = 'REJECTED_BY_RESTAURANT'
}
