/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import Inicio.MenuInicioA;
import Inicio.MenuInicioE;
import Login.nuevoUsuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pkgConectar.conectar;


public class EditarEmpleados extends javax.swing.JFrame {

    /**
     * Creates new form Ventas
     */
    private String tipoU;

    public EditarEmpleados() {
        initComponents();
        cargar();
        Bloquear();
        setLocationRelativeTo(null);
    }

    public EditarEmpleados(String tipoU) {
        initComponents();
        this.tipoU = tipoU;
        cargar();
        Bloquear();
        setLocationRelativeTo(null);
    }

    public String id, nom, ape, gen, F_N, tel, dir, sueldo;

    DefaultTableModel model;

    conectar cc = new conectar();
    Connection cn = cc.conexion();

    public void Bloquear() {
        txtCodigo.setVisible(false);
        txtNombre.setVisible(false);
        txtFNacimiento.setVisible(false);
        txtDireccion.setVisible(false);
        txtTelefono.setVisible(false);
        txtSueldo.setVisible(false);
        txtApellido.setVisible(false);
        comboSexo.setVisible(false);

        lblCodigo.setVisible(false);
        lblNombre.setVisible(false);
        lblFNacimiento.setVisible(false);
        lblDireccion.setVisible(false);
        lblTelefono.setVisible(false);
        lblSueldo.setVisible(false);
        lblApellido.setVisible(false);
        lblSexo.setVisible(false);

        btnCancelar.setVisible(false);
        BguardarNreg.setVisible(false);
        btnNuevo.setEnabled(true);
        Bborrar.setVisible(true);
    }

    public void Desbloquear() {
        txtCodigo.setVisible(true);
        txtNombre.setVisible(true);
        txtFNacimiento.setVisible(true);
        txtDireccion.setVisible(true);
        txtTelefono.setVisible(true);
        txtSueldo.setVisible(true);
        txtApellido.setVisible(true);
        comboSexo.setVisible(true);
        btnCancelar.setVisible(true);

        lblCodigo.setVisible(true);
        lblNombre.setVisible(true);
        lblFNacimiento.setVisible(true);
        lblDireccion.setVisible(true);
        lblTelefono.setVisible(true);
        lblSueldo.setVisible(true);
        lblApellido.setVisible(true);
        lblSexo.setVisible(true);

        btnCancelar.setVisible(true);
        btnNuevo.setEnabled(false);
    }

    public void Limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtFNacimiento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtSueldo.setText("");
        txtApellido.setText("");
        comboSexo.setSelectedIndex(0);
    }

    void cargar() {
        String[] titulos = {"id", "Nombre", "Apellidos", "Genero", "Fecha de nacimiento", "Telefono", "Direccion", "Sueldo"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM empleados";
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idEmpleados");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellidos");
                registros[3] = rs.getString("Genero");
                registros[4] = rs.getString("Fecha_Nac");
                registros[5] = rs.getString("Telefono");
                registros[6] = rs.getString("Direccion");
                registros[7] = rs.getString("sueldo");
                model.addRow(registros);
            }
            t_empleados.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void Buscar(String valor) {
        String[] titulos = {"id", "Nombre", "Apellidos", "Genero", "Fecha de nacimiento", "Telefono", "Direccion", "Sueldo"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM empleados where idEmpleados like'%" + valor + "%'";
        model = new DefaultTableModel(null, titulos);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idEmpleados");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellidos");
                registros[3] = rs.getString("Genero");
                registros[4] = rs.getString("Fecha_Nac");
                registros[5] = rs.getString("Telefono");
                registros[6] = rs.getString("Direccion");
                registros[7] = rs.getString("sueldo");
                model.addRow(registros);
            }
            t_empleados.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error detectado : \n" + ex);
        }
    }

    void guardar() {
        // TODO add your handling code here:
        if (txtCodigo.getText().equals("") || txtNombre.getText().equals("") || txtFNacimiento.getText().equals("") || txtDireccion.getText().equals("") || txtTelefono.getText().equals("") || txtSueldo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Algunos campos estan vacios");

        } else {
            //se PUEDE REGISTRAR EN LA BASE DE DATOS
            String sql = "";
            id = txtCodigo.getText();
            nom = txtNombre.getText();
            ape = txtApellido.getText();
            gen = (String) comboSexo.getSelectedItem();
            F_N = txtFNacimiento.getText();
            tel = txtTelefono.getText();
            dir = txtDireccion.getText();
            sueldo = txtSueldo.getText();
            sql = "INSERT INTO empleados (idEmpleados,Nombre,Apellidos,Genero,Fecha_Nac,Telefono,Direccion,sueldo) VALUES (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, id);
                psd.setString(2, nom);
                psd.setString(3, ape);
                psd.setString(4, gen);
                psd.setString(5, F_N);
                psd.setString(6, tel);
                psd.setString(7, dir);
                psd.setString(8, sueldo);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "El empleado se registro con exito");
                    Buscar("");
                    Limpiar();
                    new nuevoUsuario(id).setVisible(true);
                    Bloquear();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void borrar() {
        int filaseleccionada;
        String sql;

        filaseleccionada = t_empleados.getSelectedRow();

        if (filaseleccionada == -1) {

            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");

        } else {
            sql = "DELETE FROM vendedor WHERE idEmpleados='" + (String) model.getValueAt(filaseleccionada, 0) + "'";

            try {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + ex);
            }
            sql = "DELETE FROM administrador WHERE idEmpleados='" + (String) model.getValueAt(filaseleccionada, 0) + "'";
            try {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + ex);
            }
            sql = "DELETE FROM empleados WHERE idEmpleados='" + (String) model.getValueAt(filaseleccionada, 0) + "'";
            try {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Se ha borrado el registro del empleado y el usuario satisfactoriamente");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + ex);
            }
            
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

        jPanel1 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFNacimiento = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtFNacimiento = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<>();
        lblSueldo = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        txtApellido = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        BguardarNreg = new javax.swing.JButton();
        Bborrar = new javax.swing.JButton();
        lblempleado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_empleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EMPLEADOS");

        lblCodigo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblCodigo.setText("Codigo:");

        lblNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        lblFNacimiento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblFNacimiento.setText("Fecha de Nacimiento:");

        lblSexo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblSexo.setText("Sexo:");

        lblDireccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblDireccion.setText("Direccion:");

        lblTelefono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono:");

        txtCodigo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtCodigo.setToolTipText("Agrega y busca un empleado");

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtFNacimiento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtTelefono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        comboSexo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));

        lblSueldo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblSueldo.setText("Sueldo:");

        txtSueldo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnCancelar.setText("Cancelar nuevo registro");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        btnNuevo.setText("Nuevo registro");
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoMouseClicked(evt);
            }
        });

        txtApellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblApellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblApellido.setText("Apellidos");

        BguardarNreg.setText("Guardar nuevo registro");
        BguardarNreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BguardarNregMouseClicked(evt);
            }
        });

        Bborrar.setText("Borrar registro seleccionado");
        Bborrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BborrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblFNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFNacimiento))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblCodigo)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblApellido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblDireccion)
                                .addGap(18, 18, 18)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblSueldo)
                                    .addGap(30, 30, 30)
                                    .addComponent(txtSueldo))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblTelefono)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblSexo)
                        .addGap(18, 18, 18)
                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(BguardarNreg)
                        .addGap(18, 18, 18)
                        .addComponent(Bborrar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnCancelar)
                    .addComponent(BguardarNreg)
                    .addComponent(Bborrar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellido))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFNacimiento)
                            .addComponent(txtFNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDireccion)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSexo)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefono)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSueldo)
                            .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56))
        );

        lblempleado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblempleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menus/empleado.png"))); // NOI18N
        lblempleado.setText("Editar Empleado");

        t_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(t_empleados);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Buscar:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setText("Volver");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(lblempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblempleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        //Guarda los cambios si midifico algo (Ya no :P)
        Limpiar();
        Bloquear();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseClicked
        // TODO add your handling code here:
        BguardarNreg.setVisible(true);
        Bborrar.setVisible(false);
        Desbloquear();
    }//GEN-LAST:event_btnNuevoMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        Buscar(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void BguardarNregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BguardarNregMouseClicked
        guardar();
        BguardarNreg.setVisible(false);
        Bborrar.setVisible(true);
    }//GEN-LAST:event_BguardarNregMouseClicked

    private void BborrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BborrarMouseClicked
        borrar();
        Buscar("");
    }//GEN-LAST:event_BborrarMouseClicked

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        new MenuInicioA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseReleased

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
            java.util.logging.Logger.getLogger(EditarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bborrar;
    private javax.swing.JButton BguardarNreg;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSueldo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblempleado;
    private javax.swing.JTable t_empleados;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
