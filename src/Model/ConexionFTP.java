
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
//        // Creando nuestro objeto ClienteFTP
//        client = new FTPClient();
//        // Datos para conectar al servidor FTP
//        ftp = "200.2.14.89"; // También puede ir la IP
//        user = "javier";
//        password = "redes123";
 
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
    
    public void subirArchivo(){
        
    String remote_working_dir_path = "C:\\1\\vanessa\\javierFTP";
    String local_filepath = "C:\\Users\\spereira.17\\Documents\\Javier\\Proyectos DTI\\Evidencias RECORD POR POBLACION.xls";
    String remote_filename = "Evidencias RECORD POR POBLACION.xls";

    
    try {
        FileInputStream fis = new FileInputStream(local_filepath);
        client.enterLocalPassiveMode(); // IMPORTANTE!!!! 
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.changeWorkingDirectory(remote_working_dir_path);
        boolean uploadFile = client.storeFile(remote_filename,fis);

        if ( uploadFile == false ) {
            throw new Exception("Error al subir el fichero");
        }
        fis.close();
    } catch (Exception eFTPClient) {
        // Gestionar el error, mostrar pantalla, reescalar excepcion... etc...
    } 
    }
    

}
