/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.logic.enumclass.Estado;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */
@Entity
@Table(name = "MATRICULA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findById", query = "SELECT m FROM Matricula m WHERE m.id = :id"),
    @NamedQuery(name = "Matricula.findByCancelada", query = "SELECT m FROM Matricula m WHERE m.cancelada = :cancelada"),
    @NamedQuery(name = "Matricula.findByEstado", query = "SELECT m FROM Matricula m WHERE m.estado = :estado"),
    @NamedQuery(name = "Matricula.findByMatriculada", query = "SELECT m FROM Matricula m WHERE m.matriculada = :matriculada")})
public class Matricula implements Serializable {
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date matriculada;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cancelada;
    @OneToOne
    private Curso curso;
    @Column
    private Estado estado;

    

    public Matricula() {
    }
    
    public Matricula(Date matriculada, Curso curso) {
        this.matriculada = matriculada;
        this.curso = curso;
        this.estado= Estado.ACTIVO;
    }

    
    
    //============================
    //Metodos Get
    
    public Estado getEstado(){
            return estado;
    }
    
    public Date getMatriculada() {
        return matriculada;
    }

    public Date getCancelada() {
        return cancelada;
    }

    public Curso getCurso() {
        return curso;
    }
    public Long getId() {
        return id;
    }
    
    
    //============================

    //============================
    //Metodos Set
    public void setCancelada(Date cancelada) {
        this.cancelada = cancelada;
        this.estado = Estado.CANCELADO;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setMatriculada(Date matriculada) {
        this.matriculada = matriculada;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public void setEstado(Estado estado){
        this.estado= estado;
        if(estado == Estado.ACTIVO){
        this.cancelada=null;
        }
        
    }

    //============================

    
    
    public Matricula(Long id) {
        this.id = id;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "Matricula.logic.Matricula[ id=" + id + " ]";
    }

    
}
