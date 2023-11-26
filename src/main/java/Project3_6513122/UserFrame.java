package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserFrame extends JFrame {
    private JFrame mainFrame;
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private JButton         btn_play, btn_deposit, btn_withdraw, btn_logout, btn_back;
    private ArrayList<User> UserList;
    private int index;
    public UserFrame(JFrame pf, int id, ArrayList<User> ul) {
        mainFrame = pf;
        index = id;
        UserList = ul;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("User Page");

        Addinput(this);
    }

    public void Addinput(JFrame userFrame) {
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        MyImageIcon usernameICON = new MyImageIcon(MyConstants.USERNAMESHOW).resize(369, 40);
        JLabel label1 = new JLabel(usernameICON);
        MyImageIcon creditICON = new MyImageIcon(MyConstants.CREDITSHOW).resize(260, 40);
        JLabel label2 = new JLabel(creditICON);
        MyImageIcon moneyICON = new MyImageIcon(MyConstants.MONEYSHOW).resize(260, 40);
        JLabel label3 = new JLabel(moneyICON);

        JLabel username = new JLabel(UserList.get(index).getUsername());
        JLabel money = new JLabel(String.valueOf(UserList.get(index).getMoney()));
        JLabel credits = new JLabel(String.valueOf(UserList.get(index).getCredits()));

        label1.setBounds(30, 30, 369, 40);
        label1.setIcon(usernameICON);
        label1.setVisible(true);
        label3.setBounds(30, 100, 260, 40);
        label3.setIcon(creditICON);
        label3.setVisible(true);
        label2.setBounds(30, 170, 260, 40);
        label2.setIcon(moneyICON);
        label2.setVisible(true);

        username.setVisible(true);
        username.setBounds(200, 0, 369, 40);
        username.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        username.setForeground(Color.white);

        money.setVisible(true);
        money.setBounds(140, 0, 260, 40);
        money.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        money.setForeground(Color.white);

        credits.setVisible(true);
        credits.setBounds(150, 0, 260, 40);
        credits.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        credits.setForeground(Color.white);

        label1.add(username);
        label2.add(money);
        label3.add(credits);

        MyImageIcon playBUTTON = new MyImageIcon(MyConstants.PLAY_ICON2).resize(193, 70);
        btn_play = new JButton(playBUTTON);
        btn_play.setBounds(550, 220,193, 70);
        btn_play.setBorderPainted(false);
        btn_play.setContentAreaFilled(false);
        btn_play.setFocusPainted(false);
        btn_play.setOpaque(false);
        btn_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* SLOT_FRAME */
                SlotFrame slotFrame = new SlotFrame(userFrame, index, UserList, mainFrame);
                slotFrame.setVisible(true);

                dispose();
            }
        });
        drawpane.add(btn_play);

        MyImageIcon depositICON = new MyImageIcon(MyConstants.DEPOSIT2).resize(393, 98);
        btn_deposit = new JButton(depositICON);
        btn_deposit.setBounds(440, 400,393, 98);
        btn_deposit.setBorderPainted(false);
        btn_deposit.setContentAreaFilled(false);
        btn_deposit.setFocusPainted(false);
        btn_deposit.setOpaque(false);
        btn_deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositFrame depositFrame = new DepositFrame(mainFrame, index, UserList, "Deposit");
                depositFrame.setVisible(true);
                userFrame.dispose();
            }
        });
        drawpane.add(btn_deposit);

        MyImageIcon withdrawICON = new MyImageIcon(MyConstants.WITHDRAW2).resize(457, 98);
        btn_withdraw = new JButton(withdrawICON);
        btn_withdraw.setBounds(410, 300,457, 98);
        btn_withdraw.setBorderPainted(false);
        btn_withdraw.setContentAreaFilled(false);
        btn_withdraw.setFocusPainted(false);
        btn_withdraw.setOpaque(false);
        btn_withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawFrame withdrawFrame = new WithdrawFrame(mainFrame, index, UserList, "Withdraw");
                withdrawFrame.setVisible(true);
                userFrame.dispose();
            }
        });
        drawpane.add(btn_withdraw);

        MyImageIcon logoutICON = new MyImageIcon(MyConstants.LOGOUT2).resize(315, 84);
        btn_logout = new JButton(logoutICON);
        btn_logout.setBounds(490, 500,315, 84);
        btn_logout.setBorderPainted(false);
        btn_logout.setContentAreaFilled(false);
        btn_logout.setFocusPainted(false);
        btn_logout.setOpaque(false);
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* overwrite file with new UserList information */
                try{
                    Files.delete(Paths.get(MyConstants.SHEET));
                    PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
                    for(User u : UserList) {
                        write.println(u.getUsername() + " " + u.getPassword() + " " + u.getMoney() + " " + u.getCredits());
                    }
                    write.close();
                } catch (Exception er) {
                    System.err.println(er);
                }

                mainFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_logout);

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
                MainApplication mainApplication = new MainApplication();
                mainApplication.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_back);

        drawpane.add(label1);
        drawpane.add(label2);
        drawpane.add(label3);
        contentpane.add(drawpane);
        repaint();
        validate();
    }
}
