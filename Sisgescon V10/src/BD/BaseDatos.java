package BD;

import Clases.Articulo;
import Clases.EMailEvaluar;
import Clases.MailTipo;
import Clases.Mail;
import Clases.ArticulosEvaluaciones;
import Clases.AutoresSecundarios;
import Clases.EncriptaTexto;
import Clases.Perfil;
import Clases.Usuario;
//import ClasesArticulos.Articulos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import Clases.Congreso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
//import usuarios_perfiles.Perfil;
//import usuarios_perfiles.Usuario;

public class BaseDatos 
{       
    private static EncriptaTexto miEncriptacion  = new EncriptaTexto();
	private static Connection conn;
	private static Statement stmt;
	@SuppressWarnings("unused")
	private static ResultSet rs;
	private static String myConnectionString = "jdbc:mysql://localhost:3306/sisgescon";
	private static String sUsuarioDB="root";
	private static String sPasswordDB="root";

    static void insertPDF(String string, boolean insertStatement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void insertPDF(int av_articulo_id, FileInputStream av_contenido, int av_version) throws ClassNotFoundException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // Creo una nueva instancia de driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Obtengo la conexión
            //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phpmyadmin?user=root");
            String insertSQL= "INSERT INTO articulos_versiones (av_articulo_id, av_contenido,av_version) VALUES (?,?,?)";
            conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
            java.sql.PreparedStatement pstmt= conn.prepareStatement(insertSQL);
            pstmt.setInt(1, av_articulo_id);
            pstmt.setBinaryStream(2, av_contenido);
            pstmt.setInt(3, av_version);            	
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public BaseDatos()
	{
		// Creo una conexion
		conn = null;
		// Creo una instruccion
		stmt = null;
		// Creo un set de resultados
		rs = null;
	}
	
	public static boolean Check() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        try{
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
			conn.close();
			return true;
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, " Hubo un error de conexion");
            return false;
        }
	}
	
	public static int NonQuery(String text,boolean isInsertStatement) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		int id = 0;
        try{
            // Creo una nueva instancia de driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Obtengo la conexión
            //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phpmyadmin?user=root");
            conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
            // Instanción la instrucción
           
            if(!isInsertStatement) {
	            stmt = conn.createStatement();	
	            // Cargo un set de resultados ejecutando la instrucción
	            stmt.executeUpdate(text);                
	            // Cuando terminé, siempre cierro la conexión
	            conn.close();
            }else{
            	stmt = conn.createStatement();	
            	stmt.executeUpdate(text, Statement.RETURN_GENERATED_KEYS);
            	ResultSet rss = stmt.getGeneratedKeys();
            	rss.next();
            	id = rss.getInt(1);
            	conn.close();
            	return id;
            }
                  
            return 0;
            
         }
         catch(SQLException e1){
             System.out.println(e1);
         }
        
        return 0;
	}
        
	public static Vector<Clases.Usuario> UsuarioQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
            try {                
                    Vector<Usuario> usuarios = new Vector<Usuario>();
                    // Creo una nueva instancia de driver
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    // Obtengo la conexión
                    conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
                    // Instanción la instrucción
                    stmt = conn.createStatement();
                    // Cargo un set de resultados ejecutando la instrucción
                    ResultSet rs = stmt.executeQuery(text);
                    while(rs.next())
                    {	
                        try {
                            usuarios.add(new Usuario(rs.getInt("u_id"),rs.getString("u_apellido"),rs.getString("u_nombre"),rs.getString("u_direccion"),rs.getString("u_ciudad"),rs.getString("u_mail"),rs.getString("u_usuario"),miEncriptacion.Desencriptar(rs.getString("u_password")),rs.getString("u_cp"),rs.getString("u_pais"),rs.getString("u_sitioweb"),rs.getString("u_filiacion"),rs.getBoolean("u_miembro_uic")));
                        } catch (Exception ex) {
                            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // Cuando terminé, siempre cierro la conexión
                    conn.close();
                    return usuarios;
            } catch(SQLException e1){
                    System.out.println(e1);
            }

            return new Vector<Usuario>();	
	}
        
    public static ResultSet UsuarioQuery2(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
            ResultSet rs = null;
            try{
                // Creo una nueva instancia de driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                // Obtengo la conexión
                //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phpmyadmin?user=root");
                conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
                // Instanción la instrucción
                stmt = conn.createStatement();	
                // Cargo un set de resultados ejecutando la instrucción
                rs = stmt.executeQuery(text);                
                // Cuando terminé, siempre cierro la conexión
                //conn.close();
                //return rs;
            }
            catch(SQLException e1){
                System.out.println(e1);
            }            
            return rs;
	}
    
    public static Vector<Congreso> CongresoQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Congreso> congresos = new Vector<Congreso>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                        Congreso tmp = new Congreso(rs.getInt("c_id"),rs.getString("c_nombre"),rs.getDate("c_inicio"),rs.getDate("c_fin"),rs.getString("c_universidad_sede"),rs.getDate("c_ini_presentacion_articulos"),rs.getDate("c_fin_presentacion_articulos"),rs.getString("c_nombre_acronimo"),rs.getString("c_homepage"),rs.getString("c_email"),rs.getInt("c_anio"),rs.getDate("c_ini_eval_articulos"),rs.getDate("c_fin_eval_articulos"),rs.getDate("c_ini_conf_articulos"),rs.getDate("c_fin_conf_articulos"),rs.getDate("c_ini_versiones_articulos"),rs.getDate("c_fin_versiones_articulos"),rs.getDate("c_ini_notificacion_autores"),rs.getDate("c_fin_notificacion_autores"),rs.getDate("c_ini_inscripcion"),rs.getDate("c_fin_inscripcion"));
                        congresos.add(tmp);
			}
			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return congresos;
		} catch(SQLException e1){
			System.out.println(e1);
		}
		//Date midate=Date();
		return new Vector<Congreso>();	
	}
    
    public static Vector<Perfil> PerfilQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Perfil> perfiles = new Vector<Perfil>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
	              perfiles.add(new Perfil(rs.getInt("p_id"),rs.getString("p_descripcion")));
			}
			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return perfiles;
		} catch(SQLException e1){
			System.out.println(e1);
		}
		
		return new Vector<Perfil>();	
	}
    
    public static Vector<Articulo> ArticulosQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Articulo> articulos = new Vector<Articulo>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                            articulos.add(new Articulo(rs.getInt("ca_id"),rs.getInt("ca_congreso_id"), rs.getString("ca_titulo"),rs.getString("ca_resumen"),rs.getInt("ca_estado"),rs.getInt("ca_idioma")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
                            //int congreso_id,String titulo, String resumen, int estado, int idioma)
			}
			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return articulos;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<Articulo>();	
	}
    
    public static Boolean ExisteArticulosPDFQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException
	{
		try {                    
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
                        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
                        Boolean hayDato=false;
			while(rs.next())
			{	
                            hayDato= true;
			}
			// Cuando terminé, siempre cierro la conexión
			conn.close();
                        return hayDato;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return false;	
	}
    
    public static Boolean ArticulosPDFQuery(String text, String nombrePDF) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException
	{
                //byte[] resultado = null;
                    File file = new File(nombrePDF);
                    FileOutputStream output = new FileOutputStream(file);
		try {                    
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
                        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
                        boolean existe=false;
			while(rs.next())
			{	
                            //resultado = rs.getBytes("av_contenido");
                            InputStream input = rs.getBinaryStream("av_contenido");
                            byte[] buffer = new byte[1024];
                            while (input.read(buffer) > 0) {
                                output.write(buffer);    
                            }
                            existe=true;
			}
			// Cuando terminé, siempre cierro la conexión
			conn.close();
                        //return file;
			//return resultado;
                        return existe;
		} catch(SQLException e1){
                    System.out.println(e1);
		} catch (IOException ex) {		
                    Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }		
		return false;	
	}
    
     public static Vector<AutoresSecundarios> AutoresSecundariosQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<AutoresSecundarios> autoresSecundarios = new Vector<AutoresSecundarios>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                            //autoresSecundarios.add(new AutoresSecundarios(rs.getInt("aas_id"),rs.getInt("aas_articulo_id"),rs.getString("ass_nombre"),rs.getString("ass_apellido"),rs.getString("ass_mail"),rs.getString("ass_filiacion")));
                            autoresSecundarios.add(new AutoresSecundarios(rs.getInt("aas_id"),rs.getInt("aas_articulo_id"),rs.getString("ass_nombre"),rs.getString("ass_apellido"),rs.getString("ass_mail"),rs.getString("ass_filiacion"),rs.getString("ass_oic")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
			}

			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return autoresSecundarios;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<AutoresSecundarios>();	
	}
     
     public static Vector<ArticulosEvaluaciones> ArticulosEvaluacionesQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<ArticulosEvaluaciones> articulosEvaluaciones = new Vector<ArticulosEvaluaciones>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{
                            
                            articulosEvaluaciones.add(new ArticulosEvaluaciones(rs.getInt("ca_congreso_id"),rs.getInt("aev_id"),rs.getInt("aev_articulo_id"), rs.getInt("aev_usuario_id"),rs.getInt("aev_calificacion"),rs.getString("aev_comentario_autor"),rs.getString("aev_comentario_organizador"),rs.getDate("aev_fecha")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
			}

			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return articulosEvaluaciones;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<ArticulosEvaluaciones>();	
	}
     
    public static Vector<Mail> MailQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Mail> mail = new Vector<Mail>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                            mail.add(new Mail(rs.getString("m_descripcion"),rs.getInt("m_id"),rs.getInt("m_idioma_id"),rs.getInt("m_tipo")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
			}

			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return mail;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<Mail>();	
	}
    
     public static Vector<MailTipo> MailTipoQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<MailTipo> mailTipo = new Vector<MailTipo>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                            mailTipo.add(new MailTipo(rs.getInt("mt_id"),rs.getString("mt_descripcion")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
			}

			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return mailTipo;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<MailTipo>();	
	}
     
    public static Vector<EMailEvaluar> EMailEvaluarQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<EMailEvaluar> emailEvaluador = new Vector<EMailEvaluar>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexión
			conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
	        // Instanción la instrucción
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucción
			ResultSet rs = stmt.executeQuery(text);
			while(rs.next())
			{	
                            emailEvaluador.add(new EMailEvaluar(rs.getString("u_mail"),rs.getInt("aev_calificacion"),rs.getDate("c_fin_eval_articulos")));
                            //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
			}

			// Cuando terminé, siempre cierro la conexión
			conn.close();
			return emailEvaluador;
		} catch(SQLException e1){
			System.out.println(e1);
		}		
		return new Vector<EMailEvaluar>();	
	} //EMailEvaluarConflictosQuery
    
    public static Vector<EMailEvaluar> EMailEvaluarConflictosQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{            
            try {
                    Vector<EMailEvaluar> emailEvaluador = new Vector<EMailEvaluar>();
                    // Creo una nueva instancia de driver
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    // Obtengo la conexión
                    conn=DriverManager.getConnection(myConnectionString, sUsuarioDB, sPasswordDB);
            // Instanción la instrucción
                    stmt = conn.createStatement();
                    // Cargo un set de resultados ejecutando la instrucción
                    ResultSet rs = stmt.executeQuery(text);
                    while(rs.next())
                    {	
                        emailEvaluador.add(new EMailEvaluar(rs.getString("u_mail"),rs.getInt("aev_calificacion"),rs.getDate("c_ini_conf_articulos")));
                        //int congreso_id,String titulo, String resumen, String estado, String idioma,byte[] pdf
                    }
                    // Cuando terminé, siempre cierro la conexión
                    conn.close();
                    return emailEvaluador;
            } catch(SQLException e1){
                    System.out.println(e1);
            }		
            return new Vector<EMailEvaluar>();	
	}
}