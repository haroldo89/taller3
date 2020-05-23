package com.informatica.servidor.logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.Getter;


/**
 *
 * @author Vamaya
 */
public class Hueso {
    //VARIABLES
    @Getter private int xFinalDinamica, yFinalDinamica, xInicial, yInicial;//Coordenadas iniciales y finales
    @Getter private final int ancho;//ancho de la linea
    @Getter private final double hipotenusa;//longitud de la linea
    @Getter private final Articulacion articulacion;
    
    //CONSTRUCTOR
    public Hueso(int xFinal, int yFinal, int xInicial, int yInicial, int ancho) {
        this.xFinalDinamica = xFinal;
        this.yFinalDinamica = yFinal;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.ancho = ancho;
        
        //Se inicia la articulación base del hueso
        this.articulacion = new Articulacion(xInicial, yInicial, ancho);
        
        //Se calcula la altura o el radio del vector(Hipotenusa)
        double catetoOpuestoX = Math.abs(xInicial - xFinal);
        double catetoAdyacenteY = Math.abs(yInicial - yFinal);
        hipotenusa = Math.hypot(catetoOpuestoX, catetoAdyacenteY);
    }
    
    //METODOS
    
    /**
     * Metodo que permite girar n grados el extremo de la linea, respecto a una base fija
     * @param alphaGrados 
     */
    public void girarHueso(int alphaGrados){
        
        //Se revisa si alphaGrados es negativo
        if(alphaGrados<0){
            alphaGrados = 360 - Math.abs(alphaGrados);
        }
        //Movimiento en radianes
        double alpha = Math.toRadians(alphaGrados);
        
        //deltas de desplaxamiento de acuerdo al angulo
        double deltaX = hipotenusa*Math.sin(alpha);
        double deltaY = hipotenusa*Math.cos(alpha);
        
        
        //Se inicializan variables de corrimiento
        double xm = 0;
        double ym = 0;
        
        //Se revisa el valor de alpha y se asigna valor al corrimiento
        if( (alphaGrados>=0 && alphaGrados <=90)){
            xm = xInicial + Math.abs(deltaX);
            ym = yInicial - Math.abs(deltaY);
        }
        else if( (alphaGrados>90 && alphaGrados <180)){
            xm = xInicial + Math.abs(deltaX);
            ym = yInicial + Math.abs(deltaY);
        }
        else if( (alphaGrados>=180 && alphaGrados <=270) ){
            xm = xInicial - Math.abs(deltaX);
            ym = yInicial + Math.abs(deltaY);
        }
        else if( (alphaGrados>270 && alphaGrados <=360)){
            xm = xInicial - Math.abs(deltaX);
            ym = yInicial - Math.abs(deltaY);
        }
        
        //Se pasan los valores a las variables de la clase
        xFinalDinamica=(int) xm;
        yFinalDinamica=(int) ym;
    }
    
    /**
     * Metodo que permite cambiar la base de el pseudo plano de la linea
     * (Corrimiento/traslación de toda la linea)
     * @param xTrasladada
     * @param yTrasladada 
     */
    public void cambiarCoordenadasBase(int xTrasladada, int yTrasladada){
        //Se calcula el corrimiento de coordenadas
        int deltaTrasladoX = xInicial - xTrasladada;
        int deltaTrasladoY = yInicial - yTrasladada;
        
        //Se asigna el nuevo valor a la base
        xInicial = xTrasladada;
        yInicial = yTrasladada;
        
        //Se calcula el nuevo valor de las coordenadas en el extremo
        xFinalDinamica = xFinalDinamica - deltaTrasladoX;
        yFinalDinamica = yFinalDinamica - deltaTrasladoY;
        
        //Se traslada la articulación
        articulacion.cambiarCoordenadasBase(xTrasladada, yTrasladada);
    }
    
    /**
     * Función que permite graficar la linea
     * @param lapiz 
     */
    public void dibujar(Graphics lapiz){
        Graphics2D lapiz2D = (Graphics2D) lapiz;
        lapiz.setColor(new Color(102, 255, 255));
        lapiz2D.setStroke(new BasicStroke(ancho));//Se aumenta anco de la liena
        lapiz2D.drawLine(xFinalDinamica, yFinalDinamica, xInicial, yInicial);
    }
    
}
