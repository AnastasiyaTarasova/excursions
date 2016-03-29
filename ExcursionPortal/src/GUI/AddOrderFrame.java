/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.Service;
import java.awt.Color;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import logic.Client;
import logic.CurrentUser;
import logic.Order;
import logic.User;
import logic.Guide;

/**
 *
 * @author Анастасия
 */
public class AddOrderFrame extends javax.swing.JFrame {
    ArrayList<User> excursions;
    ArrayList<User> client;
    public Excursion excursion = new Excursion();
    Order order;
    Order order2;
    Object selectedItem1, selectedItem2, selectedItem3;
    List<User> users;
    List<User> users_;
    String[] statusZ = {"На рассмотрении", "Одобрена", "Проведена", "Отказано"};
    
    SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formTime = new SimpleDateFormat("HH:mm");
    
    //Запонение выпадающего списка Excursion
    public void getCBListExcursion() throws SQLException{
        excursions =  (ArrayList<User>) Service.getAll(2);
        if (excursions.size()!= 0)
            for(int i=0; i < excursions.size();i++){
                cbExcursion.addItem(excursions.get(i).getNameOfExcursion());
            }
    }
  
    //Запонение выпадающего списка Client
    /*public void getCBListClient() throws SQLException{
        client =  (ArrayList<User>) Service.getAll(1);
        if (client.size()!= 0)
        {
            int customer_size = client.size();
            for(int i=0; i < client.size();i++){
                //cbClient.addItem(client.get(customer_size - i - 1).getLastName());
            }
        }
    }*/

    public AddOrderFrame() throws SQLException {
        super("Новый заказ"); 
        initComponents();
        buttonOk.setText("Отправить заказ");
        jLabel1.setVisible(false);
        editId.setVisible(false);
        this.getCBListExcursion();
        //this.getCBListClient();
        /*cbClient.setSelectedItem(CurrentUser.getUser().getLastName());
        cbClient.setEnabled(false);*/
        lastname.setText(CurrentUser.getUser().getLastName());
        lastname.setEnabled(false);
        cbStatus.removeAllItems();
        /*for(int i=0; i < statusZ.length;i++){
            cbStatus.addItem(statusZ[i]);
        }*/
        //cbStatus.addItem("На рассмотрении");
        cbStatus.setSelectedIndex(0);
        cbStatus.setEditable(true);
        ((JTextField)cbStatus.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
        cbStatus.setEnabled(false);
    }
    
    public AddOrderFrame(Order excursion) throws SQLException {
        super("Edit Order"); 
        initComponents();
        this.order2 = excursion;
        jLabel1.setVisible(true);
        editId.setEnabled(false);
        editId.setText(Long.toString(excursion.getId()));
        lastname.setText(excursion.getClient().getLastName());
        lastname.setDisabledTextColor(Color.BLACK);
        lastname.setEnabled(false);
        //cbClient.addItem(excursion.getClient().getLastName());
        cbExcursion.addItem(excursion.getExcursion().getNameOfExcursion());
        cbStatus.addItem(excursion.getStatus());
        //cbClient.setSelectedItem((User.findLastNameClient(excursion.getClient().getLastName())).toString());
        //cbExcursion.setSelectedItem((User.findNameOfExcursion(excursion.getExcursion().getNameOfExcursion())).toString());
        editAdress.setText(excursion.getAddress());
        editDate.setText(formDate.format(excursion.getDate()));
        editTime.setText(formTime.format(excursion.getTime()));
        editMinut.setText(Integer.toString(excursion.getMinut()));
        editSumma.setText(Integer.toString(excursion.getSum()));
        User cur = CurrentUser.getUser();
        if(cur instanceof Guide){ //выводятсяна экран значения записанные в бд и ставятся метки чтобы их нельзя трогать. 
                                  //чтобы их изменить нужно залезть в бд с запросом на именение и тогда изменить
                                  //изменения применяются при нажатии на кнопке
            editSumma.setDisabledTextColor(Color.BLACK);
            editSumma.setEnabled(false);
            editMinut.setDisabledTextColor(Color.BLACK);
            editMinut.setEnabled(false);
            editTime.setDisabledTextColor(Color.BLACK);
            editTime.setEnabled(false);
            editDate.setDisabledTextColor(Color.BLACK);
            editDate.setEnabled(false);
            editAdress.setDisabledTextColor(Color.BLACK);
            editAdress.setEnabled(false);
                        
            /*cbExcursion.setBackground(Color.WHITE);
            ((JTextField) cbExcursion.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLACK);
            JComboBox cmb = new JComboBox("first", "second");
            ComboBoxRenderer renderer = new ComboBoxRenderer(cbExcursion);
            renderer.setColors("black", "black");
            renderer.setStrings("first", "second");
            cmb.setRenderer(renderer);*/
            cbExcursion.setEditable(true);
            ((JTextField)cbExcursion.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
            cbExcursion.setEnabled(false);
            
            //cbClient.setEnabled(false);
            
            
            cbStatus.removeAllItems();
            /*if(excursion.getStatus().equals("На рассмотрении")){
                cbStatus.addItem("Одобрена");
                cbStatus.addItem("Отказано");
            
            }
            else if(excursion.getStatus().equals("Одобрена")){
                cbStatus.addItem("Проведена");
            }
            else{
                cbStatus.addItem("Одобрена");
                //cbStatus.addItem("Отказано");
                //cbStatus.addItem("Проведена");
            }*/
            String[] buff = new String[2];
            buff = order2.GetAvailableStatus(/*excursion.getStatus()*/);
            cbStatus.addItem(buff[0]);
            if(buff[1] != null)
                cbStatus.addItem(buff[1]);
            /*cbStatus.addItem("Одобрена");
            cbStatus.addItem("Проведена");
            cbStatus.addItem("Отказано");*/
            //getEvaluabeStatus - вернуть статусы, в которые возможнен переход в данный момент времени и спрятать это не в gui
        }
        if(cur instanceof Client){
            cbStatus.removeAllItems();
            cbStatus.addItem(order2.GetAvailableStatus(/*excursion.getStatus()*/)[0]);
            cbStatus.setEditable(true);
            ((JTextField)cbStatus.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
            cbStatus.setEnabled(false);
        }
        buttonOk.setText("Save");

        cbStatus.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        editAdress = new javax.swing.JTextField();
        editSumma = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editMinut = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        buttonOk = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox();
        cbExcursion = new javax.swing.JComboBox();
        editDate = new javax.swing.JFormattedTextField();
        editTime = new javax.swing.JFormattedTextField();
        lastname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Hомер заказа");

        jLabel2.setText("Клиент");

        jLabel3.setText("Название экскурсии");

        jLabel4.setText("Дата");

        jLabel5.setText("Время");

        jLabel9.setText("Место встречи с экскурсоводом");

        jLabel6.setText("Продолжиельность (мин)");

        jLabel7.setText("Стоимость");

        jButton1.setText("Отмена");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonOk.setText("Отправить заказ");
        buttonOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonOkMouseClicked(evt);
            }
        });
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        cbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbStatusMouseClicked(evt);
            }
        });
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonOk)
                .addGap(27, 27, 27))
            .addComponent(cbStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(buttonOk))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        cbExcursion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbExcursionMouseClicked(evt);
            }
        });
        cbExcursion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbExcursionItemStateChanged(evt);
            }
        });
        cbExcursion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExcursionActionPerformed(evt);
            }
        });

        try {
            editDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            editTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbExcursion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editId)
                            .addComponent(editAdress)
                            .addComponent(editMinut)
                            .addComponent(editSumma)
                            .addComponent(editDate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(editTime)
                            .addComponent(lastname))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(editId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbExcursion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(editAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(editTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editMinut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editSumma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void buttonOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOkMouseClicked
        try {
            
            long millis = (formTime.parse(editTime.getText())).getTime();
            long second = (millis / 1000) % 60;
            long minute = (millis / (1000 * 60)) % 60;
            long hour = (millis / (1000 * 60 * 60)) % 24;
            String time = String.format("%02d:%02d:%02d:%d", hour+3, minute, second, millis);
            java.sql.Time timeValue = new java.sql.Time(formTime.parse(time).getTime());
            
            //users  = Service.findNameOfExcursion(selectedItem2.toString());
            //users_ = Service.findLastNameClient(selectedItem1.toString());
            if (buttonOk.getText() != "Save"){ 
                //Order excursion = new Order(Service.find(users.get(0).getId()),Service.find(users_.get(0).getId()),
                //    editCity.getText(), (Date) formDate.parse(editDate.getText()), timeValue,
                //    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
              //  System.out.println("excursion.getDate1 "+excursion.getDate());
              //  System.out.println("excursion.getId1 "+excursion.getId());
              //  System.out.println("excursion.idtClient1 "+excursion.getClient().getId());
                int addOrderResult = Service.addOrder(selectedItem2.toString(),lastname.getText(),
                    editAdress.getText(), (Date) formDate.parse(editDate.getText()), timeValue,
                    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
                if (addOrderResult == 0)
                {
                    JOptionPane.showMessageDialog(null, "Введены не все данные, либо данные не корректны"); 
                }
                else if (addOrderResult == -3)
                {
                    JOptionPane.showMessageDialog(null, "Экскурсовод занят, попробуйте выбрать другое время");
                }
                else if (addOrderResult != -3)
                {
                    JOptionPane.showMessageDialog(null, "Заказ успешно добавлен");
                }
              
                
                this.setVisible(false);
            }
            else {
                
                //Order excursion3 = new Order(excursion2.getId(), Service.find(users.get(0).getId()),Service.find(users_.get(0).getId()),
                //    editCity.getText(), (Date) formDate.parse(editDate.getText()),  timeValue,
                //    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
                int updateOrderResult = 0;
                if(CurrentUser.getUser() instanceof Guide)
                    updateOrderResult = Service.updateOrder(order2.getId(), order2.getExcursion().getNameOfExcursion(),order2.getExcursion().getLastName(),
                    order2.getAddress(), (Date) formDate.parse(editDate.getText()),  timeValue,
                    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
                else
                updateOrderResult = Service.updateOrder(Long.valueOf(editId.getText()), selectedItem2.toString(),lastname.getText(),
                    editAdress.getText(), (Date) formDate.parse(editDate.getText()),  timeValue,
                    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
                
//                updateOrderResult = Service.updateOrder(Long.valueOf(editId.getText()), selectedItem2.toString(),lastname.getText(),
//                    editAdress.getText(), (Date) formDate.parse(editDate.getText()),  timeValue,
//                    Integer.valueOf(editMinut.getText()),Integer.valueOf(editSumma.getText()),  selectedItem3.toString(), Boolean.parseBoolean("False"));
                
                this.setVisible(false);
                
                if (updateOrderResult == 0)
                {
                    JOptionPane.showMessageDialog(null, "Введены не все данные, либо данные не корректны");
                }
                else if (updateOrderResult == -3)
                {
                    JOptionPane.showMessageDialog(null, "Экскурсовод занят, попробуйте выбрать другое время");
                }
                else if (updateOrderResult == -2)
                {
                    JOptionPane.showMessageDialog(null, "Информация обновлена");
                }
            }
        } catch (Exception ex) {
            if (ex instanceof ParseException)
                Logger.getLogger(AddOrderFrame.class.getName()).log(Level.SEVERE, null, ex);
            else
                Logger.getLogger(AddExcursionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonOkMouseClicked

    private void cbExcursionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExcursionActionPerformed
       selectedItem2 = cbExcursion.getSelectedItem();
         
    }//GEN-LAST:event_cbExcursionActionPerformed

    private void cbExcursionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbExcursionMouseClicked
        try {
            cbExcursion.removeAllItems();
            this.getCBListExcursion();
        } catch (SQLException ex) {
            Logger.getLogger(AddOrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbExcursionMouseClicked

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        selectedItem3 = cbStatus.getSelectedItem();
    }//GEN-LAST:event_cbStatusActionPerformed

    private void cbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbStatusMouseClicked
        /*cbStatus.removeAllItems();
        for(int i=0; i < statusZ.length;i++){
            cbStatus.addItem(statusZ[i]);
        }
        cbStatus.setSelectedIndex(0);*/
        //сbStatus.setSelectedItem(client.get(i).getLastName());
    }//GEN-LAST:event_cbStatusMouseClicked

    private void cbExcursionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbExcursionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbExcursionItemStateChanged

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonOkActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameActionPerformed

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
            java.util.logging.Logger.getLogger(AddOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddOrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddOrderFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddOrderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOk;
    private javax.swing.JComboBox cbExcursion;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JTextField editAdress;
    private javax.swing.JFormattedTextField editDate;
    private javax.swing.JTextField editId;
    private javax.swing.JTextField editMinut;
    private javax.swing.JTextField editSumma;
    private javax.swing.JFormattedTextField editTime;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastname;
    // End of variables declaration//GEN-END:variables
}
