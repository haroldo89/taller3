/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatica.cliente.presentacion;

import com.informatica.cliente.utils.NombreSlider;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author harol otro comentario
 */
//public class Controlador implements ChangeListener {
public class Controlador implements MouseListener {

    //VARIABLES
    private Vista ventana;
    private Modelo modelo;

    //CONSTRUCTOR
    public Controlador(Vista v) {
        this.ventana = v;
        modelo = ventana.getModelo();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Entro a click del mouse...");        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Entro a presionado del mouse...");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Entro a cambio del mouse...");
        if (e.getSource().equals(ventana.getSldrFalangeDigIzq())) {
            System.out.println("Falange dig izq");
            //Mover la falange dig izq
            modelo.enviar(NombreSlider.FALANGE_DIG_IZQ.toString(), ventana.getSldrFalangeDigIzq().getValue());
            modelo.girarFalDigIzq(ventana.getSldrFalangeDigIzq().getValue(), false);

        } else if (e.getSource().equals(ventana.getSldFalangeDigDer())) {
            System.out.println("Falange dig der");
            //Mover la falange dig der
            modelo.enviar(NombreSlider.FALANGE_DIG_DER.toString(), ventana.getSldFalangeDigDer().getValue());
            modelo.girarFalDigDer(ventana.getSldFalangeDigDer().getValue(), false);

        } else if (e.getSource().equals(ventana.getSldFalangeProxIzq())) {
            System.out.println("Falange prox izq");
            //Mover la falange prox izq
            modelo.enviar(NombreSlider.FALANGE_PROX_IZQ.toString(), ventana.getSldFalangeProxIzq().getValue());
            modelo.girarFalProxIzq(ventana.getSldFalangeProxIzq().getValue(), false);

        } else if (e.getSource().equals(ventana.getSldFalangeProxDer())) {
            System.out.println("Falange prox der");
            //Mover la falange prox der
            modelo.enviar(NombreSlider.FALANGE_PROX_DER.toString(), ventana.getSldFalangeProxDer().getValue());
            modelo.girarFalProxDer(ventana.getSldFalangeProxDer().getValue(), false);

        } else if (e.getSource().equals(ventana.getSldMano())) {
            System.out.println("Mano");
            //Mover la mano
            modelo.enviar(NombreSlider.MANO.toString(), ventana.getSldMano().getValue());
            modelo.girarMano(ventana.getSldMano().getValue(), false);

        } else if (e.getSource().equals(ventana.getSldAnteBrazo())) {
            System.out.println("Antebrazo");
            //Mover el antebrazo
            modelo.enviar(NombreSlider.ANTEBRAZO.toString(), ventana.getSldAnteBrazo().getValue());
            modelo.girarAnteBrazo(ventana.getSldAnteBrazo().getValue(), false);
            
        } else if (e.getSource().equals(ventana.getSldBrazo())) {
            System.out.println("Brazo");
            //Mover el antebrazo
            modelo.enviar(NombreSlider.BRAZO.toString(), ventana.getSldBrazo().getValue());
            modelo.girarBrazo(ventana.getSldBrazo().getValue(), false);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Entro a entered del mouse...");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Entro a exited del mouse...");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
