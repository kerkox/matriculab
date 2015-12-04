/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdentificacion", query = "SELECT p FROM Persona p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")})

//    @NamedQuery(name = "Persona.findByDtype", query = "SELECT p FROM Persona p WHERE p.dtype = :dtype"),
public abstract class Persona implements Serializable {
    
    @Id
    long identificacion;
    @Column(nullable = false, length = 50)
    String nombre;
    @Column(nullable = false, length = 50)
    String apellido;
    @Column(nullable = false, length = 50)
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
