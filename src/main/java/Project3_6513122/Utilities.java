package Project3_6513122;

import java.awt.Image;
import javax.swing.ImageIcon;

interface MyConstants {
    //----- Resource files
    static final String PATH        = "src/main/java/Project3_6513122/resources/source_slot/";
    static final String SHEET       = PATH + "sheet.txt";
    static final String BG_HOME     = PATH + "bg-play.png";
    static final String PLAY_ICON   = PATH + "play-button.png";
    static final String HOWTOPLAY_ICON   = PATH + "howtoplay.png";
    static final String RED   = PATH + "box-for-login.png";
    static final String BG_LOGIN     = PATH + "bg-login.jpg";
    static final String HEAD_ICON     = PATH + "slotty69logo.png";
    static final String SLOT_CARD = PATH + "/slot_cha_white/";
    static final String SLOT_MAINBG = PATH + "bg-slotmaingame.jpg";
    static final String BET_ICON = PATH + "bet-.png";
    static final String USERNAME = PATH + "username.png";
    static final String PASSWORD = PATH + "password.png";
    static final String SUBMIT = PATH + "summit.png";
    static final String CREATEACC = PATH + "create-acc.png";
    static final String BACK = PATH + "back-button.png";
    static final String PLAY_ICON2   = PATH + "play3-button.png";
    static final String USERNAMESHOW = PATH + "username_show.png";
    static final String MONEYSHOW = PATH + "money-show.png";
    static final String CREDITSHOW = PATH + "credits-show.png";
    static final String WITHDRAW2 = PATH + "withdraw-button2.png";
    static final String DEPOSIT2 = PATH + "Deposit-button2.png";
    static final String WITHDRAW = PATH + "withdraw-button.png";
    static final String DEPOSIT = PATH + "Deposit-button.png";
    static final String LOGOUT2 = PATH + "log-out-button2.png";
    static final String WITHDRAWAMOUNTICON = PATH + "withdrawamount-text.png";
    static final String DEPOSITAMOUNTICON = PATH + "depositamount-text.png";
    static final String REPASSWORD = PATH + "re-password.png";
    static final String REGISTER = PATH + "register-button.png";
    static final String BALANCE = PATH + "balance-nobg.png";
    static final String SLOTMACHINE = PATH + "slot-machine.png";
    static final String ROLL = PATH + "roll-button.png";
    static final String STOP = PATH + "stop-button.png";
    static final String HOWTOPLAYPAGE = PATH + "howtoplay-page.png";

    //----- Sizes and locations
    static final int FRAMEWIDTH  = 1366;
    static final int FRAMEHEIGHT = 768;
    static final int PLAYWIDTH  = 599;
    static final int PLAYHEIGHT  = 263;
    static final int HTPHEIGHT  = 138;
    static final int HTPWIDTH  = 599;
}

class MyImageIcon extends ImageIcon {
    public MyImageIcon(String fname) { super(fname); }
    public MyImageIcon(Image image) { super(image); }
    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}
