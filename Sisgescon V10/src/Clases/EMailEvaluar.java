/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author PABECASIS
 */
public class EMailEvaluar {
    private String u_mail;
    private int aev_calificacion;
    private Date c_fin_eval_articulos;

    public EMailEvaluar(String u_mail, int aev_calificacion, Date c_fin_eval_articulos) {
        this.u_mail = u_mail;
        this.aev_calificacion = aev_calificacion;
        this.c_fin_eval_articulos = c_fin_eval_articulos;
    }
       
    /**
     * @return the u_mail
     */
    public String getU_mail() {
        return u_mail;
    }

    /**
     * @param u_mail the u_mail to set
     */
    public void setU_mail(String u_mail) {
        this.u_mail = u_mail;
    }

    /**
     * @return the aev_calificacion
     */
    public int getAev_calificacion() {
        return aev_calificacion;
    }

    /**
     * @param aev_calificacion the aev_calificacion to set
     */
    public void setAev_calificacion(int aev_calificacion) {
        this.aev_calificacion = aev_calificacion;
    }

    /**
     * @return the c_fin_eval_articulos
     */
    public Date getC_fin_eval_articulos() {
        return c_fin_eval_articulos;
    }

    /**
     * @param c_fin_eval_articulos the c_fin_eval_articulos to set
     */
    public void setC_fin_eval_articulos(Date c_fin_eval_articulos) {
        this.c_fin_eval_articulos = c_fin_eval_articulos;
    }
    
}
