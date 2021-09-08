package PlaneWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    int planeX;
    int planeY;
    int []shellX=new int[55];
    int []shellY=new int[55];
    double []degrees=new double[55];
    boolean isStart=false;
    boolean isDie=false;
    boolean suspend=false;
    boolean up,down,right,left;
    long startTime;
    long endTime;
    long suspendTime1;
    long suspendTime2;
    Timer timer;

    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keycode=e.getKeyCode();
                if(keycode==KeyEvent.VK_SPACE){
                    if(isDie==true){
                        init();
                        startTime=System.currentTimeMillis();
                        isDie=false;
                    }else if (isStart==true&&suspend==false){
                        suspend=true;
                        suspendTime1=System.currentTimeMillis();
                        repaint();
                    }else if(suspend==true&&suspend==true){
                        suspend=false;
                        suspendTime2=System.currentTimeMillis();
                        repaint();
                    }
                    else{
                        isStart=true;
                        startTime=System.currentTimeMillis();
                        repaint();
                    }
                }
                if(keycode==KeyEvent.VK_UP){
                  up=true;
                }
                if(keycode==KeyEvent.VK_DOWN){
                  down=true;
                }
                if(keycode==KeyEvent.VK_LEFT){
                  left=true;
                }
                if(keycode==KeyEvent.VK_RIGHT){
                  right=true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int keycode=e.getKeyCode();
                if(keycode==KeyEvent.VK_UP){
                    up=false;
                }
                if(keycode==KeyEvent.VK_DOWN){
                    down=false;
                }
                if(keycode==KeyEvent.VK_LEFT){
                    left=false;
                }
                if(keycode==KeyEvent.VK_RIGHT){
                    right=false;
                }
            }
        });
       timer=new Timer(50, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              if(isStart==true&&isDie==false&&suspend==false){
                  if(left==true){
                      planeX=planeX-9;
                  }
                  if(right==true){
                      planeX=planeX+9;
                  }
                  if(up==true){
                      planeY=planeY-9;
                  }
                  if(down==true){
                      planeY=planeY+9;
                  }
                  for(int i=0;i<55;i++){
                      shellX[i]+=Math.cos(degrees[i])*20;
                      shellY[i]+=Math.sin(degrees[i])*20;

                      if(shellX[i]<=0){
                          shellX[i]=750;
                      }
                      if(shellX[i]>750){
                          shellX[i]=0;
                      }
                      if(shellY[i]<=0){
                          shellY[i]=750;
                      }
                      if(shellY[i]>750){
                          shellY[i]=0;
                      }
                    boolean flag=new Rectangle(planeX,planeY,60,50).intersects(new Rectangle(shellX[i],shellY[i],25,25));
                      if(flag){
                          isDie=true;
                          endTime=System.currentTimeMillis();
                      }
                  }
                   repaint();
              }
           }
       });
       timer.start();
    }


    public void init(){
        planeX=350;
        planeY=650;
    for(int i=0;i<55;i++){
        shellX[i]=100;
        shellY[i]=100;
        degrees[i]=(int)(Math.random()*2*Math.PI);
    }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Picture.bgImage.paintIcon(this,g,0,0);
        Picture.planeImage.paintIcon(this,g,planeX,planeY);
        for(int i=0;i<55;i++){
            Picture.shellImage.paintIcon(this,g,shellX[i],shellY[i]);
        }
        if(isStart==false){
            g.setColor(new Color(173, 234, 248));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击空格开始游戏",250,350);
        }
        if(isDie==true){
            g.setColor(new Color(173, 234, 248));
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("游戏结束,您坚持了"+((suspendTime1-startTime)+(endTime-suspendTime2))/1000+"秒，按空格键重新开始",125,375);
        }
        if(suspend==true){
            g.setColor(new Color(173, 234, 248));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按空格键继续",275,350);
        }
    }
}