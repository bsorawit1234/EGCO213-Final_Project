package Project3_6513122;

import java.io.FileWriter;
import java.io.PrintWriter;

public class User {
    private String username, password;
    private int credits, money, bet;

    public User(String un, String pw) {
        username = un;
        password = pw;
        money = 0;
        credits = 100;
        bet = 0;
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
    public int getBet() { return bet; }

    public void setMoney(int money) {
        this.money = money;
    }
    public void setCredits(int credits) { this.credits = credits; }
    public void setBet(int bet) { this.bet = bet; }
}
