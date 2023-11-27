package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class MainApplication extends JFrame implements ActionListener{
    private JLabel drawpane;
    private JPanel contentPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int playheight = MyConstants.PLAYHEIGHT;
    private int playwidth = MyConstants.PLAYWIDTH;
    private int htpwidth = MyConstants.HTPWIDTH;
    private int htpheight = MyConstants.HTPHEIGHT;
    private JButton btn_play, btn_htp;
    private ArrayList<User> UserList = new ArrayList<User>();

    public static void main(String[] args) { new MainApplication(); }

    public MainApplication() {
        if((new File(MyConstants.SHEET).exists())) constructsList();

        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("SLOTTY69 🎰");
        this.setResizable(true);

        addComponents();
    }

    private void constructsList() {
        //construct ArrayList
            try {
                Scanner scan = new Scanner(new FileReader(MyConstants.SHEET));
                String line;
                String[] col;
                User user;

                while (scan.hasNext()) {
                    line = scan.nextLine();
                    col = line.split(" ");
                    user = new User(col[0], col[1]);
                    user.setMoney(Integer.parseInt(col[2]));
                    user.setCredits(Integer.parseInt(col[3]));
                    UserList.add(user);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
    }

        public void addComponents () {
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(null);

            drawpane = new JLabel();
            MyImageIcon background = new MyImageIcon(MyConstants.BG_HOME).resize(framewidth, frameheight);
            drawpane.setIcon(background);
            drawpane.setBounds(0, 0, framewidth, frameheight);
            drawpane.setLayout(null);

            MyImageIcon headICON = new MyImageIcon(MyConstants.HEAD_ICON).resize(423, 201);
            JLabel head = new JLabel(headICON);
            head.setBounds(470, 0, 423, 201);
            contentPane.add(head);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);
            buttonPanel.setBounds(0, 0, framewidth, frameheight); // Adjust these bounds as needed
            buttonPanel.setLayout(null);

            MyImageIcon playBUTTON = new MyImageIcon(MyConstants.PLAY_ICON).resize(playwidth, playheight);
            btn_play = new JButton(playBUTTON);
            btn_play.setBounds(380, 290, playwidth, playheight);
            btn_play.setBorderPainted(false);
            btn_play.setContentAreaFilled(false);
            btn_play.setFocusPainted(false);
            btn_play.setOpaque(false);
            btn_play.addActionListener(this);

            MyImageIcon htpBUTTON = new MyImageIcon(MyConstants.HOWTOPLAY_ICON).resize(htpwidth, htpheight);
            btn_htp = new JButton(htpBUTTON);
            btn_htp.setBounds(380, 580, htpwidth, htpheight);
            btn_htp.setBorderPainted(false);
            btn_htp.setContentAreaFilled(false);
            btn_htp.setFocusPainted(false);
            btn_htp.setOpaque(false);
            btn_htp.addActionListener(this);

            buttonPanel.add(btn_htp);
            buttonPanel.add(btn_play);

            drawpane.add(buttonPanel);
            contentPane.add(drawpane);

            setContentPane(contentPane);

            validate();
            repaint();
        }

//        private void login_page () {
//            JFrame newFrame = new JFrame("Login");
//            newFrame.setSize(framewidth, frameheight);
//            newFrame.setLocationRelativeTo(null);
//            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            newFrame.setResizable(false);
//
//            JLabel label = new JLabel("This is a new frame!");
//            label.setHorizontalAlignment(SwingConstants.CENTER);
//            label.setFont(new Font("Arial", Font.PLAIN, 24));
//            newFrame.add(label);
//
//            newFrame.setVisible(true);
//        }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == btn_play) {
                LoginFrame loginFrame = new LoginFrame(this, UserList);
                loginFrame.setVisible(true);
                this.setVisible(false);
//            RegisterFrame registerFrame = new RegisterFrame(this, UserList);
//            registerFrame.setVisible(true);
//            new SlotFrame(this).setVisible(true);
//            this.setVisible(false);
            } else if (e.getSource() == btn_htp) {
                HowtoplayFrame howToPlayFrame = new HowtoplayFrame();
                howToPlayFrame.setVisible(true);
                this.setVisible(false);
            }
        }
}


