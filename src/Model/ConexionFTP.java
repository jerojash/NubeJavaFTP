
package Model;
    import java.io.FileInputStream;
    import java.io.PrintStream;
    import java.io.IOException;
    import java.io.FileInputStream; // Abrir y leer el fichero
import org.apache.commons.net.ftp.FTPFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

    import org.apache.commons.net.ftp.FTP;
    import org.apache.commons.net.ftp.FTPClient;    

public class ConexionFTP {
    private FTPClient client;   
    private String ftp; 
    private String user;
    private String password;


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

    public void subirArchivo(String file_dir,String file_name){
        String remote_working_dir_path = "C:\\Users\\Maria Gabriela\\Desktop\\Carpeta_conexion_FTP";
        
        try {
            FileInputStream fis = new FileInputStream(file_dir);
            client.enterLocalPassiveMode(); // IMPORTANTE!!!! 
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory(remote_working_dir_path);
            boolean uploadFile = client.storeFile(file_name,fis);

            if ( uploadFile == false ) {
                throw new Exception("Error al subir el fichero");
            }else{
                System. out. println("Su archivo ha sido subido con exito al SERVIOR FTP\n");
            }
            fis.close();
        } catch (Exception eFTPClient) {
            System.err.println("Error: " + eFTPClient.getMessage());
            // Gestionar el error, mostrar pantalla, reescalar excepcion... etc...
        }
    }
    
    public void traer_archivos_de_servidor_ftp(){
        try {
            FTPFile[] archivos = client.listFiles();
            System.out.println("\nArchivos en la raíz:");
            for (FTPFile archivo : archivos) {
                System.out.println(archivo.getName()); 
            }
        } catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
