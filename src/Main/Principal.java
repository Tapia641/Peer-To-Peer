package Main;

import Multicast.ClienteMulticast;
import Multicast.ServidorMulticast;
import java.io.File;
import javax.swing.JOptionPane;

public class Principal {

    private static int PORT;

    public static void main(String[] args) throws Exception {
        Iniciar();
    }

    public static void Iniciar() throws Exception {

        /* REQUISITO INDISPENSABLE */
        PORT = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el puerto:"));

        /* CREAMOS LA CARPETA CON EL NOMBRE DEL PUERTO */
        File directorio = new File("C:\\Users\\Tapia\\Desktop\\" + String.valueOf(PORT));
        directorio.mkdir();

        /* TRABAJANDO CON MULTICAST */
        ClienteMulticast CM = new ClienteMulticast(PORT);
        ServidorMulticast SM = new ServidorMulticast(PORT);
        SM.start();
        CM.start();

        /*TRABAJANDO CON FLUJO*/
    }

}
