package Clases;

import BD.BaseDatos;
import BD.GestorBaseDatos;
import GestionMails.ControladorMail;
import forms.frmMain;
import java.awt.*;
import java.awt.SplashScreen;
import java.net.ConnectException;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class ScreenSplash {
    ControladorMail controlador=new ControladorMail();
    public final SplashScreen splash ;
    final String[] texto = {"Cargando Paneles",
                            "Cargando Modulo Visual","Conectando a la Base de Datos", ""};
     boolean connection = false;
    public ScreenSplash() {
        splash = SplashScreen.getSplashScreen();
    }

   public void animar() throws ClassNotFoundException, InstantiationException, IllegalAccessException
   {
        if (splash != null)
        {
            Graphics2D g = splash.createGraphics();
            for(int i=1; i<texto.length; i++)
            {                                                   
                //se pinta texto del array
                g.setColor( new Color(4,52,101));//color de fondo
                g.fillRect(3, 28,300,12);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.drawString("Cargando "+texto[i-1]+"...", 3, 38);                
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(4, 8,(i*307/texto.length), 12);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                //float dash1[] = {2.0f};
                //BasicStroke dashed = new BasicStroke(9.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash1, 0.0f);
                //g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor( new Color(4,52,101));
                
                splash.update();
                try {
                    Thread.sleep(1000);
                } 
                    catch(InterruptedException e) { }
                }
            splash.close();
        }
        try {
                connection = BaseDatos.Check();
                
                if (!connection){ return;}
 
                
               frmMain main=new frmMain();
main.setVisible(true);
            
               Vector vector=GestorBaseDatos.BuscarEMailEvaluarTodos();
if(vector.size()!=0){
String destinatario=controlador.arrayToString(vector);
controlador.setPanel(main.getPanel());
GestionMails.Mail mail=new GestionMails.Mail(destinatario,GestorBaseDatos.BuscarMailTipo(9).get(0).getMt_descripcion(),GestorBaseDatos.BuscarMailCompleto(9).get(0).getM_descripcion(),1);
Date fecha =new Date();
controlador.setEvaluacionLimite(fecha);
controlador.EnvioRecordatorio(mail);
}else{
    System.out.println("Todos los evaluadores estan al dia o entre las fechas para realizar evaluaciones");
}
Vector conflictos=GestorBaseDatos.BuscarEMailEvaluarConflictos();
if(vector.size()!=0){
String destinatario=controlador.arrayToString(vector);
controlador.setPanel(main.getPanel());
GestionMails.Mail mail=new GestionMails.Mail(destinatario,GestorBaseDatos.BuscarMailTipo(12).get(0).getMt_descripcion(),GestorBaseDatos.BuscarMailCompleto(12).get(0).getM_descripcion(),1);
controlador.envioAutomatico(mail);
}             
else{System.out.println("No se encontraron evaluadores que no hayan resuelto los conflictos y preferencias");}
               
               
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}