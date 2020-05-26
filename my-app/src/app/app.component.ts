import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Options, ChangeContext } from 'ng5-slider';
import { Modelo } from 'src/model/Modelo';


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
    floor: -360,
    ceil: 360,

  };

  sldFalangeDigDerOpt: Options = {
    floor: -360,
    ceil: 360
  };

  sldFalangeProxIzqOpt: Options = {
    floor: -360,
    ceil: 360
  };

  sldFalangeProxDerOpt: Options = {
    floor: -360,
    ceil: 360
  };

  sldManoOpt: Options = {
    floor: -180,
    ceil: 180
  };

  sldAnteBrazoOpt: Options = {
    floor: -180,
    ceil: 180
  };

  sldBrazoOpt: Options = {
    floor: -90,
    ceil: 90
  };

  modelo: Modelo;

  getModelo(): Modelo {
    if (this.modelo == null) {
      this.modelo = new Modelo(this.ctx);
    }
    return this.modelo;
  }

  constructor() {   }


  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.fillStyle = 'red';
    this.modelo = new Modelo(this.ctx);
    this.getModelo().dibujar(this.ctx);
  }

  onsldBrazoOptChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarBrazo(value, true);
  }

  onsldAnteBrazoOptChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarAnteBrazo(value, true);
  }

  onsldManoValueChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarMano(value, true);
  }

  onsldFalangeProxDerOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarFalProxDer(value, true);
  }

  onsldFalangeProxIzqOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarFalProxIzq(value, true);
  }

  onsldFalangeDigDerOptValueChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarFalDigDer(value, true);
  }

  onsldFalangeDigIzqOptChangeEnd(changeContext: ChangeContext): void {
    let value: number  = +changeContext.value;
    this.getModelo().girarFalDigIzq(value, true);
  }

}
