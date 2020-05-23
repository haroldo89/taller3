package com.informatica.servidor.presentacion;

// import logica;
import com.informatica.servidor.logica.BrazoRobot;
import com.informatica.servidor.logica.BrazoServidor;
import com.informatica.servidor.utils.Constantes;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 *
 * @author harol
 */
public class Modelo implements Runnable {
    
    //VARIABLES
    private Vista ventanaApp;
    private BrazoRobot brazoCompleto;
    private BrazoServidor brazoServidor;
    Boolean inicio = true;
    
    //CONSTRUCTOR
    public Modelo() {
        ventanaApp = new Vista(this);
        brazoCompleto = new BrazoRobot();
        brazoServidor = new BrazoServidor(this);
    }

    
    //GETTERS
    public Vista getVentanaApp() {
        if(ventanaApp == null){
            ventanaApp = new Vista(this);
        }
        return ventanaApp;
    }

    public BrazoRobot getBrazoCompleto() {
        if(brazoCompleto == null){
            brazoCompleto = new BrazoRobot();
        }
        return brazoCompleto;
    }

    public BrazoServidor getBrazoServidor() {
        if(brazoServidor == null){
            brazoServidor = new BrazoServidor(this);
        }
        return brazoServidor;
    }
    
    
    
    
    
    
    /**
     * Metodo para iniciar la ventana principal de la aplicación
     */
    public void iniciar() {
        getVentanaApp().setSize(Constantes.ANCHO_MAXIMO_FRAME, Constantes.ALTO_MAXIMO_FRAME);
        getVentanaApp().setVisible(true);        
                
        run();
        
        System.out.println("Estableciendo conexión...");
            getBrazoServidor().conectar();
            System.out.println("Finalizando!");
            
    }

    
    //METODOS
    public void girarFalDigIzq(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldrFalangeDigIzq().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoIzq().getHuesoUnido().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    public void girarFalDigDer(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldFalangeDigDer().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoDer().getHuesoUnido().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    
    public void girarFalProxIzq(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldFalangeProxIzq().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoIzq().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    public void girarFalProxDer(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldFalangeProxDer().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getDedoDer().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    public void girarMano(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldMano().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getMano().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    public void girarAnteBrazo(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldAnteBrazo().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getAnteBrazo().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    public void girarBrazo(int alphaGrados){
//        int alphaGrados = getVentanaApp().getSldBrazo().getValue();
//        System.out.println("Grados: " + alphaGrados);
        getBrazoCompleto().getBrazo().girarHueso(alphaGrados);

        dibujar();
    }
    
    
    
    private void dibujar(){
        
        
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
        if(inicio){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Error");
            }
            inicio = false;
        }
        dibujar();
    }
    
}
