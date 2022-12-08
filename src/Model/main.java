
package Model;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {

  
    public static void main(String[] args) {
        ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "200.2.14.89", "javier", "redes123");
        
        nuevo.conectar();
        
        nuevo.subirArchivo();
        
        
        nuevo.desconectar();
    }
    
}
