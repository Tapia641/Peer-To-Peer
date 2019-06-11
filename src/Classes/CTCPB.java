package Classes;


/* ALUMNO: HERNANDEZ TAPIA LUIS ENRIQUE */
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CTCPB{

    private int PORT;
    private String mensaje, IP;

    public CTCPB(String IP, int PORT, String mensaje) {
        this.IP = IP;
        this.PORT = PORT;
        this.mensaje = mensaje;
    }

    public void Cliente() {
        /* SIEMPRE PONER EL SOCKET EN UN TRY-CATCH */
        try {
            /* PREPARAMOS LOS DATOS DE LA CONEXION */
            //BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            //System.out.println("Escribe la direccion del servidor:");
            //String host = teclado.readLine();
            //System.out.printf("\nEscriba el puerto:");
            //int port = Integer.parseInt(teclado.readLine());

            /* CERRAMOS EL SOCKET DEL LADO DEL CLIENTE */
            Socket socketCliente = new Socket(IP, PORT);

            /* OBTENEMOS EL CANAL DE ENTRADA */
            //BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            /* OBTENEMOS EL CANAL DE SALIDA */
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

            /* ENVIAMOS INFORMACION AL SERVIDOR */
            //System.out.println("Enviando saludo...");
            //String mensaje = "Hola servidor, soy el cliente.";
            salida.println(mensaje);
            salida.flush();// Lo que faltaba ;)

            /* RECIBIMOS INFORMACION DEL SERVIDOR */
//            mensaje = entrada.readLine();
            //          System.out.println("Recibimos un mensaje del servidor");
            //        System.out.println("Mensaje: " + mensaje);
            // SE LIMPIA EL FLUJO EN ORDEN
            salida.flush();
            salida.close();
            //      teclado.close();
            //    entrada.close();
            socketCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
