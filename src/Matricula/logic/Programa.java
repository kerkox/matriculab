package Matricula.logic;

import Matricula.logic.enumclass.Jornada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Programa implements Serializable {
   
    private String codigo;
    private String nombre;
    private Jornada jornada;
    private List<Semestre> semestres = new ArrayList<>();

    public Programa() {
    }

    public Programa(String codigo, String nombre, Jornada jornada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.jornada = jornada;
    }

    //==============================
    //Metodos GET

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    //==============================
    //Metodos Set
    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    //==============================
    //==============================
    //Metodo Buscar

    public Asignatura buscar(String codigo, byte semestreNumero) throws Exception {
        for (Semestre sem : this.semestres) {
            if (sem.getNumero() == semestreNumero) {
                return sem.buscar(codigo);
            }
        }

        throw new Exception("Semestre Invalido");
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
        final Programa other = (Programa) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + " - "+ nombre;
    }

    
    
    
}
