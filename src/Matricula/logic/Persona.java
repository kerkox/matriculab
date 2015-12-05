/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;

/**
 *
 * @author atenea
 */
public abstract class Persona implements Serializable {
    
    long identificacion;
    String nombre;
    String apellido;
    String password;

    public Persona() {
    }

    public Persona(long identificacion, String nombre, String apellido, String password) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    //==============================
    //Metodos Get

    public String getPassword() {
        return password;
    }
    
    
    public long getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFullName(){
        return nombre +" "+apellido;
    }
    //==============================

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.identificacion != other.identificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido ;
    }
    
    
    
}
