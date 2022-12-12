package Model;
import View.Login;
import SQL.SQLConnection;
import java.io.File;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {
    public static void main(String[] args) throws IOException {
        //ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "192.168.0.100", "javier", "redes123");
        ConexionFTP nuevo = new ConexionFTP(new FTPClient(), "192.168.0.103", "Angel", "redes");
        nuevo.conectar();
        nuevo.listar_archivos_en_servidor();
//        nuevo.crearCarpeta();
//        nuevo.desconectar();

//        SQLConnection con = new SQLConnection();
//        con.connected();

 
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