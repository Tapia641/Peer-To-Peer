/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE*/
package Multicast;

/*
OBJETIVO:
EXTRAER LA DIRECCION IP DEL ANUNCIANTE Y EL NUMERO DE PUERTO
DEL SERVIDOR DE DATAGRAMAS DEL SERVIDOR ANUNCIANTE.
 */
import Classes.ListaCircular;
import Classes.NodoIP;
import Datagrama.ServidorDatagrama;
import Interface.Interface;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.util.Pair;

public class ClienteMulticast extends Thread {

    public ListaCircular Lista;
    public Interface Ventana;
    public int PORT;
    private NodoIP MiNodo;
    private Vector<Integer> V;
    private Hashtable<Integer, String> ListaNodos;
    private Pair<String, Integer> P, PSiguiente, PAnterior;
    private ServidorDatagrama SD;
    public Vector<Pair<String, Integer>> Temporizador;

    public ClienteMulticast(int PORT) {
        this.PORT = PORT;

        /* TRABJANDO CON DATAGRAMAS */
        SD = new ServidorDatagrama(PORT);
        SD.start();
    }

    @Override
    public void run() {
        try {
            iniciarCliente();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteMulticast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ListaCircular getListaCircular() {
        return Lista;
    }

    public void iniciarCliente() throws InterruptedException {

        /* CREAMOS EL GRUPO */
        InetAddress grupo = null;
        Ventana = new Interface(PORT);
        Ventana.setVisible(true);
        Ventana.setTitle(String.valueOf(PORT));
        V = new Stack<>();
        ListaNodos = new Hashtable<Integer, String>();
        MiNodo = new NodoIP();
        Temporizador = new Stack<>();

        try {

            /* SOCKET MULTICAST CON PUERTO 7999 */
            MulticastSocket clienteSocket = new MulticastSocket(7999);
            System.out.println("Cliente Multicast escuchando puerto " + clienteSocket.getPort());
            clienteSocket.setReuseAddress(true);

            try {

                /* DIRECCION DEL GRUPO */
                grupo = InetAddress.getByName("228.1.1.1");

            } catch (UnknownHostException e) {
                System.out.println("Direccion erronea ");
            }

            /* GRUPO POR EL CUAL SE RECIBE MULTIPLES TRAMAS */
            clienteSocket.joinGroup(grupo);
            System.out.println("Unido al grupo :)");

            while (true) {

                /* ----------------COMENZAMOS A RECIBIR EL MENSAJE------------------ */
 /* OBTENEMOS EL MENSAJE DEL SERVIDOR */
                DatagramPacket paqueteServidor = new DatagramPacket(new byte[100], 100);
                clienteSocket.receive(paqueteServidor);

                /* LO TRANSFORMAMOS A UNA CADENA */
                String mensaje = new String(paqueteServidor.getData());
                int LocalPort = (int) Double.parseDouble(mensaje);
                System.out.println("Datagrama recibido: " + paqueteServidor.getAddress() + LocalPort);

                /*AGREGAMAOS UN NODO*/
                String IPaux = String.valueOf(paqueteServidor.getAddress());
                IPaux = IPaux.replaceAll(Pattern.quote("/"), "");

                P = new Pair<>(IPaux, LocalPort);

                /*VAMOS ACTUALIZANDO TEMPORIZADOR*/
                //if (Temporizador.contains(P)) {
                    
                //}

                /*SI ES UNO NUEVO*/
                if (!V.contains(LocalPort)) {
                    V.add(LocalPort);
                    Collections.sort(V);

                    /*AGREGAMOS AL HASTABLE LA NUEVA DIRECCION*/
                    ListaNodos.put(LocalPort, IPaux);

                    /*ACTUALIZAMOS LA LISTA DE DIRECCIONES IP Y PUERTOS*/
                    Ventana.AgregarElementos(IPaux, LocalPort);
                    Ventana.ActualizarVector(V);
                    Ventana.ActualizarHastable(ListaNodos);

                } else if (V.size() > 2) {
                    System.out.println("Actual: " + P.getKey() + ":" + PORT);
                    MiNodo.setActual(P);

                    int Pos = V.indexOf(PORT);

                    if (Pos == 0) {
                        //System.out.println("Anterior: " + V.get(V.size() - 1));
                        //System.out.println("Siguiente: " + V.get(Pos + 1));

                        PSiguiente = new Pair<>(ListaNodos.get(V.get(Pos + 1)), V.get(Pos + 1));
                        PAnterior = new Pair<>(ListaNodos.get(V.get(V.size() - 1)), V.get(V.size() - 1));

                        Ventana.AgregarSiguiente(PSiguiente.getKey() + ":" + PSiguiente.getValue());
                        Ventana.AgregarAnterior(PAnterior.getKey() + ":" + PAnterior.getValue());

                        MiNodo.setSiguiente(PSiguiente);
                        MiNodo.setAnterior(PAnterior);

                    } else if (Pos == V.size() - 1) {
                        //System.out.println("Anterior: " + V.get(Pos - 1));
                        //System.out.println("Siguiente: " + V.get(0));

                        PAnterior = new Pair<>(ListaNodos.get(V.get(Pos - 1)), V.get(Pos - 1));
                        PSiguiente = new Pair<>(ListaNodos.get(V.get(0)), V.get(0));

                        Ventana.AgregarSiguiente(PSiguiente.getKey() + ":" + PSiguiente.getValue());
                        Ventana.AgregarAnterior(PAnterior.getKey() + ":" + PAnterior.getValue());

                        MiNodo.setSiguiente(PSiguiente);
                        MiNodo.setAnterior(PAnterior);

                    } else {
                        //System.out.println("Anterior: " + V.get(Pos - 1));
                        //System.out.println("Siguiente: " + V.get(Pos + 1));

                        PAnterior = new Pair<>(ListaNodos.get(V.get(Pos - 1)), V.get(Pos - 1));
                        PSiguiente = new Pair<>(ListaNodos.get(V.get(Pos + 1)), V.get(Pos + 1));

                        Ventana.AgregarSiguiente(PSiguiente.getKey() + ":" + PSiguiente.getValue());
                        Ventana.AgregarAnterior(PAnterior.getKey() + ":" + PAnterior.getValue());

                        MiNodo.setSiguiente(PSiguiente);
                        MiNodo.setAnterior(PAnterior);

                    }

                    //CD.ActualizarNodo(MiNodo);
                    Ventana.ActualizarNodo(MiNodo);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
