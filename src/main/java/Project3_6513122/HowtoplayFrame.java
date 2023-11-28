package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HowtoplayFrame extends JFrame {
    private JLabel drawpane;
    private JPanel contentPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;

    public HowtoplayFrame() {
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

        contentPane.add(drawpane);

        setContentPane(contentPane);

        validate();
        repaint();
    }
}