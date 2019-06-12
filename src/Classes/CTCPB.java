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
            /* CERRAMOS EL SOCKET DEL LADO DEL CLIENTE */
            Socket socketCliente = new Socket(IP, PORT);

            /* OBTENEMOS EL CANAL DE SALIDA */
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

            /* ENVIAMOS INFORMACION AL SERVIDOR */
            salida.println(mensaje);
            salida.flush();// Lo que faltaba ;)

            // SE LIMPIA EL FLUJO EN ORDEN
            salida.flush();
            salida.close();

            socketCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
