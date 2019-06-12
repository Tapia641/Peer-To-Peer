package Flujo;


import java.io.*;
import java.net.Socket;

public class ClienteArchivo extends Thread{

    private  int PORT;
    private String nombre;

    public ClienteArchivo(String nombre, int PORT) {
        this.PORT = PORT;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        IniciarCliente();
    }
    
    public void IniciarCliente() {
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        
        //Fichero a transferir
        
        /*OBTENEMOS EL USUARIO DE CADA COMPUTADORA*/
        File user = new File(System.getProperty("user.name"));
        String url ="C:\\Users\\" + user + "\\Documents\\";
        final String filename = url + PORT + "\\" + nombre;

        try {
            final File localFile = new File(filename);
            Socket client = new Socket("localhost", 9050);
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(client.getOutputStream());
            //Enviamos el nombre del fichero
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(localFile.getName());
            //Enviamos el fichero
            byteArray = new byte[8192];
            while ((in = bis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
            }

            bis.close();
            bos.close();
            client.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
