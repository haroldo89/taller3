import { Injectable } from '@angular/core';
import * as io from 'socket.io-client';
import { HttpClient, HttpHeaders, } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token',
    'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE'
  })
};

@Injectable({
  providedIn: 'root'
})
export class SocketioService {

  private socketPoint = 'http://localhost:3000';
  private endpoint = 'http://localhost:3000/message/sendMessage';

  private socket: any;

  constructor(private http: HttpClient) {
    this.socket = io(this.socketPoint);
  }

  sendMessage(message: string): Observable<string> {
    const params = {
      format: message
    };
    return this.http.post<string>(this.endpoint, params)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  public getMessages = () => {
    return Observable.create((observer) => {
      this.socket.on('new-message', (message) => {
        observer.next(message);
      });
    });
  }


  // Error handling 
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }


}
