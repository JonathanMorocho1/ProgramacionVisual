/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.Persona;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANDRES
 */
public class ModelTablePersona extends AbstractTableModel{
    //ARREGLO CON EL NOMBRE DE LAS COLUMNAS
    public String[] m_colNames = {"Cedula", "Nombres", "Apellidos", "Direccion", "Telefono", "Correo"};
    public List<Persona>personas; //Lista para mostrar personas

    public ModelTablePersona(List<Persona> personas) {
        this.personas = personas;
    }
    //Numero de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return personas.size();
    }
//Numero de colunnas que va a mostrar
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }
//Metodo donde se obtienen las personas
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = personas.get(rowIndex);
        switch(columnIndex){
            case 0:
                return persona.getCedula();
            case 1:
                return persona.getNombre();
            case 2:
                return persona.getApellidos();
            case 3:
                return persona.getDireccion();
            case 4:
                return persona.getTelefono();
            case 5:
                return persona.getCorreo(); 
        }
        return new String();
    }
//ESTE METODO SIRVE PARA INGRESAR DEFINIR LOS NOMBRES DE LAS COLUMNAS
    @Override
    public String getColumnName(int column) {
        return m_colNames[column];
    }
    
    
}
