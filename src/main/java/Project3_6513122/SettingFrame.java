package Project3_6513122;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Set;

public class SettingFrame extends JFrame {
    private final String[]  playlist = {"former", "funk-casino", "funk-it", "jazz-club", "upbeat-funky-pop"};
    private MainApplication mainFrame;
    private UserFrame       userFrame;
    private JPanel          contentpane;
    private JLabel          drawpane;
    private JButton         btn_back;
    private JComboBox       playlist_box;
    private JCheckBox       on_off;
    private int             framewidth = MyConstants.FRAMEWIDTH;
    private int             frameheight = MyConstants.FRAMEHEIGHT;
    private float           volume;
    private boolean         isUserFrame, isMainFrame;

    public SettingFrame(MainApplication pf) {
        mainFrame = pf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Setting");


        Addinput(this);
    }

    public SettingFrame(MainApplication pf, UserFrame uf) {
        mainFrame = pf;
        userFrame = uf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Login");


        Addinput(this);
    }

    public void Addinput(JFrame frame) {
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.SETTINGPAGE).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        playlist_box = new JComboBox(playlist);
        playlist_box.setBounds(200, 300, 383, 40);
        playlist_box.setSelectedItem(playlist[3]);
        playlist_box.setEditable(false);
        playlist_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getTheme_song().stop();
                mainFrame.setIsSongPlaying(false);
                switch (String.valueOf(playlist_box.getSelectedItem())) {
                    case "former" : {
                        mainFrame.setTheme_song(new MySoundEffect(MyConstants.SONG1));
                        mainFrame.setTheme_path(MyConstants.SONG1);
                    } break;
                    case "funk-casino" : {
                        mainFrame.setTheme_song(new MySoundEffect(MyConstants.SONG2));
                        mainFrame.setTheme_path(MyConstants.SONG2);
                    } break;
                    case "funk-it" : {
                        mainFrame.setTheme_song(new MySoundEffect(MyConstants.SONG3));
                        mainFrame.setTheme_path(MyConstants.SONG3);
                    } break;
                    case "jazz-club" : {
                        mainFrame.setTheme_song(new MySoundEffect(MyConstants.SONG4));
                        mainFrame.setTheme_path(MyConstants.SONG4);
                    } break;
                    case "upbeat-funky-pop" : {
                        mainFrame.setTheme_song(new MySoundEffect(MyConstants.SONG5));
                        mainFrame.setTheme_path(MyConstants.SONG5);
                    } break;
                }
                mainFrame.getTheme_song().playLoop();
                mainFrame.getTheme_song().setVolume(0.01f);
                mainFrame.setIsSongPlaying(true);
            }
        });
        drawpane.add(playlist_box);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setBounds(900, 300, 300, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setBackground(new Color(0, 0, 0, 0));
        slider.setOpaque(false);
        drawpane.add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mainFrame.setSliderValue(slider.getValue());
                volume = (float) (slider.getValue() * 0.00025);
                mainFrame.setVolumeTheme_song(volume);
            }
        });
        slider.setValue(mainFrame.getSliderValue());


        on_off = new JCheckBox();
        on_off.setBounds(1200, 308, 30, 30);
        on_off.setPreferredSize(new Dimension(on_off.getPreferredSize().width, on_off.getPreferredSize().height));
        on_off.setOpaque(false);
        on_off.setBackground(new Color(0, 0, 0, 0));
        on_off.setFocusPainted(false);
        on_off.setForeground(Color.white);
        on_off.setSelected(true);
        drawpane.add(on_off);
        on_off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!on_off.isSelected()) {
                    mainFrame.getTheme_song().stop();
                    mainFrame.setIsSongPlaying(false);
                    playlist_box.setEnabled(false);
                    slider.setEnabled(false);
                } else {
                    mainFrame.getTheme_song().playLoop();
                    mainFrame.setIsSongPlaying(true);
                    playlist_box.setEnabled(true);
                    slider.setEnabled(true);
                }
            }
        });


        MyImageIcon backBUTTON = new MyImageIcon(MyConstants.BACK).resize(169, 74);
        btn_back = new JButton(backBUTTON);
        btn_back.setBounds(30, 630, 169, 74);
        btn_back.setBorderPainted(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setFocusPainted(false);
        btn_back.setOpaque(false);
        btn_back.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMainFrame) mainFrame.setVisible(true);
                else if(isUserFrame) userFrame.setVisible(true);
                dispose();
            }
        });
        drawpane.add(btn_back);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btn_back.doClick();
                }
            }
        });
        frame.setFocusable(true);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public void setIsMainFrame(boolean value) {
        isMainFrame = value;
    }
    public void setIsUserFrame(boolean value) {
        isUserFrame = value;
    }
}
