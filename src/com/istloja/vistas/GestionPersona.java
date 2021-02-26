/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;


  
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import com.istloja.modelo.Utilidades;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;  
import javax.swing.JTextField;

/**
 *
 * @author ANDRES
 */
public class GestionPersona {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private Utilidades utilidades;
    private JFrame frameGestionContable;
    
    public GestionPersona(JTextField txtCedula, JTextField txtNombre, JTextField txtApellido, JTextField txtDireccion, JTextField txtCorreo, JTextField txtTelefono, Utilidades utilidades, JFrame frameGestionContable) {
        this.txtCedula = txtCedula;
        this.txtNombre = txtNombre;
        this.txtApellido = txtApellido;
        this.txtDireccion = txtDireccion;
        this.txtCorreo = txtCorreo;
        this.txtTelefono = txtTelefono;
        this.utilidades = utilidades;
        this.frameGestionContable = frameGestionContable;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(JTextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(Utilidades utilidades) {
        this.utilidades = utilidades;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

    

    public void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
    
    public Persona guardarEditar(){
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo cedula no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCedula.requestFocus();
            return null;
        }
        if (!utilidades.validadorDeCedula(txtCedula.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "La CEDULA ingresada no es valida", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCedula.requestFocus();
            return null;
        }
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo nombre no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return null;
        }
        if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo apellido no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtApellido.requestFocus();
            return null;
        }
        if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo direccion no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtDireccion.requestFocus();
            return null;
        }
        if (txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo correo no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCorreo.requestFocus();
            return null;
        }
        if (!utilidades.validarCorreo(txtCorreo.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "Los datos ingresados en el CORREO no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCorreo.requestFocus();
            return null;
        }
        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo telefono no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtTelefono.requestFocus();
            return null;
        }
        if (!utilidades.validarNumeros(txtTelefono.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "Los datos ingresados en el TELEFONO no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtTelefono.requestFocus();
            return null;
        }
        Persona persona = new Persona();
        persona.setCedula(txtCedula.getText());
        persona.setNombre(txtNombre.getText());
        persona.setApellidos(txtApellido.getText());
        persona.setDireccion(txtDireccion.getText());
        persona.setCorreo(txtCorreo.getText());
        persona.setTelefono(txtTelefono.getText());
        System.out.println(persona.toString());
        return persona;
    }
    
    /*public Proveedores guardarEditarProveedores(){
        if (txtRuc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo ruc no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtRuc.requestFocus();
            return null;
        }
        
        if (txtRazonSocial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo nombre no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return null;
        }
        if (txtTipoActividad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo apellido no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtTipoActividad.requestFocus();
            return null;
        }
        if (txtNombreRepresentanteLegal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo direccion no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombreRepresentanteLegal.requestFocus();
            return null;
        }
        if (txtApellidoRepresentanteLegal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo correo no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtApellidoRepresentanteLegal.requestFocus();
            return null;
        }
        
        if (txtTelefonoProveedores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo telefono no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtTelefonoProveedores.requestFocus();
            return null;
        }
        if (txtCorreoProveedores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo telefono no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCorreoProveedores.requestFocus();
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
        System.out.println(proveedor.toString());
        return proveedor;
    }*/
}
