package com.informatica.cliente.logica;

import java.awt.Graphics;
import lombok.Getter;

/**
 *
 * @author Vamaya
 */
@Getter
public class BrazoRobot {
    
    //VARIABLES    
    private final Dedo dedoIzq;
    private final Dedo dedoDer;
    private final Mano mano;    
    private final AnteBrazo anteBrazo;    
    private final Brazo brazo; 
    private final Base base;
    
    //CONSTRUCTOR
    public BrazoRobot() {
        
        dedoIzq = BrazoRobotFabrica.crearDedo();
        dedoDer = BrazoRobotFabrica.crearDedo();
        
        mano = BrazoRobotFabrica.crearMano(dedoIzq, dedoDer);
        
        anteBrazo = BrazoRobotFabrica.crearAntebrazo(mano);
        
        brazo = BrazoRobotFabrica.crearBrazo(anteBrazo);
                
        base= BrazoRobotFabrica.crerBase();
    }
   
    
    
    public void dibujarBrazo(Graphics lapiz){       
        
        dedoIzq.getHuesoUnido().dibujar(lapiz);
        dedoDer.getHuesoUnido().dibujar(lapiz);
        
        dedoIzq.dibujar(lapiz);
        dedoDer.dibujar(lapiz);
        
        mano.dibujar(lapiz);
        
        anteBrazo.dibujar(lapiz);
        
        brazo.dibujar(lapiz);
        
        base.dibujar(lapiz);
        
        //ARTICULACIONES
        brazo.getArticulacion().dibujar(lapiz);
        
        anteBrazo.getArticulacion().dibujar(lapiz);
        
        mano.getArticulacion().dibujar(lapiz);
        
        dedoIzq.getArticulacion().dibujar(lapiz);
        dedoDer.getArticulacion().dibujar(lapiz);
        
        dedoIzq.getHuesoUnido().getArticulacion().dibujar(lapiz);
        dedoDer.getHuesoUnido().getArticulacion().dibujar(lapiz);
        
    }   
    
}
