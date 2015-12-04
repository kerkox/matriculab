/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import Matricula.logic.enumclass.Estado;
import Matricula.persistence.CursoJpaController;
import Matricula.persistence.MatriculaJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author atenea
 */

public class Tabulado implements Serializable {


    private List<Matricula> matriculas = new ArrayList<>();

    private Periodo periodo;

    private byte creditos = 0;



    public Tabulado() {
    }

    public Tabulado(Periodo perido) {
        this.periodo = perido;


    }
    
    public void ActualizarCreditos(){
        this.creditos=0;
        for(Matricula matri : this.matriculas){
            if(matri.getEstado()==Estado.ACTIVO){
            this.creditos += matri.getCurso().getAsignatura().getCreditos();
            }
        }
    }

    //==============================
    //Metodos Get
    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public byte getCreditos() {
        return creditos;
    }


    //==============================
    //==============================
    //Metodos Set

    public void setCreditos(byte creditos) {
        this.creditos = creditos;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    //==============================
    //Matricular cursos
    //////*********************************
    public void MatricularCurso(Curso curso) throws Exception {
        if (matriculas.contains(new Matricula(new Date(), curso))) {
            Matricula matri = matriculas.get(matriculas.indexOf(new Matricula(new Date(), curso)));
            if (matri.getCurso().getEstado() == Estado.CANCELADO) {
                matri.getCurso().setEstado(Estado.ACTIVO);
                creditos += matri.getCurso().getAsignatura().getCreditos();

            } else {
                throw new Exception("Curso ya Matriculado");
            }
        } else {
            this.matriculas.add(new Matricula(new Date(), curso));

            creditos += curso.getAsignatura().getCreditos();

        }
    }

    public void MatricularCurso(Curso curso, CursoJpaController CursoJpa, MatriculaJpaController matriculaJpa) throws Exception {
        if (matriculas.contains(new Matricula(new Date(), curso))) {
            Matricula matri = matriculas.get(matriculas.indexOf(new Matricula(new Date(), curso)));
            if (matri.getEstado() == Estado.CANCELADO) {
                matri.setEstado(Estado.ACTIVO);
                creditos += matri.getCurso().getAsignatura().getCreditos();
                matriculaJpa.edit(matri);

            } else {
                throw new Exception("Curso ya Matriculado");
            }
        } else {
            this.matriculas.add(new Matricula(new Date(), curso));
            
            creditos += curso.getAsignatura().getCreditos();

        }
    }

    //==============================
    //Cancelar cursos
    //////*********************************
    public void CancelarCurso(Curso curso) {
        Matricula matri = matriculas.get(matriculas.indexOf(new Matricula(new Date(), curso)));
        if (matri.getCurso().getEstado() == Estado.ACTIVO) {
            matri.getCurso().setEstado(Estado.CANCELADO);
            matri.setCancelada(new Date());
            creditos -= matri.getCurso().getAsignatura().getCreditos();

        }
    }

    public void CancelarCurso(Curso curso, CursoJpaController CursoJpa, MatriculaJpaController matriculaJpa) throws Exception {
        Matricula matri = matriculas.get(matriculas.indexOf(new Matricula(new Date(), curso)));
        if (matri.getCurso().getEstado() == Estado.ACTIVO) {
            if((creditos-matri.getCurso().getAsignatura().getCreditos())<6) throw new Exception("ERROR: no se puede cancelar cantidad minima de creditos invalida (6)");
//            matri.getCurso().setEstado(EstadoCurso.CANCELADO);
//            CursoJpa.edit(matri.getCurso());
            matri.setCancelada(new Date());
            matriculaJpa.edit(matri);
            creditos -= matri.getCurso().getAsignatura().getCreditos();

        }
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
        final Tabulado other = (Tabulado) obj;
        if (!Objects.equals(this.matriculas, other.matriculas)) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        return true;
    }

}