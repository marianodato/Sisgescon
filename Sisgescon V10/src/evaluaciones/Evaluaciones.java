/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluaciones;

import BD.GestorBaseDatos;
import Clases.Articulo;
import Clases.ArticulosEvaluaciones;
import java.util.ArrayList;
import Clases.Usuario;
import comun.Constantes;
import congresos.Congreso;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlumnoUB
 */
public class Evaluaciones {
    
    private ArrayList<Evaluacion> evaluaciones;
    
    public Evaluaciones(Congreso c) {
        evaluaciones = new ArrayList<>();
            
            try {
                Vector<ArticulosEvaluaciones> tmp = new Vector<>();
                tmp = GestorBaseDatos.BuscarArticulosEvaluacionesXCongreso(c.getId());
                
                for (ArticulosEvaluaciones ae : tmp) {
                    
                    if (ae.getAev_calificacion() != Constantes.E_NO_EVALUADO) {
                        Evaluacion a = new Evaluacion();
                        a.setArticulo(GestorBaseDatos.BuscarArticulosXId(ae.getArticuloId()).get(0));
                        a.setEvaluador(GestorBaseDatos.BuscarUsuario(String.valueOf(ae.getAev_usuario_id()), "u_id").get(0));
                        a.setCalificacion(ae.getAev_calificacion());
                        a.setFecha(ae.getAev_fecha());
                        a.setId(ae.getAev_id());
                        a.setComentarioOrganizador(ae.getAev_comentario_organizador());
                        a.setComentarioAutor(ae.getAev_comentario_autor());
                        evaluaciones.add(a);
                    }
                }

            } catch (InstantiationException ex) {
                Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    public void agregar(Evaluacion e) {
        this.evaluaciones.add(e);
        
        java.sql.Date fecha = new Date(0);
        
        try {
            GestorBaseDatos.AgregarArticulosEvaluaciones(e.getEvaluador().getI_Id(), e.getCalificacion(), e.getComentarioAutor(), e.getComentarioOrganizador(), fecha, e.getArticulo().getArticulo_id());
        } catch (InstantiationException ex) {
            Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ArrayList<Evaluacion> getEvaluaciones(Usuario evaluador) {
        ArrayList<Evaluacion> resultado = new ArrayList<>();
        for (Evaluacion e : evaluaciones) {
            System.out.println("Evaluacion:" + e.getArticulo().getArticulo_id() + ", " + e.getEvaluador().getI_Id());
            if (e.getEvaluador().getI_Id() == evaluador.getI_Id()) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    public ArrayList<Evaluacion> getEvaluaciones(Articulo articulo) {
        ArrayList<Evaluacion> resultado = new ArrayList<>();
        for (Evaluacion e : evaluaciones) {
            if (e.getArticulo().getArticulo_id() == articulo.getArticulo_id()) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    public ArrayList<Usuario> getEvaluadores(Articulo articulo) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        for (Evaluacion e : evaluaciones) {
            if (articulo != null) {
                if (e.getArticulo().getArticulo_id() != articulo.getArticulo_id())
                    continue;
            }
            resultado.add(e.getEvaluador());
        }
        
        return resultado;
    }
    
    public ArrayList<Articulo> getArticulos(Usuario evaluador) {
        ArrayList<Articulo> resultado = new ArrayList<>();
        for (Evaluacion e : evaluaciones) {
            if (evaluador != null) {
                if (e.getEvaluador().getI_Id() != evaluador.getI_Id())
                    continue;
            }
            resultado.add(e.getArticulo());
        }
        
        return resultado;
    }
    
    public Evaluacion getEvaluacion(Articulo articulo, Usuario evaluador) {
        for (Evaluacion e : evaluaciones) {
            if (e.getArticulo().getArticulo_id() == articulo.getArticulo_id() && e.getEvaluador().getI_Id() == evaluador.getI_Id())
                return e;
        }
        
        return null;
    }
    
}
