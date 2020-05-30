import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Options, ChangeContext } from 'ng5-slider';
import { Modelo } from 'src/model/Modelo';
import { SocketioService } from './services/socketio.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-app';
  @ViewChild('canvas', { static: true }) canvas: ElementRef<HTMLCanvasElement>;
  ctx: CanvasRenderingContext2D;

  sldFalangeDigIzqValue: number = 0;
  sldFalangeDigDerValue: number = 0;
  sldFalangeProxIzqValue: number = 0;
  sldFalangeProxDerValue: number = 0;
  sldManoValue: number = 0;
  sldAnteBrazoValue: number = 0;
  sldBrazoValue: number = 0;

  sldFalangeDigIzqOpt: Options = {
    floor: -60,
    ceil: 30,
  };

  sldFalangeDigDerOpt: Options = {
    floor: -30,
    ceil: 60
  };

  sldFalangeProxIzqOpt: Options = {
    floor: -90,
    ceil: 0
  };

  sldFalangeProxDerOpt: Options = {
    floor: 0,
    ceil: 90
  };

  sldManoOpt: Options = {
    floor: -90,
    ceil: 90
  };

  sldAnteBrazoOpt: Options = {
    floor: -90,
    ceil: 90
  };

  sldBrazoOpt: Options = {
    floor: -90,
    ceil: 90
  };

  modelo: Modelo;
  messages: any;

  getModelo(): Modelo {
    if (this.modelo == null) {
      this.modelo = new Modelo(this.ctx);
    }
    return this.modelo;
  }

  constructor(private socketService: SocketioService) { }


  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.fillStyle = 'red';
    this.modelo = new Modelo(this.ctx);
    this.getModelo().dibujar(this.ctx);

    // Obtener mensajes socket
    this.socketService
      .getMessages()
      .subscribe((message: string) => {
        this.moveArmPart(message);
        console.log('aca voy a poner un mensaje:' + message);
      });
  }

  // Enviar mensaje a socket
  sendMessage(message: any) {
    this.socketService.sendMessage(message).subscribe();
  }

  moveArmPart(responseData: string) {
    let response = responseData.split(':');
    if (response[0] === 'sldBrazoOpt') {
      this.sldBrazoValue = +response[1];
      this.getModelo().girarBrazo(+response[1], true);
    } else if (response[0] === 'sldAnteBrazoOpt') {
      this.sldAnteBrazoValue = +response[1];
      this.getModelo().girarAnteBrazo(+response[1], true);
    } else if (response[0] === 'sldManoOpt') {
      this.sldManoValue = +response[1];
      this.getModelo().girarMano(+response[1], true);
    } else if (response[0] === 'sldFalangeProxDerOpt') {
      this.sldFalangeProxDerValue = +response[1];
      this.getModelo().girarFalProxDer(+response[1], true);
    } else if (response[0] === 'sldFalangeProxIzqOpt') {
      this.sldFalangeProxIzqValue = +response[1];
      this.getModelo().girarFalProxIzq(+response[1], true);
    } else if (response[0] === 'sldFalangeDigDerOpt') {
      this.sldFalangeDigDerValue = +response[1];
      this.getModelo().girarFalDigDer(+response[1], true);
    } else if (response[0] === 'sldFalangeDigIzqOpt') {
      this.sldFalangeDigIzqValue = +response[1];
      this.getModelo().girarFalDigIzq(+response[1], true);
    }
  }

  onsldBrazoOptChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarBrazo(value, true);
    this.sendMessage('sldBrazoOpt:' + value);
  }

  onsldAnteBrazoOptChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarAnteBrazo(value, true);
    this.sendMessage('sldAnteBrazoOpt:' + value);
  }

  onsldManoValueChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarMano(value, true);
    this.sendMessage('sldManoOpt:' + value);
  }

  onsldFalangeProxDerOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarFalProxDer(value, true);
    this.sendMessage('sldFalangeProxDerOpt:' + value);
  }

  onsldFalangeProxIzqOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarFalProxIzq(value, true);
    this.sendMessage('sldFalangeProxIzqOpt:' + value);
  }

  onsldFalangeDigDerOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarFalDigDer(value, true);
    this.sendMessage('sldFalangeDigDerOpt:' + value);
  }

  onsldFalangeDigIzqOptChangeEnd(changeContext: ChangeContext): void {
    let value: number = +changeContext.value;
    this.getModelo().girarFalDigIzq(value, true);
    this.sendMessage('sldFalangeDigIzqOpt:' + value);
  }

}
