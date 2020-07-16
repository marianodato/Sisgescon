package forms;
import Clases.Congreso;
import BD.GestorBaseDatos;
import static BD.GestorBaseDatos.getCongresos;
import Clases.Perfil;
import static forms.frmMain.jdpPrincipal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enhu
 */
public class Perfiles_congresos extends javax.swing.JInternalFrame {

    public static Vector<Congreso> misCongresos = new Vector<Congreso>();
    public static int esOrgEv;
    
    public Perfiles_congresos() {
        initComponents();
        this.setLocation((jdpPrincipal.getWidth()/2-this.getWidth()/2), (jdpPrincipal.getHeight()/2-this.getHeight()/2));
        try {
            GestorBaseDatos.CargarCongresos();
        } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        misCongresos= GestorBaseDatos.getCongresos();
        DefaultListModel congresos = new DefaultListModel();
        for(Congreso i: misCongresos){
            congresos.addElement(i.getS_nombre());
        }
        
        listCongresos.setModel(congresos);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listCongresos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPerfiles = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Sistema de Gestion de Congresos - Seleccion de congreso y perfil");
        setPreferredSize(new java.awt.Dimension(800, 600));

        listCongresos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        listCongresos.setToolTipText("");
        listCongresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listCongresosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(listCongresos);

        listPerfiles.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(listPerfiles);

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Perfiles");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Congresos");

        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setText("Inscribirse");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(78, 78, 78)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    //frmMain Main = new frmMain();
    if(listPerfiles.isSelectionEmpty()==false){
    if(listPerfiles.getSelectedValue().toString().equals("Autor")){
       frmMain.mnuEvaluador.setEnabled(false);
       frmMain.mnuAsignarArticulos.setEnabled(false);
       frmMain.mnuCongresos.setEnabled(false);
       frmMain.mnuAutor.setEnabled(true);
       frmMain.mnuAcepRech.setEnabled(false);
       frmMain.mnuInvitaciones.setEnabled(false);
       JOptionPane.showMessageDialog(null, "Se ha selccionado el perfil Autor","Mensaje",JOptionPane.INFORMATION_MESSAGE);
       this.dispose();
       
    }else if (listPerfiles.getSelectedValue().toString().equals("Organizador")){
        frmMain.mnuAutor.setEnabled(false);
        frmMain.mnuEvaluador.setEnabled(true);
        frmMain.mnuCongresos.setEnabled(false);
        frmMain.mnuAsignarArticulos.setEnabled(true);
        frmMain.mnuAcepRech.setEnabled(true);
        frmMain.mnuInvitaciones.setEnabled(true);
        JOptionPane.showMessageDialog(null, "Se ha selccionado el perfil Organizador","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        esOrgEv = 0;
        this.dispose();

    }else if (listPerfiles.getSelectedValue().toString().equals("Organizador Responsable")){
        frmMain.mnuAutor.setEnabled(false);
        frmMain.mnuEvaluador.setEnabled(true);
        frmMain.mnuCongresos.setEnabled(false);
        frmMain.mnuAsignarArticulos.setEnabled(true);
        frmMain.mnuAcepRech.setEnabled(true);
        frmMain.mnuInvitaciones.setEnabled(true);
        JOptionPane.showMessageDialog(null, "Se ha selccionado el perfil Organizador Responsable","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        esOrgEv = 0;
        this.dispose();

    }else{
           frmMain.mnuAutor.setEnabled(false);
        frmMain.mnuEvaluador.setEnabled(true);
        frmMain.mnuCongresos.setEnabled(false);
        frmMain.mnuAsignarArticulos.setEnabled(false);
        frmMain.mnuAcepRech.setEnabled(true);
        frmMain.mnuInvitaciones.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Se ha selccionado el perfil Evaluador","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        esOrgEv = 1;
        this.dispose();
       
        
    }
    }else{
        JOptionPane.showMessageDialog(null, "No se ha seleccionado un congreso o un perfil","Error",JOptionPane.WARNING_MESSAGE);
    }
    
    frmMain.miCongreso.setId(misCongresos.get(listCongresos.getSelectedIndex()).getI_Id());

    }//GEN-LAST:event_jButton1ActionPerformed

   
    
    
    
    private void listCongresosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCongresosMousePressed
    Date todayDate = new Date();
    Vector<Perfil> perfiles = new Vector<Perfil>();
    Vector<Congreso> congreso = new Vector<Congreso>();
        try {
            perfiles = GestorBaseDatos.BuscarPerfilxCongresoxUsuario(misCongresos.get(listCongresos.getSelectedIndex()).getI_Id(),frmMain.miUsuario.getI_Id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        DefaultListModel listModel = new DefaultListModel();    
        for(Perfil i: perfiles){
            
            listModel.addElement(i.getS_descripcion());
        }
        
        listPerfiles.setModel(listModel);
    
        
        
        try {
            congreso = GestorBaseDatos.BuscarCongresoXID(misCongresos.get(listCongresos.getSelectedIndex()).getI_Id()); 
           } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (todayDate.compareTo(congreso.firstElement().getD_fin()) <= 0 && todayDate.compareTo(congreso.firstElement().getD_inicio())>= 0){
            //habilita el boton
            jButton2.setEnabled(true);
            
        }else{
            jButton2.setEnabled(false);  //lo deshabilita
        }
        
        
        
        
    }//GEN-LAST:event_listCongresosMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Vector<Perfil> perfiles = new Vector<Perfil>();
        boolean autorExiste=false;
        
        try {
            perfiles = GestorBaseDatos.BuscarPerfilxCongresoxUsuario(misCongresos.get(listCongresos.getSelectedIndex()).getI_Id(), frmMain.miUsuario.getI_Id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for (Perfil i: perfiles)
        { 
            if(i.getS_descripcion().equals("Autor")){
            autorExiste = true;
        }
        }
        
        if (autorExiste == true){
            JOptionPane.showMessageDialog(null, "Usted ya esta inscripto en este congreso","Error",JOptionPane.WARNING_MESSAGE);
        }else{
            
            try {
            GestorBaseDatos.AgregarRelacion_Usuario_Congreso_Perfil(frmMain.miUsuario.getI_Id(), misCongresos.get(listCongresos.getSelectedIndex()).getI_Id(), 4);
            
            // TODO add your handling code here:
        } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Autor");
        listPerfiles.setModel(listModel);
        
        
                
        }
        
        
        
 
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JList listCongresos;
    public static javax.swing.JList listPerfiles;
    // End of variables declaration//GEN-END:variables
}