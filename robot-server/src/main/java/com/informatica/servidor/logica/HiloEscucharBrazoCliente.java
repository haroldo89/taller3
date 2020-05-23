package com.informatica.servidor.logica;

import com.informatica.servidor.presentacion.Modelo;
import com.informatica.servidor.utils.NombreSlider;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Clase de tipo "Hilo" que permite recibir los mensajes que envian los clientes
 *
 * @author Vamaya
 */
public class HiloEscucharBrazoCliente extends Thread {

    //VARIABLES
    private final String raizLog;//String para guardar el nombre del hilo e identificar por consola quien está atentiendo la solicitud
    private final Modelo modelo;//Se crea una unión con el modelo para poder modificar el dibujo del brazo
    private final Socket cliente;//Socket del cliente, enviado por el servidor
    private final BrazoServidor servidor;//Clase donde se generó la conexión del servidor
    private DataOutputStream datosSalida;//Variable para el envío de datos al cliente
    private DataInputStream datosEntrada;//Variable para obtener datos del cliente
    private String nombreSlider;//Alamacenar en la clase el nombre del Slider moificado
    private Integer valorSlider;//Alamacenar en la clase el valor del Slider modificado
    public static final int MAX_BYTES = 200;//Tamaño máximo (en bytes) del mensaje que envía el cliente
    private final boolean lecturaActiva;//Variable booleana para mantener al servidor escuchando al cliente

    //CONSTRUCTOR
    public HiloEscucharBrazoCliente(Socket cliente, Modelo modelo, BrazoServidor servidor) {
        this.cliente = cliente;
        this.modelo = modelo;
        this.servidor = servidor;
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
     * enviados por el cliente,
     */
    @Override
    public void run() {
        try {
            //Ciclo de lectura de datos enviados por el cliente
            while (isLecturaActiva()) {
                datosEntrada = new DataInputStream(cliente.getInputStream());//Se crea conexión de lectura de datos con el cliente
                System.out.println(raizLog + "Conexión de lectura de datos iniciada con el cliente .....");
                //Se leen los datos de entrada del cleinte
                String mensajeCliente = "";
                mensajeCliente = leerDataCliente();
                //Se maneja el mensaje y se actualizan los valores
                String[] vectorMensaje = mensajeCliente.split(","); //Se convierte en un vector segun las comas encontradas en el mensaje
                nombreSlider = vectorMensaje[0];
                valorSlider = Integer.parseInt(vectorMensaje[1]);
                //Se mueve el brazo con los valores recibidos
                moverBrazo(nombreSlider, valorSlider);
                //Se envía el datos a los demás clientes conectados
                actualizarMensajeClientes();
            }

        } catch (IOException e) {
            System.out.println(raizLog + "IO Error/ Client " + this.getName() + " terminated abruptly");
        } catch (NullPointerException e) {
            System.out.println(raizLog + "Client " + this.getName() + " Closed");
        } catch (Exception e) {
            System.out.println(raizLog + "Algun otro error");
        } finally {
            try {
                System.out.println(raizLog + "Cerrando conexión .....");
                if (datosEntrada != null) {
                    datosEntrada.close();
                    System.out.println(raizLog + "Socket Input Stream Closed");
                }

                if (cliente != null) {
                    cliente.close();
                    System.out.println(raizLog + "Socket Closed");
                }

            } catch (IOException e) {
                System.out.println(raizLog + "Socket Close Error");
            }
        }//end finally
    }

    /**
     * Método que permite leer los datos que envía el cliente
     *
     * @return
     * @throws Exception
     */
    public String leerDataCliente() throws Exception {
        byte[] buffer = new byte[MAX_BYTES];//Buffer para almacenar lo datos enviado por el cliente
        System.out.println(raizLog + "Esperando mensaje del cliente .....");
        datosEntrada.read(buffer);//Se queda acá, hasta que el cliente envíe algo
        System.out.println(raizLog + "El cliente envió un mensaje, capturando mensaje .....");
        String mensaje = new String(buffer).trim();//Variable para almacenar el mensaje enviado por el cliente
        System.out.println(raizLog + "El mensaje enviado por el cliente fue : [" + mensaje + "]");
        return mensaje; //e retorna el menaje recibido
    }

    /**
     * Metodo que permite actualizar el valor de los demás clientes
     *
     * @throws IOException
     */
    public void actualizarMensajeClientes() throws IOException {
        //Se carga la lista de hilos que tiene el servidor
        List<HiloEscucharBrazoCliente> listaHilos = servidor.getListaHilosClientes();
        //Se recorre de cada hilo
        for (HiloEscucharBrazoCliente unHilo : listaHilos) {
            //Se revisa que el hilo no sea el hilo actual (sta misma instancia, la instancia que envía el mensaje) y que además esté activo
            if (!unHilo.getName().equals(this.getName()) && unHilo.isAlive()) {
                //Enviar mensaje a los demás
                System.out.println(raizLog + "Se va a actualizar el valor en los demás cliente .....");
                unHilo.enviarMensaje(this.nombreSlider, this.valorSlider);
            }

        }
    }

    /**
     * Método que permite enviar mensaje a los demás hilos
     *
     * @param nombreSliderEnviado
     * @param ValorSliderEnviado
     * @throws IOException
     */
    public void enviarMensaje(String nombreSliderEnviado, Integer ValorSliderEnviado) throws IOException {
        //Establecer el protocolo de mensaje a enviar
        String mensaje = nombreSliderEnviado + "," + ValorSliderEnviado + ",";
        //Capturo el flujo de salida y lo asocio al dato de salida
        datosSalida = new DataOutputStream(this.cliente.getOutputStream());
        //Opero con los mensajes
        System.out.println(raizLog + "Enviando a los clientes el mensaje: [" + mensaje + "]");
        datosSalida.write(mensaje.getBytes()); // ESTE ES EL PROTOCOLO
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
            modelo.girarFalDigIzq(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_DIG_DER.toString())) {
            System.out.println(raizLog + "Falange dig der");
            //Mover la falange dig der
            modelo.girarFalDigDer(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_PROX_IZQ.toString())) {
            System.out.println(raizLog + "Falange prox izq");
            //Mover la falange prox izq
            modelo.girarFalProxIzq(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.FALANGE_PROX_DER.toString())) {
            System.out.println(raizLog + "Falange prox der");
            //Mover la falange prox der
            modelo.girarFalProxDer(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.MANO.toString())) {
            System.out.println(raizLog + "Mano");
            //Mover la mano
            modelo.girarMano(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.ANTEBRAZO.toString())) {
            System.out.println(raizLog + "Antebrazo");
            //Mover el antebrazo
            modelo.girarAnteBrazo(valorSlider);

        } else if (nombreSlider.equals(NombreSlider.BRAZO.toString())) {
            System.out.println(raizLog + "Brazo");
            //Mover el antebrazo
            modelo.girarBrazo(valorSlider);

        }

    }

}
