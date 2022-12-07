
package ftpjava;
    import java.io.PrintStream;
    import java.io.IOException;

    import org.apache.commons.net.ftp.FTPClient;

public class main {

  
    public static void main(String[] args) {
        // Creando nuestro objeto ClienteFTP
        FTPClient client = new FTPClient();
        // Datos para conectar al servidor FTP
        String ftp = "200.2.14.89"; // También puede ir la IP
        String user = "javier";
        String password = "redes123";
 
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
            // Cerrando sesión
            client.logout();
            System.out.println(client.getReplyString());
 
            // Desconectandose con el servidor
            client.disconnect();
 
        } catch (IOException ioe) {
                System. out. println("No conectado por error\n");
        }
    }
    
}
