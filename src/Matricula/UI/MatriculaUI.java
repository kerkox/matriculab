/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.UI;

import Matricula.logic.Curso;
import Matricula.logic.Estudiante;
import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.Matricula;
import Matricula.logic.Periodo;
import Matricula.logic.Tabulado;
import Matricula.logic.Universidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author atenea
 */
public class MatriculaUI extends javax.swing.JFrame {

    /**
     * Creates new form Matricula
     */
    private Estudiante estu;
    private Universidad u;
    private Principal main;
    private MatriculaUI matri = this;
    private Curso cursoMatricular;
    private byte creditos;

    public MatriculaUI(Universidad u, Estudiante estu, Principal main) {
        this.main = main;
        this.u = u;
        this.estu = estu;
        initComponents();
        FieldPeriodo.setText(u.getPeriodoActual().toString());
        FieldCodeStudent.setText(estu.getCodigo());
        FieldNameStudent.setText(estu.getFullName());
        if (estu.getTabuladoActual() == null) {
            creditos = 0;
        } else {
            estu.getTabuladoActual().ActualizarCreditos();
            creditos = estu.getTabuladoActual().getCreditos();
        }

        //**************************
        //#########
        ListarPeriodos();
        ButtonConsultar.addActionListener(new ListenerConsultarTabuladoPerido());

        //**************************
        FieldTotalCreditos.setText(creditos + "");

        ButtonSearchCourse.addActionListener(new ListenerCursosProgramados());
        ButtonFinished.addActionListener(new ListenerFinished());
        ButtonEnroll.addActionListener(new ListenerMatricular());
        ButtonCancel.addActionListener(new ListenerCancelar());

        FieldNumberGroup.addActionListener(new ListenerSearch()); // Busqueda de un Curso por codigo de asignatura
        tableEnrolls.setModel(new AbstractTableModel() {

            String[] names = {"Codigo", "Asignatura", "Grupo", "Estado", "Creditos"};

            @Override
            public int getRowCount() {
                if (estu.getTabuladoActual() == null) {
                    return 0;
                }
                return estu.getTabuladoActual().getMatriculas().size();
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public String getColumnName(int column) {
                return names[column];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Matricula matricula = estu.getTabuladoActual().getMatriculas().get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return matricula.getCurso().getAsignatura().getCodigo();
                    case 1:
                        return matricula.getCurso().getAsignatura().getNombre();
                    case 2:
                        return matricula.getCurso().getGrupo();
                    case 3:
                        return matricula.getEstado();
                    case 4:
                        return matricula.getCurso().getAsignatura().getCreditos();
                }

                return "";
            }
        });

        TableTabulados.setModel(new AbstractTableModel() {

            String[] names = {"Codigo", "Asignatura", "Grupo", "Estado", "Creditos"};

            @Override
            public int getRowCount() {
                if (estu.getTabulados().isEmpty()) {
                    return 0;
                }
                return estu.getTabulado((Periodo) ComboTabulados.getSelectedItem()).getMatriculas().size();
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public String getColumnName(int column) {
                return names[column];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Matricula matricula = estu.getTabulado((Periodo) ComboTabulados.getSelectedItem()).getMatriculas().get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return matricula.getCurso().getAsignatura().getCodigo();
                    case 1:
                        return matricula.getCurso().getAsignatura().getNombre();
                    case 2:
                        return matricula.getCurso().getGrupo();
                    case 3:
                        return matricula.getEstado();
                    case 4:
                        return matricula.getCurso().getAsignatura().getCreditos();
                }

                return "";
            }
        });
        ActualizarTablas();
    }

    public void ActualizarTablas() {
        tableEnrolls.updateUI();
    }

    public void ListarPeriodos() {
        //***************************
        //listando periodos del estudiante
        ComboTabulados.removeAllItems();
        if (estu.getTabulados().isEmpty()) {
            ComboTabulados.addItem("No hay tabulados disponibles");
        } else {
            for (Tabulado tabu : estu.getTabulados()) {
                ComboTabulados.addItem(tabu.getPeriodo());
            }
        }

        //***************************
    }

    public void LoadMatricula(Curso curso) {
        this.cursoMatricular = curso;
        FieldNameSubject.setText(cursoMatricular.getAsignatura().getNombre());
        FieldNumberGroup.setText(cursoMatricular.getGrupo() + "");
        FieldCodeSubject.setText(cursoMatricular.getAsignatura().getCodigo());

    }

    public void clear() {
        cursoMatricular = null;
        FieldNameSubject.setText("");
        FieldNumberGroup.setText("");
        FieldCodeSubject.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonFinished = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        FieldPeriodo = new javax.swing.JTextField();
        FieldCodeStudent = new javax.swing.JTextField();
        FieldNameStudent = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        FieldCodeSubject = new javax.swing.JTextField();
        ButtonSearchCourse = new javax.swing.JButton();
        FieldNameSubject = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FieldNumberGroup = new javax.swing.JTextField();
        ButtonEnroll = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        FieldTotalCreditos = new javax.swing.JTextField();
        ButtonCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEnrolls = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableTabulados = new javax.swing.JTable();
        ComboTabulados = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        ButtonConsultar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(639, 411));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matricula Academica");

        ButtonFinished.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonFinished.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/boton-regresar50x50.png"))); // NOI18N
        ButtonFinished.setText("Finalizar");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Periodo:");

        FieldPeriodo.setEditable(false);

        FieldCodeStudent.setEditable(false);

        FieldNameStudent.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Estudiante:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Asignatura:");

        ButtonSearchCourse.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ButtonSearchCourse.setText("...");
        ButtonSearchCourse.setMaximumSize(new java.awt.Dimension(45, 20));
        ButtonSearchCourse.setMinimumSize(new java.awt.Dimension(45, 20));
        ButtonSearchCourse.setPreferredSize(new java.awt.Dimension(40, 22));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Grupo:");

        ButtonEnroll.setText("Matricular");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Total Creditos:");

        FieldTotalCreditos.setEditable(false);

        ButtonCancel.setText("Cancelar");

        tableEnrolls.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(tableEnrolls);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonFinished))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(FieldCodeStudent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FieldNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(FieldPeriodo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FieldCodeSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(FieldNumberGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ButtonEnroll)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(FieldTotalCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ButtonCancel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ButtonSearchCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FieldNameSubject)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ButtonFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(FieldPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(FieldCodeStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(FieldCodeSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSearchCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldNameSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(FieldNumberGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonEnroll)
                    .addComponent(ButtonCancel)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FieldTotalCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Matricula", jPanel1);

        TableTabulados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane3.setViewportView(TableTabulados);

        ComboTabulados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Seleccionar Periodo a consultar:");

        ButtonConsultar.setText("Consultar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(ComboTabulados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(ButtonConsultar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(9, 9, 9)
                .addComponent(ComboTabulados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(ButtonConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tabulados", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new ListenerFinished().actionPerformed(null);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JButton ButtonEnroll;
    private javax.swing.JButton ButtonFinished;
    private javax.swing.JButton ButtonSearchCourse;
    private javax.swing.JComboBox ComboTabulados;
    private javax.swing.JTextField FieldCodeStudent;
    private javax.swing.JTextField FieldCodeSubject;
    private javax.swing.JTextField FieldNameStudent;
    private javax.swing.JTextField FieldNameSubject;
    private javax.swing.JTextField FieldNumberGroup;
    private javax.swing.JTextField FieldPeriodo;
    private javax.swing.JTextField FieldTotalCreditos;
    private javax.swing.JTable TableTabulados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableEnrolls;
    // End of variables declaration//GEN-END:variables
//vamos a hacer una ventana con las materias disponibles para asi evitar el error al buscar
//en la ventana que se muestra los cursos disponibles para el programa del estudiante 

    public class ListenerCursosProgramados implements ActionListener {

        CursosEstudiante courses = null;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (courses == null) {
                courses = new CursosEstudiante(u.getPeriodoActual(), matri, estu.getPrograma());
            }
            courses.setVisible(true);
        }
    }

    public class ListenerFinished implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(new FileOutputStream("univalle.data"));
                    oos.writeObject(MatriculaUI.this.u);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                estu = null;
                main.setVisible(true);
                setVisible(false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

    public class ListenerSearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String codeSubject = FieldCodeSubject.getText().trim();
                String grupo = FieldNumberGroup.getText().trim();
                if (codeSubject.equals("")) {
                    throw new Exception("El campo del codigo de asignatura no puede ser vacio");
                }
                if (grupo.equals("")) {
                    throw new Exception("El campo de grupo no puede ser vacio");
                }
                byte group = Byte.parseByte(grupo);

                cursoMatricular = u.BuscarCurso(codeSubject, group);
                FieldNameSubject.setText(cursoMatricular.getAsignatura().getNombre());
            } catch (ObjectNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerMatricular implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (cursoMatricular == null) {
                    throw new Exception("No se ha seleccionado ningun Curso");
                }
//                estu.Matricular(cursoMatricular, u.getPeridoActual(), );
                u.MatricularCurso(estu, cursoMatricular);
                creditos = estu.getTabuladoActual().getCreditos();
                FieldTotalCreditos.setText(creditos + "");
                tableEnrolls.updateUI();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            clear();

        }

    }

    public class ListenerCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Aqui se cancela un Curso matriculado por un estudiante
            if (tableEnrolls.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun Curso para Cancelar");
            } else {
                try {
                    Curso curso = estu.getTabuladoActual().getMatriculas().get(tableEnrolls.getSelectedRow()).getCurso();

                    u.CancelarCurso(estu, curso);
                    creditos = estu.getTabuladoActual().getCreditos();
                    FieldTotalCreditos.setText(creditos + "");
                    tableEnrolls.updateUI();
                    tableEnrolls.clearSelection();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
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
            ListarPeriodos();
            TableTabulados.updateUI();

        }

    }

    public class ListenerConsultarTabuladoPerido implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ListarPeriodos();
            TableTabulados.updateUI();
        }

    }

}
