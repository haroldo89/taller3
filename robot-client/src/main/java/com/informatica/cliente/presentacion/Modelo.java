package com.informatica.cliente.presentacion;

// import logica;
import com.informatica.cliente.logica.BrazoCliente;
import com.informatica.cliente.logica.BrazoRobot;
import com.informatica.cliente.utils.Constantes;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;

/**
 *
 * @author harol
 */
//public class Modelo implements Runnable {
public class Modelo implements Runnable {

    //VARIABLES
    private Vista ventanaApp;
    private BrazoCliente brazoCliente;
    private BrazoRobot brazoCompleto;
    Boolean inicio = true;

    //CONSTRUCTOR
    public Modelo() {
        ventanaApp = new Vista(this);
        brazoCliente = new BrazoCliente(this);
        brazoCompleto = new BrazoRobot();
    }

    //GETTERS
    public Vista getVentanaApp() {
        if (ventanaApp == null) {
            ventanaApp = new Vista(this);
        }
        return ventanaApp;
    }

    public BrazoRobot getBrazoCompleto() {
        if (brazoCompleto == null) {
            brazoCompleto = new BrazoRobot();
        }
        return brazoCompleto;
    }

    public BrazoCliente getBrazoCliente() {
        if (brazoCliente == null) {
            brazoCliente = new BrazoCliente(this);
        }
        return brazoCliente;
    }

    //METODOS
    /**
     * Metodo para iniciar la ventana principal de la aplicación
     */
    public void iniciar() {
        getVentanaApp().setSize(Constantes.ANCHO_MAXIMO_FRAME, Constantes.ALTO_MAXIMO_FRAME);
        getVentanaApp().setVisible(true);
        conectar();
        run();

    }

    public void conectar() {
        try {            
            getBrazoCliente().conectar();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void enviar(String nombreSilder, int valorSlider) {
        getBrazoCliente().setNombreSlider(nombreSilder);
        getBrazoCliente().setValorSlider(valorSlider);
        System.out.println("Se va a enviar: Nombre [" + nombreSilder + "] valor [" + valorSlider + "]");

        try {            
            getBrazoCliente().enviarMensaje();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }

    //METODOS
    public void girarFalDigIzq(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldrFalangeDigIzq().getValue();
        System.out.println("Grados: " + alphaGrados);
        //getBrazo().girarFalDidIzq(alphaGrados);
        getBrazoCompleto().getDedoIzq().getHuesoUnido().girarHueso(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldrFalangeDigIzq().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarFalDigDer(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldFalangeDigDer().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoDer().getHuesoUnido().girarHueso(alphaGrados);
        //.getFalangeDigitalDerecha().girarHueso(alphaGrados);
        //girarFalDidDer(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldFalangeDigDer().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarFalProxIzq(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldFalangeProxIzq().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoIzq().girarHueso(alphaGrados);
        //girarFalProxIzq(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldFalangeProxIzq().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarFalProxDer(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldFalangeProxDer().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoDer().girarHueso(alphaGrados);
        //girarFalProxDer(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldFalangeProxDer().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarMano(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldMano().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getMano().girarHueso(alphaGrados);
        //girarMano(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldMano().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarAnteBrazo(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldAnteBrazo().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getAnteBrazo().girarHueso(alphaGrados);
        //girarAnteBrazo(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldAnteBrazo().setValue(alphaGrados);
        }
        dibujar();
    }

    public void girarBrazo(int alphaGrados, Boolean isEnviado) {
        //int alphaGrados = getVentanaApp().getSldBrazo().getValue();
        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getBrazo().girarHueso(alphaGrados);
        //girarBrazo(alphaGrados);
        
        //
        if(isEnviado){
            getVentanaApp().getSldBrazo().setValue(alphaGrados);
        }
        dibujar();        
    }
    

    private void dibujar() {
        System.out.println("Diujando...");
        Canvas lienzo = getVentanaApp().getLienzo();
        BufferedImage dobleBuffer = new BufferedImage(lienzo.getWidth(), lienzo.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics lapiz = dobleBuffer.getGraphics();
        lapiz.clearRect(0, 0, Constantes.ANCHO_MAXIMO_CANVAS, Constantes.ALTO_MAXIMO_CANVAS);
        getBrazoCompleto().dibujarBrazo(lapiz);
        Graphics pincel = lienzo.getGraphics();
        pincel.drawImage(dobleBuffer, 0, 0, lienzo);
    }

    @Override
    public void run() {
        if (inicio) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
            inicio = false;
        }
        dibujar();        
        //getBrazoCliente().leerDataServer();
    }
}
