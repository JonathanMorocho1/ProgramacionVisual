/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.Proveedores;
import com.istloja.modelo.Persona;
import com.istloja.vistas.GestionPersonaV1;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANDRES
 */
public class ModeloTableProveedores extends AbstractTableModel{
    //ARREGLO CON EL NOMBRE DE LAS COLUMNAS
    private String[] m_colNames = {"RUC", "Razon Social", "Tipo de Actividad",
        "Nombre Representante", "Apellido Representante", "Telefono", "Correo",
        "Direccion", "Fecha Vencimiento"};
    private List<Proveedores>proveedores; //Lista para mostrar proveedores
    private GestionPersonaV1 gContable;

    public ModeloTableProveedores(List<Proveedores> proveedores,  GestionPersonaV1 gContable) {
        this.gContable = gContable;
        this.proveedores = proveedores;
    }
    //Numero de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return proveedores.size();
    }
//Numero de colunnas que va a mostrar
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }
//Metodo donde se obtienen las personas
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedores proveedor = proveedores.get(rowIndex);
        switch(columnIndex){
            case 0:
                return proveedor.getRuc();
            case 1:
                return proveedor.getRazonSocial();
            case 2:
                return proveedor.getTipoActividad();
            case 3:
                return proveedor.getNombreRepresentanteLegal();
            case 4:
                return proveedor.getApellidoRepresentanteLegal();
            case 5:
                return proveedor.getTelefonoProveedor();
            case 6:
                return proveedor.getCorreoProveedores();
            case 7:
                return proveedor.getDireccionProveedores();
            case 8:
                return proveedor.getFechaVencimiento();
        }
        return new String();
    }
//ESTE METODO SIRVE PARA INGRESAR DEFINIR LOS NOMBRES DE LAS COLUMNAS
    @Override
    public String getColumnName(int column) {
        return m_colNames[column];
    }
//PARA ELEGIR O SELECCIONAR UNA FILA O COLUMNA
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        gContable.clickProveedores(proveedores.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Proveedores> getProveedores() {
        return proveedores;
    }

    public void  setProveedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }
}
