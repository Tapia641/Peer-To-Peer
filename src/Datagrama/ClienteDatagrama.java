package Datagrama;

import Classes.NodoIP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteDatagrama extends Thread {

    public String Nombre_Archivo;
    private String destino;
    public int PORT;
    private String res;

    public ClienteDatagrama() {
    }

    public String RealizarConsulta(String Nombre_Archivo, int PORT, String IP) {
        try {

            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = Nombre_Archivo.getBytes();
            String newIP = IP.replaceAll("/", "");
            InetAddress hostServidor = InetAddress.getByName(newIP);
            int puertoServidor = PORT;

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion
                    = new DatagramPacket(mensaje, Nombre_Archivo.length(), hostServidor,
                            puertoServidor);

            // Enviamos el datagrama
            System.out.println("Enviamos al servidor: " + Nombre_Archivo);

            socketUDP.send(peticion);

            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta
                    = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);

            // Enviamos la respuesta
            res = new String(respuesta.getData(), 0, respuesta.getLength());
            System.out.println("Respuesta: " + res);

            // Cerramos el socket
            socketUDP.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }
}
