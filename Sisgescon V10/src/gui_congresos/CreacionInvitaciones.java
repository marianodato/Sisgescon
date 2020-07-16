package gui_congresos;
import BD.GestorBaseDatos;
import GestionMails.ControladorMail;
import GestionMails.Mail;
import comun.BaseDatos;
import comun.CInternalFrame;
import comun.Constantes;
import comun.Regex;
import congresos.Congreso;
import forms.Creacion_usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import usuarios_perfiles.Perfil;
import Clases.Usuario;


/**
 *
 * @author juan
 */
public class CreacionInvitaciones extends CInternalFrame  {

    private ControladorMail controlador=new ControladorMail();
    private Mail mail;
    private BaseDatos bd=new BaseDatos();
    public static boolean abierta=false;
    private DefaultListModel<String> modelo;
    private ArrayList<Invitado> evaluadoresInvitados;
    private ArrayList<Invitado> organizadoresInvitados;
    private Congreso congreso;
    private int indice;
    private String email="";
    
    public CreacionInvitaciones(Congreso congreso) {
        super("Creación de Invitaciones al Congreso");
        initComponents();
        
        this.congreso = congreso;
        modelo = new DefaultListModel<>();
        listaInvitados.setModel(modelo);
        evaluadoresInvitados = new ArrayList<>();
        organizadoresInvitados = new ArrayList<>();
        
        actualizarLista();
    }
    
    private void actualizarLista() {
        modelo.removeAllElements();
        if (organizadoresInvitados.size() > 0){
            modelo.addElement("--- Organizadores ---");
            for (Invitado i : organizadoresInvitados) {
                modelo.addElement(i.getTodo());
            }
            
            indice = organizadoresInvitados.size() +1;
        }
        
        
        if (evaluadoresInvitados.size() > 0){
            modelo.addElement("--- Evaluadores ---");
            for (Invitado i : evaluadoresInvitados) {
                modelo.addElement(i.getTodo());
            }
            
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        tfApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaInvitados = new javax.swing.JList();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Perfil");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Organizador", "Evaluador" }));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        tfNombre.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Email");

        jScrollPane1.setViewportView(listaInvitados);

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Lista de Invitados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguiente)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfApellido, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPerfil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(108, 108, 108)
                        .addComponent(btnEliminar))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addGap(20, 20, 20)
                        .addComponent(tfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4)
                        .addGap(20, 20, 20)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private String crearUsuarios(ArrayList<Invitado> invitados, int perfil) {
        String email = "";
        for (int i = 0; i < invitados.size(); i++) {
            if(invitados.size()-1==i)
                email=email.concat(invitados.get(i).getEmail());
            else
                email=email.concat(invitados.get(i).getEmail()+",");
            
            Usuario nuevoUsuario=new Usuario();
            //nuevoUsuario.setI_Id(4);
            nuevoUsuario.setS_usuario(invitados.get(i).getEmail());
            nuevoUsuario.setS_nombre(invitados.get(i).getNombre());
            nuevoUsuario.setS_apellido(invitados.get(i).getApellido());
            nuevoUsuario.setS_password(invitados.get(i).getEmail());
            nuevoUsuario.setS_mail(invitados.get(i).getEmail());

            try {
                     GestorBaseDatos.AgregarUsuario(nuevoUsuario);
                     GestorBaseDatos.AgregarRelacion_Usuario_Congreso_Perfil
                    (nuevoUsuario.getI_Id(), (int) congreso.getId(), perfil);
                     
                 } catch (InstantiationException ex) {
                     Logger.getLogger(Creacion_usuario.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IllegalAccessException ex) {
                     Logger.getLogger(Creacion_usuario.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Creacion_usuario.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(Creacion_usuario.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
            
        }
        
        return email;
    }
    
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
        try {
            String emailOrg;
            String emailEva;
            
            emailOrg = crearUsuarios(organizadoresInvitados, Constantes.P_ORGANIZADOR);
            
            emailEva = crearUsuarios(evaluadoresInvitados, Constantes.P_EVALUADOR);
            
            String asuntoOrg = GestorBaseDatos.BuscarMailTipo(10).get(0).getMt_descripcion();
            String asuntoEva = GestorBaseDatos.BuscarMailTipo(11).get(0).getMt_descripcion();
            String cuerpoOrg = GestorBaseDatos.BuscarMailCompleto(10).get(0).getM_descripcion();
            String cuerpoEva = GestorBaseDatos.BuscarMailCompleto(11).get(0).getM_descripcion();
            
            cuerpoOrg = controlador.format(cuerpoOrg);
            cuerpoEva = controlador.format(cuerpoEva);
            
            controlador.setPanel(puntero);
            controlador.setCongreso(congreso.getNombre(),congreso.getAcronimo(),
                    congreso.getEmail(),congreso.getSitioWeb(),congreso.getSede());
            controlador.EnviarInvitaciones(new Mail(emailOrg,asuntoOrg,cuerpoOrg,1)
                    ,new Mail(emailEva,asuntoEva,cuerpoEva,1));
            this.dispose();
        } catch (InstantiationException ex) {
            Logger.getLogger(CreacionInvitaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CreacionInvitaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreacionInvitaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreacionInvitaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private boolean validar() {
        
        String errores = "";
        
        try {
            
            if(tfNombre.getText().isEmpty()){
                errores = errores.concat("Nombre es obligatorio.\n");
                tfNombre.requestFocus();
            }    
            if(tfApellido.getText().isEmpty()){
                errores = errores.concat("Apellido es obligatorio.\n");
                tfApellido.requestFocus();
            }
            if(tfEmail.getText().isEmpty()){
                errores = errores.concat("Email es obligatorio.\n");
                tfEmail.requestFocus();
            }
            
           
            
            if (!Regex.evaluar(tfNombre.getText(), "^[A-Z\\ a-z]+$")){
                errores = errores.concat("Nombre tiene un formato incorrecto.\n");
                tfNombre.requestFocus();
            }
            if (!Regex.evaluar(tfApellido.getText(), "^[A-Z\\ a-z]+$")){
                errores = errores.concat("Apellido tiene un formato incorrecto.\n");
                tfApellido.requestFocus();
            }
            if (!Regex.evaluar(tfEmail.getText(), 
                 "^[_a-zA-Z0-9.]+@(?:[a-z]+\\.)+[a-z]{2,3}(?:\\.[a-z]{2,3})?$")){
                errores = errores.concat("Email tiene un formato incorrecto.\n");
                tfEmail.requestFocus();
            }
            
            for (Invitado i : organizadoresInvitados) {
                if (i.getEmail().equals(tfEmail.getText())) {
                    errores = errores.concat("El usuario con este mail ya existe");
                    tfEmail.requestFocus();
                }
            }
            
            for (Invitado i : evaluadoresInvitados) {
                if (i.getEmail().equals(tfEmail.getText())) {
                    errores = errores.concat("El usuario con este mail ya existe");
                    tfEmail.requestFocus();
                }
            }
            
             if (!errores.isEmpty())
                throw new Exception(errores);
        }
        catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                   JOptionPane.ERROR_MESSAGE);
            
           return false;
        }
        
        return true;
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
       if (validar()) {
        Invitado i = new Invitado(tfNombre.getText(), tfApellido.getText(), 
                                    tfEmail.getText());
           
           if (cbPerfil.getSelectedIndex() == 0) {
                organizadoresInvitados.add(i);
           }
           else {
               evaluadoresInvitados.add(i);
           }

        tfNombre.setText("");
        tfApellido.setText("");
        tfEmail.setText("");
        tfNombre.requestFocus();

        actualizarLista();
        
     }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = listaInvitados.getSelectedIndex();
        
        if (i == 0 || i == indice) return;
        
        if (organizadoresInvitados.isEmpty() && 
                evaluadoresInvitados.isEmpty()) return;
        
        if (organizadoresInvitados.isEmpty()) {
            evaluadoresInvitados.remove(i-1);
        }
        else if (evaluadoresInvitados.isEmpty()) {
            organizadoresInvitados.remove(i-1);
        }
        else {
            if (organizadoresInvitados.size() < i) {
                evaluadoresInvitados.remove(i-2-organizadoresInvitados.size());
            }
            else {
                organizadoresInvitados.remove(i-1);
            }
        }
        
        actualizarLista();
        
        listaInvitados.setSelectedIndex(i-1 == indice ? i-2 : i-1);
    }//GEN-LAST:event_btnEliminarActionPerformed
    
    class Invitado {
        
        private String nombre;
        private String apellido;
        private String email;
        
        public Invitado(String nombre, String apellido, String email) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public String getApellido() {
            return apellido;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getTodo() {
            return getNombre().concat(" ").concat(getApellido()).concat(" <")
                    .concat(getEmail()).concat(">");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox cbPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaInvitados;
    private javax.swing.JTextField tfApellido;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
