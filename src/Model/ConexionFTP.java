
package Model;
import java.io.BufferedOutputStream;
import java.io.File;
    import java.io.FileInputStream;
    import java.io.PrintStream;
    import java.io.IOException;
    import java.io.FileInputStream; // Abrir y leer el fichero
import org.apache.commons.net.ftp.FTPFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

    import org.apache.commons.net.ftp.FTP;
    import org.apache.commons.net.ftp.FTPClient;    

public class ConexionFTP {
    private FTPClient client;   
    private String ftp; 
    private String user;
    private String password;
    private String nombre = "";

    public ConexionFTP(FTPClient client, String ftp, String user, String password) {
        this.client = client;
        this.ftp = ftp;
        this.user = user;
        this.password = password;
    }

    public void conectar(){
        try {
            // Conactando al servidor
            client.connect(ftp);
            System.out.println(client.getReplyString());

            // Logueado un usuario (true = pudo conectarse, false = no pudo
            // conectarse)
            boolean login = client.login(user, password);
            if (login){
                System. out. println("Conectado\n");
            System.out.println(client.getReplyString());
            }else{
                System. out. println("No conectado\n");
            }
        } catch (IOException ioe) {
                System. out. println("No conectado por error\n");
        }
    }

    public void desconectar(){
        // Cerrando sesión
        try {
            client.logout();
            System.out.println(client.getReplyString());
            // Desconectandose con el servidor
            client.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subirArchivo(String local_filepath, String remote_filename){
        String remote_working_dir_path = "C:\\Users\\Maria\\Documents\\carpetaftp";

        try {
            FileInputStream fis = new FileInputStream(local_filepath);
            client.enterLocalPassiveMode(); // IMPORTANTE!!!! 
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory(remote_working_dir_path);
            boolean uploadFile = client.storeFile(remote_filename,fis);

            if ( uploadFile == false ) {
                throw new Exception("Error al subir el fichero");
            }else{
                 JOptionPane.showMessageDialog(null,"El archivo "+remote_filename+" fue cargado con exito", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
       
            }
            fis.close();
        } catch (Exception eFTPClient) {
                System. out. println(eFTPClient);
        }
    }
    
    public void descargarArchivo(){
        try {
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
 
             //APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "presupuesto.docx"; //Solo se coloca el nombre del archivo, no hace falta con la ruta completa
            File downloadFile1 = new File("C:\\Users\\Ricardo Fanghella\\Documents\\Proyecto\\presupuesto.docx"); //Donde voy a guardar el archivo
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = client.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("El archivo a sido descargado con exito");
            }else{
                System.out.println("Falla.");
            }

           
           
        }catch (IOException io){
            System.out.println(io);
        }
    }
}
