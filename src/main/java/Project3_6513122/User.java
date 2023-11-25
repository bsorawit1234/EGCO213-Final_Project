package Project3_6513122;

import java.io.FileWriter;
import java.io.PrintWriter;

public class User {
    private String username, password;
    private int credits, money;

    public User(String un, String pw) {
        username = un;
        password = pw;
        money = 0;
        credits = 100;
        try{
            PrintWriter write = new PrintWriter(new FileWriter(MyConstants.SHEET, true));
            write.println(username + " " + password + " " + money + " " + credits);
            write.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getMoney() {
        return money;
    }
    public int getCredits() {
        return credits;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

}
