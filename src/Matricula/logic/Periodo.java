/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.enumclass.Estado;
import Matricula.logic.enumclass.Mes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author atenea
 */
public class Periodo implements Serializable {

    private Mes inicia;
    private Mes fin;
    private int year = 0;
    private List<Curso> cursos = new ArrayList<>();
    private boolean actual;

    public Periodo() {
        this.actual = true;
    }

    public Periodo(Mes inicia, Mes fin, int year1) {
        this.inicia = inicia;
        this.fin = fin;
        this.year = year1;
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
        return year;
    }

    public List<Curso> getCursos() {
        return cursos;
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
        this.year = year;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

  //==============================
    public void editCurso(Curso curso) {
        Curso course = this.cursos.get(this.cursos.indexOf(curso));
        course.setEstado(Estado.ACTIVO);
    }

    //==============================
    //Metodo Cancelar Curso
    public void CancelarCurso(Curso curso) {
        Curso course = this.cursos.get(this.cursos.indexOf(curso));
        course.setEstado(Estado.CANCELADO);
    }

    public void CancelarCurso(int index) throws Exception {
        Curso curso = this.cursos.get(index);
        curso.setEstado(Estado.CANCELADO);
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
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (year == 0) {
            return inicia + " - " + fin;
        }
        return inicia + " - " + fin + " " + year;
    }

}
