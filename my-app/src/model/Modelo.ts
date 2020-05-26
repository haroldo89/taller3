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
    //int alphaGrados = getVentanaApp().getSldrFalangeDigIzq().getValue();
    console.log("Grados: " + alphaGrados);
    //getBrazo().girarFalDidIzq(alphaGrados);
    this.getBrazoCompleto().dedoIzq.huesoUnido.girarHueso(alphaGrados);
    //
    // if(isEnviado){
    //     getVentanaApp().getSldrFalangeDigIzq().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarFalDigDer(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldFalangeDigDer().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoDer.huesoUnido.girarHueso(alphaGrados);
    //.getFalangeDigitalDerecha().girarHueso(alphaGrados);
    //girarFalDidDer(alphaGrados);
    //
    // if(isEnviado){
    //     getVentanaApp().getSldFalangeDigDer().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarFalProxIzq(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldFalangeProxIzq().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoIzq.girarHueso(alphaGrados);
    //girarFalProxIzq(alphaGrados);
    //
    // if(isEnviado){
    //     getVentanaApp().getSldFalangeProxIzq().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarFalProxDer(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldFalangeProxDer().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().dedoDer.girarHueso(alphaGrados);
    //girarFalProxDer(alphaGrados);
    //
    // if(isEnviado){
    //     getVentanaApp().getSldFalangeProxDer().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarMano(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldMano().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().mano.girarHueso(alphaGrados);
    //girarMano(alphaGrados);
    //
    // if (isEnviado) {
    //     getVentanaApp().getSldMano().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarAnteBrazo(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldAnteBrazo().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().anteBrazo.girarHueso(alphaGrados);
    //girarAnteBrazo(alphaGrados);
    //
    // if (isEnviado) {
    //     getVentanaApp().getSldAnteBrazo().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }

  girarBrazo(alphaGrados: number, isEnviado: boolean) {
    //int alphaGrados = getVentanaApp().getSldBrazo().getValue();
    console.log("Grados: " + alphaGrados);
    this.getBrazoCompleto().brazo.girarHueso(alphaGrados);
    //girarBrazo(alphaGrados);
    //
    // if (isEnviado) {
    //     getVentanaApp().getSldBrazo().setValue(alphaGrados);
    // }
    this.dibujar(this.ctx);
  }


  dibujar(lapiz: CanvasRenderingContext2D) {
    console.log("Diujando...");
    lapiz.clearRect(0, 0, this.constantes.ANCHO_MAXIMO_CANVAS, this.constantes.ALTO_MAXIMO_CANVAS);
    this.getBrazoCompleto().dibujarBrazo(lapiz);
  }

}
