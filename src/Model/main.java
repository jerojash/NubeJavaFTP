
package Model;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {
    public static void main(String[] args) {
//        ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "192.168.0.103", "Angel", "redes");
//        nuevo.conectar();
//        nuevo.subirArchivo("sss");
//        nuevo.desconectar();

        ManejoString mn = new ManejoString();
        
        System.out.println(mn.getFileName("C:\\Users\\Maria Gabriela\\Desktop\\Carpeta_conexion_FTP"));
    }
}
