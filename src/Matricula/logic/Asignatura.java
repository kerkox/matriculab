/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author atenea
 */
public class Asignatura implements Serializable {
  
   
    
    private String codigo; 
    private String nombre; 
    private byte creditos; 
    private byte intensidad; 

    public Asignatura() {
    }

    public Asignatura(String codigo, String nombre, byte creditos, byte intensidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos; 
        this.intensidad = intensidad; 
    }

    
    //==============================
    //Metodos Get
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getCreditos() {
        return creditos;
    }

    public byte getIntensidad() {
        return intensidad;
    }
    
    //==============================

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreditos(byte creditos) {
        this.creditos = creditos;
    }

    public void setIntensidad(byte intensidad) {
        this.intensidad = intensidad;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignatura other = (Asignatura) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public Asignatura(String codigo) {
        this.codigo = codigo;
    }

    public Asignatura(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  nombre + " "+ codigo;
    }

      
}
