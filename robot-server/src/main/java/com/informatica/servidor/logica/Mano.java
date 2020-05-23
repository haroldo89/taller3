package com.informatica.servidor.logica;

import lombok.Getter;

/**
 *
 * @author Vamaya
 */
public class Mano extends HuesoConHueso{
    
    //VARIABLES    
    @Getter private final Dedo dedoDer;
    
    //CONSTRUCTOR
    public Mano(Dedo dedoIzq, Dedo dedoDer, int xFinal, int yFinal, int xInicial, int yInicial, int ancho) {
        super(dedoIzq, xFinal, yFinal, xInicial, yInicial, ancho);
        this.dedoDer = dedoDer;
    }
    
    //METODOS
    @Override
    public void girarHueso(int alphaGrados){
        super.girarHueso(alphaGrados);
        int xn = this.getXFinalDinamica();
        int yn= this.getYFinalDinamica();
        dedoDer.cambiarCoordenadasBase(xn, yn);
    }
    
    @Override
    public void cambiarCoordenadasBase(int xTrasladada, int yTrasladada){
        super.cambiarCoordenadasBase(xTrasladada, yTrasladada);
        int xnf = this.getXFinalDinamica();
        int ynf= this.getYFinalDinamica();
        dedoDer.cambiarCoordenadasBase(xnf, ynf);
    }

}
