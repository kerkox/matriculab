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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */
@Entity
@Table(name = "CUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cupo.findAll", query = "SELECT c FROM Cupo c"),
    @NamedQuery(name = "Cupo.findById", query = "SELECT c FROM Cupo c WHERE c.id = :id"),
    @NamedQuery(name = "Cupo.findByCantidad", query = "SELECT c FROM Cupo c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Cupo.findByDisponibles", query = "SELECT c FROM Cupo c WHERE c.disponibles = :disponibles")})
public class Cupo implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int cantidad;
    @Column
    private int disponibles;
    @OneToOne
    private Programa programa;

    public Cupo() {
    }
    

    public Cupo(int cantidad, Programa programa) {
        this.cantidad = cantidad;
        this.disponibles = cantidad;
        this.programa = programa;
    }

    //============================
    //Metodos Get
    public int getCantidad() {
        return cantidad;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public Programa getPrograma() {
        return programa;
    }

    public long getId() {
        return id;
    }
    
    
    
    //============================
    
    //============================
    //Metodos Set
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    //============================

    public void setId(long id) {
        this.id = id;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    //Metodos usados para cuando se agrega o cancela un cupo
    
    /**
     * Decrementa en uno la cantidad disponible 
     * de cupos cuando se matricula un curso
     */
    public void DecrementarDisponibles(){
        this.disponibles -= 1;
    }
    /**
     * Incrementa en uno la disponiblidad 
     * de cupos cuando un estudiante cancela un curso
     */
    public void IncrementarDisponibles(){
        this.disponibles += 1;
    }

    public Cupo(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

  

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cupo other = (Cupo) obj;
       
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        return true;
    }

  

    
    @Override
    public String toString() {
        return "Matricula.logic.Cupo[ id=" + id + " ]";
    }
    

}
