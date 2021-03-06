package Matricula.UI;

import Matricula.logic.Asignatura;
import Matricula.logic.Cupo;
import Matricula.logic.Curso;
import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.Universidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CuposUI extends javax.swing.JFrame {

    private Universidad u;
    private ProgramarCurso proCurso;
    private List<Curso> cursos = new ArrayList<>();
    private List<Cupo> cupos = new ArrayList<>();
    private Asignatura asignatura;
    private CuposUI cupoUi = this;

    public CuposUI(Universidad u, ProgramarCurso pro) {
        this.u = u;
        this.proCurso = pro;
        initComponents();
        SubjectSearch.addActionListener(new ListenerListaAsignaturas());
        ButtonConsultar.addActionListener(new ListenerBuscar());
        TableCupos.setModel(new AbstractTableModel() {

            String[] names = {"Programa", "Cupos", "Disponibles"};

            @Override
            public int getRowCount() {
                if (cupos.isEmpty()) {
                    return 0;
                }
                return cupos.size();
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
                Cupo cupo = cupos.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return cupo.getPrograma().toString();
                    case 1:
                        return cupo.getCantidad();
                    case 2:
                        return cupo.getDisponibles();
                }
                return "";
            }
        });

    }

    public void FiltrarCupos() {
        for (Curso curso : u.getPeriodoActual().getCursos()) {
            if (curso.getAsignatura().equals(asignatura)) {
                for (Cupo cupo : curso.getCupos()) {
                    cupos.add(cupo);
                }
            }

        }
    }

    public void LoadAsignatura(Asignatura asig) {
        asignatura = asig;

        SubjectCode.setText(asignatura.getCodigo());
        SubjectName.setText(asignatura.getNombre());
        SubjectCredits.setText(asignatura.getCodigo());
        SubjectIntensity.setText(asignatura.getIntensidad() + "");
        FiltrarCupos();
        TableCupos.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SubjectSearch = new javax.swing.JButton();
        SubjectName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SubjectCredits = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SubjectIntensity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SubjectCode = new javax.swing.JFormattedTextField();
        ButtonConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCupos = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cupos de asignatura");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Asignatura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel2.setText("Código:");

        SubjectSearch.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        SubjectSearch.setText("...");

        SubjectName.setEditable(false);

        jLabel3.setText("Nombre:");

        SubjectCredits.setEditable(false);

        jLabel4.setText("Creditos:");

        SubjectIntensity.setEditable(false);

        jLabel5.setText("intensidad Horaria:");

        SubjectCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        ButtonConsultar.setText("Consultar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SubjectIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(ButtonConsultar)
                                .addGap(0, 16, Short.MAX_VALUE))
                            .addComponent(SubjectName))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SubjectSearch)
                    .addComponent(jLabel4)
                    .addComponent(SubjectCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(SubjectIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TableCupos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableCupos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JFormattedTextField SubjectCode;
    private javax.swing.JTextField SubjectCredits;
    private javax.swing.JTextField SubjectIntensity;
    private javax.swing.JTextField SubjectName;
    private javax.swing.JButton SubjectSearch;
    private javax.swing.JTable TableCupos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
public class ListenerBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                asignatura = u.BuscarAsignatura(SubjectCode.getText().trim());
                LoadAsignatura(asignatura);

            } catch (ObjectNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }

    }

    public class ListenerListaAsignaturas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new AsignaturasUI(u, cupoUi).setVisible(true);
        }

    }

}
