package Clases;

public class Perfil {

	int i_Id;
	String S_descripcion;
        
	
	public Perfil(int i_Id, String s_descripcion) {
		this.i_Id = i_Id;
		S_descripcion = s_descripcion;               
        }

        
        /*, boolean b_organizadorresp, boolean b_organizador, boolean b_evaluador, boolean b_autor
         this.b_organizadorresp = b_organizadorresp;
                this.b_organizador = b_organizador;
                this.b_evaluador = b_evaluador;
                this.b_autor = b_autor;
    */
	public int getI_Id() {
		return i_Id;
	}

	public void setI_Id(int i_Id) {
		this.i_Id = i_Id;
	}

	public String getS_descripcion() {
		return S_descripcion;
	}

	public void setS_descripcion(String s_descripcion) {
		S_descripcion = s_descripcion;
	}
        
        
}
