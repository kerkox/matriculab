/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.logic.enumclass.Jornada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PROGRAMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByCodigo", query = "SELECT p FROM Programa p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Programa.findByJornada", query = "SELECT p FROM Programa p WHERE p.jornada = :jornada"),
    @NamedQuery(name = "Programa.findByNombre", query = "SELECT p FROM Programa p WHERE p.nombre = :nombre")})
public class Programa implements Serializable {
   
    @Id
    private String codigo;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column
    private Jornada jornada;
    @OneToMany
    private List<Semestre> semestres = new ArrayList<>();

    public Programa() {
    }

    public Programa(String codigo, String nombre, Jornada jornada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.jornada = jornada;
    }

    //==============================
    //Metodos GET

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    //==============================
    //Metodos Set
    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    //==============================
    //==============================
    //Metodo Buscar

    public Asignatura buscar(String codigo, byte semestreNumero) throws Exception {
        for (Semestre sem : this.semestres) {
            if (sem.getNumero() == semestreNumero) {
                return sem.buscar(codigo);
            }
        }

        throw new Exception("Semestre Invalido");
    }

    //==============================
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programa other = (Programa) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + " - "+ nombre;
    }

    
    
    
}
