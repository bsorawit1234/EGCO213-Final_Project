package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


class RegisterFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JPasswordField  passwordField;
    private JTextArea       usernameTextArea;
    private JButton         btn_submit;
    private ArrayList<User> UserList;

    public RegisterFrame(JFrame pf, ArrayList<User> ul) {
        ParentFrame = pf;
        UserList = ul;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Register");

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

        btn_submit = new JButton("Submit");
        btn_submit.setFont(new Font("Trend Sans One", Font.PLAIN, 50));
        btn_submit.setBounds(300, 400,400 , 50);
        btn_submit.setForeground(Color.gray);
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    register(usernameTextArea.getText(), new String(passwordField.getPassword()), UserList);
                } catch (MyException err) {
                    /*show JDialogue*/
                    JOptionPane.showMessageDialog(getParent(),err.getMessage());
                    usernameTextArea.setText("");
                    passwordField.setText("");
                } catch (Exception err) {
                    System.err.println(err);
                }
            }
        });
        drawpane.add(btn_submit);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public void register(String username, String password, ArrayList<User> UserList) throws MyException {
        if(UserList != null) {
           for(User u : UserList) {
              if(u.getUsername().equals(username)) {
                  throw new MyException("Username has been taken already.");
              }
           }
        }
        if(username == null || password == null) {
            throw new MyException("Username and Password cannot be empty.");
        }
        if(username.contains(" ") || password.contains(" ")) {
            throw new MyException("Username and Password cannot contain space.");
        }
        if(username.length() < 5 || password.length() < 5) {
            throw new MyException("Username and Password length must be greater than 5 characters.");
        }

        UserList.add(new User(username, password));

        /* write to file */
        try{
            PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
            write.println(username + " " + password);
            write.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
