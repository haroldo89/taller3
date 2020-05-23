package com.informatica.cliente.logica;

import lombok.Getter;

/**
 *
 * @author Vamaya
 */
public class HuesoConHueso  extends Hueso{
    //VARIABLES
    @Getter private final Hueso huesoUnido;
    
    //CONSTRUCTOR
    public HuesoConHueso(Hueso huesoUnido, int xFinal, int yFinal, int xInicial, int yInicial, int ancho) {
        super(xFinal, yFinal, xInicial, yInicial, ancho);
        this.huesoUnido = huesoUnido;
    }
    
    //METODOS SOBRE ESCRITOS
    @Override
    public void girarHueso(int alphaGrados){
        super.girarHueso(alphaGrados);
        int xn = this.getXFinalDinamica();
        int yn= this.getYFinalDinamica();
        huesoUnido.cambiarCoordenadasBase(xn, yn);
        
    }
    
    @Override
    public void cambiarCoordenadasBase(int xTrasladada, int yTrasladada){
        super.cambiarCoordenadasBase(xTrasladada, yTrasladada);
        int xnf = this.getXFinalDinamica();
        int ynf= this.getYFinalDinamica();
        huesoUnido.cambiarCoordenadasBase(xnf, ynf);
    }
    
    
    
}
