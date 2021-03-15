/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelo;

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

    public Inventarios(int idInventario, String codigoProducto,
            String cantidadProductos, String descripcion, 
            String preciosCompra_sinIva, String preciosCompra_conIva,
            String precioMayorista, String precioClienteFijo,
            String precioClienteNormal) {
        this.idInventario = idInventario;
        this.codigoProducto = codigoProducto;
        this.cantidadProductos = cantidadProductos;
        this.descripcion = descripcion;
        this.preciosCompra_sinIva = preciosCompra_sinIva;
        this.preciosCompra_conIva = preciosCompra_conIva;
        this.precioMayorista = precioMayorista;
        this.precioClienteFijo = precioClienteFijo;
        this.precioClienteNormal = precioClienteNormal;
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

    @Override
    public String toString() {
        return "Inventarios{" + "idInventario=" + idInventario +
                ", codigoProducto=" + codigoProducto +
                ", cantidadProductos=" + cantidadProductos +
                ", descripcion=" + descripcion +
                ", preciosCompra_sinIva=" + preciosCompra_sinIva +
                ", preciosCompra_conIva=" + preciosCompra_conIva + 
                ", precioMayorista=" + precioMayorista +
                ", precioClienteFijo=" + precioClienteFijo +
                ", precioClienteNormal=" + precioClienteNormal + '}';
    }
    
    public Inventarios get(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
