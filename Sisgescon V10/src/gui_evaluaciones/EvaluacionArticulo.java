package gui_evaluaciones;
import BD.GestorBaseDatos;
import Clases.Articulo;
import comun.CInternalFrame;
import comun.Constantes;
import congresos.Congreso;
import evaluaciones.Asignacion;
import evaluaciones.Evaluacion;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Clases.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author juan
 */
public class EvaluacionArticulo extends CInternalFrame {
    
    private Asignacion asignacion;
    
    private final String EXCELENTE = "Excelente";
    private final String BUENO = "Bueno";
    private final String REGULAR = "Regular";
    private final String POBRE = "Pobre";
    private final String MALO = "Malo";
    
    private Articulo articulo;
    private Usuario evaluador;
    private Congreso congreso;
    private Usuario usuario;
    private int perfil;
    
    private int ventanaFuente = 0;
    
    public EvaluacionArticulo(Evaluacion evaluacion, Usuario usuario, Congreso congreso, int fuente, int perfil) {
        this(null, evaluacion, usuario, congreso, fuente, perfil);
    }
    
    public EvaluacionArticulo(Asignacion asignacion, Evaluacion evaluacion, Usuario usuario,
            Congreso congreso, int fuente, int perfil) {
        super("Evaluación de Artículo");
        
        this.perfil = perfil;
        this.asignacion = asignacion;
        
        initComponents();
        
        if (evaluacion == null) {
            labelFechaHeader.setVisible(false);
            labelFecha.setVisible(false);
        }
        
        if (asignacion != null) {
            articulo = asignacion.getArticulo();
            evaluador = asignacion.getEvaluador();
        } 
        else if (evaluacion != null) {
            articulo = evaluacion.getArticulo();            
            evaluador = evaluacion.getEvaluador();
        }
        
        this.ventanaFuente = fuente;
        this.congreso = congreso;
        this.usuario = usuario;
        
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(20,20,20,20));
        
        lblTitulo.setText(wrap(articulo.getTitulo()));
        labelAutoresHeader.setVisible(false);
        labelAutores.setVisible(false);
        labelResumen.setText(wrap(articulo.getResumen(), 50));
        
        if (evaluacion != null) {
            labelAutores.setText(getAutores(articulo));
            labelAutoresHeader.setVisible(true);
            labelAutores.setVisible(true);
            taComentariosAut.setText(evaluacion.getComentarioAutor());
            taComentariosOrg.setText(evaluacion.getComentarioOrganizador());
            taComentariosOrg.setEditable(false);
            taComentariosAut.setEditable(false);
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton5.setEnabled(false);
            
            labelFechaHeader.setVisible(true);
            labelFecha.setVisible(true);
            labelFecha.setText(convertirFecha(evaluacion.getFecha()));
            
            if (usuario.getI_Id() != evaluador.getI_Id()) {
                taComentariosOrg.setEditable(false);
                taComentariosAut.setEditable(false);
            }
            switch (evaluacion.getCalificacion()) {
                case 2:
                    jRadioButton1.setSelected(true);
                    break;
                case 1:
                    jRadioButton2.setSelected(true);
                    break;
                case 0:
                    jRadioButton3.setSelected(true);
                    break;
                case -1:
                    jRadioButton4.setSelected(true);
                    break;
                case -2:
                    jRadioButton5.setSelected(true);
                    break;
            }
            btnSiguiente.setVisible(false);
        }
        
        btnDescargar.setEnabled(tieneArchivo());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitulo = new javax.swing.JLabel();
        labelAutores = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        labelFechaHeader = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taComentariosAut = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        taComentariosOrg = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDescargar = new javax.swing.JButton();
        labelResumen = new javax.swing.JLabel();
        labelAutoresHeader = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblTitulo.setText("Titulo");
        lblTitulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTitulo.setAlignmentY(0.0F);
        lblTitulo.setMaximumSize(new java.awt.Dimension(400, 84));
        lblTitulo.setMinimumSize(new java.awt.Dimension(49, 28));
        lblTitulo.setPreferredSize(new java.awt.Dimension(49, 84));

        labelAutores.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        labelAutores.setText("Autor");

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Calificación"));
        jPanel1.setPreferredSize(new java.awt.Dimension(140, 200));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Excelente");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Bueno");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Pobre");

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("Malo");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Regular");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        labelFechaHeader.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelFechaHeader.setText("Evaluado el:");

        labelFecha.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelFecha.setText("10/10/10 10:10:10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(labelFechaHeader)
                    .addComponent(labelFecha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5)
                .addGap(18, 18, 18)
                .addComponent(labelFechaHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFecha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Comentarios"));

        taComentariosAut.setColumns(20);
        taComentariosAut.setRows(5);
        jScrollPane2.setViewportView(taComentariosAut);

        taComentariosOrg.setColumns(20);
        taComentariosOrg.setRows(5);
        jScrollPane1.setViewportView(taComentariosOrg);

        jLabel1.setText("Autores");

        jLabel2.setText("Organizadores");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDescargar.setLabel("Descargar");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        labelResumen.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        labelResumen.setText("Resumen");
        labelResumen.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        labelAutoresHeader.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        labelAutoresHeader.setText("Autores:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAutores)
                            .addComponent(labelAutoresHeader)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDescargar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addComponent(labelResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAutoresHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAutores)
                    .addComponent(btnDescargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        
        Date hoy = new java.util.Date();
        if (hoy.compareTo(congreso.getFinEvaluacion()) > 0) {
            mostrarMensaje("El período de evaluación ya finalizó.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hoy.compareTo(congreso.getInicioEvaluacion()) < 0) {
            mostrarMensaje("El período de evaluación aún no ha comenzado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
               
        
        try {
            int calificacion = this.getCalificacion();
            String comentariosAut = this.taComentariosAut.getText();
            String comentariosOrg = this.taComentariosOrg.getText();
            
            Evaluacion e = new Evaluacion(calificacion, comentariosAut, comentariosOrg,
                    articulo, evaluador);
            
            e.setComentarioOrganizador(comentariosOrg);
            
            GestorBaseDatos.ActualizoArticuloEvaluacion(asignacion.getId(), e.getEvaluador().getI_Id(), calificacion, comentariosAut, comentariosOrg, convertirFechaSql(new Date()), e.getArticulo().getArticulo_id());
            
            this.dispose();
        } catch (Exception ex) {
            //Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (ventanaFuente == 0)
            this.abrirVentana(new SeleccionArticulo(usuario, congreso, Constantes.EVALUADOR));
        else
            this.abrirVentana(new SeleccionEvaluadorArticulo(usuario, congreso, perfil));
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        try {
            //byte[] archivo = GestorBaseDatos.BuscarArticuloPDF(articulo.getArticulo_id());            
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Seleccione ruta para guardar el archivo");
            //jfc.setSelectedFile(new File(tituloToArchivo()));
            int resultado = jfc.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                GestorBaseDatos.BuscarArticuloPDF(articulo.getArticulo_id(),jfc.getSelectedFile().getAbsolutePath());
                //OutputStream out = new FileOutputStream(jfc.getSelectedFile().getAbsolutePath());
                //out.write(archivo);
                //out.close();
            }
                
        } catch (InstantiationException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDescargarActionPerformed
    
    private boolean tieneArchivo() {
        try {
            return GestorBaseDatos.ExisteArticuloPDF(articulo.getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvaluacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private String tituloToArchivo() {
       String titulo = articulo.getTitulo();       
       return titulo.replace(" ", "_").concat(".pdf");
    }
    
    private int getCalificacion() throws Exception {
        
        if (jRadioButton1.isSelected())
            return 2;
        else if (jRadioButton2.isSelected())
            return 1;
        else if (jRadioButton3.isSelected())
            return 0;
        else if (jRadioButton4.isSelected())
            return -1;
        else if (jRadioButton5.isSelected())
            return -2;
        else
            throw new Exception();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAutores;
    private javax.swing.JLabel labelAutoresHeader;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFechaHeader;
    private javax.swing.JLabel labelResumen;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea taComentariosAut;
    private javax.swing.JTextArea taComentariosOrg;
    // End of variables declaration//GEN-END:variables
}
