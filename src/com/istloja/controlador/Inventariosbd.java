/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.ConexionBD;
import com.istloja.modelo.Inventarios;
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
public class Inventariosbd {
    //Metodo que resive para registrar una persona
    public boolean CrearInventario(Inventarios inventarios){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
         String sql = "INSERT INTO `bdejercicio1`.`inventarios` (`codigo_pro`, `descripcion`, `precios_compra`, `precios_venta`, `can_productos`) "
                + "VALUES ('" +inventarios.getCodigoProducto()+ "','"
                + "" + inventarios.getDescripcion() + "', '"
                + "" + inventarios.getPreciosCompra() + "', '"
                + "" + inventarios.getPreciosVenta() + "', '"
                + "" + inventarios.getCantidadProductos()+"')";
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
    public boolean EliminarInventarios(Inventarios inventarios){
        boolean eliminar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        
        String sql = "DELETE from `bdejercicio1`.`inventarios` where (`idPersona` = '"+String.valueOf(inventarios.getIdInventario())+"');";
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
    public boolean EditarInventarios(Inventarios inventarios){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
       String sql = "UPDATE `bdejercicio1`.`inventarios` SET `cedula` = '" + inventarios.getCodigoProducto() + 
               "', `nombres` = '" + inventarios.getDescripcion() + "', `apellidos` = '" + inventarios.getPreciosCompra() + 
                "', `direccion` = '" + inventarios.getPreciosVenta() + "', `correo` = '" + inventarios.getCantidadProductos() + 
               "' WHERE (`idpersona` = '"+ inventarios.getIdInventario() + "');";
 
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
    public List<Inventarios> obtenerInventarios() {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        String sql = "SELECT * FROM bdejercicio1.inventarios;";
        List<Inventarios> listaInventarios = new ArrayList<Inventarios>();
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Inventarios c = new Inventarios();
                c.setCodigoProducto(rs.getString(1));
                c.setDescripcion(rs.getString(2));
                c.setPreciosCompra(rs.getString(3));
                c.setPreciosVenta(rs.getString(4));
                c.setCantidadProductos(rs.getString(5));
                listaInventarios.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaInventarios;
    }
}