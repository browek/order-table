export interface ReservationRequest {
  id: number;
  numberOfPersons: number;
  clientUsername: string;
  dateAndTime: Date;
  message?: string;
}
