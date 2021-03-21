package com.istloja.modelo;

import java.util.Date;

public class Persona {

    private int idPersona;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String correo;
    private String telefono;
    private String genero;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaNacimiento;

    public Persona(int idPersona, String cedula, String nombre, String apellidos, String direccion, String correo, String telefono, String genero, Date fechaRegistro, Date fechaActualizacion, Date fechaNacimiento) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    

    public Persona(){
    
    }
    
    public Persona(int idPersona){
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + 
                ", cedula=" + cedula + ", nombre=" + nombre +
                ", apellidos=" + apellidos + ", direccion=" + direccion + 
                ", correo=" + correo + ", telefono=" + telefono +
                ", genero=" + genero + ", fechaRegistro=" + fechaRegistro +
                ", fechaActualizacion=" + fechaActualizacion + 
                ", fechaNacimiento=" + fechaNacimiento + '}';
    }
     

    
}
