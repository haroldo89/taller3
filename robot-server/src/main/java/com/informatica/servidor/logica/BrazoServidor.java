package com.informatica.servidor.logica;

import com.informatica.servidor.presentacion.Modelo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite generar un servidor y conectarse con varios clientes
 *
 * @author Vamaya
 */
public class BrazoServidor {

    //VARIABLES
    private final Modelo modelo; //Se crea una unión con el modelo para poder modificar el dibujo del brazo
    private ServerSocket server; //Socket del servidor
    private final int puerto; //Puerto para iniciar la conexión
    private Socket cliente; //Socket del cliente
    private final List<HiloEscucharBrazoCliente> listaHilosClientes = new ArrayList<>(); //Arreglo de hilos instanciaos por cada cliente
    private final boolean conectarActivo; //Variable booleana para amntener al servidor recibiendo clientes

    //CONSTRUCTOR
    public BrazoServidor(Modelo modelo) {
        this.puerto = 5000;
        this.conectarActivo = true;
        this.modelo = modelo;
    }

    //MÉTODOS
    /**
     * Método que permite iniciar el servidor y quedarse esperando por la
     * conexión de un nuevo cliente
     */
    public void conectar() {
        System.out.println("Iniciando Servidor en el puerto [" + puerto + "] ......");
        try {
            server = new ServerSocket(puerto);//Inicia el servidor en el puerto indicado
        } catch (IOException e) {
            System.out.println("Error de servidor, no se pudo iniciar conexión en el puerto [" + puerto + "]");
        }
        //Se inicia ciclo para quedarse escuchando cliente..
        while (conectarActivo) {
            try {
                System.out.println("Esperando que se conecte un cliente .....");
                cliente = server.accept();//Bloque la ejecución de todo lo demás hasta que no se genere una conexión
                System.out.println("Conexión establecida con el cliente");
                //Se genera un nuevo hilo de ejecución para recibir datos por parte del cliente
                HiloEscucharBrazoCliente hiloCliente = new HiloEscucharBrazoCliente(cliente, modelo, this);
                System.out.println("Conexión atendida, se recibirán datos de este cliente en el hilo [" + hiloCliente.getName() + "]");
                listaHilosClientes.add(hiloCliente);//Se agrega el hilo que atendió la conexión a la lista de hilos
                hiloCliente.start();//Se inicia el hilo
            } catch (IOException e) {
                System.out.println("Error de conexión con el cliente");
            }

        }

    }

    
    //GETTER
    public List<HiloEscucharBrazoCliente> getListaHilosClientes() {
        return listaHilosClientes;
    }

}
