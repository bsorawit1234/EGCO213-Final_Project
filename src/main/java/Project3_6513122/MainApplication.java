package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class MainApplication extends JFrame implements ActionListener {
    //private JLabel contentpane;
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
        constructsList();

        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("SLOTTY69 🎰");
        this.setResizable(true);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(MyConstants.BG_HOME).getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(null); // Or use a layout manager for components
        setContentPane(contentPane);

        addComponents();
    }

    public void resizeButtons() {
        // Calculate new button sizes based on the frame's current size
        int newButtonWidth = (int) (getWidth() * 0.2); // 20% of the frame width
        int newButtonHeight = (int) (getHeight() * 0.1); // 10% of the frame height

        // Set the new size for the buttons
        btn_play.setPreferredSize(new Dimension(newButtonWidth, newButtonHeight));
        btn_htp.setPreferredSize(new Dimension(newButtonWidth, newButtonHeight));

        // Repaint the content pane to reflect the changes
        contentPane.revalidate();
        contentPane.repaint();
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
    
    public void addComponents() {

        contentPane.setLayout(new BorderLayout());

        JPanel headPanel = new JPanel(new GridBagLayout());
        headPanel.setOpaque(false);

        MyImageIcon headICON = new MyImageIcon(MyConstants.HEAD_ICON).resize(423, 201);
        JLabel head = new JLabel(headICON);
        //head.setBounds(470, 0, 423, 201);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        headPanel.add(head, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        buttonPanel.setOpaque(false);

        MyImageIcon playBUTTON = new MyImageIcon(MyConstants.PLAY_ICON).resize(playwidth, playheight);
        btn_play = new JButton(playBUTTON);
        //btn_play.setBounds(900, 280, playwidth, playheight);
        btn_play.setBorderPainted(false);
        btn_play.setContentAreaFilled(false);
        btn_play.setFocusPainted(false);
        btn_play.setOpaque(false);
        btn_play.addActionListener(this);
        buttonPanel.add(btn_play);

        MyImageIcon htpBUTTON = new MyImageIcon(MyConstants.HOWTOPLAY_ICON).resize(htpwidth, htpheight);
        btn_htp = new JButton(htpBUTTON);
        //btn_htp.setBounds(380, 570,htpwidth , htpheight);
        btn_htp.setBorderPainted(false);
        btn_htp.setContentAreaFilled(false);
        btn_htp.setFocusPainted(false);
        btn_htp.setOpaque(false);
        btn_htp.addActionListener(this);
        buttonPanel.add(btn_htp);

        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0;
        gbcButtonPanel.gridy = 1;
        gbcButtonPanel.anchor = GridBagConstraints.CENTER;
        //gbcButtonPanel.insets = new Insets(70, 0, 0, 0);
        //gbcButtonPanel.insets = new Insets((int) (frameheight * 0.1), 0, 0, 0);

        headPanel.add(buttonPanel, gbcButtonPanel);
        contentPane.add(headPanel, BorderLayout.NORTH);

        contentPane.validate();
        contentPane.repaint();
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
            LoginFrame loginFrame = new LoginFrame(this, UserList);
            loginFrame.setVisible(true);
//            RegisterFrame registerFrame = new RegisterFrame(this, UserList);
//            registerFrame.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btn_htp) {
            HowtoplayFrame howToPlayFrame = new HowtoplayFrame();
            howToPlayFrame.setVisible(true);
            this.setVisible(false);
        }
        // Add more conditions for other buttons as needed (e.g., registration button)
    }
}

