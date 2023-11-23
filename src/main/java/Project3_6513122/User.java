package Project3_6513122;

public class User {
    private String username, password;
    private double credits;

    public User(String un, String pw) {
        username = un;
        password = pw;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
