/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.Conexion;

/**
 *
 * @author Marllory Diaz
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion cn = new Conexion();
        cn.abrir_conexion();
       
        frm_docente frm = new frm_docente();
       frm.show();
       
    
    }  
}


