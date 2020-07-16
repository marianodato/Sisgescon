package forms;

//import Articulos_GUI.Creacion_articulo;
import BD.GestorBaseDatos;
import Clases.EMailEvaluar;
//import Clases.;
import Clases.ScreenSplash;
import Clases.Usuario;
import congresos.Congreso;
//import Clases.Usuario;
import gui_congresos.CreacionCongreso;
import gui_congresos.CreacionInvitaciones;
import gui_evaluaciones.SeleccionArticulo;
import gui_evaluaciones.SeleccionEvaluadorArticulo;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//import usuarios_perfiles.Usuario;

public class frmMain extends javax.swing.JFrame {
    
    public JDesktopPane getPanel(){
    return jdpPrincipal;
    
    }
    //EMailEvaluar misEvaluaciones= new EMailEvaluar();
    //GestorBaseDatos.
    static  Usuario miUsuario=new Usuario();
    static Congreso miCongreso=new Congreso();
    
    public static boolean connection;
    
    public static void setUsuario(Usuario usr) {
        miUsuario=usr;
        setMenu(true);
    }
    
    
       private static void setMenu(boolean opc) {
        mnuCongresos.setEnabled(opc);
        mnuInvitaciones.setEnabled(false);
        mnuUsuario.setEnabled(opc);
        mnuPerfiles.setEnabled(opc);
        mnuCongresos.setEnabled(opc);        
    }      
    
    public frmMain() {          
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuEntrar = new javax.swing.JMenu();
        mnuCongresos = new javax.swing.JMenu();
        mnuUsuario = new javax.swing.JMenu();
        mnuPerfiles = new javax.swing.JMenu();
        mnuAsignarArticulos = new javax.swing.JMenu();
        mnuEvaluador = new javax.swing.JMenu();
        mnuInvitaciones = new javax.swing.JMenu();
        mnuAcepRech = new javax.swing.JMenu();
        mnuAutor = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SisGesCon - Sistema de Gestion de Congresos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        mnuEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_lock_outline_black_24dp.png"))); // NOI18N
        mnuEntrar.setToolTipText("Iniciar sesion");
        mnuEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuEntrarMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuEntrar);

        mnuCongresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_account_balance_black_24dp.png"))); // NOI18N
        mnuCongresos.setToolTipText("Mis Congresos");
        mnuCongresos.setEnabled(false);
        mnuCongresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuCongresosMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuCongresos);

        mnuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_group_black_24dp.png"))); // NOI18N
        mnuUsuario.setToolTipText("Mi usuario");
        mnuUsuario.setEnabled(false);
        mnuUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuUsuarioMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuUsuario);

        mnuPerfiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_folder_shared_black_24dp.png"))); // NOI18N
        mnuPerfiles.setToolTipText("Mis perfiles");
        mnuPerfiles.setEnabled(false);
        mnuPerfiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuPerfilesMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuPerfiles);

        mnuAsignarArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_verified_user_black_36dp.png"))); // NOI18N
        mnuAsignarArticulos.setToolTipText("Asignar articulos");
        mnuAsignarArticulos.setEnabled(false);
        mnuAsignarArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuAsignarArticulosMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuAsignarArticulos);

        mnuEvaluador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_content_paste_black_24dp.png"))); // NOI18N
        mnuEvaluador.setToolTipText("Ver evaluaciones");
        mnuEvaluador.setEnabled(false);
        mnuEvaluador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuEvaluadoresMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuEvaluador);

        mnuInvitaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_mail_black_36dp.png"))); // NOI18N
        mnuInvitaciones.setToolTipText("Crear invitaciones");
        mnuInvitaciones.setEnabled(false);
        mnuInvitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuInvitacionesMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuInvitaciones);

        mnuAcepRech.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_thumbs_up_down_black_36dp.png"))); // NOI18N
        mnuAcepRech.setToolTipText("Aceptar o rechazar articulos");
        mnuAcepRech.setEnabled(false);
        mnuAcepRech.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuAcepRechMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuAcepRech);

        mnuAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ic_insert_invitation_black_24dp.png"))); // NOI18N
        mnuAutor.setToolTipText("Autor");
        mnuAutor.setEnabled(false);
        mnuAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuAutoresMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuAutor);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        frmLogin miLogin = new frmLogin();
        jdpPrincipal.add(miLogin);       
        miLogin.show();
    }//GEN-LAST:event_formWindowOpened

    private void mnuEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuEntrarMouseClicked
        if (getMiUsuario().getB_ingresado()) {
            if (JOptionPane.showConfirmDialog(rootPane , "¿Desea cerrar su sesion?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                getMiUsuario().clear();
                setMenu(false);
                mnuAutor.setEnabled(false);
                mnuAsignarArticulos.setEnabled(false);
                mnuEvaluador.setEnabled(false);
                mnuCongresos.setEnabled(false);
                frmLogin miLogin = new frmLogin();
                jdpPrincipal.add(miLogin);
                miLogin.show();
            }            
        }
        else{
            frmLogin miLogin = new frmLogin();
            jdpPrincipal.add(miLogin);
            miLogin.show();
        }    
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuEntrarMouseClicked

    private void mnuInvitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuInvitacionesMouseClicked
        // TODO add your handling code here:
        System.out.println(frmMain.miCongreso.getNombre());
        CreacionInvitaciones creacionInvitacion = new CreacionInvitaciones(getCongresoCompleto(frmMain.miCongreso.getId()));
        creacionInvitacion.setPuntero(jdpPrincipal);
        jdpPrincipal.add(creacionInvitacion);
    }//GEN-LAST:event_mnuInvitacionesMouseClicked

    private void mnuUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuUsuarioMouseClicked
        // TODO add your handling code here:
        try{
            if (mnuUsuario.isEnabled()){
                Creacion_usuario.bModificacion=true;
                Creacion_usuario miUsuarioNuevo = new Creacion_usuario();
                jdpPrincipal.add(miUsuarioNuevo);        
                miUsuarioNuevo.show();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, " Hubo un error" + e.getMessage());
        }
    }//GEN-LAST:event_mnuUsuarioMouseClicked

    private void mnuCongresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuCongresosMouseClicked
        // TODO add your handling code here:
        try{
           if (mnuCongresos.isEnabled()){
                CreacionCongreso miCongreso = new CreacionCongreso(miUsuario);
                miCongreso.setPuntero(jdpPrincipal);
                jdpPrincipal.add(miCongreso);
                miCongreso.show();
            }  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, " Hubo un error" + e.getMessage());
        }
    }//GEN-LAST:event_mnuCongresosMouseClicked

    private void mnuPerfilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuPerfilesMouseClicked
        // TODO add your handling code here:
        try{
           if (mnuPerfiles.isEnabled()){
                Perfiles_congresos miPerfil = new Perfiles_congresos();
                jdpPrincipal.add(miPerfil);
                miPerfil.show();
            }  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, " Hubo un error" + e.getMessage());
        }
        
        
        
    }//GEN-LAST:event_mnuPerfilesMouseClicked

    private void mnuEvaluadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuEvaluadoresMouseClicked
        // TODO add your handling code here:
        SeleccionEvaluadorArticulo seleccionEvaluadorArticulo = new SeleccionEvaluadorArticulo(miUsuario, frmMain.miCongreso, Perfiles_congresos.esOrgEv);
        seleccionEvaluadorArticulo.setPuntero(jdpPrincipal);
        jdpPrincipal.add(seleccionEvaluadorArticulo);
    }//GEN-LAST:event_mnuEvaluadoresMouseClicked

    private void mnuAsignarArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuAsignarArticulosMouseClicked
        // TODO add your handling code here:
        gui_evaluaciones.AsignacionArticulos  asignacionArticulos = new gui_evaluaciones.AsignacionArticulos(miUsuario,getCongresoCompleto(miCongreso.getId()));
        asignacionArticulos.setPuntero(jdpPrincipal);
        jdpPrincipal.add(asignacionArticulos);
    }//GEN-LAST:event_mnuAsignarArticulosMouseClicked

    private void mnuAutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuAutoresMouseClicked
        // TODO add your handling code here:
        try{
           if (mnuAutor.isEnabled()){
                Creacion_articulo miArticulo = new Creacion_articulo(jdpPrincipal,getCongresoCompleto(miCongreso.getId()));
                jdpPrincipal.add(miArticulo);
                miArticulo.show();
            }  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, " Hubo un error" + e.getMessage());
        }
    }//GEN-LAST:event_mnuAutoresMouseClicked

    private void mnuAcepRechMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuAcepRechMouseClicked
        // TODO add your handling code here:
        SeleccionArticulo seleccionArticulo = new SeleccionArticulo(miUsuario, getCongresoCompleto(frmMain.miCongreso.getId()), Perfiles_congresos.esOrgEv);
        seleccionArticulo.setPuntero(jdpPrincipal);
        jdpPrincipal.add(seleccionArticulo);
        //seleccionArticulo.show();
    }//GEN-LAST:event_mnuAcepRechMouseClicked

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>               
        new ScreenSplash().animar();
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JDesktopPane jdpPrincipal;
    public static javax.swing.JMenu mnuAcepRech;
    public static javax.swing.JMenu mnuAsignarArticulos;
    public static javax.swing.JMenu mnuAutor;
    public static javax.swing.JMenu mnuCongresos;
    private javax.swing.JMenu mnuEntrar;
    public static javax.swing.JMenu mnuEvaluador;
    public static javax.swing.JMenu mnuInvitaciones;
    private static javax.swing.JMenu mnuPerfiles;
    private static javax.swing.JMenu mnuUsuario;
    // End of variables declaration//GEN-END:variables

    public static Usuario getMiUsuario() {
        return miUsuario;
    }

    public static void setMiUsuario(Usuario miUsuario) {
        frmMain.miUsuario = miUsuario;
    }
    public static Congreso getMiCongreso() {
        return miCongreso;
    }

    public static void setMiCongreso(Congreso miCongreso) {
        frmMain.miCongreso = miCongreso;
    }
    
    public Congreso getCongresoCompleto(int id) {
        try {
            return GestorBaseDatos.BuscarCongresoXID(id).get(0);
        } catch (InstantiationException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
