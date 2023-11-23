package Project3_6513122;

import java.awt.Image;
import javax.swing.ImageIcon;

interface MyConstants {
    //----- Resource files
    static final String PATH        = "src/main/java/Project3_6513122/resources/";
    static final String SHEET       = PATH + "sheet.txt";
    static final String BG_HOME     = PATH + "BG_HOME.jpeg";
    static final String PLAY_ICON   = PATH + "PLAY.png";
    static final String ORANGE   = PATH + "ORANGE.jpeg";

    //----- Sizes and locations
    static final int FRAMEWIDTH  = 1000;
    static final int FRAMEHEIGHT = 600;
    static final int PLAYWIDTH  = 500;
    static final int PLAYHEIGHT  = 300;
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
