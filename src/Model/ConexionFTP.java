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

import org.apache.commons.net.ftp.FTPReply;

    import org.apache.commons.net.ftp.FTP;
    import org.apache.commons.net.ftp.FTPClient;    

public class ConexionFTP {
    static FTPClient client;   
    static String ftp; 
    static String user;
    static String password;
    static String nombre = "";

    public ConexionFTP(FTPClient client, String ftp, String user, String password) {
        ConexionFTP.client = client;
        ConexionFTP.ftp = ftp;
        ConexionFTP.user = user;
        ConexionFTP.password = password;
    }

    public ConexionFTP(){
        
    }

    public boolean conectar(){
        try {
            // Conactando al servidor
            client.connect(ftp);
            System.out.println(client.getReplyString());

            // Logueado un usuario (true = pudo conectarse, false = no pudo
            // conectarse)
            boolean login = client.login(user, password);
            if (login){
                JOptionPane.showMessageDialog(null,"Conexión exitosa. Mensaje del servidor: \n"+client.getReplyString(), "Mensaje",JOptionPane.INFORMATION_MESSAGE);
                return true;
            //System.out.println(client.getReplyString());
            }else{
                JOptionPane.showMessageDialog(null,"Credenciales incorrectas.", "Error",JOptionPane.ERROR_MESSAGE);
       
            }
        } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Error al intentar conectar con el servidor especificado.", "Error",JOptionPane.ERROR_MESSAGE);
       
        }
        return false;
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
    
    public void crearCarpeta(){
        String dirToCreate = "/upload123";
            boolean success;
        try {
            success = client.makeDirectory(dirToCreate);
        
            //showServerReply(client);
            if (success) {
                System.out.println("Successfully created directory: " + dirToCreate);
            } else {
                System.out.println("Failed to create directory. See server's reply.");
            }
            } catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Esto me funcionara para poder descargar lo que este en el servidor
    //nuevo.descargarArchivo("C:\\Users\\Angel\\Desktop\\REPO_REDES_PROYECTO\\Carpeta_Conexion_FTP\\","Simio3DStatus.log");
    //nuevo.listar_archivos_en_servidor();
    
    public static void descargarArchivo(String carpeta_en_pc_que_descarga, String fileName_in_ftp) throws FileNotFoundException, IOException{
        String nombre_completo = carpeta_en_pc_que_descarga + fileName_in_ftp;
        System.out.println(nombre_completo);
        try ( //fos = new FileOutputStream(localFile);
                
            BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(nombre_completo))) {
            if (client.retrieveFile(fileName_in_ftp, buffOut)) {
                System.out.println("El archivo ha sido descargado con exito!!");
            } else {
                System.out.println("Error en la descarga.");
            }
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
    }
}
    
    public void listar_archivos_en_servidor(){
        try {
                client.enterLocalPassiveMode();
                client.setFileType(FTP.BINARY_FILE_TYPE);
 
                FTPFile[] archivos = client.listFiles();
                System.out.println("\nArchivos en la raíz:");
                for (FTPFile archivo : archivos) {
                    System.out.println(archivo.getName());
                }
                archivos = client.listFiles("/upload");
                System.out.println("\nArchivos del directorio `upload`:");
                for (FTPFile archivo : archivos) {
                    System.out.println(archivo.getName());
                }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

//    public void mostrar_archivos_carpeta(){
//        String sCarpAct = System.getProperty("user.dir");
//        File carpeta = new File(sCarpAct);
//        
//        String[] listado = carpeta.list();
//        if (listado == null || listado.length == 0) {
//            System.out.println("No hay elementos dentro de la carpeta actual");
//            return;
//        }
//        else {
//            for (int i=0; i< listado.length; i++) {
//                System.out.println(listado[i]);
//            }
//        }
//    }
}