package GestionMails;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Clases.EMailEvaluar;
import GestionMails.Mail;
import GestionMails.InterfaceEvaluacion;
import GuiMail.VistaMail;
import GestionMails.LogicaMail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author Chiara
 */
public class ControladorMail {

    private LogicaMail logica;
    private JFrame frame;
    private VistaMail vista;
    private JDesktopPane panel;

    public ControladorMail() {

        logica = new LogicaMail();
    }
    
    public String arrayToString(Vector <EMailEvaluar> misEvaluadores){
        String cadenas="";
        for (int i=0;i<misEvaluadores.size();i++){
            if (i!=misEvaluadores.size()-1){
                cadenas+=misEvaluadores.get(i).getU_mail()+ ",";
            }
            else {
                cadenas+=misEvaluadores.get(i).getU_mail();
            }
        }
        return cadenas;
    }
            
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setPanel(JDesktopPane panel) {
        this.panel = panel;
        logica.setPanel(panel);

    }

    //Mail manual   invitaciones
    public void IniciarMail(Mail mail) {
        logica.setMail(mail);
        logica.construirCuerpo();
        mail = logica.getMail();
        this.vista = new VistaMail(this);
        this.vista.setJTextField1(mail.getDestinatario());
        this.vista.setJTextField2(mail.getAsunto());
        this.vista.setJArea(mail.getCuerpo());
        this.panel.add(vista);

    }

    public void EnviarInvitaciones(Mail mailOrganizadores, Mail mailEvaluadores) {
        if (mailOrganizadores.getDestinatario().equals("") || mailEvaluadores.getDestinatario().equals("")) {
            if (mailOrganizadores.getDestinatario().equals("")) {
                logica.setMail(mailEvaluadores);
                logica.construirCuerpo();
                mailEvaluadores = logica.getMail();
                this.vista = new VistaMail(this);
                this.vista.setJTextField1(mailEvaluadores.getDestinatario());
                this.vista.setJTextField2(mailEvaluadores.getAsunto());
                this.vista.setJArea(mailEvaluadores.getCuerpo());
                this.panel.add(vista);
            } else {
                logica.setMail(mailOrganizadores);
                logica.construirCuerpo();
                mailOrganizadores = logica.getMail();
                this.vista = new VistaMail(this);
                this.vista.setJTextField1(mailOrganizadores.getDestinatario());
                this.vista.setJTextField2(mailOrganizadores.getAsunto());
                this.vista.setJArea(mailOrganizadores.getCuerpo());
                this.panel.add(vista);

            }

        } else {
            logica.setMail(mailOrganizadores);
            logica.setMailEvaluadores(mailEvaluadores);
            logica.construirCuerpo();
            mailOrganizadores = logica.getMail();
            this.vista = new VistaMail(this);
            this.vista.setJTextField1(mailOrganizadores.getDestinatario());
            this.vista.setJTextField2(mailOrganizadores.getAsunto());
            this.vista.setJArea(mailOrganizadores.getCuerpo());
            this.vista.setInvitaciones(true);
            this.panel.add(vista);
        }

    }

    public void construirCuerpoEvaluadores(VistaMail vista) {

        logica.construirCuerpoEvaluadores();
        Mail mail = logica.getMailEvaluadores();
        this.vista = new VistaMail(this);
        vista.setJTextField1(mail.getDestinatario());
        vista.setJTextField2(mail.getAsunto());
        vista.setJArea(mail.getCuerpo());
        

    }

    public void setCongreso(String nombre, String acronimo, String mail, String sitioWeb, String sede) {//a confirmar datos del congreso
        this.logica.setCongreso(nombre, acronimo, mail, sitioWeb, sede);

    } // recepcion/modificacion/eliminacion/aceptacion articulo/solicitudConflictosYpreferencias   

    public void envioAutomatico(Mail mail) {
        this.logica.setMail(mail);
        this.logica.construirCuerpo();
        enviarH();
    }

    public void envioMasivo(Mail mail,String asignacion,JDesktopPane panel){
    Mensajero mensajero=new Mensajero();
    mensajero.setPanel(panel);
    mensajero.setAsignacion(asignacion);    
    mensajero.setMail(mail);
    mensajero.construirCuerpo();
    mensajero.start();
    
    }
    
    public void setAsignacion(String asignacion) {
        this.logica.setAsignacion(asignacion);

    }

    public boolean sonMails(String mails) {
        String[] arrayMails = mails.split(",");
        int indice = 0;
        while (indice < arrayMails.length) {

            if (!(this.logica.esMail(arrayMails[indice]))) {
                return false;
            }

            indice++;
        }

        return true;

    }

    public void EnvioRecordatorio(Mail mail) {
        if ((!mail.getDestinatario().equals(""))) {

            envioAutomatico(mail);

        }

    }

    public boolean enviarH() {
        //envio de un mail por un hilo paralelo
        return this.logica.enviarH();
    }

    public boolean enviar() {
        return this.logica.enviar();
    }

    public void setMail(Mail mail) {
        this.logica.setMail(mail);

    }

    public Mail getMail() {
        return this.logica.getMail();
    }

    public int getIdioma() {
        return this.logica.getMail().getIdioma();
    }

    public void setEvaluacionLimite(Date date) {

        this.logica.setEvaluacionLimite(date);
    }

    public void setEvaluacion(ArrayList<InterfaceEvaluacion> evaluaciones) {
        String CadenaEvaluaciones = "";
        InterfaceEvaluacion e;
        CadenaEvaluaciones += evaluaciones.get(0).getNombreArticulo();
        for (int i = 0; i < evaluaciones.size(); i++) {
            e = evaluaciones.get(i);
            System.out.println(e.getNombreArticulo() + " " + e.getComentario());
            CadenaEvaluaciones += "\n\n" + "Evaluacion" + (i + 1) + "\nCalificacion: " + evaluaciones.get(i).getCalificacion() + "\n" + evaluaciones.get(i).getComentario() + "\n\n";
        }

        this.logica.setEvaluacion(CadenaEvaluaciones);
    }
    
    public String format(String cuerpo){
    int indice=0;
    String cadena="";
        while(cuerpo.charAt(indice)!=','){
        cadena+=cuerpo.charAt(indice);
            indice++;
        }
        cadena+=",\n";
        indice++;
        int espacio=0;
        while(indice<cuerpo.length()){
            if(cuerpo.charAt(indice)==' '){
            espacio++;
            if(espacio==10){
            cadena+="\n";
            espacio=0;
            }
            else{
            cadena+=" ";
            }
            }else{
            cadena+=cuerpo.charAt(indice);
            }
            
            indice++;
        
        }
        System.out.println(cadena);
        cadena+="\n";
        return cadena;
        
    }

}
