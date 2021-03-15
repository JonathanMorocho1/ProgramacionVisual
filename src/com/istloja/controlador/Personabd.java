/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.ConexionBD;
import com.istloja.modelo.Persona;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ANDRES
 */
public class Personabd {
    
    
    //Metodo que resive para registrar una persona
    public boolean CrearPersona(Persona persona){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
         String sql = "INSERT INTO `bdejercicio1`.`persona` (`idPersona`,`cedula`, `nombres`, `apellidos`,"
                 + " `direccion`, `correo`, `telefono`, `genero`) "
                + "VALUES ('" + String.valueOf(persona.getIdPersona()) + "','"
                + "" + persona.getCedula() + "', '"
                + "" + persona.getNombre() + "', '"
                + "" + persona.getApellidos() + "', '"
                + "" + persona.getDireccion() + "', '"
                + "" + persona.getCorreo() + "', '"
                + "" + persona.getTelefono()+"','" 
                + "" + persona.getGenero()+"')";
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
    public boolean EliminarPersona(Persona persona){
        boolean eliminar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        
        String sql = "DELETE from `bdejercicio1`.`persona` where (`idPersona` = '"+String.valueOf(persona.getIdPersona())+"');";
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
    public boolean EditarPersona(Persona persona){
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos
        Connection con = null;
        //INSERT INTO ejercici
        
        String sql = "UPDATE `bdejercicio1`.`persona` SET `cedula` = '" + persona.getCedula() + 
               "', `nombres` = '" + persona.getNombre() + "', `apellidos` = '" + persona.getApellidos() + 
                "', `direccion` = '" + persona.getDireccion() + "', `correo` = '" + persona.getCorreo() + 
                "', `telefono` = '" + persona.getTelefono() + "', `genero` = '" + persona.getGenero() + 
               "' WHERE (`idpersona` = '"+ persona.getIdPersona() + "');";
 
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
    public List<Persona> obtenerPersonas() {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        String sql = "SELECT * FROM bdejercicio1.persona;";
        List<Persona> listaPersonas = new ArrayList<Persona>();
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaPersonas;
    }//Fin traer datos
    
    
    public Persona buscarPersonasCedula(String Cedula) {
        Connection co = null;
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        Persona c = null;
        String sql = "Select * from persona WHERE cedula like "+Cedula+";";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
        return c;
    }
    
    public Persona buscarPersonasApellido(String apellido) {
        Connection co = null;
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        Persona c = null;
        String sql = "Select * from persona WHERE apellidos like '"+apellido+"';";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
        return c;
    }
    
    //BUSCAR POR NOMBRE 
    //METODOS PARA EL COMOBO BOX PARA PODER HACER LA BUSQUEDA MEDIANTE List
    
    public List<Persona> buscarPersonasCedulaCombo(String Cedula) {
        Connection co = null;
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        Persona c = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE cedula like "+Cedula+";";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
        return listaPersonas;
    }
    
    public List<Persona> buscarPersonasNombre(String nombre) {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE nombres like \"%"+nombre+"%\"";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaPersonas;
    }//Fin traer datos
    
    public List<Persona> buscarPersonasApellidoCombo(String apellido) {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE nombres like \"%"+apellido+"%\"";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaPersonas;
    }//Fin traer datos
    
    
    public List<Persona> buscarPersonasTelefono(String telefono) {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE nombres like \"%"+telefono+"%\"";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaPersonas;
    }//Fin traer datos
    
    public List<Persona> buscarPersonasCorreo(String correo) {
        //Conexion con base de datos
        Connection co = null;
        //Preparar los datos
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE nombres like \"%"+correo+"%\"";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Persona c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                listaPersonas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaPersonas;
    }//Fin traer datos
    
    public List<Persona> buscarPersonasCedulaLista(String Cedula) {
        Connection co = null;
        Statement stm = null;
        //Sentencia de JDBC para obtener valores de la base de datos.
        ResultSet rs = null;
        Persona c = null;
        List<Persona> personasEncontradas = new ArrayList<Persona>();
        String sql = "Select * from persona WHERE cedula like \"%"+Cedula+"%\";";
        try {
            co = new ConexionBD().ConexionBD();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c = new Persona();
                c.setIdPersona(rs.getInt(1));
                c.setCedula(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellidos(rs.getString(4));
                c.setDireccion(rs.getString(5));
                c.setCorreo(rs.getString(6));
                c.setTelefono(rs.getString(7));
                c.setGenero(rs.getString(8));
                personasEncontradas.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }
        return personasEncontradas;
    }
}
