/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author atenea
 */
@Entity
@Table(name = "ASIGNATURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByCodigo", query = "SELECT a FROM Asignatura a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Asignatura.findByCreditos", query = "SELECT a FROM Asignatura a WHERE a.creditos = :creditos"),
    @NamedQuery(name = "Asignatura.findByIntensidad", query = "SELECT a FROM Asignatura a WHERE a.intensidad = :intensidad"),
    @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre")})
public class Asignatura implements Serializable {
  
   
    
    @Id
    private String codigo; 
    @Column(nullable = false, length = 80)
    private String nombre; 
    @Column
    private byte creditos; 
    @Column
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
