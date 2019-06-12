/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE*/

package Datagrama;

/*
OBJETIVO:

 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.File;

public class ServidorDatagrama extends Thread {
    
    public int PORT;
    public String nombre;
    File filearchivo = null;
    
    public ServidorDatagrama(int PORT) {
        this.PORT = PORT;
    }
    
    @Override
    public void run() {
        iniciarServidor();
    }
    
    public void iniciarServidor() {
        
        try {
            DatagramSocket socketUDP = new DatagramSocket(PORT);
            byte[] bufer = new byte[1000];

            // Construimos el DatagramPacket para recibir peticiones
            DatagramPacket peticion
                    = new DatagramPacket(bufer, bufer.length);

            // Leemos una petición del DatagramSocket
            socketUDP.receive(peticion);
            
            nombre = new String(peticion.getData(), 0, peticion.getLength());
            String res;
            System.out.println("Nombre del archivo a buscar: " + nombre);
            if (Existe(PORT, nombre)) {
                res = String.valueOf(PORT);
            } else {
                res = "-1";
            }
            // Construimos el DatagramPacket para enviar la respuesta
            DatagramPacket respuesta = new DatagramPacket(res.getBytes(), res.length(),
                    peticion.getAddress(), peticion.getPort());

            // Enviamos la respuesta, que es un eco
            socketUDP.send(respuesta);
            socketUDP.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean Existe(int Carpeta, String Nombre_Archivo) {
        File file = new File("C:\\Users\\Tapia\\Desktop\\" + Carpeta + "\\" + Nombre_Archivo);
        return file.exists();
    }
    
}
