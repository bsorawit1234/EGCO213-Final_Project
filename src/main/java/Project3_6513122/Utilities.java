package Project3_6513122;

import java.awt.Image;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;

interface MyConstants {
    //----- Resource files
    static final String PATH        = "src/main/java/Project3_6513122/resources/";
    static final String SHEET       = "src/main/java/Project3_6513122/sheet.txt";
    static final String BG_HOME     = PATH + "bg-play.png";
    static final String PLAY_ICON   = PATH + "play-button.png";
    static final String HOWTOPLAY_ICON   = PATH + "howtoplay.png";
    static final String RED   = PATH + "box-for-login.png";
    static final String BG_LOGIN     = PATH + "bg-login.jpg";
    static final String HEAD_ICON     = PATH + "slotty69logo.png";
    static final String SLOT_CARD = PATH + "/slot_cha_white/";
    static final String SLOT_MAINBG = PATH + "bg-slotmaingame.jpg";
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
    static final String SLOTMACHINE = PATH + "slot-machine.png";
    static final String ROLL = PATH + "roll-button.png";
    static final String STOP = PATH + "stop-button.png";
    static final String HOWTOPLAYPAGE = PATH + "howtoplay-page.png";
    static final String SONG1 = PATH + "song/song1-former.wav";
    static final String SONG2 = PATH + "song/song2-funk-casino.wav";
    static final String SONG3 = PATH + "song/song3-funk-it.wav";
    static final String SONG4 = PATH + "song/song4-the-best-jazz-club.wav"; //default
    static final String SONG5 = PATH + "song/song5-upbeat-funky-pop.wav";
    static final String SETTINGPAGE = PATH + "setting-page.png";
    static final String SETTINGBUTTON = PATH + "setting-button.png";
    static final String WINPRIZE = PATH + "win-prize.png";

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
class MySoundEffect {
    private Clip clip;
    private FloatControl gainControl;

    public MySoundEffect(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(float gain) {
        if (gain < 0.0f) gain = 0.0f;
        if (gain > 1.0f) gain = 1.0f;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
