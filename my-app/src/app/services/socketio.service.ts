import { Injectable } from '@angular/core';
import * as io from 'socket.io-client';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SocketioService {

  private socketPoint = 'http://localhost:3000';
  private endpoint = 'http://localhost:3000/message/sendMessage';
  private  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };


  private socket: any;

  constructor(private http: HttpClient) {
    this.socket = io(this.socketPoint);
  }

  public sendMessage(message: any) {
    this.socket.emit('new-message', message);
  }

  // public sendMessage(message: string) {
  //   let params = new HttpParams();
  //   params = params.append("format", message);
  //   this.http.get(this.sendMessagePoint, {params: params}); 
  // }

  public getMessages = () => {
    return Observable.create((observer) => {
        this.socket.on('new-message', (message) => {
            observer.next(message);
        });
    });
}

}
