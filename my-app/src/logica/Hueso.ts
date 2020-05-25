class Hueso {
    xFinalDinamica: number;
    yFinalDinamica: number;
    xInicial: number;
    yInicial: number;
    hipotenusa: number;
    ancho: number;
    articulacion: Articulacion 

    constructor(xFinal: number, yFinal: number,  xInicial:number, yInicial: number,  ancho: number) {
        this.xFinalDinamica = xFinal;
        this.yFinalDinamica = yFinal;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.ancho = ancho;

        this.articulacion = new Articulacion(xInicial, yInicial, ancho)

        let catetoOpuestoX: number  = Math.abs(xInicial - xFinal)
        let catetoAdyacenteY: number = Math.abs(yInicial - yFinal);

        this.hipotenusa = Math.hypot(catetoOpuestoX, catetoAdyacenteY);
    }
}