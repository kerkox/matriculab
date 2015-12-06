package Matricula.logic;

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
