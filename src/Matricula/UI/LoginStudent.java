package Matricula.UI;

import Matricula.logic.Estudiante;
import Matricula.logic.Exceptions.DebtStudentException;
import Matricula.logic.Exceptions.ObjectNotFoundException;
import Matricula.logic.Universidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginStudent extends javax.swing.JFrame {

    private Universidad u;
    private Principal main;

    public LoginStudent(Universidad uni, Principal main) {
        this.main = main;
        this.u = uni;
        initComponents();
        ListenerLogueo lg = new ListenerLogueo();
        FieldPasswordStudent.addActionListener(lg);
        ButtonAccess.addActionListener(lg);
        ButtonBack.addActionListener(new ListenerBack());
    }

    public void clear() {
        FieldCodeStudent.setText("");
        FieldPasswordStudent.setText("");
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
        FieldCodeStudent = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ButtonAccess = new javax.swing.JButton();
        FieldPasswordStudent = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        ButtonBack = new javax.swing.JButton();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingrese código de estudiante y contraseña");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Código:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        ButtonAccess.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonAccess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/login30x30.png"))); // NOI18N
        ButtonAccess.setText("Ingresar");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_icon-cp200x161.png"))); // NOI18N

        ButtonBack.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/boton-regresar50x50.png"))); // NOI18N
        ButtonBack.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ButtonAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(FieldCodeStudent)
                                    .addComponent(FieldPasswordStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(FieldCodeStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(FieldPasswordStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        clear();
        main.setVisible(true);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAccess;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JTextField FieldCodeStudent;
    private javax.swing.JPasswordField FieldPasswordStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
public class ListenerLogueo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Estudiante estu = u.buscarEstudiante(FieldCodeStudent.getText().trim());
                if (estu.getPassword().equals(FieldPasswordStudent.getText())) {
                    if(!u.ValidarDeudas(estu)){
                        throw new DebtStudentException("El actual Estudiante: "+ estu.toString()+" Presenta Deudas");
                    }
                    new MatriculaUI(u, estu, main).setVisible(true);
                    setVisible(false);
                }else{
                    throw new ObjectNotFoundException("Contraseña incorrecta");
                }
                
            } catch (ObjectNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (DebtStudentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public class ListenerBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            main.setVisible(true);
            setVisible(false);
        }

    }

}
