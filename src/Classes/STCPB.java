/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE*/

package Classes;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class STCPB extends Thread {

    private int PORT;
    private String infoCliente;

    @Override
    public void run() {
        Servidor();
    }

    public STCPB(int PORT) {
        this.PORT = PORT;
    }

    public String Servidor() {
        
        /* SIEMPRE PONER EL SOCKET EN UN TRY-CATCH */
        
        try {
            
            /* PUERTO EN EL QUE ESCUCHA PETICIONES */
            ServerSocket socketServidor = new ServerSocket(PORT);
            System.out.println("Esperando cliente...");

            while (true) {
                
                /* BLOQUEO HASTA QUE RECIBA ALGUNA PETICION DEL CLIENTE */
                Socket socketCliente = socketServidor.accept();

                /* ESTABLECEMOS EL CANAL DE ENTRADA */
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

                /* OBTENEMOS EL CANAL DE SALIDA */
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

                /* RECIBIMOS INFORMACION DEL CLIENTE */
                infoCliente = entrada.readLine();
                System.out.println("Recibimos un mensaje del cliente");
                System.out.println("Mensaje: " + infoCliente);
        
                // SE LIMPIA EL FLUJO EN ORDEN
                salida.flush();
                salida.close();
                entrada.close();
                socketCliente.close();
                // NOTA: EL SOCKET DEL SERVIDOR NUNCA SE CIERRA socketServidor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infoCliente;
    }
}
