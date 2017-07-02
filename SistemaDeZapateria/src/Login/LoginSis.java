package Login;

import Inicio.MenuInicioA;
import Inicio.MenuInicioE;
import Menus.Invetarios;
import Menus.Ventas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkgConectar.conectar;


public class LoginSis extends javax.swing.JFrame {

    private String usuario, contraseña, tipo;

    /**
     * Creates new form LoginSis
     */
    public LoginSis() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void iniciarSesion() {
        // Entrada de datos
        usuario = txtUsuario.getText();
        contraseña = txtContrasena.getText();
        switch (opcion.getSelectedIndex()) {
            case 0:
                tipo = "administrador";
                String sql1 = "SELECT * FROM administrador WHERE usrAdministrador='" + usuario + "' && password='" + contraseña + "'";
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql1);
                    if (rs.first()) {
                        new Progreso(tipo).setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "El usuario y/o contraseña ingresado no son correctos", "ERROR", HEIGHT, null);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "El usuario y/o contraseña ingresado no son correctos", "ERROR", HEIGHT, null);
                }
                break;

            case 1:
                //Vendedor
                tipo = "empleado";
                String sql = "SELECT * FROM vendedor WHERE usrVendedor='" + usuario + "' && password='" + contraseña + "'";
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.first()) {
                        new Progreso(tipo).setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "El usuario y/o contraseña ingresado no son correctos", "ERROR", HEIGHT, null);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "El usuario y/o contraseña ingresado no son correctos", "ERROR", HEIGHT, null);
                }
                break;
            default:
                break;

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

        jPanel = new javax.swing.JPanel();
        lblIconLlave = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        btnok = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        lblTipoUsuario = new javax.swing.JLabel();
        opcion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INICIAR SESION");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel.setBackground(new java.awt.Color(0, 102, 102));

        lblIconLlave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/login.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, new java.awt.Color(153, 153, 153)), "Acceso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblContrasena.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblContrasena.setText("Contraseña:");

        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUsuario.setToolTipText("Teclea tu nombre de usuario para ingresar");
        txtUsuario.setPreferredSize(new java.awt.Dimension(9, 25));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtContrasena.setToolTipText("Teclea tu contraseña para ingresar");
        txtContrasena.setPreferredSize(new java.awt.Dimension(9, 25));
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyPressed(evt);
            }
        });

        btnok.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnok.setText("Iniciar");
        btnok.setToolTipText("Aceptar");
        btnok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnokMouseClicked(evt);
            }
        });
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Cancelar");
        btncancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancelarMouseClicked(evt);
            }
        });

        lblTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipoUsuario.setText("Tipo de Usuario:");

        opcion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "VENDEDOR" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnok)
                        .addGap(18, 18, 18)
                        .addComponent(btncancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoUsuario)
                            .addComponent(lblUsuario)
                            .addComponent(lblContrasena))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(opcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoUsuario)
                    .addComponent(opcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnok))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("DejaVu Serif", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ZAPATERIA");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(lblIconLlave))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconLlave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 570, 480));

        getAccessibleContext().setAccessibleName("ZAPATERIA");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtContrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyPressed

//        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
//            new Progreso().setVisible(true);
//            dispose();
//        }
    }//GEN-LAST:event_txtContrasenaKeyPressed

    private void btncancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelarMouseClicked
        // TODO add your handling code here:
        txtUsuario.setText("");
        txtContrasena.setText("");

    }//GEN-LAST:event_btncancelarMouseClicked

    private void btnokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnokMouseClicked
//        new Progreso().setVisible(true);
//        dispose();
        iniciarSesion();
    }//GEN-LAST:event_btnokMouseClicked

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnokActionPerformed

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
            java.util.logging.Logger.getLogger(LoginSis.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginSis.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginSis.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginSis.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginSis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblIconLlave;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JComboBox<String> opcion;
    public static javax.swing.JPasswordField txtContrasena;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
conectar cc = new conectar();
    Connection cn = cc.conexion();

    private Object Progreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}