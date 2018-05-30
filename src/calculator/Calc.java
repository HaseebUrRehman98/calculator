/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author haseeb
 */
public class Calc extends javax.swing.JFrame {

    private int prova1 = 0;
    private int moveflag = 0;
    private JButton buttons[][];
    private String[] caratteri = {"<-", "CE", "C", "x²", "1", "2", "3", "/", "4", "5", "6", "-", "7", "8", "9", "*", "0", ".", "+", "="};
    private KeyListener key;
    protected JLabel resultlabel;
    private int k = 0;
    private int l;
    private int h = 85;
    private int operatorflag = 0;
    private int numberflag = 1;
    private Calculator calcolo;
    private JButton X;
    private JFrame jframe;
    private JButton maximize;
    private int lastX, lastY;
    private MouseListener mouse;

    private JLabel name;
    private JPanel movepanel;

    public Calc() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(220, 300);
        this.setVisible(true);
        this.setResizable(false);
        //this.setLayout(null);
        this.getRootPane().setBorder(BorderFactory.createBevelBorder(1, Color.GREEN, Color.GREEN));
        this.setBackground(new Color(0, 0, 0, 1));

        key = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        if (buttons[i][j].getText().equals(ke.getKeyChar() + "")) {
                            resultlabel.setText(calcolo.valueChecker(resultlabel.getText(), ke.getKeyChar() + ""));
                            adjustTextSize();
                        }
                    }
                }
                if (ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    resultlabel.setText(calcolo.valueChecker(resultlabel.getText(), "<-" + ""));
                }
                if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
                    resultlabel.setText(calcolo.valueChecker(resultlabel.getText(), "=" + ""));
                }

            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }

        };
        mouse = new MouseListener() {
            JButton button2 = null;

            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getComponent() instanceof JButton) {
                    JButton button = (JButton) me.getComponent();
                    if (button.getText().equals("X")) {
                        System.exit(0);
                    } else if (button.getText().equals("_")) {
                        jframe.setState(Frame.ICONIFIED);
                    } else {

                        resultlabel.setText(calcolo.valueChecker(resultlabel.getText(), button.getText()));
                        adjustTextSize();
                    }
                }
                if (me.getComponent() instanceof JPanel) // check if mousepressed on Panel
                {
                    lastX = me.getXOnScreen();
                    lastY = me.getYOnScreen();
                    moveflag = 1;
                }

            }

            @Override
            public void mouseReleased(MouseEvent me) { // moveJframe
                if (moveflag == 1) {
                    int x = me.getXOnScreen();
                    int y = me.getYOnScreen();
                    setLocation(getLocationOnScreen().x + x - lastX,
                            getLocationOnScreen().y + y - lastY);
                    lastX = x;
                    lastY = y;
                    moveflag = 0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) { //check if mouse enter mimize and X
                if (me.getComponent() instanceof JButton) {
                    JButton button = (JButton) me.getComponent();
                    if (button.getText().equals("X") || button.getText().equals("_")) {
                        button.setBackground(Color.green);
                        button2 = button;
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent me) { // colorchange
                if (button2 != null) {
                    button2.setBackground(new Color(0, 0, 0, 0));
                }
                button2 = null;

            }
        };

        //initComponents();
        calcolo = new Calculator();
        movepanel = new JPanel();
        X = new JButton();
        maximize = new JButton();
        name = new JLabel();
        resultlabel = new JLabel();
        buttons = new JButton[5][4];

        // name 
        name.setText("CALCULATOR 1.0");
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setForeground(Color.GREEN);
        name.setSize(120, 40);
        name.setBackground(new Color(0, 0, 0, 1));
        name.setFont(new Font("SansSerif", Font.ITALIC, 12));
        //move panel
        movepanel.setSize(220, 40);
        movepanel.setName("movepanel");
        movepanel.setBackground(Color.LIGHT_GRAY);
        movepanel.addMouseListener(mouse);
        movepanel.setBackground(new Color(0, 0, 0, 128));

        //minmize setting
        X.setText("_");
        X.setName("minimize");
        X.setBounds(137, 0, 40, 40);
        X.setBackground(new Color(0, 0, 0, 0));
        X.setHorizontalAlignment(SwingConstants.CENTER);
        X.setBorder(BorderFactory.createLineBorder(Color.yellow));
        X.setForeground(Color.black);
        X.addMouseListener(mouse);
        // maximize setting
        maximize.setText("X");
        maximize.setBounds(175, 0, 40, 40);
        maximize.setBackground(new Color(0, 0, 0, 0));
        maximize.setForeground(Color.black);
        maximize.setHorizontalAlignment(SwingConstants.CENTER);
        maximize.setBorder(BorderFactory.createLineBorder(Color.yellow));
        maximize.addMouseListener(mouse);

        // result show label
        resultlabel.setText("0");
        resultlabel.setBounds(12, 40, 178, 45);
        resultlabel.setBackground(new Color(0, 0, 0, 200));
        resultlabel.setForeground(Color.white);
        resultlabel.setOpaque(true);
        resultlabel.setAutoscrolls(true);
        resultlabel.setBorder(BorderFactory.createLineBorder(Color.black));
        resultlabel.setFont(new Font("Verdana", Font.ITALIC, 15));
        resultlabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // adding
       
        this.add(resultlabel);
        this.add(X);
        this.add(maximize);
        this.add(name);
        this.add(movepanel);

        int converter = 0;

        for (int i = 0; i < buttons.length; i++) {
            l = 10;
            for (int j = 0; j < buttons[i].length; j++) {

                buttons[i][j] = new JButton();
                buttons[i][j].setText(caratteri[k]);

                buttons[i][j].setBounds(l, h, 47, 40);
                buttons[i][j].addKeyListener(key);
                buttons[i][j].addMouseListener(mouse);

                try {
                    converter = (int) Integer.parseInt(caratteri[k]); // adding different background to numbers button
                    buttons[i][j].setBackground(Color.CYAN);
                    buttons[i][j].setForeground(Color.BLACK);
                } catch (NumberFormatException io) {
                    buttons[i][j].setBackground(Color.black);
                    buttons[i][j].setForeground(Color.white);
                } finally {
                    this.add(buttons[i][j]);
                    l += 45;
                    k++;
                }

            }
            h += 40;
            JButton prova=new JButton();
           prova.setSize(1,1);
           this.add(prova);
        }
        // this.pack();

    }

    public void adjustTextSize() {
        Font labelFont = resultlabel.getFont();
        String labelText = resultlabel.getText();
        int resultlabeltextWidth = resultlabel.getFontMetrics(labelFont).stringWidth(labelText);
        int resultlabelWidth = resultlabel.getWidth();
        if (resultlabeltextWidth >= resultlabelWidth - 20) {
            resultlabel.setFont(new Font(labelFont.getName(), Font.PLAIN, resultlabel.getFont().getSize() - 1));
        } else {
            resultlabel.setFont(new Font("Verdana", Font.ITALIC, 15));
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
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusCycleRoot(true);
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
