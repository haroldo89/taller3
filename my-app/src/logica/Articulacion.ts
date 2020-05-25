class Articulacion {
    xInicial: number;
    yInicial: number;
    radio: number;

    constructor(xInicial:number, yInicial:number, radio:number){
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.radio = radio;
    }

    cambiarCoordenadasBase(xTrasladada:number, yTrasladada:number){        
        //Se asigna el nuevo valor a la base
        this.xInicial = xTrasladada;
        this.yInicial = yTrasladada;
    }

    

}