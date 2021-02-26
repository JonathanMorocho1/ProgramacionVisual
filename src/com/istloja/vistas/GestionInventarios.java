/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Inventarios;
import com.istloja.modelo.Persona;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ANDRES
 */
public class GestionInventarios {
    private JTextField txtCodigoProducto;
    private JTextField txtDescripcion;
    private JTextField txtPreciosCompra;
    private JTextField txtPreciosVenta;
    private JTextField txtCantidadProductos;
    private JFrame frameGestionContable;

    public GestionInventarios(JTextField txtCodigoProducto, JTextField txtDescripcion, JTextField txtPreciosCompra, JTextField txtPreciosVenta, JTextField txtCantidadProductos, JFrame frameGestionContable) {
        this.txtCodigoProducto = txtCodigoProducto;
        this.txtDescripcion = txtDescripcion;
        this.txtPreciosCompra = txtPreciosCompra;
        this.txtPreciosVenta = txtPreciosVenta;
        this.txtCantidadProductos = txtCantidadProductos;
        this.frameGestionContable = frameGestionContable;
    }
    
    public GestionInventarios(){
    
    }  

    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(JTextField txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public JTextField getTxtPreciosCompra() {
        return txtPreciosCompra;
    }

    public void setTxtPreciosCompra(JTextField txtPreciosCompra) {
        this.txtPreciosCompra = txtPreciosCompra;
    }

    public JTextField getTxtPreciosVenta() {
        return txtPreciosVenta;
    }

    public void setTxtPreciosVenta(JTextField txtPreciosVenta) {
        this.txtPreciosVenta = txtPreciosVenta;
    }

    public JTextField getTxtCantidadProductos() {
        return txtCantidadProductos;
    }

    public void setTxtCantidadProductos(JTextField txtCantidadProductos) {
        this.txtCantidadProductos = txtCantidadProductos;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }
   
    
    public Inventarios guardarEditarInventario(){
        if (txtCodigoProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo cedula no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCodigoProducto.requestFocus();
            return null;
        }
        
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo nombre no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtDescripcion.requestFocus();
            return null;
        }
        if (txtPreciosCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo apellido no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPreciosCompra.requestFocus();
            return null;
        }
        if (txtPreciosVenta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo direccion no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPreciosVenta.requestFocus();
            return null;
        }
        if (txtCantidadProductos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo correo no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCantidadProductos.requestFocus();
            return null;
        }
        
        Inventarios inventarios = new Inventarios();
        inventarios.setCodigoProducto(txtCodigoProducto.getText());
        inventarios.setDescripcion(txtDescripcion.getText());
        inventarios.setPreciosCompra(txtPreciosCompra.getText());
        inventarios.setPreciosVenta(txtPreciosVenta.getText());
        inventarios.setCantidadProductos(txtCantidadProductos.getText());
        System.out.println(inventarios.toString());
        return inventarios;
    }
}
