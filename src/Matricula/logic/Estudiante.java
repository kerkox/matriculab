/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Tabulado getTabulado(Periodo periodo) {
        if (tabulados.isEmpty()) {
            return null;
        }
        for (Tabulado tabu : tabulados) {
            if (tabu.getPeriodo().equals(periodo)) {
                return tabu;
            }
        }
        return null;
    }

    //==================================
    //==================================
    //Metodos Add
    public void add(Tabulado tabulado) {
        this.tabulados.add(tabulado);
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
    public void ActualizarTabulado() {
        this.tabulados.add(tabuladoActual);
        tabuladoActual = null;
    }

    public void EliminarDeuda(int index) {
        this.deudas.remove(index);

    }

    public void EliminarDeuda(Deuda deuda) {
        this.deudas.remove(deuda);
    }

    //==================================
    //Metodos de matricula

    public void Matricular(Curso curso, Periodo periodo) throws Exception {
        //////*********************************
        if (tabuladoActual == null) {
            tabuladoActual = new Tabulado(periodo);
            this.tabulados.add(tabuladoActual);
        }
        tabuladoActual.MatricularCurso(curso);
    }

    //==================================
    //Metodos de Cancelar curso
    public void Cancelar(Curso curso) throws Exception {

        tabuladoActual.CancelarCurso(curso);

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
