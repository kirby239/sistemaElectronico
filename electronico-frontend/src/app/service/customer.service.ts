import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { customer } from '../interface/customer';
import { catchError, Observable, throwError } from 'rxjs';
import { MensajeService } from './mensaje.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private URL = environment.HOST + "customer/";

  constructor(private http: HttpClient, private mensaje: MensajeService) { }
  createCustomer(customer: customer): Observable<any> {
    return this.http.post(`${this.URL}save`, customer).pipe(
      catchError(this.handleError.bind(this))
    );
  }

  // Listar todos los cliente
  getAllCustomers(): Observable<any[]> {
    
    return this.http.get<any[]>(`${this.URL}list`) .pipe(
      catchError(this.handleError.bind(this)));

    
  }

  private handleError(error: HttpErrorResponse) {
    return throwError(() => this.mensaje.MostrarMensaje(error.error['message']));
  }

}
