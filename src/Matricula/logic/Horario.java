package Matricula.logic;

import Matricula.logic.enumclass.Dia;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Horario implements Serializable {

    private Date horaIncio;
    private Date horaFinalizacion;
    private Dia dia;

    public Horario() {
    }

    public Horario(Date horaIncio, Date horaFinalizacion, Dia dia) throws Exception {
        if (horaFinalizacion.getHours() < horaIncio.getHours()) {
            throw new Exception("Error la Hora de finalizacion no puede ser anterior a la Hora de inicio");
        }
        if (horaFinalizacion.getHours() == horaIncio.getHours()) {
            if ((horaIncio.getMinutes() >= 0) && (horaFinalizacion.getMinutes() <= 59)) {
                throw new Exception("Error Tiempo Minimo de la clase 1 hora");
            }
        }
        this.horaIncio = horaIncio;
        this.horaFinalizacion = horaFinalizacion;
        this.dia = dia;
    }

    //==============================
    //Metodos Get
    public Date getHoraIncio() {
        return horaIncio;
    }

    public Date getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public Dia getDia() {
        return dia;
    }

    //==============================
    //Metodos Set
    public void setHoraIncio(Date horaIncio) {
        this.horaIncio = horaIncio;
    }

    public void setHoraFinalizacion(Date horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.horaIncio, other.horaIncio)) {
            return false;
        }
        if (!Objects.equals(this.horaFinalizacion, other.horaFinalizacion)) {
            return false;
        }
        if (this.dia != other.dia) {
            return false;
        }

        return true;
    }

    

}
