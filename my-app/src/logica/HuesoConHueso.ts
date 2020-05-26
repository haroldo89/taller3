import { Hueso } from './Hueso';

export class HuesoConHueso extends Hueso {
    huesoUnido: Hueso;

    constructor(huesoUnido: Hueso, xFinal: number, yFinal: number, xInicial: number, yInicial: number, ancho: number) {
        super(xFinal, yFinal, xInicial, yInicial, ancho);
        this.huesoUnido = huesoUnido;
    }

    //METODOS SOBRE ESCRITOS
    girarHueso(alphaGrados: number){
        super.girarHueso(alphaGrados);
        let xn: number = this.xFinalDinamica;
        let yn: number = this.yFinalDinamica;
        this.huesoUnido.cambiarCoordenadasBase(xn, yn);

    }

    cambiarCoordenadasBase(xTrasladada: number, yTrasladada: number){
        super.cambiarCoordenadasBase(xTrasladada, yTrasladada);
        let xnf: number = this.xFinalDinamica;
        let ynf: number = this.yFinalDinamica;
        this.huesoUnido.cambiarCoordenadasBase(xnf, ynf);
    }

}
