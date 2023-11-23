package Project3_6513122;

import javax.swing.*;
import java.awt.*;

public class HowtoplayFrame extends JFrame {
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;

    public HowtoplayFrame() {
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("How to play");

        JLabel label = new JLabel("How to play page");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(label);
    }
}