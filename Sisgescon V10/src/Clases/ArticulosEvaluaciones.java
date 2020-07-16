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
public class ArticulosEvaluaciones {
    private int aev_id, aev_usuario_id, aev_calificacion, ca_id, aev_articulo_id;
    private String aev_comentario_autor,aev_comentario_organizador;
    private Date aev_fecha;
    
    public ArticulosEvaluaciones(int ca_id, int aev_id, int aev_articulo_id, int aev_usuario_id, int aev_calificacion, String aev_comentario_autor, String aev_comentario_organizador, Date aev_fecha) {
        this.aev_id = aev_id;
        this.aev_usuario_id = aev_usuario_id;
        this.aev_calificacion = aev_calificacion;
        this.aev_comentario_autor = aev_comentario_autor;
        this.aev_comentario_organizador = aev_comentario_organizador;
        this.aev_fecha = aev_fecha;
        this.ca_id=ca_id;
        this.aev_articulo_id = aev_articulo_id;
    }

    /**
     * @return the aev_id
     */
    public int getCa_id() {
        return ca_id;
    }

    /**
     * @param aev_id the aev_id to set
     */
    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }
    /**
     * @return the aev_id
     */
    public int getAev_id() {
        return aev_id;
    }

    /**
     * @param aev_id the aev_id to set
     */
    public void setAev_id(int aev_id) {
        this.aev_id = aev_id;
    }

    /**
     * @return the aev_usuario_id
     */
    public int getAev_usuario_id() {
        return aev_usuario_id;
    }

    /**
     * @param aev_usuario_id the aev_usuario_id to set
     */
    public void setAev_usuario_id(int aev_usuario_id) {
        this.aev_usuario_id = aev_usuario_id;
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
     * @return the aev_comentario_autor
     */
    public String getAev_comentario_autor() {
        return aev_comentario_autor;
    }

    /**
     * @param aev_comentario_autor the aev_comentario_autor to set
     */
    public void setAev_comentario_autor(String aev_comentario_autor) {
        this.aev_comentario_autor = aev_comentario_autor;
    }

    /**
     * @return the aev_comentario_evaluador
     */
    public String getAev_comentario_organizador() {
        return aev_comentario_organizador;
    }

    /**
     * @param aev_comentario_evaluador the aev_comentario_evaluador to set
     */
    public void setAev_comentario_organizador(String aev_comentario_evaluador) {
        this.aev_comentario_organizador = aev_comentario_evaluador;
    }

    /**
     * @return the aev_fecha
     */
    public Date getAev_fecha() {
        return aev_fecha;
    }

    /**
     * @param aev_fecha the aev_fecha to set
     */
    public void setAev_fecha(Date aev_fecha) {
        this.aev_fecha = aev_fecha;
    }
    
    public int getArticuloId() {
        return aev_articulo_id;
    }

    
    
}
