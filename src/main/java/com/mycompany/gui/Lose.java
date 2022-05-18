/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author zezos
 */
public class Lose extends javax.swing.JFrame {

    public void setText(String string) {
        this.NMCL.setText(string);
    }

    public Lose(JFrame source) {
        initComponents();
        this.source = source;
        Point pt = source.getLocation() ;
        setLocation(pt.x+3, pt.y + source.getHeight()/3);

        this.NMCL.setLocation(80, 80);
        this.NMCL.setFont(new java.awt.Font("Silkscreen", 0, 24));
        this.show();
        try {
            for (int i = 0; i <= 280; i += 5) {
                Thread.sleep(60);
                this.NMCL.repaint();
                if (i <= 200) {
                    this.NMCL.setLocation(20, i);
                } else {
                    this.NMCL.setLocation(20, i - 200);
                }
            }
            this.NMCL.setLocation(80, 80);
            this.NMCL.setFont(new java.awt.Font("Silkscreen", 0, 18));
            //edit ya MR shaaheen :)
            this.NMCL.setText("Press Any key to Try again    ESC to quit");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Bye");
            Thread.currentThread().stop();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NMCL = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(360, 200));
        setMinimumSize(new java.awt.Dimension(360, 200));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));
        jPanel1.setForeground(new java.awt.Color(31, 31, 31));
        jPanel1.setMaximumSize(new java.awt.Dimension(360, 200));
        jPanel1.setMinimumSize(new java.awt.Dimension(360, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 200));

        NMCL.setFont(new java.awt.Font("Silkscreen", 0, 24)); // NOI18N
        NMCL.setForeground(new java.awt.Color(196, 207, 161));
        NMCL.setText("No more chances left");

        jButton1.setBackground(new java.awt.Color(31, 31, 31));
        jButton1.setForeground(new java.awt.Color(31, 31, 31));
        jButton1.setBorder(null);
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(NMCL))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jButton1)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(NMCL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            Main_page mp = new Main_page();
            dispose();
            source.dispose();
            mp.setVisible(true);
        } else {
            try {
                GameField Gf = new GameField();
                dispose();
                source.dispose();
                Thread.sleep(20);
                Gf.setVisible(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Win.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Win.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1KeyPressed

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
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Lose(source).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel NMCL;
    private javax.swing.JButton jButton1;
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    private static JFrame source;
}
