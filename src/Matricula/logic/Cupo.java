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
public class Cupo implements Serializable {
    
    private int cantidad;
    private int disponibles;
    private Programa programa;

    public Cupo() {
    }
    

    public Cupo(int cantidad, Programa programa) {
        this.cantidad = cantidad;
        this.disponibles = cantidad;
        this.programa = programa;
    }

    //============================
    //Metodos Get
    public int getCantidad() {
        return cantidad;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public Programa getPrograma() {
        return programa;
    }

    
    //============================
    
    //============================
    //Metodos Set
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    //============================

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    //Metodos usados para cuando se agrega o cancela un cupo
    
    /**
     * Decrementa en uno la cantidad disponible 
     * de cupos cuando se matricula un curso
     */
    public void DecrementarDisponibles(){
        this.disponibles -= 1;
    }
    /**
     * Incrementa en uno la disponiblidad 
     * de cupos cuando un estudiante cancela un curso
     */
    public void IncrementarDisponibles(){
        this.disponibles += 1;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

  

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cupo other = (Cupo) obj;
       
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        return true;
    }

  

    
    @Override
    public String toString() {
        return programa +" disponbles: " + disponibles;
    }
    

}
