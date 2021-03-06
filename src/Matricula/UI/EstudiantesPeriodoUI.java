
package Matricula.UI;

import Matricula.logic.Estudiante;
import Matricula.logic.Periodo;
import Matricula.logic.Universidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EstudiantesPeriodoUI extends javax.swing.JFrame {

    private Universidad u;
    private ProgramarCurso proCurso;
    private List<Estudiante> estudiantes = new ArrayList<>();
            
    public EstudiantesPeriodoUI(Universidad u, ProgramarCurso pro) {
        initComponents();
        this.u = u;
        this.proCurso = pro;
        ComboPeriodos.removeAllItems();
        for(Periodo periodo: u.getPeriodos()){
            ComboPeriodos.addItem(periodo);
        }
        TableStudents.setModel(new AbstractTableModel() {

            String[] names= {"Nombres", "Apellidos", "Codigo", "programa"};
            
            @Override
            public String getColumnName(int column) {
                return names[column]; //To change body of generated methods, choose Tools | Templates.
            }

           
            @Override
            public int getRowCount() {
                if(estudiantes.isEmpty()) return 0;
                return estudiantes.size();
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Estudiante estu = estudiantes.get(rowIndex);
                switch(columnIndex){
                    case 0: return estu.getNombre();
                    case 1: return estu.getApellido();
                    case 2: return estu.getCodigo();
                    case 3: return estu.getPrograma().toString();
                }
                return "";
            }
        });
        ComboPeriodos.addActionListener(new ListenerPeriodos());
        ButtonConsultar.addActionListener(new ListenerPeriodos());
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
        ComboPeriodos = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableStudents = new javax.swing.JTable();
        ButtonConsultar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estudiantes por Periodo Academico");

        ComboPeriodos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setText("Seleccionar Periodo Academico:");

        TableStudents.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableStudents);

        ButtonConsultar.setText("Consultar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ComboPeriodos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonConsultar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JComboBox ComboPeriodos;
    private javax.swing.JTable TableStudents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
public class ListenerPeriodos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            estudiantes = u.FiltrarEstudiantesPeriodo((Periodo) ComboPeriodos.getSelectedItem());
            TableStudents.updateUI();
        }
    
}





}
