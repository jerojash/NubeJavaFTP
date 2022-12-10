
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

    public void subirArchivo(String file_dir, String local_filepath){
        String remote_working_dir_path = "C:\\Users\\Maria\\Documents\\carpetaftp";
//        String local_filepath = "C:\\Users\\Angel\\Desktop\\REPO_REDES_PROYECTO\\Carpeta_Conexion_FTP\\hola.txt";
        //nombre_archivo(file_dir);
         
        String remote_filename = "holaCopia.txt";

        try {
            FileInputStream fis = new FileInputStream(local_filepath);
            client.enterLocalPassiveMode(); // IMPORTANTE!!!! 
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory(remote_working_dir_path);
            boolean uploadFile = client.storeFile(remote_filename,fis);

            if ( uploadFile == false ) {
                throw new Exception("Error al subir el fichero");
            }else{
                System. out. println("Su archivo ha sido subido con exito al SERVIOR FTP\n");
            }
            fis.close();
        } catch (Exception eFTPClient) {
                System. out. println(eFTPClient);
        }
    }
    
    public void nombre_archivo(String file_dir){
        int cont = 0;
 
        for (int x = file_dir.length()-1; x>0; x--){            
            nombre = String.valueOf(file_dir.charAt(x)) + nombre;
            System. out. println(file_dir.charAt(x)+"\n");
        }
        System. out. println(nombre);
    }
}
