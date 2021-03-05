/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelTables;

import com.istloja.modelo.Inventarios;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;

/**
 *
 * @author ANDRES
 */
public interface ComunicacionPersona {
    void clickPersona(Persona p);
    void clickProveedores(Proveedores p);
    void clickInventarios(Inventarios i);
}
