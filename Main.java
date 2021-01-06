/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabd;

/**
 *
 * @author ANDRES
 */
public class Main {
    public static void main(String[] args) {
        
        PruebaBD basedeDatos = new PruebaBD();
        if(basedeDatos.ConexionBD() != null ){
            System.out.println("Conexion exitosa...");
        }else{
            System.out.println("Error en la conexion...");
        }
        
    }
}
