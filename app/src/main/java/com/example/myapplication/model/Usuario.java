package com.example.myapplication.model;

import android.media.session.MediaSession;

import java.util.Date;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String contrasenia;

    private Date LastConnection;

    private String token;


    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String contrasenia, Date lastConnection, String token) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        LastConnection = lastConnection;
        this.token = token;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getLastConnection() {
        return LastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        LastConnection = lastConnection;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
