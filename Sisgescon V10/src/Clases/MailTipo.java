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
public class MailTipo {
    private int mt_id;
    private String  mt_descripcion;

    public MailTipo(int mt_id, String mt_descripcion) {
        this.mt_id= mt_id;
        this.mt_descripcion = mt_descripcion;
    }

    /**
     * @return the mt_id
     */
    public int getMt_id() {
        return mt_id;
    }

    /**
     * @param mt_id the mt_id to set
     */
    public void setMt_id(int mt_id) {
        this.mt_id = mt_id;
    }

    /**
     * @return the mt_descripcion
     */
    public String getMt_descripcion() {
        return mt_descripcion;
    }

    /**
     * @param mt_descripcion the mt_descripcion to set
     */
    public void setMt_descripcion(String mt_descripcion) {
        this.mt_descripcion = mt_descripcion;
    }
    
}
