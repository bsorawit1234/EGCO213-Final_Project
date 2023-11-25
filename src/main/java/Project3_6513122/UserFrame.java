package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JButton         btn_play, btn_deposit, btn_withdraw, btn_logout;
    private ArrayList<User> UserList;
    private User user;
    public UserFrame(JFrame pf, User us, ArrayList<User> ul) {
        ParentFrame = pf;
        user = us;
        UserList = ul;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("User Page");

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
        JLabel username = new JLabel("Username : " + user.getUsername());
        JLabel money = new JLabel("Money       : " + user.getMoney());
        JLabel credits = new JLabel("Credits      : " + user.getCredits());
        label.setBounds(380, 50,500 , 500);
        label.setIcon(Orange);
        label.setVisible(true);

        username.setVisible(true);
        username.setBounds(20, 30, 300, 100);
        username.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        username.setForeground(Color.white);

        money.setVisible(true);
        money.setBounds(20, 60, 200, 100);
        money.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        money.setForeground(Color.white);

        credits.setVisible(true);
        credits.setBounds(20, 90, 200, 100);
        credits.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        credits.setForeground(Color.white);

        label.add(username);
        label.add(money);
        label.add(credits);

        btn_play = new JButton("Play");
        btn_play.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_play.setBounds(380, 250,400 , 50);
        btn_play.setForeground(Color.gray);
        btn_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlotFrame slotFrame = new SlotFrame(ParentFrame, user, UserList);
                slotFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_play);

        btn_deposit = new JButton("DEPOSIT");
        btn_deposit.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_deposit.setBounds(380, 320,400 , 50);
        btn_deposit.setForeground(Color.gray);
        btn_deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        drawpane.add(btn_deposit);

        btn_withdraw = new JButton("WITHDRAW");
        btn_withdraw.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_withdraw.setBounds(380, 390,400 , 50);
        btn_withdraw.setForeground(Color.gray);
        btn_withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        drawpane.add(btn_withdraw);

        btn_logout = new JButton("LOGOUT");
        btn_logout.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_logout.setBounds(380, 460,400 , 50);
        btn_logout.setForeground(Color.gray);
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* overwrite file with new UserList information */
                try{
                    PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
                    for(User u : UserList) {
                        write.println(u.getUsername() + " " + u.getPassword() + " " + u.getMoney() + " " + u.getCredits());
                    }
                    write.close();
                } catch (Exception er) {
                    System.err.println(er);
                }

                ParentFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_logout);

        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }
}
