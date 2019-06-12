/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE*/
package Interface;

import Classes.CTCPB;
import Classes.MD5Checksum;
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
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.util.Pair;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Interface extends javax.swing.JFrame {

    public DefaultListModel modelo;
    public NodoIP MiNodo;
    public String IP;
    public int PORT;
    public ClienteDatagrama CD;
    public Vector<Integer> V;
    public Hashtable<Integer, String> ListaNodos;
    public Pair<String, Integer> P, PSiguiente, PAnterior;
    public CTCPB ClientTexto;
    public ServerTexto ST;
    public Vector<String> ListaEncontrados;

    public Interface(int PORT) {
        CD = new ClienteDatagrama();
        initComponents();
        modelo = new DefaultListModel();
        V = new Stack<>();
        this.PORT = PORT;
        ListaEncontrados = new Stack<>();
        ST = new ServerTexto();
        ST.start();

    }

    public void ActualizarHastable(Hashtable<Integer, String> ListaNodos) {
        this.ListaNodos = ListaNodos;
    }

    public void AgregarElementos(String IP, int PORT) {
        modelo.addElement(String.valueOf(IP + ":" + PORT));
        jListLista.setModel(modelo);
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

        while (i < V.size()) {

            if (Existe(archivo, MiNodo.getActual().getValue())) {

//                /*CREAMOS EL TEXTO A MOSTRAR EN LA VENTANA*/
//                texto = "Se encontró el archivo en: " + MiNodo.getActual().getKey()
//                        + ":" + MiNodo.getActual().getValue()
//                        + " procedemos a descargarlo " + "\n";

                /*SE LO MANDAMOS*/
                //jTextAreaTexto.setText(texto);
                if (i == V.size() - 2) {
                    /*CREAMOS UNA INSTANCIA*/
                    ClientTexto = new CTCPB(MiNodo.getActual().getKey(), MiNodo.getActual().getValue(),
                            "Tengo el archivo: " + archivo + ".");

                    /*LO EJECUTAMOS*/
                    ClientTexto.Cliente();

                    /*OBTENEMOS EL MD5*/
                    File user = new File(System.getProperty("user.name"));
                    String url = "C:\\Users\\" + user + "\\Documents\\" + MiNodo.getActual().getValue() + "\\" + archivo;
                    MD5Checksum MD = new MD5Checksum();
                    String MD5 = "";
                    try {
                        MD5 = MD.getMD5Checksum(url);
                    } catch (Exception ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!ListaEncontrados.contains(MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue() + ":" + MD5)) {

                        ListaEncontrados.add(MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue() + ":" + MD5);
                        //ListaEncontrados.add(MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue());
                    }

                    break;

                } else {

                    /*CREAMOS UNA INSTANCIA*/
                    ClientTexto = new CTCPB(MiNodo.getActual().getKey(), MiNodo.getActual().getValue(),
                            "Tengo el archivo: " + archivo + " Pregunto al siguiente "
                            + MiNodo.getSiguiente().getKey() + ":" + MiNodo.getSiguiente().getValue());

                    /*LO EJECUTAMOS*/
                    ClientTexto.Cliente();

                    /*OBTENEMOS EL MD5*/
                    File user = new File(System.getProperty("user.name"));
                    String url = "C:\\Users\\" + user + "\\Documents\\" + MiNodo.getActual().getValue() + "\\" + archivo;
                    MD5Checksum MD = new MD5Checksum();
                    String MD5 = "";
                    try {
                        MD5 = MD.getMD5Checksum(url);
                    } catch (Exception ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //LO AGREGAMOS A LA LISTA
                    if (!ListaEncontrados.contains(MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue() + ":" + MD5)) {

                        ListaEncontrados.add(MiNodo.getActual().getKey() + ":" + MiNodo.getActual().getValue() + ":" + MD5);
                    }
                }

                /*MÉTODO PARA DESCARGAR EN LA PRÁCTICA PASADA*/
//                try {
//                    RecibirArchivo(MiNodo.getActual().getKey(), PORT, MiNodo.getActual().getValue(), archivo);
//
//                } catch (IOException | InterruptedException ex) {
//                    ex.printStackTrace();
//                }
                //break;
            } else {

                if (i == V.size() - 1) {
                    /*CUANDO LLEGAMOS AL ÚLTIMO NODO*/
                    String text = "Terminó la busqueda.";

                    ClientTexto = new CTCPB(MiNodo.getActual().getKey(), MiNodo.getActual().getValue(), text);
                    ClientTexto.Cliente();
                    break;

                } else {

                    String text = "El servidor " + MiNodo.getAnterior().getKey()
                            + ":" + MiNodo.getAnterior().getValue()
                            + " pregunta por el archivo: " + archivo
                            + " No lo tengo, pregunto a: " + MiNodo.getSiguiente().getKey() + ":"
                            + MiNodo.getSiguiente().getValue();

                    ClientTexto = new CTCPB(MiNodo.getActual().getKey(), MiNodo.getActual().getValue(), text);
                    ClientTexto.Cliente();
                }

            }

            Mueve();
            i++;

        }

//        if (i == V.size()) {
//            //jTextAreaTexto.setText("");
//            texto = "No se encontró el archivo especificado.";
//            MiNodo = Aux;
//            jTextAreaTexto.setText(texto);
//        }
        if (!ListaEncontrados.isEmpty()) {
            //System.err.println(ListaEncontrados.toString());
            String texto_mostrar = "\n";
            for (String textoaux : ListaEncontrados) {
                texto_mostrar += textoaux + "\n";
            }
            jTextAreaTexto.setText(jTextAreaTexto.getText() + "\n" + "Se encontró en :\n" + texto_mostrar);

            /*PROCEDEMOS A DESCARGAR*/
            Vector<String> Iguales = new Stack<>();
            String cad[];

            for (String encontrado : ListaEncontrados) {
                cad = encontrado.split(Pattern.quote(":"));
                if (!Iguales.contains(cad[2])) {
                    Iguales.add(cad[2]);
                }
            }

            JOptionPane.showMessageDialog(null, "Se encontró los siguientes MD5 para el mismo archivo\n"
                    + Iguales.toString());

            String total = 1 + "/" + ListaEncontrados.size();
            jTextAreaTexto.setText(jTextAreaTexto.getText() + "\n" + "Se obtiene: " + total + " de cada nodo.");
            int j = 0;
            for (String s : ListaEncontrados) {
                cad = s.split(Pattern.quote(":"));

                if (Iguales.contains(cad[2])) {
                    j++;
                    try {
                        RecibirArchivo(cad[0], PORT, Integer.parseInt(cad[1]), archivo, "Parte: " + j);
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //break;
                }

            }

            ListaEncontrados.clear();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo en la topología.");
        }

    }//GEN-LAST:event_jButtonBuscarMouseClicked

    public static boolean Existe(String nombre_archivo, int pos) {

        /*OBTENEMOS EL USUARIO DE CADA COMPUTADORA*/
        File user = new File(System.getProperty("user.name"));
        String url = "C:\\Users\\" + user + "\\Documents\\" + pos + "\\" + nombre_archivo;
        File filearchivo = new File(url);

        if (filearchivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void RecibirArchivo(String IP, int PortDestino, int PortOrigen, String nombre_archivo, String parte) throws IOException, InterruptedException {
        JOptionPane.showMessageDialog(null, "Descargando de: " + PortOrigen + " A: " + PortDestino + " " + parte);

        if (parte.equals("Parte: " + String.valueOf(ListaEncontrados.size() - 1))) {
            //Donde se va a guardar
            ServidorArchivo SA = new ServidorArchivo(nombre_archivo, PortDestino);
            SA.start();
            sleep(1000);

            //Fichero a transferir     
            ClienteArchivo CA = new ClienteArchivo(nombre_archivo, PortOrigen, IP);
            CA.start();
        }

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

        private String infoCliente = "";

        @Override
        public void run() {
            IniciarservidorTexto();
        }

        public void IniciarservidorTexto() {

            /* SIEMPRE PONER EL SOCKET EN UN TRY-CATCH */
            try {

                /* PUERTO EN EL QUE ESCUCHA PETICIONES */
                ServerSocket socketServidor = new ServerSocket(PORT);
                System.out.println("Esperando peticiones...");

                while (true) {
                    /* BLOQUEO HASTA QUE RECIBA ALGUNA PETICION DEL CLIENTE */
                    Socket socketCliente = socketServidor.accept();

                    /* ESTABLECEMOS EL CANAL DE ENTRADA */
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

                    /* OBTENEMOS EL CANAL DE SALIDA */
                    PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

                    /* RECIBIMOS INFORMACION DEL CLIENTE */
                    infoCliente = entrada.readLine() + "\n";

                    /* ENVIAMOS INFORMACION AL CLIENTE */
                    jTextAreaTexto.setText(infoCliente);

                    salida.flush();
                    salida.close();
                    entrada.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
