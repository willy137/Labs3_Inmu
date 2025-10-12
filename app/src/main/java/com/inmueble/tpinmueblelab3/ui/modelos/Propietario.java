package com.inmueble.tpinmueblelab3.ui.modelos;

import java.io.Serializable;

public class Propietario implements Serializable {

    private int idProp;

    private int dni;

    private String apellido;

    private String nombre;

    private int telefono;

    private String mail;

    private String password;

    public Propietario() {
    }

    public Propietario(int idProp, int dni, String apellido, String nombre, int telefono, String mail, String password) {
        this.idProp = idProp;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.password = password;
    }

    public int getIdProp() {
        return idProp;
    }

    public void setIdProp(int idProp) {
        this.idProp = idProp;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
