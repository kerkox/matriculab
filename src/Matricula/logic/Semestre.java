/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */
@Entity
@Table(name = "SEMESTRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semestre.findAll", query = "SELECT s FROM Semestre s"),
    @NamedQuery(name = "Semestre.findById", query = "SELECT s FROM Semestre s WHERE s.id = :id"),
    @NamedQuery(name = "Semestre.findByNumero", query = "SELECT s FROM Semestre s WHERE s.numero = :numero")})
public class Semestre implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private byte numero;
    @OneToMany
    private List<Asignatura> asignaturas = new ArrayList<>();
    

    public Semestre() {
    }

    public Semestre(byte numero) {
        this.numero = numero;
    }

    //==============================
    //Metodos Get
    public byte getNumero() {
        return numero;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }
    
    public Long getId() {
        return id;
    }
    
    //==============================
    //Metodos Set
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    //==============================
    
    //==============================
    //Metodos buscar
    
    public Asignatura buscar(String codigo) throws Exception{
        
        for(Asignatura asignature: this.asignaturas){
            if(asignature.getCodigo().equals(codigo)){
                return asignature;
            }
        }
        throw new Exception("No se encuentra la Asignatura con codigo: "+ codigo);
    }
  
    
    
}
