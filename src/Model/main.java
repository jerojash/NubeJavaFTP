
package Model;
import Model.view.JFileChooserMain;
import Model.view.Login;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {
    public static void main(String[] args) {
//        ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "192.168.0.103", "Angel", "redes");
//        nuevo.conectar();
//        nuevo.subirArchivo("sss");
//        nuevo.desconectar();
//        nuevo.traer_archivos_de_servidor_ftp();
        Login open = null;  
        open = new Login();
        open.setVisible(true);
    }
}