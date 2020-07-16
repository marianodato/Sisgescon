package Clases;

import java.sql.Date;

public class Congreso extends congresos.Congreso {

    /*private int i_Id;
     private String s_nombre;
     private Date d_inicio;
     private Date d_fin;
     private String s_sede;
     private Date d_inicio_pres_articulos;
     private Date d_fin_pres_articulos;
     private String s_acronimo;
     private String s_sitioweb;
     private String s_mail;
     private int i_anio;
     private Date d_inicio_eval_articulos;
     private Date d_fin_eval_articulos;
     private Date d_inicio_conf_articulos;
     private Date d_fin_conf_articulos;
     private Date d_fin_versiones_articulos;
     private Date d_fin_notificacion_autores;*/
    public Congreso(int i_Id, String s_nombre, Date d_inicio, Date d_fin, String s_sede, Date d_inicio_pres_articulos,
            Date d_fin_pres_articulos, String s_acronimo, String s_sitioweb, String s_mail, int anio,
            Date d_inicio_eval_articulos, Date d_fin_eval_articulos, Date d_inicio_conf_articulos,
            Date d_fin_conf_articulos, Date d_ini_versiones_articulos, Date d_fin_versiones_articulos, Date d_ini_notificacion_autores, Date d_fin_notificacion_autores,Date d_ini_inscripcion,Date d_fin_inscripcion) {

        super(i_Id, s_nombre, d_inicio, d_fin, s_sede, d_inicio_pres_articulos,
                d_fin_pres_articulos, s_acronimo, s_sitioweb, s_mail, anio,
                d_inicio_eval_articulos, d_fin_eval_articulos, d_inicio_conf_articulos,
                d_fin_conf_articulos, d_ini_versiones_articulos, d_fin_versiones_articulos, d_ini_notificacion_autores, d_fin_notificacion_autores, d_ini_inscripcion, d_fin_inscripcion);

        /*this.i_Id = i_Id;
         this.s_nombre = s_nombre;
         this.d_inicio = d_inicio;
         this.d_fin = d_fin;
         this.s_sede = s_sede;
         this.d_inicio_pres_articulos = d_inicio_pres_articulos;
         this.d_fin_pres_articulos = d_fin_pres_articulos;
         this.s_acronimo = s_acronimo;
         this.s_sitioweb = s_sitioweb;
         this.s_mail = s_mail;
         this.i_anio = anio;
         this.d_inicio_eval_articulos = d_inicio_eval_articulos;
         this.d_fin_eval_articulos = d_fin_eval_articulos;
         this.d_inicio_conf_articulos = d_inicio_conf_articulos;
         this.d_fin_conf_articulos = d_fin_conf_articulos;
         this.d_fin_versiones_articulos = d_fin_versiones_articulos;
         this.d_fin_notificacion_autores = d_fin_notificacion_autores;*/
    }

    public int getI_Id() {
        return (int) super.getId();
    }

    public void setI_Id(int i_Id) {
        super.setId(i_Id);
    }

    public String getS_nombre() {
        return super.getNombre();
    }

    public void setS_nombre(String s_nombre) {
        super.setNombre(s_nombre);
    }

    public Date getD_inicio() {
        return super.getInicio();
    }

    public void setD_inicio(Date d_inicio) {
        super.setInicio(d_inicio);
    }

    public Date getD_fin() {
        return super.getFin();
    }

    public void setD_fin(Date d_fin) {
        super.setFin(d_fin);
    }

    public String getS_sede() {
        return super.getSede();
    }

    public void setS_sede(String s_sede) {
        super.setSede(s_sede);
    }

    public Date getD_inicio_pres_articulos() {
        return super.getInicioRecepcionArt();
    }

    public void setD_inicio_pres_articulos(Date d_inicio_pres_articulos) {
        super.setInicioRecepcionArt(d_inicio_pres_articulos);
    }

    public Date getD_fin_pres_articulos() {
        return super.getFinRecepcionArt();
    }

    public void setD_fin_pres_articulos(Date d_fin_pres_articulos) {
        super.setFinRecepcionArt(d_fin_pres_articulos);
    }

    public String getS_acronimo() {
        return super.getAcronimo();
    }

    public void setS_acronimo(String s_acronimo) {
        super.setAcronimo(s_acronimo);
    }

    public String getS_sitioweb() {
        return super.getSitioWeb();
    }

    public void setS_sitioweb(String s_sitioweb) {
        super.setSitioWeb(s_sitioweb);
    }

    public String getS_mail() {
        return super.getEmail();
    }

    public void setS_mail(String s_mail) {
        super.setEmail(s_mail);
    }

    public int getI_Anio() {
        return super.getAno();
    }

    public void setI_Anio(int anio) {
        super.setAno(anio);
    }

    public Date getD_inicio_eval_articulos() {
        return super.getInicioEvaluacion();
    }

    public void setD_inicio_eval_articulos(Date d_inicio_eval_articulos) {
        super.setInicioEvaluacion(d_inicio_eval_articulos);
    }

    public Date getD_fin_eval_articulos() {
        return super.getFinEvaluacion();
    }

    public void setD_fin_eval_articulos(Date d_fin_eval_articulos) {
        super.setFinEvaluacion(d_fin_eval_articulos);
    }

    public Date getD_inicio_conf_articulos() {
        return super.getInicioConflictosArt();
    }

    public void setD_inicio_conf_articulos(Date d_inicio_conf_articulos) {
        super.setInicioConflictosArt(d_inicio_conf_articulos);
    }

    public Date getD_fin_conf_articulos() {
        return super.getFinConflictosArt();
    }

    public void setD_fin_conf_articulos(Date d_fin_conf_articulos) {
        super.setFinConflictosArt(d_fin_conf_articulos);
    }

    public Date getD_fin_versiones_articulos() {
        return super.getFinVersionFinalArt();
    }

    public void setD_fin_versiones_articulos(Date d_fin_versiones_articulos) {
        super.setFinVersionFinalArt(d_fin_versiones_articulos);
    }

    public Date getD_fin_notificacion_autores() {
        return super.getFinNotificacionAutores();
    }

    public void setD_fin_notificacion_autores(Date d_fin_notificacion_autores) {
        super.setFinNotificacionAutores(d_fin_notificacion_autores);
    }

    public Date getD_ini_versiones_articulos() {
        return super.getInicioVersionFinalArt();
    }

    public void setD_ini_versiones_articulos(Date d_ini_versiones_articulos) {
        super.setInicioVersionFinalArt(d_ini_versiones_articulos);
    }

    public Date getD_ini_notificacion_autores() {
        return super.getInicioNotificacionAutores();
    }

    public void setD_ini_notificacion_autores(Date d_ini_notificacion_autores) {
        super.setInicioNotificacionAutores(d_ini_notificacion_autores);
    }
    
    public Date getD_ini_inscripcion() {
        return super.getInicioInscripcion();
    }

    public void setD_ini_inscripcion(Date d_ini_notificacion_autores) {
        super.setInicioInscripcion(d_ini_notificacion_autores);
    }
    
    public Date getD_fin_inscripcion() {
        return super.getFinInscripcion();
    }

    public void setD_fin_inscripcion(Date d_ini_notificacion_autores) {
        super.setFinInscripcion(d_ini_notificacion_autores);
    }


}
