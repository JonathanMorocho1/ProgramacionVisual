/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Utilidades;
import javax.swing.JFrame;
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
    private Utilidades utilidades;
    private JFrame frameGestionContable;

    public GestionProveedores(JTextField txtRuc, JTextField txtRazonSocial, JTextField txtTipoActividad, JTextField txtNombreRepresentanteLegal, JTextField txtApellidoRepresentanteLegal, JTextField txtTelefonoProveedores, JTextField txtCorreoProveedores) {
        this.txtRuc = txtRuc;
        this.txtRazonSocial = txtRazonSocial;
        this.txtTipoActividad = txtTipoActividad;
        this.txtNombreRepresentanteLegal = txtNombreRepresentanteLegal;
        this.txtApellidoRepresentanteLegal = txtApellidoRepresentanteLegal;
        this.txtTelefonoProveedores = txtTelefonoProveedores;
        this.txtCorreoProveedores = txtCorreoProveedores;
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

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

    
    
    
}


