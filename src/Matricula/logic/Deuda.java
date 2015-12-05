/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author atenea
 */
public class Deuda implements Serializable {
   

   private Periodo periodo;
    //=====================================

    public Deuda() {
    }

    public Deuda(Periodo periodo) {
        this.periodo = periodo;
    }

    //=====================================
    //Metodos Get
    public Periodo getPeriodo() {
        return periodo;
    }

    //=====================================
    //=====================================
    //Metodos Set

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

  //=====================================
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deuda other = (Deuda) obj;
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return periodo.toString();
    }

}
