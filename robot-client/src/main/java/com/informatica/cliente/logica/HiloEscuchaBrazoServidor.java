package com.informatica.cliente.logica;

import com.informatica.cliente.presentacion.Modelo;
import com.informatica.cliente.utils.NombreSlider;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase de tipo "Hilo" que permite recibir los mensajes que envia el servidorlos clientes
 * @author Vamaya
 */
public class HiloEscuchaBrazoServidor extends Thread {

    //VARIABLES
    private final String raizLog;//String para guardar el nombre del hilo e identificar por consola quien está atentiendo la solicitud
    private final Modelo modelo;//Se crea una unión con el modelo para poder modificar el dibujo del brazo
    private final Socket servidor;//Socket del SERVIDOR, enviado por el CLIENTE
    private DataInputStream datosEntrada;//Variable para obtener datos del servidor
    private String nombreSlider;//Alamacenar en la clase el nombre del Slider moificado
    private Integer valorSlider;//Alamacenar en la clase el valor del Slider modificado
    public static final int MAX_BYTES = 200;//Tamaño máximo (en bytes) del mensaje que envía el cliente
    private final boolean lecturaActiva;//Variable booleana para mantener al servidor escuchando al cliente

    //CONSTRUCTOR
    public HiloEscuchaBrazoServidor(Socket servidor, Modelo modelo) {
        this.servidor = servidor;
        this.modelo = modelo;
        this.lecturaActiva = true;
        this.raizLog = "[" + this.getName() + "] - ";
    }

    //MÉTODOS
    
    /**
     * Metodo que permite retornar el valor de la varible booleana
     * "lecturaActiva"
     *
     * @return
     */
    public boolean isLecturaActiva() {
        return lecturaActiva;
    }

    
    
    /**
     * Metodo run del hilo, se ejecuta al iniciar el hilo Recibe los datos
     * enviados por el servidor,
     */
    @Override
    public void run() {
        try {
            //Ciclo de lectura de datos enviados por el cliente
            while (isLecturaActiva()) {
                datosEntrada = new DataInputStream(servidor.getInputStream());//Se crea conexión de lectura de datos con el SERVIDOR
                System.out.println(raizLog + "Conexión de lectura de datos iniciada con el SERVIDOR .....");
                //Se leen los datos de entrada del cleinte
                String mensajeServidor = "";
                mensajeServidor = leerDataServidor();
                //Se maneja el mensaje y se actualizan los valores
                String[] vectorMensaje = mensajeServidor.split(","); //Se convierte en un vector segun las comas encontradas en el mensaje
                nombreSlider = vectorMensaje[0];
                valorSlider = Integer.parseInt(vectorMensaje[1]);
                //Se mueve el brazo con los valores recibidos
                moverBrazo(nombreSlider, valorSlider);
            }

        } catch (IOException e) {
            System.out.println(raizLog + "IO Error/ Server " + this.getName() + " terminated abruptly");
        } catch (NullPointerException e) {
            System.out.println(raizLog + "Server " + this.getName() + " Closed");
        } catch (Exception e) {
            System.out.println(raizLog + "Algun otro error");
        } finally {
            try {
                System.out.println(raizLog + "Cerrando conexión .....");
                if (datosEntrada != null) {
                    datosEntrada.close();
                    System.out.println(raizLog + "Socket Input Stream Closed");
                }

                if (servidor != null) {
                    servidor.close();
                    System.out.println(raizLog + "Socket Closed");
                }

            } catch (IOException e) {
                System.out.println(raizLog + "Socket Close Error");
            }
        }//end finally
    }

    
    
    /**
     * Método que permite leer los datos que envía el servidor
     *
     * @return
     * @throws Exception
     */
    public String leerDataServidor() throws Exception {
        byte[] buffer = new byte[MAX_BYTES];//Buffer para almacenar lo datos enviado por el cliente
        System.out.println(raizLog + "Esperando mensaje del servidor .....");
        datosEntrada.read(buffer);//Se queda acá, hasta que el cliente envíe algo
        System.out.println(raizLog + "El SERVIDOR envió un mensaje, capturando mensaje .....");
        String mensaje = new String(buffer).trim();//Variable para almacenar el mensaje enviado por el cliente
        System.out.println(raizLog + "El mensaje enviado por el servidor fue : [" + mensaje + "]");
        return mensaje; //e retorna el menaje recibido
    }
    
        
    
    /**
     * Método para mover el brazo del servidor, de acuerdo con el mensaje
     * recibido por alguno de los clientes
     *
     * @param nombreSlider
     * @param valorSlider
     */
    public void moverBrazo(String nombreSlider, Integer valorSlider) {
        System.out.println(raizLog + "Se va a cambiar la posición del brao del servidor .....");
        if (nombreSlider.equals(NombreSlider.FALANGE_DIG_IZQ.toString())) {
            System.out.println(raizLog + "Falange dig izq");
            //Mover la falange dig izq
            modelo.girarFalDigIzq(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_DIG_DER.toString())) {
            System.out.println(raizLog + "Falange dig der");
            //Mover la falange dig der
            modelo.girarFalDigDer(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_PROX_IZQ.toString())) {
            System.out.println(raizLog + "Falange prox izq");
            //Mover la falange prox izq
            modelo.girarFalProxIzq(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_PROX_DER.toString())) {
            System.out.println(raizLog + "Falange prox der");
            //Mover la falange prox der
            modelo.girarFalProxDer(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.MANO.toString())) {
            System.out.println(raizLog + "Mano");
            //Mover la mano
            modelo.girarMano(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.ANTEBRAZO.toString())) {
            System.out.println(raizLog + "Antebrazo");
            //Mover el antebrazo
            modelo.girarAnteBrazo(valorSlider, true);

        } else if (nombreSlider.equals(NombreSlider.BRAZO.toString())) {
            System.out.println(raizLog + "Brazo");
            //Mover el antebrazo
            modelo.girarBrazo(valorSlider, true);

        }

    }
    
   

    
    
    
    
    
}
