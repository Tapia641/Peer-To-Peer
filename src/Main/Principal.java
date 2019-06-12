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
        
        /*OBTENEMOS EL USUARIO DE CADA COMPUTADORA*/
        File user = new File(System.getProperty("user.name"));
        String url ="C:\\Users\\" + user + "\\Documents\\" + String.valueOf(PORT);
        
        /*CREAMOS EL DIRECTORIO EN SUS DOCUMENTOS*/
        File dir = new File(url);

        /* CREAMOS LA CARPETA CON EL NOMBRE DEL PUERTO */
        dir.mkdir();

        /* TRABAJANDO CON MULTICAST */
        ClienteMulticast CM = new ClienteMulticast(PORT);
        ServidorMulticast SM = new ServidorMulticast(PORT);
        SM.start();
        CM.start();

        /*TRABAJANDO CON FLUJO*/
    }

}
