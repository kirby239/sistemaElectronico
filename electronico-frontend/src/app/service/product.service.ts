import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { product } from '../interface/product';
import { MensajeService } from './mensaje.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private URL = environment.HOST + "product/";

  constructor(private http: HttpClient, private mensaje: MensajeService) { }
  createProduct(product: product): Observable<any> {
    return this.http.post(`${this.URL}save`, product).pipe(
      catchError(this.handleError.bind(this))
    );
  }
  getAllProduct(): Observable<any[]> {
    return this.http.get<any[]>(`${this.URL}list`).pipe(
      catchError(this.handleError.bind(this))
    );
  }
  private handleError(error: HttpErrorResponse) {
    return throwError(() => this.mensaje.MostrarMensaje(error.error['message']));
  }
}
