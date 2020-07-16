package gui_evaluaciones;
import BD.GestorBaseDatos;
import GestionMails.ControladorMail;
import GestionMails.InterfaceEvaluacion;
import GestionMails.Mail;
import Clases.Articulo;
import comun.BaseDatos;
import comun.CInternalFrame;
import comun.Constantes;
import congresos.Congreso;
import evaluaciones.Asignacion;
import evaluaciones.Evaluacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Clases.Usuario;
import evaluaciones.Evaluaciones;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author juan
 */
public class AceptacionArticulo extends CInternalFrame {

    private Asignacion asignacion;
    private ArrayList<Evaluacion> evaluaciones;
    private ControladorMail controlador;
    private BaseDatos bd;

    private DefaultComboBoxModel<String> modeloEvaluadores;

    private Articulo articulo;
    private Usuario evaluador;
    private int perfil;
    private Congreso congreso;

    private boolean enEvaluacion;
    private Usuario usuario;

    public AceptacionArticulo(Articulo articulo, Usuario organizador, Congreso congreso, int perfil) {
        super("Evaluación de Artículo");
        
        this.perfil = perfil;
        this.congreso = congreso;
        this.usuario = organizador;
        initComponents();
        taComentariosAut.setEditable(false);
        taComentariosOrg.setEditable(false);
        controlador=new ControladorMail();
        bd=new BaseDatos();
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        this.articulo = articulo;

        labelTitulo.setText(CInternalFrame.wrap(articulo.getTitulo()));
        labelAutores.setText(getAutores(articulo));
        labelResumen.setText(wrap(articulo.getResumen()));
        btnDescargar.setEnabled(tieneArchivo());

        evaluaciones = new Evaluaciones(congreso).getEvaluaciones(articulo);

        modeloEvaluadores = new DefaultComboBoxModel<>();
        comboEvaluadores.setModel(modeloEvaluadores);

        for (Evaluacion e : evaluaciones) {
            modeloEvaluadores.addElement(getNombreCompleto(e.getEvaluador()));
        }

        comboEvaluadores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                actualizar();
            }
        });
        
        
        if (articulo.getEstado() != Constantes.A_PENDIENTE) {
            btnSiguiente.setEnabled(false);
            if (articulo.getEstado() == Constantes.A_ACEPTADO) {
                jRadioButton8.setSelected(true);
            } else {
                jRadioButton9.setSelected(true);
            }
            jRadioButton8.setEnabled(false);
            jRadioButton9.setEnabled(false);
        }

        actualizar();
    }

    private float getPromedio() {
        float tmp = 0;

        for (Evaluacion e : evaluaciones) {
            tmp += e.getCalificacion();
        }

        return tmp / evaluaciones.size();
    }

    public ArrayList<InterfaceEvaluacion> convertir() {
        ArrayList<InterfaceEvaluacion> interEvaluacion = new ArrayList<>();
        for (int i = 0; i < evaluaciones.size(); i++) {
            interEvaluacion.add(evaluaciones.get(i));
        }
        return interEvaluacion;
    }

    public void actualizar() {
        Evaluacion e = evaluaciones.get(comboEvaluadores.getSelectedIndex());
        System.out.println(e.getComentarioAutor());
        labelCalificacion.setText(e.getNombreCalificacion());
        labelFecha.setText(convertirFecha(e.getFecha()));
        taComentariosOrg.setText(e.getComentarioAutor());
        taComentariosAut.setText(e.getComentarioOrganizador());
        labelPromedio.setText(String.valueOf(getPromedio()));
        labelEvaluaciones.setText(String.valueOf(evaluaciones.size()));
        System.out.println(taComentariosOrg.getText());
    }
    
    public void mailAutomatico(int tipo,int nroIdioma){
        try {
            controlador.setPanel(puntero);
            String asunto=GestorBaseDatos.BuscarMailTipo(tipo).get(0).getMt_descripcion();
            String cuerpo=GestorBaseDatos.BuscarMailCompleto(tipo).get(0).getM_descripcion();
            
            cuerpo = controlador.format(cuerpo);
            Mail mail = new Mail(articulo.getAutor().getS_mail(),asunto,cuerpo, nroIdioma);
            controlador.setEvaluacion(convertir());
            controlador.envioAutomatico(mail);
        } catch (InstantiationException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        labelTitulo = new javax.swing.JLabel();
        labelAutores = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        labelEvaluaciones = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelPromedio = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taComentariosAut = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taComentariosOrg = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        comboEvaluadores = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelCalificacion = new javax.swing.JLabel();
        btnDescargar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        labelResumen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        labelTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        labelTitulo.setText("Titulo");
        labelTitulo.setMaximumSize(new java.awt.Dimension(400, 28));

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Calificaciones"));
        jPanel1.setPreferredSize(new java.awt.Dimension(140, 200));

        jLabel6.setText("Evaluaciones");

        labelEvaluaciones.setText("-1");

        jLabel8.setText("Promedio");

        labelPromedio.setText("-3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(labelEvaluaciones)
                    .addComponent(jLabel8)
                    .addComponent(labelPromedio))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEvaluaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPromedio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Evaluaciones"));

        taComentariosAut.setColumns(20);
        taComentariosAut.setRows(5);
        jScrollPane1.setViewportView(taComentariosAut);

        jLabel2.setText("Comentarios a Organizadores");

        taComentariosOrg.setColumns(20);
        taComentariosOrg.setRows(5);
        jScrollPane2.setViewportView(taComentariosOrg);

        jLabel1.setText("Comentarios a Autores");

        comboEvaluadores.setName(""); // NOI18N
        comboEvaluadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEvaluadoresItemStateChanged(evt);
            }
        });

        jLabel3.setText("Evaluador");

        jLabel4.setText("Calificación:");

        jLabel5.setText("Evaluado el:");

        labelFecha.setText("10/10/10 10:10:10");

        labelCalificacion.setText("Excelente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboEvaluadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCalificacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelFecha)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboEvaluadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(labelFecha)
                    .addComponent(labelCalificacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnDescargar.setLabel("Descargar");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Decisión"));
        jPanel4.setPreferredSize(new java.awt.Dimension(140, 200));

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setText("Aceptar");

        buttonGroup1.add(jRadioButton9);
        jRadioButton9.setText("Rechazar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton9)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        labelResumen.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        labelResumen.setText("Resumen");
        labelResumen.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel7.setText("Autores:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDescargar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAutores)
                            .addComponent(labelResumen)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelResumen)
                .addGap(43, 43, 43)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAutores)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDescargar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.abrirVentana(new SeleccionArticulo(usuario, congreso, perfil));
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        if (jRadioButton8.isSelected()) {
            articulo.setEstado(Constantes.A_ACEPTADO);

            if (articulo.getIdioma() == Constantes.I_INGLES) {
                mailAutomatico(15, 1);
            } else {
                mailAutomatico(16, 2);
            }
        } else if (jRadioButton9.isSelected()) {
            articulo.setEstado(Constantes.A_RECHAZADO);
            if (articulo.getIdioma() == Constantes.I_INGLES) {
                mailAutomatico(17, 1);
            } else {
                mailAutomatico(18, 2);
            }
        }

        int estado = jRadioButton8.isSelected() ? Constantes.A_ACEPTADO :
        jRadioButton9.isSelected() ? Constantes.A_RECHAZADO : -1;

        try {
            GestorBaseDatos.ActualizoArticuloEstado(articulo.getArticulo_id(), estado);
        } catch (InstantiationException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void comboEvaluadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEvaluadoresItemStateChanged

    }//GEN-LAST:event_comboEvaluadoresItemStateChanged

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        try {
                        
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Seleccione ruta para guardar el archivo");
            //jfc.setSelectedFile(new File(tituloToArchivo()));
            int resultado = jfc.showSaveDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                GestorBaseDatos.BuscarArticuloPDF(articulo.getArticulo_id(),jfc.getSelectedFile().getAbsolutePath());
                //File miPDF= new File(jfc.getSelectedFile().getAbsolutePath());
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
    
    private String tituloToArchivo() {
       String titulo = articulo.getTitulo();
       
       return titulo.replace(" ", "_").concat(".pdf");
    }
    
    private int getCalificacion() throws Exception {

        int i = 4;

        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return i - 2;
            }

            i--;
        }

        throw new Exception();
    }
    
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
            Logger.getLogger(AceptacionArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboEvaluadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAutores;
    private javax.swing.JLabel labelCalificacion;
    private javax.swing.JLabel labelEvaluaciones;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelPromedio;
    private javax.swing.JLabel labelResumen;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextArea taComentariosAut;
    private javax.swing.JTextArea taComentariosOrg;
    // End of variables declaration//GEN-END:variables
}
