/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.logic;

/**
 *
 * @author Polker
 */
public class Docente extends Persona{

    
    
    private String profesion;

    public Docente() {
    }

    public Docente(String profesion, long identificacion, String nombre, String apellido, String password) {
        super(identificacion, nombre, apellido, password);
        this.profesion = profesion;
    }

    //==============================
    //Metodos Get
    public String getProfesion() {
        return profesion;
    }
    
    
    //==============================
    //Metodos Set
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    //==============================



    
    
}
