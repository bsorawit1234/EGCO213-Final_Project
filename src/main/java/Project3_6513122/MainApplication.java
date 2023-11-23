package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication extends JFrame implements ActionListener {
    private JLabel contentpane;

    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int playheight = MyConstants.PLAYHEIGHT;
    private int playwidth = MyConstants.PLAYWIDTH;

    private JButton btn_play, btn_htp;

    public static void main(String[] args) { new MainApplication(); }

    public MainApplication() {
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

        addComponents();
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
        JLabel head = new JLabel("SLOTTY69");
        head.setBounds(380, 0, 250, 250);
        head.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        head.setForeground(Color.WHITE);

        MyImageIcon playBUTTON = new MyImageIcon(MyConstants.PLAY_ICON).resize(playwidth, playheight);
        btn_play = new JButton(playBUTTON);
        btn_play.setBounds(300, 200, 400, 100);
        btn_play.addActionListener(this);

        btn_htp = new JButton("HOW TO PLAY");
        btn_htp.setFont(new Font("Trend Sans One", Font.PLAIN, 50));
        btn_htp.setBounds(300, 400,400 , 50);
        btn_htp.setForeground(Color.gray);
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
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btn_htp) {
            HowtoplayFrame howToPlayFrame = new HowtoplayFrame();
            howToPlayFrame.setVisible(true);
            this.setVisible(false);
        }
        // Add more conditions for other buttons as needed (e.g., registration button)
    }
}

