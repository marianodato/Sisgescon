package gui_evaluaciones;
import Clases.Articulo;
import comun.CInternalFrame;
import comun.Constantes;
import congresos.Congreso;
import evaluaciones.Asignacion;
import evaluaciones.Asignaciones;
import evaluaciones.Evaluacion;
import evaluaciones.Evaluaciones;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Clases.Usuario;

/**
 *
 * @author alumno
 */
public class SeleccionArticulo extends CInternalFrame {

    private ArrayList<Articulo> articulos;
    private Usuario usuario;
    private Congreso congreso;
    private Evaluaciones evaluaciones;
    private Asignaciones asignaciones;
    private int perfil;
    
    public SeleccionArticulo(Usuario usuario, Congreso congreso, int perfil) {
        super("Seleccionar un Artículo");
        initComponents();
        
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(20,20,20,20));
        
        this.usuario = usuario;
        this.congreso = congreso;
        this.perfil = perfil;
        this.evaluaciones = new Evaluaciones(congreso);
        this.asignaciones = new Asignaciones(congreso);
        this.articulos = new ArrayList<>();
        
        System.out.println("perfil:" + perfil);
        
        if (perfil == Constantes.EVALUADOR) {
            ArrayList<Articulo> tmp;
            tmp = evaluaciones.getArticulos(usuario);
            for (Articulo a : tmp) {
                articulos.add(a);
            }
            tmp = asignaciones.getArticulos(usuario);
            boolean repetido;
            for (Articulo a : tmp) {
                repetido = false;
                for (Articulo ag : articulos) {
                    if (ag.getArticulo_id() == a.getArticulo_id())
                        repetido = true;
                }
                if (!repetido)
                    articulos.add(a);
            }
                
        }
        if (perfil == Constantes.ORGANIZADOR) {
            articulos = congreso.getArticulos(usuario.getI_Id());
        }
        
        if (articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos para evaluar.", "", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaArticulos.setModel(modelo);
        
        for (Articulo a : articulos) {
            modelo.addElement(a.getTitulo());
        }
        
        listaArticulos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting())
                    actualizar();
            }              
           
        });
        
        if (articulos.size() > 0) {
            listaArticulos.setSelectedIndex(0);
            actualizar();
        }
            
        else
            btnSiguiente.setEnabled(false);
        
        
    }
    
    private void actualizar() {
        if (perfil == Constantes.EVALUADOR) {
            Evaluacion e = null;
            if (articulos.size() > 0) {
                e = evaluaciones.getEvaluacion(getArticuloSeleccionado(), usuario);
                labelTitulo.setText(wrap(getArticuloSeleccionado().getTitulo()));
                
                if (e != null) {
                    labelCalificacion.setText(e.getNombreCalificacion());
                    labelFecha.setText(convertirFecha(e.getFecha()));
                    //labelFecha.setText(e.getFecha().toString());
                    labelAutores.setText(getAutores(getArticuloSeleccionado()));
                }

                else {
                    labelCalificacion.setText("Pendiente");
                    labelFecha.setText("--/--/--");
                    labelAutores.setText("-");    
                }
            }   
            else {
                labelTitulo.setText("No hay artículos para evaluar");
                labelCalificacion.setText("-");
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
                default:
                    labelCalificacion.setText("???");
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

        btnSiguiente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArticulos = new javax.swing.JList();
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
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

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (perfil == Constantes.EVALUADOR) {
            Asignacion asig = asignaciones.getAsignacion(usuario, getArticuloSeleccionado());
            this.abrirVentana(new EvaluacionArticulo(asig,
                    getEvaluacionArticulo(), usuario, congreso,
                    Constantes.SELECCION_ARTICULO, perfil));
        }
        else if (perfil == Constantes.ORGANIZADOR) {
            if (evaluaciones.getEvaluaciones(getArticuloSeleccionado()).isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Este artículo aún no ha sido evaluado", "Error", 0);
            }
            else {
                this.abrirVentana(new AceptacionArticulo(getArticuloSeleccionado(), 
                    usuario, congreso, perfil));
            }
                
        }
        
    }

    private Articulo getArticuloSeleccionado() {
        return articulos.get(this.listaArticulos.getSelectedIndex());
    }
    
    private Evaluacion getEvaluacionArticulo() {        
        return evaluaciones.getEvaluacion(getArticuloSeleccionado(), usuario);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
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
