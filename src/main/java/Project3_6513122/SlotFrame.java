package Project3_6513122;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class SlotFrame extends JFrame {
    private JTextArea betINPUT;
    private JFrame mainFrame, ParentFrame;
    private JPanel contentpane;
    private JPanel drawpane;
    private JTextField balanceDISPLAY;
    private JLayeredPane layeredPane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    private int spinCountBtn = 0;
    private ArrayList<SlotLabel> slotty = new ArrayList<SlotLabel>();
    private ArrayList<User> UserList;
    private User user;
    private int index;
    private static final CountDownLatch latch = new CountDownLatch(9);

    public SlotFrame(JFrame pf, int id, ArrayList<User> ul, JFrame mf) {
        ParentFrame = pf;
        index = id;
        UserList = ul;
        mainFrame = mf;
        user = UserList.get(index);
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

        MyImageIcon background = new MyImageIcon(MyConstants.SLOT_MAINBG).resize(framewidth, frameheight);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, framewidth, frameheight);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));


        int x = 0, y = 100;
        for(int i = 0; i < 3; i++) {
            x = 400;
            for(int j = 0; j < 3; j++) {
                SlotLabel s = new SlotLabel(x, y, (int)(Math.random() * 10));
//                SlotLabel s = new SlotLabel(x, y, 3);
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

        MyImageIcon backBUTTON = new MyImageIcon(MyConstants.BACK).resize(169, 74);
        JButton btn_back = new JButton(backBUTTON);
        btn_back.setBounds(30, 630,169 , 74);
        btn_back.setBorderPainted(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setFocusPainted(false);
        btn_back.setOpaque(false);

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserFrame userFrame = new UserFrame(mainFrame, index, UserList);
                userFrame.setVisible(true);
                dispose();
            }
        });
        layeredPane.add(btn_back, Integer.valueOf(2));

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
        balanceDISPLAY.setText(String.valueOf(user.getCredits()));
        layeredPane.add(balanceDISPLAY, Integer.valueOf(2));

        betINPUT = new JTextArea();
        betINPUT.setFont(new Font("SanSerif", Font.BOLD, 30));
        betINPUT.setBounds(1025, 350, 200, 50); // Adjust position and size as needed
        betINPUT.setEditable(true);
        betINPUT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char enteredChar = e.getKeyChar();
                String enteredText = betINPUT.getText() + e.getKeyChar();

                if(!Character.isDigit(enteredChar)) {
                    e.consume(); // prevent event if the key that user has typed is not a number.
                }

                try {
                    int enteredNum = Integer.parseInt(enteredText);
                    if(enteredNum > user.getCredits()) {
                        e.consume();
                    } else {
                        user.setBet(enteredNum);
                    }
                } catch (NumberFormatException err) {
                    e.consume();
                }
            }
        });

        spin_btn.addActionListener(e -> {
            if(spinCountBtn % 2 == 0) {
                for(SlotLabel s: slotty) {
                    Thread slotThread = new Thread() {
                        public void run() {
                            s.setSpin(true);
                            while (s.isSpin()) s.spin();
                            latch.countDown();
                        }
                    };
                    slotThread.start();
                }
            } else {
                for(SlotLabel s: slotty) {
                    s.setSpin(false);
                }
            }

            spinCountBtn++;
            spin_btn.setText(spin_text[spinCountBtn % 2]);

            if(spinCountBtn != 0 && spinCountBtn % 2 == 0) {
                try {
                    latch.await();
                    check_slot();
                } catch (InterruptedException err) {
                    System.err.println(err);
                }
            } else if(spinCountBtn % 2 != 0) {
                if(user.getBet() <= user.getCredits()) {
                    user.setCredits(user.getCredits() - user.getBet());
                }
                betINPUT.setText(null);
            }

            balanceDISPLAY.setText(String.valueOf(user.getCredits()));
        });

        try {
            Files.delete(Paths.get(MyConstants.SHEET));
            PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
            for (User u : UserList)
                write.println(u.getUsername() + " " + u.getPassword() + " " + u.getMoney() + " " + u.getCredits());
            write.close();
        } catch (Exception er) {
            System.err.println(er);
        }

        layeredPane.add(betINPUT, Integer.valueOf(2));
        contentpane.add(layeredPane, BorderLayout.CENTER);
        validate();
        repaint();
    }

    public void check_slot() {
        int credits = user.getCredits();
        int bet = user.getBet();

        System.out.println("\nBET: " + bet);
        for(int i = 0; i < 9; i++) {
            if(i % 3 == 0 && i != 0) System.out.println();
            System.out.printf("%3d", slotty.get(i).getID());
        }

        boolean bigWin = true;
        for(int i = 1; i < slotty.size(); i++) {
            if (slotty.get(0).getID() != slotty.get(i).getID()) {
                bigWin = false;
                break;
            }
        }

        if(bigWin) {
            user.setCredits(credits + (bet * 100));
        } else {
            // Diagonal
            if (slotty.get(0).getID() >= 10 && slotty.get(4).getID() >= 10 && slotty.get(8).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(0).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(8).getID()) {
                credits += bet * 3;
            } else if(slotty.get(0).getID() == slotty.get(8).getID()) {
                credits += bet * 2;
            }

            if (slotty.get(2).getID() >= 10 && slotty.get(4).getID() >= 10 && slotty.get(6).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(2).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(6).getID()) {
                credits += bet * 3;
            } else if(slotty.get(2).getID() == slotty.get(6).getID()) {
                credits += bet * 2;
            }

            // Horizontal
            if (slotty.get(0).getID() >= 10 && slotty.get(1).getID() >= 10 && slotty.get(2).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(0).getID() == slotty.get(1).getID() && slotty.get(1).getID() == slotty.get(2).getID()) {
                credits += bet * 3;
            } else if(slotty.get(0).getID() == slotty.get(2).getID()) {
                credits += bet * 2;
            }

            if (slotty.get(3).getID() >= 10 && slotty.get(4).getID() >= 10 && slotty.get(5).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(3).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(5).getID()) {
                credits += bet * 3;
            } else if(slotty.get(3).getID() == slotty.get(5).getID()) {
                credits += bet * 2;
            }

            if (slotty.get(6).getID() >= 10 && slotty.get(7).getID() >= 10 && slotty.get(8).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(6).getID() == slotty.get(7).getID() && slotty.get(7).getID() == slotty.get(8).getID()) {
                credits += bet * 3;
            } else if(slotty.get(6).getID() == slotty.get(8).getID()) {
                credits += bet * 2;
            }

            // Vertical
            if (slotty.get(0).getID() >= 10 && slotty.get(3).getID() >= 10 && slotty.get(6).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(0).getID() == slotty.get(3).getID() && slotty.get(3).getID() == slotty.get(6).getID()) {
                credits += bet * 3;
            } else if (slotty.get(0).getID() == slotty.get(6).getID()) {
                credits += bet * 2;
            }

            if (slotty.get(1).getID() >= 10 && slotty.get(4).getID() >= 10 && slotty.get(7).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(1).getID() == slotty.get(4).getID() && slotty.get(4).getID() == slotty.get(7).getID()) {
                credits += bet * 3;
            } else if(slotty.get(1).getID() == slotty.get(7).getID()) {
                credits += bet * 2;
            }

            if (slotty.get(2).getID() >= 10 && slotty.get(5).getID() >= 10 && slotty.get(8).getID() >= 10) {
                credits += bet * 3;
            } else if (slotty.get(2).getID() == slotty.get(5).getID() && slotty.get(5).getID() == slotty.get(8).getID()) {
                credits += bet * 3;
            } else if(slotty.get(2).getID() == slotty.get(8).getID()) {
                credits += bet * 2;
            }
        }
        user.setCredits(credits);
        user.setBet(0);


    }
}

class SlotLabel extends JLabel {
    private int width = 100, height = 100, curX, curY;
    private boolean spin = false;
    private int ID;
    public SlotLabel(int x, int y, int id) {
        curX = x;
        curY = y;
        ID = id; // NO FACE
        MyImageIcon startImg = new MyImageIcon(MyConstants.SLOT_CARD + ID + ".png").resize(width, height);
        setIcon(startImg);
        setBounds(curX, curY, width, height);
        setVisible(true);
        validate();
        repaint();
    }
    public int getID() { return ID; }
    public boolean isSpin() { return spin; }
    public void setSpin(boolean s) { spin = s; }
    public void spin() {
        ID = (int)(Math.random() * 14);
        MyImageIcon img = new MyImageIcon(MyConstants.SLOT_CARD + ID + ".png").resize(width, height);
        setIcon(img);
        repaint();

        try { Thread.sleep(100); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}


