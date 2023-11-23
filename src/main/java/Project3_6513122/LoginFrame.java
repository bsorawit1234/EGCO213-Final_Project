package Project3_6513122;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JPasswordField  passfield;
    private JTextArea       textarea;

    public LoginFrame() {
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Login");

        Addinput();
    }

    public void Addinput() {
        contentpane = (JPanel)getContentPane();

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_HOME).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setLayout(null);

        MyImageIcon Orange = new MyImageIcon(MyConstants.ORANGE).resize(500, 300);
        JLabel label = new JLabel(Orange);
        JLabel username = new JLabel("Username : ");
        JLabel password = new JLabel("Password : ");
        label.setBounds(300, 50,400 , 500);
        label.setIcon(Orange);
        label.setVisible(true);
        username.setVisible(true);
        username.setBounds(20, 80, 200, 100);
        username.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        password.setVisible(true);
        password.setBounds(20, 200, 200, 100);
        password.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        label.add(username);
        label.add(password);

        JTextArea usernameTextArea = new JTextArea();
        usernameTextArea.setFont(new Font("SanSerif", Font.BOLD, 20));
        usernameTextArea.setBounds(320, 225, 350, 30); // Adjust position and size as needed
        usernameTextArea.setEditable(true);
        drawpane.add(usernameTextArea);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("SanSerif", Font.BOLD, 20));
        passwordField.setBounds(320, 350, 350, 30); // Adjust position and size as needed
        passwordField.setEchoChar('*'); // To hide the password characters
        drawpane.add(passwordField);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }
}
