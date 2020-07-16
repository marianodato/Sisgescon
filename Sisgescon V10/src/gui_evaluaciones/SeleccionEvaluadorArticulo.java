package gui_evaluaciones;
import Clases.Articulo;
import comun.CInternalFrame;
import comun.Constantes;
import congresos.Congreso;
import evaluaciones.Asignaciones;
import evaluaciones.Evaluacion;
import evaluaciones.Evaluaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Clases.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class SeleccionEvaluadorArticulo extends CInternalFrame {

    private ArrayList<Articulo> articulos;
    private Usuario usuario;
    private Congreso congreso;
    private int perfil;
    private Evaluaciones evaluaciones;
    private ArrayList<Usuario> evaluadores;
    private Asignaciones asignaciones;
    
    private DefaultComboBoxModel<String> modeloEvaluadores;
    private DefaultListModel<String> modeloArticulos;
    
    private boolean bloqueo; // de listener de articulos
    
    public SeleccionEvaluadorArticulo(Usuario usuario, Congreso congreso, int perfil) {
        super("Seleccionar un Artículo");
        initComponents();
        
        labelCalificacion.setText("Pendiente");
        labelFecha.setText("--/--/--");
        labelFechaHeader.setVisible(false);
        labelFecha.setVisible(false);
        labelAutores.setText("-");  
        labelCalificacion.setText("-");
        labelFecha.setText("--/--/--");
        labelAutores.setText("-");
        
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(20,20,20,20));
        
        this.usuario = usuario;
        this.perfil = perfil;
        this.congreso = congreso;
        this.evaluaciones = new Evaluaciones(congreso);
        this.asignaciones = new Asignaciones(congreso);
        this.evaluadores = new ArrayList<>();
        this.articulos = new ArrayList<>();
        
        for (Usuario e : congreso.getEvaluadores()) {
            if (e.getI_Id() != usuario.getI_Id())
                evaluadores.add(e);
        }
        
        if (evaluadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay evaluadores en el congreso.", "", JOptionPane.INFORMATION_MESSAGE);
            cerrarVentana();
        }
        
        if (perfil == Constantes.EVALUADOR) {
            ArrayList<Articulo> tmp;
            tmp = evaluaciones.getArticulos(usuario);
            for (Articulo a : tmp)
                articulos.add(a);
        }
        if (perfil == Constantes.ORGANIZADOR) {
            articulos = congreso.getArticulos();
        }
        
        modeloArticulos = new DefaultListModel<>();
        listaArticulos.setModel(modeloArticulos);
        
        modeloEvaluadores = new DefaultComboBoxModel<>();
        comboEvaluadores.setModel(modeloEvaluadores);
         
        for (Usuario ev : evaluadores) {
            modeloEvaluadores.addElement(getNombreCompleto(ev));
        }
        
        comboEvaluadores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                bloqueo = true;
                actualizarArticulos();
                bloqueo = false;
            }
        });
        
        listaArticulos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting() && !bloqueo)
                    actualizar();
            }              
           
        });
        
        if (!articulos.isEmpty() && !evaluadores.isEmpty())
        {
            listaArticulos.setSelectedIndex(0);
            actualizarArticulos();
        }
        else {
            btnSiguiente.setEnabled(false);
        }
            
        
        
    }
    
    private Usuario getEvaluadorSeleccionado() {
        return evaluadores.get(comboEvaluadores.getSelectedIndex());
    }
    
    private void actualizar() {
        if (articulos.size() > 0) {
            if (perfil == Constantes.EVALUADOR) {
                Evaluacion e = null;
                    e = evaluaciones.getEvaluacion(getArticuloSeleccionado(), getEvaluadorSeleccionado());
                    labelTitulo.setText(wrap(getArticuloSeleccionado().getTitulo()));

                    if (e != null) {
                        labelCalificacion.setText(e.getNombreCalificacion());
                        labelFecha.setText(convertirFecha(e.getFecha()));
                        labelAutores.setText(getAutores(getArticuloSeleccionado()));
                    }

                    else {
                        labelCalificacion.setText("Pendiente");
                        labelFecha.setText("--/--/--");
                        labelAutores.setText("-");    
                    }
            }
            else if (perfil == Constantes.ORGANIZADOR) {
                labelCalificacionHeader.setText("Estado: ");
                labelFechaHeader.setVisible(false);
                labelFecha.setVisible(false);
                labelAutores.setText(getAutores(getArticuloSeleccionado()));
                labelTitulo.setText(wrap(getArticuloSeleccionado().getTitulo()));
                switch (getArticuloSeleccionado().getEstado()) {
                    case Constantes.A_PENDIENTE:
                        labelCalificacion.setText("Pendiente");
                        break;
                    case Constantes.A_ACEPTADO:
                        labelCalificacion.setText("Aceptado");
                        break;
                    case Constantes.A_RECHAZADO:
                        labelCalificacion.setText("Rechazado");
                        break;
                }
            }
        }
        else {
                labelTitulo.setText("No hay artículos evaluados");
                labelCalificacion.setText("-");
                labelFecha.setText("--/--/--");
                labelAutores.setText("-");
        }
        
        btnSiguiente.setEnabled(articulos.size() > 0);
        
    }
    
    private void actualizarArticulos() {
        modeloArticulos.removeAllElements();
        
        articulos = evaluaciones.getArticulos(getEvaluadorSeleccionado());
        
        if (perfil == Constantes.EVALUADOR) {
            articulos = filtarArticulos(evaluaciones.getArticulos(usuario));
        }

        for (Articulo a : articulos) {
            modeloArticulos.addElement(a.getTitulo());
        }
        
        if (articulos.size() > 0)
            listaArticulos.setSelectedIndex(0);
        
        actualizar();
    }
    
    private ArrayList<Articulo> filtarArticulos(ArrayList<Articulo> articulosFiltro) {
        ArrayList<Articulo> resultado = new ArrayList<>();
        
        for (Articulo a : articulos) {
            for (Articulo af : articulosFiltro) {
                if (a.getArticulo_id() == af.getArticulo_id())
                    resultado.add(a);
            }
        }
        
        return resultado;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArticulos = new javax.swing.JList();
        comboEvaluadores = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelFechaHeader = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelCalificacionHeader = new javax.swing.JLabel();
        labelCalificacion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelAutores = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Artículos"));

        listaArticulos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaArticulos);

        comboEvaluadores.setName(""); // NOI18N
        comboEvaluadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEvaluadoresItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Evaluador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addComponent(comboEvaluadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEvaluadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Artículo"));

        labelTitulo.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        labelTitulo.setText("Titulo");

        labelFechaHeader.setText("Evaluado el:");

        labelFecha.setText("10/10/10 10:10:10");

        labelCalificacionHeader.setText("Calificación:");

        labelCalificacion.setText("Excelente");

        jLabel6.setText("Autores:");

        labelAutores.setText("Yo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelCalificacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelFecha))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo)
                            .addComponent(labelAutores)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelCalificacionHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelFechaHeader))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelTitulo)
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCalificacionHeader)
                    .addComponent(labelFechaHeader))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCalificacion)
                    .addComponent(labelFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAutores)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 694, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.abrirVentana(new EvaluacionArticulo(getEvaluacionArticulo(), usuario, congreso, Constantes.SELECCION_EVALUADOR_ARTICULO, perfil)); 
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void comboEvaluadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEvaluadoresItemStateChanged

    }//GEN-LAST:event_comboEvaluadoresItemStateChanged

    private Articulo getArticuloSeleccionado() {
        return articulos.get(this.listaArticulos.getSelectedIndex());
    }
    
    private Evaluacion getEvaluacionArticulo() {
        return evaluaciones.getEvaluacion(getArticuloSeleccionado(), getEvaluadorSeleccionado());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox comboEvaluadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAutores;
    private javax.swing.JLabel labelCalificacion;
    private javax.swing.JLabel labelCalificacionHeader;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFechaHeader;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JList listaArticulos;
    // End of variables declaration//GEN-END:variables
}
