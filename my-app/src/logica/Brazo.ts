import { AnteBrazo } from './AnteBrazo';
import { HuesoConHueso } from './HuesoConHueso';

export class Brazo extends HuesoConHueso {
    constructor(anteBrazo: AnteBrazo, xFinal: number, yFinal: number, xInicial: number, yInicial: number, ancho: number) {
        super(anteBrazo, xFinal, yFinal, xInicial, yInicial, ancho);
    }
}
