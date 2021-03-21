/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;


  
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import com.istloja.modelo.Utilidades;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JComboBox;
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
    private JComboBox cbxGenero; 
    private Utilidades utilidades;
    private JDateChooser JDateFechaNacimiento;
    private JFrame frameGestionContable;

    public GestionPersona(JTextField txtCedula, JTextField txtNombre, JTextField txtApellido, JTextField txtDireccion, JTextField txtCorreo, JTextField txtTelefono, JComboBox cbxGenero, Utilidades utilidades, JDateChooser JDateFechaNacimiento, JFrame frameGestionContable) {
        this.txtCedula = txtCedula;
        this.txtNombre = txtNombre;
        this.txtApellido = txtApellido;
        this.txtDireccion = txtDireccion;
        this.txtCorreo = txtCorreo;
        this.txtTelefono = txtTelefono;
        this.cbxGenero = cbxGenero;
        this.utilidades = utilidades;
        this.JDateFechaNacimiento = JDateFechaNacimiento;
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

    public JComboBox getCbxGenero() {
        return cbxGenero;
    }

    public void setCbxGenero(JComboBox cbxGenero) {
        this.cbxGenero = cbxGenero;
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
    
    public JDateChooser getJDateFechaNacimiento() {
        return JDateFechaNacimiento;
    }

    public void setJDateFechaNacimiento(JDateChooser JDateFechaNacimiento) {
        this.JDateFechaNacimiento = JDateFechaNacimiento;
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
        cbxGenero.setSelectedItem("Seleccionar:");
        //JDateFechaNacimiento.setDate("00-00-0000");
    }
    
    public Persona guardarEditar(boolean isEditar){
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
        persona.setGenero(cbxGenero.getSelectedItem().toString());
        
        if(isEditar){
            persona.setFechaActualizacion(new Date());
        }else {
            persona.setFechaRegistro(new Date());
        }
        persona.setFechaNacimiento(JDateFechaNacimiento.getDate());
        System.out.println(persona.toString());
        return persona;
    }
    
    /**/
}
