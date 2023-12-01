package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private MainApplication mainFrame;
    private JPanel contentpane, containerPassword;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JPasswordField  passwordField;
    private JTextArea       usernameTextArea;
    private JButton         btn_submit, btn_register, btn_back;
    private JRadioButton    showPw;
    private ArrayList<User> UserList;
    private User user;
    public LoginFrame(MainApplication pf, ArrayList<User> ul) {
        mainFrame = pf;
        UserList = ul;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable( true);
        this.setTitle("Login");

        Addinput();
    }

    public void Addinput() {
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        MyImageIcon red = new MyImageIcon(MyConstants.RED).resize(383, 324);
        JLabel label = new JLabel(red);
        MyImageIcon username = new MyImageIcon(MyConstants.USERNAME).resize(248, 42);
        JLabel label1 = new JLabel(username);
        MyImageIcon password = new MyImageIcon(MyConstants.PASSWORD).resize(248, 42);
        JLabel label2 = new JLabel(password);
        label.setBounds(450, 200,383 , 324);
        label.setIcon(red);
        label.setVisible(true);

        label1.setBounds(20, 20, 248, 42);
        label1.setIcon(username);
        label1.setVisible(true);

        label2.setBounds(20, 145, 248, 42);
        label2.setIcon(password);
        label2.setVisible(true);

        label.add(label1);
        label.add(label2);

        usernameTextArea = new JTextArea();
        usernameTextArea.setFont(new Font("SanSerif", Font.BOLD, 30));
        usernameTextArea.setBounds(470, 280, 350, 40); // Adjust position and size as needed
        usernameTextArea.setEditable(true);
        drawpane.add(usernameTextArea);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("SanSerif", Font.BOLD, 30));
        passwordField.setBounds(470, 500, 350, 40); // Adjust position and size as needed
        passwordField.setEchoChar('*'); // To hide the password characters
        drawpane.add(passwordField);

        showPw = new JRadioButton();
        showPw.setBackground(Color.white);
        showPw.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPw.setSelected(true);
                passwordField.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPw.setSelected(false);
                passwordField.setEchoChar('*');
            }
        });
        containerPassword = new JPanel(new BorderLayout());
        containerPassword.add(passwordField, BorderLayout.CENTER);
        containerPassword.add(showPw, BorderLayout.EAST);
        containerPassword.setVisible(true);
        containerPassword.setBounds(470, 400, 350, 40);
        drawpane.add(containerPassword);

        MyImageIcon submitBUTTON = new MyImageIcon(MyConstants.SUBMIT).resize(142, 42);
        btn_submit = new JButton(submitBUTTON);
        btn_submit.setBounds(470, 460,142 , 42);
        btn_submit.setBorderPainted(false);
        btn_submit.setContentAreaFilled(false);
        btn_submit.setFocusPainted(false);
        btn_submit.setOpaque(false);
        btn_submit.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = login(usernameTextArea.getText(), new String(passwordField.getPassword()), UserList);
                    UserFrame userFrame = new UserFrame(mainFrame, index, UserList);
                    userFrame.setVisible(true);
                    dispose();
                } catch (MyException err) {
                    JOptionPane.showMessageDialog(getParent(),err.getMessage());
                    usernameTextArea.setText("");
                    passwordField.setText("");
                } catch (Exception err) {
                    System.err.println(err);
                }
            }
        });
        drawpane.add(btn_submit);

        MyImageIcon createACCBUTTON = new MyImageIcon(MyConstants.CREATEACC).resize(401, 59);
        btn_register = new JButton(createACCBUTTON);
        btn_register.setBounds(445, 550,401 , 59);
        btn_register.setBorderPainted(false);
        btn_register.setContentAreaFilled(false);
        btn_register.setFocusPainted(false);
        btn_register.setOpaque(false);
        btn_register.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame(mainFrame, UserList);
                registerFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_register);

        MyImageIcon backBUTTON = new MyImageIcon(MyConstants.BACK).resize(169, 74);
        btn_back = new JButton(backBUTTON);
        btn_back.setBounds(30, 630,169 , 74);
        btn_back.setBorderPainted(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setFocusPainted(false);
        btn_back.setOpaque(false);
        btn_back.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                MainApplication mainApplication = new MainApplication();
//                mainApplication.setVisible(true);
                mainFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_back);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public int login(String username, String password, ArrayList<User> UserList) throws MyException {
        if(username.isBlank() || password.isBlank()) throw new MyException("Please enter somethings.");
        if(UserList != null) {
            for(int i = 0; i < UserList.size(); i++) {
                if(UserList.get(i).getUsername().equals(username)) {
                    if(UserList.get(i).getPassword().equals(password)) {
                        return i;
                    } else {
                        throw new MyException("Username or Password is incorrect.");
                    }
                } else {
                    if(i == UserList.size()-1) throw new MyException("Username or Password is incorrect.");
                }
            }
        }
        throw new MyException("username or Password is incorrect");
    }
}
