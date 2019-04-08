/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UserDao;
import model.User;

import javax.swing.*;
import java.math.BigDecimal;

/**
 *
 * @author asus
 */
public class TurnMoney extends javax.swing.JFrame {

    /**
     * Creates new form TurnMoney
     */
    public TurnMoney() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TurnCard = new javax.swing.JTextField();
        TurnMoney = new javax.swing.JTextField();
        TurnConfirm = new javax.swing.JButton();
        TurnBackMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        jLabel1.setText("转账账号：");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        jLabel2.setText("转账金额：");

        TurnCard.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        TurnCard.setText("jTextField1");

        TurnMoney.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        TurnMoney.setText("jTextField2");

        TurnConfirm.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        TurnConfirm.setText("确认");
        TurnConfirm.setActionCommand("RegisteConfirm");
        TurnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnConfirmActionPerformed(evt);
            }
        });

        TurnBackMenu.setFont(new java.awt.Font("微软雅黑", 0, 16)); // NOI18N
        TurnBackMenu.setText("返回主菜单");
        TurnBackMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TurnBackMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(TurnConfirm)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(TurnBackMenu))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TurnMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TurnCard, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(TurnCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(TurnMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TurnConfirm)
                                        .addComponent(TurnBackMenu))
                                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>                        
    private void transferButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        String changeNumber = TurnCard.getText();
        if (changeNumber == null || changeNumber.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "对方账号不能为空", "失败", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDao authDao = UserDao.getInstance();
        User targetAuth = authDao.getMoneyByNumber(changeNumber);
        if (targetAuth == null) {
            JOptionPane.showMessageDialog(this, "对方账号不存在", "失败", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String changMon = TurnMoney.getText();
        if (changMon == null || changMon.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "转账金额不能为空", "失败", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BigDecimal amount = new BigDecimal(changMon);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "转账金额必须大于 0", "失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            User user = null;
            if (amount.compareTo(user.getMoney()) > 0) {
                JOptionPane.showMessageDialog(this, "余额不足", "失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserDao userDao = null;
            userDao.updateMoneyByNum(changeNumber, user.getMoney().add(amount));
            userDao.updateMoneyByNum(user.getNumber(), user.getMoney().subtract(amount));
            user.setMoney(user.getMoney().subtract(amount));
            JOptionPane.showMessageDialog(this, "转账成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "请输入正确的数值", "失败", JOptionPane.ERROR_MESSAGE);

        }
    }
    private void TurnBackMenuMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void TurnConfirmActionPerformed(java.awt.event.ActionEvent evt) {

    }

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
            java.util.logging.Logger.getLogger(TurnMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TurnMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TurnMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TurnMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TurnMoney().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton TurnBackMenu;
    private javax.swing.JTextField TurnCard;
    private javax.swing.JButton TurnConfirm;
    private javax.swing.JTextField TurnMoney;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    // End of variables declaration                   
}

