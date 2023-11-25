package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SlotFrame extends JFrame {
    private JTextArea betINPUT;
    private JFrame ParentFrame;
    private JPanel contentpane;
    private JPanel drawpane;
    private JTextField balanceDISPLAY;
    private JLayeredPane layeredPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int spinCountBtn = 0;
    private ArrayList<SlotLabel> slotty = new ArrayList<SlotLabel>();

    public SlotFrame(JFrame pf) {
        ParentFrame = pf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("SLOT FRAME");
        //this.setLayout(null);

        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(framewidth, frameheight));

        drawpane = new JPanel();
        drawpane.setBounds(0, 0, framewidth, frameheight);
        //drawpane.setLayout(new GridLayout(3, 3));

        MyImageIcon background = new MyImageIcon(MyConstants.SLOT_MAINBG).resize(framewidth, frameheight);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, framewidth, frameheight);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        //drawpane.add(backgroundLabel);


        int x = 0, y = 100;
        for(int i = 0; i < 3; i++) {
            x = 400;
            for(int j = 0; j < 3; j++) {
                SlotLabel s = new SlotLabel(x, y);
                slotty.add(s);
                layeredPane.add(s, Integer.valueOf(1));
                x += 200;
            }
            y += 200;
        }


        String[] spin_text = {"SPIN", "STOP"}; // Wating for img button spin, stop
        JButton spin_btn = new JButton(spin_text[0]);
        spin_btn.setBounds(x + 50, 500, 100, 100);
        layeredPane.add(spin_btn, Integer.valueOf(2));

        spin_btn.addActionListener(e -> {
            for(SlotLabel s: slotty) {
                if(spinCountBtn % 2 == 0) {
                    Thread slotThread = new Thread() {
                        public void run() {
                            s.setSpin(true);
                            while(s.isSpin()) s.spin();
                        }
                    };
                    slotThread.start();
                } else {
                    s.setSpin(false);
                }
            }
            spinCountBtn++;
            spin_btn.setText(spin_text[spinCountBtn % 2]);

            if(spinCountBtn != 0 && spinCountBtn % 2 == 0) {
                check_slot(100);
            }
        });

//        JLabel balance = new JLabel("BALANCE: ");
//        balance.setBounds(10, 10, 200, 200);
//        balance.setVisible(true);
//        layeredPane.add(balance, Integer.valueOf(2));

//        JLabel bet = new JLabel("BET: ");
//        bet.setBounds(10, 40, 200, 200);
//        bet.setVisible(true);
//        layeredPane.add(bet, Integer.valueOf(2));

        MyImageIcon balanceICON = new MyImageIcon(MyConstants.BET_ICON).resize(169, 74);
        JLabel balance = new JLabel(balanceICON);
        balance.setBounds(1000, 80, 169, 74);
        layeredPane.add(balance, Integer.valueOf(2));

        MyImageIcon betICON = new MyImageIcon(MyConstants.BET_ICON).resize(169, 74);
        JLabel bet = new JLabel(betICON);
        bet.setBounds(1000, 250, 169, 74);
        layeredPane.add(bet, Integer.valueOf(2));

        balanceDISPLAY = new JTextField();
        balanceDISPLAY.setBounds(1025, 180, 200, 50);
        balanceDISPLAY.setFont(new Font("SanSerif", Font.BOLD, 30));
        balanceDISPLAY.setVisible(true);
        balanceDISPLAY.setEditable(false);
        layeredPane.add(balanceDISPLAY, Integer.valueOf(2));

        betINPUT = new JTextArea();
        betINPUT.setFont(new Font("SanSerif", Font.BOLD, 30));
        betINPUT.setBounds(1025, 350, 200, 50); // Adjust position and size as needed
        betINPUT.setEditable(true);
        layeredPane.add(betINPUT, Integer.valueOf(2));

        contentpane.add(layeredPane, BorderLayout.CENTER);
        validate();
        repaint();

    }



    public void check_slot(float bet) {
        boolean bigWin = true;
        for(int i = 1; i < slotty.size(); i++) {
            if (slotty.get(0).getID() != slotty.get(i).getID()) {
                bigWin = false;
                break;
            }
        }

        if(bigWin) {
            bet *= 20;
        } else {
            // Diagonal
            if (slotty.get(0).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(8).getID()) {
                bet *= 5;
            }
            if (slotty.get(2).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(6).getID()) {
                bet *= 5;
            }
            // Horizontal
            if (slotty.get(0).getID() == slotty.get(1).getID() && slotty.get(1).getID() == slotty.get(2).getID()) {
                bet *= 3;
            }
            if (slotty.get(3).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(5).getID()) {
                bet *= 3;
            }
            if (slotty.get(6).getID() == slotty.get(7).getID() && slotty.get(7).getID() == slotty.get(8).getID()) {
                bet *= 3;
            }
            // Vertical
            if (slotty.get(0).getID() == slotty.get(3).getID() && slotty.get(3).getID() == slotty.get(6).getID()) {
                bet *= 3;
            }
            if (slotty.get(1).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(7).getID()) {
                bet *= 3;
            }
            if (slotty.get(2).getID() == slotty.get(5).getID() && slotty.get(5).getID() == slotty.get(8).getID()) {
                bet *= 3;
            }

        }
    }
}

class SlotLabel extends JLabel {
    private int width = 100, height = 100, curX, curY;
    private boolean spin = false;
    private int ID;
    public SlotLabel(int x, int y) {
        curX = x;
        curY = y;
        ID = (int)(Math.random() * 10);
        MyImageIcon startImg = new MyImageIcon(MyConstants.SLOT_CARD + ID + ".jpg").resize(width, height);
        setIcon(startImg);
        setBounds(curX, curY, width, height);
        setVisible(true);
        validate();
        repaint();
    }
    public int getID() { return ID; }
    public boolean isSpin() { return spin; }
    public void setSpin(boolean s) { spin = s; }
    public void setCorrect(int w, int h) {
        width = w;
        height = h;
        setIcon(new MyImageIcon(MyConstants.SLOT_CARD + ID + ".jpg").resize(width, height));
        setBounds(curX, curY, width, height);
        setVisible(true);
        validate();
        repaint();
    }
    public void spin() {
        ID = (int)(Math.random() * 10);
        for(int i = 0; i < 10; i++) {
            ID = (ID + 1) % 10;
            MyImageIcon img = new MyImageIcon(MyConstants.SLOT_CARD + ID + ".jpg").resize(100, 100);
            setIcon(img);
            repaint();

            try { Thread.sleep(100); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}


