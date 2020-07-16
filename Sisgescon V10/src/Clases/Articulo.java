package Clases;

import BD.GestorBaseDatos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martinlorenzo
 */
public class Articulo{
   
    private int articulo_id;
    private int congreso_id;
    private String titulo;
    private String resumen;
    private int estado;
    private int idioma;
    
    public Articulo(int articulo_id, int congreso_id,String titulo, String resumen, int estado, int idioma){
        this.articulo_id=articulo_id;
        this.congreso_id=congreso_id;
        this.titulo=titulo;
        this.resumen=resumen;
        this.estado = estado;
        this.idioma = idioma;
    }

    public Articulo(int congreso_id,String titulo, String resumen, int estado, int idioma){
        this(0, congreso_id, titulo, resumen, estado, idioma);
    }
    
    public Articulo(){
        
    }

    public int getCongreso_id() {
        return congreso_id;
    }

    public void setCongreso_id(int congreso_id) {
        this.congreso_id = congreso_id;
    }
    
    public int getArticulo_id() {
        return articulo_id;
    }

    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }
    
     public Usuario getAutor() {
        try {
            return GestorBaseDatos.BuscarUsuarioXArticulo(getArticulo_id()).get(0);
        } catch (InstantiationException ex) {
            Logger.getLogger(articulos.Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(articulos.Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(articulos.Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(articulos.Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
