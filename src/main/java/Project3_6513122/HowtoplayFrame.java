package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HowtoplayFrame extends JFrame {
    private JFrame mainFrame;
    private JLabel drawpane;
    private JPanel contentPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JButton btn_back;

    public HowtoplayFrame(JFrame pf) {
        mainFrame = pf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("How to play");

        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.HOWTOPLAYPAGE).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        MyImageIcon backBUTTON = new MyImageIcon(MyConstants.BACK).resize(169, 74);
        btn_back = new JButton(backBUTTON);
        btn_back.setBounds(30, 630,169 , 74);
        btn_back.setBorderPainted(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setFocusPainted(false);
        btn_back.setOpaque(false);
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_back);

        contentPane.add(drawpane);

        setContentPane(contentPane);



        validate();
        repaint();
    }
}