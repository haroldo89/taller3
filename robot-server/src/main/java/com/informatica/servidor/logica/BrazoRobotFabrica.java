package com.informatica.servidor.logica;

import com.informatica.servidor.utils.Constantes;

/**
 *
 * @author Vamaya
 */
public class BrazoRobotFabrica {

    //CONSTRUCTOR
    public BrazoRobotFabrica() {
    }
    
    //METODOS
    public static Dedo crearDedo(){
        //Se inicializan los valores de la falange digital
        int xiFalDig = Constantes.ANCHO_MAXIMO_CANVAS/2;
        int yiFalDig = 0;
        int wFalDig = 10;
        int hFalDig = Constantes.ALTO_DEDO/2;

        //Se inicializan los valores de la falange proximal
        int xiFalProx = xiFalDig;
        int yiFalProx = yiFalDig + hFalDig;        
        int wFalProx = 10;
        int hFalProx = Constantes.ALTO_DEDO/2;
        
        return new Dedo(new Hueso(xiFalDig, yiFalDig, xiFalDig, yiFalDig+hFalDig, wFalDig), xiFalProx, yiFalProx, xiFalProx, yiFalProx+hFalProx, wFalProx);
    }
    
    
    
    public static Mano crearMano(Dedo dedoIzq, Dedo dedoDer){
        int xiMano = Constantes.ANCHO_MAXIMO_CANVAS/2;
        int yiMano = Constantes.ALTO_DEDO;
        int wMano = 20;
        int hMano = Constantes.ALTO_MANO;
        
        return new Mano(dedoIzq, dedoDer, xiMano, yiMano, xiMano, yiMano + hMano, wMano);
    
    }
    
    public static AnteBrazo crearAntebrazo(Mano mano) {
        //Se inicializan los valores del antebrazo
        int xiAnteBrazo = Constantes.ANCHO_MAXIMO_CANVAS/2;
        int yiAnteBrazo = Constantes.ALTO_DEDO+Constantes.ALTO_MANO;       
        int wAnteBrazo = 20;
        int hAnteBrazo = Constantes.ALTO_ANTEBRAZO;
        
        return new AnteBrazo(mano, xiAnteBrazo, yiAnteBrazo, xiAnteBrazo, yiAnteBrazo + hAnteBrazo, wAnteBrazo);
        
    }
    
    
    public static Brazo crearBrazo(AnteBrazo anteBrazo) {
        //Se inicializan los valores del brazo
        int xiBrazo = Constantes.ANCHO_MAXIMO_CANVAS/2;
        int yiBrazo = Constantes.ALTO_DEDO+Constantes.ALTO_MANO+Constantes.ALTO_ANTEBRAZO;
        int wBrazo = 20;
        int hBrazo = Constantes.ALTO_BRAZO;
        
        return new Brazo(anteBrazo, xiBrazo, yiBrazo, xiBrazo, yiBrazo + hBrazo, wBrazo);
    }
    
    
    public static Base crerBase(){
        //Se inicializan los valores del brazo
        int xiBrazo = Constantes.ANCHO_MAXIMO_CANVAS/2;
        int yiBrazo = Constantes.ALTO_DEDO+Constantes.ALTO_MANO+Constantes.ALTO_ANTEBRAZO;
        int wBrazo = 20;
        int hBrazo = Constantes.ALTO_BRAZO;
        return new Base(xiBrazo, yiBrazo+hBrazo, 100, wBrazo/2);
    }
    
    
}
