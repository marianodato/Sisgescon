package comun;
import BD.GestorBaseDatos;
import Clases.Articulo;
import Clases.AutoresSecundarios;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import Clases.Usuario;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AlumnoUB
 */
public abstract class CInternalFrame extends javax.swing.JInternalFrame{
    
    protected JDesktopPane puntero;
    
    public CInternalFrame(String titulo) {
        super(titulo, false, true, false, false);
        setVisible(true);       
    } 
    
    public void setPuntero(JDesktopPane puntero) {
        //if (this.puntero == null) {
            this.puntero = puntero;
            this.setLocation(puntero.getWidth() / 2 - getWidth() / 2 , puntero.getHeight() / 2 - getHeight() / 2);
        //}
    }
   
    
    protected void mostrarMensaje(String msg, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, msg, titulo, tipo);
    }
    

    public void abrirVentana(CInternalFrame ventana) {
        dispose();
        puntero.removeAll();
        puntero.add(ventana);
        ventana.setPuntero(puntero);
        
    }
    
    public void cerrarVentana() {
        dispose();
    }
    
    protected static String wrap(String s) {
        return "<html>".concat(s).concat("</html>");
    }
    
    protected static String wrap(String s, int espacio) {
        return "<html><p style=\"line-height:".concat(String.valueOf(espacio)).concat("px;\">").concat(s).concat("</p></html>");
    }
    
    public static String convertirFecha(Date d) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        return formatter.format(d);
    }
    
    protected static String getNombreCompleto(Usuario u) {
        return u.getS_nombre().concat(" ").concat(u.getS_apellido());
    }
    
    
    protected java.sql.Date convertirFechaSql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    
    protected String getAutores(Articulo a) {
        String resultado = "<html>".concat(getNombreCompleto(a.getAutor())).concat(", ");
        try {
            Vector<AutoresSecundarios> autoresSecundarios = new Vector<>();
            autoresSecundarios = GestorBaseDatos.BuscarAutoresSecundarios(a.getArticulo_id());
            for (AutoresSecundarios as : autoresSecundarios) {
                resultado = resultado.concat(as.getAss_nombre()).concat(" ").concat(as.getAss_apellido()).concat(", ");
            }
            return resultado.substring(0, resultado.length()-", ".length()).concat("</html>");
        } catch (InstantiationException ex) {
            Logger.getLogger(CInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
}
