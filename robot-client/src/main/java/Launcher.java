
import com.informatica.cliente.presentacion.Modelo;


/**
 *
 * @author Vamaya
 */
public class Launcher {
    private final Modelo modelo;
    
    /**
     * Constructor del lanzador, para inicar la aplicación
     */
    public Launcher() {
        modelo = new Modelo();
        modelo.iniciar();
    }
    
    /**
     * Metodo Main que permite el inicio de la aplicación principal
     * @param args 
     */
    public static void main(String[] args) {
        new Launcher();
    }
}
