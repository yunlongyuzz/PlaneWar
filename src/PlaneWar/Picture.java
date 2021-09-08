package PlaneWar;

import javax.swing.*;
import java.net.URL;

public class Picture {

    static URL shellURL = Picture.class.getResource("/picture/炸弹.png");
    static ImageIcon shellImage = new ImageIcon(shellURL);

    static URL bgURL=Picture.class.getResource("/picture/背景.png");
    static ImageIcon bgImage = new ImageIcon(bgURL);

    static URL planeURL = Picture.class.getResource("/picture/飞机.png");
    static ImageIcon planeImage = new ImageIcon(planeURL);

}
