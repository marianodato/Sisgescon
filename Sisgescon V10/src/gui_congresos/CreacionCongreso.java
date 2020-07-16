package gui_congresos;
import comun.CInternalFrame;
import comun.Regex;
import congresos.Congreso;
import javax.swing.JOptionPane;
import Clases.Usuario;

/**
 *CInternalFrame
 * @author VictoriaBalin
 */
public class CreacionCongreso extends CInternalFrame {

    private static final int limiteNombre=40;
    private static final int limiteAcronimo=12;
    private static final int limiteAno=4;
    private Congreso congreso;
    private Usuario usuario;
    
    public CreacionCongreso(Usuario usuario) {
        super("Creación de un Congreso");
        initComponents();
        this.usuario = usuario;
        nombreCongreso.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        web = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombreCongreso = new javax.swing.JTextField();
        acronimo = new javax.swing.JTextField();
        sede = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ano = new javax.swing.JTextField();

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        email.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Email");

        web.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        web.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Página Web");

        nombreCongreso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nombreCongreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreCongresoKeyTyped(evt);
            }
        });

        acronimo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        acronimo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        acronimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                acronimoKeyTyped(evt);
            }
        });

        sede.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sede.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnAnterior.setText("Anterior");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sede");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Acrónimo");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Año");

        ano.setToolTipText("");
        ano.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                anoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(btnSiguiente)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(264, 264, 264))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreCongreso)
                                .addGap(126, 126, 126)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(web, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(acronimo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(sede, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(ano))))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(web, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acronimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sede, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAnterior))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if(validar()){    
            this.abrirVentana(new CreacionFechasCongreso(this.congreso, usuario));
        }
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void nombreCongresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreCongresoKeyTyped
        if(nombreCongreso.getText().length()==limiteNombre){
            evt.consume();
        }
    }//GEN-LAST:event_nombreCongresoKeyTyped

    private void acronimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acronimoKeyTyped
        if(acronimo.getText().length()==limiteAcronimo){
            evt.consume();
        }
    }//GEN-LAST:event_acronimoKeyTyped

    private void anoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoKeyTyped
        if(ano.getText().length()==limiteAno){
            evt.consume();
        }
    }//GEN-LAST:event_anoKeyTyped
    
    public boolean validar(){
        String errores="";
        
        try{
            if(nombreCongreso.getText().isEmpty()){
                errores=errores.concat("El nombre no puede estar vacío.\n");
                nombreCongreso.requestFocus();
            }
            if(acronimo.getText().isEmpty()){
                errores=errores.concat("El acrónimo no puede estar vacío.\n");
                acronimo.requestFocus();
            }
            if(sede.getText().isEmpty()){
                errores=errores.concat("La sede no puede estar vacía.\n");
                sede.requestFocus();
            }
            if(web.getText().isEmpty()){
                errores=errores.concat("La página web no puede estar vacía.\n");
                web.requestFocus();
            }
            if(email.getText().isEmpty()){
                errores=errores.concat("El email no puede estar vacío.\n");
                email.requestFocus();
            }
            if(ano.getText().isEmpty()){
                errores=errores.concat("El año no puede estar vacío.\n");
                ano.requestFocus();
            }
            
            if(!Regex.evaluar(sede.getText(), "^[A-Z\\ a-z]+$")){
                errores=errores.concat("El nombre de la sede tiene un formato incorrecto.\n");
                sede.requestFocus();
            }
            if(!Regex.evaluar(web.getText(),
                    "^(?:http[s]?:\\/\\/)?(?:www\\.)?[_a-zA-Z0-9]+\\.[a-z]{2,3}(?:\\.[a-z]{2,3})?$")){
                errores=errores.concat("El email tiene un formato incorrecto.\n");
                web.requestFocus();
            }
            if(!Regex.evaluar(email.getText(), 
                    "^[_a-zA-Z0-9.]+@(?:[a-z]+\\.)+[a-z]{2,3}(?:\\.[a-z]{2,3})?$")){
                errores=errores.concat("El formato del email es incorrecto.\n");
                email.requestFocus();
            }
            if(!isNumero(ano.getText())){
                errores=errores.concat("El formato del año es incorrecto.\n");
                ano.requestFocus();
            }else if(!anoActual(ano.getText())){
                errores=errores.concat("El año no puede ser menor al actual.\n");
                ano.requestFocus();
            }
            
            if (!errores.isEmpty())
                throw new Exception(errores);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int anio=Integer.parseInt(ano.getText());
        congreso = new Congreso(acronimo.getText(), anio, email.getText(),
                nombreCongreso.getText(), sede.getText(), web.getText());
        return true;
    }
    
    public boolean isNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean anoActual(String ano){
        int valorAno=Integer.parseInt(ano);
        if(valorAno<2015)
            return false;
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acronimo;
    private javax.swing.JTextField ano;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nombreCongreso;
    private javax.swing.JTextField sede;
    private javax.swing.JTextField web;
    // End of variables declaration//GEN-END:variables
}
