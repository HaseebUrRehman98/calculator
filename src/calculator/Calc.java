/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author haseeb
 */
public class Calc extends javax.swing.JFrame {

    private JButton b[][];
    private String[] caratteri = {"<-", "CE", "C", "^", "1", "2", "3", "/", "4", "5", "6", "-", "7", "8", "9", "*", "0", ".", "+", "="};
    private ActionListener listner;
    private KeyListener key;
    private JLabel label;
    private int k = 0;
    private int l;
    private int h = 50;
    private int operatorflag = 0;
    private int numberflag = 1;
    private Calculator calcolo;

    public Calc() {
        initComponents();
        calcolo = new Calculator();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Calculator 1.0");
        this.setSize(250, 300);
        this.setResizable(false);
        // intialize
        label = new JLabel();
        b = new JButton[5][4];
        key = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //System.err.println(ke.getKeyCode());
                for (int i = 0; i < b.length; i++) {
                    for (int j = 0; j < b[i].length; j++) {
                        if (b[i][j].getText().equals(ke.getKeyChar() + "")) {
                           label.setText(calcolo.valueChecker(label.getText(), ke.getKeyChar() + ""));
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }

        };

        listner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                label.setText(calcolo.valueChecker(label.getText(), ae.getActionCommand()));
            }
        };
        // format setting jlabel and button
        label.setText("0");
        label.setBounds(12, 5, 175, 45);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(label);
        int converter = 0;
        for (int i = 0; i < b.length; i++) {
            l = 10;
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = new JButton();
                b[i][j].setText(caratteri[k]);
                b[i][j].setBounds(l, h, 45, 40);
                b[i][j].addActionListener(listner);
                b[i][j].addKeyListener(key);
                try {
                    converter = (int) Integer.parseInt(caratteri[k]); // adding different background to numbers button
                    b[i][j].setBackground(Color.CYAN);
                } catch (NumberFormatException io) {
                    b[i][j].setBackground(Color.yellow);

                } finally {
                    this.add(b[i][j]);
                    l += 45;
                    k++;
                }
            }
            h += 40;
        }

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(440, 278, 230, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}