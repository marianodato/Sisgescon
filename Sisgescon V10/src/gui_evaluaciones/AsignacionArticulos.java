package gui_evaluaciones;

import BD.GestorBaseDatos;
import GestionMails.ControladorMail;
import GestionMails.Mail;
import Clases.Articulo;
import comun.BaseDatos;
import comun.CInternalFrame;
import congresos.Congreso;
import evaluaciones.Asignacion;
import evaluaciones.Asignaciones;
import evaluaciones.Evaluaciones;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Clases.Usuario;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class AsignacionArticulos extends CInternalFrame {

    private ArrayList<Usuario> evaluadores;
    private String[] nombreEvaluadores;
    private ArrayList<Articulo> articulos;
    private Asignaciones asignaciones;
    private ControladorMail controlador;
    private BaseDatos bd;
    
    private Usuario evAnterior;

    private Congreso congreso;
    private Usuario organizador;
    private JPanel panel;
    
    private ArrayList<Integer> asignacionesExistentes;

    public AsignacionArticulos(Usuario perfil, Congreso congreso) {
        super("Asignación de Artículos");
        initComponents();

        controlador = new ControladorMail();
        bd = new BaseDatos();
        
        asignacionesExistentes = new ArrayList<>();
        asignaciones = new Asignaciones(congreso);
        for (Asignacion a : asignaciones.getAsignaciones()) {
            if (a.getId() != 0)
                asignacionesExistentes.add(a.getId());
        }

        articulos = congreso.getArticulos();
        this.congreso = congreso;
        this.organizador = perfil;
        
        if (!esPermitido()) {
            this.dispose();
        }

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(100, 500));
        jScrollPane1.setViewportView(panel);

        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(40, 40, 40, 40));

        evaluadores = congreso.getEvaluadores(organizador.getI_Id());
        
        if (evaluadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay evaluadores en el congreso.", "", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }

        nombreEvaluadores = new String[evaluadores.size()];

        for (int i = 0; i < evaluadores.size(); ++i) {
            nombreEvaluadores[i] = getNombreCompleto(evaluadores.get(i));
        }

        jComboBox1.setModel(new DefaultComboBoxModel(nombreEvaluadores));
        jComboBox1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                guardarAsignaciones();
                cargarArticulos();
                actualizarListaArticulos();
                evAnterior = getEvaluadorSeleccionado();
            }
        });
        
        if (!evaluadores.isEmpty()) {
            crearListaArticulos();
            cargarArticulos();
            actualizarListaArticulos();
        
            evAnterior = getEvaluadorSeleccionado();   
        }

        pack();
    }

    private Usuario getEvaluadorSeleccionado() {
        return evaluadores.get(jComboBox1.getSelectedIndex());
    }

    private void crearListaArticulos() {
        panel.removeAll();
        ACheckBox acb;
        for (Articulo a : articulos) {
        acb = new ACheckBox(asignaciones.getAsignacion(getEvaluadorSeleccionado(), a));
        acb.setEnabled(acb.getAsignacion().getArticulo().getAutor().getI_Id() != organizador.getI_Id());
        panel.add(acb);
        panel.revalidate();
        panel.repaint();
        }
        pack();
    }

    private void actualizarAsignaciones() {
        Usuario evaluador = getEvaluadorSeleccionado();
//        for (Asignacion a : asignaciones.getAsignaciones()) {
//            if (a.getEvaluador().getI_Id() == evAnterior.getI_Id() && !a.esValida())
//                asignaciones.eliminar(a);
//        }
    }

    private void actualizarListaArticulos() {
        Usuario evaluador = getEvaluadorSeleccionado();
        for (Articulo a : articulos) {
            if (a.getAutor().getI_Id()
                    == evaluador.getI_Id()) {
                panel.getComponent(articulos.indexOf(a)).setEnabled(false);
            }
        }
        pack();
    }
    
    private void limpiarAsignaciones() {
        
    }

    private void guardarAsignaciones() {
        actualizarAsignaciones();
        ACheckBox a;
        for (Component c : panel.getComponents()) {
            a = ((ACheckBox) c);
//            if (a.getAsignacion().esValida())
                asignaciones.agregar(a.getAsignacion());
//            else
//                asignaciones.eliminar(a.getAsignacion());
        }
    }

    private void cargarArticulos() {
        ACheckBox acb;
        Usuario evaluador = getEvaluadorSeleccionado();
        Asignacion asignacion;
        panel.removeAll();
        for (Articulo a : articulos) {
            asignacion = asignaciones.getAsignacion(evaluador, a);
            acb = new ACheckBox(asignacion);
            acb.setSelected(asignacion.esValida());
            acb.setEnabled(acb.getAsignacion().getArticulo().getAutor().getI_Id() != organizador.getI_Id());
            panel.add(acb);
            panel.revalidate();
            panel.repaint();
        }

        panel.setPreferredSize(new Dimension(100, 10 * articulos.size()));

        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
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

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 507, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        guardarAsignaciones();
        try {
            
            String asunto, cuerpo, s;
            int mailId;
            
            controlador.setPanel(puntero);
            for (int i = 0; i < evaluadores.size(); i++) {
                s = getAsignaciones(i, true);
                if (s.length() > 0) {
                    mailId = 13;
                }
                else {
                    mailId = 14;
                }
                s = getAsignaciones(i, false);
                if (s.length() > 0) {
                    
                    asunto = GestorBaseDatos.BuscarMailTipo(mailId).get(0).getMt_descripcion();
                    cuerpo = GestorBaseDatos.BuscarMailCompleto(mailId).get(0).getM_descripcion();

                    cuerpo = controlador.format(cuerpo);
                    
                    Mail mail = new Mail(evaluadores.get(i).getS_mail(),
                        asunto, cuerpo, 1);
                    controlador.envioMasivo(mail, s, puntero);
                }
            }
            
            ArrayList<Asignacion> eliminadas = new ArrayList<>();
            
            eliminadas = getAsignacionesEliminadas();
            
//            ArrayList<Articulo> tmp; s = "";
//            for (Usuario evaluador: evaluadores) {
//                tmp = getArticulos(eliminadas, evaluador);
//                for (Articulo a : tmp) {
//                    s = s.concat(a.getTitulo()).concat(("\n"));
//                }
//                if (tmp.size() > 0) {
//                    // ENVIA LAS ASIGNACIONES ELIMINADAS
//                   System.out.println("Se elimina para " + getNombreCompleto(evaluador) + "\n" + s);
//                }
//            }
            
            for (Asignacion a : eliminadas) {     
                try {
                    GestorBaseDatos.EliminarArticulosEvaluaciones(a.getId());
                } catch (InstantiationException ex) {
                    Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            asignaciones.guardar();
            
            this.dispose();
        } catch (InstantiationException ex) {
            Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed
    
    private boolean esPermitido() {
        Date hoy = new java.util.Date();
        if (hoy.compareTo(congreso.getFinConflictosArt()) <= 0 && hoy.compareTo(congreso.getInicioConflictosArt()) >= 0){
            System.out.println(congreso.getFinConflictosArt());
            System.out.println(congreso.getInicioConflictosArt());
            return true;
        }
        else {
            if (hoy.compareTo(congreso.getFinConflictosArt()) > 0) {
                mostrarMensaje("El período de conflictos y preferencias ya finalizó.", "Error", JOptionPane.ERROR_MESSAGE);
            }
                
            if (hoy.compareTo(congreso.getInicioConflictosArt()) < 0) {
                mostrarMensaje("El período de conflictos y preferencias aún no ha comenzado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;           
    }
    
    public ArrayList<Asignacion> getAsignacionesEliminadas() {
        int i;
        ArrayList<Asignacion> eliminadas = new ArrayList<>();
        for (Asignacion a : asignaciones.getAsignaciones()) {
            i = asignacionesExistentes.indexOf(a.getId());
            if (i != -1 && a.esValida()) {
                asignacionesExistentes.remove(i);
            }
                
        }
        Asignacion aux;
        for (Integer id : asignacionesExistentes) {
            aux = asignaciones.getAsignacion(id);
            if (aux != null)
                eliminadas.add(aux);
        }
        return eliminadas;
    }
    
    public String getAsignaciones(int indice, boolean repetidos) {
        String u = "";
        boolean existe;
        ArrayList<Asignacion> asig = asignaciones.getAsignaciones(evaluadores.get(indice));
        
        for (Asignacion a : asig) {
            if (a.esValida()) {
                existe = false;
                for (Integer i : asignacionesExistentes) {
                    existe = existe || i == a.getId();
                }
                if (!existe || !repetidos) 
                    u = u + a.getArticulo().getTitulo() + "\n";
            }
                
        }
        return u;
    }

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

    }//GEN-LAST:event_jComboBox1ItemStateChanged
    
    private ArrayList<Articulo> getArticulos(ArrayList<Asignacion> asignaciones, Usuario evaluador) {
        ArrayList<Articulo> resultado = new ArrayList<>();
        for (Asignacion a : asignaciones) {
            if (a.getEvaluador().getI_Id() == evaluador.getI_Id())
                resultado.add(a.getArticulo());
        }
        return resultado;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

class ACheckBox extends JCheckBox {

    private Articulo articulo;
    private Asignacion asignacion;

    public ACheckBox(Asignacion a) {
        super (a.getArticulo().getTitulo());
        asignacion = a;
        articulo = a.getArticulo();
        this.setEnabled(!a.esEvaluado());
        super.setBorder(new EmptyBorder(10, 10, 0, 0));
    }

    public Asignacion getAsignacion() {
        asignacion.setValida(this.isSelected());
        return asignacion;
    }

    public Articulo getArticulo() {
        return articulo;
    }
}