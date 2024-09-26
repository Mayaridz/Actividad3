/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marllory Diaz
 */
public class Docente extends Persona{
    private int id;
    private String nit;
    private double salario;
    private String codigodocente;
    private String fecha_ingresolab;
    private String fecha_ingresore;
    Conexion cn;
    
    public Docente(){}
    public Docente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigodocente, Double salario, String fecha_ingresolab, String fecha_ingresore){
        super(nombres,apellidos,direccion,telefono,fecha_nacimiento);
        this.nit=nit;
        this.id=id;
        this.codigodocente= codigodocente;
        this.salario= salario;
        this.fecha_ingresolab= fecha_ingresolab;
        this.fecha_ingresore= fecha_ingresore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCodigodocente() {
        return codigodocente;
    }

    public void setCodigodocente(String codigodocente) {
        this.codigodocente = codigodocente;
    }

    public String getFecha_ingresolab() {
        return fecha_ingresolab;
    }

    public void setFecha_ingresolab(String fecha_ingresolab) {
        this.fecha_ingresolab = fecha_ingresolab;
    }

    public String getFecha_ingresore() {
        return fecha_ingresore;
    }

    public void setFecha_ingresore(String fecha_ingresore) {
        this.fecha_ingresore = fecha_ingresore;
    }
    
    @Override
    public DefaultTableModel Leer (){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
query="Select idpersona as id, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laboral, fecha_ingreso_registro from persona";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"id", "Nit", "Nombres", "Apellidos", "Direccion", "Telefono", "Fecha Nacimiento", "Codigo Docente", "Salario","Fecha_ingresolab", "Fecha_ingresore"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String[11];
        while(consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("nit");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            datos[7] = consulta.getString("codigo_docente");
            datos[8] = consulta.getString("salario");
            datos[9] = consulta.getString("fecha_ingreso_laboral");
            datos[10] = consulta.getString("fecha_ingreso_registro");
            tabla.addRow(datos);
        }
        cn.cerrar_conexion();
        }catch(SQLException ex){
          System.out.println("Error: " + ex.getMessage());
}
return tabla;
    }
    
    @Override
    public void Agregar()
    {
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO persona(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laboral, fecha_ingreso_registro) VALUES (?,?,?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getCodigodocente());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_ingresolab());
            parametro.setString(10, getFecha_ingresore());
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro Ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(HeadlessException |SQLException ex){
        System.out.println("Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void Actualizar(){
       try{
            PreparedStatement parametro;
            String query = "UPDATE persona set nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, codigo_docente=?, salario= ?, fecha_ingreso_laboral=?, fecha_ingreso_registro=? where idpersona= ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getCodigodocente());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_ingresolab());
            parametro.setString(10, getFecha_ingresore());
            parametro.setInt(11, getId());
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro Actualizaddo", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(HeadlessException |SQLException ex){
        System.out.println("Error: " + ex.getMessage());
        }
   }
    
    @Override
    public void Eliminar(){
   try{
            PreparedStatement parametro;
            String query = "DELETE FROM persona where idpersona = ?;";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId());
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(HeadlessException |SQLException ex){
        System.out.println("Error: " + ex.getMessage());
        }
   }
    
        }
