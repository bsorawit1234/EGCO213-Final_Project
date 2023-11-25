package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class MainApplication extends JFrame implements ActionListener {
    private JLabel contentpane;

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
        constructsList();

        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("SLOTTY69 🎰");
        this.setResizable(false);

        // set background image
        setContentPane(contentpane = new JLabel());
        MyImageIcon background = new MyImageIcon(MyConstants.BG_HOME).resize(framewidth, frameheight);
        contentpane.setIcon(background);
        contentpane.setLayout(null);

//        contentpane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 100));

        addComponents();
    }
    private void constructsList() {
        if((new File(MyConstants.PATH)).exists()) {
            //construct ArrayList
            try{
                Scanner scan = new Scanner(new FileReader(MyConstants.SHEET));
                String line;
                String[] col;

                while(scan.hasNext()) {
                    line = scan.nextLine();
                    col = line.split(" ");
                    UserList.add(new User(col[0], col[1]));
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    private void newFrame(String p) {
        JFrame frame = new JFrame();
        frame.setSize(framewidth, frameheight);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
//        frame.setTitle("SLOTTY69");
        frame.setTitle(p); // use for check if it changed page
    }
    
    public void addComponents() {
        MyImageIcon headICON = new MyImageIcon(MyConstants.HEAD_ICON).resize(282, 134);
        JLabel head = new JLabel(headICON);
        head.setBounds(350, 0, 282, 134);

        MyImageIcon playBUTTON = new MyImageIcon(MyConstants.PLAY_ICON).resize(playwidth, playheight);
        btn_play = new JButton(playBUTTON);
        btn_play.setBounds(265, 250, 470, 170);
        btn_play.setBorderPainted(false);
        btn_play.setContentAreaFilled(false);
        btn_play.setFocusPainted(false);
        btn_play.setOpaque(false);
        btn_play.addActionListener(this);

        MyImageIcon htpBUTTON = new MyImageIcon(MyConstants.HOWTOPLAY_ICON).resize(htpwidth, htpheight);
        btn_htp = new JButton(htpBUTTON);
        btn_htp.setBounds(250, 475,500 , 90);
        btn_htp.setBorderPainted(false);
        btn_htp.setContentAreaFilled(false);
        btn_htp.setFocusPainted(false);
        btn_htp.setOpaque(false);
        btn_htp.addActionListener(this);

        contentpane.add(head);
        contentpane.add(btn_htp);
        contentpane.add(btn_play);

        btn_play.validate();
        btn_play.repaint();
        btn_htp.validate();
        btn_htp.repaint();
        head.validate();
        head.repaint();
    }
    
    private void login_page() {
        JFrame newFrame = new JFrame("Login");
        newFrame.setSize(framewidth, frameheight);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setResizable(false);

        // Add components or modify as needed for the new frame
        // For example:
        JLabel label = new JLabel("This is a new frame!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        newFrame.add(label);

        newFrame.setVisible(true);
        //this.setVisible(false); // Hide the current frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_play) {
//            LoginFrame loginFrame = new LoginFrame(this, UserList);
//            loginFrame.setVisible(true);
//            RegisterFrame registerFrame = new RegisterFrame(this, UserList);
//            registerFrame.setVisible(true);
            new SlotFrame(this).setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btn_htp) {
            HowtoplayFrame howToPlayFrame = new HowtoplayFrame();
            howToPlayFrame.setVisible(true);
            this.setVisible(false);
        }
        // Add more conditions for other buttons as needed (e.g., registration button)
    }
}

