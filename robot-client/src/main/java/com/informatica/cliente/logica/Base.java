package com.informatica.cliente.logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.Getter;

/**
 *
 * @author Vamaya
 */
public class Base {
    //VARIABLES
    @Getter private int xInicial, yInicial;//Coordenadas iniciales y finales
    @Getter private final int ancho;//ancho de la linea
    @Getter private final int alto;//longitud de la linea
    
    //CONSTRUCTOR
    public Base(int xInicial, int yInicial, int ancho, int alto) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    //METODOS
    
    /**
     * Función que permite graficar la base
     * @param lapiz 
     */
    public void dibujar(Graphics lapiz){
        Graphics2D lapiz2D = (Graphics2D) lapiz;
        lapiz.setColor(new Color(102, 255, 255));
        //lapiz2D.setStroke(new BasicStroke(ancho));//Se aumenta anco de la liena
        //lapiz2D.drawLine(xFinalDinamica, yFinalDinamica, xInicial, yInicial);
        lapiz2D.drawRect(xInicial-ancho/2, yInicial-alto/2, ancho, alto);
    }
}
