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
    private String descripcion;
    private String preciosCompra;
    private String preciosVenta;
    private String cantidadProductos;

    public Inventarios(int idInventario, String codigoProducto, String descripcion, String preciosCompra, String preciosVenta, String cantidadProductos) {
        this.idInventario = idInventario;
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.preciosCompra = preciosCompra;
        this.preciosVenta = preciosVenta;
        this.cantidadProductos = cantidadProductos;
    }
    
    public Inventarios(){
    
    }

    /*public Inventarios(int Inventarios){
        this.idInventario = idInventario;
    }*/
    
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPreciosCompra() {
        return preciosCompra;
    }

    public void setPreciosCompra(String preciosCompra) {
        this.preciosCompra = preciosCompra;
    }

    public String getPreciosVenta() {
        return preciosVenta;
    }

    public void setPreciosVenta(String preciosVenta) {
        this.preciosVenta = preciosVenta;
    }

    public String getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(String cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    
    
    @Override
    public String toString() {
        return  "Inventarios{" +"codigo_pro" +codigoProducto+ "descripcion" +descripcion+ "precios_compra" +preciosCompra+ "precios_venta" +preciosVenta+ "can_productos" +cantidadProductos+ '}';
    }

    public Inventarios get(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
