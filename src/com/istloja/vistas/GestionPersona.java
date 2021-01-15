/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ANDRES
 */
public class GestionPersona extends JFrame{
    
    private JPanel panel = new JPanel();
    
    private JPanel panelTitulo = new JPanel();
    private JPanel panelCuerpo = new JPanel();
    private JPanel panelButton = new JPanel();
            
    public GestionPersona(){
        //mostrar pantalla
        this.show();
        //titulo de la ventana
        this.setTitle("REGISTRO");
        //tmaño de la ventana
        this.setSize(450,250);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        //EL layout se encarga de ordenar o Distribuir los componentes
        
        panel.setLayout(new FlowLayout());        
        JLabel label = new JLabel("Registro de Personas.");
        
        panelTitulo.add(label);
        panel.add(panelTitulo);
        panel.add(label);
        
       
        
        panelCuerpo.setLayout(new GridLayout(7,2));
        panelCuerpo.add(new JLabel("CÉDULA"));
        panelCuerpo.add(new JTextField("1150460762"));
        panelCuerpo.add(new JLabel("NOMBRE"));
        panelCuerpo.add(new JTextField("Jonatan"));
        panelCuerpo.add(new JLabel("APELLIDO"));
        panelCuerpo.add(new JTextField("Morocho"));
        panelCuerpo.add(new JLabel("DIRECCIÓN"));
        panelCuerpo.add(new JTextField("El valle"));
        panelCuerpo.add(new JLabel("CORREO"));
        panelCuerpo.add(new JTextField("jamorocho@tecnologicoloja.edu.ec"));
        panelCuerpo.add(new JLabel("TELEFONO"));
        panelCuerpo.add(new JTextField("0969084769"));
        panel.add(panelCuerpo);
        
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(new JButton("Agregar"));
        panelButton.add(new JButton("Editar"));
        panelButton.add(new JButton("Eliminar"));
        panelButton.add(new JButton("Traer Ultimo Registro"));
        
        panel.add(panelButton);
        
        //Agrega el panel al JFrame
        this.add(panel);
        //Centrar la pantalla
        this.setLocationRelativeTo(null);
        //Cerrar o dar fin a la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
