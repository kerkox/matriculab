package Matricula.logic;

import Matricula.logic.Exceptions.DateBeforeException;
import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.enumclass.Estado;
import Matricula.logic.enumclass.Mes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author atenea
 */
public class Universidad {

    private String nit;
    private String nombre;
    private String direccion;
    private Periodo periodoActual;
    private List<Periodo> periodos = new ArrayList<>();
    private List<Programa> programas = new ArrayList<>();
    private List<Asignatura> asignaturas = new ArrayList<>();
    private List<Docente> docentes = new ArrayList<>();
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Universidad(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public void setPeriodoActual(Periodo actual) throws Exception {
        this.periodoActual = actual;

    }

    //==============================
    //Metodos Get
    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Periodo getPeriodoActual() {
        return periodoActual;
    }

    public List<Periodo> getPeriodos() {
        return this.periodos;
    }

    public List<Programa> getProgramas() {
        return this.programas;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    //==============================
    //Mostrar los cursos para una asignatura especifica 
    //En el periodo actual
    /**
     *
     * @param code
     * @return Lista de Cursos
     * @throws Exception Asignatura no Encontrada Recibe un codigo de asignatura
     * y busca los cursos con la asignatura en el periodo Actual
     */
    public List<Curso> programacionAsignatura(String code) throws Exception {
        return periodoActual.buscar(code);
    }

    //==============================
    /**
     *
     * @return Lista de todos cursos
     */
    public List<Curso> TodaLaProgramacion() {
        return periodoActual.getCursos();
    }

    public void regitrarCurso(Curso curso) throws Exception {
        periodoActual.add(curso);

    }

    /**
     *
     * @param incia Fecha de incio del periodo
     * @param finaliza Fecha de finalizacion del periodo
     * @param year año del periodo
     * @throws DateBeforeException Se lanza el error cuando se desea crear un
     * periodo y el año es anterior al ultimo periodo registrado
     */
    public void CrearPeriodo(Mes incia, Mes finaliza, int year) throws DateBeforeException, Exception {

        Periodo periodo = new Periodo(incia, finaliza, year);
        switch (PeriodoBefore(periodo, periodoActual)) {
            case 0:
                throw new DateBeforeException("ERROR: periodo ya creado (Actual)");
            case 1:
                throw new DateBeforeException("No se puede crear un periodo anterior: ");
            case 2:
                throw new DateBeforeException("No se puede crear un periodo de un año anterior: " + periodo.getYear());
        }

        periodoActual = periodo;

    }

    /**
     *
     * @param inicial Periodo inicial
     * @param Final Periodo Final
     * @return 1 si el periodo inicial esta antes del final 0 si es el mismo -1
     * en caso del inicial estar despues del final 2 en caso de ser el inicial
     * de un año anterior al final
     */
    public int PeriodoBefore(Periodo inicial, Periodo Final) {
        if (inicial.getYear() < Final.getYear()) {
            return 2;
        }
        if (inicial.getYear() == Final.getYear()) {
            if ((inicial.getInicia().equals(Mes.Febrero)) && (Final.getInicia().equals(Mes.Agosto))) {
                return 1;
            }
            if (((inicial.getInicia().equals(Mes.Febrero)) && (Final.getInicia().equals(Mes.Febrero)))
                    || (((inicial.getInicia().equals(Mes.Agosto)) && (Final.getInicia().equals(Mes.Agosto))))) {
                return 0;
            }
        }
        return -1;

    }

    public void CrearPeriodo(Periodo periodo) throws DateBeforeException, Exception {

        switch (PeriodoBefore(periodo, periodoActual)) {
            case 0:
                throw new DateBeforeException("ERROR: periodo ya creado (Actual)");
            case 1:
                throw new DateBeforeException("No se puede crear un periodo anterior: ");
            case 2:
                throw new DateBeforeException("No se puede crear un periodo de un año anterior: " + periodo.getYear());
        }

        periodoActual = periodo;

    }

    public void ActulizarPeriodoEstudiantes() throws Exception {
        for (Estudiante est : this.estudiantes) {
            est.ActualizarTabulado();
        }
    }

    public void registrar(Estudiante estudiante) {
        try {
            this.estudiantes.add(estudiante);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void registrar(Docente docente) {
        try {
            this.docentes.add(docente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void registrar(Asignatura asignatura) {
        try {
            this.asignaturas.add(asignatura);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void registrar(Programa programa) {
        try {
            this.programas.add(programa);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void registrarIncial(Curso curso) throws Exception {
        this.periodoActual.add(curso);
    }

    public void registrar(Curso curso) throws Exception {

        boolean create = true;
        if (curso == null) {
            throw new Exception("No se ha creado el Curso");
        }
        if (curso.getHorarios().isEmpty()) {
            throw new Exception("No se ha registrado Un horario al curso");
        }
        if (curso.getCupos().isEmpty()) {
            throw new Exception("No se ha registrado Un Cupo al curso");
        }
        if (curso.getDocente() == null) {
            throw new Exception("Docente no asignado");
        }
        if (curso.getAsignatura() == null) {
            throw new Exception("Asignatura no asignada");
        }
        List<Curso> cursos = this.periodoActual.getCursos();

        if (cursos.contains(curso)) {
            if (cursos.get(cursos.indexOf(curso)).getEstado() == Estado.CANCELADO) {
                this.periodoActual.getCursos().get(cursos.indexOf(curso)).setEstado(Estado.ACTIVO);
            } else {
                throw new Exception("ERROR CURSO YA REGISTRADO");
            }

        }

    }

    public Estudiante buscarEstudiante(String codigo) throws ObjectNotFoundException {
        Estudiante est = null;
        for (Estudiante estu : this.estudiantes) {
            if (estu.getCodigo().equals(codigo)) {
                est = estu;
            }
            break;
        }

        if (est == null) {
            throw new ObjectNotFoundException("El estudiante con codigo: " + codigo + " No fue encontrado");
        }
        return est;
    }

    /**
     *
     * @param estu: estudiante para validad deudas
     * @return true, si esta libre de deudas false, en el caso de que tenga
     * alguna deuda
     */
    public boolean ValidarDeudas(Estudiante estu) {
        return estu.getDeudas().isEmpty();
    }

    public Docente buscarDocente(long id) throws ObjectNotFoundException {

        for (Docente doc : this.docentes) {
            if (doc.getIdentificacion() == id) {
                return doc;
            }
        }

        throw new ObjectNotFoundException("Docente con identifiacion: " + id + " no encontrado");

    }

    public Asignatura BuscarAsignatura(String code) throws ObjectNotFoundException {
        for (Asignatura subject : this.asignaturas) {
            if (subject.getCodigo().equals(code)) {
                return subject;
            }

        }
        throw new ObjectNotFoundException("Asignatura con codigo: " + code + " no encontrada");
    }

    //////*********************************
    public void MatricularCurso(Estudiante estu, Curso curso) throws Exception {
        estu.Matricular(curso, getPeriodoActual(), cursoJpa, matricualJpa);
        estudianteJpa.edit(estu);
        //////*********************************
    }

    public void CancelarCurso(Estudiante estu, Curso curso) throws Exception {
        estu.Cancelar(curso, cursoJpa, matricualJpa);

//        cursoJpa.edit(curso);
        List<Matricula> matris = estu.getTabuladoActual().getMatriculas();
        matricualJpa.edit(matris.get(matris.indexOf(new Matricula(new Date(), curso))));

        tabuladoJpa.edit(estu.getTabuladoActual());
        estudianteJpa.edit(estu);

    }

    public void CancelarCursoPeriodo(Curso curso) throws Exception {
        getPeriodoActual().CancelarCurso(curso);
        periodoJpa.edit(getPeriodoActual());
    }

    public void CancelarCursoPeriodo(int index) throws Exception {
        getPeriodoActual().CancelarCurso(index, cursoJpa);
        periodoJpa.edit(getPeriodoActual());
    }

    public Curso BuscarCurso(String codeSubject, byte group) throws ObjectNotFoundException {
        Curso course = cursoJpa.findCurso(group, codeSubject);
        if (course == null) {
            throw new ObjectNotFoundException("No se encuentra el Curso: grupo: " + group + " asignatura codigo: " + codeSubject);
        }
        return course;
    }

    public boolean estudiantesMatriculados(Curso curso) {
        List<Estudiante> estudiantes = this.estudianteJpa.findEstudianteEntities();
        for (Estudiante estu : estudiantes) {
            if (estu.getTabuladoActual() == null) {
                continue;
            }
            for (Matricula matri : estu.getTabuladoActual().getMatriculas()) {
                if (matri.getCurso().equals(curso)) {
                    if (matri.getEstado() == Estado.ACTIVO) {
                        return true;
                    }

                }
            }

        }
        return false;

    }

}
