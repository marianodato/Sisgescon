/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author PABECASIS
 */
public class AutoresSecundarios {

     private String ass_nombre, ass_apellido,ass_mail,ass_filiacion;
     private int aas_articulo_id,aas_id;
     private String ass_oic;

   

   
     
    public AutoresSecundarios(int aas,int aas_articulo_id, String ass_nombre, String ass_apellido, String ass_mail, String ass_filiacion,String ass_oic) {
        this.aas_id=aas_id;
        this.aas_articulo_id = aas_articulo_id;
        this.ass_nombre = ass_nombre;
        this.ass_apellido = ass_apellido;
        this.ass_mail = ass_mail;
        this.ass_filiacion = ass_filiacion;
        this.ass_oic = ass_oic;
    }
    
   

    public int getAas_id() {
        return aas_id;
    }

    public void setAas_id(int aas_id) {
        this.aas_id = aas_id;
    }
  

    /**
     * @return the aas_articulo_id
     */
    public int getAas_articulo_id() {
        return aas_articulo_id;
    }

    /**
     * @param aas_articulo_id the aas_articulo_id to set
     */
    public void setAas_articulo_id(int aas_articulo_id) {
        this.aas_articulo_id = aas_articulo_id;
    }

    /**
     * @return the ass_nombre
     */
    public String getAss_nombre() {
        return ass_nombre;
    }

    /**
     * @param ass_nombre the ass_nombre to set
     */
    public void setAss_nombre(String ass_nombre) {
        this.ass_nombre = ass_nombre;
    }

    /**
     * @return the ass_apellido
     */
    public String getAss_apellido() {
        return ass_apellido;
    }

    /**
     * @param ass_apellido the ass_apellido to set
     */
    public void setAss_apellido(String ass_apellido) {
        this.ass_apellido = ass_apellido;
    }

    /**
     * @return the ass_mail
     */
    public String getAss_mail() {
        return ass_mail;
    }

    /**
     * @param ass_mail the ass_mail to set
     */
    public void setAss_mail(String ass_mail) {
        this.ass_mail = ass_mail;
    }

    /**
     * @return the ass_filiacion
     */
    public String getAss_filiacion() {
        return ass_filiacion;
    }

    /**
     * @param ass_filiacion the ass_filiacion to set
     */
    public void setAss_filiacion(String ass_filiacion) {
        this.ass_filiacion = ass_filiacion;
    }

     public String getAss_oic() {
        return ass_oic;
    }

    public void setAss_oic(String ass_oic) {
        this.ass_oic = ass_oic;
    }
    /**
     * @return the ass_pais
     */
    
           
}
