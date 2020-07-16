package congresos;
import BD.GestorBaseDatos;
import Clases.ArticulosEvaluaciones;
import Clases.Articulo;
import comun.Constantes;
import comun.Modelo;
import evaluaciones.Asignacion;
import evaluaciones.Asignaciones;
import evaluaciones.Evaluacion;
import evaluaciones.Evaluaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Clases.Usuario;

/**
 * @author VictoriaBalin
 * @version 1.0
 * @created 12-sep-2015 11:43:47 a.m.
 */
public class Congreso extends Modelo{

    private String acronimo;
    private int ano;
    private String email;
    private Date fin;
    private Date finInscripcion;
    private Date finConflictosArt;
    private Date finEvaluacion;
    private Date finNotificacionAutores;
    private Date finRecepcionArt;
    private Date finVersionFinalArt;
    private Date inicio;
    private Date inicioInscripcion;
    private Date inicioConflictosArt;
    private Date inicioEvaluacion;
    private Date inicioNotificacionAutores;
    private Date inicioRecepcionArt;
    private Date inicioVersionFinalArt;
    private String nombre;
    private String sede;
    private String sitioWeb;

    /*public Congreso(String acronimo, int ano, String email, Date fin, Date finInscripcion, Date finConflictosArt, Date finEvaluacion, Date finNotificacionAutores, Date finRecepcionArt, Date finVersionFinalArt, Date inicio, Date inicioInscripcion, Date inicioConflictosArt, Date inicioEvaluacion, Date inicioNotificacionAutores, Date inicioRecepcionArt, Date inicioVersionFinalArt, String nombre, String sede, String sitioWeb) {
        this.acronimo = acronimo;
        this.ano = ano;
        this.email = email;
        this.fin = fin;
        this.finInscripcion = finInscripcion;
        this.finConflictosArt = finConflictosArt;
        this.finEvaluacion = finEvaluacion;
        this.finNotificacionAutores = finNotificacionAutores;
        this.finRecepcionArt = finRecepcionArt;
        this.finVersionFinalArt = finVersionFinalArt;
        this.inicio = inicio;
        this.inicioInscripcion = inicioInscripcion;
        this.inicioConflictosArt = inicioConflictosArt;
        this.inicioEvaluacion = inicioEvaluacion;
        this.inicioNotificacionAutores = inicioNotificacionAutores;
        this.inicioRecepcionArt = inicioRecepcionArt;
        this.inicioVersionFinalArt = inicioVersionFinalArt;
        this.nombre = nombre;
        this.sede = sede;
        this.sitioWeb = sitioWeb;
    }*/
    
    public Congreso() {
        
    }
    
    public Congreso(int i_Id, String s_nombre, Date d_inicio, Date d_fin, String s_sede, Date d_inicio_pres_articulos,
			Date d_fin_pres_articulos, String s_acronimo, String s_sitioweb, String s_mail, int anio,
			Date d_inicio_eval_articulos, Date d_fin_eval_articulos, Date d_inicio_conf_articulos,
			Date d_fin_conf_articulos, Date d_ini_versiones_articulos, Date d_fin_versiones_articulos, Date d_ini_notificacion_autores, Date d_fin_notificacion_autores,
                        Date d_ini_inscripcion,Date d_fin_inscripcion) {
     
        this.setId(i_Id);
        this.nombre = s_nombre;
        this.inicio = d_inicio;
        this.fin = d_fin;
        this.sede = s_sede;
        this.inicioRecepcionArt = d_inicio_pres_articulos;
        this.finRecepcionArt = d_fin_pres_articulos;
        this.acronimo = s_acronimo;
        this.sitioWeb = s_sitioweb;
        this.email = s_mail;
        this.ano = anio;
        this.inicioEvaluacion = d_inicio_eval_articulos;
        this.finEvaluacion = d_fin_eval_articulos;
        this.inicioConflictosArt = d_inicio_conf_articulos;
        this.finConflictosArt = d_fin_conf_articulos;
        this.inicioVersionFinalArt = d_ini_versiones_articulos;
        this.finVersionFinalArt = d_fin_versiones_articulos;
        this.inicioNotificacionAutores = d_ini_notificacion_autores;
        this.finNotificacionAutores = d_fin_notificacion_autores;
        this.inicioInscripcion = d_ini_inscripcion;
        this.finInscripcion = d_fin_inscripcion;
    }

    public Congreso(String acronimo, int ano, String email, String nombre, 
            String sede, String sitioWeb) {
        this.acronimo = acronimo;
        this.ano = ano;
        this.email = email;
        this.nombre = nombre;
        this.sede = sede;
        this.sitioWeb = sitioWeb;
    }
//    public Congreso(Date fin, 
//            Date finInscripcion, Date finConflictosArt, Date finEvaluacion, 
//            Date finNotificacionAutores, Date finRecepcionArt, 
//            Date finVersionFinalArt, Date inicio, Date inicioInscripcion, 
//            Date inicioConflictosArt, Date inicioEvaluacion, 
//            Date inicioNotificacionAutores, Date inicioRecepcionArt, 
//            Date inicioVersionFinalArt){
//        this.fin = fin;
//        this.finInscripcion = finInscripcion;
//        this.finConflictosArt = finConflictosArt;
//        this.finEvaluacion = finEvaluacion;
//        this.finNotificacionAutores = finNotificacionAutores;
//        this.finRecepcionArt = finRecepcionArt;
//        this.finVersionFinalArt = finVersionFinalArt;
//        this.inicio = inicio;
//        this.inicioInscripcion = inicioInscripcion;
//        this.inicioConflictosArt = inicioConflictosArt;
//        this.inicioEvaluacion = inicioEvaluacion;
//        this.inicioNotificacionAutores = inicioNotificacionAutores;
//        this.inicioRecepcionArt = inicioRecepcionArt;
//        this.inicioVersionFinalArt=inicioVersionFinalArt;
//    }

    public void cancelar() {
        this.fin = null;
        this.finInscripcion =null;
        this.finConflictosArt = null;
        this.finEvaluacion = null;
        this.finNotificacionAutores = null;
        this.finRecepcionArt = null;
        this.finVersionFinalArt = null;
        this.inicio = null;
        this.inicioInscripcion = null;
        this.inicioConflictosArt = null;
        this.inicioEvaluacion = null;
        this.inicioNotificacionAutores = null;
        this.inicioRecepcionArt = null;
    }
    
    public String getAcronimo() {
        return acronimo;
    }

    public int getAno() {
        return ano;
    }

    public String getEmail() {
        return email;
    }

    public Date getFin() {
        return fin;
    }
    
    public Date getFinInscripcion() {
        return finInscripcion;
    }

    public Date getFinConflictosArt() {
        return finConflictosArt;
    }

    public Date getFinEvaluacion() {
        return finEvaluacion;
    }

    public Date getFinNotificacionAutores() {
        return finNotificacionAutores;
    }

    public Date getFinRecepcionArt() {
        return finRecepcionArt;
    }

    public Date getFinVersionFinalArt() {
        return finVersionFinalArt;
    }

    public Date getInicio() {
        return inicio;
    }
    
    public Date getInicioInscripcion() {
        return inicioInscripcion;
    }

    public Date getInicioConflictosArt() {
        return inicioConflictosArt;
    }

    public Date getInicioEvaluacion() {
        return inicioEvaluacion;
    }

    public Date getInicioNotificacionAutores() {
        return inicioNotificacionAutores;
    }

    public Date getInicioRecepcionArt() {
        return inicioRecepcionArt;
    }
    
    public Date getInicioVersionFinalArt(){
        return inicioVersionFinalArt;
    }

    public String getNombre(){
        return nombre;
    }
    
    public String getSede() {
        return sede;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo=acronimo;
    }

    public void setAno(int ano) {
        this.ano=ano;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setFin(Date fin) {
        this.fin=fin;
    }
    
    public void setFinInscripcion(Date finInscripcion){
        this.finInscripcion=finInscripcion;
    }
    
    public void setFinConflictosArt(Date finConflictosArt) {
        this.finConflictosArt=finConflictosArt;
    }

    public void setFinEvaluacion(Date finEvaluadion) {
        this.finEvaluacion=finEvaluadion;
    }

    public void setFinNotificacionAutores(Date finNotificacion) {
        this.finNotificacionAutores=finNotificacion;
    }

    public void setFinRecepcionArt(Date finRecepcion) {
        this.finRecepcionArt=finRecepcion;
    }

    public void setFinVersionFinalArt(Date finVersion) {
        this.finVersionFinalArt=finVersion;
    }

    public void setInicio(Date inicio) {
        this.inicio=inicio;
    }
    
    public void setInicioInscripcion(Date inicioInscripcion){
        this.inicioInscripcion=inicioInscripcion;
    }
    
    public void setInicioConflictosArt(Date inicioConflictos) {
        this.inicioConflictosArt=inicioConflictos;
    }

    public void setInicioEvaluacion(Date inicioEvaluacion) {
        this.inicioEvaluacion=inicioEvaluacion;
    }

    public void setInicioNotificacionAutores(Date inicioNotificacionAutores) {
        this.inicioNotificacionAutores=inicioNotificacionAutores;
    }

    public void setInicioRecepcionArt(Date inicioRecepcionArt) {
        this.inicioRecepcionArt=inicioRecepcionArt;
    }
    
    public void setInicioVersionFinalArt(Date inicioVersionFinalArt) {
        this.inicioVersionFinalArt = inicioVersionFinalArt;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    public void setSede(String sede) {
        this.sede=sede;
    }

    public void setSitioWeb(String sitio) {
        this.sitioWeb=sitio;
    }
    
    public ArrayList<Articulo> getArticulos() {
        return this.getArticulos(-1);
    }

    public ArrayList<Articulo> getArticulos(int filtro_id) {
        try {
            Vector<Articulo> articulos = BD.GestorBaseDatos.BuscarArticulos(getId());
            ArrayList<Articulo> tmp = new ArrayList<>();
            
            for (Articulo a : articulos) {
                if (a.getAutor().getI_Id() != filtro_id)
                    tmp.add(a);
            }   
            return tmp;
        
        } catch (InstantiationException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Usuario> getEvaluadores() {
        return this.getEvaluadores(-1);
    }

    public ArrayList<Usuario> getEvaluadores(int filtro_id) {
        try {
            ArrayList<Usuario> tmp = new ArrayList<>();
            Vector<Usuario> result = GestorBaseDatos.BuscarUsuarioXCongresoTipo(getId(), Constantes.P_EVALUADOR);
            for (Usuario u : result) {
                if (u.getI_Id() != filtro_id)
                    tmp.add(u);
            }
            return tmp;
        } catch (InstantiationException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
//    public Evaluaciones getEvaluaciones() {
//        Evaluaciones es = new Evaluaciones();
//        try {
//            Vector<ArticulosEvaluaciones> tmp = new Vector<>();
//            tmp = GestorBaseDatos.BuscarArticulosEvaluacionesXCongreso(getId());
//            
//            for (ArticulosEvaluaciones ae : tmp) {
//                Evaluacion e = new Evaluacion(ae.getAev_calificacion(), ae.getAev_comentario_autor(), ae.getAev_comentario_evaluador(), null, null);
//                e.setArticulo(GestorBaseDatos.BuscarArticulosXId(ae.getCa_id()).get(0));
//                e.setEvaluador(GestorBaseDatos.BuscarUsuario(String.valueOf(ae.getAev_usuario_id()), "u_id").get(0));
//                es.agregar(e);
//            }
//            
//            return es;
//            
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }
    
//    public Asignaciones getAsignaciones() {
//        
//        Asignaciones as = new Asignaciones();
//        try {
//            Vector<ArticulosEvaluaciones> tmp = new Vector<>();
//            tmp = GestorBaseDatos.BuscarArticulosEvaluacionesXCongreso(getId());
//            
//            for (ArticulosEvaluaciones ae : tmp) {
//                
//                if (ae.getAev_calificacion() == Constantes.E_NO_EVALUADO) {
//                    Asignacion a = new Asignacion();
//                    //a.setArticulo(GestorBaseDatos.BuscarArticulosXId(ae.getCa_id()).get(0));
//                    a.setEvaluador(GestorBaseDatos.BuscarUsuario(String.valueOf(ae.getAev_usuario_id()), "u_id").get(0));
//                    as.agregar(a);
//                }
//            }
//            
//            return as;
//            
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Congreso.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }

}
