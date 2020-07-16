package evaluaciones;

import Clases.Articulo;
import Clases.Usuario;
import comun.Modelo;

/**
 * @author VictoriaBalin
 * @version 1.0
 * @created 10-sep-2015 10:56:28 a.m.
 */
public class Asignacion extends Modelo{
        
	private Articulo articulo;
	private Usuario evaluador;
        private boolean valida;
        private boolean evaluado;

	public Asignacion(Articulo articulo, Usuario evaluador, boolean valida){
            this.articulo = articulo;
            this.evaluador = evaluador;
            this.valida = valida;
	}
        
        public Asignacion(Articulo articulo, Usuario evaluador) {
            this(articulo, evaluador, false);
        }
        
        public Asignacion(Usuario evaluador, Articulo articulo) {
            this(articulo, evaluador);
        }
        
        public Asignacion() {
            
        }
        
        public void setEvaluador(Usuario u) {
            evaluador = u;
        }
        
        public void setArticulo(Articulo a) {
            articulo = a;
        }

	public Articulo getArticulo(){
		return articulo;
	}

	public Usuario getEvaluador(){
		return evaluador;
	}
        
        public boolean esIgual(Asignacion a) {
            return this.articulo.getArticulo_id() == a.articulo.getArticulo_id() && 
                   this.evaluador.getI_Id() == a.evaluador.getI_Id();
        }
        
        public void setValida(boolean b) {
            valida = b;
        }
        
        public boolean esValida() {
            return valida;
        }
        
        public void setEvaluado(boolean e) {
            evaluado = e;
        }
        
        public boolean esEvaluado() {
            return evaluado;
        }
        
}//end Asignacion