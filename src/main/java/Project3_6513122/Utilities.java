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
    static final String ORANGE   = PATH + "ORANGE.jpeg";
    static final String BG_LOGIN     = PATH + "bg-login.jpg";
    static final String HEAD_ICON     = PATH + "slotty69logo.png";
    static final String SLOT_CARD = PATH + "/slot_card/";

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
