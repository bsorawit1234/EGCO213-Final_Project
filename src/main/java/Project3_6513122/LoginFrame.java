package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane, containerPassword;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JPasswordField  passwordField;
    private JTextArea       usernameTextArea;
    private JButton         btn_submit, btn_register;
    private JRadioButton    showPw;
    private ArrayList<User> UserList;
    private User user;
    public LoginFrame(JFrame pf, ArrayList<User> ul) {
        ParentFrame = pf;
        UserList = ul;
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
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(framewidth, frameheight);
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
        username.setForeground(Color.white);
        password.setVisible(true);
        password.setBounds(20, 200, 200, 100);
        password.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        password.setForeground(Color.white);
        label.add(username);
        label.add(password);

        usernameTextArea = new JTextArea();
        usernameTextArea.setFont(new Font("SanSerif", Font.BOLD, 20));
        usernameTextArea.setBounds(320, 225, 350, 30); // Adjust position and size as needed
        usernameTextArea.setEditable(true);
        drawpane.add(usernameTextArea);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("SanSerif", Font.BOLD, 20));
        passwordField.setBounds(320, 350, 350, 30); // Adjust position and size as needed
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

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(showPw.isSelected()) {
//                   showPw.setSelected(true);
//                   passwordField.setEchoChar((char) 0);
//                } else {
//                    showPw.setSelected(false);
//                    passwordField.setEchoChar('*');
//                }
//            }
        });
        containerPassword = new JPanel(new BorderLayout());
        containerPassword.add(passwordField, BorderLayout.CENTER);
        containerPassword.add(showPw, BorderLayout.EAST);
        containerPassword.setVisible(true);
        containerPassword.setBounds(320, 350, 350, 30);
        drawpane.add(containerPassword);

        btn_submit = new JButton("Submit");
        btn_submit.setFont(new Font("Trend Sans One", Font.PLAIN, 50));
        btn_submit.setBounds(300, 400,400 , 50);
        btn_submit.setForeground(Color.gray);
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user = login(usernameTextArea.getText(), new String(passwordField.getPassword()), UserList);
                    UserFrame userFrame = new UserFrame(ParentFrame, user, UserList);
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

        btn_register = new JButton("CREATE ACCOUNT");
        btn_register.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_register.setBounds(300, 500,400 , 50);
        btn_register.setForeground(Color.gray);
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame(ParentFrame, UserList);
                registerFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_register);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public User login(String username, String password, ArrayList<User> UserList) throws MyException {
        if(username.isBlank() || password.isBlank()) throw new MyException("Please enter somethings.");
        if(UserList != null) {
            for(int i = 0; i < UserList.size(); i++) {
                if(UserList.get(i).getUsername().equals(username)) {
                    if(UserList.get(i).getPassword().equals(password)) {
                        return UserList.get(i);
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
