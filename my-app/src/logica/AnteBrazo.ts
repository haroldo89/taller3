import { Mano } from './Mano';
import { HuesoConHueso } from './HuesoConHueso';

export class AnteBrazo extends HuesoConHueso {

  constructor(mano: Mano, xFinal: number, yFinal: number, xInicial: number, yInicial: number, ancho: number) {
    super(mano, xFinal, yFinal, xInicial, yInicial, ancho);
  }
}
