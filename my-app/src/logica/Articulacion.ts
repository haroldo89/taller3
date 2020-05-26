export class Articulacion {
    xInicial: number;
    yInicial: number;
    radio: number;

    constructor(xInicial: number, yInicial: number, radio: number) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.radio = radio;
    }

    cambiarCoordenadasBase(xTrasladada: number, yTrasladada: number) {
        //Se asigna el nuevo valor a la base
        this.xInicial = xTrasladada;
        this.yInicial = yTrasladada;
    }

    /**
     * Función que permite graficar la linea
     * @param lapiz
     */
    dibujar(lapiz: CanvasRenderingContext2D) {
        lapiz.fillStyle = 'blue';
        lapiz.fillRect(this.xInicial - this.radio / 2, this.yInicial - this.radio / 2, this.radio, this.radio);
    }

}
