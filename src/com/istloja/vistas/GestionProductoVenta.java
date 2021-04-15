/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.ProductoVenta;
import javax.swing.JTextField;

/**
 *
 * @author ANDRES
 */
public class GestionProductoVenta {
    private JTextField txtNumeroNotaVenta;
    private JTextField txtCedulaClienteVenta;
    private JTextField txtNombreClienteVenta;
    private JTextField txtTelefonoClienteVenta;
    private JTextField txtIDProductoVenta;
    private JTextField txtDireccionClienteVenta;
    private JTextField txtCantidadProductosVenta;

    public GestionProductoVenta(JTextField txtNumeroNotaVenta, JTextField txtCedulaClienteVenta, JTextField txtNombreClienteVenta, JTextField txtTelefonoClienteVenta, JTextField txtIDProductoVenta, JTextField txtDireccionClienteVenta, JTextField txtCantidadProductosVenta) {
        this.txtNumeroNotaVenta = txtNumeroNotaVenta;
        this.txtCedulaClienteVenta = txtCedulaClienteVenta;
        this.txtNombreClienteVenta = txtNombreClienteVenta;
        this.txtTelefonoClienteVenta = txtTelefonoClienteVenta;
        this.txtIDProductoVenta = txtIDProductoVenta;
        this.txtDireccionClienteVenta = txtDireccionClienteVenta;
        this.txtCantidadProductosVenta = txtCantidadProductosVenta;
    }

    public JTextField getTxtNumeroNotaVenta() {
        return txtNumeroNotaVenta;
    }

    public void setTxtNumeroNotaVenta(JTextField txtNumeroNotaVenta) {
        this.txtNumeroNotaVenta = txtNumeroNotaVenta;
    }

    public JTextField getTxtCedulaClienteVenta() {
        return txtCedulaClienteVenta;
    }

    public void setTxtCedulaClienteVenta(JTextField txtCedulaClienteVenta) {
        this.txtCedulaClienteVenta = txtCedulaClienteVenta;
    }

    public JTextField getTxtNombreClienteVenta() {
        return txtNombreClienteVenta;
    }

    public void setTxtNombreClienteVenta(JTextField txtNombreClienteVenta) {
        this.txtNombreClienteVenta = txtNombreClienteVenta;
    }

    public JTextField getTxtTelefonoClienteVenta() {
        return txtTelefonoClienteVenta;
    }

    public void setTxtTelefonoClienteVenta(JTextField txtTelefonoClienteVenta) {
        this.txtTelefonoClienteVenta = txtTelefonoClienteVenta;
    }

    public JTextField getTxtIDProductoVenta() {
        return txtIDProductoVenta;
    }

    public void setTxtIDProductoVenta(JTextField txtIDProductoVenta) {
        this.txtIDProductoVenta = txtIDProductoVenta;
    }

    public JTextField getTxtDireccionClienteVenta() {
        return txtDireccionClienteVenta;
    }

    public void setTxtDireccionClienteVenta(JTextField txtDireccionClienteVenta) {
        this.txtDireccionClienteVenta = txtDireccionClienteVenta;
    }

    public JTextField getTxtCantidadProductosVenta() {
        return txtCantidadProductosVenta;
    }

    public void setTxtCantidadProductosVenta(JTextField txtCantidadProductosVenta) {
        this.txtCantidadProductosVenta = txtCantidadProductosVenta;
    }
    
    public void guardarEditarProductoVenta(){
    ProductoVenta productoVenta = new ProductoVenta();
        //productoVenta.setCantidad(txtCantidadProductosVenta.getText());
        
    }    
}
