export interface ReservationRequest {
  id: number;
  numberOfPersons: number;
  dateAndTime: Date;
  message?: string;
}
