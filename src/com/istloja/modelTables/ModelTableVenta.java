/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.ProductoVenta;
import com.istloja.vistas.GestionPersonaV1;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANDRES
 */
public class ModelTableVenta extends AbstractTableModel{
    
    private String[] m_colNames = {"CANTIDAD", "DESCRIPCIÓN", "SUBTOTAL", "TOTAL"};
    private List<ProductoVenta> productoVenta; //Lista para mostrar personas
    private GestionPersonaV1 gContable;

    public ModelTableVenta (List<ProductoVenta> productoVenta,  GestionPersonaV1 gContable) {
        this.gContable = gContable;
        this.productoVenta = productoVenta;
    }
    
    @Override
    public int getRowCount() {
        return productoVenta.size();
    }

    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductoVenta persona = productoVenta.get(rowIndex);
        switch(columnIndex){
            case 0:
                return persona.getCantidad();
            case 1:
                return persona.getDescripcion();
            case 2: 
                return persona.getSubTotal();
            case 3:
                return persona.getTotal();
        }
        return new String();
    }
   
    @Override
    public String getColumnName(int column) {
        return m_colNames[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        gContable.clickProductoVender(productoVenta.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.\
    }

    public List<ProductoVenta> getProductoVentas() {
        return productoVenta;
    }
    
    public void setProductoVentas(List<ProductoVenta> personasNombre) {
        this.productoVenta = personasNombre;
    }
}
