import { BrazoRobot } from "src/logica/BrazoRobot";
import { Constantes } from 'src/utils/Constantes';

export class Modelo {
  brazoCompleto: BrazoRobot;
  constantes: Constantes;

  getBrazoCompleto(): BrazoRobot {
    if (this.brazoCompleto == null) {
      this.brazoCompleto = new BrazoRobot();
    }
    return this.brazoCompleto;
  }

  constructor(private ctx: CanvasRenderingContext2D) {
    this.brazoCompleto = new BrazoRobot();
    this.constantes = new Constantes();
  }

  //METODOS
  girarFalDigIzq(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoIzq.huesoUnido.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarFalDigDer(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoDer.huesoUnido.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarFalProxIzq(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoIzq.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarFalProxDer(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoDer.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarMano(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().mano.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarAnteBrazo(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().anteBrazo.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  girarBrazo(alphaGrados: number, isEnviado: boolean) {
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().brazo.girarHueso(alphaGrados);
    this.dibujar(this.ctx);
  }

  dibujar(lapiz: CanvasRenderingContext2D) {
    console.log("Diujando...");
    lapiz.clearRect(0, 0, this.constantes.ANCHO_MAXIMO_CANVAS, this.constantes.ALTO_MAXIMO_CANVAS);
    this.getBrazoCompleto().dibujarBrazo(lapiz);
  }

}
