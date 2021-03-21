/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelo;

import java.util.Date;

/**
 *
 * @author ANDRES
 */
public class Proveedores {
    private int idProveedores;
    private String ruc;
    private String razonSocial;
    private String tipoActividad;
    private String nombreRepresentanteLegal;
    private String apellidoRepresentanteLegal;
    private String telefonoProveedor;
    private String correoProveedores;
    private String direccionProveedores;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaVencimiento;

    public Proveedores(int idProveedores, String ruc, String razonSocial, String tipoActividad, String nombreRepresentanteLegal, String apellidoRepresentanteLegal, String telefonoProveedor, String correoProveedores, String direccionProveedores, Date fechaRegistro, Date fechaActualizacion, Date fechaVencimiento) {
        this.idProveedores = idProveedores;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.tipoActividad = tipoActividad;
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
        this.apellidoRepresentanteLegal = apellidoRepresentanteLegal;
        this.telefonoProveedor = telefonoProveedor;
        this.correoProveedores = correoProveedores;
        this.direccionProveedores = direccionProveedores;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaVencimiento = fechaVencimiento;
    }

       
    public Proveedores() {
    }
    
    

    public int getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getApellidoRepresentanteLegal() {
        return apellidoRepresentanteLegal;
    }

    public void setApellidoRepresentanteLegal(String apellidoRepresentanteLegal) {
        this.apellidoRepresentanteLegal = apellidoRepresentanteLegal;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCorreoProveedores() {
        return correoProveedores;
    }

    public void setCorreoProveedores(String correoProveedores) {
        this.correoProveedores = correoProveedores;
    }

    public String getDireccionProveedores() {
        return direccionProveedores;
    }

    public void setDireccionProveedores(String direccionProveedores) {
        this.direccionProveedores = direccionProveedores;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Proveedores{" + "idProveedores=" + idProveedores +
                ", ruc=" + ruc + ", razonSocial=" + razonSocial + ", tipoActividad=" + tipoActividad +
                ", nombreRepresentanteLegal=" + nombreRepresentanteLegal +
                ", apellidoRepresentanteLegal=" + apellidoRepresentanteLegal +
                ", telefonoProveedor=" + telefonoProveedor + ", correoProveedores=" + correoProveedores +
                ", direccionProveedores=" + direccionProveedores + 
                ", fechaRegistro=" + fechaRegistro + ", fechaActualizacion=" + fechaActualizacion +
                ", fechaVencimiento=" + fechaVencimiento + '}';
    }   

        
}
