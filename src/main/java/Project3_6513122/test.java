package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test implements ActionListener {

    private JButton btn_play;

    public test() {
        ImageIcon playIcon = createImageIcon("src/main/java/Project3_6513122/resources/source slot/play-button.png");

        if (playIcon != null) {
            btn_play = new JButton(playIcon);
            btn_play.setBounds(265, 232, playIcon.getIconWidth(), playIcon.getIconHeight());
            btn_play.setBorderPainted(false);
            btn_play.setContentAreaFilled(false);
            btn_play.setFocusPainted(false);
            btn_play.setOpaque(false);
            btn_play.addActionListener(this);

            JFrame frame = new JFrame("Transparent Image Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.add(btn_play);
            frame.setSize(800, 600);
            frame.setVisible(true);
        }
    }

    private ImageIcon createImageIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image modifiedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(modifiedImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action performed when the button is clicked
        if (e.getSource() == btn_play) {
            // Handle button click action
            System.out.println("Button clicked!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}