/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.Inventarios;
import com.istloja.vistas.GestionPersonaV1;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANDRES
 */
public class ModelTableInventarios extends AbstractTableModel{
    private String[] m_colNames = {"CODIGO", "CANTIDAD PRODUCTOS",
        "DESCRIPCIÓN", "PRECIOS COMPRA(sinIva)",
        "PRECIOS Compra(conIva)", "Precio Mayorista",
        "Precio Cliente Fijo", "Precio Cliente Normal", "Fecha Caducidad"};
    private List<Inventarios>inventarios; //Lista para mostrar personas
    private GestionPersonaV1 gContable;
    
    public ModelTableInventarios(List<Inventarios> inventario,  GestionPersonaV1 gContable) {
        this.gContable = gContable;
        this.inventarios = inventario;
    }
    
    //Numero de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return inventarios.size();
    }
    //Numero de colunnas que va a mostrar
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inventarios inventario = this.inventarios.get(rowIndex);
        switch(columnIndex){
            case 0:
                return inventario.getCodigoProducto();
            case 1:
                return inventario.getCantidadProductos();
            case 2: 
                return inventario.getDescripcion();
            case 3:
                return inventario.getPreciosCompra_sinIva();
            case 4:
                return inventario.getPreciosCompra_conIva();
            case 5:
                return inventario.getPrecioMayorista();
            case 6:
                return inventario.getPrecioClienteFijo();
            case 7:
                return inventario.getPrecioClienteNormal();
            case 8:
                return inventario.getFechaVencimiento();
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
        gContable.clickInventarios(inventarios.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Inventarios> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventarios> inventarios) {
        this.inventarios = inventarios;
    }
}
