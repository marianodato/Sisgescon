/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMails;

/**
 *
 * @author ortizjuan11
 */
public class Mail {

    private String destinatario;
    private String asunto;
    private String cuerpo;
    private int idioma;
    private String cuerpoOriginal;

    public Mail(String destinatario, String asunto, String cuerpo, int idioma) {
        this.destinatario = destinatario;
        this.idioma = idioma;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.cuerpoOriginal=cuerpo;

    }

    
    public void reestablecer(){
    this.cuerpo=this.cuerpoOriginal;
    }
    public String getDestinatario() {
        return destinatario;
    }
    
    
    public void addCuerpo(String agregado){
    this.cuerpo+=agregado;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

}
