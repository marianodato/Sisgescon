package BD;

import Clases.Articulo;
import Clases.EMailEvaluar;
import Clases.MailTipo;
import Clases.Mail;
import Clases.ArticulosEvaluaciones;
import Clases.AutoresSecundarios;
//import ClasesArticulos.Articulos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Clases.Congreso;
import java.sql.Date;
//import usuarios_perfiles.Perfil;
//import usuarios_perfiles.Usuario;
import Clases.EncriptaTexto;
import Clases.Perfil;
import Clases.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import articulos.Articulo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorBaseDatos
{    
        private static Vector<EMailEvaluar> misEMailEvaluar = new Vector<EMailEvaluar>();
        private static Vector<MailTipo> misMailTipo = new Vector<MailTipo>();
        private static Vector<Mail> misMail = new Vector<Mail>();
        private static Vector<ArticulosEvaluaciones> misArticulosEvaluaciones = new Vector<ArticulosEvaluaciones>();
	private static Vector<Usuario> usuarios = new Vector<Usuario>();
	private static Vector<Congreso> congresos = new Vector<Congreso>();
	private static Vector<Perfil> perfiles = new Vector<Perfil>();
        private static Vector<Articulo> misArticulos = new Vector<Articulo>();
        private static Vector<AutoresSecundarios> misAutoresSecundarios = new Vector<AutoresSecundarios>();
	private static boolean isInsertStatement = false;
	private static EncriptaTexto miEncriptacion  = new EncriptaTexto();
	//LOS ID NO SE PASAN EN LOS INSERT PORQUE SON AUTOINCREMENT!!!!
	

	//QUERIES USUARIOS!!!!
	public static void AgregarUsuario(Usuario usuario) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
            isInsertStatement=true;
            int newId = 0;
            
            try {
                //newId = BaseDatos.NonQuery("INSERT INTO usuarios (u_nombre,u_apellido,u_direccion,u_ciudad,u_mail,u_password,u_usuario,u_cp,u_pais,u_sitioweb,u_filiacion,u_miembro_uic) VALUES ('"+usuario.getS_nombre()+
                //                "','"+usuario.getS_apellido()+"','"+usuario.getS_direccion()+"','"+usuario.getS_ciudad()+"','"+usuario.getS_mail()+"','"+usuario.getS_password()+"','"+usuario.getS_usuario()+"','"+usuario.getS_cp()+"','"+usuario.getS_pais()+"','"+usuario.getS_sitioweb()+"','"+usuario.getS_filiacion()+"',"+usuario.getB_miembro()+")",isInsertStatement);
                newId = BaseDatos.NonQuery("INSERT INTO usuarios (u_nombre,u_apellido,u_direccion,u_ciudad,u_mail,u_password,u_usuario,u_cp,u_pais,u_sitioweb,u_filiacion,u_miembro_uic) VALUES ('"+usuario.getS_nombre()+
                        "','"+usuario.getS_apellido()+"','"+usuario.getS_direccion()+"','"+usuario.getS_ciudad()+"','"+usuario.getS_mail()+"','" + miEncriptacion.Encriptar(usuario.getS_password()) + "','"+usuario.getS_usuario()+"','"+usuario.getS_cp()+"','"+usuario.getS_pais()+"','"+usuario.getS_sitioweb()+"','"+usuario.getS_filiacion()+"',"+usuario.getB_miembro()+")",isInsertStatement);
            } catch (Exception ex) {
                Logger.getLogger(GestorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(newId!= 0)
            {
                usuario.setI_Id(newId);
            }

            usuarios.add(usuario);
	}
        
	public static Vector<Usuario> BuscarUsuario(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM usuarios WHERE "+campo+" LIKE '"+value+"'";
			return BaseDatos.UsuarioQuery(string);	
	}
        
        public void BuscarUsuario2(Usuario usuario,String sUsuario, String sPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
            try {
                String query="SELECT * FROM usuarios where u_usuario='" + sUsuario + "' and u_password='" + miEncriptacion.Encriptar(sPassword) + "';";
                ResultSet rs=BaseDatos.UsuarioQuery2(query);
                while (rs.next()) {
                    usuario.setI_Id(rs.getInt("u_id"));
                    usuario.setS_apellido(rs.getString("u_apellido"));
                    usuario.setS_nombre(rs.getString("u_nombre"));
                    usuario.setS_direccion(rs.getString("u_direccion"));
                    usuario.setS_ciudad(rs.getString("u_ciudad"));
                    usuario.setS_mail(rs.getString("u_mail"));
                    usuario.setS_password(rs.getString("u_password"));
                    usuario.setS_usuario(rs.getString("u_usuario"));
                    usuario.setS_cp(rs.getString("u_cp"));
                    usuario.setS_pais(rs.getString("u_pais"));
                    usuario.setS_sitioweb(rs.getString("u_sitioweb"));
                    usuario.setS_filiacion(rs.getString("u_filiacion"));
                    usuario.setB_miembro(rs.getBoolean("u_miembro_uic"));
                    usuario.setB_ingresado(true);
                }
                rs.close();
            } catch (Exception ex) {
                Logger.getLogger(GestorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
        
    public static void EliminarUsuario(int i_Id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {				
                    isInsertStatement=false;
                    BaseDatos.NonQuery("DELETE FROM usuarios WHERE u_id="+i_Id,isInsertStatement);
                    for(int i=0;i<usuarios.size();i++) if(usuarios.get(i).getI_Id() == i_Id) usuarios.remove(i);
    }
        
    public static void ModificarUsuario(Usuario usuario) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException    {				
        isInsertStatement=false;
            try {
                BaseDatos.NonQuery("UPDATE usuarios SET u_nombre='"+usuario.getS_nombre()+"',u_apellido='"+usuario.getS_apellido()
                        +"',u_direccion='"+usuario.getS_direccion()+"',u_ciudad='"+usuario.getS_ciudad()+"',u_mail='"+usuario.getS_mail()+"',u_password='"+ miEncriptacion.Encriptar(usuario.getS_password()) +"',u_cp='"+usuario.getS_cp()+"',u_pais='"+usuario.getS_pais()+"',u_sitioweb='"+usuario.getS_sitioweb()+"',u_filiacion='"+usuario.getS_filiacion()+"',u_miembro_uic="+usuario.getB_miembro()+" WHERE u_id="+usuario.getI_Id(),isInsertStatement);
            } catch (Exception ex) {
                Logger.getLogger(GestorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        for(int i=0;i<usuarios.size();i++) {
            if(usuarios.get(i).getI_Id() == usuario.getI_Id())  {
                usuarios.get(i).setS_apellido(usuario.getS_apellido());
                usuarios.get(i).setS_nombre(usuario.getS_nombre());
                usuarios.get(i).setS_direccion(usuario.getS_direccion());
                usuarios.get(i).setS_ciudad(usuario.getS_ciudad());
                usuarios.get(i).setS_mail(usuario.getS_mail());
                usuarios.get(i).setS_password(usuario.getS_password());
                //usuarios.get(i).setS_usuario(usuario.getS_usuario());
                usuarios.get(i).setS_cp(usuario.getS_cp());
                usuarios.get(i).setS_pais(usuario.getS_pais());
                usuarios.get(i).setS_sitioweb(usuario.getS_sitioweb());
                usuarios.get(i).setS_filiacion(usuario.getS_filiacion());
                usuarios.get(i).setB_miembro(usuario.getB_miembro());
                //usuarios.get(i).setTelefono(cliente.getTelefono());
            }
        }
    }
        
	public static void CargarUsuarios() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		usuarios = BaseDatos.UsuarioQuery("SELECT * FROM usuarios");
	}
	
	public static Vector<Clases.Usuario> getUsuarios() {
		return usuarios;
	}
	
	public static Vector<Usuario> BuscarUsuarioXArticulo(int aa_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_articulo_autor WHERE aa_articulo_id=" + aa_articulo_id + ";";
            return BaseDatos.UsuarioQuery(string);	
            //return misArticulos=BaseDatos.UsuarioQuery(string);	
	}
        
        public static Vector<Usuario> BuscarUsuarioXCongresoTipo(int c_id, int ucp_perfil_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_usuario_congreso_tipo WHERE c_id=" + c_id + " AND ucp_perfil_id=" + ucp_perfil_id + ";";
            return BaseDatos.UsuarioQuery(string);	
            //return misArticulos=BaseDatos.UsuarioQuery(string);	
	}
	//---------------------------------------------------------------------------------------------------------------
	//QUERIES CONGRESOS
	
	public static void AgregarCongreso(Congreso congreso) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=true;
			int newId = 0;
			
			newId = BaseDatos.NonQuery("INSERT INTO congresos (c_nombre,c_inicio,c_fin,c_universidad_sede,c_ini_presentacion_articulos,c_fin_presentacion_articulos,c_nombre_acronimo,c_homepage,c_email,c_anio,c_ini_eval_articulos,c_fin_eval_articulos,c_ini_conf_articulos,c_fin_conf_articulos,c_ini_versiones_articulos,c_fin_versiones_articulos,c_ini_notificacion_autores,c_fin_notificacion_autores,c_ini_inscripcion,c_fin_inscripcion) VALUES ('"+congreso.getS_nombre()+
					"','"+congreso.getD_inicio()+"','"+congreso.getD_fin()+"','"+congreso.getS_sede()+"','"+congreso.getD_inicio_pres_articulos()+"','"+congreso.getD_fin_pres_articulos()+"','"+congreso.getS_acronimo()+"','"+congreso.getS_sitioweb()+"','"+congreso.getS_mail()+"',"+congreso.getI_Anio()+",'"+congreso.getD_inicio_eval_articulos()+"','"+congreso.getD_fin_eval_articulos()+"','"+
					congreso.getD_inicio_conf_articulos()+"','"+congreso.getD_fin_conf_articulos()+"','"+congreso.getD_ini_versiones_articulos()+"','"+congreso.getD_fin_versiones_articulos()+"','"+congreso.getD_ini_notificacion_autores()+"','"+congreso.getD_fin_notificacion_autores()+"'," + "'" + congreso.getD_ini_inscripcion() + "','" + congreso.getD_fin_inscripcion() + "')",isInsertStatement);
			
			if(newId!= 0)
			{
				congreso.setI_Id(newId);
			}
			
			congresos.add(congreso);
	}
        
	public static Vector<Congreso> BuscarCongreso(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM congresos WHERE "+campo+" LIKE '"+value+"'";
			return BaseDatos.CongresoQuery(string);	
	}
        
        public static Vector<Congreso> BuscarCongresoXID(int value) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM congresos WHERE c_id=" + value;
			return BaseDatos.CongresoQuery(string);	
	}
        
	public static void EliminarCongreso(int i_Id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM congresos WHERE c_id="+i_Id,isInsertStatement);
			for(int i=0;i<congresos.size();i++) if(congresos.get(i).getI_Id() == i_Id) congresos.remove(i);
	}
        
	public static void ModificarCongreso(Congreso congreso) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE congresos SET c_nombre='"+congreso.getS_nombre()+"',c_inicio="+congreso.getD_inicio()
					+",c_fin="+congreso.getD_fin()+",c_universidad_sede='"+congreso.getS_sede()+"',c_ini_presentacion_articulos="+congreso.getD_inicio_pres_articulos()+",c_fin_presentacion_articulos="+congreso.getD_fin_pres_articulos()+",c_nombre_acronimo='"+congreso.getS_acronimo()+"',c_homepage='"+congreso.getS_sitioweb()+"',c_email='"+congreso.getS_mail()+"',c_anio="+congreso.getI_Anio()+",c_ini_eval_articulos="+congreso.getD_inicio_eval_articulos()+",c_fin_eval_articulos="+congreso.getD_fin_eval_articulos()+",c_fin_versiones_articulos="+congreso.getD_fin_versiones_articulos()+",c_fin_notificacion_autores="+congreso.getD_fin_notificacion_autores()+",c_ini_versiones_autores="+congreso.getD_ini_versiones_articulos()+",c_ini_notificacion_autores="+congreso.getD_ini_notificacion_autores()+" WHERE c_id="+congreso.getI_Id(),isInsertStatement);
			for(int i=0;i<congresos.size();i++)
				{
					if(congresos.get(i).getI_Id() == congreso.getI_Id())
					{
                                            congresos.get(i).setS_nombre(congreso.getS_nombre());
                                            congresos.get(i).setD_inicio(congreso.getD_inicio());
                                            congresos.get(i).setD_fin(congreso.getD_fin());
                                            congresos.get(i).setS_sede(congreso.getS_sede());
                                            congresos.get(i).setD_inicio_pres_articulos(congreso.getD_inicio_pres_articulos());
                                            congresos.get(i).setD_fin_pres_articulos(congreso.getD_fin_pres_articulos());
                                            congresos.get(i).setS_acronimo(congreso.getS_acronimo());
                                            congresos.get(i).setS_sitioweb(congreso.getS_sitioweb());
                                            congresos.get(i).setS_mail(congreso.getS_mail());
                                            congresos.get(i).setD_inicio_eval_articulos(congreso.getD_inicio_eval_articulos());
                                            congresos.get(i).setD_fin_eval_articulos(congreso.getD_fin_eval_articulos());
                                            congresos.get(i).setD_inicio_conf_articulos(congreso.getD_inicio_conf_articulos());
                                            congresos.get(i).setD_fin_conf_articulos(congreso.getD_fin_conf_articulos());
                                            congresos.get(i).setD_fin_versiones_articulos(congreso.getD_fin_versiones_articulos());
                                            congresos.get(i).setD_fin_notificacion_autores(congreso.getD_fin_notificacion_autores());
                                            congresos.get(i).setD_ini_notificacion_autores(congreso.getD_ini_notificacion_autores());
                                            congresos.get(i).setD_ini_versiones_articulos(congreso.getD_ini_versiones_articulos());
					}
				}
	}
        
	public static void CargarCongresos() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		congresos = BaseDatos.CongresoQuery("SELECT * FROM congresos");
	}
	
	public static Vector<Congreso> getCongresos() {
		return congresos;
	}
	
	//---------------------------------------------------------------------------------------------------------------
	//QUERIES PERFILES
	
	public static void AgregarPerfil(Perfil perfil) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=true;
			int newId = 0;
			
			newId = BaseDatos.NonQuery("INSERT INTO perfiles (p_descripcion) VALUES ('"+perfil.getS_descripcion()+")",isInsertStatement);
			
			if(newId!= 0)
			{
				perfil.setI_Id(newId);
			}
			
			perfiles.add(perfil);
	}
        
	public static Vector<Perfil> BuscarPerfil(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM perfiles WHERE "+campo+" LIKE '"+value+"'";
			return BaseDatos.PerfilQuery(string);	
	}
	 
	public static void EliminarPerfil(int i_Id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM perfiles WHERE p_id="+i_Id,isInsertStatement);
			for(int i=0;i<perfiles.size();i++) if(perfiles.get(i).getI_Id() == i_Id) perfiles.remove(i);
	}
        
	public static void ModificarPerfil(Perfil perfil) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE perfiles SET p_descripcion='"+perfil.getS_descripcion()+" WHERE p_id="+perfil.getI_Id(),isInsertStatement);
			for(int i=0;i<perfiles.size();i++)
				{
					if(perfiles.get(i).getI_Id() == perfil.getI_Id())
					{
						perfiles.get(i).setS_descripcion(perfil.getS_descripcion());
					}
				}
	}
        
	public static void CargarPerfiles() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		perfiles = BaseDatos.PerfilQuery("SELECT * FROM perfiles");
	}
	
	public static Vector<Perfil> getPerfiles() {
		return perfiles;
	}
	
	//---------------------------------------------------------------------------------------------------------------
	//QUERIES USUARIOS_CONGRESOS_PERFILES
	
	public static void AgregarRelacion_Usuario_Congreso_Perfil(int id_us, int id_con, int id_per) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false
			
			BaseDatos.NonQuery("INSERT INTO usuarios_congresos_perfiles (ucp_usuario_id, ucp_congreso_id,ucp_perfil_id) VALUES ("+id_us+","+id_con+","+id_per+")",isInsertStatement);
	}
	
	public static void EliminarRelacion_Usuario_Congreso_Perfil(int id_us, int id_con, int id_per) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM usuarios_congresos_perfiles WHERE ucp_usuario_id="+id_us + " AND ucp_congreso_id ="+id_con+ " AND ucp_perfil_id="+id_per,isInsertStatement);
	}  
	
	public static Vector<Perfil> BuscarPerfilxCongresoxUsuario(int id_con,int id_us) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM perfiles WHERE p_id IN (SELECT ucp_perfil_id FROM usuarios_congresos_perfiles WHERE ucp_usuario_id="+id_us+" AND ucp_congreso_id="+id_con+")";
			return BaseDatos.PerfilQuery(string);	
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------------
	//QUERIES Articulos
	

    	
        public static void AgregarArticulos(Articulo articulo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {        
                        isInsertStatement=true;//va false
                        
                        int newId=0;
                        
            newId=BaseDatos.NonQuery("INSERT INTO articulos (ca_congreso_id, ca_titulo,ca_resumen,ca_estado,ca_idioma) VALUES (" + articulo.getCongreso_id() + ",'" + articulo.getTitulo() + "','" + articulo.getResumen() + "'," + articulo.getEstado() + "," + articulo.getIdioma() + ")",isInsertStatement);
                        
                        if(newId!= 0)
            {
                articulo.setArticulo_id(newId);
            }
            misArticulos.add(articulo);
        }
        
        
        
        
	public static void AgregarArticulos2(int ca_congreso_id, String ca_titulo, String ca_resumen,int ca_estado,int ca_idioma) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO articulos (ca_congreso_id, ca_titulo,ca_resumen,ca_estado,ca_idioma) VALUES (" + ca_congreso_id + ",'" + ca_titulo + "','" + ca_resumen + "'," + ca_estado + "," + ca_idioma + ")",isInsertStatement);
	}
        
        public static int idAgregarArticulos(Clases.Articulo articulo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
        {        
            isInsertStatement=true;//va false                        
            int newId=0;                        
            newId=BaseDatos.NonQuery("INSERT INTO articulos (ca_congreso_id, ca_titulo,ca_resumen,ca_estado,ca_idioma) VALUES (" + articulo.getCongreso_id() + ",'" + articulo.getTitulo() + "','" + articulo.getResumen() + "'," + articulo.getEstado() + "," + articulo.getIdioma() + ")",isInsertStatement);
            if(newId!= 0)
            {
                return(newId);
            }
            else{
                return 0;
            }
        }
        
        public static void AgregarRelacionUsuarioxArticulo(int c_usuario_id, int c_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("INSERT INTO usuarios_articulos (c_usuario_id, c_articulo_id) VALUES ("+c_usuario_id+","+ c_articulo_id+")",isInsertStatement);
	}  

	public static void EliminarArticuloPDF(int av_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("DELETE FROM articulos_versiones WHERE av_articulo_id=" + av_articulo_id,isInsertStatement);
	}
        public static void AgregarArticuloPDF(int av_articulo_id, byte[] av_contenido, int av_version) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO articulos_versiones (av_articulo_id, av_contenido,av_version) VALUES (" + av_articulo_id + ",'" + av_contenido + "'," + av_version + ")",isInsertStatement);
                        //BaseDatos.insertPDF(av_articulo_id, av_contenido, av_version);
                        
        }
        
        public static void AgregarNuevoArticuloPDF(int av_articulo_id, FileInputStream av_contenido, int av_version) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			//BaseDatos.NonQuery("INSERT INTO articulos_versiones (av_articulo_id, av_contenido,av_version) VALUES (" + av_articulo_id + ",'" + av_contenido + "'," + av_version + ")",isInsertStatement);
                        BaseDatos.insertPDF(av_articulo_id, av_contenido, av_version);
                        
        }
        
        public static Boolean BuscarArticuloPDF(int av_articulo_id, String nombre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
            return BuscarArticuloPDF(av_articulo_id, -1, nombre);
        }
        
        public static Boolean BuscarArticuloPDF(int av_articulo_id, int av_version, String nombre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
            String string;
            
            if (av_version == -1) // la ultima version
                string = "SELECT * FROM articulos_versiones WHERE av_articulo_id=" + av_articulo_id + " order by av_version desc limit 1;";
            else
                string = "SELECT * FROM articulos_versiones WHERE av_articulo_id=" + av_articulo_id + " and av_version=" + av_version + ";";
            
            return BaseDatos.ArticulosPDFQuery(string,nombre);
        }
        
        public static Boolean ExisteArticuloPDF(int av_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
            return ExisteArticuloPDF(av_articulo_id, -1);
        }
        
        public static Boolean ExisteArticuloPDF(int av_articulo_id, int av_version) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
            String string;
            
            if (av_version == -1) // la ultima version
                string = "SELECT * FROM articulos_versiones WHERE av_articulo_id=" + av_articulo_id + " order by av_version desc limit 1;";
            else
                string = "SELECT * FROM articulos_versiones WHERE av_articulo_id=" + av_articulo_id + " and av_version=" + av_version + ";";
            
            return BaseDatos.ExisteArticulosPDFQuery(string);
        }
        
	public static void EliminarArticulos(int id_art) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM articulos WHERE ca_id=" + id_art ,isInsertStatement);
	}  
	
        public static void ActualizoArticulos(int id_art, String ca_titulo, String ca_resumen, int ca_estado, int ca_idioma) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE articulos SET ca_titulo='" + ca_titulo + "' ,ca_resumen='" + ca_resumen + "', ca_estado=" + ca_estado + ", ca_idioma=" + ca_idioma + " WHERE ca_id=" + id_art ,isInsertStatement);
	}  
	
        public static void ActualizoArticuloEstado(int id_art, int ca_estado) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE articulos SET ca_estado=" + ca_estado  + " WHERE ca_id=" + id_art ,isInsertStatement);
	}
        
        public static Vector<Articulo> BuscarArticulosXId(int ca_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM articulos WHERE ca_id=" + ca_id + ";";
            return misArticulos=BaseDatos.ArticulosQuery(string);	
	}
        
         public static Vector<Articulo> BuscarArticulosXID(int ca_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_cargar_articulos WHERE ca_id=" + ca_id + ";";
            return misArticulos=BaseDatos.ArticulosQuery(string);	
	}
        
        public static Vector<Articulo> CagarArticulosXCongresoUsuario(int ca_congreso_id, int c_usuario_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_cargar_articulos WHERE ca_congreso_id=" + ca_congreso_id + " and c_usuario_id=" + c_usuario_id + ";";
            return misArticulos=BaseDatos.ArticulosQuery(string);	
	}
        
        public static void AgregarRelacionArticuloxAutor(int aa_articulo_id, int aa_usuario_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("INSERT INTO articulos_autores (aa_articulo_id, aa_usuario_id) VALUES ("+aa_articulo_id+","+ aa_usuario_id+")",isInsertStatement);
	}
        
        public static void EliminarRelacionArticuloxAutor(int aa_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM articulos_autores WHERE aa_articulo_id="+ aa_articulo_id,isInsertStatement);
	}
        public static void EliminarRelacionUsuarioxArticulo(int c_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM usuarios_articulos WHERE c_articulo_id="+ c_articulo_id,isInsertStatement);
	}
	public static Vector<Articulo> BuscarArticulos(int ca_congreso_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM articulos WHERE ca_congreso_id=" + ca_congreso_id + ";";
            return misArticulos=BaseDatos.ArticulosQuery(string);	
	}
	
        public static Vector<Articulo> BuscarArticulosXCongresoUsuario(int ca_congreso_id, int c_usuario_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM articulos WHERE ca_congreso_id=" + ca_congreso_id + " and c_usuario_id=" + c_usuario_id + ";";
            return misArticulos=BaseDatos.ArticulosQuery(string);	
	}
	//---------------------------------------------------------------------------------------------------------------
	//articulos_autores_secundarios 
        
        public static void AgregarArticulosAutoresSecundarios(int aas_articulo_id, String ass_nombre, String ass_apellido,String ass_mail,String ass_filiacion, String ass_oic) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO articulos_autores_secundarios (aas_articulo_id, ass_nombre,ass_apellido,ass_mail,ass_filiacion,ass_oic) VALUES (" + aas_articulo_id + ",'" + ass_nombre + "','" + ass_apellido + "','" + ass_mail + "','" + ass_filiacion +"','"+ass_oic +"')",isInsertStatement);
	}
        
	public static void EliminarArticulosAutoresSecundariosXArticuloID(int aas_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM articulos_autores_secundarios WHERE aas_id=" + aas_id ,isInsertStatement);
	}

        public static Vector<AutoresSecundarios> BuscarAutoresSecundarios(int aas_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM articulos_autores_secundarios WHERE aas_articulo_id=" + aas_articulo_id + ";";
            return misAutoresSecundarios=BaseDatos.AutoresSecundariosQuery(string);	
	}
        
        public static void ActualizoAutoresSecundarios(int aas_articulo_id, String ass_nombre, String ass_apellido,String ass_mail,String ass_filiacion, String ass_oic) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE articulos_autores_secundarios SET ass_nombre='" + ass_nombre + "' ,ass_apellido='" + ass_apellido + "', ass_mail='" + ass_mail + "', ass_filiacion='" + ass_filiacion + "', ass_oic='" + ass_oic + "' WHERE aas_articulo_id=" + aas_articulo_id  ,isInsertStatement);
	} 
	//---------------------------------------------------------------------------------------------------------------
	//articulos_autores_secundarios 
        
        public static void AgregarArticulosEvaluaciones(int aev_usuario_id,int aev_calificacion,String aev_comentario_autor,String aev_comentario_organizador, Date aev_fecha, int aev_articulo_id ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO articulos_evaluaciones (aev_usuario_id,aev_calificacion,aev_comentario_autor,aev_comentario_organizador,aev_fecha,aev_articulo_id) "
                                + "VALUES (" + aev_usuario_id + "," + aev_calificacion + ",'" + aev_comentario_autor + "','" + aev_comentario_organizador + "','" + aev_fecha + "'," + aev_articulo_id + ")",isInsertStatement);
	}
        
        public static void AgregarArticulosEvaluacionesSinFecha(int aev_usuario_id,int aev_calificacion,String aev_comentario_autor,String aev_comentario_organizador, int aev_articulo_id ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO articulos_evaluaciones (aev_usuario_id,aev_calificacion,aev_comentario_autor,aev_comentario_organizador,aev_fecha,aev_articulo_id) "
                                + "VALUES (" + aev_usuario_id + "," + aev_calificacion + ",'" + aev_comentario_autor + "','" + aev_comentario_organizador + "','2015-11-09'," + aev_articulo_id + ")",isInsertStatement);
	}
        
        public static void ActualizoArticuloEvaluacion(int aev_id, int aev_usuario_id, int aev_calificacion, String aev_comentario_autor, String aev_comentario_organizador, Date aev_fecha, int aev_articulo_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE articulos_evaluaciones SET aev_usuario_id=" + aev_usuario_id +  ", aev_calificacion=" + aev_calificacion + ", aev_comentario_autor='" + aev_comentario_autor + "', aev_comentario_organizador='" +aev_comentario_organizador + "', aev_fecha='" + aev_fecha + "', aev_articulo_id=" + aev_articulo_id + " WHERE aev_id=" + aev_id ,isInsertStatement);
	}
        
	public static void EliminarArticulosEvaluaciones(int aev_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM articulos_evaluaciones WHERE aev_id=" + aev_id ,isInsertStatement);
	}

	public static void ActualizoArticulos(int aev_id, int aev_calificacion, String aev_comentario_autor, String aev_comentario_organizador, Date aev_fecha) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("UPDATE articulos_evaluaciones SET aev_calificacion=" + aev_calificacion + 
                                ", aev_comentario_autor='" + aev_comentario_autor + "', aev_comentario_organizador='" + aev_comentario_organizador + "', aev_fecha='" + aev_fecha + "' WHERE aev_id=" + aev_id ,isInsertStatement);
	}  
        
        public static Vector<ArticulosEvaluaciones> BuscarArticulosEvaluaciones(int aev_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM articulos_evaluaciones WHERE aev_id=" + aev_id + ";";
            return misArticulosEvaluaciones=BaseDatos.ArticulosEvaluacionesQuery(string);	
	}
        
        public static Vector<ArticulosEvaluaciones> BuscarArticulosEvaluacionesXCongreso(int ca_congreso_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_articulo_evaluaciones WHERE ca_congreso_id=" + ca_congreso_id + ";";
            System.out.println(string);
            return misArticulosEvaluaciones=BaseDatos.ArticulosEvaluacionesQuery(string);	
	}

        

        //---------------------------------------------------------------------------------------------------------------
	//mails 
        
        public static void AgregarMail( String m_descripcion, int m_idioma_id,int m_tipo,String aev_comentario_autor,String aev_comentario_evaluador, Date aev_fecha ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{			
			isInsertStatement=false;//va false			
			BaseDatos.NonQuery("INSERT INTO mails (m_descripcion, m_idioma_id,m_tipo) "
                                + "VALUES ('" + m_descripcion + "'," + m_idioma_id + "," + m_tipo + ")",isInsertStatement);
	}
        
	public static void EliminarMail(int m_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			isInsertStatement=false;
			BaseDatos.NonQuery("DELETE FROM mails WHERE m_id=" + m_id ,isInsertStatement);
	}
        
//        public static Vector<Articulos> BuscarMail(int m_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
//	{		
//            //String string = "SELECT * FROM articulos;";
//            String string = "SELECT * FROM mails WHERE aev_id=" + m_id + ";";
//            return misArticulos=BaseDatos.ArticulosQuery(string);	
//	}
        
        public static Vector<Mail> BuscarMailCompleto(int m_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_mail_tipo WHERE m_id=" + m_id + ";";
            return misMail=BaseDatos.MailQuery(string);	
	}
        
        public static Vector<MailTipo> BuscarMailTipo(int mt_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM mails_tipos WHERE mt_id=" + mt_id + ";";
            return misMailTipo=BaseDatos.MailTipoQuery(string);
	}
        
        public static Vector<EMailEvaluar> BuscarEMailEvaluar(Date c_fin_eval_articulos) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_mails_evaluar WHERE c_fin_eval_articulos=" + c_fin_eval_articulos + ";";
            return misEMailEvaluar=BaseDatos.EMailEvaluarQuery(string);
	}
        
        public static Vector<EMailEvaluar> BuscarEMailEvaluarTodos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_mails_evaluar;";
            return misEMailEvaluar=BaseDatos.EMailEvaluarQuery(string);
	}
        
        public static Vector<EMailEvaluar> BuscarEMailEvaluarConflictos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
            //String string = "SELECT * FROM articulos;";
            String string = "SELECT * FROM v_mails_evaluar_conflictos;";
            return misEMailEvaluar=BaseDatos.EMailEvaluarConflictosQuery(string);
	}

}