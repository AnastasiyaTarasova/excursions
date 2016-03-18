/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.Admin;
import logic.Client;
import logic.User;

/**
 *
 * @author Анастасия
 */
public class Excursion extends javax.swing.JFrame {
    private ExcursionTableModel model;
    ArrayList<User> excursions;
    User user, user2;
        
    public void getAllExcursions(User user) throws SQLException{
        this.user = user;                                      
        excursions =  (ArrayList<User>) Service.getAll(2);
        model = new ExcursionTableModel(excursions);
        jTable1.setModel(model);
        jTable1.setRowSelectionInterval(0, 0);
    
    }
   
    public Excursion(User user) throws SQLException {
        super("Экскурсии");
        initComponents();
        this.getAllExcursions(user);
        firstName_.setText(user.getFirstName());
        firstName_.setEnabled(false);
        lastName_.setText(user.getLastName());
        lastName_.setEnabled(false);
        
        if (user instanceof Admin) {
            buttonOrder.setEnabled(false);
            labelType.setText("Администратор");
	} 
        else if (user instanceof Client) {
            buttonNewExcursion.setEnabled(false);
            buttonEditExcursion.setEnabled(false);
            buttonDeleteExcursion.setEnabled(false); 
            labelType.setText("Клиент");
        }
        else {
            buttonOrder.setEnabled(false);
            buttonNewExcursion.setEnabled(false);
            buttonDeleteExcursion.setEnabled(false);
            labelType.setText("Экскурсовод");
        }    
    }
    
     public Excursion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        firstName_ = new javax.swing.JTextField();
        lastName_ = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        editNameOfExcursion = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        buttonAllExcursion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        buttonNewExcursion = new javax.swing.JButton();
        buttonDeleteExcursion = new javax.swing.JButton();
        buttonEditExcursion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        buttonOrders = new javax.swing.JButton();
        buttonOrder = new javax.swing.JButton();
        labelType = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Имя");

        jLabel2.setText("Фамилия");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Поиск");

        jButton4.setText("Найти");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        buttonAllExcursion.setText("Показать всё");
        buttonAllExcursion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAllExcursionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editNameOfExcursion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAllExcursion, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(editNameOfExcursion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(buttonAllExcursion))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Personage", "Personage", "Name", "Surname", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonNewExcursion.setText("Добавить экскурсию");
        buttonNewExcursion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonNewExcursionMouseClicked(evt);
            }
        });
        buttonNewExcursion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewExcursionActionPerformed(evt);
            }
        });

        buttonDeleteExcursion.setText("Удалить экскурсю");
        buttonDeleteExcursion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteExcursionMouseClicked(evt);
            }
        });
        buttonDeleteExcursion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteExcursionActionPerformed(evt);
            }
        });

        buttonEditExcursion.setText("Изменить экскурсию");
        buttonEditExcursion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEditExcursionMouseClicked(evt);
            }
        });
        buttonEditExcursion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditExcursionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonDeleteExcursion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonNewExcursion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEditExcursion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonNewExcursion)
                .addGap(18, 18, 18)
                .addComponent(buttonDeleteExcursion)
                .addGap(18, 18, 18)
                .addComponent(buttonEditExcursion)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonOrders.setText("Список заказов");
        buttonOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonOrdersMouseClicked(evt);
            }
        });
        buttonOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrdersActionPerformed(evt);
            }
        });

        buttonOrder.setText("Оформить заказ");
        buttonOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonOrderMouseClicked(evt);
            }
        });
        buttonOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(buttonOrders)
                .addGap(18, 18, 18)
                .addComponent(buttonOrder)
                .addContainerGap())
        );

        labelType.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(73, 73, 73)
                        .addComponent(firstName_, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lastName_, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(labelType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(firstName_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(lastName_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewExcursionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNewExcursionMouseClicked
        AddExcursionFrame addPerson = new AddExcursionFrame();
        addPerson.setTitle("Добавить экскурсию");
        addPerson.setVisible(true);
    
    }//GEN-LAST:event_buttonNewExcursionMouseClicked

    private void buttonNewExcursionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewExcursionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNewExcursionActionPerformed

    private void buttonEditExcursionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditExcursionMouseClicked
        int rowIndex = jTable1.getSelectedRow();
        long id = Long.parseLong((String)jTable1.getValueAt(rowIndex,0));
        user2 = Service.find(id);
        AddExcursionFrame addPerson = new AddExcursionFrame(user2);
        addPerson.setTitle("Изменить информацию об экскурсоводе");
        addPerson.setVisible(true);
    
    }//GEN-LAST:event_buttonEditExcursionMouseClicked

    private void buttonEditExcursionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditExcursionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditExcursionActionPerformed

    private void buttonOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOrdersMouseClicked
        try {
            OrdersFrame orders = null;
            orders = new OrdersFrame(user);
            orders.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Excursion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonOrdersMouseClicked

    private void buttonOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOrderMouseClicked
        try {
            AddOrderFrame order = null;
            order = new AddOrderFrame();
            order.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Excursion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonOrderMouseClicked

    private void buttonAllExcursionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAllExcursionMouseClicked
        try {
            this.getAllExcursions(user);
            editNameOfExcursion.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Excursion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonAllExcursionMouseClicked

    private void buttonOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonOrderActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try {
            excursions = (ArrayList<User>) Service.findNameOfExcursion(editNameOfExcursion.getText());
            if (excursions != null){
                model = new ExcursionTableModel(excursions);
                jTable1.setModel(model);
                jTable1.setRowSelectionInterval(0, 0);
                }
            else{
                JOptionPane.showMessageDialog(null, "Экскурсия не существует");
                System.out.println("null2 = ");
                editNameOfExcursion.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Excursion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jButton4MouseClicked

    private void buttonDeleteExcursionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteExcursionMouseClicked
        try {
            int rowIndex = jTable1.getSelectedRow();
            long id = Long.parseLong((String)jTable1.getValueAt(rowIndex,0));
            Service.removeUser((long)id);
            getAllExcursions(user);
        } catch (SQLException ex) {
            Logger.getLogger(Excursion.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_buttonDeleteExcursionMouseClicked

    private void buttonDeleteExcursionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteExcursionActionPerformed
    }//GEN-LAST:event_buttonDeleteExcursionActionPerformed

    private void buttonOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonOrdersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Excursion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Excursion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Excursion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Excursion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                   
                //ConnectionManager con = ConnectionManager.getInstance();
               // con.getConnection();
               // new Excursion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllExcursion;
    private javax.swing.JButton buttonDeleteExcursion;
    private javax.swing.JButton buttonEditExcursion;
    private javax.swing.JButton buttonNewExcursion;
    private javax.swing.JButton buttonOrder;
    private javax.swing.JButton buttonOrders;
    private javax.swing.JTextField editNameOfExcursion;
    private javax.swing.JTextField firstName_;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelType;
    private javax.swing.JTextField lastName_;
    // End of variables declaration//GEN-END:variables
}
