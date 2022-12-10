
package Model;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {
    public static void main(String[] args) {
        ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "192.168.0.100", "javier", "redes123");
        nuevo.conectar();
        //nuevo.subirArchivo();
        nuevo.desconectar();

        }
}
