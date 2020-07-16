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
public class Mail {
    private int m_id,m_idioma_id,m_tipo;
    private String m_descripcion;
    public Mail(String m_descripcion, int m_id, int m_idioma_id, int m_tipo) {
        this.m_id=m_id;
        this.m_idioma_id=m_idioma_id;
        this.m_tipo=m_tipo;
        this.m_descripcion=  m_descripcion;
    }

    /**
     * @return the m_id
     */
    public int getM_id() {
        return m_id;
    }

    /**
     * @param m_id the m_id to set
     */
    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    /**
     * @return the m_idioma_id
     */
    public int getM_idioma_id() {
        return m_idioma_id;
    }

    /**
     * @param m_idioma_id the m_idioma_id to set
     */
    public void setM_idioma_id(int m_idioma_id) {
        this.m_idioma_id = m_idioma_id;
    }

    /**
     * @return the m_tipo
     */
    public int getM_tipo() {
        return m_tipo;
    }

    /**
     * @param m_tipo the m_tipo to set
     */
    public void setM_tipo(int m_tipo) {
        this.m_tipo = m_tipo;
    }

    /**
     * @return the m_descripcion
     */
    public String getM_descripcion() {
        return m_descripcion;
    }

    /**
     * @param m_descripcion the m_descripcion to set
     */
    public void setM_descripcion(String m_descripcion) {
        this.m_descripcion = m_descripcion;
    }
    
}
