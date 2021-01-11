/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.PruebaBD;
import com.istloja.modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANDRES
 */
public class Personabd {
    
    
    //Metodo que resive una persona
    public boolean CrearPersona(Persona persona){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
         String sql = "INSERT INTO `bdejercicio1`.`persona` (`idPersona`,`cedula`, `nombres`, `apellidos`, `direccion`, `correo`, `telefono`) "
                + "VALUES ('" + String.valueOf(persona.getIdPersona()) + "','"
                + "" + persona.getCedula() + "', '"
                + "" + persona.getNombre() + "', '"
                + "" + persona.getApellidos() + "', '"
                + "" + persona.getDireccion() + "', '"
                + "" + persona.getCorreo() + "', '"
                + "" + persona.getTelefono()+"')";
        try{
            PruebaBD conexion = new PruebaBD();
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
    }
    
    //Metodo que resive una persona
    public boolean EliminarPersona(Persona persona){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        
        String sql = "DELETE from persona where idPersona = "+persona.getIdPersona();
        try{
            PruebaBD conexion = new PruebaBD();
            con = conexion.ConexionBD();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        
        }catch(SQLException e){
            System.out.println("Error Clase elimar: " +e.getMessage());
        }
    
        return registrar;
    }
    
    //Metodo que resive una persona
    public boolean EditarPersona(Persona persona){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        String sql = ("UPDATE persona SET cedula ='12121212', nombres = 'ndasdsha', apellidos = 'sdjahsdsa',"
                + " direccion = 'abdashdbash', correo = 'fjadbsd@', telefono = '82727272' where idPersona = 2");
        
        try{
            PruebaBD conexion = new PruebaBD();
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
    }
    
}
