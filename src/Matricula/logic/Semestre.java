package Matricula.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Semestre implements Serializable {
    
    private byte numero;
    private List<Asignatura> asignaturas = new ArrayList<>();
    

    public Semestre() {
    }

    public Semestre(byte numero) {
        this.numero = numero;
    }

    //==============================
    //Metodos Get
    public byte getNumero() {
        return numero;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }
    
    //==============================
    //Metodos Set
    
    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    //==============================
    
    //==============================
    //Metodos buscar
    
    public Asignatura buscar(String codigo) throws Exception{
        
        for(Asignatura asignature: this.asignaturas){
            if(asignature.getCodigo().equals(codigo)){
                return asignature;
            }
        }
        throw new Exception("No se encuentra la Asignatura con codigo: "+ codigo);
    }
  
    
    
}
