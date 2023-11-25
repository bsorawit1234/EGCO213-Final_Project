package Project3_6513122;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DepositFrame extends JFrame {
    private JFrame          ParentFrame;
    private JPanel          contentpane, amountContainer;
    private JLabel          drawpane;
    private JTextArea       amountField, descriptionField;
    private int             framewidth = MyConstants.FRAMEWIDTH;
    private int             frameheight = MyConstants.FRAMEHEIGHT;
    private JButton         btn_deposit;
//    private ArrayList<User> UserList;
    private User user;
    public DepositFrame(JFrame pf, User us) {
        ParentFrame = pf;
        user = us;
//        UserList = ul;
        this.setSize(framewidth, frameheight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Deposit Page");

        Addinput();
    }

    public void Addinput() {
        contentpane = (JPanel)getContentPane();

        drawpane = new JLabel();
        MyImageIcon background = new MyImageIcon(MyConstants.BG_LOGIN).resize(1200, 675);
        drawpane.setIcon(background);
        drawpane.setLayout(null);

        MyImageIcon Orange = new MyImageIcon(MyConstants.ORANGE).resize(500, 300);
        JLabel label = new JLabel(Orange);
        JLabel username = new JLabel("Username : " + user.getUsername());
        JLabel money = new JLabel("Money       : " + user.getMoney());
        JLabel credits = new JLabel("Credits      : " + user.getCredits());
        label.setBounds(300, 50,400 , 500);
        label.setIcon(Orange);
        label.setVisible(true);

        username.setVisible(true);
        username.setBounds(20, 30, 200, 100);
        username.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        username.setForeground(Color.white);

        money.setVisible(true);
        money.setBounds(20, 60, 200, 100);
        money.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        money.setForeground(Color.white);

        credits.setVisible(true);
        credits.setBounds(20, 90, 200, 100);
        credits.setFont(new Font("Trend Sans One", Font.PLAIN, 25));
        credits.setForeground(Color.white);

        label.add(username);
        label.add(money);
        label.add(credits);

        JLabel amount = new JLabel("Deposit Amount");
        amount.setVisible(true);
//        amount.setBounds(800, 30, 200, 100);
        amount.setBounds(400, 270,400 , 40);
        amount.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        amount.setForeground(Color.white);
        drawpane.add(amount);

        JLabel dollarSign = new JLabel("$");
        dollarSign.setVisible(true);
//        dollarSign.setBounds(800, 30, 200, 100);
        dollarSign.setBounds(200, 320,600 , 50);
        dollarSign.setFont(new Font("Trend Sans One", Font.PLAIN, 40));

        amountField = new JTextArea();
        amountField.setFont(new Font("SanSerif", Font.PLAIN, 40));
        amountField.setBounds(200, 320,600 , 50);
        amountField.setEditable(true);
        amountField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        amountContainer = new JPanel(new BorderLayout());
        amountContainer.add(dollarSign, BorderLayout.WEST);
        amountContainer.add(amountField, BorderLayout.CENTER);
        amountContainer.setVisible(true);
        amountContainer.setBounds(200, 320,600 , 50);
        drawpane.add(amountContainer);

//        // Sample data for the table
//        Object[][] data = {
//                {"User1", 100, 500.0},
//        };
//        Object[] columnNames = {"Username", "Credits", "Money"};
//        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        JTable userTable = new JTable(model);
//        userTable.setPreferredSize(new Dimension(500, 350));
//        JScrollPane scrollPane = new JScrollPane(userTable);
////        userTable.setBounds(300, 250, 400, 40);
//        scrollPane.setBounds(300, 250, 400, 40);
//        drawpane.add(scrollPane);

        btn_deposit = new JButton("DEPOSIT");
        btn_deposit.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_deposit.setBounds(680, 480, 200, 50);
        btn_deposit.setForeground(Color.gray);
        btn_deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Integer.parseInt(amountField.getText()) > user.getMoney()) {
//                        if(Integer.parseInt(amountField.getText()) > 1000000000) {
//                            throw new MyException("You cannot deposit money more than 999999999");
//                        }
                        throw new MyException("You cannot deposit money with the value that you have provided");
                    }
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(getParent(), "Please enter number only");
                    amountField.setText("");
                }
                catch (Exception err) {
                    System.err.println(err);
                }
            }
        });
        drawpane.add(btn_deposit);

        JButton btn_back = new JButton("Back");
        btn_back.setFont(new Font("Trend Sans One", Font.PLAIN, 30));
        btn_back.setBounds(100, 480, 200, 50);
        btn_back.setForeground(Color.gray);
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        drawpane.add(btn_back);


        drawpane.add(label);
        contentpane.add(drawpane);
        repaint();
        validate();
    }

    public User login(String username, String password, ArrayList<User> UserList) throws MyException {
        if(UserList != null) {
            for(User u : UserList) {
                if(u.getUsername().equals(username)) {
                    if(u.getPassword().equals(password)) {
                        return u;
                    } else {
                        throw new MyException("Username or Password is incorrect.");
                    }
                } else {
                    throw new MyException("Username or Password is incorrect.");
                }
            }
        }
        return null;
    }
}
