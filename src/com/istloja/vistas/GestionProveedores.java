/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Proveedores;
import com.istloja.modelo.Utilidades;
import com.toedter.calendar.JDateChooser;
//import java.sql.Date;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/** *

 * @author ANDRES
 */
public class GestionProveedores {
    private JTextField txtRuc;
    private JTextField txtRazonSocial;
    private JTextField txtTipoActividad;
    private JTextField txtNombreRepresentanteLegal;
    private JTextField txtApellidoRepresentanteLegal;
    private JTextField txtTelefonoProveedores;
    private JTextField txtCorreoProveedores;
    private JTextField txtDireccionProveedores;
    private Utilidades utilidades;
    private JDateChooser JDateFechaVencimientoProveedores;
    private JFrame frameGestionContable;

    public GestionProveedores(JTextField txtRuc, JTextField txtRazonSocial, JTextField txtTipoActividad, JTextField txtNombreRepresentanteLegal, JTextField txtApellidoRepresentanteLegal, JTextField txtTelefonoProveedores, JTextField txtCorreoProveedores, JTextField txtDireccionProveedores, Utilidades utilidades, JDateChooser JDateFechaVencimientoProveedores, JFrame frameGestionContable) {
        this.txtRuc = txtRuc;
        this.txtRazonSocial = txtRazonSocial;
        this.txtTipoActividad = txtTipoActividad;
        this.txtNombreRepresentanteLegal = txtNombreRepresentanteLegal;
        this.txtApellidoRepresentanteLegal = txtApellidoRepresentanteLegal;
        this.txtTelefonoProveedores = txtTelefonoProveedores;
        this.txtCorreoProveedores = txtCorreoProveedores;
        this.txtDireccionProveedores = txtDireccionProveedores;
        this.utilidades = utilidades;
        this.JDateFechaVencimientoProveedores = JDateFechaVencimientoProveedores;
        this.frameGestionContable = frameGestionContable;
    }

     

    

    

    public JTextField getTxtRuc() {
        return txtRuc;
    }

    public void setTxtRuc(JTextField txtRuc) {
        this.txtRuc = txtRuc;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public void setTxtRazonSocial(JTextField txtRazonSocial) {
        this.txtRazonSocial = txtRazonSocial;
    }

    public JTextField getTxtTipoActividad() {
        return txtTipoActividad;
    }

    public void setTxtTipoActividad(JTextField txtTipoActividad) {
        this.txtTipoActividad = txtTipoActividad;
    }

    public JTextField getTxtNombreRepresentanteLegal() {
        return txtNombreRepresentanteLegal;
    }

    public void setTxtNombreRepresentanteLegal(JTextField txtNombreRepresentanteLegal) {
        this.txtNombreRepresentanteLegal = txtNombreRepresentanteLegal;
    }

    public JTextField getTxtApellidoRepresentanteLegal() {
        return txtApellidoRepresentanteLegal;
    }

    public void setTxtApellidoRepresentanteLegal(JTextField txtApellidoRepresentanteLegal) {
        this.txtApellidoRepresentanteLegal = txtApellidoRepresentanteLegal;
    }

    public JTextField getTxtTelefonoProveedores() {
        return txtTelefonoProveedores;
    }

    public void setTxtTelefonoProveedores(JTextField txtTelefonoProveedores) {
        this.txtTelefonoProveedores = txtTelefonoProveedores;
    }

    public JTextField getTxtCorreoProveedores() {
        return txtCorreoProveedores;
    }

    public void setTxtCorreoProveedores(JTextField txtCorreoProveedores) {
        this.txtCorreoProveedores = txtCorreoProveedores;
    }

    public JTextField getTxtDireccionProveedores() {
        return txtDireccionProveedores;
    }

    public void setTxtDireccionProveedores(JTextField txtDireccionProveedores) {
        this.txtDireccionProveedores = txtDireccionProveedores;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(Utilidades utilidades) {
        this.utilidades = utilidades;
    }

    public JDateChooser getJDateFechaVencimientoProveedores() {
        return JDateFechaVencimientoProveedores;
    }

    public void setJDateFechaVencimientoProveedores(JDateChooser JDateFechaVencimientoProveedores) {
        this.JDateFechaVencimientoProveedores = JDateFechaVencimientoProveedores;
    }
    
    public Proveedores guardarEditarProveedores(boolean isEditarProveedores){
        if (txtRuc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo ruc no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtRuc.requestFocus();
            return null;
        }
        if (txtRazonSocial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo RazonSocial no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtRazonSocial.requestFocus();
            return null;
        }
        if (txtTipoActividad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo TipodeActvidad no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtTipoActividad.requestFocus();
            return null;
        }
        if (txtNombreRepresentanteLegal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Nombre no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombreRepresentanteLegal.requestFocus();
            return null;
        }
        if (txtApellidoRepresentanteLegal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Apellido no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtApellidoRepresentanteLegal.requestFocus();
            return null;
        }
        
        if (txtTelefonoProveedores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo telefono no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtTelefonoProveedores.requestFocus();
            return null;
        }
        if (txtCorreoProveedores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo correo no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCorreoProveedores.requestFocus();
            return null;
        }
        if (txtDireccionProveedores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo direccion no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtDireccionProveedores.requestFocus();
            return null;
        }
        
        Proveedores proveedor = new Proveedores();
        proveedor.setRuc(txtRuc.getText());
        proveedor.setRazonSocial(txtRazonSocial.getText());
        proveedor.setTipoActividad(txtTipoActividad.getText());
        proveedor.setNombreRepresentanteLegal(txtNombreRepresentanteLegal.getText());
        proveedor.setApellidoRepresentanteLegal(txtApellidoRepresentanteLegal.getText());
        proveedor.setTelefonoProveedor(txtTelefonoProveedores.getText());
        proveedor.setCorreoProveedores(txtCorreoProveedores.getText());
        proveedor.setDireccionProveedores(txtDireccionProveedores.getText());
        if(isEditarProveedores){
            proveedor.setFechaActualizacion(new Date());
        }else {
            proveedor.setFechaRegistro(new Date());
        }
        proveedor.setFechaVencimiento(JDateFechaVencimientoProveedores.getDate());
        System.out.println(proveedor.toString());
        return proveedor;
    }
    
    
}


