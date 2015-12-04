/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Polker
 */
@Entity
@Table(name = "DOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByIdentificacion", query = "SELECT d FROM Docente d WHERE d.identificacion = :identificacion"),
    @NamedQuery(name = "Docente.findByApellido", query = "SELECT d FROM Docente d WHERE d.apellido = :apellido"),
    @NamedQuery(name = "Docente.findByNombre", query = "SELECT d FROM Docente d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Docente.findByProfesion", query = "SELECT d FROM Docente d WHERE d.profesion = :profesion")})
public class Docente extends Persona{

    
    @Column(nullable = false, length = 80)
    private String profesion;

    public Docente() {
    }

    public Docente(String profesion, long identificacion, String nombre, String apellido, String password) {
        super(identificacion, nombre, apellido, password);
        this.profesion = profesion;
    }

    //==============================
    //Metodos Get
    public String getProfesion() {
        return profesion;
    }
    
    
    //==============================
    //Metodos Set
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    //==============================



    
    
}
