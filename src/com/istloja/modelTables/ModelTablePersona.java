/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.Persona;
import com.istloja.vistas.GestionPersonaV1;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANDRES
 */
public class ModelTablePersona extends AbstractTableModel{
    //ARREGLO CON EL NOMBRE DE LAS COLUMNAS
    private String[] m_colNames = {"Cedula", "Nombres", "Apellidos", "Direccion", "Telefono", "Correo"};
    private List<Persona>personas; //Lista para mostrar personas
    private GestionPersonaV1 gContable;

    public ModelTablePersona(List<Persona> personas,  GestionPersonaV1 gContable) {
        this.gContable = gContable;
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
//Sirve para definir los nombres de las columnas
    @Override
    public String getColumnName(int column) {
        return m_colNames[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        gContable.clickPersona(personas.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.\
    }

    public List<Persona> getPersonas() {
        return personas;
    }
    
    public void setPersonas(List<Persona> personasNombre) {
        this.personas = personasNombre;
    }
    
    
    
    
}
