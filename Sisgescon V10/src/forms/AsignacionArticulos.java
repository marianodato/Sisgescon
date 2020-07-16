package forms;

//import articulos.Articulo;
//import congresos.Congreso;
//import evaluaciones.Asignacion;
//import evaluaciones.Asignaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;
//import usuarios_perfiles.Perfil;

/**
 *
 * @author juan
 */
public class AsignacionArticulos extends javax.swing.JInternalFrame {
   
    //private ArrayList<Perfil> evaluadores;
    private String[] nombreEvaluadores;
    //private ArrayList<Articulo> articulos;
    //private Asignaciones asignacionesOrig, asignaciones;
    
    //private Congreso congreso;
    private JPanel panel;
    
//    public AsignacionArticulos(Perfil perfil, Congreso congreso) {
//        initComponents();
//        
//        asignaciones = new Asignaciones();
//        asignacionesOrig = new Asignaciones();
//        
//        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setPreferredSize(new Dimension(100,500));
//        jScrollPane1.setViewportView(panel);
//        this.congreso = congreso;
//        evaluadores = congreso.getEvaluadores(perfil);
//        articulos = congreso.getArticulos(perfil);
//        
//        nombreEvaluadores = new String[evaluadores.size()];
//        
//        for (int i = 0; i < evaluadores.size(); ++i) {
//            nombreEvaluadores[i] = evaluadores.get(i).getNombre();
//        }
//        
//        jComboBox1.setModel(new DefaultComboBoxModel(nombreEvaluadores));
//        jComboBox1.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                guardarAsignaciones();
//                cargarArticulos(evaluadores.get(jComboBox1.getSelectedIndex()));
//            }
//        });
//        
//        cargarArticulos(evaluadores.get(jComboBox1.getSelectedIndex()));
//        pack();
//    }
    
//    private void guardarAsignaciones() {
//        ACheckBox a;
//        for (Component c : panel.getComponents()) {
//            a = ((ACheckBox) c);
//            //System.out.printf("El articulo #%d es %s\n", a.getArticulo().getId(), a.isSelected());
//            asignaciones.agregar(a.getAsignacion());
//        }
//    }
//    
//    private void cargarArticulos(Perfil p) {
//        ACheckBox acb;
//        articulos = congreso.getArticulos(p);
//        //jScrollPane1.getViewport().removeAll();
//        panel.removeAll();
//        
//        for (Articulo a : articulos) {
//            //jScrollPane1.getViewport().add(new JCheckBox(a.getTitulo()));
//            //panel.add(new JCheckBox(a.getTitulo()));
//            acb = new ACheckBox(p, a);
//            acb.setSelected(asignaciones.getAsignacion(p, a).esValida());
//            if (a.getAutor().esIgual(p)) acb.setEnabled(false);
//            panel.add(acb);
//        }
//        
//        panel.setPreferredSize(new Dimension(100, 10*articulos.size()));
//        
//        pack();
//    }
//    
//    private ArrayList<Asignacion> getDiferencias() {
//        ArrayList<Asignacion> diferencias = asignaciones.getAsignaciones();
//        
//        for (Asignacion oa : asignacionesOrig.getAsignaciones()) {
//            for (Asignacion a : diferencias) {
//                if (oa.esIgual(a) && oa.esValida() == a.esValida())
//                    diferencias.remove(a);
//            }
//        }
//        
//        return diferencias;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Evaluador");

        jLabel2.setText("Artículos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 522, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 644, Short.MAX_VALUE)
                        .addComponent(btnSiguiente)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
//        this.guardarAsignaciones();
        String result = "";
//        for (Asignacion a : this.getDiferencias()) {
//            result = result.concat(a.esValida() ? "+" : "-");
//            result = result.concat(" ").concat(a.getArticulo().getTitulo()).concat("/").concat(a.getEvaluador().getNombre());
//            result = result.concat("\n");
//        }
        System.out.println(result);
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}


class ACheckBox extends JCheckBox {
    
//    private Perfil evaluador;
//    private Articulo articulo;  
//    
//    public ACheckBox(Perfil p, Articulo a) {
//        super(a.getTitulo());
//        evaluador = p;
//        articulo = a;
//    }
//    
//    public Asignacion getAsignacion() {
//        return new Asignacion(articulo, evaluador, this.isSelected());
//    }
//    
//    public Articulo getArticulo() {
//        return articulo;
//    }
}
