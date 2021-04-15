/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.controlador.Inventariosbd;
import com.istloja.controlador.Personabd;
import com.istloja.controlador.Proveedorbd;
import com.istloja.modelTables.ComunicacionPersona;
import java.awt.Toolkit;
import com.istloja.modelTables.ModelTableInventarios;
import com.istloja.modelTables.ModelTablePersona;
import com.istloja.modelTables.ModeloTableProveedores;
import com.istloja.modelTables.ModelTableVenta;
import com.istloja.modelo.Inventarios;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import com.istloja.modelo.ProductoVenta;
import com.istloja.modelo.Utilidades;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.beans.PropertyEditorManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ANDRES
 */
public class GestionPersonaV1 extends javax.swing.JFrame implements ComunicacionPersona {
   
    private Utilidades utilidades;
    private Personabd controladorPersona;
    private Persona personaEditar;
    private Persona personaSeleccionada;
    //private Proveedores proveedoresEditar;
    private Inventarios inventariosEditarEliminar;
    private Proveedores proveedoresEditarEliminar;
    private GestionPersona gestionPersona;
    private GestionProveedores gestionProveedores;
    private GestionInventarios gestionInventarios;
    //private Persona personaEliminar;
    private ModelTablePersona modeloTablePersona;
    private ModeloTableProveedores modeloTableProveedores;
    private ModelTableInventarios modelTableInvetarios;
    private ModelTableVenta modelTableVenta;
    private Proveedorbd controladorProveedor;
    private Inventariosbd controladorInventarios;
    
    /**
     * Creates new form GestionPersonaV1
     */
    public GestionPersonaV1() {
        controladorPersona = new Personabd();
        controladorProveedor = new Proveedorbd();
        controladorInventarios = new Inventariosbd();
        modeloTablePersona = new ModelTablePersona(controladorPersona.obtenerPersonas(),this);
        modeloTableProveedores = new ModeloTableProveedores(controladorProveedor.obtenerProveedores(), this);
        modelTableInvetarios = new ModelTableInventarios(controladorInventarios.obtenerInventarios(), this);
        modelTableVenta = new ModelTableVenta(new ArrayList<ProductoVenta>(), this);
        initComponents();
        this.setLocationRelativeTo(null);
        utilidades = new Utilidades();
        gestionPersona = new GestionPersona(txtCedula, txtNombre, txtApellido, txtDireccion, txtCorreo, txtTelefono, cbxGenero, utilidades, JDateFechaNacimiento, this);
        gestionProveedores = new GestionProveedores(txtRuc, txtRazonSocial, txtTipoActividad, txtNombreRepresentanteLegal, txtApellidoRepresentanteLegal, txtTelefonoProveedores, txtCorreoProveedores, txtDireccionProveedores, utilidades, JDateFechaVencimientoProveedores, this);
        gestionInventarios = new GestionInventarios(txtCodigoProducto, txtCantidadProductos, txtDescripcion, txtPreciosCompraSinIva, txtPreciosCompraConIva, txtPrecioMayorista, txtPrecioClienteFijo, txtPrecioClienteNormal, JDateFechaVencimientoInventarios, this);
        BloquearCamposInventarios();
        BloquearCamposClienteVenta();
        productosVenta = new ArrayList<>();
    }

    void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        cbxGenero.setSelectedItem("Seleccionar:");
        JDateFechaNacimiento.setDate(null);
    }
    void BloquearCamposInventarios(){
        txtPreciosCompraConIva.setEditable(false);
        /*txtPrecioMayorista.setEditable(false);
        txtPrecioClienteFijo.setEditable(false);
        txtPrecioClienteNormal.setEditable(false);*/
    }
    
    public void calcularCanitdadesInventarios(){
               
        String precio = txtPreciosCompraSinIva.getText();
        
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.00", dfs);
        
        //para sacar el precio con IVA
        double iva = Double.parseDouble(precio)*0.12;
        double total_con_iva = Double.parseDouble(precio) + iva;
        String totalIva = String.valueOf(df.format(total_con_iva));
        txtPreciosCompraConIva.setText(totalIva);
        
        //Para sacar el precio del mayorista
        double precioMayorista = total_con_iva*0.10;
        double totalMayorista = total_con_iva + precioMayorista;
        String mostrarMayorista = String.valueOf(df.format(totalMayorista));
        txtPrecioMayorista.setText(mostrarMayorista);
        
        //para sacar el precio del cliente fijo
        double precioClienteFijo = total_con_iva*0.12;
        double totalClienteFijo = total_con_iva + precioClienteFijo;
        String mostrarClienteFijo = String.valueOf(df.format(totalClienteFijo));
        txtPrecioClienteFijo.setText(mostrarClienteFijo);
        
        //para sacar el precio del cliente normal
        double precioClienteNormal = total_con_iva*0.15;
        double totalClienteNormal = total_con_iva + precioClienteNormal;
        String mostrarClienteNormal = String.valueOf(df.format(totalClienteNormal));
        txtPrecioClienteNormal.setText(mostrarClienteNormal); 
        
        
    }
    public void calcularValoresAdicionales(){
        double subTotal = 0;
        double iva = 0;
        double precioFinal = 0;
        if(!productosVenta.isEmpty()){
            for(ProductoVenta productosVenta : productosVenta){
                subTotal = subTotal + (productosVenta.getSubTotal() * productosVenta.getCantidad());
            }
            iva = subTotal * 0.12;
            precioFinal = subTotal + iva;
            txtSubTotalVentas.setText(String.valueOf(utilidades.dosDecimales(subTotal)));
            txtIvaVentas.setText(String.valueOf(utilidades.dosDecimales(iva)));
            txtTotalVentas.setText(String.valueOf(utilidades.dosDecimales(precioFinal)));
        }else{
            txtSubTotalVentas.setText(String.valueOf(utilidades.dosDecimales(subTotal)));
            txtIvaVentas.setText(String.valueOf(utilidades.dosDecimales(iva)));
            txtTotalVentas.setText(String.valueOf(utilidades.dosDecimales(precioFinal)));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabGeneral = new javax.swing.JTabbedPane();
        panelClientes = new javax.swing.JPanel();
        panelCuerpoRegistro = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        cbxGenero = new javax.swing.JComboBox<>();
        rdbtnCedula = new javax.swing.JRadioButton();
        rdbtnPasaporte = new javax.swing.JRadioButton();
        lblFechaNacimiento = new javax.swing.JLabel();
        JDateFechaNacimiento = new com.toedter.calendar.JDateChooser();
        btnEditar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        lblBuscarClientecbx = new javax.swing.JLabel();
        cboxElegirBuscar = new javax.swing.JComboBox<>();
        txtBuscarClientescbx = new javax.swing.JTextField();
        panelProveedores = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblRuc = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        lblTipoActividad = new javax.swing.JLabel();
        lblNombreRepresentanteLegal = new javax.swing.JLabel();
        lblApellidoRepresentanteLegal = new javax.swing.JLabel();
        lblTelefonoProveedores = new javax.swing.JLabel();
        lblCorreoProveedores = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtTipoActividad = new javax.swing.JTextField();
        txtNombreRepresentanteLegal = new javax.swing.JTextField();
        txtApellidoRepresentanteLegal = new javax.swing.JTextField();
        txtTelefonoProveedores = new javax.swing.JTextField();
        txtCorreoProveedores = new javax.swing.JTextField();
        lblDireccionProveedores = new javax.swing.JLabel();
        txtDireccionProveedores = new javax.swing.JTextField();
        lblFechaVencimientoProveedores = new javax.swing.JLabel();
        JDateFechaVencimientoProveedores = new com.toedter.calendar.JDateChooser();
        lblBuscarProveedorescbx = new javax.swing.JLabel();
        cbxElegirProveedores = new javax.swing.JComboBox<>();
        txtBuscarProveedoresCbx = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        btnEditarProveedores = new javax.swing.JButton();
        btnGuardarProveedores = new javax.swing.JButton();
        btnEliminarProveedores = new javax.swing.JButton();
        btnLimpiarProveedores = new javax.swing.JButton();
        panelInventarios = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblCodigoProducto = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblPreciosCompraSinIva = new javax.swing.JLabel();
        lblPrecioMayorista = new javax.swing.JLabel();
        lblCantidadProductos = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPreciosCompraSinIva = new javax.swing.JTextField();
        txtPrecioMayorista = new javax.swing.JTextField();
        txtCantidadProductos = new javax.swing.JTextField();
        lblPreciosCompraConIva = new javax.swing.JLabel();
        txtPreciosCompraConIva = new javax.swing.JTextField();
        lblPrecioClienteFijo = new javax.swing.JLabel();
        txtPrecioClienteFijo = new javax.swing.JTextField();
        lblPrecioClienteNormal = new javax.swing.JLabel();
        txtPrecioClienteNormal = new javax.swing.JTextField();
        lblFechaVencimientoInventarios = new javax.swing.JLabel();
        JDateFechaVencimientoInventarios = new com.toedter.calendar.JDateChooser();
        lblBuscarInventarios = new javax.swing.JLabel();
        cbxInventarios = new javax.swing.JComboBox<>();
        txtBuscarInventarioscbx = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaInventarios = new javax.swing.JTable();
        btnGuardarInventarios = new javax.swing.JButton();
        btnEditarInventarios = new javax.swing.JButton();
        btnEliminarInventarios = new javax.swing.JButton();
        btnTraerInventario = new javax.swing.JButton();
        panelVentas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblIDproducto = new javax.swing.JLabel();
        txtNumeroNotaVenta = new javax.swing.JTextField();
        txtCedulaClienteVenta = new javax.swing.JTextField();
        txtNombreClienteVenta = new javax.swing.JTextField();
        txtTelefonoClienteVenta = new javax.swing.JTextField();
        txtIDProductoVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtFechaVenta = new javax.swing.JTextField();
        txtCantidadProductosVenta = new javax.swing.JTextField();
        btnAgregarNotaVents = new javax.swing.JButton();
        btnBusquedaAvanzada = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtSubTotalVentas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIvaVentas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotalVentas = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaNotaVenta = new javax.swing.JTable();
        btnEliminarCamposVenta = new javax.swing.JButton();
        MenuGeneral = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        MenuItemGuardar = new javax.swing.JMenuItem();
        MenuItemEliminar = new javax.swing.JMenuItem();
        MenuItemEditar = new javax.swing.JMenuItem();
        MenuItemTraer = new javax.swing.JMenuItem();
        MenuItemSalir = new javax.swing.JMenuItem();
        MenuEditar = new javax.swing.JMenu();
        MenuItemBuscarCedula = new javax.swing.JMenuItem();
        MenuItemBuscarApellido = new javax.swing.JMenuItem();
        MenuAcerca = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        panelClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelCuerpoRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Clientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        txtNombre.setToolTipText("Ingrese el nombre del Usuario");
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNombreMouseExited(evt);
            }
        });

        txtCedula.setToolTipText("Ingrese una cédula valida");
        txtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaFocusLost(evt);
            }
        });
        txtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCedulaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCedulaMouseExited(evt);
            }
        });

        lblCedula.setText("Cedula");

        lblNombre.setText("Nombre");

        txtApellido.setToolTipText("Ingresar el apellido del Usuario");
        txtApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtApellidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtApellidoMouseExited(evt);
            }
        });

        lblApellido.setText("Apellido");

        lblDireccion.setText("Direccion");

        txtDireccion.setToolTipText("Dirección del Usuario");
        txtDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtDireccionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtDireccionMouseExited(evt);
            }
        });

        txtCorreo.setToolTipText("Ingrese el correo electronico de Usuario");
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCorreoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCorreoMouseExited(evt);
            }
        });

        lblCorreo.setText("Correo");

        txtTelefono.setToolTipText("Ingrese el telefono del Usuario");
        txtTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTelefonoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTelefonoMouseExited(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lblTelefono.setText("Telefono");

        lblGenero.setText("Genéro");

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "No definido", "Masculino", "Femenino" }));

        rdbtnCedula.setText("Cédula");

        rdbtnPasaporte.setText("Pasaporte");

        lblFechaNacimiento.setText("Fecha de Nacimiento");

        javax.swing.GroupLayout panelCuerpoRegistroLayout = new javax.swing.GroupLayout(panelCuerpoRegistro);
        panelCuerpoRegistro.setLayout(panelCuerpoRegistroLayout);
        panelCuerpoRegistroLayout.setHorizontalGroup(
            panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoRegistroLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblCedula)
                    .addComponent(lblApellido)
                    .addComponent(lblDireccion)
                    .addComponent(lblCorreo)
                    .addComponent(lblTelefono)
                    .addComponent(lblGenero)
                    .addComponent(lblFechaNacimiento))
                .addGap(32, 32, 32)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellido)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCuerpoRegistroLayout.createSequentialGroup()
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbtnPasaporte))
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCuerpoRegistroLayout.setVerticalGroup(
            panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoRegistroLayout.createSequentialGroup()
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbtnCedula)
                        .addComponent(rdbtnPasaporte))
                    .addComponent(lblCedula, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(panelCuerpoRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(JDateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        btnEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/usuario.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setToolTipText("Editar clientes");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/escoba.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("Limpiar todos los campos");
        btnLimpiar.setMaximumSize(new java.awt.Dimension(32, 32));
        btnLimpiar.setMinimumSize(new java.awt.Dimension(32, 32));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar Clientes");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guardar Clientes");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        TablaClientes.setModel(modeloTablePersona
        );
        TablaClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(TablaClientes);

        lblBuscarClientecbx.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscarClientecbx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/buscar.png"))); // NOI18N
        lblBuscarClientecbx.setText("Buscar Cliente:");

        cboxElegirBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cboxElegirBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Cedula", "Nombres", "Apellidos", "Telefono", "Correo" }));

        txtBuscarClientescbx.setToolTipText("Para realizar una busqueda ingrese un parametro");
        txtBuscarClientescbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientescbxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCuerpoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelClientesLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnGuardar)
                                .addGap(35, 35, 35)
                                .addComponent(btnEliminar)
                                .addGap(35, 35, 35)
                                .addComponent(btnEditar)
                                .addGap(35, 35, 35)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblBuscarClientecbx)
                .addGap(18, 18, 18)
                .addComponent(cboxElegirBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtBuscarClientescbx, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCuerpoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarClientecbx)
                    .addComponent(cboxElegirBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarClientescbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Clientes", panelClientes);

        panelProveedores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        lblRuc.setText("RUC");

        lblRazonSocial.setText("Razón Social");

        lblTipoActividad.setText("Tipo de Actividad");

        lblNombreRepresentanteLegal.setText("Nombre de Representante Legal");

        lblApellidoRepresentanteLegal.setText("Apellido de Representante Legal");

        lblTelefonoProveedores.setText("Telefono");

        lblCorreoProveedores.setText("Correo");

        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        txtTelefonoProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedoresKeyTyped(evt);
            }
        });

        lblDireccionProveedores.setText("Dirección");

        lblFechaVencimientoProveedores.setText("FechaVencimiento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefonoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoRepresentanteLegal)
                    .addComponent(lblNombreRepresentanteLegal)
                    .addComponent(lblTipoActividad)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRuc)
                    .addComponent(lblCorreoProveedores)
                    .addComponent(lblDireccionProveedores)
                    .addComponent(lblFechaVencimientoProveedores))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRuc)
                    .addComponent(txtRazonSocial)
                    .addComponent(txtNombreRepresentanteLegal)
                    .addComponent(txtApellidoRepresentanteLegal)
                    .addComponent(txtTelefonoProveedores)
                    .addComponent(txtCorreoProveedores)
                    .addComponent(txtTipoActividad)
                    .addComponent(txtDireccionProveedores)
                    .addComponent(JDateFechaVencimientoProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuc)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRazonSocial)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoActividad)
                    .addComponent(txtTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreRepresentanteLegal)
                    .addComponent(txtNombreRepresentanteLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoRepresentanteLegal)
                    .addComponent(txtApellidoRepresentanteLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefonoProveedores)
                    .addComponent(txtTelefonoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreoProveedores)
                    .addComponent(txtCorreoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccionProveedores)
                    .addComponent(txtDireccionProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaVencimientoProveedores)
                    .addComponent(JDateFechaVencimientoProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblBuscarProveedorescbx.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscarProveedorescbx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/buscar.png"))); // NOI18N
        lblBuscarProveedorescbx.setText("Buscar Proveedores:");

        cbxElegirProveedores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxElegirProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Ruc", "Nombre", " " }));

        txtBuscarProveedoresCbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProveedoresCbxKeyReleased(evt);
            }
        });

        tablaProveedores.setModel(modeloTableProveedores);
        jScrollPane5.setViewportView(tablaProveedores);

        btnEditarProveedores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/usuario.png"))); // NOI18N
        btnEditarProveedores.setText("Editar");
        btnEditarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedoresActionPerformed(evt);
            }
        });

        btnGuardarProveedores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/disco-flexible.png"))); // NOI18N
        btnGuardarProveedores.setText("Guardar");
        btnGuardarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedoresActionPerformed(evt);
            }
        });

        btnEliminarProveedores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/eliminar.png"))); // NOI18N
        btnEliminarProveedores.setText("Eliminar");
        btnEliminarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedoresActionPerformed(evt);
            }
        });

        btnLimpiarProveedores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/escoba.png"))); // NOI18N
        btnLimpiarProveedores.setText("Limpiar");
        btnLimpiarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedoresLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedoresLayout.createSequentialGroup()
                        .addComponent(btnGuardarProveedores)
                        .addGap(38, 38, 38)
                        .addComponent(btnEditarProveedores)
                        .addGap(38, 38, 38)
                        .addComponent(btnEliminarProveedores)
                        .addGap(38, 38, 38)
                        .addComponent(btnLimpiarProveedores)
                        .addGap(220, 220, 220))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedoresLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblBuscarProveedorescbx)
                        .addGap(18, 18, 18)
                        .addComponent(cbxElegirProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarProveedoresCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelProveedoresLayout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarProveedores)
                    .addComponent(btnEditarProveedores)
                    .addComponent(btnEliminarProveedores)
                    .addComponent(btnLimpiarProveedores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxElegirProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarProveedoresCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarProveedorescbx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabGeneral.addTab("Proveedores", panelProveedores);

        panelInventarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Inventarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        lblCodigoProducto.setText("Codigo Producto");

        lblDescripcion.setText("Descripción");

        lblPreciosCompraSinIva.setText("Precios Compra Sin Iva");

        lblPrecioMayorista.setText("Precio Mayorista");

        lblCantidadProductos.setText("Cantidad Productos");

        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        txtPreciosCompraSinIva.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPreciosCompraSinIvaFocusLost(evt);
            }
        });
        txtPreciosCompraSinIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPreciosCompraSinIvaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPreciosCompraSinIvaKeyReleased(evt);
            }
        });

        lblPreciosCompraConIva.setText("Precios Compra Con Iva");

        lblPrecioClienteFijo.setText("Precio Cliente Fijo");

        lblPrecioClienteNormal.setText("Precio Cliente Normal");

        lblFechaVencimientoInventarios.setText("Fecha de Vencimiento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantidadProductos)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigoProducto)
                    .addComponent(lblPreciosCompraSinIva, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreciosCompraConIva)
                    .addComponent(lblPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioClienteFijo)
                    .addComponent(lblPrecioClienteNormal)
                    .addComponent(lblFechaVencimientoInventarios))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPrecioMayorista)
                    .addComponent(txtPreciosCompraSinIva, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadProductos)
                    .addComponent(txtPreciosCompraConIva)
                    .addComponent(txtPrecioClienteFijo)
                    .addComponent(txtPrecioClienteNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(JDateFechaVencimientoInventarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoProducto)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantidadProductos)
                    .addComponent(txtCantidadProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreciosCompraSinIva)
                    .addComponent(txtPreciosCompraSinIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreciosCompraConIva)
                    .addComponent(txtPreciosCompraConIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioMayorista)
                    .addComponent(txtPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioClienteFijo)
                    .addComponent(txtPrecioClienteFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioClienteNormal)
                    .addComponent(txtPrecioClienteNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaVencimientoInventarios)
                    .addComponent(JDateFechaVencimientoInventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        lblBuscarInventarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscarInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/buscar.png"))); // NOI18N
        lblBuscarInventarios.setText("Buscar Inventarios:");

        cbxInventarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxInventarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Codigo", "Descripción" }));

        txtBuscarInventarioscbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarInventarioscbxKeyReleased(evt);
            }
        });

        tablaInventarios.setModel(modelTableInvetarios);
        jScrollPane3.setViewportView(tablaInventarios);

        btnGuardarInventarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/disco-flexible.png"))); // NOI18N
        btnGuardarInventarios.setText("Guardar");
        btnGuardarInventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarInventariosActionPerformed(evt);
            }
        });

        btnEditarInventarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditarInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/usuario.png"))); // NOI18N
        btnEditarInventarios.setText("Editar");
        btnEditarInventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarInventariosActionPerformed(evt);
            }
        });

        btnEliminarInventarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/eliminar.png"))); // NOI18N
        btnEliminarInventarios.setText("Eliminar");
        btnEliminarInventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInventariosActionPerformed(evt);
            }
        });

        btnTraerInventario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnTraerInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/escoba.png"))); // NOI18N
        btnTraerInventario.setText("Limpiar");
        btnTraerInventario.setMaximumSize(new java.awt.Dimension(114, 40));
        btnTraerInventario.setMinimumSize(new java.awt.Dimension(114, 40));
        btnTraerInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraerInventarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInventariosLayout = new javax.swing.GroupLayout(panelInventarios);
        panelInventarios.setLayout(panelInventariosLayout);
        panelInventariosLayout.setHorizontalGroup(
            panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventariosLayout.createSequentialGroup()
                .addGroup(panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(panelInventariosLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelInventariosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblBuscarInventarios)
                .addGap(18, 18, 18)
                .addComponent(cbxInventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txtBuscarInventarioscbx, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardarInventarios)
                .addGap(41, 41, 41)
                .addComponent(btnEditarInventarios)
                .addGap(41, 41, 41)
                .addComponent(btnEliminarInventarios)
                .addGap(41, 41, 41)
                .addComponent(btnTraerInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        panelInventariosLayout.setVerticalGroup(
            panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventariosLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTraerInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardarInventarios)
                        .addComponent(btnEditarInventarios)
                        .addComponent(btnEliminarInventarios)))
                .addGap(25, 25, 25)
                .addGroup(panelInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarInventarioscbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxInventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarInventarios))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Inventario", panelInventarios);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nota de Venta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabel2.setText("N° Nota de Venta");

        jLabel3.setText("Cédula o Ruc del Cliente");

        jLabel4.setText("Nombre Cliente");

        jLabel6.setText("Telefono");

        lblIDproducto.setText("ID Producto");

        txtCedulaClienteVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCedulaClienteVentaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaClienteVentaFocusLost(evt);
            }
        });

        jLabel9.setText("Cantidad");

        jLabel7.setText("Fecha de Venta");

        jLabel5.setText("Dirección");

        btnAgregarNotaVents.setText("AGREGAR");
        btnAgregarNotaVents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNotaVentsActionPerformed(evt);
            }
        });

        btnBusquedaAvanzada.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBusquedaAvanzada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/buscar.png"))); // NOI18N
        btnBusquedaAvanzada.setText("Busqueda Avanzada");
        btnBusquedaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaAvanzadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblIDproducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumeroNotaVenta)
                    .addComponent(txtIDProductoVenta)
                    .addComponent(txtTelefonoClienteVenta)
                    .addComponent(txtNombreClienteVenta)
                    .addComponent(txtCedulaClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCantidadProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnAgregarNotaVents)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBusquedaAvanzada))
                    .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroNotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedulaClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIDProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIDproducto)
                                    .addComponent(jLabel9)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAgregarNotaVents)
                                .addComponent(btnBusquedaAvanzada))))
                    .addComponent(txtCantidadProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jLabel1.setText("Tipo de Pago");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Efectivo", "Crédito", " ", " " }));

        jLabel8.setText("Sub Total");

        jLabel10.setText("IVA");

        jLabel11.setText("Total");

        tablaNotaVenta.setModel(modelTableVenta);
        jScrollPane4.setViewportView(tablaNotaVenta);

        btnEliminarCamposVenta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarCamposVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/istloja/resource/img/eliminar.png"))); // NOI18N
        btnEliminarCamposVenta.setText("Eliminar");
        btnEliminarCamposVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCamposVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentasLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(txtIvaVentas)
                                    .addComponent(txtSubTotalVentas)))
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEliminarCamposVenta)
                                .addGap(122, 122, 122)))
                        .addGap(46, 46, 46)))
                .addContainerGap())
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtSubTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIvaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(btnEliminarCamposVenta)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Nota de Venta", panelVentas);

        MenuGeneral.setForeground(new java.awt.Color(60, 63, 65));
        MenuGeneral.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        MenuArchivo.setText("Archivo");
        MenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuArchivoActionPerformed(evt);
            }
        });

        MenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemGuardar.setText("Guardar");
        MenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemGuardarActionPerformed(evt);
            }
        });
        MenuArchivo.add(MenuItemGuardar);

        MenuItemEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemEliminar.setText("Eliminar");
        MenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemEliminarActionPerformed(evt);
            }
        });
        MenuArchivo.add(MenuItemEliminar);

        MenuItemEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemEditar.setText("Editar");
        MenuItemEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemEditarActionPerformed(evt);
            }
        });
        MenuArchivo.add(MenuItemEditar);

        MenuItemTraer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemTraer.setText("Traer");
        MenuItemTraer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemTraerActionPerformed(evt);
            }
        });
        MenuArchivo.add(MenuItemTraer);

        MenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemSalir.setText("Salir");
        MenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSalirActionPerformed(evt);
            }
        });
        MenuArchivo.add(MenuItemSalir);

        MenuGeneral.add(MenuArchivo);

        MenuEditar.setText("Busqueda");

        MenuItemBuscarCedula.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemBuscarCedula.setText("Buscar por Cedula");
        MenuItemBuscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemBuscarCedulaActionPerformed(evt);
            }
        });
        MenuEditar.add(MenuItemBuscarCedula);

        MenuItemBuscarApellido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemBuscarApellido.setText("Buscar por Apellido");
        MenuItemBuscarApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemBuscarApellidoActionPerformed(evt);
            }
        });
        MenuEditar.add(MenuItemBuscarApellido);

        MenuGeneral.add(MenuEditar);

        MenuAcerca.setText("Acerca de");
        MenuAcerca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuAcercaMouseClicked(evt);
            }
        });
        MenuGeneral.add(MenuAcerca);

        setJMenuBar(MenuGeneral);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuArchivoActionPerformed

    private void MenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_MenuItemSalirActionPerformed

    private void MenuItemEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemEditarActionPerformed
        // TODO add your handling code here:
       EditarPersona();
    }//GEN-LAST:event_MenuItemEditarActionPerformed

    private void MenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemEliminarActionPerformed
        // TODO add your handling code here:
        EliminarPersona();
    }//GEN-LAST:event_MenuItemEliminarActionPerformed

    private void MenuItemBuscarApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemBuscarApellidoActionPerformed
        // TODO add your handling code here:
        BuscarPersonaApellido();
    }//GEN-LAST:event_MenuItemBuscarApellidoActionPerformed

    private void MenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemGuardarActionPerformed
        // TODO add your handling code here:
        GuardarPersona();
    }//GEN-LAST:event_MenuItemGuardarActionPerformed

    private void MenuItemTraerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemTraerActionPerformed
        // TODO add your handling code here:
        TraerPersona();
    }//GEN-LAST:event_MenuItemTraerActionPerformed

    private void MenuItemBuscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemBuscarCedulaActionPerformed
        // TODO add your handling code here:
        BuscarPersonaCedula();
    }//GEN-LAST:event_MenuItemBuscarCedulaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        GuardarPersona();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        EliminarPersona();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        EditarPersona();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedoresActionPerformed
        // TODO add your handling code here:
        GuardarProveedores();               
    }//GEN-LAST:event_btnGuardarProveedoresActionPerformed

    private void btnEditarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedoresActionPerformed
        // TODO add your handling code here:
        EditarProveedor();
    }//GEN-LAST:event_btnEditarProveedoresActionPerformed

    private void btnLimpiarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProveedoresActionPerformed
        // TODO add your handling code here:
        limpiarCamposProveedor();
    }//GEN-LAST:event_btnLimpiarProveedoresActionPerformed

    private void btnEliminarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedoresActionPerformed
        // TODO add your handling code here:
        EliminarProveedores();
    }//GEN-LAST:event_btnEliminarProveedoresActionPerformed

    private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost
        // TODO add your handling code here:
        if(rdbtnCedula.isSelected()){
            if (txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo cedula no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
                txtCedula.requestFocus();
            }
            if (!utilidades.validadorDeCedula(txtCedula.getText())) {
                JOptionPane.showMessageDialog(this, "La CEDULA ingresada no es valida", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(rdbtnPasaporte.isSelected()){
            if (txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo cedula no tiene datos", "Error", JOptionPane.ERROR_MESSAGE);
                txtCedula.requestFocus();
            }
            //JOptionPane.showMessageDialog(this, "Pasaporte Ingresado","Informacion", JOptionPane.INFORMATION_MESSAGE);
            
        }    
    }//GEN-LAST:event_txtCedulaFocusLost
            
    private void txtBuscarClientescbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientescbxKeyReleased
        // TODO add your handling code here:
        buscarClienteCombo();
    }//GEN-LAST:event_txtBuscarClientescbxKeyReleased

    private void txtBuscarProveedoresCbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedoresCbxKeyReleased
        // TODO add your handling code here:
        buscarProveedorCombo();
    }//GEN-LAST:event_txtBuscarProveedoresCbxKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
            if (Character.isLetter(c)) {
                getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Solo digite números");
            } else if (txtTelefono.getText().length() >= 10) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
            if (Character.isLetter(c)) {
                getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Solo digite números");
            } else if (txtRuc.getText().length() >= 10) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtTelefonoProveedoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedoresKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
            if (Character.isLetter(c)) {
                getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Solo digite números");
            } else if (txtTelefonoProveedores.getText().length() >= 10) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
    }//GEN-LAST:event_txtTelefonoProveedoresKeyTyped

    private void txtCedulaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMouseEntered
        // TODO add your handling code here:
        txtCedula.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtCedulaMouseEntered

    private void txtCedulaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMouseExited
        // TODO add your handling code here:
        txtCedula.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtCedulaMouseExited

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Se han limpiado los campos");
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void txtNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseEntered
        // TODO add your handling code here:
        txtNombre.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtNombreMouseEntered

    private void txtNombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseExited
        // TODO add your handling code here:
        txtNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNombreMouseExited

    private void txtApellidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidoMouseEntered
        // TODO add your handling code here:
        txtApellido.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtApellidoMouseEntered

    private void txtApellidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidoMouseExited
        // TODO add your handling code here:
        txtApellido.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtApellidoMouseExited

    private void txtDireccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDireccionMouseEntered
        // TODO add your handling code here:
        txtDireccion.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtDireccionMouseEntered

    private void txtDireccionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDireccionMouseExited
        // TODO add your handling code here:
        txtDireccion.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDireccionMouseExited

    private void txtCorreoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMouseEntered
        // TODO add your handling code here:
        txtCorreo.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtCorreoMouseEntered

    private void txtCorreoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMouseExited
        // TODO add your handling code here:
        txtCorreo.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtCorreoMouseExited

    private void txtTelefonoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelefonoMouseEntered
        // TODO add your handling code here:
        txtTelefono.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_txtTelefonoMouseEntered

    private void txtTelefonoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelefonoMouseExited
        // TODO add your handling code here:
        txtTelefono.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtTelefonoMouseExited

    private void MenuAcercaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuAcercaMouseClicked
        // TODO add your handling code here:
        AcercaDe pantalla = new AcercaDe(this, true);
        pantalla.setVisible(true);
    }//GEN-LAST:event_MenuAcercaMouseClicked

    private void btnTraerInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraerInventarioActionPerformed
        // TODO add your handling code here:
        limpiarCamposInventarios();
    }//GEN-LAST:event_btnTraerInventarioActionPerformed

    private void btnEliminarInventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInventariosActionPerformed
        // TODO add your handling code here:
        EliminarInventario();
    }//GEN-LAST:event_btnEliminarInventariosActionPerformed

    private void btnEditarInventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarInventariosActionPerformed
        // TODO add your handling code here:
        EditarInventario();
    }//GEN-LAST:event_btnEditarInventariosActionPerformed

    private void btnGuardarInventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarInventariosActionPerformed
        // TODO add your handling code here:
        GuardarInventarios();
    }//GEN-LAST:event_btnGuardarInventariosActionPerformed

    private void txtBuscarInventarioscbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarInventarioscbxKeyReleased
        // TODO add your handling code here:
        buscarInventarioCombo();
    }//GEN-LAST:event_txtBuscarInventarioscbxKeyReleased

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo digite números");
        } else if (txtCodigoProducto.getText().length() >= 10) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void txtPreciosCompraSinIvaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPreciosCompraSinIvaFocusLost
        // TODO add your handling code here:
        //calcularCanitdadesInventarios();
        double valor = precioConIva(Double.parseDouble(txtPreciosCompraSinIva.getText()));
        txtPreciosCompraConIva.setText(String.valueOf(valor));
        txtPrecioMayorista.setText(String.valueOf(precioMayorista(Double.parseDouble(txtPreciosCompraConIva.getText()))));
        txtPrecioClienteFijo.setText(String.valueOf(precioClienteFijo(Double.parseDouble(txtPreciosCompraConIva.getText()))));
        txtPrecioClienteNormal.setText(String.valueOf(precioNormal(Double.parseDouble(txtPreciosCompraConIva.getText()))));
    }//GEN-LAST:event_txtPreciosCompraSinIvaFocusLost

    private void txtPreciosCompraSinIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreciosCompraSinIvaKeyReleased
        // TODO add your handling code here:
        //calcularCanitdadesInventarios();
    }//GEN-LAST:event_txtPreciosCompraSinIvaKeyReleased

    private void txtPreciosCompraSinIvaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreciosCompraSinIvaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreciosCompraSinIvaKeyPressed

    private void txtCedulaClienteVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaClienteVentaFocusLost
        // TODO add your handling code here:
        TraerDatosVenta();
    }//GEN-LAST:event_txtCedulaClienteVentaFocusLost
    List<ProductoVenta> productosVenta;
    private void btnAgregarNotaVentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNotaVentsActionPerformed
        // TODO add your handling code here:
        String idProducto = txtIDProductoVenta.getText();
        if(idProducto != null && !idProducto.isEmpty() ){
            Inventarios buscarInventariosPorCodigoVentaV = controladorInventarios.buscarInventarioPorCodigo(txtIDProductoVenta.getText());
            if(buscarInventariosPorCodigoVentaV != null){
                buscarInventariosPorCodigoVentaV.setCantidadProductosVender(Integer.parseInt(txtCantidadProductosVenta.getText()));
                ProductoVenta productoVenta = new ProductoVenta();
                productoVenta.setCantidad(buscarInventariosPorCodigoVentaV.getCantidadProductosVender());
                productoVenta.setDescripcion(buscarInventariosPorCodigoVentaV.getDescripcion());
                //double iva = Double.parseDouble(precio)*0.12;
                double valorSinIva = (Double.parseDouble(buscarInventariosPorCodigoVentaV.getPrecioClienteNormal()) / 1.12);
                productoVenta.setSubTotal(utilidades.dosDecimales(valorSinIva));
                productoVenta.setTotal(utilidades.dosDecimales(valorSinIva * buscarInventariosPorCodigoVentaV.getCantidadProductosVender()));
                productosVenta.add(productoVenta);
                calcularValoresAdicionales();
                modelTableVenta.setProductoVentas(productosVenta);
                modelTableVenta.fireTableDataChanged();
            }else{
                JOptionPane.showMessageDialog(this, "El codigo del producto no se ha encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else{
                JOptionPane.showMessageDialog(this, "El codigo del producto no se ha encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnAgregarNotaVentsActionPerformed

    private void btnBusquedaAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaActionPerformed
        // TODO add your handling code here:
        BusquedaAvanzada dialog = new BusquedaAvanzada(new javax.swing.JFrame(),
                true, modelTableInvetarios, controladorInventarios);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnBusquedaAvanzadaActionPerformed

    private void txtCedulaClienteVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaClienteVentaFocusGained
        // TODO add your handling code here:
        txtNombreClienteVenta.setText("");
        txtDireccionClienteVenta.setText("");
        txtTelefonoClienteVenta.setText("");
        txtFechaVenta.setText("");
    }//GEN-LAST:event_txtCedulaClienteVentaFocusGained

    private void btnEliminarCamposVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCamposVentaActionPerformed
        // TODO add your handling code here:
        limpiarCamposVenta();
    }//GEN-LAST:event_btnEliminarCamposVentaActionPerformed
    
//------------------------------------------------------------------------------------------------------------------------  
    
    //METODOS PARA QUE FUNCIONEN LOS BOTONES DEL MODULO PERSONAS CLIENTES
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    //METODO PARA GUARDAR UNA PERSONA
    public void GuardarPersona(){
    if(controladorPersona.buscarPersonasCedula(txtCedula.getText()) != null ){
            JOptionPane.showMessageDialog(rootPane, "La Persona con ese numero ya se encuentra registrada en el sistema");
        }else{
            Persona personaGuardar = gestionPersona.guardarEditar(false);
            if(JDateFechaNacimiento.getDate() != null){
                personaGuardar.setFechaNacimiento(JDateFechaNacimiento.getDate());
            }
            if(personaGuardar != null){    
                if (controladorPersona.CrearPersona(personaGuardar)) {
                JOptionPane.showMessageDialog(rootPane, "Persona registrada en el sistema");
                limpiar();
                actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se puede guardar la person", "Errror", JOptionPane.ERROR_MESSAGE);
                }
            
        
            }    
        }
    }        
    //METODO PARA EDITAR UNA PERSONA.
    public void EditarPersona(){
        if(personaEditar == null){
            JOptionPane.showMessageDialog(rootPane, "No hay una persona para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona personaEditarLocal = gestionPersona.guardarEditar(true);
        if(personaEditarLocal != null){
            personaEditarLocal.setIdPersona(personaEditar.getIdPersona());
            if(controladorPersona.EditarPersona(personaEditarLocal)){
                JOptionPane.showMessageDialog(rootPane, "Editado Correctamente");
                limpiar();
                personaEditar = null;
                actualizarTabla();
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se puede editar la persona");
            }
        }
    }
        
    void actualizarTabla(){
        List<Persona> tablaActualizada = controladorPersona.obtenerPersonas();
        
        modeloTablePersona.setPersonas(tablaActualizada);
        modeloTablePersona.fireTableDataChanged();
    }
    //METODO PARA TRAER UNA PERSONA.
    public void TraerPersona(){
        List<Persona> obtenerPersonas = controladorPersona.obtenerPersonas();
        personaEditar = obtenerPersonas.get(obtenerPersonas.size()-1);
        txtCedula.setText(personaEditar.getCedula());
        txtNombre.setText(personaEditar.getNombre());
        txtApellido.setText(personaEditar.getApellidos());
        txtDireccion.setText(personaEditar.getDireccion());
        txtCorreo.setText(personaEditar.getCorreo());
        txtTelefono.setText(personaEditar.getTelefono());
        cbxGenero.setSelectedItem(personaEditar.getGenero());
        JDateFechaNacimiento.setDate(personaEditar.getFechaNacimiento());
    }
        
    //METODO PARA BUSCAR A UNA PERSONA POR LA CEDULA.
    public void BuscarPersonaCedula(){
        personaEditar = controladorPersona.buscarPersonasCedula(txtCedula.getText());
        if(personaEditar != null){
            txtCedula.setText(personaEditar.getCedula());
            txtNombre.setText(personaEditar.getNombre());
            txtApellido.setText(personaEditar.getApellidos());
            txtDireccion.setText(personaEditar.getDireccion());
            txtCorreo.setText(personaEditar.getCorreo());
            txtTelefono.setText(personaEditar.getTelefono());
        
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se encontro la persona con ese numero de CEDULA en la base de Datos", "Errror", JOptionPane.ERROR_MESSAGE);
            txtCedula.setText("");
            txtCedula.requestFocus();
        }
    }
    
    //METODO PARA ELIMINAR UNA PERSONA.
    public void EliminarPersona(){
        if(personaEditar != null){
            if(controladorPersona.EliminarPersona(personaEditar)){
                JOptionPane.showMessageDialog(rootPane, "Eliminado Correctamente");
                limpiar();
                //personaEditar = null;
                 actualizarTabla();
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se pudo ELIMINAR SOURCE la Persona");
                actualizarTabla();
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "No hay persona");
            actualizarTabla();
            }
    }
    
    public void ValoresPersona(Persona p) {
        System.out.println("Persona"+ p.toString());
        
        txtCedula.setText(p.getCedula());
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellidos());
        txtDireccion.setText(p.getDireccion());
        txtCorreo.setText(p.getCorreo());
        txtTelefono.setText(p.getTelefono());
        cbxGenero.setSelectedItem(p.getGenero());
        JDateFechaNacimiento.setDate(p.getFechaNacimiento());
        personaEditar = p;
    }
    
    //METODO PARA BUSCAR A PERSONA POR APELLIDO.
    public void BuscarPersonaApellido(){
        personaEditar = controladorPersona.buscarPersonasApellido(txtApellido.getText());
        if(personaEditar != null){
            txtCedula.setText(personaEditar.getCedula());
            txtNombre.setText(personaEditar.getNombre());
            txtApellido.setText(personaEditar.getApellidos());
            txtDireccion.setText(personaEditar.getDireccion());
            txtCorreo.setText(personaEditar.getCorreo());
            txtTelefono.setText(personaEditar.getTelefono());
        
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se encontro la persona con ese APELLIDO en la base de Datos", "Errror", JOptionPane.ERROR_MESSAGE);
            txtApellido.setText(null);
            txtApellido.requestFocus();
        }
    }
    
    public void buscarClienteCombo(){
        switch(cboxElegirBuscar.getSelectedIndex()){
            case 1:
                modeloTablePersona.setPersonas(controladorPersona.buscarPersonasCedulaLista(txtBuscarClientescbx.getText()));
                modeloTablePersona.fireTableDataChanged();
                break;
            case 2:
                modeloTablePersona.setPersonas(controladorPersona.buscarPersonasNombre(txtBuscarClientescbx.getText()));
                modeloTablePersona.fireTableDataChanged();
                break;
            case 3:
                break;
        }
    }
    // </editor-fold>
    //FIN METODOS PERSONAS
    
    
    
    //METODOS PARA QUE FUNCIONES LOS BOTONES DEL MODULO PROVEEDORES
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    public void buscarProveedorCombo(){
        switch(cbxElegirProveedores.getSelectedIndex()){
            case 0:
                modeloTableProveedores.fireTableDataChanged();
                break;
            case 1:
                modeloTableProveedores.setProveedores(controladorProveedor.buscarProveedoresRucCombo(txtBuscarProveedoresCbx.getText()));
                modeloTableProveedores.fireTableDataChanged();
                break;
            case 2:
                modeloTableProveedores.setProveedores(controladorProveedor.buscarProveedoresNombreCombo(txtBuscarProveedoresCbx.getText()));
                modeloTableProveedores.fireTableDataChanged();
                break;
        }
    }
    
    public void GuardarProveedores(){  
        
        Proveedores proveedorGuardar = gestionProveedores.guardarEditarProveedores(false);
        if(JDateFechaVencimientoProveedores.getDate() != null){
            proveedorGuardar.setFechaVencimiento(JDateFechaVencimientoProveedores.getDate());
        }
        if (proveedorGuardar != null) {
            if(controladorProveedor.RegistrarProveedor(proveedorGuardar)){
                JOptionPane.showMessageDialog(rootPane, "Proveedor guardado con éxito del sitema.");
                limpiarCamposProveedor();
                actualizarTablaProveedores();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede guardar el proveedor.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    public void EditarProveedor(){
        if(proveedoresEditarEliminar == null){
            JOptionPane.showMessageDialog(rootPane, "No hay una proveedor para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Proveedores proveedoresEditarLocal = gestionProveedores.guardarEditarProveedores(true);
        if(proveedoresEditarLocal != null){
            proveedoresEditarLocal.setIdProveedores(proveedoresEditarEliminar.getIdProveedores());
            if(controladorProveedor.EditarProveedores(proveedoresEditarLocal)){
                JOptionPane.showMessageDialog(rootPane, "Editado Correctamente");
                limpiarCamposProveedor();
                proveedoresEditarEliminar = null;
                actualizarTablaProveedores();
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se puede editar el PROVEEDOR SOURCE");
            }
            
        }
    }
    public void EliminarProveedores(){
        if(proveedoresEditarEliminar != null){
            if(controladorProveedor.EliminarProveedores(proveedoresEditarEliminar)){
                JOptionPane.showMessageDialog(rootPane, "Proveedor eliminado con éxito del sitema.");
                limpiarCamposProveedor();
                modeloTableProveedores.setProveedores(controladorProveedor.obtenerProveedores());
                modeloTableProveedores.fireTableDataChanged();
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se pudo eliminar el proveedor");
                actualizarTablaProveedores();
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "No hay persona");
            actualizarTablaProveedores();
        }
        
    }
    
    public void TraerProveedor(){
        List<Proveedores> obtenerProveedores = controladorProveedor.obtenerProveedores();
        proveedoresEditarEliminar = obtenerProveedores.get(obtenerProveedores.size()-1);
        txtRuc.setText(proveedoresEditarEliminar.getRuc());
        txtRazonSocial.setText(proveedoresEditarEliminar.getRazonSocial());
        txtTipoActividad.setText(proveedoresEditarEliminar.getTipoActividad());
        txtNombreRepresentanteLegal.setText(proveedoresEditarEliminar.getNombreRepresentanteLegal());
        txtApellidoRepresentanteLegal.setText(proveedoresEditarEliminar.getApellidoRepresentanteLegal());
        txtTelefonoProveedores.setText(proveedoresEditarEliminar.getTelefonoProveedor());
        txtCorreoProveedores.setText(proveedoresEditarEliminar.getCorreoProveedores());
        txtDireccionProveedores.setText(proveedoresEditarEliminar.getDireccionProveedores());
    }
    void limpiarCamposProveedor() {
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtTipoActividad.setText("");
        txtNombreRepresentanteLegal.setText("");
        txtApellidoRepresentanteLegal.setText("");
        txtTelefonoProveedores.setText("");
        txtCorreoProveedores.setText("");
        txtDireccionProveedores.setText("");
        JDateFechaVencimientoProveedores.setDate(null);
    }
    
    void actualizarTablaProveedores(){
        List<Proveedores> tablaActualizadaProveedores = controladorProveedor.obtenerProveedores();
        
        modeloTableProveedores.setProveedores(tablaActualizadaProveedores);
        modeloTableProveedores.fireTableDataChanged();
    }
    // </editor-fold>
    //FIN DE METODO PROVEEDORES
    
    
    
    //METODOS PARA QUE FUNCIONEN LOS BOTONES DEL MODULO INVENTARIOS
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    public void buscarInventarioCombo(){
        switch(cbxInventarios.getSelectedIndex()){
            case 0:
                modelTableInvetarios.fireTableDataChanged();
                break;
            case 1:
                modelTableInvetarios.setInventarios(controladorInventarios.buscarInvetarioCodigoCombo(txtBuscarInventarioscbx.getText()));
                modelTableInvetarios.fireTableDataChanged();
                break;
            case 2:
                modelTableInvetarios.setInventarios(controladorInventarios.buscarInvetarioDescripcionCombo(txtBuscarInventarioscbx.getText()));
                modelTableInvetarios.fireTableDataChanged();
                break;
        }
    }
    public void GuardarInventarios(){
        Inventarios inventariosGuardar = gestionInventarios.guardarEditarInventario(false);
        if(JDateFechaVencimientoInventarios.getDate() != null){
            inventariosGuardar.setFechaVencimiento(JDateFechaVencimientoInventarios.getDate());
        }
        if (inventariosGuardar != null) {
            if(controladorInventarios.CrearInventario(inventariosGuardar)){
                JOptionPane.showMessageDialog(rootPane, "Inventario guardado con éxito del sitema.");
                limpiarCamposInventarios();
                actualizarTablaInventarios();
            }else {
            JOptionPane.showMessageDialog(rootPane, "No se puede guardar el proveedor.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }
    
    public void EditarInventario(){
        if(inventariosEditarEliminar == null){
            JOptionPane.showMessageDialog(rootPane, "No hay un Inventario para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Inventarios inventariosEditarLocal = gestionInventarios.guardarEditarInventario(true);//ME FALTA EL METODO PARA LOS CAMPOS
        if(inventariosEditarLocal != null){
            inventariosEditarLocal.setIdInventario(inventariosEditarEliminar.getIdInventario());
            if(controladorInventarios.EditarInventarios(inventariosEditarLocal)){
                JOptionPane.showMessageDialog(rootPane, "Editado Correctamente");
                limpiarCamposInventarios();
                inventariosEditarEliminar = null;
                actualizarTablaInventarios();
            }
        }
    }
    public void EliminarInventario(){
        if(inventariosEditarEliminar != null){
            if(controladorInventarios.EliminarInventarios(inventariosEditarEliminar)){
                JOptionPane.showMessageDialog(rootPane, "Eliminado Correctamente");
                limpiarCamposInventarios();
                actualizarTablaInventarios();
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se pudo Eliminar SOURCE el inventario");
                actualizarTablaInventarios();
            }
        }
    }
    
    void actualizarTablaInventarios(){
        List<Inventarios> tablaActualizadaInventarios = controladorInventarios.obtenerInventarios();
        
        modelTableInvetarios.setInventarios(tablaActualizadaInventarios);
        modelTableInvetarios.fireTableDataChanged();
    }
    
    //METODO PARA TRAER UN INVENTARIO.
    public void TraerInventario(){
        List<Inventarios> obtenerInventarios = controladorInventarios.obtenerInventarios();
        inventariosEditarEliminar = obtenerInventarios.get(obtenerInventarios.size()-1);
        txtCodigoProducto.setText(inventariosEditarEliminar.getCodigoProducto());
        txtCantidadProductos.setText(inventariosEditarEliminar.getCantidadProductos());
        txtDescripcion.setText(inventariosEditarEliminar.getDescripcion());
        txtPreciosCompraSinIva.setText(inventariosEditarEliminar.getPreciosCompra_sinIva());
        txtPreciosCompraConIva.setText(inventariosEditarEliminar.getPreciosCompra_conIva());
        txtPrecioMayorista.setText(inventariosEditarEliminar.getPrecioMayorista());
        txtPrecioClienteFijo.setText(inventariosEditarEliminar.getPrecioClienteFijo());
        txtPrecioClienteNormal.setText(inventariosEditarEliminar.getPrecioClienteNormal());
    }
    void limpiarCamposInventarios() {
        txtCodigoProducto.setText("");
        txtCantidadProductos.setText("");
        txtDescripcion.setText("");
        txtPreciosCompraSinIva.setText("");
        txtPreciosCompraConIva.setText("");
        txtPrecioMayorista.setText("");
        txtPrecioClienteFijo.setText("");
        txtPrecioClienteNormal.setText("");
        JDateFechaVencimientoInventarios.setDate(null);
    }
    
    public double precioConIva(double precioSinIva) {
        return utilidades.dosDecimales((precioSinIva * 0.12) + precioSinIva);
    }

    public double precioMayorista(double precioSinIva) {
        return utilidades.dosDecimales((precioSinIva * 0.10) + precioSinIva);
    }

    public double precioClienteFijo(double precioSinIva) {
        return utilidades.dosDecimales((precioSinIva * 0.12) + precioSinIva);
    }

    public double precioNormal(double precioSinIva) {
        return utilidades.dosDecimales((precioSinIva * 0.15) + precioSinIva);
    }

    private Inventarios valoresActualizarInventario(Inventarios i) {
        i.setCodigoProducto(txtCodigoProducto.getText());
        i.setCantidadProductos(txtCantidadProductos.getText());
        i.setDescripcion(txtDescripcion.getText());
        i.setPreciosCompra_sinIva(txtPreciosCompraSinIva.getText());
        i.setPreciosCompra_conIva(txtPreciosCompraConIva.getText());
        i.setPrecioMayorista(txtPrecioMayorista.getText());
        i.setPrecioClienteFijo(txtPrecioClienteFijo.getText());
        i.setPrecioClienteNormal(txtPrecioClienteNormal.getText());
        return i;
    }
    // </editor-fold>
    //FIN METODOS INVENTARIOS
    
    
    
    //METODOS PARA LA FUNCION DEL MODULO VENTAS
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    
    //METODO PARA TRAER LOS DATOS DEL CLIENTE
    public void TraerDatosVenta(){
        personaEditar = controladorPersona.buscarPersonasCedula(txtCedulaClienteVenta.getText());
        if(personaEditar != null){
            txtCedulaClienteVenta.setText(personaEditar.getCedula());
            txtNombreClienteVenta.setText(personaEditar.getNombre()+" "+personaEditar.getApellidos());
            txtDireccionClienteVenta.setText(personaEditar.getDireccion());
            txtTelefonoClienteVenta.setText(personaEditar.getTelefono());
            txtFechaVenta.setText(utilidades.devolverFecha(new Date()));
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se encontro la persona con ese numero de CEDULA en la base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            txtCedulaClienteVenta.setText("");
            txtCedulaClienteVenta.requestFocus();
        }
    }
    //FIN TRAER
    
    public void TraerDatosVentaProducto(){
        inventariosEditarEliminar = controladorInventarios.buscarInventarioPorCodigo(txtIDProductoVenta.getText());
        if(inventariosEditarEliminar != null){
            txtIDProductoVenta.setText(inventariosEditarEliminar.getCodigoProducto());
            txtCantidadProductosVenta.setText(inventariosEditarEliminar.getCantidadProductos());
        }else{
            JOptionPane.showMessageDialog(rootPane, "No se encontro la persona con ese numero de CEDULA en la base de Datos", "Errror", JOptionPane.ERROR_MESSAGE);
            txtIDProductoVenta.setText("");
            txtIDProductoVenta.requestFocus();
        }
    }
    
    
    
    
    
    //BLOQUEAR CAMPOS DE CLIENTES.
    void BloquearCamposClienteVenta(){
        txtNombreClienteVenta.setEditable(false);
        txtDireccionClienteVenta.setEditable(false);
        txtTelefonoClienteVenta.setEditable(false);
        txtFechaVenta.setEditable(false);
    }
    public void limpiarCamposVenta(){
         txtNumeroNotaVenta.setText("");
         txtCedulaClienteVenta.setText("");
         txtNombreClienteVenta.setText("");
         txtTelefonoClienteVenta.setText("");
         txtIDProductoVenta.setText("");
         txtDireccionClienteVenta.setText("");
         txtFechaVenta.setText("");
         txtCantidadProductosVenta.setText("");
         txtIvaVentas.setText("");
         txtSubTotalVentas.setText("");
         txtTotalVentas.setText("");
         productosVenta.removeAll(productosVenta);
         tablaNotaVenta.updateUI();
     }
    
    // </editor-fold>
    //FIN DE METODOS VENTAS
    
//------------------------------------------------------------------------------------------------------------------------
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionPersonaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionPersonaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionPersonaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionPersonaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionPersonaV1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateFechaNacimiento;
    private com.toedter.calendar.JDateChooser JDateFechaVencimientoInventarios;
    private com.toedter.calendar.JDateChooser JDateFechaVencimientoProveedores;
    private javax.swing.JMenu MenuAcerca;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuEditar;
    private javax.swing.JMenuBar MenuGeneral;
    private javax.swing.JMenuItem MenuItemBuscarApellido;
    private javax.swing.JMenuItem MenuItemBuscarCedula;
    private javax.swing.JMenuItem MenuItemEditar;
    private javax.swing.JMenuItem MenuItemEliminar;
    private javax.swing.JMenuItem MenuItemGuardar;
    private javax.swing.JMenuItem MenuItemSalir;
    private javax.swing.JMenuItem MenuItemTraer;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JButton btnAgregarNotaVents;
    private javax.swing.JButton btnBusquedaAvanzada;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarInventarios;
    private javax.swing.JButton btnEditarProveedores;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCamposVenta;
    private javax.swing.JButton btnEliminarInventarios;
    private javax.swing.JButton btnEliminarProveedores;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarInventarios;
    private javax.swing.JButton btnGuardarProveedores;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarProveedores;
    private javax.swing.JButton btnTraerInventario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboxElegirBuscar;
    private javax.swing.JComboBox<String> cbxElegirProveedores;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> cbxInventarios;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblApellidoRepresentanteLegal;
    private javax.swing.JLabel lblBuscarClientecbx;
    private javax.swing.JLabel lblBuscarInventarios;
    private javax.swing.JLabel lblBuscarProveedorescbx;
    private javax.swing.JLabel lblCantidadProductos;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCodigoProducto;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCorreoProveedores;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionProveedores;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaVencimientoInventarios;
    private javax.swing.JLabel lblFechaVencimientoProveedores;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblIDproducto;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreRepresentanteLegal;
    private javax.swing.JLabel lblPrecioClienteFijo;
    private javax.swing.JLabel lblPrecioClienteNormal;
    private javax.swing.JLabel lblPrecioMayorista;
    private javax.swing.JLabel lblPreciosCompraConIva;
    private javax.swing.JLabel lblPreciosCompraSinIva;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoProveedores;
    private javax.swing.JLabel lblTipoActividad;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCuerpoRegistro;
    private javax.swing.JPanel panelInventarios;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JRadioButton rdbtnCedula;
    private javax.swing.JRadioButton rdbtnPasaporte;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaInventarios;
    private javax.swing.JTable tablaNotaVenta;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoRepresentanteLegal;
    private javax.swing.JTextField txtBuscarClientescbx;
    private javax.swing.JTextField txtBuscarInventarioscbx;
    private javax.swing.JTextField txtBuscarProveedoresCbx;
    private javax.swing.JTextField txtCantidadProductos;
    private javax.swing.JTextField txtCantidadProductosVenta;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCedulaClienteVenta;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreoProveedores;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionProveedores;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtIDProductoVenta;
    private javax.swing.JTextField txtIvaVentas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreClienteVenta;
    private javax.swing.JTextField txtNombreRepresentanteLegal;
    private javax.swing.JTextField txtNumeroNotaVenta;
    private javax.swing.JTextField txtPrecioClienteFijo;
    private javax.swing.JTextField txtPrecioClienteNormal;
    private javax.swing.JTextField txtPrecioMayorista;
    private javax.swing.JTextField txtPreciosCompraConIva;
    private javax.swing.JTextField txtPreciosCompraSinIva;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtSubTotalVentas;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoClienteVenta;
    private javax.swing.JTextField txtTelefonoProveedores;
    private javax.swing.JTextField txtTipoActividad;
    private javax.swing.JTextField txtTotalVentas;
    // End of variables declaration//GEN-END:variables

    //SETEAR EN LA CAJA
    @Override
    public void clickPersona(Persona p) {
        //System.out.println("Persona"+ p.toString());
        txtCedula.setText(p.getCedula());
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellidos());
        txtDireccion.setText(p.getDireccion());
        txtCorreo.setText(p.getCorreo());
        txtTelefono.setText(p.getTelefono());
        cbxGenero.setSelectedItem(p.getGenero());
        JDateFechaNacimiento.setDate(p.getFechaNacimiento());
        personaEditar = p;
    }
    
    //SETEAR EN LA CAJA
    @Override
    public void clickProveedores(Proveedores pr) {
        System.out.println("Proveedores"+ pr.toString());
        txtRuc.setText(pr.getRuc());
        txtRazonSocial.setText(pr.getRazonSocial());
        txtTipoActividad.setText(pr.getTipoActividad());
        txtNombreRepresentanteLegal.setText(pr.getNombreRepresentanteLegal());
        txtApellidoRepresentanteLegal.setText(pr.getApellidoRepresentanteLegal());
        txtTelefonoProveedores.setText(pr.getTelefonoProveedor());
        txtCorreoProveedores.setText(pr.getCorreoProveedores());
        txtDireccionProveedores.setText(pr.getDireccionProveedores());
        JDateFechaVencimientoProveedores.setDate(pr.getFechaVencimiento());
        proveedoresEditarEliminar = pr;
    }

    @Override
    public void clickInventarios(Inventarios i) {
        System.out.println("Inventarios"+i.toString());
        txtCodigoProducto.setText(i.getCodigoProducto());
        txtCantidadProductos.setText(String.valueOf(i.getCantidadProductos()));
        txtDescripcion.setText(i.getDescripcion());
        txtPreciosCompraSinIva.setText(i.getPreciosCompra_sinIva());
        txtPreciosCompraConIva.setText(i.getPreciosCompra_conIva());
        txtPrecioMayorista.setText(i.getPrecioMayorista());
        txtPrecioClienteFijo.setText(i.getPrecioClienteFijo());
        txtPrecioClienteNormal.setText(i.getPrecioClienteNormal());
        JDateFechaVencimientoInventarios.setDate(i.getFechaVencimiento());
        txtIDProductoVenta.setText(i.getCodigoProducto());
        inventariosEditarEliminar = i;
    }
    
    int contadorClick = 0;  
    boolean ejecutar;
    @Override
    public void clickProductoVender(ProductoVenta pv){
        ejecutar = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ejecutar){
                    try {
                        Thread.sleep(1000);
                        contadorClick = 0;
                        ejecutar = false;
                        System.out.println("Reinicia CLICK");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }).start();
        contadorClick++;
        if(contadorClick == 2){
            if(JOptionPane.showConfirmDialog(rootPane, "Se eliminara el Item, ¿Desea Continuar?",
                    "Eliminar Producto", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                productosVenta.remove(pv);
                calcularValoresAdicionales();
                modelTableVenta.setProductoVentas(productosVenta);
                modelTableVenta.fireTableDataChanged();
            }
            contadorClick = 0;
            ejecutar = false;
        }
        System.out.println("contadorClick" +contadorClick);
    }
}
