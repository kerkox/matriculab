package Matricula.logic;

import java.io.Serializable;
import java.util.Objects;

public class Deuda implements Serializable {
   

   private Periodo periodo;
   private String dependencia;
   private String observacion;
   //=====================================
   
    public Deuda() {
    }

    public Deuda(Periodo periodo) {
        this.periodo = periodo;
    }

    public Deuda(Periodo periodo, String dependencia) {
        this.periodo = periodo;
        this.dependencia = dependencia;
    }
    
    

    //=====================================
    //Metodos Get
    public Periodo getPeriodo() {
        return periodo;
    }

    public String getDependencia() {
        return dependencia;
    }

    public String getObservacion() {
        return observacion;
    }
    

    //=====================================
    //=====================================
    //Metodos Set

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
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
