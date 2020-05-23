/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatica.cliente.logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.Getter;

/**
 *
 * @author DELL
 */
public class Articulacion {
    
     //VARIABLES
    @Getter private int xInicial, yInicial;//Coordenadas iniciales y finales
    @Getter private final int radio;//ancho de la linea
    
    //CONSTRUCTOR
    public Articulacion(int xInicial, int yInicial, int radio) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.radio = radio;
    }
    
    //METODOS
    
    /**
     * Metodo que permite cambiar la base de el pseudo plano de la articulación
     * (Corrimiento/traslación de toda la linea)
     * @param xTrasladada
     * @param yTrasladada 
     */
    public void cambiarCoordenadasBase(int xTrasladada, int yTrasladada){        
        //Se asigna el nuevo valor a la base
        xInicial = xTrasladada;
        yInicial = yTrasladada;
    }
    
    /**
     * Función que permite graficar la linea
     * @param lapiz 
     */
    public void dibujar(Graphics lapiz){
        Graphics2D lapiz2D = (Graphics2D) lapiz;
        lapiz2D.setColor(Color.BLACK);
        lapiz2D.fillOval(xInicial-radio/2, yInicial-radio/2, radio, radio);
    }
}
