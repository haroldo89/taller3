import { Hueso } from "./Hueso";
import { HuesoConHueso } from './HuesoConHueso';

export class Dedo extends HuesoConHueso {

    constructor(falangeDigital: Hueso, xFinal: number, yFinal: number, xInicial: number, yInicial: number, ancho: number) {
        super(falangeDigital, xFinal, yFinal, xInicial, yInicial, ancho);
    }

}
