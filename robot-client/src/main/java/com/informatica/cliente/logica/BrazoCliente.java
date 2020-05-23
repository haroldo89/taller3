package com.informatica.cliente.logica;

import com.informatica.cliente.presentacion.Modelo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import lombok.Setter;

/**
 * Clase que permite generar un cliente y conectarse a un servidor existente
 * @author Vamaya
 */
public class BrazoCliente{
    
    //VARIABLES
    private final Modelo modelo; //Se crea una unión con el modelo para poder modificar el dibujo del brazo
    private final String serverHost = "localhost"; //Dirección del servidor 
    private final int serverPort = 5000; //Puerto de conexión con el servidor
    private Socket serverSocket; //Socket del servidor
    private DataOutputStream datosSalida; //Variable para el envío de datos al servidor
    @Setter
    private String nombreSlider;//Alamacenar en la clase el nombre del Slider moificado
    @Setter
    private Integer valorSlider;//Alamacenar en la clase el valor del Slider modificado

    //CONSTRUCTOR
    public BrazoCliente(Modelo modelo) {
        this.modelo = modelo;
    }

    //MÉTODOS
    
    /**
     * Método para conectarse con el servidor e iniciar hilo para recbir datos
     *
     * @throws java.io.IOException
     */
    public void conectar() throws IOException {
        //Establecer conexion
        System.out.println("Estableciendo conexion con el servidor...");
        serverSocket = new Socket(serverHost, serverPort);
        System.out.println("Abriendo hilo para recibir de datos del servidor...");
        HiloEscuchaBrazoServidor hiloServidor = new HiloEscuchaBrazoServidor(serverSocket, modelo);
        hiloServidor.start();
    }
    
    /**
     * Método para enviar mensajes al servidor
     * @throws IOException 
     */
    public synchronized void enviarMensaje() throws IOException{
        //Capturo el flujo de salida y lo asocio al dato de salida
        datosSalida = new DataOutputStream(serverSocket.getOutputStream());
        //Establecer el protocolo de mensaje a enviar
        String mensaje = nombreSlider + "," + valorSlider+ ",";
        //Opero con los mensajes
        System.out.println("Enviando el mensaje: [" + mensaje + "]");
        datosSalida.write(mensaje.getBytes()); // ESTE ES EL PROTOCOLO
    }

}
