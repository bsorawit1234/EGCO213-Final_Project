package Project3_6513122;

// Sorawit Phattharakundilok    6513122
// Thinnaphat Phumphotingam     6513166
// Wish Semangern               6513175
// Napasrapee Satittham         6513012

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class MainApplication extends JFrame implements ActionListener {
    private JLabel drawpane;
    private JPanel contentPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int playheight = MyConstants.PLAYHEIGHT;
    private int playwidth = MyConstants.PLAYWIDTH;
    private int htpwidth = MyConstants.HTPWIDTH;
    private int htpheight = MyConstants.HTPHEIGHT;
    private JButton btn_play, btn_htp, btn_setting;
    private MySoundEffect theme_song;
    private String theme_path;
    private boolean isSongPlaying;
    private int sliderValue;
    private float soundVoulume;
    private ArrayList<User> UserList = new ArrayList<User>();


    public static void main(String[] args) {
        new MainApplication();
    }

    public MainApplication() {
        if ((new File(MyConstants.SHEET).exists())) constructsList();

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

    public void addComponents() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_HOME).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        theme_path = MyConstants.SONG4;
        soundVoulume = 0.01f;
        theme_song = new MySoundEffect(theme_path);
        theme_song.playLoop();
        theme_song.setVolume(soundVoulume); //min 0.00 + F - max 0.05 + F
        sliderValue = 50;

        isSongPlaying = true;

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
        btn_play.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_play.addActionListener(this);

        MyImageIcon htpBUTTON = new MyImageIcon(MyConstants.HOWTOPLAY_ICON).resize(htpwidth, htpheight);
        btn_htp = new JButton(htpBUTTON);
        btn_htp.setBounds(380, 580, htpwidth, htpheight);
        btn_htp.setBorderPainted(false);
        btn_htp.setContentAreaFilled(false);
        btn_htp.setFocusPainted(false);
        btn_htp.setOpaque(false);
        btn_htp.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_htp.addActionListener(this);

        MyImageIcon settingBUTTON = new MyImageIcon(MyConstants.SETTINGBUTTON).resize(150, 150);
        btn_setting = new JButton(settingBUTTON);
        btn_setting.setBounds(1170, 20, 150, 150);
        btn_setting.setBorderPainted(false);
        btn_setting.setContentAreaFilled(false);
        btn_setting.setFocusPainted(false);
        btn_setting.setOpaque(false);
        btn_setting.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_setting.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                theme_song.stop();
            }
        });

        buttonPanel.add(btn_htp);
        buttonPanel.add(btn_play);
        buttonPanel.add(btn_setting);

        drawpane.add(buttonPanel);
        contentPane.add(drawpane);

        setContentPane(contentPane);

        validate();
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_play) {
            LoginFrame loginFrame = new LoginFrame(this, UserList);
            loginFrame.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btn_htp) {
            HowtoplayFrame howToPlayFrame = new HowtoplayFrame(this);
            howToPlayFrame.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btn_setting) {
            SettingFrame settingFrame = new SettingFrame(this);
            settingFrame.setVisible(true);
            settingFrame.setIsMainFrame(true);
            settingFrame.setIsUserFrame(false);
            this.setVisible(false);
        }
    }

    public MySoundEffect getTheme_song() {
        return theme_song;
    }

    public void setTheme_song(MySoundEffect theme_song) {
        this.theme_song = theme_song;
    }

    public void setTheme_path(String theme_path) {
        this.theme_path = theme_path;
    }

    public void setVolumeTheme_song(float gain) {
        this.theme_song.setVolume(gain);
    }

    public void setIsSongPlaying(boolean value) {
        this.isSongPlaying = value;
    }

    public boolean IsSongPlaying() {
        return this.IsSongPlaying();
    }

    public void setSoundVoulume(float soundVoulume) {
        this.soundVoulume = soundVoulume;
    }

    public void setSliderValue(int sliderValue) {
        this.sliderValue = sliderValue;
    }

    public int getSliderValue() {
        return sliderValue;
    }
}


