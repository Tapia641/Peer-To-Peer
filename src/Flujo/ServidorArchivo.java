package Flujo;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ServidorArchivo extends Thread {

    private int PORT;
    private String file;

    public ServidorArchivo(String nombre, int PORT) {
        this.file = nombre;
        this.PORT = PORT;
    }

    @Override
    public void run() {
        IniciarServidor();
    }

    public void IniciarServidor() {

        ServerSocket server;
        Socket connection;

        DataOutputStream output;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;

        try {
            server = new ServerSocket(9050);
//            while (true) {
            //Aceptar conexiones
            connection = server.accept();
            //Buffer de 1024 bytes
            receivedData = new byte[1024];
            bis = new BufferedInputStream(connection.getInputStream());
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
            file = file.substring(file.indexOf('\\') + 1, file.length());
            //Para guardar fichero recibido

            bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Tapia\\Desktop\\" + PORT + "\\" + file));
            JOptionPane.showMessageDialog(null, "Se guarda en : " + PORT);
            while ((in = bis.read(receivedData)) != -1) {
                bos.write(receivedData, 0, in);
            }
            bos.close();
            dis.close();
            server.close();
            //          }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
