package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;


class RegisterFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane, containerRePassword, containerPassword;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JPasswordField  passwordField, repasswordField;
    private JTextArea       usernameTextArea;
    private JButton         btn_submit, btn_back;
    private JCheckBox       certify;
    private boolean         isCertify;
    private JRadioButton    showPw1, showPw2;
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
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setLayout(null);

        MyImageIcon red = new MyImageIcon(MyConstants.RED).resize(383, 324);
        JLabel label = new JLabel(red);
        MyImageIcon username = new MyImageIcon(MyConstants.USERNAME).resize(248, 42);
        JLabel label1 = new JLabel(username);
        MyImageIcon password = new MyImageIcon(MyConstants.PASSWORD).resize(248, 42);
        JLabel label2 = new JLabel(password);
        MyImageIcon repassword = new MyImageIcon(MyConstants.REPASSWORD).resize(338, 45);
        JLabel label3 = new JLabel(repassword);

        label.setBounds(450, 200,383 , 324);
        label.setIcon(red);
        label.setVisible(true);

        label1.setBounds(20, 20, 248, 42);
        label1.setIcon(username);
        label1.setVisible(true);

        label2.setBounds(20, 120, 248, 42);
        label2.setIcon(password);
        label2.setVisible(true);

        label3.setBounds(15, 220, 338, 45);
        label3.setIcon(repassword);
        label3.setVisible(true);

        label.add(label1);
        label.add(label2);
        label.add(label3);

        usernameTextArea = new JTextArea();
        usernameTextArea.setFont(new Font("SanSerif", Font.BOLD, 20));
        usernameTextArea.setBounds(470, 275, 350, 30); // Adjust position and size as needed
        usernameTextArea.setEditable(true);
        drawpane.add(usernameTextArea);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("SanSerif", Font.BOLD, 20));
        passwordField.setBounds(470, 375, 350, 30); // Adjust position and size as needed
        passwordField.setEchoChar('*'); // To hide the password characters

        showPw1 = new JRadioButton();
        showPw1.setBackground(null);
        showPw1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPw1.setSelected(true);
                showPw2.setSelected(true);
                passwordField.setEchoChar((char) 0);
                repasswordField.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPw1.setSelected(false);
                showPw2.setSelected(false);
                passwordField.setEchoChar('*');
                repasswordField.setEchoChar('*');
            }
        });
        containerPassword = new JPanel(new BorderLayout());
        containerPassword.add(passwordField, BorderLayout.CENTER);
        containerPassword.add(showPw1, BorderLayout.EAST);
        containerPassword.setVisible(true);
        containerPassword.setBounds(470, 375, 350, 30);
        drawpane.add(containerPassword);

        repasswordField = new JPasswordField();
        repasswordField.setFont(new Font("SanSerif", Font.BOLD, 20));
        repasswordField.setBounds(470, 475, 350, 30); // Adjust position and size as needed
        repasswordField.setEchoChar('*'); // To hide the password characters

        showPw2 = new JRadioButton();
        showPw2.setBackground(Color.white);
        showPw2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                showPw1.setSelected(true);
                showPw2.setSelected(true);
                passwordField.setEchoChar((char) 0);
                repasswordField.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPw1.setSelected(false);
                showPw2.setSelected(false);
                passwordField.setEchoChar('*');
                repasswordField.setEchoChar('*');
            }
        });
        containerRePassword = new JPanel(new BorderLayout());
        containerRePassword.add(repasswordField, BorderLayout.CENTER);
        containerRePassword.add(showPw2, BorderLayout.EAST);
        containerRePassword.setVisible(true);
        containerRePassword.setBounds(470, 475, 350, 30);
        drawpane.add(containerRePassword);

        certify = new JCheckBox();
        certify.setFont(new Font("SanSerif", Font.PLAIN, 16));
        certify.setText("I am certify that I am at least 20 years old.");
        certify.setBounds(480, 520, 350, 30);
        certify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCertify = certify.isSelected();
            }
        });
        certify.setOpaque(false);
        certify.setBackground(new Color(0, 0, 0, 0));
        certify.setFocusPainted(false);
        certify.setForeground(Color.white);
        drawpane.add(certify);

        MyImageIcon registerBUTTON = new MyImageIcon(MyConstants.REGISTER).resize(277, 74);
        btn_submit = new JButton(registerBUTTON);
        btn_submit.setBounds(490, 570, 277, 74);
        btn_submit.setIcon(registerBUTTON);
        btn_submit.setVisible(true);
        btn_submit.setBorderPainted(false);
        btn_submit.setContentAreaFilled(false);
        btn_submit.setFocusPainted(false);
        btn_submit.setOpaque(false);
        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = register(usernameTextArea.getText(), new String(passwordField.getPassword()), new String(repasswordField.getPassword()), UserList);
                    UserFrame userFrame = new UserFrame(ParentFrame, index, UserList);
                    userFrame.setVisible(true);
                    dispose();
                } catch (IsCertify error) {
                    JOptionPane.showMessageDialog(getParent(),error.getMessage());
                } catch (MyException err) {
                    /*show JDialogue*/
                    JOptionPane.showMessageDialog(getParent(),err.getMessage());
                    usernameTextArea.setText("");
                    passwordField.setText("");
                    repasswordField.setText("");
                } catch (Exception err) {
                    System.err.println(err);
                }
            }
        });
        drawpane.add(btn_submit);

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
                LoginFrame loginFrame = new LoginFrame(ParentFrame, UserList);
                loginFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_back);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public int register(String username, String password, String repassword, ArrayList<User> UserList) throws MyException, IsCertify {
        if(!isCertify) {
            throw new IsCertify("Please check the box that you are older than 20 years old");
        }
        if(UserList != null) {
           for(User u : UserList) {
              if(u.getUsername().equals(username)) {
                  throw new MyException("Username has been taken already.");
              }
           }
        }
        if(username.isBlank() || password.isBlank()) {
            throw new MyException("Username and Password cannot be empty.");
        } else if(username.contains(" ") || password.contains(" ")) {
            throw new MyException("Username and Password cannot contain space.");
        } else if(username.length() < 5 || password.length() < 5) {
            throw new MyException("Username and Password length must be greater than 5 characters.");
        } else if(username.length() > 10) {
            throw new MyException("Username cannot exceed 10 characters");
        }
        else if(!password.equals(repassword)) {
            throw new MyException("Password doesn't match.");
        }

        User newUser = new User(username, password);
        UserList.add(newUser);

        /* write to file */
        try{
            PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
            write.println(newUser.getUsername() + " " + newUser.getPassword() + " " + newUser.getMoney() + " " + newUser.getCredits());
            write.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        return UserList.size() - 1;
    }
}
