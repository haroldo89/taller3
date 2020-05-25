import { Component, ViewChild, ElementRef, OnInit, NgZone } from '@angular/core';
import { Square } from './square';
import { Options } from 'ng5-slider';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-app';
  @ViewChild('canvas', { static: true }) canvas: ElementRef<HTMLCanvasElement>;
  ctx: CanvasRenderingContext2D;
  requestId;
  interval;
  squares: Square[] = [];

  sldFalangeDigIzqValue: number = 0;
  sldFalangeDigDerValue: number = 0;
  sldFalangeProxIzqValue: number = 0;
  sldFalangeProxDerValue: number = 0;
  sldManoValue: number = 0;
  sldAnteBrazoValue: number = 0;
  sldBrazoValue: number = 0;
  
  sldFalangeDigIzqOpt: Options = {
    floor: -360,
    ceil: 360
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

  constructor(private ngZone: NgZone) { }

  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.fillStyle = 'red';
    this.ngZone.runOutsideAngular(() => this.tick());
    setInterval(() => {
      this.tick();
    }, 200);
  }

  tick() {
    this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    this.squares.forEach((square: Square) => {
      square.moveRight();
    });
    this.requestId = requestAnimationFrame(() => this.tick);
  }

  play() {
    const square = new Square(this.ctx);
    this.squares = this.squares.concat(square);
  }

  ngOnDestroy() {
    clearInterval(this.interval);
    cancelAnimationFrame(this.requestId);
  }

}
