package Project3_6513122;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SlotFrame extends JFrame {
    private JFrame ParentFrame;
    private JPanel contentpane;
    private JLabel drawpane;
    private int framewidth = MyConstants.FRAMEWIDTH;
    private int frameheight = MyConstants.FRAMEHEIGHT;
    Timer timer;

    public SlotFrame(JFrame pf) {
        ParentFrame = pf;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SLOT FRAME");
        this.setLayout(null);

        contentpane = (JPanel) getContentPane();
        ArrayList<MyImageIcon> slot_image = new ArrayList<MyImageIcon>();
        ArrayList<ArrayList<JLabel>> slot = new ArrayList<ArrayList<JLabel>>();

        for(int i = 0; i < 10; i++) {
            slot_image.add(new MyImageIcon(MyConstants.SLOT_CARD + i + ".jpg").resize(100, 100));
        }

        for(int i = 0; i < 3; i++) {
            ArrayList<JLabel> slot_row = new ArrayList<JLabel>();
            for (int j = 0; j < 3; j++) {
                slot_row.add(new JLabel());
            }
            slot.add(slot_row);
        }

        int x = 0, y = 100;
        for (int i = 0; i < 3; i++) {
            x = 325;
            for (int j = 0; j < 3; j++) {
                JLabel s = slot.get(i).get(j);
                int randomIndex = (int)(Math.random()*10);
                s.setIcon(slot_image.get(randomIndex));
                s.setBounds(x, y, 100, 100);
                s.setVisible(true);
                contentpane.add(s);
                s.validate();
                s.repaint();
                x += 125;
            }
            y += 125;
        }

        JButton lever = new JButton("Lever");
        lever.setBounds(x + 50, y, 50, 50);
        lever.addActionListener(e -> {
            for(int i = 0; i < 10; i++){
                Timer t = new Timer(150, e1 -> {
                    for (ArrayList<JLabel> row : slot) {
                        for (JLabel s : row) {
                            int random = (int)(Math.random()*10);
                            s.setIcon(slot_image.get(random));
                        }
                    }
                });
                t.start();
            }
        });

        contentpane.add(lever);
    }


}
