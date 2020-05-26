import { Articulacion } from './Articulacion';
import { mathOperation } from 'src/utils/mathOperations';

export class Hueso {
  xFinalDinamica: number;
  yFinalDinamica: number;
  xInicial: number;
  yInicial: number;
  hipotenusa: number;
  ancho: number;
  articulacion: Articulacion
  mathOperation: mathOperation

  constructor(xFinal: number, yFinal: number, xInicial: number, yInicial: number, ancho: number) {
    this.xFinalDinamica = xFinal;
    this.yFinalDinamica = yFinal;
    this.xInicial = xInicial;
    this.yInicial = yInicial;
    this.ancho = ancho;

    this.articulacion = new Articulacion(xInicial, yInicial, ancho)

    let catetoOpuestoX: number = Math.abs(xInicial - xFinal)
    let catetoAdyacenteY: number = Math.abs(yInicial - yFinal);

    this.hipotenusa = Math.hypot(catetoOpuestoX, catetoAdyacenteY);
    this.mathOperation = new mathOperation();
  }

  /**
   * Metodo que permite girar n grados el extremo de la linea, respecto a una base fija
   * @param alphaGrados
   */
  girarHueso(alphaGrados: number) {

    //Se revisa si alphaGrados es negativo
    if (alphaGrados < 0) {
      alphaGrados = 360 - Math.abs(alphaGrados);
    }
    //Movimiento en radianes
    let alpha: number = this.mathOperation.degrees_to_radians(alphaGrados);

    //deltas de desplaxamiento de acuerdo al angulo
    let deltaX: number = this.hipotenusa * Math.sin(alpha);
    let deltaY: number = this.hipotenusa * Math.cos(alpha);


    //Se inicializan variables de corrimiento
    let xm: number = 0;
    let ym: number = 0;

    //Se revisa el valor de alpha y se asigna valor al corrimiento
    if ((alphaGrados >= 0 && alphaGrados <= 90)) {
      xm = this.xInicial + Math.abs(deltaX);
      ym = this.yInicial - Math.abs(deltaY);
    }
    else if ((alphaGrados > 90 && alphaGrados < 180)) {
      xm = this.xInicial + Math.abs(deltaX);
      ym = this.yInicial + Math.abs(deltaY);
    }
    else if ((alphaGrados >= 180 && alphaGrados <= 270)) {
      xm = this.xInicial - Math.abs(deltaX);
      ym = this.yInicial + Math.abs(deltaY);
    }
    else if ((alphaGrados > 270 && alphaGrados <= 360)) {
      xm = this.xInicial - Math.abs(deltaX);
      ym = this.yInicial - Math.abs(deltaY);
    }

    //Se pasan los valores a las variables de la clase
    this.xFinalDinamica = xm;
    this.yFinalDinamica = ym;
  }

  /**
   * Metodo que permite cambiar la base de el pseudo plano de la linea
   * (Corrimiento/traslación de toda la linea)
   * @param xTrasladada
   * @param yTrasladada
   */
  cambiarCoordenadasBase(xTrasladada: number, yTrasladada: number) {
    //Se calcula el corrimiento de coordenadas
    let deltaTrasladoX: number = this.xInicial - xTrasladada;
    let deltaTrasladoY: number = this.yInicial - yTrasladada;

    //Se asigna el nuevo valor a la base
    this.xInicial = xTrasladada;
    this.yInicial = yTrasladada;

    //Se calcula el nuevo valor de las coordenadas en el extremo
    this.xFinalDinamica = this.xFinalDinamica - deltaTrasladoX;
    this.yFinalDinamica = this.yFinalDinamica - deltaTrasladoY;

    //Se traslada la articulación
    this.articulacion.cambiarCoordenadasBase(xTrasladada, yTrasladada);
  }

  dibujar(lapiz: CanvasRenderingContext2D) {
    lapiz.beginPath();
    lapiz.fillStyle = 'green';
    lapiz.lineWidth = 10;
    lapiz.lineTo(this.xFinalDinamica, this.yFinalDinamica);
    lapiz.lineTo(this.xInicial, this.yInicial);
    lapiz.stroke();

  }
}
