package Interface;

import Classes.CTCPB;
import Classes.NodoIP;
import Classes.STCPB;
import Datagrama.ClienteDatagrama;
import Flujo.ClienteArchivo;
import Flujo.ServidorArchivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Tapia
 */
public class Interface extends javax.swing.JFrame {

    public DefaultListModel modelo;
    public NodoIP MiNodo;
    public String IP;
    public int PORT;
    public ClienteDatagrama CD;
    public Vector<Integer> V;
    public Pair<String, Integer> P, PSiguiente, PAnterior;
    public STCPB ServerTexto;
    public CTCPB ClientTexto;
    public ServerTexto ST;

    public Interface(int PORT) {
        CD = new ClienteDatagrama();
        initComponents();
        modelo = new DefaultListModel();
        V = new Stack<>();
        this.PORT = PORT;
        ST = new ServerTexto();
        ST.start();

    }

    public void AgregarElementos(String IP, int PORT) {
        modelo.addElement(String.valueOf(IP + ":" + PORT));
        jListLista.setModel(modelo);
        //this.PORT = PORT;
        //this.IP = IP;
    }

    public void AgregarAnterior(String text) {
        jTextFieldAnterior.setText(text);
    }

    public void AgregarSiguiente(String text) {
        jTextFieldSiguiente.setText(text);

    }

    public void ActualizarNodo(NodoIP MiNodo) {
        this.MiNodo = MiNodo;
    }

    public void ActualizarVector(Vector<Integer> V) {
        this.V = V;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListLista = new javax.swing.JList<>();
        jTextFieldAnterior = new javax.swing.JTextField();
        jTextFieldSiguiente = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaTexto = new javax.swing.JTextArea();
        jButtonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jListLista);

        jLabel1.setText("Siguiente");

        jLabel2.setText("Anterior");

        jTextAreaTexto.setColumns(20);
        jTextAreaTexto.setRows(5);
        jScrollPane2.setViewportView(jTextAreaTexto);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(jTextFieldAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscar)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMouseClicked
        String archivo = jTextFieldNombre.getText();
        String texto = "";
        int i = -1;
        NodoIP Aux = MiNodo;

        while (i != V.size()) {
            //texto = "Se busca en: " + MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue() + "\n";
/*
            ClientTexto = new CTCPB("127.0.0.1", MiNodo.getActual().getValue(),
                    "El servidor: " + MiNodo.getAnterior().getKey() + ":"
                    + MiNodo.getAnterior().getValue()
                    + " Preguntó por el archivo: " + archivo);
            ClientTexto.Cliente();
             */

            if (Existe(archivo, MiNodo.getActual().getValue())) {

                texto = "Se encontró el archivo en: " + MiNodo.getActual().getKey()
                        + ":" + MiNodo.getActual().getValue()
                        + " procedemos a descargarlo " + "\n";
                jTextAreaTexto.setText(texto);

                ClientTexto = new CTCPB("127.0.0.1", MiNodo.getActual().getValue(),
                        "Tengo el archivo: " + archivo);
                ClientTexto.Cliente();

                try {
                    RecibirArchivo(MiNodo.getActual().getKey(), PORT, MiNodo.getActual().getValue(), archivo);

                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            } else {
                String text = "El servidor " + MiNodo.getAnterior().getKey()
                        + ":" + MiNodo.getAnterior().getValue()
                        + " pregunta por el archivo: " + archivo
                        + " No lo tengo, pregunto a: " + MiNodo.getSiguiente().getKey() + ":"
                        + MiNodo.getSiguiente().getValue();

                ClientTexto = new CTCPB("127.0.0.1", MiNodo.getActual().getValue(), text
                );

                ClientTexto.Cliente();

            }

            Mueve();
            i++;

        }

        if (i == V.size()) {
            jTextAreaTexto.setText("");
            texto = "No se encontró el archivo especificado.";
            MiNodo = Aux;
            jTextAreaTexto.setText(texto);
        }


    }//GEN-LAST:event_jButtonBuscarMouseClicked

    public static boolean Existe(String nombre, int pos) {
        File filearchivo = new File("C:\\Users\\Tapia\\Desktop\\" + pos + "\\" + nombre);
        if (filearchivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void RecibirArchivo(String IP, int PortDestino, int PortOrigen, String nombre) throws IOException, InterruptedException {

        //Donde se va a guardar
        //JOptionPane.showMessageDialog(null, "Donde se va a guardar: " + PORT);
        ServidorArchivo SA = new ServidorArchivo(nombre, PortDestino);
        SA.start();
        sleep(1000);

        //Fichero a transferir     
        //JOptionPane.showMessageDialog(null, "Origen: " + delista);
        ClienteArchivo CA = new ClienteArchivo(nombre, PortOrigen);
        CA.start();
    }

    public void Mueve() {
        P = new Pair<>(MiNodo.getSiguiente().getKey(), MiNodo.getSiguiente().getValue());
        MiNodo.setActual(P);

        int Pos = V.indexOf(P.getValue());

        if (Pos == 0) {
            PAnterior = new Pair<>(P.getKey(), V.get(V.size() - 1));
            PSiguiente = new Pair<>(P.getKey(), V.get(Pos + 1));

            MiNodo.setSiguiente(PSiguiente);
            MiNodo.setAnterior(PAnterior);
        } else if (Pos == V.size() - 1) {
            PAnterior = new Pair<>(P.getKey(), V.get(Pos - 1));
            PSiguiente = new Pair<>(P.getKey(), V.get(0));

            MiNodo.setSiguiente(PSiguiente);
            MiNodo.setAnterior(PAnterior);
        } else {
            PAnterior = new Pair<>(P.getKey(), V.get(Pos - 1));
            PSiguiente = new Pair<>(P.getKey(), V.get(Pos + 1));

            MiNodo.setSiguiente(PSiguiente);
            MiNodo.setAnterior(PAnterior);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaTexto;
    private javax.swing.JTextField jTextFieldAnterior;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldSiguiente;
    // End of variables declaration//GEN-END:variables

    class ServerTexto extends Thread {

        @Override
        public void run() {
            IniciarservidorTexto();
        }

        public void IniciarservidorTexto() {
            String infoCliente = "";
            /* SIEMPRE PONER EL SOCKET EN UN TRY-CATCH */
            try {
                /* PUERTO EN EL QUE ESCUCHA PETICIONES */
                ServerSocket socketServidor = new ServerSocket(PORT);
                System.out.println("Esperando peticiones...");

                while (true) {
                    /* BLOQUEO HASTA QUE RECIBA ALGUNA PETICION DEL CLIENTE */
                    Socket socketCliente = socketServidor.accept();
                    //System.out.println(
                    //      "Conexion establecida desde " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());

                    /* ESTABLECEMOS EL CANAL DE ENTRADA */
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

                    /* OBTENEMOS EL CANAL DE SALIDA */
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

                    /* RECIBIMOS INFORMACION DEL CLIENTE */
                    infoCliente = entrada.readLine();
                    //System.err.println("Recibimos un mensaje del cliente " + infoCliente);
                    //System.out.println("Mensaje: " + infoCliente);

                    /* ENVIAMOS INFORMACION AL CLIENTE */
                    //System.out.println("Enviando respuesta...");
                    //String resCliente = "Hola cliente, soy el servidor.";
                    //salida.println(resCliente);
                    // SE LIMPIA EL FLUJO EN ORDEN
                    jTextAreaTexto.setText(infoCliente + "\n");

                    salida.flush();
                    salida.close();
                    entrada.close();

//                    jTextAreaTexto.setText(jTextAreaTexto.getText() +"\n"+ MiNodo.getActual().);
                    //socketCliente.close();
                    // NOTA: EL SOCKET DEL SERVIDOR NUNCA SE CIERRA socketServidor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
