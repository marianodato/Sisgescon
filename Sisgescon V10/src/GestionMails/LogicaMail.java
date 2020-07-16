/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMails;

import GestionMails.Mail;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 */
public class LogicaMail {
    private Mail mail;
    private Mail mailEvaluadores;
    private String nombreCongreso = "";
    private String acronimoCongreso = "";
    private String mailCongreso = "";
    private String sitioWebCongreso = "";
    private String sedeCongreso = "";
    private String asignacionArticulos = "";
    private Date fechaRecordatorio;
    private String evaluacion="";
    private final Mensajero mensajero;

    
    
    
    public LogicaMail() {
        this.mensajero = new Mensajero();
    }

    
    public void construirCuerpoEvaluadores(){
            if (this.mailEvaluadores.getIdioma() == 1 && (!this.nombreCongreso.equals(""))) {
            this.mailEvaluadores.addCuerpo("\n Name:  "+ this.nombreCongreso + "\n Acronym:  "+this.acronimoCongreso + "\n Mail:  ");
            this.mailEvaluadores.addCuerpo(this.mailCongreso + "\n Website:  "+this.sitioWebCongreso + "\n Adress:  "+this.sedeCongreso + "\n\n");
            }
        if (this.mailEvaluadores.getIdioma() == 2 && (!this.nombreCongreso.equals(""))) {
            this.mailEvaluadores.addCuerpo("\n Nombre:  "+ this.nombreCongreso + "\n Acronimo:  "+this.acronimoCongreso + "\n Mail:  ");
            this.mailEvaluadores.addCuerpo(this.mailCongreso + "\n Sitio Web:  "+this.sitioWebCongreso + "\n Sede:  "+this.sedeCongreso + "\n\n");
            
        }

        if ((!this.asignacionArticulos.equals(""))) {
            if (this.mailEvaluadores.getIdioma()== 1) {
                this.mailEvaluadores.addCuerpo("\n\nPapers Assigned:\n");
            } else {
                this.mailEvaluadores.addCuerpo("\n\nArticulos Asignados:\n");
            }

            this.mailEvaluadores.addCuerpo("\n" + this.asignacionArticulos);
        }

        if(this.fechaRecordatorio!=null){
        this.mailEvaluadores.addCuerpo("\n"+fechaRecordatorio.getDay()+"/"+fechaRecordatorio.getMonth()+"/"+fechaRecordatorio.getYear());
        }
        
        
        if((!this.evaluacion.equals(""))){
        this.mailEvaluadores.addCuerpo("\n"+this.evaluacion);
            
        }
        
        
        if (this.mailEvaluadores.getIdioma()== 1) {
            this.mailEvaluadores.addCuerpo("\n\nThank you for your participation.\nBest Regards,\nCongresos UB");
        } else {
            this.mailEvaluadores.addCuerpo("\n\nGracias por su participación.\nAtentamente,\nCongresos UB");
       
        }

        mensajero.setMail(mailEvaluadores);

    
    }
    public Mail getMailEvaluadores(){
    
    return this.mailEvaluadores;
    
    }
    public void setMailEvaluadores(Mail mailEvaluadores){
    this.mailEvaluadores=mailEvaluadores;
    }
    
    
     public void setMail(Mail mail){
        this.mensajero.setMail(mail);
        this.mail=mail;
    }
    
    public Mail getMail(){
    return this.mail;
    }
   
    public void setPanel(JDesktopPane panel) {
        mensajero.setPanel(panel);
    }

    public boolean enviarH() {
        mensajero.start();
        return mensajero.getEnviado();

    }

    public boolean enviar() {
        mensajero.enviar();
        return mensajero.getEnviado();
    }

    public void setCongreso(String nombre, String acronimo, String mail, String sitioWeb, String sede) {
        this.nombreCongreso = nombre;
        this.acronimoCongreso = acronimo;
        this.mailCongreso = mail;
        this.sitioWebCongreso = sitioWeb;
        this.sedeCongreso = sede;

    }

    public boolean esMail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*"
                + "@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    public void construirCuerpo() {
        System.out.println(mail);
        System.out.println(nombreCongreso);
        if (this.mail.getIdioma() == 1 && (!this.nombreCongreso.equals(""))) {
            this.mail.addCuerpo("\n Name:  "+ this.nombreCongreso + "\n Acronym:  "+this.acronimoCongreso + "\n Mail:  ");
            this.mail.addCuerpo(this.mailCongreso + "\n Website:  "+this.sitioWebCongreso + "\n Adress:  "+this.sedeCongreso + "\n\n");
            }
        if (this.mail.getIdioma() == 2 && (!this.nombreCongreso.equals(""))) {
            this.mail.addCuerpo("\n Nombre:  "+ this.nombreCongreso + "\n Acronimo:  "+this.acronimoCongreso + "\n Mail:  ");
            this.mail.addCuerpo(this.mailCongreso + "\n Sitio Web:  "+this.sitioWebCongreso + "\n Sede:  "+this.sedeCongreso + "\n\n");
            
        }

        if ((!this.asignacionArticulos.equals(""))) {
            if (this.mail.getIdioma()== 1) {
                this.mail.addCuerpo("\n\nPapers Assigned:\n");
            } else {
                this.mail.addCuerpo("\n\nArticulos Asignados:\n");
            }

            this.mail.addCuerpo("\n" + this.asignacionArticulos);
        }

        if(this.fechaRecordatorio!=null){
        this.mail.addCuerpo("\n"+fechaRecordatorio.getDay()+"/"+fechaRecordatorio.getMonth()+"/"+fechaRecordatorio.getYear());
        }
        
        
        if((!this.evaluacion.equals(""))){
        this.mail.addCuerpo("\n"+this.evaluacion);
            
        }
        
        
        if (this.mail.getIdioma()== 1) {
            this.mail.addCuerpo("\n\nThank you for your participation.\nBest Regards,\nCongresos UB");
        } else {
            this.mail.addCuerpo("\n\nGracias por su participación.\nAtentamente,\nCongresos UB");
       
        }

        mensajero.setMail(mail);

    }

    public void setAsignacion(String asignacion) {
        this.asignacionArticulos = asignacion;

    }

    
    public void setEvaluacionLimite(Date fechaRecordatorio){
    this.fechaRecordatorio=fechaRecordatorio;
    
    }
    public void setEvaluacion(String evaluacion){
    
    this.evaluacion=evaluacion;
    
    }
}
