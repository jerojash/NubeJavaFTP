/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConexionFTP;
import javax.swing.JFrame;
import org.apache.commons.net.ftp.FTPClient;

public class controllerCrearConexionFTP {
    
    
    public boolean conectar(String server, String user, String pass){
        ConexionFTP con = new ConexionFTP(new FTPClient(), server, user, pass );
         return con.conectar();
    }
    
    public void cambiarInterfaz(JFrame actual, JFrame nueva, int ancho, int largo){
        nueva.setResizable(false); 
        nueva.setSize(ancho, largo);   
        nueva.setVisible(true);   
        nueva.setLocationRelativeTo(null);
        actual.dispose();
    }
}
