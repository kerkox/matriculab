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

/**
 *
 * @author atenea
 */
public class Matricula implements Serializable {

    private Date matriculada;
    private Date cancelada;
    private Curso curso;
    private Estado estado;

    public Matricula() {
    }

    public Matricula(Date matriculada, Curso curso) {
        this.matriculada = matriculada;
        this.curso = curso;
        this.estado = Estado.ACTIVO;
    }

    //============================
    //Metodos Get
    public Estado getEstado() {
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

    //============================
    //============================
    //Metodos Set
    public void setCancelada(Date cancelada) {
        this.cancelada = cancelada;
        this.estado = Estado.CANCELADO;
    }

    public void setMatriculada(Date matriculada) {
        this.matriculada = matriculada;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        if (estado == Estado.ACTIVO) {
            this.cancelada = null;
        }

    }

    //============================
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
        return curso + " " + estado;
    }

}
