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
 * @author VictoriaBalin
 * @version 1.0
 * @created 10-sep-2015 10:56:29 a.m.
 */
public class Asignaciones{

	private ArrayList<Asignacion> asignaciones;
        private ArrayList<Asignacion> nuevasAsignaciones;

	public Asignaciones(Congreso c){
            
            asignaciones = new ArrayList<>();
            nuevasAsignaciones = new ArrayList<>();
            
            try {
                Vector<ArticulosEvaluaciones> tmp = new Vector<>();
                tmp = GestorBaseDatos.BuscarArticulosEvaluacionesXCongreso(c.getId());
                
                for (ArticulosEvaluaciones ae : tmp) {

                    //if (ae.getAev_calificacion() == Constantes.E_NO_EVALUADO) {
                        Asignacion a = new Asignacion();
                        a.setArticulo(GestorBaseDatos.BuscarArticulosXId(ae.getArticuloId()).get(0));
                        a.setEvaluador(GestorBaseDatos.BuscarUsuario(String.valueOf(ae.getAev_usuario_id()), "u_id").get(0));
                        a.setValida(true);
                        a.setEvaluado(ae.getAev_calificacion() != Constantes.E_NO_EVALUADO);
                        a.setId(ae.getAev_id());
                        asignaciones.add(a);
                    //}
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
        
        public void guardar() {
            
            java.sql.Date fecha = new Date(0);
            
            for (Asignacion a : nuevasAsignaciones) {
                try {
                    if (a.esValida())
                        GestorBaseDatos.AgregarArticulosEvaluaciones(a.getEvaluador().getI_Id(), Constantes.E_NO_EVALUADO, "", null, fecha, a.getArticulo().getArticulo_id());
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
        }

	public void crear(){
            
	}
        
        public void agregar(Asignacion asignacion) {
            boolean encontrada = false;
            for (Asignacion a : asignaciones) {
                if (a.esIgual(asignacion)) {
                    a.setValida(asignacion.esValida());
                    encontrada = true;
                }
            }
            if (!encontrada) {
                asignaciones.add(asignacion);
                nuevasAsignaciones.add(asignacion);
            }
                
        }
        
        public ArrayList<Articulo> getArticulos(Usuario evaluador) {
            return getArticulos(evaluador, false);
        }
        
        public ArrayList<Articulo> getArticulos(Usuario evaluador, boolean todos) {
            ArrayList<Articulo> articulos = new ArrayList<>();
            for (Asignacion a : this.getAsignaciones(evaluador)) {
                if (todos || a.esValida())
                    articulos.add(a.getArticulo());
            }
            return articulos;
        }
        
        // db?
        public ArrayList<Asignacion> getAsignaciones(Usuario evaluador) {
            ArrayList<Asignacion> resultado = new ArrayList<>();
            for (Asignacion a : asignaciones) {
                if (evaluador.getI_Id() == a.getEvaluador().getI_Id())
                    resultado.add(a);
            }
            
            return resultado;
        }
        // db
        public Asignacion getAsignacion(Usuario evaluador, Articulo articulo) {
            Asignacion asignacion = new Asignacion(evaluador, articulo);
            for (Asignacion as : asignaciones) {
                if (asignacion.esIgual(as))
                    return as;
            }
            return asignacion;
        }
        
        public Asignacion getAsignacion(int id) {
            for (Asignacion as : asignaciones) {
                if (as.getId() == id) {
                    return as;
                }
                    
            }
            return null;
        }

	public ArrayList<Asignacion> getAsignaciones(){
            return asignaciones;
	}
        
        public void eliminar(Asignacion asignacion) {
            Asignacion tmp = null;
            for (Asignacion a : asignaciones) {
                if (asignacion.esIgual(a))
                    tmp = a;
            }
            if (tmp != null) {
                asignaciones.remove(tmp);
            }
            for (Asignacion a : nuevasAsignaciones) {
                if (asignacion.esIgual(a))
                    tmp = a;
            }
            if (tmp != null) {
                nuevasAsignaciones.remove(tmp);
            }
                
        }
        
}//end Asignaciones