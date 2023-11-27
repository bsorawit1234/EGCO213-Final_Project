package Project3_6513122;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DepositWithdrawFrame extends JFrame {

    protected JFrame            mainFrame;
    private JPanel              contentpane, amountContainer;
    private JLabel              drawpane;
    protected JDialog           modalDialog;
    protected JCheckBox         checkBox;
    protected JTextArea         amountField;
    private int                 framewidth = MyConstants.FRAMEWIDTH;
    private int                 frameheight = MyConstants.FRAMEHEIGHT;
    protected int               index;
    private String              whichFrame;
    protected JButton           btn_proceed, btn_submit, btn_back;
    protected ArrayList<User>   UserList;
//    private JLabel ;

    public DepositWithdrawFrame(JFrame pf, int id, ArrayList<User> ul, String which) {
        mainFrame = pf;
        index = id;
        UserList = ul;
        whichFrame = which;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Deposit Page");

        Addinput(this);
    }

    public void Addinput(JFrame frame) {
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(null);

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(framewidth, frameheight);
        drawpane.setIcon(background);
        drawpane.setBounds(0, 0, framewidth, frameheight);
        drawpane.setLayout(null);

        MyImageIcon usernameICON = new MyImageIcon(MyConstants.USERNAMESHOW).resize(369, 40);
        JLabel label1 = new JLabel(usernameICON);
        MyImageIcon creditICON = new MyImageIcon(MyConstants.CREDITSHOW).resize(260, 40);
        JLabel label2 = new JLabel(creditICON);
        MyImageIcon moneyICON = new MyImageIcon(MyConstants.MONEYSHOW).resize(260, 40);
        JLabel label3 = new JLabel(moneyICON);

        JLabel username = new JLabel(UserList.get(index).getUsername());
        JLabel money = new JLabel(String.valueOf(UserList.get(index).getMoney()));
        JLabel credits = new JLabel(String.valueOf(UserList.get(index).getCredits()));

        label1.setBounds(30, 30, 369, 40);
        label1.setIcon(usernameICON);
        label1.setVisible(true);
        label3.setBounds(30, 100, 260, 40);
        label3.setIcon(creditICON);
        label3.setVisible(true);
        label2.setBounds(30, 170, 260, 40);
        label2.setIcon(moneyICON);
        label2.setVisible(true);

        username.setVisible(true);
        username.setBounds(200, 0, 369, 40);
        username.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        username.setForeground(Color.white);

        money.setVisible(true);
        money.setBounds(140, 0, 260, 40);
        money.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        money.setForeground(Color.white);

        credits.setVisible(true);
        credits.setBounds(150, 0, 260, 40);
        credits.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        credits.setForeground(Color.white);

        label1.add(username);
        label2.add(money);
        label3.add(credits);

        JLabel proceed = new JLabel(whichFrame + " Amount");
        proceed.setVisible(true);
        proceed.setBounds(400, 270,400 , 40);
        proceed.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        proceed.setForeground(Color.white);
        drawpane.add(proceed);

        JLabel dollarSign = new JLabel("$");
        dollarSign.setVisible(true);
        dollarSign.setBounds(200, 320,600 , 50);
        dollarSign.setFont(new Font("Trend Sans One", Font.PLAIN, 40));

        amountField = new JTextArea();
        amountField.setFont(new Font("SanSerif", Font.PLAIN, 40));
        amountField.setBounds(200, 320,600 , 50);
        amountField.setEditable(true);
        amountField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        amountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char enteredChar = e.getKeyChar();
                String enteredText = amountField.getText() + e.getKeyChar();

                if(!Character.isDigit(enteredChar)) {
                    e.consume(); // prevent event if the key that user has typed is not a number.
                }
                try {
                    int enteredNum = Integer.parseInt(enteredText);
                    if(whichFrame.equals("Deposit")) {
                        if (enteredNum > UserList.get(index).getMoney()) {
                            e.consume();
                        }
                    } else if(whichFrame.equals("Withdraw")) {
                        if (enteredNum > UserList.get(index).getCredits()) {
                            e.consume();
                        }
                    }
                } catch (NumberFormatException err) {
                    e.consume();
                }
            }
        });

        amountContainer = new JPanel(new BorderLayout());
        amountContainer.add(dollarSign, BorderLayout.WEST);
        amountContainer.add(amountField, BorderLayout.CENTER);
        amountContainer.setVisible(true);
        amountContainer.setBounds(200, 320,600 , 50);
        drawpane.add(amountContainer);

        btn_proceed = new JButton(whichFrame);
        btn_proceed.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_proceed.setBounds(680, 480, 200, 50);
        btn_proceed.setForeground(Color.gray);
        btn_proceed.addActionListener(this::actionPerformed);
        drawpane.add(btn_proceed);

        MyImageIcon backBUTTON = new MyImageIcon(MyConstants.BACK).resize(169, 74);
        btn_back = new JButton(backBUTTON);
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
        drawpane.add(btn_back);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btn_back.doClick();
                } else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn_proceed.doClick();
                }
            }
        });
        frame.setFocusable(true);

        drawpane.add(label1);
        drawpane.add(label2);
        drawpane.add(label3);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    protected void showTableModalDialog(JFrame parentFrame, int amount) {
        modalDialog = new JDialog(parentFrame, "Confirm", true);
        modalDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modalDialog.setResizable(false);

        JPanel popupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Sample data for the table
        Object[][] data = {
                {whichFrame, amount},
        };

        // Column names
        Object[] columnNames = {"Transactions", "Amounts"};

        // Create a DefaultTableModel with data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create a JTable with the DefaultTableModel
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Set the preferred size of the JTable to fit the data
        Dimension tableSize = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(tableSize.width + tableSize.width - 20, table.getRowHeight() * (data.length + 2) - 10));

        popupPanel.add(scrollPane);

        // Add a checkbox
        checkBox = new JCheckBox("accept");
        popupPanel.add(checkBox);

        // Add a Submit button
        btn_submit = new JButton("Submit");
        btn_submit.addActionListener(this::actionPerformed);

        // Add the Submit button to the bottom of the popupPanel
        popupPanel.add(btn_submit);

        modalDialog.getContentPane().add(popupPanel);

        modalDialog.setSize(300, 200);
        modalDialog.setLocationRelativeTo(parentFrame);
        modalDialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
    }

}
