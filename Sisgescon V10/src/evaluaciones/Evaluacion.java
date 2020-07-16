package evaluaciones;

import GestionMails.InterfaceEvaluacion;
import Clases.Articulo;
import java.sql.Time;
import java.util.Date;
import usuarios_perfiles.Perfil;
import Clases.Usuario;
import comun.Modelo;

/**
 * @author VictoriaBalin
 * @version 1.0
 * @created 10-sep-2015 10:52:21 a.m.
 */
public class Evaluacion extends Modelo implements InterfaceEvaluacion{

	private String comentarioAutor;
	private String comentarioOrganizador;
	private Date fecha;
	private int calificacion;
	private Articulo articulo;
        private Usuario evaluador;
        
        public String getNombreCalificacion() {
            
            switch (calificacion) {
                case -2:
                    return "Malo";
                case -1:
                    return "Pobre";
                case 0:
                    return "Regular";
                case 1:
                    return "Bueno";
                case 2:
                    return "Excelente";
            }
            
            return null;
        }
        
        public Evaluacion() {
            
        }
        

	public Evaluacion(int calificacion, String comentAutor, String comentOrg,
                Articulo articulo, Usuario evaluador){
            this.calificacion = calificacion;
            comentarioAutor = comentAutor;
            this.articulo = articulo;
            this.evaluador = evaluador;
            fecha = new Date();
	}

	public String getComentarioAutor(){
		return comentarioAutor;
	}

	public String getComentarioOrganizador(){
		return comentarioOrganizador;
	}

	public Date getFecha(){
		return fecha;
	}

	public int getCalificacion(){
		return calificacion;
	}
        
        public Articulo getArticulo() {
            return articulo;
        }
        
        public void setComentarioAutor(String comentAut) {
            comentarioAutor = comentAut;
        }
        
        public void setComentarioOrganizador(String comentOrg) {
            comentarioOrganizador = comentOrg;
        }
        
        public Usuario getEvaluador() {
            return evaluador;
        }
        
        public void setEvaluador(Usuario u) {
            evaluador = u;
        }
        
        public void setArticulo(Articulo a) {
            articulo = a;
        }
        
        public void setFecha(Date d) {
            this.fecha = d;
        }
        
        public void setCalificacion(int c) {
            this.calificacion = c;
        }

    @Override
    public String getNombreArticulo() {
        return articulo.getTitulo();
    }

    @Override
    public String getComentario() {
        return getComentarioAutor();
    }
}//end Evaluacion