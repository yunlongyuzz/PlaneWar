package PlaneWar;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame jf =new JFrame();
        jf.setTitle("飞机大战小游戏 By 余云龙" );

        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
        int height =Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);

        jf.setResizable(false);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel gamePanel=new GamePanel();
        jf.add(gamePanel);

        jf.setVisible(true);


    }
}
