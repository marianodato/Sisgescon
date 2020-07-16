package Clases;

import BD.GestorBaseDatos;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario extends GestorBaseDatos {
    private int i_Id;
    private String s_apellido;
    private String s_nombre;
    private String s_direccion;
    private String s_ciudad;
    private String s_mail;
    private String s_password;
    private String s_usuario;
    private String s_cp;
    private String s_pais;
    private String s_sitioweb;
    private String s_filiacion;
    private boolean b_miembro;
    private boolean b_ingresado;
    
    public Usuario() {
        this.b_ingresado=false;
    }

    public Usuario(int i_Id,String s_apellido, String s_nombre,String s_direccion,String s_ciudad,String s_mail, String s_usuario,String s_password,String s_cp,String s_pais,String s_sitioweb,String s_filiacion, Boolean b_miembro) {
        this.i_Id=i_Id;
        this.s_apellido=s_apellido;
        this.s_nombre=s_nombre;
        this.s_direccion=s_direccion;
        this.s_ciudad=s_ciudad;
        this.s_mail=s_mail;
        this.s_password=s_password;
        this.s_usuario=s_usuario;
        this.s_cp=s_cp;
        this.s_pais=s_pais;
        this.s_sitioweb=s_sitioweb;
        this.s_filiacion=s_filiacion;
        this.b_miembro=b_miembro;
        this.b_ingresado=true;
    }

    public int getI_Id() {
        return i_Id;
    }

    public void setI_Id(int i_Id) {
        this.i_Id = i_Id;
    }

    public String getS_apellido() {
        return s_apellido;
    }

    public void setS_apellido(String s_apellido) {
        this.s_apellido = s_apellido;
    }


    public String getS_nombre() {
        return s_nombre;
    }


    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getS_direccion() {
        return s_direccion;
    }

    public void setS_direccion(String s_direccion) {
        this.s_direccion = s_direccion;
    }

    public String getS_ciudad() {
        return s_ciudad;
    }

    public void setS_ciudad(String s_ciudad) {
        this.s_ciudad = s_ciudad;
    }

    public String getS_mail() {
        return s_mail;
    }

    public void setS_mail(String s_mail) {
        this.s_mail = s_mail;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_usuario() {
        return s_usuario;
    }

    public void setS_usuario(String s_usuario) {
        this.s_usuario = s_usuario;
    }

    public String getS_cp() {
        return s_cp;
    }

    public void setS_cp(String s_cp) {
        this.s_cp = s_cp;
    }

    public String getS_pais() {
        return s_pais;
    }

    public void setS_pais(String s_pais) {
        this.s_pais = s_pais;
    }

    public String getS_sitioweb() {
        return s_sitioweb;
    }

    public void setS_sitioweb(String s_sitioweb) {
        this.s_sitioweb = s_sitioweb;
    }

    public String getS_filiacion() {
        return s_filiacion;
    }

    public void setS_filiacion(String s_filiacion) {
        this.s_filiacion = s_filiacion;
    }

    public Boolean getB_miembro() {
        return b_miembro;
    }

    public void setB_miembro(Boolean b_miembro) {
        this.b_miembro = b_miembro;
    }

    public boolean getB_ingresado() {
        return b_ingresado;
    }

    public void setB_ingresado(boolean b_ingresado) {
        this.b_ingresado = b_ingresado;
    }
    
    public boolean isB_miembro() {
        return b_miembro;
    }

    public void setB_miembro(boolean b_miembro) {
        this.b_miembro = b_miembro;
    }

    public void clear() {
        this.i_Id=0;
        this.s_apellido="";
        this.s_nombre="";
        this.s_direccion="";
        this.s_ciudad="";
        this.s_mail="";
        this.s_password="";
        this.s_usuario="";
        this.s_cp="";
        this.s_pais="";
        this.s_sitioweb="";
        this.s_filiacion="";
        this.b_miembro=false;
        this.b_ingresado=false;
    }
    
}
