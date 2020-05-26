export class Base {
    xInicial: number;
    yInicial: number;
    ancho: number;;//ancho de la linea
    alto: number;;//longitud de la linea

    constructor(xInicial: number, yInicial: number, ancho: number, alto: number) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Función que permite graficar la base
     * @param lapiz
     */
    dibujar(lapiz: CanvasRenderingContext2D) {
        lapiz.fillStyle = 'orange';
        lapiz.fillRect(this.xInicial - this.ancho / 2, this.yInicial - this.alto / 2, this.ancho, this.alto);
    }
}
