/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMails;


import GestionMails.Mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Chiara
 */
public class Mensajero extends Thread implements Runnable {

    public static String usuario = "USER";
    public static String contraseña = "PASSWORD";
    private Mail mail;
    private JDesktopPane panel;
    public boolean enviado = false;
    public String asignacionArticulos;

    public void enviar() {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");

        props.setProperty("mail.user", usuario);
        props.setProperty("mail.password", contraseña);

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.timeout", 10000);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });
        System.out.println(session);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.mail.getDestinatario()));

            message.setSubject(this.mail.getAsunto());
            message.setText(this.mail.getCuerpo());

            Transport.send(message);
            //Base de datos guarda notificacion
            System.out.println("su mensaje ha sido enviado");
            enviado = true;

        } catch (MessagingException e) {
            enviado = false;

        }

    }

    public boolean getEnviado() {
        return this.enviado;
    }

    public void setPanel(JDesktopPane panel) {
        this.panel = panel;
    }

    
    public void setMail(Mail mail){
    this.mail=mail;
    }

    public void setAsignacion(String asignacion){
    this.asignacionArticulos=asignacion;
    
    }
    
    public void construirCuerpo(){
    if ((!this.asignacionArticulos.equals(""))) {
            if (this.mail.getIdioma()== 1) {
                this.mail.addCuerpo("\n\nPapers Assigned:\n");
            } else {
                this.mail.addCuerpo("\n\nArticulos Asignados:\n");
            }

            this.mail.addCuerpo("\n" + this.asignacionArticulos);
        }

      if (this.mail.getIdioma()== 1) {
            this.mail.addCuerpo("\n\nThank you for your participation.\nBest Regards,\nCongresos UB");
        } else {
            this.mail.addCuerpo("\n\nGracias por su participación.\nAtentamente,\nCongresos UB");
       
        }
    
    }
    
    @Override
    public void run() {
        enviar();
        if (!enviado) {

            panel.add(new GuiMail.Error());
        }

    }
}
