package Project3_6513122;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DepositFrame extends DepositWithdrawFrame {

    public DepositFrame(JFrame pf, int id, ArrayList<User> ul, String which) {
        super(pf, id, ul, which);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_proceed) {
            try {
                if (amountField.getText().isBlank()) throw new MyException("Please enter something.");
                if (Integer.parseInt(amountField.getText()) > UserList.get(index).getMoney() || Integer.parseInt(amountField.getText()) < 0) {
                    throw new MyException("You cannot deposit money with the value that you have provided");
                }
                if (Integer.parseInt(amountField.getText()) > 1000000000) {
                    throw new MyException("You cannot deposit money more than 999999999");
                }
                showTableModalDialog(this, Integer.parseInt(amountField.getText()));
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "Please enter number only");
                amountField.setText("");
            } catch (MyException err) {
                JOptionPane.showMessageDialog(getParent(),err.getMessage());
                amountField.setText("");
            }
            catch (Exception err) {
                System.err.println(err);
            }
        } else if(e.getSource() == btn_submit) {
            if(checkBox.isSelected()) {
                UserList.get(index).setCredits(UserList.get(index).getCredits() + Integer.parseInt(amountField.getText()));
                UserList.get(index).setMoney(UserList.get(index).getMoney() - Integer.parseInt(amountField.getText()));

                try{
                    Files.delete(Paths.get(MyConstants.SHEET));
                    PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
                    for(User u : UserList) write.println(u.getUsername() + " " + u.getPassword() + " " + u.getMoney() + " " + u.getCredits());
                    write.close();
                } catch (Exception er) {
                    System.err.println(er);
                }

                JOptionPane.showMessageDialog(modalDialog, "Deposit Success!!");

                UserFrame userFrame = new UserFrame(mainFrame, index, UserList);
                userFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(modalDialog, "You have to accept to proceed");
            }
        }
    }
}
