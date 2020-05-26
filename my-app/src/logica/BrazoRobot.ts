import { AnteBrazo } from './AnteBrazo';
import { Brazo } from './Brazo';
import { Base } from './Base';
import { Dedo } from './Dedo';
import { Mano } from './Mano';
import { BrazoRobotFabrica } from './BrazoRobotFabrica';

export class BrazoRobot {
    dedoIzq: Dedo;
    dedoDer: Dedo;
    mano: Mano;
    anteBrazo: AnteBrazo;
    brazo: Brazo;
    base: Base;

    BrazoRobotFabrica: BrazoRobotFabrica;

    constructor() {
        this.BrazoRobotFabrica = new BrazoRobotFabrica();

        this.dedoIzq = this.BrazoRobotFabrica.crearDedo();
        this.dedoDer = this.BrazoRobotFabrica.crearDedo();
        this.mano = this.BrazoRobotFabrica.crearMano(this.dedoIzq, this.dedoDer);
        this.anteBrazo = this.BrazoRobotFabrica.crearAntebrazo(this.mano);
        this.brazo = this.BrazoRobotFabrica.crearBrazo(this.anteBrazo);
        this.base = this.BrazoRobotFabrica.crerBase();
    }

    dibujarBrazo(lapiz: any){
        this.dedoIzq.huesoUnido.dibujar(lapiz);
        this.dedoDer.huesoUnido.dibujar(lapiz);
        this.dedoIzq.dibujar(lapiz);
        this.dedoDer.dibujar(lapiz);
        this.mano.dibujar(lapiz);
        this.anteBrazo.dibujar(lapiz);
        this.brazo.dibujar(lapiz);
        this.base.dibujar(lapiz);
        // Articulaciones.
        this.brazo.articulacion.dibujar(lapiz);
        this.anteBrazo.articulacion.dibujar(lapiz);
        this.mano.articulacion.dibujar(lapiz);
        this.dedoIzq.articulacion.dibujar(lapiz);
        this.dedoDer.articulacion.dibujar(lapiz);
        this.dedoIzq.huesoUnido.articulacion.dibujar(lapiz);
        this.dedoDer.huesoUnido.articulacion.dibujar(lapiz);
    }

}
