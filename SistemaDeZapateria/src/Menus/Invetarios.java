/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import Inicio.MenuInicioA;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pkgConectar.conectar;


public class Invetarios extends javax.swing.JFrame {

    /**
     * Creates new form Invetarios
     */
    DefaultTableModel model;
    DefaultTableModel model2;
    private boolean Editando = false;
    private boolean guardando = false;
    private String tipo = "", idAux = "";

    public Invetarios() {
        initComponents();
        setLocationRelativeTo(null);
        Bloquear();
        cargar();
    }

    public Invetarios(String tipo) {
        initComponents();
        this.tipo = tipo;
        setLocationRelativeTo(null);
        Bloquear();
        cargar();
    }

    public void Bloquear() {
        txtCodigo.setEnabled(true);

        TFprecio.setVisible(false);
        TFmarca.setVisible(false);
        TAdescripcion.setVisible(false);
        TFcodProducto.setVisible(false);
        TFproveedor.setVisible(false);

        lblPrecio.setVisible(false);
        lblMarca.setVisible(false);
        lblDescrip.setVisible(false);
        lblCodeProd.setVisible(false);
        lblProv.setVisible(false);

        Bact.setVisible(false);
        Bnuevo.setVisible(true);
        Beliminar.setVisible(true);
        BgNuevo.setVisible(false);
    }

    public void Desbloquear() {
        txtCodigo.setEnabled(false);

        TFprecio.setVisible(true);
        TFmarca.setVisible(true);
        TAdescripcion.setVisible(true);
        TFcodProducto.setVisible(true);
        TFproveedor.setVisible(true);

        lblPrecio.setVisible(true);
        lblMarca.setVisible(true);
        lblDescrip.setVisible(true);
        lblCodeProd.setVisible(true);
        lblProv.setVisible(true);

        Bact.setVisible(true);
        Bnuevo.setVisible(false);
        Beliminar.setVisible(false);
    }

    public void Limpiar() {
        txtCodigo.setText("");

        TFprecio.setText("");
        TFmarca.setText("");
        TAdescripcion.setText("");
        TFcodProducto.setText("");
        TFproveedor.setText("");
    }

    void mostrar_guardar() {
        if (BgNuevo.isVisible() == true) {
            BgNuevo.setVisible(false);
        } else if (BgNuevo.isVisible() == false) {
            BgNuevo.setVisible(true);
        }
    }
    public conectar cc = new conectar();
    public Connection cn = cc.conexion();

    void cargar() {
        String[] titulos = {"Producto", "Descripcion", "Precio", "Marca", "idProveedor"};
        String[] registros = new String[5];
        String sql = "SELECT * FROM producto";
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idProducto");
                registros[1] = rs.getString("Descripcion");
                registros[2] = rs.getString("Precio");
                registros[3] = rs.getString("Marca");
                registros[4] = rs.getString("idProveedor");
                model.addRow(registros);
            }
            t_datos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void Buscar(String valor) {
        String[] titulos = {"Producto", "Descripcion", "Precio", "Marca", "idProveedor"};
        String[] registros = new String[5];
        String sql = "SELECT * FROM producto where idProducto like'%" + valor + "%'";
        model = new DefaultTableModel(null, titulos);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idProducto");
                registros[1] = rs.getString("Descripcion");
                registros[2] = rs.getString("Precio");
                registros[3] = rs.getString("Marca");
                registros[4] = rs.getString("idProveedor");
                model.addRow(registros);
            }
            t_datos.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error detectado : \n" + ex);
        }
    }

    public int modificar() {
        int filaseleccionada;

        try {

            filaseleccionada = t_datos.getSelectedRow();

            if (filaseleccionada == -1) {

                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
                Editando = false;
                return 0;

            } else {
                String id = (String) model.getValueAt(filaseleccionada, 0);
                idAux = id;
                String desc = (String) model.getValueAt(filaseleccionada, 1);
                String precio = (String) model.getValueAt(filaseleccionada, 2);
                String marca = (String) model.getValueAt(filaseleccionada, 3);
                String prov = (String) model.getValueAt(filaseleccionada, 4);
                txtCodigo.setText("");
                TFprecio.setText(precio);
                TFmarca.setText(marca);
                TAdescripcion.setText(desc);
                TFcodProducto.setText(id);
                TFproveedor.setText(prov);
                return 1;
            }

        } catch (HeadlessException ex) {

            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void actualizar() {
        String sql = "UPDATE producto SET idProducto = '" + TFcodProducto.getText() + "' ,"
                + "Descripcion = '" + TAdescripcion.getText() + "' ,"
                + "Precio = '" + TFprecio.getText() + "' ,"
                + "Marca = '" + TFmarca.getText() + "' ,"
                + "idProveedor = '" + TFproveedor.getText() + "' "
                + "WHERE idProducto = '" + idAux + "'";

        try {
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Actualizacion satisfactoriamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + ex);
        }
    }

    public void borrar() {
        int filaseleccionada;

        filaseleccionada = t_datos.getSelectedRow();

        if (filaseleccionada == -1) {

            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            Editando = false;

        } else {
            String sql = "DELETE FROM producto WHERE idProducto='" + Integer.valueOf((String) model.getValueAt(filaseleccionada, 0)) + "'";

            try {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Actualizacion satisfactoriamente");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + ex);
            }
        }
    }

    public boolean nuevo() {
        if (TFcodProducto.getText().equals("") || TAdescripcion.getText().equals("") || TFprecio.getText().equals("") || TFmarca.getText().equals("") || TFproveedor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No deje campos vacios");
            return false;
        } else {
            String a = "INSERT INTO producto(idProducto,Descripcion,Precio,Marca,idProveedor)VALUES (?,?,?,?,?)";
            try {
                PreparedStatement b = cn.prepareStatement(a);
                b.setString(1, TFcodProducto.getText());
                b.setString(2, TAdescripcion.getText());
                b.setString(3, TFprecio.getText());
                b.setString(4, TFmarca.getText());
                b.setString(5, TFproveedor.getText());
                b.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro del producto guardado satisfactoriamente");
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                return false;
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
        lblImagen = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        lblDescrip = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        TFprecio = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        TFmarca = new javax.swing.JTextField();
        TFcodProducto = new javax.swing.JTextField();
        TFproveedor = new javax.swing.JTextField();
        lblCodeProd = new javax.swing.JLabel();
        lblProv = new javax.swing.JLabel();
        Beditar = new javax.swing.JButton();
        TAdescripcion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        Bact = new javax.swing.JButton();
        Beliminar = new javax.swing.JButton();
        Bnuevo = new javax.swing.JButton();
        BgNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INVENTARIOS");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        lblImagen.setFont(new java.awt.Font("Arial Black", 3, 14)); // NOI18N
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menus/SalidaAlmacen.png"))); // NOI18N
        lblImagen.setText("Inventario");

        lblCodigo.setText("Codigo:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        t_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_datos.setFocusable(false);
        jScrollPane1.setViewportView(t_datos);

        lblDescrip.setText("Descripcion");

        lblPrecio.setText("Precio");

        lblMarca.setText("Marca");

        lblCodeProd.setText("Codigo Producto");

        lblProv.setText("Proveedor");

        Beditar.setText("Editar Seleccionado");
        Beditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BeditarMouseClicked(evt);
            }
        });

        jButton2.setText("Volver");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        Bact.setText("Actualizar registro");
        Bact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BactMouseClicked(evt);
            }
        });

        Beliminar.setText("Eliminar registro");
        Beliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BeliminarMouseClicked(evt);
            }
        });

        Bnuevo.setText("Nuevo registro");
        Bnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BnuevoMouseClicked(evt);
            }
        });

        BgNuevo.setText("Guardar nuevo registro");
        BgNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BgNuevoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addGap(18, 18, 18)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(113, 113, 113)
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarca)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPrecio)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TFmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblDescrip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TAdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodeProd, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblProv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TFproveedor)
                            .addComponent(TFcodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Beditar)
                        .addGap(35, 35, 35)
                        .addComponent(Bact, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(Beliminar)
                        .addGap(43, 43, 43)
                        .addComponent(Bnuevo)
                        .addGap(18, 18, 18)
                        .addComponent(BgNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Beditar)
                    .addComponent(Beliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BgNuevo)
                    .addComponent(Bact))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFcodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodeProd)
                            .addComponent(lblDescrip)
                            .addComponent(TAdescripcion))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProv)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrecio)
                            .addComponent(TFprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarca))))
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        Buscar(txtCodigo.getText());
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void BeditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BeditarMouseClicked
        Editando = !Editando;
        if (Editando == false) {
            Beditar.setText("Editar Seleccionado");
            Limpiar();
            Bloquear();
        } else if (Editando == true) {
            //modificar();
            if (modificar() == 1) {
                Beditar.setText("Cancelar");
                Desbloquear();
            }
        }
    }//GEN-LAST:event_BeditarMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        new MenuInicioA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void BactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactMouseClicked
        actualizar();
        Beditar.setText("Editar Seleccionado");
        Bloquear();
        Buscar("");
    }//GEN-LAST:event_BactMouseClicked

    private void BeliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BeliminarMouseClicked
        borrar();
        Buscar("");
    }//GEN-LAST:event_BeliminarMouseClicked

    private void BnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BnuevoMouseClicked

        Limpiar();
        Desbloquear();
        Beditar.setVisible(false);
        Bact.setVisible(false);
        BgNuevo.setVisible(true);

    }//GEN-LAST:event_BnuevoMouseClicked

    private void BgNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BgNuevoMouseClicked
        if (nuevo()) {
            Bloquear();

            Beditar.setVisible(true);
            Bact.setVisible(true);
            Buscar("");
        }
    }//GEN-LAST:event_BgNuevoMouseClicked

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
            java.util.logging.Logger.getLogger(Invetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invetarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bact;
    private javax.swing.JButton Beditar;
    private javax.swing.JButton Beliminar;
    private javax.swing.JButton BgNuevo;
    private javax.swing.JButton Bnuevo;
    private javax.swing.JTextField TAdescripcion;
    private javax.swing.JTextField TFcodProducto;
    private javax.swing.JTextField TFmarca;
    private javax.swing.JTextField TFprecio;
    private javax.swing.JTextField TFproveedor;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodeProd;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProv;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
