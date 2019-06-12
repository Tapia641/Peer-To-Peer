/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE*/

package Multicast;

/*
OBJETIVO:
ANUNCIAR CADA 5 SEGUNDOS EL PUERTO DE SERVICIO DEL SERVIDOR DE DATAGRAMAS
 */

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMulticast extends Thread {

    public int Port_Datagrama;

    public ServidorMulticast(int PORT) {
        this.Port_Datagrama = PORT;
    }

    @Override
    public void run() {
        iniciarServidor();
    }

    public void iniciarServidor() {
        
        /* CREAMOS EL GRUPO */
        InetAddress grupo = null;

        try {

            /* SOCKET MULTICAST CON EL PUERTO INGRESADO */
            MulticastSocket servidorSocket = new MulticastSocket(1234);
            servidorSocket.setReuseAddress(true);
            servidorSocket.setTimeToLive(1);

            /* UNIENDOSE AL GRUPO */
            String mensaje = String.valueOf(Port_Datagrama);
            byte[] b = mensaje.getBytes();
            grupo = InetAddress.getByName("228.1.1.1");
            servidorSocket.joinGroup(grupo);

            System.out.println("Servidor Multicast iniciado con exito...");

            while (true) {

                /* ----------------COMENZAMOS A ENVIAR MENSAJE---------------- */

                /* SE ENVIARA A TRAVES DEL 7999 DONDE ESTAN A LA ESCUCHA*/
                DatagramPacket paqueteMensaje = new DatagramPacket(b, b.length, grupo, 7999);

                /* MANDANDO MENSAJE */
                servidorSocket.send(paqueteMensaje);
                System.out.println("Mensaje: Puerto del servidor de datagramas "
                        + Integer.parseInt(mensaje) + ", con TTL: "
                        + servidorSocket.getTimeToLive());

                /* ESPERAMOS LOS 5 SEGUNDOS, MEJOR 1 ;D */
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
