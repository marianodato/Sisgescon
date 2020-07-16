/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;
import Clases.Articulo;
import Clases.AutoresSecundarios;
import congresos.Congreso;
import Clases.EmailValidator;
import BD.GestorBaseDatos;
import Clases.LimiteTextField;
import GestionMails.ControladorMail;
import GestionMails.Mail;
import javax.swing.text.AbstractDocument;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import forms.Perfiles_congresos;
import static forms.frmMain.jdpPrincipal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author martinlorenzo
 */
public class Creacion_articulo extends javax.swing.JInternalFrame {
    JDesktopPane puntero; //Articulo articulo = new Articulo();
    public static Vector<Articulo> articulos = new Vector<Articulo>();
    //DefaultListModel modelolistarticulos;
    Congreso congreso;
    ControladorMail controlador = new ControladorMail();
    EmailValidator emailValidator = new EmailValidator();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF solamente", "pdf");
    DefaultTableModel modelo;
    private int filas=0;
    private String oic;
    private int idioma =0;
    private int cantautores;
    //private byte[] pdfbytes;
    private boolean editarArticulo;
    private boolean flag;
    private boolean validar;
    FileInputStream input;
    int versionado = 1;
    
    //private boolean fueraDeFechaPres=false;

    
    

    
    /**
     * Creates new form hijo1
     */
    public Creacion_articulo(JDesktopPane puntero, Congreso congreso) {
        this.puntero = puntero;
        this.congreso = congreso;
        articulos.clear();
        
        initComponents();
        //this.setLocation((padre.getWidth()/2-this.getWidth()/2), (padre.getHeight()/2-this.getHeight()/2));
        
        jLabel3.setVisible(false);
        Date todayDate = new Date();
        Vector<Clases.Congreso> congresos = new Vector<Clases.Congreso>();
        
        try {
            congresos = GestorBaseDatos.BuscarCongresoXID(frmMain.miCongreso.getId()); 
           } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (todayDate.compareTo(congresos.firstElement().getD_fin_pres_articulos()) <= 0 && todayDate.compareTo(congresos.firstElement().getD_inicio_pres_articulos()) >= 0 || todayDate.compareTo(congresos.firstElement().getD_fin_versiones_articulos())<=0 && todayDate.compareTo(congresos.firstElement().getD_ini_versiones_articulos())>=0){
            //habilita el boton
            validar = true;
            editarBtn.setEnabled(true);
            crearBtn.setEnabled(true);
            
//fueraDeFechaPres=false;
            
        }else{
            editarBtn.setEnabled(false);  //lo deshabilita
            crearBtn.setEnabled(false);
            validar = false;
        }
        
        if(todayDate.compareTo(congresos.firstElement().getD_fin_versiones_articulos())<=0 && todayDate.compareTo(congresos.firstElement().getD_ini_versiones_articulos())>=0){
            
         jLabel3.setVisible(true);
        }
        
       
        
        
         try {
             articulos = GestorBaseDatos.CagarArticulosXCongresoUsuario(frmMain.miCongreso.getId(), frmMain.miUsuario.getI_Id());
         } catch (InstantiationException ex) {
             Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        newArtPanel.setVisible(false);
        autoresPanel.setVisible(false);
        resPreviewArea.setEnabled(false);
        txtNombrePdf.setEditable(false);
        guardarAutBtn.setVisible(false);
        //resPreviewArea.setText("Contenido extraido de la DB ");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Email");
        modelo.addColumn("Filiación");
        modelo.addColumn("Miembro OIC");
        jTableAutores.setModel(modelo); 
         
        DefaultListModel tablaarticulos = new DefaultListModel();
        for(Articulo i: articulos){
            
            tablaarticulos.addElement(i.getTitulo());
        }
        
        listaArticulos.setModel(tablaarticulos); 
        
        
        this.setLocation((jdpPrincipal.getWidth()/2-this.getWidth()/2), (jdpPrincipal.getHeight()/2-this.getHeight()/2));
    
    }
    
        
    public void mailAutomatico(int tipo, int nroIdioma, String email) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        System.out.println("0"+email);
        controlador.setPanel(puntero);
        Mail mail = new Mail(email, GestorBaseDatos.BuscarMailTipo(tipo).get(0).getMt_descripcion(), GestorBaseDatos.BuscarMailCompleto(tipo).get(0).getM_descripcion(), nroIdioma);
        controlador.setCongreso(congreso.getNombre(), congreso.getAcronimo(), congreso.getEmail(), congreso.getSitioWeb(), congreso.getSede());
        controlador.envioAutomatico(mail);
    }

    public void mailAutomatico2(int tipo, int nroIdioma, String email) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        System.out.println("1"+email);
        controlador.setPanel(puntero);
        Mail mail = new Mail(email, GestorBaseDatos.BuscarMailTipo(tipo).get(0).getMt_descripcion(), GestorBaseDatos.BuscarMailCompleto(tipo).get(0).getM_descripcion(), nroIdioma);
        controlador.envioAutomatico(mail);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoresPanel = new javax.swing.JPanel();
        guardarAutBtn = new javax.swing.JButton();
        autores = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        txtNomAutor = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        txtApeAutor = new javax.swing.JTextField();
        sigAutbtn = new javax.swing.JButton();
        atrasAutbtn = new javax.swing.JButton();
        email = new javax.swing.JLabel();
        txtEmailAutor = new javax.swing.JTextField();
        filiacion = new javax.swing.JLabel();
        txtFilAutor = new javax.swing.JTextField();
        miembroOIC = new javax.swing.JLabel();
        oicBox = new javax.swing.JCheckBox();
        borrarAutorbtn = new javax.swing.JButton();
        modifAutorbtn = new javax.swing.JButton();
        agregarAutorbtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAutores = new javax.swing.JTable();
        nuevoAutorbtn = new javax.swing.JButton();
        newArtPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        newOrEditLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pdfBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        newartAtras = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resumenTextArea = new javax.swing.JTextArea();
        tituloTxtfield = new javax.swing.JTextField();
        txtNombrePdf = new javax.swing.JTextField();
        newartSiguiente = new javax.swing.JButton();
        idiomesp = new javax.swing.JCheckBox();
        idiomingles = new javax.swing.JCheckBox();
        idiomalbl = new javax.swing.JLabel();
        misArtPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArticulos = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        resPreviewArea = new javax.swing.JTextArea();
        editarBtn = new javax.swing.JButton();
        borrarBtn = new javax.swing.JButton();
        crearBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanelABM_articulos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Sistema de Gestión de Congresos - Administrador de articulos");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        autoresPanel.setToolTipText("Autores participantes del artículo");
        autoresPanel.setLayout(null);

        guardarAutBtn.setText("Guardar");
        guardarAutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarAutBtnMouseClicked(evt);
            }
        });
        autoresPanel.add(guardarAutBtn);
        guardarAutBtn.setBounds(400, 500, 90, 30);

        autores.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        autores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autores.setText("Autores");
        autores.setToolTipText("");
        autoresPanel.add(autores);
        autores.setBounds(320, 10, 110, 30);

        nombre.setText("Nombre");
        autoresPanel.add(nombre);
        nombre.setBounds(20, 380, 80, 14);

        txtNomAutor.setDocument(new LimiteTextField(25));
        autoresPanel.add(txtNomAutor);
        txtNomAutor.setBounds(10, 400, 210, 30);

        apellido.setText("Apellido");
        autoresPanel.add(apellido);
        apellido.setBounds(20, 440, 70, 14);

        txtApeAutor.setDocument(new LimiteTextField(25));
        autoresPanel.add(txtApeAutor);
        txtApeAutor.setBounds(10, 460, 210, 30);

        sigAutbtn.setText("Siguiente");
        sigAutbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sigAutbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(sigAutbtn);
        sigAutbtn.setBounds(660, 520, 100, 23);

        atrasAutbtn.setText("Atras");
        atrasAutbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atrasAutbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(atrasAutbtn);
        atrasAutbtn.setBounds(560, 520, 100, 23);

        email.setText("Email");
        autoresPanel.add(email);
        email.setBounds(260, 380, 45, 14);

        txtEmailAutor.setDocument(new LimiteTextField(40));
        autoresPanel.add(txtEmailAutor);
        txtEmailAutor.setBounds(260, 400, 260, 30);

        filiacion.setText("Filiación");
        autoresPanel.add(filiacion);
        filiacion.setBounds(260, 440, 90, 14);

        txtFilAutor.setDocument(new LimiteTextField(40));
        autoresPanel.add(txtFilAutor);
        txtFilAutor.setBounds(260, 460, 260, 30);

        miembroOIC.setText("Miembro OIC");
        autoresPanel.add(miembroOIC);
        miembroOIC.setBounds(550, 400, 90, 14);

        oicBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oicBoxActionPerformed(evt);
            }
        });
        autoresPanel.add(oicBox);
        oicBox.setBounds(640, 390, 30, 30);

        borrarAutorbtn.setText("Borrar");
        borrarAutorbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarAutorbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(borrarAutorbtn);
        borrarAutorbtn.setBounds(200, 500, 90, 30);

        modifAutorbtn.setText("Modificar");
        modifAutorbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modifAutorbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(modifAutorbtn);
        modifAutorbtn.setBounds(300, 500, 90, 30);

        agregarAutorbtn.setText("Agregar");
        agregarAutorbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarAutorbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(agregarAutorbtn);
        agregarAutorbtn.setBounds(400, 500, 90, 30);

        jTableAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAutoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableAutores);

        autoresPanel.add(jScrollPane3);
        jScrollPane3.setBounds(4, 60, 760, 290);

        nuevoAutorbtn.setText("Limpiar Campos");
        nuevoAutorbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoAutorbtnMouseClicked(evt);
            }
        });
        autoresPanel.add(nuevoAutorbtn);
        nuevoAutorbtn.setBounds(10, 500, 140, 30);

        getContentPane().add(autoresPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 560));

        newArtPanel.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Titulo");
        newArtPanel.add(jLabel1);
        jLabel1.setBounds(10, 40, 30, 15);

        newOrEditLbl.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        newOrEditLbl.setText("Nuevo articulo");
        newArtPanel.add(newOrEditLbl);
        newOrEditLbl.setBounds(6, 6, 305, 22);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Resumen");
        newArtPanel.add(jLabel4);
        jLabel4.setBounds(10, 130, 80, 15);

        pdfBtn.setText("Buscar Archivo");
        pdfBtn.setToolTipText("Seleccione un archivo Pdf desde su computadora");
        pdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfBtnActionPerformed(evt);
            }
        });
        newArtPanel.add(pdfBtn);
        pdfBtn.setBounds(10, 400, 120, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Adjuntar Archivo");
        newArtPanel.add(jLabel5);
        jLabel5.setBounds(20, 380, 110, 15);

        newartAtras.setText("Atrás");
        newartAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newartAtrasMouseClicked(evt);
            }
        });
        newArtPanel.add(newartAtras);
        newartAtras.setBounds(480, 500, 120, 23);

        resumenTextArea.setDocument(new LimiteTextField(800));
        resumenTextArea.setColumns(20);
        resumenTextArea.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        resumenTextArea.setRows(5);
        jScrollPane2.setViewportView(resumenTextArea);

        newArtPanel.add(jScrollPane2);
        jScrollPane2.setBounds(10, 150, 750, 190);

        tituloTxtfield.setDocument(new LimiteTextField(40));
        newArtPanel.add(tituloTxtfield);
        tituloTxtfield.setBounds(10, 60, 390, 30);

        txtNombrePdf.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        newArtPanel.add(txtNombrePdf);
        txtNombrePdf.setBounds(130, 400, 270, 30);

        newartSiguiente.setText("Siguiente");
        newartSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newartSiguienteMouseClicked(evt);
            }
        });
        newArtPanel.add(newartSiguiente);
        newartSiguiente.setBounds(630, 500, 110, 23);

        idiomesp.setText("Español");
        newArtPanel.add(idiomesp);
        idiomesp.setBounds(570, 400, 120, 23);

        idiomingles.setText("Ingles");
        newArtPanel.add(idiomingles);
        idiomingles.setBounds(570, 430, 110, 23);

        idiomalbl.setText("Idioma");
        newArtPanel.add(idiomalbl);
        idiomalbl.setBounds(500, 410, 45, 14);

        getContentPane().add(newArtPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 550));

        misArtPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listaArticulos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        listaArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaArticulosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaArticulosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(listaArticulos);

        misArtPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 760, 170));

        resPreviewArea.setColumns(20);
        resPreviewArea.setRows(5);
        resPreviewArea.setToolTipText("Resumen del Articulo");
        resPreviewArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(resPreviewArea);

        misArtPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 228, 760, 192));

        editarBtn.setText("Editar");
        editarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarBtnMouseClicked(evt);
            }
        });
        misArtPanel.add(editarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 130, 40));

        borrarBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        borrarBtn.setText("Borrar");
        borrarBtn.setToolTipText("Eliminar un artículo");
        borrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarBtnMouseClicked(evt);
            }
        });
        misArtPanel.add(borrarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 130, 40));

        crearBtn.setText("Crear");
        crearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearBtnMouseClicked(evt);
            }
        });
        misArtPanel.add(crearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 140, 40));

        getContentPane().add(misArtPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Mis Articulos:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 321, 16));

        javax.swing.GroupLayout jPanelABM_articulosLayout = new javax.swing.GroupLayout(jPanelABM_articulos);
        jPanelABM_articulos.setLayout(jPanelABM_articulosLayout);
        jPanelABM_articulosLayout.setHorizontalGroup(
            jPanelABM_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelABM_articulosLayout.setVerticalGroup(
            jPanelABM_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelABM_articulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 130, 230));

        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("¡Puede subir la version final de este articulo!");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 230, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void pdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfBtnActionPerformed
        //Funcion que permite al usuario seleccionar un archivo PDF desde su computadora
        
        /*JFileChooser buscador = new JFileChooser(); //Crea el objeto chooser
        buscador.setFileFilter(filter); // pongo el filtro para los archivos
        int option = buscador.showOpenDialog(this); // Abre la ventana 
        if (option == JFileChooser.APPROVE_OPTION){ // Si hace click entonces....
            String file =buscador.getSelectedFile().getName(); // Teae el nombre del archivo.
            File pdf = buscador.getSelectedFile(); 
            txtNombrePdf.setText(file);
            pdfbytes = new byte[file.length()];
        }*/
        
        try{
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File f = chooser.getSelectedFile();
                    String filename = f.getAbsolutePath();
                    txtNombrePdf.setText(filename);
                    filename = filename.replace('\\', '/');                    
                    File file = new File(filename);
                    input = new FileInputStream(file);
                    //GestorBaseDatos.AgregarNuevoArticuloPDF(3, input, 1);  // el nuemro 3 es porque tengo un articulo que existe
                                                                           //con ese numero (deben ponerlo ustedes a si gusto), asi
                                                                           //como tambien el 1, que seria la version
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
        
        
    }//GEN-LAST:event_pdfBtnActionPerformed


    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // No hace nada.
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void crearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearBtnMouseClicked
      
        // Setea en visible el panel de creacion de articulo.
        
        if(crearBtn.isEnabled()){
        flag = true;    
        
        misArtPanel.setVisible(false);
        newOrEditLbl.setText("Nuevo Artículo");
        jPanelABM_articulos.setVisible(false);
        newArtPanel.setVisible(true);
        txtNomAutor.setText("");
        txtApeAutor.setText("");
        txtEmailAutor.setText("");
        txtFilAutor.setText("");
        oicBox.setSelected(false);
        tituloTxtfield.setText("");
        resumenTextArea.setText("");
        txtNombrePdf.setText("");
        }else{
            
            
            
        }
        
    }//GEN-LAST:event_crearBtnMouseClicked

    private void newartSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newartSiguienteMouseClicked
        //Funcion que pasa a la siguiente etapa de la creacion de un articulo.
        //Previamente se fija que todos los campos hayan sido completados.
        
        if (tituloTxtfield.getText().equals("") ){
        JOptionPane.showMessageDialog(null, "No se ha ingresado un titulo para el artículo, por favor agregue uno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        
        }
        
        else if (resumenTextArea.getText().equals("")){
        JOptionPane.showMessageDialog(null, "No se ha ingresado un resumen para el artículo, por favor agregue uno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        
        else if (txtNombrePdf.getText().equals("")){
        
            JOptionPane.showMessageDialog(null, "No se ha cargado ningún artículo, por favor cargue uno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        
        else if (idiomesp.isSelected()== false && idiomingles.isSelected() ==false){
         JOptionPane.showMessageDialog(null, "Debe seleccionar el idioma del articulo, por favor seleccione uno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        
        else if (idiomesp.isSelected() && idiomingles.isSelected() ){
         JOptionPane.showMessageDialog(null, "Debe seleccionar un solo idioma para el articulo, por favor seleccione uno solo.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        
        
        
        else {
       
      
        
        
        if (idiomesp.isSelected()){
        
           idioma = 2;
        }
        
        else if (idiomingles.isSelected()){
        idioma = 1;
        }
        
        misArtPanel.setVisible(false);
        jPanelABM_articulos.setVisible(false);
        newArtPanel.setVisible(false);
        autoresPanel.setVisible(true);
        }
        
    }//GEN-LAST:event_newartSiguienteMouseClicked

    private void oicBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oicBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_oicBoxActionPerformed

    private void agregarAutorbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarAutorbtnMouseClicked

        String [] autores = new String[5];
       // ArrayList autores = new ArrayList();

        autores[0]= txtNomAutor.getText();
        
        autores[1] = txtApeAutor.getText();
        
        autores[2]= txtEmailAutor.getText();
        
        autores[3] = txtFilAutor.getText();
        
        if (oicBox.isSelected()) {
        oic= "Si";
        
        }
        
        else oic = "No";
        
        autores[4] = oic;
        
        
         
        
        

        if (autores[0].equals("") || autores[1].equals("") ||autores[2].equals("")
                ||autores[3].equals("")){ // Se fija que los campos a modificar no esten vacios
            
         JOptionPane.showMessageDialog(null, "Los campos se encuentran vacios, Por favor complételos.", "Warnning",
        JOptionPane.WARNING_MESSAGE); // muestra error
        }
        else if(!emailValidator.validate(autores[2].trim())){
        
        JOptionPane.showMessageDialog(null, "Formato de email Incorrecto, por favor corríjalo.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
       
 
        
        
        
        else {
            
            modelo.addRow(autores);// agrega a la lista el vector de autores
            txtNomAutor.setText("");
            txtApeAutor.setText("");
            txtEmailAutor.setText("");
            txtFilAutor.setText("");
            oicBox.setSelected(false);
            //cantautores++;
        }
        
        
        
    }//GEN-LAST:event_agregarAutorbtnMouseClicked

    private void borrarAutorbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarAutorbtnMouseClicked
        // Borra un autor seleccionado de la tabla.
        int filaselec = jTableAutores.getSelectedRow();
        
        //Este if se fija que haya autores para eliminar, si es asi borra el seleccionado, caso contrario da error.
        if (filaselec >= 0){
            modelo.removeRow(filaselec);
            txtNomAutor.setText("");
            txtApeAutor.setText("");
            txtEmailAutor.setText("");
            txtFilAutor.setText("");
            oicBox.setSelected(false);
            //cantautores--;
        }
        else 
             JOptionPane.showMessageDialog(null, "No se ha seleccionado un autor para eliminar.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        
        
        
    }//GEN-LAST:event_borrarAutorbtnMouseClicked

    private void modifAutorbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifAutorbtnMouseClicked
      cantautores = jTableAutores.getRowCount();  // Funcion que modifica un autor seleccionado previamente en la talba.
      if (cantautores == 0){
          
          
          JOptionPane.showMessageDialog(null, "No hay autores para modificar, por favor agregue alguno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
          
          
      }else {
        int filaselected = jTableAutores.getSelectedRow();
        
        if (filaselected ==0 || filaselected ==1 || filaselected ==2 ||filaselected ==3 ||filaselected ==4){
          agregarAutorbtn.setVisible(false);
        guardarAutBtn.setVisible(true);
        
        
        txtNomAutor.setText(jTableAutores.getValueAt(filaselected, 0).toString());
        txtApeAutor.setText(jTableAutores.getValueAt(filaselected, 1).toString());
        txtEmailAutor.setText(jTableAutores.getValueAt(filaselected, 2).toString());
        txtFilAutor.setText(jTableAutores.getValueAt(filaselected, 3).toString());
 
        // El siguiente if solo se fija si la checkBox esta seleccionada o no y en base a eso   
        if (jTableAutores.getValueAt(filaselected,4)== "Si"){
        oicBox.setSelected(true);
        
        }
        else oicBox.setSelected(false);
        
        filas =filaselected;
        
        }
        else {JOptionPane.showMessageDialog(null, "No se ha seleccionado un autor para modificar, por favor seleccione alguno.", "Warning",
        JOptionPane.WARNING_MESSAGE);
      }
     }
    }//GEN-LAST:event_modifAutorbtnMouseClicked

    private void jTableAutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAutoresMouseClicked
        // Funcion que llena los campos de texto con los valores obtenidos de la lista, previamente cargados por el usuario.
        
        
    }//GEN-LAST:event_jTableAutoresMouseClicked

    private void nuevoAutorbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoAutorbtnMouseClicked
        // Limpia todos los campos de texto para ingresar un nuevo autor.
        
        txtNomAutor.setText("");
        txtApeAutor.setText("");
        txtEmailAutor.setText("");
        txtFilAutor.setText("");
        oicBox.setSelected(false);
        
        
        
    }//GEN-LAST:event_nuevoAutorbtnMouseClicked

    private void sigAutbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sigAutbtnMouseClicked
        // Finaliza la creacion del articulo.
        //Perfiles_congresos perfilescongresos = new Perfiles_congresos();
        cantautores = jTableAutores.getRowCount();
        
        if (cantautores==0){
        JOptionPane.showMessageDialog(null, "No hay autores agregados, por favor agregue autores al articulo.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        } else {
    
        // aca va metodo de la lista a ver si, tiene gente 
        JOptionPane.showMessageDialog(null, "Se enviará un email a todos los autores notificando "
                + "que se ha creado correctamente el artículo.", "Information",
        JOptionPane.INFORMATION_MESSAGE);
        
               int indice = 0;
            String email = "";

            while (indice < jTableAutores.getRowCount()) {
                if (indice == jTableAutores.getRowCount() - 1) {
                    email += jTableAutores.getValueAt(indice, 2);
                }else{
                email += jTableAutores.getValueAt(indice, 2) + ",";
                }
                indice++;
            
                }

            if (flag == true) {
                if (idioma == 1) {
                    try {
                        mailAutomatico(1, 1, email);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (idioma == 2) {
                    try {
                        mailAutomatico(2, 2, email);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (flag == false) {
                if (idioma == 1) {
                    try {
                        mailAutomatico2(3, 1, email);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (idioma == 2) {
                    try {
                        mailAutomatico2(4, 2, email);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
       
        autoresPanel.setVisible(false); 
        newArtPanel.setVisible(false);
        misArtPanel.setVisible(false);
        jPanelABM_articulos.setVisible(false);
        this.setVisible(false);
        if(editarArticulo==false){
            
        
        Articulo miArticulo = new Articulo();
        miArticulo.setCongreso_id(frmMain.miCongreso.getId());
        miArticulo.setTitulo(tituloTxtfield.getText());
        miArticulo.setResumen(resumenTextArea.getText());
        miArticulo.setEstado(1);
        miArticulo.setIdioma(idioma);
        
            try {
                GestorBaseDatos.AgregarArticulos(miArticulo);
                //id, titulo, resumen, estado, evaluacion, idioma
            
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            try {
                GestorBaseDatos.AgregarNuevoArticuloPDF(miArticulo.getArticulo_id(), input,versionado);
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            try {
                GestorBaseDatos.AgregarRelacionArticuloxAutor(miArticulo.getArticulo_id(),frmMain.miUsuario.getI_Id());
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                GestorBaseDatos.AgregarRelacionUsuarioxArticulo(frmMain.miUsuario.getI_Id(),miArticulo.getArticulo_id());
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i=0;i<jTableAutores.getRowCount();i++){

            try {
                GestorBaseDatos.AgregarArticulosAutoresSecundarios(miArticulo.getArticulo_id(), jTableAutores.getValueAt(i, 0).toString(),jTableAutores.getValueAt(i, 1).toString(), jTableAutores.getValueAt(i, 2).toString(),jTableAutores.getValueAt(i, 3).toString(), oic);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        }else{
            
            try {
                GestorBaseDatos.ActualizoArticulos((articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id()),tituloTxtfield.getText(), resumenTextArea.getText(), 1, idioma);
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                GestorBaseDatos.AgregarNuevoArticuloPDF((articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id()), input,versionado+1);
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i=0;i<jTableAutores.getRowCount();i++){

            try {
                GestorBaseDatos.ActualizoAutoresSecundarios(articulos.get(listaArticulos.getAnchorSelectionIndex()).getArticulo_id(), jTableAutores.getValueAt(i, 0).toString(),jTableAutores.getValueAt(i, 1).toString(), jTableAutores.getValueAt(i, 2).toString(), jTableAutores.getValueAt(i, 3).toString(),oic);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
        }
            
        }
         
        
        
    }//GEN-LAST:event_sigAutbtnMouseClicked

    private void atrasAutbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasAutbtnMouseClicked
        // TODO add your handling code here:
        autoresPanel.setVisible(false);
        misArtPanel.setVisible(false);
        jPanelABM_articulos.setVisible(false);
        newArtPanel.setVisible(true);
        
    }//GEN-LAST:event_atrasAutbtnMouseClicked

    private void newartAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newartAtrasMouseClicked
        // TODO add your handling code here:
        autoresPanel.setVisible(false); 
        newArtPanel.setVisible(false);
        misArtPanel.setVisible(true);
        jPanelABM_articulos.setVisible(true);
       
        
    }//GEN-LAST:event_newartAtrasMouseClicked

    private void editarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarBtnMouseClicked
        // TODO add your handling code here:
        String [] autores = new String[5];
        Vector<Articulo> miArticulo = new Vector<Articulo>();
        Vector<AutoresSecundarios> misAutoresSecundarios = new Vector<AutoresSecundarios>();
        
        
        
        int selectedIndex = listaArticulos.getSelectedIndex();
        if(selectedIndex != -1){
            
        if(editarBtn.isEnabled()){
        
        editarArticulo = true;
        flag = false;
        autoresPanel.setVisible(false); 
        newArtPanel.setVisible(true);
        misArtPanel.setVisible(false);
        jPanelABM_articulos.setVisible(false);
        newOrEditLbl.setText("Editar Artículo");    
            
            
        
        try {
            miArticulo = GestorBaseDatos.BuscarArticulosXID(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
           
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            misAutoresSecundarios = GestorBaseDatos.BuscarAutoresSecundarios(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Articulo i: miArticulo){    
        tituloTxtfield.setText(i.getTitulo());
        resumenTextArea.setText(i.getResumen());
        txtNombrePdf.setText(i.getTitulo()+".pdf");
        if(i.getIdioma()==1){
            idiomingles.setSelected(true);
            idiomesp.setSelected(false);
        }else{
            idiomingles.setSelected(false);
            idiomesp.setSelected(true);
        } 
        }
        
        for(AutoresSecundarios i: misAutoresSecundarios){
            
            autores[0] = i.getAss_nombre();
            autores[1] = i.getAss_apellido();
            autores[2] = i.getAss_mail();
            autores[3] = i.getAss_filiacion();
            autores[4] = i.getAss_oic();
            modelo.addRow(autores);
            
        }
        
        }else{
            
        
        
        
        
        
        }
        
        
        
        
        
        
        
        }else{
            if(validar==true){
            
            
            
            
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un articulo para editar", "Warning",
        JOptionPane.WARNING_MESSAGE);
            }else{
                
            }
        
        
        }
        
        
    }//GEN-LAST:event_editarBtnMouseClicked

    private void borrarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarBtnMouseClicked
        Vector<AutoresSecundarios> misAutoresSecundarios = new Vector<AutoresSecundarios>();
        Vector<Articulo> miArticulo = new Vector<Articulo>();
        int idiomaMiArticulo=0;
        String email="";
        int indice = 1;
        
        DefaultListModel tablaarticulos = (DefaultListModel) listaArticulos.getModel();
        int selectedIndex = listaArticulos.getSelectedIndex();
        if(selectedIndex != -1){
          
        int reply = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar este articulo?", "Mensaje", JOptionPane.YES_NO_OPTION);    
            if (reply == JOptionPane.YES_OPTION) {
        try {
            miArticulo = GestorBaseDatos.BuscarArticulosXID(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
           
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            misAutoresSecundarios = GestorBaseDatos.BuscarAutoresSecundarios(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        for(Articulo i: miArticulo){
            idiomaMiArticulo = i.getIdioma();
        }
        
         /*while (indice < jTableAutores.getRowCount()) {
                if (indice == jTableAutores.getRowCount() - 1) {
                    email += jTableAutores.getValueAt(indice, 2);
                }else{
                email += jTableAutores.getValueAt(indice, 2) + ",";
                }
                indice++;
            
                }*/
        for(AutoresSecundarios i: misAutoresSecundarios){
            System.out.println(misAutoresSecundarios.size());
            email="";
            if(indice == misAutoresSecundarios.size()){
                email += i.getAss_mail();
            }else{
                email += i.getAss_mail() + ",";
                indice++;
            }
            
            
            
        }
        
            if(idiomaMiArticulo==1){
                
                try {
                    mailAutomatico2(5,idiomaMiArticulo,email);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(idiomaMiArticulo==2){
                try {
                    mailAutomatico2(6,idiomaMiArticulo,email);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        
        
        try {
            GestorBaseDatos.EliminarArticulosAutoresSecundariosXArticuloID(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        try {
            GestorBaseDatos.EliminarRelacionArticuloxAutor(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            GestorBaseDatos.EliminarRelacionUsuarioxArticulo(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            GestorBaseDatos.EliminarArticuloPDF(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // TODO add your handling code here:
            GestorBaseDatos.EliminarArticulos(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Creacion_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Se a eliminado el articulo correctamente", "Information",
        JOptionPane.WARNING_MESSAGE);
        
            tablaarticulos.remove(selectedIndex);
           
            }else{
                
                
                
                
                
            }
            
            }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un articulo para eliminar", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_borrarBtnMouseClicked

    private void guardarAutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarAutBtnMouseClicked
        // TODO add your handling code here:
        
        //int autorselected = jTableAutores.getSelectedRow();
        
        String [] aux = new String[5];
        aux [0] = txtNomAutor.getText();
        aux [1] = txtApeAutor.getText();
        aux [2] = txtEmailAutor.getText();         
        aux [3] = txtFilAutor.getText();
        
        if (oicBox.isSelected()) {
        oic= "Si";
        
        }
        
        
        
        else oic = "No";
        
        aux[4] = oic;
        
        
        //arempty();
        
        
        if (txtNomAutor.getText().equals("") || txtApeAutor.getText().equals("") ||txtEmailAutor.getText().equals("")
                ||txtFilAutor.getText().equals("")){ // Se fija que los campos a modificar no esten vacios
            
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el autor, seleccione uno o complete los campos.", "Warning",
        JOptionPane.WARNING_MESSAGE); // muestra error
        }
        
        else if(!emailValidator.validate(txtEmailAutor.getText().trim())){ 
        // si el email esta completo pero no contiene el formato adecuado muestra error
        JOptionPane.showMessageDialog(null, "Formato de email Incorrecto, por favor corríjalo.", "Warning",
        JOptionPane.WARNING_MESSAGE);
        }
        else{ // si el email esta completo y es correcto el formato, entonces se procede a editar el usuario.
       for (int i=0 ; i<jTableAutores.getColumnCount();i++){
       
       modelo.setValueAt(aux[i], filas, i);
      
       }
         guardarAutBtn.setVisible(false);
        agregarAutorbtn.setVisible(true);
        txtNomAutor.setText("");
        txtApeAutor.setText("");
        txtEmailAutor.setText("");
        txtFilAutor.setText("");
        oicBox.setSelected(false);
        guardarAutBtn.setVisible(false);
       }  
        
        
        
    }//GEN-LAST:event_guardarAutBtnMouseClicked

    private void listaArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaArticulosMouseClicked
        
         
         
        
        
    }//GEN-LAST:event_listaArticulosMouseClicked

    private void listaArticulosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaArticulosMousePressed

    Vector<Articulo> miArticulo = new Vector<Articulo>();
        try {
            miArticulo = GestorBaseDatos.BuscarArticulosXID(articulos.get(listaArticulos.getSelectedIndex()).getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles_congresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        for(Articulo i: miArticulo){
            
            resPreviewArea.setText(i.getResumen());
        }
        
    }//GEN-LAST:event_listaArticulosMousePressed

   
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarAutorbtn;
    private javax.swing.JLabel apellido;
    private javax.swing.JButton atrasAutbtn;
    private javax.swing.JLabel autores;
    private javax.swing.JPanel autoresPanel;
    private javax.swing.JButton borrarAutorbtn;
    private javax.swing.JButton borrarBtn;
    private javax.swing.JButton crearBtn;
    private javax.swing.JButton editarBtn;
    private javax.swing.JLabel email;
    private javax.swing.JLabel filiacion;
    private javax.swing.JButton guardarAutBtn;
    private javax.swing.JLabel idiomalbl;
    private javax.swing.JCheckBox idiomesp;
    private javax.swing.JCheckBox idiomingles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanelABM_articulos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableAutores;
    public static javax.swing.JList listaArticulos;
    private javax.swing.JLabel miembroOIC;
    private javax.swing.JPanel misArtPanel;
    private javax.swing.JButton modifAutorbtn;
    private javax.swing.JPanel newArtPanel;
    private javax.swing.JLabel newOrEditLbl;
    private javax.swing.JButton newartAtras;
    private javax.swing.JButton newartSiguiente;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton nuevoAutorbtn;
    private javax.swing.JCheckBox oicBox;
    private javax.swing.JButton pdfBtn;
    private javax.swing.JTextArea resPreviewArea;
    private javax.swing.JTextArea resumenTextArea;
    private javax.swing.JButton sigAutbtn;
    private javax.swing.JTextField tituloTxtfield;
    private javax.swing.JTextField txtApeAutor;
    private javax.swing.JTextField txtEmailAutor;
    private javax.swing.JTextField txtFilAutor;
    private javax.swing.JTextField txtNomAutor;
    private javax.swing.JTextField txtNombrePdf;
    // End of variables declaration//GEN-END:variables
}
