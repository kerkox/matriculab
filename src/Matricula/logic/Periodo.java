/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.enumclass.Estado;
import Matricula.logic.enumclass.Mes;
import Matricula.persistence.CursoJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */
@Entity
@Table(name = "PERIODO", uniqueConstraints = @UniqueConstraint(columnNames = {"inicia", "year1"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findById", query = "SELECT p FROM Periodo p WHERE p.id = :id"),
    @NamedQuery(name = "Periodo.findByActual", query = "SELECT p FROM Periodo p WHERE p.actual = :actual"),
    @NamedQuery(name = "Periodo.findByFin", query = "SELECT p FROM Periodo p WHERE p.fin = :fin"),
    @NamedQuery(name = "Periodo.findByInicia", query = "SELECT p FROM Periodo p WHERE p.inicia = :inicia"),
    @NamedQuery(name = "Periodo.findByYear1", query = "SELECT p FROM Periodo p WHERE p.year1 = :year1")})
public class Periodo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Mes inicia;
    @Column
    private Mes fin;
    @Column(nullable = false)
    private int year1 = 0;
    @OneToMany
    private List<Curso> cursos = new ArrayList<>();
    @Column(nullable = false)
    private boolean actual;

    public Periodo() {
        this.actual = true;
    }

    public Periodo(Mes inicia, Mes fin, int year1) {
        this.inicia = inicia;
        this.fin = fin;
        this.year1 = year1;
        this.actual = true;
    }

    //============================
    //Metodos Get
    public boolean isActual() {
        return actual;
    }

    public Mes getInicia() {
        return inicia;
    }

    public Mes getFin() {
        return fin;
    }

    public int getYear() {
        return year1;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public Long getId() {
        return id;
    }

    //============================
    //============================
    //Metodo Agregar
    public void add(Curso curso) throws Exception {
        if (cursos.contains(curso)) {
            throw new Exception("Curso ya registrado");
        }
        this.cursos.add(curso);
    }

    //============================
    //Metodos Set
    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public void setInicia(Mes inicia) {
        this.inicia = inicia;
    }

    public void setFin(Mes fin) {
        this.fin = fin;
    }

    public void setYear(int year) {
        this.year1 = year;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //==============================

    public void editCurso(Curso curso) {
        Curso course = this.cursos.get(this.cursos.indexOf(curso));
        course.setEstado(Estado.ACTIVO);
    }

    //==============================
    //Metodo Cancelar Curso
    public void CancelarCurso(Curso curso) {
        Curso course = this.cursos.get(this.cursos.indexOf(id));
        course.setEstado(Estado.CANCELADO);
    }

    public void CancelarCurso(int index, CursoJpaController CursoJpa) throws Exception {
        Curso curso = this.cursos.get(index);
        curso.setEstado(Estado.CANCELADO);
        CursoJpa.edit(curso);
        this.cursos.set(index, curso);

    }

    //==============================
    //==============================
    //Metodos Buscar
    /**
     *
     * @param codigoAsig
     * @return la lista con los cursos programados de una asignatura si no se
     * encuentra la asignatura con el codigo, devuelve error
     *
     */
    public List<Curso> buscar(String codigoAsig) throws ObjectNotFoundException {
        ArrayList<Curso> cursosProgramados = new ArrayList<>();
        for (Curso curso : this.cursos) {

            if (curso.getAsignatura().equals(codigoAsig)) {
                cursosProgramados.add(curso);
            }

        }
        if (cursosProgramados.isEmpty()) {
            throw new ObjectNotFoundException("Asignatura con codigo: " + codigoAsig + " No encontrada");
        }

        return cursosProgramados;

    }

    public List<Curso> cursosPrograma(Programa programa) {
        ArrayList<Curso> cursosPrograma = new ArrayList<>();
        for (Curso curso : this.cursos) {
            if (curso.getEstado() == Estado.ACTIVO) {
                for (Cupo cupo : curso.getCupos()) {
                    if (cupo.getPrograma().equals(programa)) {
                        cursosPrograma.add(curso);

                    }
                }
            }

        }

        return cursosPrograma;
    }

    public Curso buscar(Curso curso) throws Exception {
        for (Curso curse : this.cursos) {
            if (curse.equals(curso)) {
                return curse;
            }
        }
        throw new Exception("Curso no encontrado: asinatura codigo"
                + curso.getAsignatura().getCodigo() + " Grupo: "
                + curso.getGrupo()); // agregar datos
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
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.inicia, other.inicia)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        if (this.year1 != other.year1) {
            return false;
        }
        return true;
    }

    public Periodo(Long id) {
        this.id = id;
    }

    public Periodo(Long id, boolean actual, int year1) {
        this.id = id;
        this.actual = actual;
        this.year1 = year1;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    @Override
    public String toString() {
        if (year1 == 0) {
            return inicia + " - " + fin;
        }
        return inicia + " - " + fin + " " + year1;
    }

}
