package Project3_6513122;

public class User {
    private String username, password;
    private double credits, money;

    public User(String un, String pw) {
        username = un;
        password = pw;
        money = 0;
        credits = 100;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public double getMoney() {
        return money;
    }
    public double getCredits() {
        return credits;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public void setCredits(double credits) {
        this.credits = credits;
    }

}
