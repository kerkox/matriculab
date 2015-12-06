package main;

import Matricula.UI.Principal;
import Matricula.logic.Periodo;
import Matricula.logic.*;
import Matricula.logic.enumclass.Jornada;
import Matricula.logic.enumclass.Mes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        try {
            Universidad u = null;
            ObjectInputStream ois = null;

            try {
                ois = new ObjectInputStream(new FileInputStream("univalle.data"));
                u = (Universidad) ois.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (u == null) {
                Periodo periodo = new Periodo(Mes.Agosto, Mes.Diciembre, 2015);
                u = new Universidad("800", "Univalle", "Carbonera");
                System.out.println("Va a crear el periodo actual");
                if (u.setPeriodoActual(periodo)) {
                    System.out.println("Periodo ya creado se usara el que ya esta");
                } else {

                    //Programas
                    Programa[] programs = {
                        new Programa("2711", "Tecnologia de Sistemas", Jornada.DIURNA),
                        new Programa("3843", "Ingenieria de Sistemas", Jornada.DIURNA),
                        new Programa("3841", "Contaduria Pública", Jornada.DIURNA)
                    };
                    if (u.getProgramas().isEmpty()) {
                        //Registro de Programas
                        for (Programa x : programs) {
                            u.registrar(x);
                        }
                    }

//*********************************
                    //Estudiantes            
                    Estudiante[] students = {
                        new Estudiante("123", 12345, "Pol", "Cortes", "1234", programs[0]),
                        new Estudiante("12345", 123456, "Paul", "Cortes", "1234", programs[0]),
                        new Estudiante("1234", 123456789, "Jeniffer", "Rosales", "1234", programs[2])};
                    if (u.getEstudiantes().isEmpty()) {

                        //Registro de Estudiantes
                        for (Estudiante x : students) {
                            u.registrar(x);
                        }
                    }
//*********************************            
                    //Docentes
                    Docente[] teachers = {
                        new Docente("Ingeniero de Sistemas", 100, "Antonio", "Velez", "1234"), // 0
                        new Docente("Matematico", 101, "Jaime", "Florez", "1234"), // 1 
                        new Docente("Ingeniero Electronico", 102, "Duvan", "Garcia", "1234"), // 2
                        new Docente("Ingeniera de Sistemas", 103, "Natalia", "Henao", "1234"), // 3
                        new Docente("Contador Público", 200, "Juan Pablo", "Hincapie", "1234"), // 4
                        new Docente("Contador Público", 201, "Robinson", "Holguin Peña", "1234"), // 5
                        new Docente("Contadora Pública", 202, "Martha Lucia", "Fuertes Diaz", "1234"), // 6
                        new Docente("Contador Público", 203, "Javier Alonso", "Murillo Muñoz", "1234"), // 7
                    };
                    if (u.getDocentes().isEmpty()) {
                        //Registro de Docentes
                        for (Docente x : teachers) {
                            u.registrar(x);
                        }
                    }
//*********************************

                    //Asignaturas
                    Asignatura[] subjects = {
                        //Asignaturas de Sistemas
                        new Asignatura("1000", "Interactivas", (byte) 4, (byte) 4), // 0
                        new Asignatura("1001", "Calculo 2", (byte) 4, (byte) 5), // 1
                        new Asignatura("1002", "Arquitectura 2", (byte) 3, (byte) 4), // 2
                        new Asignatura("1003", "Algebra Lineal", (byte) 4, (byte) 4), // 3
                        new Asignatura("1004", "Matematicas Discretas", (byte) 3, (byte) 4), // 4
                        //Asignaturas de Contaduria
                        //                new Asignatura(null, null, creditos, intensidad)                
                        new Asignatura("2000", "Etica, Moral y Fe Pública ", (byte) 3, (byte) 3), // 5
                        new Asignatura("2001", "Evaluación Financ de Proyectos", (byte) 2, (byte) 3), // 6
                        new Asignatura("2002", "Metodologia Investigacion Contable", (byte) 3, (byte) 3), // 7
                        new Asignatura("2003", "Revisoria Fiscal", (byte) 3, (byte) 3), // 8 
                        new Asignatura("2004", "Resolucion de problemas y toma de decisiones", (byte) 3, (byte) 3) // 9
                    };
                    if (u.getAsignaturas().isEmpty()) {
                        //Registro de Asignaturas
                        for (Asignatura x : subjects) {
                            u.registrar(x);
                        }
                    }
//*********************************

//*********************************
                    //Cupos
                    Cupo[] cupos = {
                        new Cupo(55, programs[0]),
                        new Cupo(50, programs[1]),
                        new Cupo(60, programs[2]),
                        new Cupo(55, programs[2])
                    };
//*********************************

                    //Cursos
                    Curso[] cursos = {
                        new Curso((byte) 50, cupos[0], teachers[0], subjects[0]),
                        new Curso((byte) 51, cupos[0], teachers[1], subjects[1]),
                        new Curso((byte) 50, cupos[0], teachers[2], subjects[2]),
                        new Curso((byte) 50, cupos[0], teachers[1], subjects[3]),
                        new Curso((byte) 52, cupos[0], teachers[3], subjects[4]),
                        new Curso((byte) 50, cupos[3], teachers[4], subjects[5]),
                        new Curso((byte) 52, cupos[3], teachers[6], subjects[6]),
                        new Curso((byte) 55, cupos[3], teachers[4], subjects[7]),
                        new Curso((byte) 57, cupos[3], teachers[5], subjects[8]),
                        new Curso((byte) 57, cupos[3], teachers[7], subjects[9])
                    };
//################################################################
                    if (u.getPeriodoActual().getCursos().isEmpty()) {

                        //Registro de Cursos
                        for (Curso x : cursos) {
                            u.registrarIncial(x);
                        }

                    }

                    if (u.getPeriodoActual() == null) {

                    }
                }

            }

            new Principal(u).setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
