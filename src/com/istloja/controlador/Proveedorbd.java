/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.ConexionBD;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public class Proveedorbd {
    //Metodo que resive para registrar una persona
    public boolean RegistrarProveedor(Proveedores proveedor){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
         /*String sql = "INSERT INTO `bdejercicio1`.`proveedor` (`idPersona`,`cedula`, `nombres`, `apellidos`, `direccion`, `correo`, `telefono`) "
                + "VALUES ('" + String.valueOf(persona.getIdPersona()) + "','"
                + "" + persona.getCedula() + "', '"
                + "" + persona.getNombre() + "', '"
                + "" + persona.getApellidos() + "', '"
                + "" + persona.getDireccion() + "', '"
                + "" + persona.getCorreo() + "', '"
                + "" + persona.getTelefono()+"')";*/
         
        String sql = "INSERT INTO `bdejercicio1`.`proveedores` (`ruc`, `razon_social`, `tipo_actividad`, `nombre_representante_legal`, `apellido_representante_legal`, `telefono`, `correo`) "
                + "VALUES ('"+proveedor.getRuc()+"',"
                + " '"+proveedor.getRazonSocial()+"',"
                + " '"+proveedor.getTipoActividad()+"',"
                + " '"+proveedor.getNombreRepresentanteLegal()+"',"
                + " '"+proveedor.getApellidoRepresentanteLegal()+"',"
                + " '"+proveedor.getTelefonoProveedor()+"',"
                + " '"+proveedor.getCorreoProveedores()+"');";
        try{
            ConexionBD conexion = new ConexionBD();
            con = conexion.ConexionBD();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
            //return  true;
        
        }catch(SQLException e){
            System.out.println("Error Clase crear: " +e.getMessage());
        }
    
        return registrar;
    }//Fin Registrar
    
    //Metodo que resive para eliminar una persona
    public boolean EliminarProveedores(Proveedores proveedor){
        boolean eliminar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        
        String sql = "DELETE FROM `bdejercicio1`.`proveedores` WHERE (`id_proveedores` = '"+proveedor.getIdProveedores()+"');";
        try{
            ConexionBD conexion = new ConexionBD();
            con = conexion.ConexionBD();
            stm = con.createStatement();
            stm.execute(sql);
            //eliminar = true;
            //stm.close();
            //con.close();
        
        }catch(SQLException e){
            System.out.println("Error Clase elimar: " +e.getMessage());
        }
    
        return eliminar;
    }//Fin eliminar
    
    //Metodo que resive para editar una persona
    public boolean EditarProveedores(Proveedores proveedor){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
       /*String sql = "UPDATE `bdejercicio1`.`persona` SET `cedula` = '" + persona.getCedula() + 
               "', `nombres` = '" + persona.getNombre() + "', `apellidos` = '" + persona.getApellidos() + 
                "', `direccion` = '" + persona.getDireccion() + "', `correo` = '" + persona.getCorreo() + 
                "', `telefono` = '" + persona.getTelefono() + "' WHERE (`idpersona` = '"+ persona.getIdPersona() + "');";*/
 
       String sql = "UPDATE `bdejercicio1`.`proveedores` SET `ruc` = '"+proveedor.getRuc()+"',"
               + " `razon_social` = '"+proveedor.getRazonSocial()+"', `tipo_actividad` = '"+proveedor.getTipoActividad()+"',"
               + " `nombre_representante_legal` = '"+proveedor.getNombreRepresentanteLegal()+"', "
               + "`apellido_representante_legal` = '"+proveedor.getApellidoRepresentanteLegal()+"', "
               + "`telefono` = '"+proveedor.getTelefonoProveedor()+"', `correo` = '"+proveedor.getCorreoProveedores()+"'"
               + " WHERE (`id_proveedores` = '"+proveedor.getIdProveedores()+"');";
        try{
            ConexionBD conexion = new ConexionBD();
            con = conexion.ConexionBD();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
            //return  true;
        
        }catch(SQLException e){
            System.out.println("Error Clase editar: " +e.getMessage());
        }
    
        return registrar;
    }//Fin editar
      
    
    //Metodo que sirve para obtener todos los registros de la base de datos
    public List<Proveedores> obtenerProveedores() {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        String sql = "SELECT * FROM bdejercicio1.proveedores;";
        List<Proveedores> listaProveedores = new ArrayList<Proveedores>();
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setIdProveedores(rs.getInt(1));
                c.setRuc(rs.getString(2));
                c.setRazonSocial(rs.getString(3));
                c.setTipoActividad(rs.getString(4));
                c.setNombreRepresentanteLegal(rs.getString(5));
                c.setApellidoRepresentanteLegal(rs.getString(6));
                c.setTelefonoProveedor(rs.getString(7));
                c.setCorreoProveedores(rs.getString(8));
                listaProveedores.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaProveedores;
    }
}
