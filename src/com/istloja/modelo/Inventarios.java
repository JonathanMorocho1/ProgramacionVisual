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
public class Inventarios {
    private int idInventario;
    private String codigoProducto;
    private String cantidadProductos;
    private String descripcion;
    private String preciosCompra_sinIva;
    private String preciosCompra_conIva;
    private String precioMayorista;
    private String precioClienteFijo;
    private String precioClienteNormal;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaVencimiento;

    public Inventarios(int idInventario, String codigoProducto, String cantidadProductos, String descripcion, String preciosCompra_sinIva, String preciosCompra_conIva, String precioMayorista, String precioClienteFijo, String precioClienteNormal, Date fechaRegistro, Date fechaActualizacion, Date fechaVencimiento) {
        this.idInventario = idInventario;
        this.codigoProducto = codigoProducto;
        this.cantidadProductos = cantidadProductos;
        this.descripcion = descripcion;
        this.preciosCompra_sinIva = preciosCompra_sinIva;
        this.preciosCompra_conIva = preciosCompra_conIva;
        this.precioMayorista = precioMayorista;
        this.precioClienteFijo = precioClienteFijo;
        this.precioClienteNormal = precioClienteNormal;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaVencimiento = fechaVencimiento;
    }
     
    public Inventarios(){
    
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(String cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPreciosCompra_sinIva() {
        return preciosCompra_sinIva;
    }

    public void setPreciosCompra_sinIva(String preciosCompra_sinIva) {
        this.preciosCompra_sinIva = preciosCompra_sinIva;
    }

    public String getPreciosCompra_conIva() {
        return preciosCompra_conIva;
    }

    public void setPreciosCompra_conIva(String preciosCompra_conIva) {
        this.preciosCompra_conIva = preciosCompra_conIva;
    }

    public String getPrecioMayorista() {
        return precioMayorista;
    }

    public void setPrecioMayorista(String precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    public String getPrecioClienteFijo() {
        return precioClienteFijo;
    }

    public void setPrecioClienteFijo(String precioClienteFijo) {
        this.precioClienteFijo = precioClienteFijo;
    }

    public String getPrecioClienteNormal() {
        return precioClienteNormal;
    }

    public void setPrecioClienteNormal(String precioClienteNormal) {
        this.precioClienteNormal = precioClienteNormal;
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
        return "Inventarios{" + "idInventario=" + idInventario + ", codigoProducto=" + codigoProducto + ", cantidadProductos=" + cantidadProductos + ", descripcion=" + descripcion + ", preciosCompra_sinIva=" + preciosCompra_sinIva + ", preciosCompra_conIva=" + preciosCompra_conIva + ", precioMayorista=" + precioMayorista + ", precioClienteFijo=" + precioClienteFijo + ", precioClienteNormal=" + precioClienteNormal + ", fechaRegistro=" + fechaRegistro + ", fechaActualizacion=" + fechaActualizacion + ", fechaVencimiento=" + fechaVencimiento + '}';
    }   

}
