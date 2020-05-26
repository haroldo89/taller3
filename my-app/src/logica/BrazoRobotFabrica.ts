import { Dedo } from './Dedo';
import { Hueso } from './Hueso';
import { Mano } from './Mano';
import { AnteBrazo } from './AnteBrazo';
import { Brazo } from './Brazo';
import { Base } from './Base';
import { Constantes } from 'src/utils/Constantes';

export class BrazoRobotFabrica {

    constantes: Constantes;

    constructor() {
        this.constantes = new Constantes();
    }

    //METODOS
    crearDedo(): Dedo {
        //Se inicializan los valores de la falange digital
        let xiFalDig: number = this.constantes.ANCHO_MAXIMO_CANVAS / 2;
        let yiFalDig: number = 0;
        let wFalDig: number = 10;
        let hFalDig: number = this.constantes.ALTO_DEDO / 2;

        //Se inicializan los valores de la falange proximal
        let xiFalProx: number = xiFalDig;
        let yiFalProx: number = yiFalDig + hFalDig;
        let wFalProx: number = 10;
        let hFalProx: number = this.constantes.ALTO_DEDO / 2;

        return new Dedo(new Hueso(xiFalDig, yiFalDig, xiFalDig, yiFalDig + hFalDig, wFalDig),
            xiFalProx, yiFalProx, xiFalProx, yiFalProx + hFalProx, wFalProx);
    }

    crearMano(dedoIzq: Dedo, dedoDer: Dedo): Mano {
        let xiMano: number = this.constantes.ANCHO_MAXIMO_CANVAS / 2;
        let yiMano: number = this.constantes.ALTO_DEDO;
        let wMano: number = 20;
        let hMano: number = this.constantes.ALTO_MANO;

        return new Mano(dedoIzq, dedoDer, xiMano, yiMano, xiMano, yiMano + hMano, wMano);
    }

    crearAntebrazo(mano: Mano): AnteBrazo {
        //Se inicializan los valores del antebrazo
        let xiAnteBrazo: number = this.constantes.ANCHO_MAXIMO_CANVAS / 2;
        let yiAnteBrazo: number = this.constantes.ALTO_DEDO + this.constantes.ALTO_MANO;
        let wAnteBrazo: number = 20;
        let hAnteBrazo: number = this.constantes.ALTO_ANTEBRAZO;

        return new AnteBrazo(mano, xiAnteBrazo, yiAnteBrazo, xiAnteBrazo, yiAnteBrazo + hAnteBrazo, wAnteBrazo);
    }

    crearBrazo(anteBrazo: AnteBrazo): Brazo {
        //Se inicializan los valores del brazo
        let xiBrazo: number = this.constantes.ANCHO_MAXIMO_CANVAS / 2;
        let yiBrazo: number = this.constantes.ALTO_DEDO + this.constantes.ALTO_MANO + this.constantes.ALTO_ANTEBRAZO;
        let wBrazo: number = 20;
        let hBrazo: number = this.constantes.ALTO_BRAZO;

        return new Brazo(anteBrazo, xiBrazo, yiBrazo, xiBrazo, yiBrazo + hBrazo, wBrazo);
    }

    crerBase(): Base {
        //Se inicializan los valores del brazo
        let xiBrazo: number = this.constantes.ANCHO_MAXIMO_CANVAS / 2;
        let yiBrazo: number = this.constantes.ALTO_DEDO + this.constantes.ALTO_MANO + this.constantes.ALTO_ANTEBRAZO;
        let wBrazo: number = 20;
        let hBrazo: number = this.constantes.ALTO_BRAZO;
        return new Base(xiBrazo, yiBrazo + hBrazo, 100, wBrazo / 2);
    }
}
