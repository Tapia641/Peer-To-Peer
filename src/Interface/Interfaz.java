package Interface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Interfaz extends javax.swing.JFrame {

        /*
     *atributos para manejar salida y entrada de texto
     */

    BufferedReader delTeclado;
    DataOutputStream alservidor;
    FileInputStream entrada;

    /*
     *atributos para manejar entrada de archivos
     */
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    FileOutputStream destino = null;
    BufferedOutputStream out = null;
    BufferedInputStream in = null;
    boolean respuesta = false;
    int tam = 0;
    String nombreArchivo = null;

    //Metodo iniciar el servidor cliente
    public void iniciar() {
        try {
            Socket yo = new Socket("127.0.0.1", 9000);

            delTeclado = new BufferedReader(new InputStreamReader(System.in));
            alservidor = new DataOutputStream(yo.getOutputStream());
            DataInputStream delServidor = new DataInputStream(yo.getInputStream());

            //System.out.println("Teclee el nombre del archivo:");
            //String el = delTeclado.readLine();
            alservidor.writeUTF(nombreArchivo);
            respuesta = delServidor.readBoolean();

            if (respuesta == true) {
                // synchronized (this) {
                System.out.println("[Servidor]" + delServidor.readUTF());
                System.out.println("[Servidor]:" + delServidor.readUTF());
                tam = delServidor.readInt();
                nombreArchivo = delServidor.readUTF();

                destino = new FileOutputStream("C:\\Users\\Tapia\\Desktop\\" + nombreArchivo);
                out = new BufferedOutputStream(destino);
                in = new BufferedInputStream(yo.getInputStream());
                // Creamos el array de bytes para leer los datos del archivo

                byte[] buffer = new byte[tam];

                // Obtenemos el archivo mediante la lectura de bytes enviados
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) in.read();
                }
                // Escribimos el archivo 
                out.write(buffer);

                System.out.println("Se recibió el archivo");

                out.flush();
                //abrir_archivo();
                in.close();
                out.close();
                yo.close();
            } else {
                System.out.println("[Servidor]" + delServidor.readUTF());

                yo.close();
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage() + " cliente");
        }

    }


    public Interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListLista = new javax.swing.JList<>();
        jTextFieldIPSiguiente = new javax.swing.JTextField();
        jTextFieldIPAnterior = new javax.swing.JTextField();
        jToggleButtonBuscar = new javax.swing.JToggleButton();
        jTextFieldNombreArchivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jListLista);

        jTextFieldIPSiguiente.setText("IP Siguiente");

        jTextFieldIPAnterior.setText("IP Anterior");

        jToggleButtonBuscar.setText("Buscar");
        jToggleButtonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButtonBuscarMouseClicked(evt);
            }
        });

        jTextFieldNombreArchivo.setText("Nombre del archivo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldIPSiguiente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombreArchivo)
                            .addComponent(jTextFieldIPAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jToggleButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jTextFieldIPAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldIPSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(jTextFieldNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonBuscar)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonBuscarMouseClicked
        nombreArchivo = jTextFieldNombreArchivo.getText();
        iniciar();
    }//GEN-LAST:event_jToggleButtonBuscarMouseClicked


    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s = null;
        ServerSocket ss = null;
        Interfaz.Tarea ob;
        int id = 0;
        int PORT = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de puerto"));
        
        Interfaz IL = new Interfaz();
        IL.setTitle(String.valueOf(PORT));
        IL.setVisible(true);
        IL.setLocationRelativeTo(null);

        try {

            ss = new ServerSocket(PORT);
            while(true){
            
                Interfaz.Tarea.escribir("Socket escuchando en puerto " + PORT);
                s = ss.accept();
                id++;
                Interfaz.Tarea.escribir("\nSe conecto el cliente No." + id + " desde la IP: " + s.getInetAddress());
                Interfaz.Tarea.escribir("**************************************************");
                //(new Tarea(s, id)).start();
                ob = new Interfaz.Tarea(s, id);

                //ob.join();
                ob.start();
                ob.join();

            }

        } catch (IOException e) {
            Interfaz.Tarea.escribir(e.getMessage() + " [servidor]");
            System.exit(3);
        } finally {
        }

    }
    
    
    static class Tarea extends Thread {

        int id;
        Socket s = null;
        /*
         *Atributos para manejar salida y entrada de texto
         */
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        DataInputStream entradaCliente = null;
        DataOutputStream salidaCliente = null;

        /*
         *Atributos para manejar salida de archivos
         */
        File archivo = null;
        long tiempoinicio = 0;
        long tiempofinal= 0;
        long tiempo_total=0;
        long initialTime;
        private Tarea(Socket socket, int id) {
            this.s = socket;
            this.id = id;
        }

        boolean checar(String nombre) {
            archivo = new File("C:\\Users\\Tapia\\Desktop\\" + nombre);
            if (archivo.exists()) {
                return true;
            } else {
                return false;
            }
        }

        public void run() {
            
            
            try {
                entradaCliente = new DataInputStream(s.getInputStream());
                salidaCliente = new DataOutputStream(s.getOutputStream());
                String nombreArchivo = entradaCliente.readUTF();
                if (checar(nombreArchivo) == true) {
                    tiempoinicio=(System.currentTimeMillis() - this.initialTime);
                    System.out.println("El cliente :" + id + " comienza la transferencia EN EL TIEMPO: "
                    + (System.currentTimeMillis() - this.initialTime)
                    + " milisegundos");
                    salidaCliente.writeBoolean(true);
                    salidaCliente.writeUTF("SI existe el archivo:" + nombreArchivo + " en el servidor");
                    salidaCliente.writeUTF("Tamaño del archivo:" + (archivo.length() / 1024) + " KB | Nombre:" + archivo.getName());
                    salidaCliente.writeInt((int) archivo.length());
                    salidaCliente.writeUTF(nombreArchivo);
                    escribir("Enviando archivo:" + nombreArchivo + " a " + s.getInetAddress());
                    FileInputStream entrada = new FileInputStream(archivo);
                    BufferedInputStream leerArch = new BufferedInputStream(entrada);
                    // Creamos el flujo de salida 
                    BufferedOutputStream salida = new BufferedOutputStream(s.getOutputStream());
                    // Creamos un array de tipo byte 
                    byte[] arreglo = new byte[(int) archivo.length()];
                    // Leemos el archivo y lo introducimos en el array de bytes 
                    leerArch.read(arreglo);
                    // Realizamos el envio de los bytes que conforman el archivo

                    for (int i = 0; i < arreglo.length; i++) {
                        salida.write(arreglo[i]);
                    }
                    tiempofinal=(System.currentTimeMillis() - this.initialTime);
                    tiempo_total=tiempofinal-tiempoinicio;
                    escribir("Archivo Enviado a cliente:" + id);

                    System.out.println("El servidor termino con cliente" + id + "  EN UN TIEMPO DE: "
                            + tiempo_total + " milisegundos");
                    System.out.println("Tiempo del cliente "+id +": ("+(System.currentTimeMillis() - this.initialTime)+") milisegundos");

                    salida.flush();
                    salida.flush();
                    salida.close();
                    entrada.close();

                    //escribir("Tiempo de envio:"+"("+(t0-t1)+")"+" milisegundos");
                }

                if (checar(nombreArchivo) == false) {
                    salidaCliente.writeBoolean(false);
                    salidaCliente.writeUTF("NO existe el archivo:" + nombreArchivo + " en el servidor");
                    escribir("se envio respuesta al cliente");
                }
            } catch (Exception ex) {
                escribir(ex.getMessage() + " id:" + id);
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                    if (s != null) {
                        s.close();
                    }
                    System.out.println("Termino proceso para cliente: " + id);

                } catch (Exception e) {
                    System.out.println(e.getMessage() + " [servidor]");
                }
            }
        }

        public static void escribir(String txt) {
            System.out.println(txt);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jListLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldIPAnterior;
    private javax.swing.JTextField jTextFieldIPSiguiente;
    private javax.swing.JTextField jTextFieldNombreArchivo;
    private javax.swing.JToggleButton jToggleButtonBuscar;
    // End of variables declaration//GEN-END:variables
}
