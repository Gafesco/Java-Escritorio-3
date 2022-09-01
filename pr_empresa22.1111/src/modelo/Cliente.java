/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author HP
 */
public class Cliente extends Persona {
    private int id;
    private String nit;
    Conexion cn;
    public TableModel leer;

    public Cliente() {}

    public Cliente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fn) {
        super(nombres, apellidos, direccion, telefono, fn);
        this.id = id;
        this.nit = nit;
        
    }
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel ();
        
        try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "Select id_cliente as id, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento from clientes;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String[7];
        
        while (consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("nit");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);

        }
        cn.cerrar_conexion();
        
        } catch (SQLException ex){
                  cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
            
        }
        return tabla;
        
    }

        
    
    @Override
    public void agregar (){
try { 
    PreparedStatement parametro;
    String query = "INSERT INTO clientes (nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getNit());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getFn());
    
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    }
    
    @Override
    public void modificar(){
        
        
try { 
    PreparedStatement parametro;
    String query = "update clientes set nit=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=? where id_cliente =?;";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getNit());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getFn());
    parametro.setInt(7, getId());
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registros Modificados","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    
    }
    
    @Override
    public void eliminar(){
            
        
        
try { 
    PreparedStatement parametro;
    String query = "delete from clientes  where id_cliente =?;";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

    parametro.setInt(1, getId());
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registros Eliminados","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    
    }
            
}
