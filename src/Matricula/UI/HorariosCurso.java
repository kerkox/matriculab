/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.UI;

import Matricula.logic.Curso;
import Matricula.logic.Horario;
import Matricula.logic.enumclass.Dia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paulker
 */
public class HorariosCurso extends javax.swing.JFrame {

    /**
     * Creates new form HorariosCurso
     */
    private Curso curso = null;

    public HorariosCurso(Curso curso) {
        this.curso = curso;
        initComponents();
        ComboDay.removeAllItems();
        for (int x = 0; x < 7; x++) {
            this.ComboDay.addItem(Dia.values()[x]);
        }

        //***************************
        //Listeners Botones
        this.ButtonAddTime.addActionListener(new ListenerAddTime());
        this.ButtonDeleteTime.addActionListener(new ListenerDelteTime());
        
        
        //***************************
        //Valores por defecto
        TimeHourStart.setValue(8);
        TimeMinutesStart.setValue(0);
        
        TimeHourFinished.setValue(10);
        TimeMinutesFinished.setValue(0);
        
        //****************************************************
        TimeHourStart.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                if ((int) TimeHourStart.getValue() < 7) {
                    JOptionPane.showMessageDialog(null, "No se abre antes de las 7");
                    TimeHourStart.setValue(7);
                }
                
                if ((int) TimeHourStart.getValue() > 20) {
                    JOptionPane.showMessageDialog(null, "No se permite clase despues de las 20:00");
                    TimeHourStart.setValue(20);
                }
            }
        });
        
        //****************************************************
        TimeMinutesStart.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                if ((int) TimeMinutesStart.getValue() < 0) {
                    TimeMinutesStart.setValue(59);
                }
                
                if ((int) TimeMinutesStart.getValue() > 59) {
                    TimeMinutesStart.setValue(0);
                }
            }
        });
        //****************************************************
        
        //****************************************************
        TimeHourFinished.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                if ((int) TimeHourFinished.getValue() < 8) {
                    JOptionPane.showMessageDialog(null, "El tiempo minimo es de una(1) Hora");
                    TimeHourFinished.setValue(8);
                }
                
                if ((int) TimeHourFinished.getValue() > 21) {
                    JOptionPane.showMessageDialog(null, "No se permite clase despues de las 21:00");
                    TimeHourFinished.setValue(20);
                }
            }
        });
        
        //****************************************************
        TimeMinutesFinished.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                if ((int) TimeMinutesFinished.getValue() < 0) {
                    TimeMinutesFinished.setValue(59);
                }
                
                if ((int) TimeMinutesFinished.getValue() > 59) {
                    TimeMinutesFinished.setValue(0);
                }
            }
        });
        //****************************************************
        //######################################
        //Tabla Horarios
        TableTime.setModel(new AbstractTableModel() {

           String[] names = {"Dia", "Hora Inicio", "Hora finalizacion"};
            
           @Override
            public String getColumnName(int Column){
                return names[Column];
               
           }
            
            @Override
            public int getRowCount() {
                if(curso==null) return 0;
                return curso.getHorarios().size();
            }

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public Object getValueAt(int Row, int Column) {
                Horario horario = curso.getHorarios().get(Row);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                switch(Column){
                    case 0: return horario.getDia().toString();
                    case 1: return sdf.format(horario.getHoraIncio());
                    case 2: return sdf.format(horario.getHoraFinalizacion());                        
                }
                return "";                
            }
        });
        
        
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
        jLabel2 = new javax.swing.JLabel();
        ComboDay = new javax.swing.JComboBox();
        ButtonAddTime = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TimeHourFinished = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        TimeMinutesFinished = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TimeHourStart = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        TimeMinutesStart = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTime = new javax.swing.JTable();
        ButtonDeleteTime = new javax.swing.JButton();

        setTitle("Asignacion de Horarios");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asignacion de Horarios");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Selecciona Dia:");

        ComboDay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ComboDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ButtonAddTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ButtonAddTime.setText("Agregar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora Finalizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Hora:");

        TimeHourFinished.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Minutos");

        TimeMinutesFinished.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimeHourFinished, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(TimeMinutesFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeHourFinished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeMinutesFinished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora Inicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Hora:");

        TimeHourStart.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Minutos");

        TimeMinutesStart.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimeHourStart, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(TimeMinutesStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeHourStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeMinutesStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        TableTime.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableTime);

        ButtonDeleteTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ButtonDeleteTime.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboDay, 0, 183, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonAddTime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonDeleteTime)))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonAddTime)
                            .addComponent(ButtonDeleteTime)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddTime;
    private javax.swing.JButton ButtonDeleteTime;
    private javax.swing.JComboBox ComboDay;
    private javax.swing.JTable TableTime;
    private javax.swing.JSpinner TimeHourFinished;
    private javax.swing.JSpinner TimeHourStart;
    private javax.swing.JSpinner TimeMinutesFinished;
    private javax.swing.JSpinner TimeMinutesStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public class ListenerAddTime implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                int Hinicio = (int)TimeHourStart.getValue();
                int Hfin = (int)TimeHourFinished.getValue();
                int Minicio = (int)TimeMinutesStart.getValue();
                int Mfin = (int)TimeMinutesFinished.getValue();
                
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                
                Date TimeStart = sdf.parse(Hinicio+":"+Minicio);
                Date TimeFinished = sdf.parse(Hfin+":"+Mfin);
                
                Horario horario = new Horario(TimeStart, TimeFinished, (Dia)ComboDay.getSelectedItem());
                
                curso.add(horario);
                TableTime.updateUI();
                
                
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        
    }
    
    public class ListenerDelteTime implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            curso.removeHorario(TableTime.getSelectedRow());
            TableTime.clearSelection();
            TableTime.updateUI();
            
        }
        
    }
    
}
