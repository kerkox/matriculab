/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.persistence.CursoJpaController;
import Matricula.persistence.MatriculaJpaController;
import Matricula.persistence.TabuladoJpaController;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author atenea
 */

public class Estudiante extends Persona {


    private String codigo;
    private Tabulado tabuladoActual;
    private List<Tabulado> tabulados = new ArrayList<>();
    private List<Deuda> deudas = new ArrayList<>();
    private Programa programa;
    
    public Estudiante() {
    }

    public Estudiante(String codigo, long identificacion, String nombre, String apellido, String password, Programa programa) {
        super(identificacion, nombre, apellido, password);
        this.codigo = codigo;
        this.programa = programa;
    }

    //==================================
    //Metodos Get

    public String getCodigo() {
        return codigo;
    }

    public Programa getPrograma() {
        return programa;
    }
    
    public List<Tabulado> getTabulados() {
        return tabulados;
    }

    public List<Deuda> getDeudas() {
        return deudas;
    }

    public Tabulado getTabuladoActual() {
        return tabuladoActual;
    }
    

    
    public Tabulado getTabulado(Periodo periodo){
        if (tabulados.isEmpty()) {
            return null;
        }
        for (Tabulado tabu : tabulados) {
            if (tabu.getPeriodo().equals(periodo) ) {
                return tabu;
            }
        }
        return null;
    }
    
    
    //==================================
    //==================================
    //Metodos Add
    public void add(Tabulado tabulado) {
        if (tabuladoActual == null) {
            this.tabulados.add(tabulado);
        } else {
            Tabulado back = tabuladoActual;
            this.tabulados.add(back);
            tabuladoActual = tabulado;
        }

    }

    public void add(Deuda deuda) {
        this.deudas.add(deuda);
    }

    //==================================
    //==================================
    //Metodos Set
    
    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTabulados(List<Tabulado> tabulados) {
        this.tabulados = tabulados;
    }

    public void setDeudas(List<Deuda> deudas) {
        this.deudas = deudas;
    }

    //=================================
    public void ActualizarTabulado(){
        this.tabulados.add(tabuladoActual);
        tabuladoActual=null;
    }
    
    
    //==================================
    //Metodos de matricula
    public void Matricular(Curso curso, Periodo periodo, CursoJpaController CursoJpa, MatriculaJpaController matriculaJpa) throws Exception {
        //////*********************************
        if (getTabuladoActual() == null) {
            this.tabulados.add(new Tabulado(periodo));
        }
        getTabuladoActual().MatricularCurso(curso, CursoJpa, matriculaJpa);
    }

    //==================================
    //Metodos de Cancelar curso
    public void Cancelar(Curso curso) {
        getTabuladoActual().CancelarCurso(curso);
    }

    public void Cancelar(Curso curso, CursoJpaController CursoJpa, MatriculaJpaController matriculaJpa) throws Exception {

        getTabuladoActual().CancelarCurso(curso, CursoJpa, matriculaJpa);

    }

    //==================================
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estudiante other = (Estudiante) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.getFullName() + " Codigo: " + codigo;
    }
    
    

}
