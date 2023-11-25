package Project3_6513122;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SlotFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int spinCountBtn = 0;
    private ArrayList<SlotLabel> slotty = new ArrayList<SlotLabel>();

    public SlotFrame(JFrame pf) {
        ParentFrame = pf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SLOT FRAME");
        this.setLayout(null);

        contentpane = (JPanel) getContentPane();
        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.PATH + "BG_HOME.jpeg").resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setLayout(null);

        int x = 0, y = 100;
        for(int i = 0; i < 3; i++) {
            x = 325;
            for(int j = 0; j < 3; j++) {
                SlotLabel s = new SlotLabel(x, y);
                slotty.add(s);
                add(s);
                x += 125;
            }
            y += 125;
        }

        String[] spin_text = {"SPIN", "STOP"}; // Wating for img button spin, stop
        JButton spin_btn = new JButton(spin_text[0]);
        spin_btn.setBounds(x + 50, y, 50, 50);

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

        JLabel balance = new JLabel("BALANCE: ");
        balance.setBounds(0, 800, 200, 200);
        balance.setVisible(true);
        balance.validate();
        balance.repaint();

        JLabel bet = new JLabel("BET: ");
        bet.setBounds(0, 800, 200, 200);
        bet.setVisible(true);
        bet.validate();
        balance.repaint();


        contentpane.add(spin_btn);
        add(balance);
        add(bet);
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
