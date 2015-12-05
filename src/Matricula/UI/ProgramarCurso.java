/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.UI;

import Matricula.logic.Asignatura;
import Matricula.logic.Cupo;
import Matricula.logic.Curso;
import Matricula.logic.Deuda;
import Matricula.logic.Docente;
import Matricula.logic.Estudiante;
import Matricula.logic.Exceptions.DateBeforeException;
import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.Periodo;
import Matricula.logic.Programa;
import Matricula.logic.Universidad;
import Matricula.logic.enumclass.Estado;
import Matricula.logic.enumclass.Mes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author atenea
 */
public class ProgramarCurso extends javax.swing.JFrame {

    /**
     * Creates new form ProgramarCurso
     */
    private Docente docenteLogueado;
    private Universidad u;
    private Docente docente;
    private Curso curso;
    private Asignatura asignatura;
    private ProgramarCurso proCurso = this;
    private Principal main;
    private Cupo cupo;
    private HorariosCurso horariosUI = null;
    private boolean save = false;
    private Estudiante estudiante;
    

    public ProgramarCurso(Docente docente, Universidad u, Principal main) {
        this.docenteLogueado = docente;
        this.main = main;
        this.u = u;
        
        
        initComponents();
        //***************************************   
        ListenerAddTime AddTime = new ListenerAddTime();
        ButtonSetTime.addActionListener(AddTime);
        //***************************************   
        ListenerAddCupo AddCupo = new ListenerAddCupo();
        ButtonAddCupo.addActionListener(AddCupo);
        //***************************************   
        SeleccionarDocente sd = new SeleccionarDocente();
        TeacherSelected.addActionListener(sd);
        //***************************************
        ListenerFinished lf = new ListenerFinished();
        ButtonFinished.addActionListener(lf);
        //***************************************   
        SeleccionarAsignatura sa = new SeleccionarAsignatura();
        SubjectSearch.addActionListener(sa);
        //***************************************   
        BuscarDocente BuscarDoc = new BuscarDocente();
        TeacherID.addActionListener(BuscarDoc);
        ButtonSearchTeacher.addActionListener(BuscarDoc);
        //***************************************   
        SearchSubject sc = new SearchSubject();
        SubjectCode.addActionListener(sc);
        //***************************************   
        ListenerSpinnerValue lsv = new ListenerSpinnerValue();
        SubjectGroup.addChangeListener(lsv);
        SubjectGroup.setValue(50);//valor por defecto
        CuposNumber.addChangeListener(lsv);
        //***************************************   
        Guardar guardar = new Guardar();
        ButtonSave.addActionListener(guardar);
        //***************************************   
        ListenerNewCourse lnc = new ListenerNewCourse();
        NewCourse.addActionListener(lnc);
        //***************************************   
        ButtonRefresh.addActionListener(new ListenerActualizar());
        //***************************************   
        TabCancelCourse.addAncestorListener(new ListenerRefresco());
        //***************************************   
        ButtonCancelCourse.addActionListener(new ListenerCancelarCurso());
        //***************************************   
        ListenerSpinnerYear lsy = new ListenerSpinnerYear();
        SpinnerYear.addChangeListener(lsy);
        SpinnerYear.setValue(2015); //valor por defecto
        //***************************************   
        ButtonNewPeriodo.addActionListener(new ListenerNewPeriodo());
        //***************************************   
        StudentButtonSearch.addActionListener(new ListenerSearchStudent());
        //***************************************   
        StudentDeudaButtonAdd.addActionListener(new ListenerAsignarDeuda());
        //***************************************   
        ListenerCancelarDeuda lcd = new ListenerCancelarDeuda();
        StudentButtonCancelDeuda.addActionListener(lcd);
        StudentFieldCode.addActionListener(lcd);
        //***************************************   

        TableCupos.setModel(new AbstractTableModel() {

            String[] names = {"Programa", "Cupos"};

            @Override
            public int getRowCount() {
                if (curso == null) {
                    return 0;
                }
                return curso.getCupos().size();
            }

            @Override
            public String getColumnName(int columnIndex) {
                return names[columnIndex];
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Cupo cupo = curso.getCupos().get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return cupo.getPrograma().toString();
                    case 1:
                        return cupo.getCantidad();
                }
                return "";
            }
        });

        for (Programa pro : u.getProgramas()) {
            CupoList.addItem(pro);
        }
        //*************************************
        //####################################
        //Tabla deudas
        this.StudentTableDeudas.setModel(new AbstractTableModel() {

            String[] names = {"Dependencia", "Observaciones"};

            @Override
            public String getColumnName(int column) {
                return names[column];
            }

            @Override
            public int getRowCount() {
                if (estudiante == null) {
                    return 0;
                }
                return estudiante.getDeudas().size();

            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Deuda deuda = estudiante.getDeudas().get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return deuda.getDependencia();
                    case 1:
                        return deuda.getObservacion();

                }
                return "";
            }
        });

        //##########################################
        //Tab Cancelar Cursos
        this.TableCursosProgramados.setModel(new AbstractTableModel() {

            String[] names = {"Codigo", "Nombre Asignatura", "Grupo", "Docente", "Creditos", "Estado"};

            @Override
            public String getColumnName(int ColumnIndex) {
                return names[ColumnIndex];
            }

            @Override
            public int getRowCount() {
                List<Curso> courses = u.getPeriodoActual().getCursos();
                if ((courses == null) || (courses.isEmpty())) {
                    return 0;
                }
                return u.getPeriodoActual().getCursos().size();
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Curso curso = u.getPeriodoActual().getCursos().get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return curso.getAsignatura().getCodigo();
                    case 1:
                        return curso.getAsignatura().getNombre();
                    case 2:
                        return curso.getGrupo();
                    case 3:
                        return curso.getDocente().getFullName();
                    case 4:
                        return curso.getAsignatura().getCreditos();
                    case 5:
                        return curso.getEstado();

                }
                return "";
            }
        });

        //***********************************
        ComboSelecionPeriodo.removeAllItems();

        ComboSelecionPeriodo.addItem("Febrero - Junio");
        ComboSelecionPeriodo.addItem("Agosto - Septiembre");
        //************************************

        //##########################################
    }

    public void refreshPeriodo() {
        FieldPeriodoActual.setText(u.getPeriodoActual().toString());
    }

    public void ActivateProgramCourse(boolean yn) {
        ButtonSetTime.setEnabled(yn);
        CupoList.setEnabled(yn);
        CuposNumber.setEnabled(yn);
        ButtonAddCupo.setEnabled(yn);
        TableCupos.updateUI();

    }

    public void LoadSubject(Asignatura subject) {
        this.curso = new Curso();
        this.asignatura = subject;
        SubjectCode.setText(asignatura.getCodigo());
        SubjectCredits.setText(asignatura.getCreditos() + "");
        SubjectIntensity.setText(asignatura.getIntensidad() + "");
        SubjectName.setText(asignatura.getNombre());
        curso.setAsignatura(asignatura);
        if (docente != null) {
            ActivateProgramCourse(true);
        }

    }

    public void LoadTeacher(Docente profesor) {
        if (curso == null) {
            curso = new Curso();
        }
        this.docente = profesor;
        TeacherID.setText(profesor.getIdentificacion() + "");
        TeacherName.setText(profesor.getFullName());
        TeacherEducation.setText(profesor.getProfesion());
        curso.setDocente(docente);
        if (asignatura != null) {
            ActivateProgramCourse(true);
        }

    }

    /**
     * Reinicio de toda la interfaz y las variables
     */
    public void clear() {
        this.docente = null;
        this.asignatura = null;
        this.curso = null;
        this.cupo = null;
        this.save = false;
        ActivateProgramCourse(save);
        NewCourse.setEnabled(save);

        this.TeacherEducation.setText("");
        this.TeacherID.setText("");
        this.TeacherName.setText("");

        this.SubjectCode.setText("");
        this.SubjectCredits.setText("");
        this.SubjectIntensity.setText("");
        this.SubjectName.setText("");

        this.TableCupos.clearSelection();
        this.TableCupos.updateUI();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabCancelCourse = new javax.swing.JTabbedPane();
        PanelRegistrar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SubjectSearch = new javax.swing.JButton();
        SubjectName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SubjectCredits = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SubjectIntensity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SubjectCode = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        ButtonSetTime = new javax.swing.JButton();
        SubjectGroup = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TeacherName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TeacherEducation = new javax.swing.JTextField();
        TeacherID = new javax.swing.JFormattedTextField();
        ButtonSearchTeacher = new javax.swing.JButton();
        TeacherSelected = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CupoList = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        ButtonAddCupo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCupos = new javax.swing.JTable();
        CuposNumber = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        CuposTotal = new javax.swing.JTextField();
        ButtonSave = new javax.swing.JButton();
        NewCourse = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCursosProgramados = new javax.swing.JTable();
        ButtonCancelCourse = new javax.swing.JButton();
        ButtonRefresh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        ButtonNewPeriodo = new javax.swing.JButton();
        ComboSelecionPeriodo = new javax.swing.JComboBox();
        SpinnerYear = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        FieldPeriodoActual = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        StudentFieldCode = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        StudentFieldName = new javax.swing.JTextField();
        StudentButtonSearch = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        StudentFieldPlan = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        StudentTableDeudas = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        StudentFieldPeriodo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        StudentDeudaDependencia = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        StudentDeudaButtonAdd = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        StudentDeudaObservacion = new javax.swing.JTextField();
        StudentButtonCancelDeuda = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        ConsultProgramacion = new javax.swing.JButton();
        ConsultCupos = new javax.swing.JButton();
        ConsultStudentPerPeriodo = new javax.swing.JButton();
        ButtonFinished = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Asignatura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel1.setText("Código:");

        SubjectSearch.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        SubjectSearch.setText("...");

        SubjectName.setEditable(false);

        jLabel2.setText("Nombre:");

        SubjectCredits.setEditable(false);

        jLabel3.setText("Creditos:");

        SubjectIntensity.setEditable(false);

        jLabel4.setText("intensidad Horaria:");

        SubjectCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SubjectIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 57, Short.MAX_VALUE))
                            .addComponent(SubjectName))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SubjectSearch)
                    .addComponent(jLabel3)
                    .addComponent(SubjectCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SubjectIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Grupo:");

        ButtonSetTime.setText("Asignar Horarios");
        ButtonSetTime.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Docente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel6.setText("Identificación:");

        jLabel7.setText("Nombre:");

        TeacherName.setEditable(false);

        jLabel8.setText("Formación:");

        TeacherEducation.setEditable(false);

        TeacherID.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        ButtonSearchTeacher.setText("Buscar");

        TeacherSelected.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        TeacherSelected.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TeacherName)
                    .addComponent(TeacherEducation)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TeacherID, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TeacherSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonSearchTeacher)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TeacherID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSearchTeacher)
                    .addComponent(TeacherSelected))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TeacherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TeacherEducation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Cupos por Programa Académico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel9.setText("Programa Académico");

        CupoList.setEnabled(false);

        jLabel10.setText("Cupos:");

        ButtonAddCupo.setText("Agregar");
        ButtonAddCupo.setEnabled(false);

        TableCupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableCupos);

        CuposNumber.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CupoList, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(CuposNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonAddCupo)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CupoList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAddCupo)
                    .addComponent(CuposNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setText("Total de Cupos: ");

        CuposTotal.setEditable(false);

        ButtonSave.setText("Guardar");

        NewCourse.setText("Programar Nuevo Curso");
        NewCourse.setEnabled(false);

        javax.swing.GroupLayout PanelRegistrarLayout = new javax.swing.GroupLayout(PanelRegistrar);
        PanelRegistrar.setLayout(PanelRegistrarLayout);
        PanelRegistrarLayout.setHorizontalGroup(
            PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelRegistrarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelRegistrarLayout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRegistrarLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(ButtonSetTime))
                                .addGroup(PanelRegistrarLayout.createSequentialGroup()
                                    .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(SubjectGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(106, 106, 106))))
                        .addGroup(PanelRegistrarLayout.createSequentialGroup()
                            .addComponent(ButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(NewCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRegistrarLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CuposTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        PanelRegistrarLayout.setVerticalGroup(
            PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
            .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelRegistrarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelRegistrarLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(SubjectGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(71, 71, 71)
                            .addComponent(ButtonSetTime)))
                    .addGap(18, 18, 18)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(CuposTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addGroup(PanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NewCourse)
                        .addComponent(ButtonSave))
                    .addContainerGap()))
        );

        TabCancelCourse.addTab("Programar Curso", PanelRegistrar);

        TableCursosProgramados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TableCursosProgramados);

        ButtonCancelCourse.setText("Cancelar Curso");

        ButtonRefresh.setText("Actualizar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonCancelCourse)
                .addGap(18, 18, 18)
                .addComponent(ButtonRefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCancelCourse)
                    .addComponent(ButtonRefresh))
                .addContainerGap(329, Short.MAX_VALUE))
        );

        TabCancelCourse.addTab("Cancelar Curso", jPanel4);

        ButtonNewPeriodo.setText("Crear nuevo Periodo");

        ComboSelecionPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Periodo");

        jLabel13.setText("Año:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Periodo Actual:");

        FieldPeriodoActual.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonNewPeriodo)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSelecionPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 280, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(17, 17, 17)
                        .addComponent(FieldPeriodoActual)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(ComboSelecionPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(SpinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ButtonNewPeriodo)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(FieldPeriodoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(597, Short.MAX_VALUE))
        );

        TabCancelCourse.addTab("Crear Nuevo Periodo", jPanel5);

        jLabel15.setText("Codigo estudiante:");

        StudentFieldCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel16.setText("Nombre:");

        StudentFieldName.setEditable(false);

        StudentButtonSearch.setText("Buscar");

        jLabel17.setText("Programa:");

        StudentFieldPlan.setEditable(false);

        StudentTableDeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(StudentTableDeudas);

        jLabel18.setText("Deudas:");

        jLabel19.setText("Periodo Matriculado:");

        StudentFieldPeriodo.setEditable(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignacion Deuda"));

        jLabel20.setText("Dependencia:");

        StudentDeudaButtonAdd.setText("Asignar Deuda");
        StudentDeudaButtonAdd.setEnabled(false);

        jLabel21.setText("Observacion:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(StudentDeudaDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StudentDeudaButtonAdd))
                    .addComponent(StudentDeudaObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentDeudaDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(StudentDeudaButtonAdd))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(StudentDeudaObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        StudentButtonCancelDeuda.setText("Eliminar Deuda");
        StudentButtonCancelDeuda.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel19))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(StudentFieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                                        .addComponent(StudentButtonSearch))
                                    .addComponent(StudentFieldName)
                                    .addComponent(StudentFieldPlan)
                                    .addComponent(StudentFieldPeriodo)))
                            .addComponent(jLabel18))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StudentButtonCancelDeuda)
                .addGap(66, 66, 66))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(StudentFieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StudentButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(StudentFieldPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(StudentFieldPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StudentButtonCancelDeuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TabCancelCourse.addTab("Deudas Estudiante", jPanel6);

        ConsultProgramacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/calendar_icon.png"))); // NOI18N
        ConsultProgramacion.setText("Programacion de Asignatura");

        ConsultCupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Accept-Male-User.png"))); // NOI18N
        ConsultCupos.setText("Cupos de Asignatura");

        ConsultStudentPerPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icono-de-estudiante-23788.png"))); // NOI18N
        ConsultStudentPerPeriodo.setText("Estudiantes por Periodo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ConsultStudentPerPeriodo)
                    .addComponent(ConsultProgramacion)
                    .addComponent(ConsultCupos))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ConsultProgramacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultCupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultStudentPerPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        TabCancelCourse.addTab("Consultas", jPanel8);

        ButtonFinished.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonFinished.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/boton-regresar50x50.png"))); // NOI18N
        ButtonFinished.setText("Finalizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonFinished))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(TabCancelCourse)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TabCancelCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new ListenerFinished().actionPerformed(null);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddCupo;
    private javax.swing.JButton ButtonCancelCourse;
    private javax.swing.JButton ButtonFinished;
    private javax.swing.JButton ButtonNewPeriodo;
    private javax.swing.JButton ButtonRefresh;
    private javax.swing.JButton ButtonSave;
    private javax.swing.JButton ButtonSearchTeacher;
    private javax.swing.JButton ButtonSetTime;
    private javax.swing.JComboBox ComboSelecionPeriodo;
    private javax.swing.JButton ConsultCupos;
    private javax.swing.JButton ConsultProgramacion;
    private javax.swing.JButton ConsultStudentPerPeriodo;
    private javax.swing.JComboBox CupoList;
    private javax.swing.JSpinner CuposNumber;
    private javax.swing.JTextField CuposTotal;
    private javax.swing.JTextField FieldPeriodoActual;
    private javax.swing.JButton NewCourse;
    private javax.swing.JPanel PanelRegistrar;
    private javax.swing.JSpinner SpinnerYear;
    private javax.swing.JButton StudentButtonCancelDeuda;
    private javax.swing.JButton StudentButtonSearch;
    private javax.swing.JButton StudentDeudaButtonAdd;
    private javax.swing.JTextField StudentDeudaDependencia;
    private javax.swing.JTextField StudentDeudaObservacion;
    private javax.swing.JFormattedTextField StudentFieldCode;
    private javax.swing.JTextField StudentFieldName;
    private javax.swing.JTextField StudentFieldPeriodo;
    private javax.swing.JTextField StudentFieldPlan;
    private javax.swing.JTable StudentTableDeudas;
    private javax.swing.JFormattedTextField SubjectCode;
    private javax.swing.JTextField SubjectCredits;
    private javax.swing.JSpinner SubjectGroup;
    private javax.swing.JTextField SubjectIntensity;
    private javax.swing.JTextField SubjectName;
    private javax.swing.JButton SubjectSearch;
    private javax.swing.JTabbedPane TabCancelCourse;
    private javax.swing.JTable TableCupos;
    private javax.swing.JTable TableCursosProgramados;
    private javax.swing.JTextField TeacherEducation;
    private javax.swing.JFormattedTextField TeacherID;
    private javax.swing.JTextField TeacherName;
    private javax.swing.JButton TeacherSelected;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
public class BuscarDocente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                docente = u.buscarDocente(Long.parseLong(TeacherID.getText()));
                TeacherEducation.setText(docente.getProfesion());
                TeacherName.setText(docente.getFullName());
                if (curso != null) {
                    ActivateProgramCourse(true);
                }
            } catch (ObjectNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class SearchSubject implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                asignatura = u.BuscarAsignatura(SubjectCode.getText().trim());
                SubjectCredits.setText(asignatura.getCreditos() + "");
                SubjectIntensity.setText(asignatura.getIntensidad() + "");
                SubjectName.setText(asignatura.getNombre());
                if (docente != null) {
                    ActivateProgramCourse(true);
                }
            } catch (ObjectNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NumberFormatException ex) {
                if (SubjectCode.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo Codigo no puede estar vacio");
                }
            }
        }

    }

    public class SeleccionarAsignatura implements ActionListener {

        CursosProgramados cursoPro = null;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cursoPro == null) {
                cursoPro = new CursosProgramados(u, proCurso);
            }
            cursoPro.setVisible(true);
        }

    }

    public class ListenerFinished implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (save) {
                this.exit();
            } else {
                int op = JOptionPane.showConfirmDialog(null, "desea Salir sin Guardar", "Guardar?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (op == 0) {
                    this.exit();
                }
            }

        }

        public void exit() {
            docenteLogueado = null;
            main.setVisible(true);
            setVisible(false);
        }

    }

    public class SeleccionarDocente implements ActionListener {

        DocentesAvaible DocAvaible = null;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (DocAvaible == null) {
                DocAvaible = new DocentesAvaible(u, proCurso);
            }
            DocAvaible.setVisible(true);
        }

    }

    public class ListenerAddCupo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Programa pro = (Programa) CupoList.getSelectedItem();
                cupo = new Cupo((int) CuposNumber.getValue(), pro);
                curso.add(cupo);
                TableCupos.updateUI();
                cupo = null;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }

    }

    public class ListenerAddTime implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (horariosUI == null) {
                horariosUI = new HorariosCurso(curso);
            }
            horariosUI.setVisible(true);
        }

    }

    public class Guardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                curso.setGrupo((byte) (int) SubjectGroup.getValue());
                curso.setEstado(Estado.ACTIVO);
                u.registrar(curso);
                save = true;
                NewCourse.setEnabled(save);
                ButtonSave.setEnabled(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }

    }

    public class ListenerNewCourse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            clear();
            ButtonSave.setEnabled(true);
        }

    }

    public class ListenerSpinnerValue implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            if ((int) CuposNumber.getValue() < 0) {
                CuposNumber.setValue(0);
            }
            if ((int) SubjectGroup.getValue() < 0) {
                SubjectGroup.setValue(0);
            }
        }

    }

    public class ListenerSpinnerYear implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            if ((int) SpinnerYear.getValue() < 2000) {
                SpinnerYear.setValue(2000);
            }
            if ((int) SpinnerYear.getValue() > 3000) {
                SpinnerYear.setValue(3000);
            }
        }

    }

    public class ListenerCancelarCurso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (TableCursosProgramados.getSelectedRow() == -1) {
                    throw new Exception("No se ha seleccinado un curso para cancelar");
                }
                Curso cur = u.getPeriodoActual().getCursos().get(TableCursosProgramados.getSelectedRow());
                int op = JOptionPane.showConfirmDialog(null, "Desea cancelar el curso : " + cur + " ", "Desea cancelar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == 0) {
                    if (u.estudiantesMatriculados(cur)) {
                        throw new Exception("Error no Se puede cancelar porque hay estudiantes Matriculados ");
                    }
                    u.CancelarCursoPeriodo(TableCursosProgramados.getSelectedRow());
//                u.getPeridoActual().getCursos().get(TableCursosProgramados.getSelectedRow());
                }
                TableCursosProgramados.updateUI();
                TableCursosProgramados.clearSelection();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerActualizar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TableCursosProgramados.updateUI();
        }

    }

    public class ListenerRefresco implements AncestorListener {

        @Override
        public void ancestorAdded(AncestorEvent event) {

        }

        @Override
        public void ancestorRemoved(AncestorEvent event) {

        }

        @Override
        public void ancestorMoved(AncestorEvent event) {
            TableCursosProgramados.updateUI();
            refreshPeriodo();
        }

    }

    public class ListenerNewPeriodo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Periodo periodo = new Periodo();
                System.out.println("Seleccion Combo: " + ComboSelecionPeriodo.getSelectedIndex());
                switch (ComboSelecionPeriodo.getSelectedIndex()) {
                    case 0:
                        periodo.setInicia(Mes.Febrero);
                        periodo.setFin(Mes.Junio);
                        break;
                    case 1:
                        periodo.setInicia(Mes.Agosto);
                        periodo.setFin(Mes.Diciembre);
                        break;
                }

                periodo.setYear((int) SpinnerYear.getValue());
                u.CrearPeriodo(periodo);
                u.ActulizarPeriodoEstudiantes();
                TabCancelCourse.updateUI();
                TableCupos.updateUI();
                TableCursosProgramados.updateUI();
                refreshPeriodo();
            } catch (DateBeforeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerSearchStudent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                estudiante = u.buscarEstudiante(StudentFieldCode.getText().trim());
                StudentFieldName.setText(estudiante.getFullName());
                StudentFieldPlan.setText(estudiante.getPrograma().toString());
                
                StudentDeudaButtonAdd.setEnabled(true);
                if(estudiante.getTabuladoActual()==null){
                 StudentFieldPeriodo.setText("No se ha matriculado todavia");   
                }else{
                    StudentFieldPeriodo.setText(estudiante.getTabuladoActual().getPeriodo().toString());
                }
                
                StudentTableDeudas.updateUI();
            } catch (ObjectNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerAsignarDeuda implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (estudiante == null) {
                    throw new Exception("Estudiante no asignado");
                }
                if (StudentDeudaDependencia.getText().trim().equals("")) {
                    throw new Exception("Dependencia Necesaria");
                }

                Deuda deubt = new Deuda(u.getPeriodoActual());
                deubt.setDependencia(StudentDeudaDependencia.getText().trim());
                deubt.setObservacion(StudentDeudaObservacion.getText().trim());

                estudiante.add(deubt);
                StudentTableDeudas.updateUI();
                StudentButtonCancelDeuda.setEnabled(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerCancelarDeuda implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (StudentTableDeudas.getSelectedRow() == -1) {
                    throw new Exception("No se ha selecionado ninguna deuda");
                }
                estudiante.EliminarDeuda(StudentTableDeudas.getSelectedRow());
                StudentTableDeudas.updateUI();
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }
    
    public class ListenerConsultaProgramacion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new ProgramacionAsignatura(u,proCurso).setVisible(true);
            setVisible(false);
        }
        
    }

}
