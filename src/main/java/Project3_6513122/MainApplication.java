package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication extends JFrame implements ActionListener {
    private JLabel contentpane;

    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;

    JButton btn_home;

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

        JLabel head = new JLabel("SLOTTY69");
        head.setBounds(400, 0, 250, 250);
        head.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        head.setForeground(Color.WHITE);

        btn_home = new JButton("PLAY");
        btn_home.setBounds(400, 200, 150, 150);
        btn_home.addActionListener(this);

        contentpane.add(head);
        contentpane.add(btn_home);

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

    private void login_page() {
        newFrame("login");

    }


    private void register_page() {
        newFrame("regis");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_home) {
            login_page();
        }
    }
}
