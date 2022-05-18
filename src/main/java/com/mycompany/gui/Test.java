package com.mycompany.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    /*public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                Queue<String> queue = new LinkedList<>();
                queue.add("Loading");

                TickerTapPane pane = new TickerTapPane();
                pane.setMessages(queue);
            }
        });
    }*/

    public class TickerTapPane extends JPanel {

        private Queue<String> queue;
        private String message;

        private int xPos;

        public TickerTapPane() {
            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (message == null) {
                        message = queue.remove();
                        xPos = getWidth();
                    }
                    xPos -= 4;
                    FontMetrics fm = getFontMetrics(getFont());
                    int stringWidth = fm.stringWidth(message);
                    if (xPos <= -stringWidth) {
                        queue.add(message);
                        xPos = getWidth();
                        message = queue.remove();
                    }
                    repaint();
                }
            });
            timer.start();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (message != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                FontMetrics fm = g2d.getFontMetrics();
                int yPos = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(message, xPos, yPos);
                g2d.dispose();
            }
        }

        protected void setMessages(Queue<String> queue) {
            this.queue = queue;
        }

    }
}