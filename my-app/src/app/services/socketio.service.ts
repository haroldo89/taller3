import { Injectable } from '@angular/core';
import * as io from 'socket.io-client';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class SocketioService {

  private url = 'http://localhost:3000';

  private socket: any;

  constructor() {
    this.socket = io(this.url);
  }

  public sendMessage(message: any) {
    this.socket.emit('new-message', message);
  }


  public getMessages = () => {
    return Observable.create((observer) => {
        this.socket.on('new-message', (message) => {
            observer.next(message);
        });
    });
}

}
