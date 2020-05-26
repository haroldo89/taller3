import { Dedo } from './Dedo';
import { HuesoConHueso } from './HuesoConHueso';

export class Mano extends HuesoConHueso {

    dedoDer: Dedo;

    constructor (dedoIzq: Dedo,  dedoDer: Dedo,  xFinal: number,  yFinal: number,  xInicial: number,  yInicial: number,  ancho: number) {
        super(dedoIzq, xFinal, yFinal, xInicial, yInicial, ancho);
        this.dedoDer = dedoDer;
    }

    //METODOS

    girarHueso(alphaGrados: number){
        super.girarHueso(alphaGrados);
        let  xn: number = this.xFinalDinamica;
        let  yn: number= this.yFinalDinamica;
        this.dedoDer.cambiarCoordenadasBase(xn, yn);
    }


    cambiarCoordenadasBase(xTrasladada: number, yTrasladada: number){
        super.cambiarCoordenadasBase(xTrasladada, yTrasladada);
        let  xnf: number = this.xFinalDinamica;
        let  ynf: number = this.yFinalDinamica;
        this.dedoDer.cambiarCoordenadasBase(xnf, ynf);
    }
}
