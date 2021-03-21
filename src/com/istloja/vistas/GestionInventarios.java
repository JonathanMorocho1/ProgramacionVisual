/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Inventarios;
import com.istloja.modelo.Persona;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ANDRES
 */
public class GestionInventarios {
    private JTextField txtCodigoProducto;
    private JTextField txtCantidadProductos;
    private JTextField txtDescripcion;
    private JTextField txtPreciosCompraSinIva;
    private JTextField txtPreciosCompraConIva;
    private JTextField txtPrecioMayorista;
    private JTextField txtPrecioClienteFijo;
    private JTextField txtPrecioClienteNormal;
    private JDateChooser JDateFechaVencimientoInventarios;
    private JFrame frameGestionContable;

    public GestionInventarios(JTextField txtCodigoProducto, JTextField txtCantidadProductos, JTextField txtDescripcion, JTextField txtPreciosCompraSinIva, JTextField txtPreciosCompraConIva, JTextField txtPrecioMayorista, JTextField txtPrecioClienteFijo, JTextField txtPrecioClienteNormal, JDateChooser JDateFechaVencimientoInventarios, JFrame frameGestionContable) {
        this.txtCodigoProducto = txtCodigoProducto;
        this.txtCantidadProductos = txtCantidadProductos;
        this.txtDescripcion = txtDescripcion;
        this.txtPreciosCompraSinIva = txtPreciosCompraSinIva;
        this.txtPreciosCompraConIva = txtPreciosCompraConIva;
        this.txtPrecioMayorista = txtPrecioMayorista;
        this.txtPrecioClienteFijo = txtPrecioClienteFijo;
        this.txtPrecioClienteNormal = txtPrecioClienteNormal;
        this.JDateFechaVencimientoInventarios = JDateFechaVencimientoInventarios;
        this.frameGestionContable = frameGestionContable;
    }

    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    public JTextField getTxtCantidadProductos() {
        return txtCantidadProductos;
    }

    public void setTxtCantidadProductos(JTextField txtCantidadProductos) {
        this.txtCantidadProductos = txtCantidadProductos;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(JTextField txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public JTextField getTxtPreciosCompraSinIva() {
        return txtPreciosCompraSinIva;
    }

    public void setTxtPreciosCompraSinIva(JTextField txtPreciosCompraSinIva) {
        this.txtPreciosCompraSinIva = txtPreciosCompraSinIva;
    }

    public JTextField getTxtPreciosCompraConIva() {
        return txtPreciosCompraConIva;
    }

    public void setTxtPreciosCompraConIva(JTextField txtPreciosCompraConIva) {
        this.txtPreciosCompraConIva = txtPreciosCompraConIva;
    }

    public JTextField getTxtPrecioMayorista() {
        return txtPrecioMayorista;
    }

    public void setTxtPrecioMayorista(JTextField txtPrecioMayorista) {
        this.txtPrecioMayorista = txtPrecioMayorista;
    }

    public JTextField getTxtPrecioClienteFijo() {
        return txtPrecioClienteFijo;
    }

    public void setTxtPrecioClienteFijo(JTextField txtPrecioClienteFijo) {
        this.txtPrecioClienteFijo = txtPrecioClienteFijo;
    }

    public JTextField getTxtPrecioClienteNormal() {
        return txtPrecioClienteNormal;
    }

    public void setTxtPrecioClienteNormal(JTextField txtPrecioClienteNormal) {
        this.txtPrecioClienteNormal = txtPrecioClienteNormal;
    }

    public JDateChooser getJDateFechaVencimientoInventarios() {
        return JDateFechaVencimientoInventarios;
    }

    public void setJDateFechaVencimientoInventarios(JDateChooser JDateFechaVencimientoInventarios) {
        this.JDateFechaVencimientoInventarios = JDateFechaVencimientoInventarios;
    }
    
    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

    
    public Inventarios guardarEditarInventario(boolean isEditarInventarios){
        if (txtCodigoProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo cedula no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCodigoProducto.requestFocus();
            return null;
        }
        if (txtCantidadProductos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo cantidad no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCantidadProductos.requestFocus();
            return null;
        }
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo descripcion no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtDescripcion.requestFocus();
            return null;
        }
        if (txtPreciosCompraSinIva.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable,
                    "El campo precioSin no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPreciosCompraSinIva.requestFocus();
            return null;
        }
        if (txtPreciosCompraConIva.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable,
                    "El campo precioCon no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPreciosCompraConIva.requestFocus();
            return null;
        }
        if (txtPrecioMayorista.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable,
                    "El campo precioMayorista no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrecioMayorista.requestFocus();
            return null;
        }
        if (txtPrecioClienteFijo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable,
                    "El campo clientefijo no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrecioClienteFijo.requestFocus();
            return null;
        }
        if (txtPrecioClienteNormal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable,
                    "El campo clientenormal no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrecioClienteNormal.requestFocus();
            return null;
        }
        
        Inventarios inventarios = new Inventarios();
        inventarios.setCodigoProducto(txtCodigoProducto.getText());
        inventarios.setCantidadProductos(txtCantidadProductos.getText());
        inventarios.setDescripcion(txtDescripcion.getText());
        inventarios.setPreciosCompra_sinIva(txtPreciosCompraSinIva.getText());
        inventarios.setPreciosCompra_conIva(txtPreciosCompraConIva.getText());
        inventarios.setPrecioMayorista(txtPrecioMayorista.getText());
        inventarios.setPrecioClienteFijo(txtPrecioClienteFijo.getText());
        inventarios.setPrecioClienteNormal(txtPrecioClienteNormal.getText());
        if(isEditarInventarios){
            inventarios.setFechaActualizacion(new Date());
        }else {
            inventarios.setFechaRegistro(new Date());
        }
        inventarios.setFechaVencimiento(JDateFechaVencimientoInventarios.getDate());
        System.out.println(inventarios.toString());
        return inventarios;
    }
}
