import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.imageio.*;
//import java.io.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.*;
import javax.swing.Timer;

public class TestRun {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        DyPanel m = new DyPanel();
        j.setSize(m.getSize());
        j.add(m);

        //j.addKeyListener(m);
        j.addMouseListener(m);
        j.addKeyListener(m);
        j.setVisible(true);

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class DyPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private Timer time;
    private Guy boi,boi2;
    private Platform p1,p2,p3,p4,p5,p6,p7,p8,p9;
    private int countdown;
    private boolean active;
    private HashSet<Integer> pressedKeys;

    DyPanel() {
        p1 = new Platform(0,890, 1440);
        /*         
        p2 = new Platform(90,770,200);
        p3 = new Platform(250,640,200);
        p4 = new Platform(410,770,200);
        p5 = new Platform(1150,770,200);
        p6 = new Platform(990,640,200);
        p7 = new Platform(830,770,200);
        */
        p2 = new Platform(110,770,250);
        p3 = new Platform(110,640,250);
        p4 = new Platform(110,380,250);
        p5 = new Platform(440,510,600);
        p6 = new Platform(680,380,260);
        p7 = new Platform(1100,770,240);
        p8 = new Platform(1200,250,200);
        p9 = new Platform(400,250,200);
        active = false;
        boi = new Guy(150,875);
        boi2 = new Guy(1290,875,new Color(158,31,31));
        pressedKeys = new HashSet<Integer>();
        time=new Timer(10, this);
        
        setSize(1440, 900);
        setVisible(true);
        time.start();
        countdown=300;
        boi.inv(300);
        boi2.inv(300);
    }
    public void paintComponent(Graphics g) {
        if(active) {
            g.setColor(new Color(170,24,206));
            g.fillRect(0,0,1440,900);
            p1.show(g);
            p2.show(g);
            p3.show(g);
            p4.show(g);
            p5.show(g);
            p6.show(g);
            p7.show(g);
            p8.show(g);
            p9.show(g);
            //boi.update();
            boi.show(g);

            boi2.show(g);
            if(boi.it) {
                g.setColor(new Color(41,255,192));
                g.fillRect(boi.x-3,boi.y-3,6,6);
            }
            if(boi2.it) {
                g.setColor(new Color(41,255,192));
                g.fillRect(boi2.x-3,boi2.y-3,6,6);
            }
            if(boi.invinc>0) {
                if(boi.pval==0) {
                    for(int x=0; x<5; x++) {
                        g.setColor(new Color(90,40,255,50+10*x));
                        g.drawOval(boi.x-25-x,boi.y-25-x,50+2*x,50+2*x);
                    }
                } else {
                    for(int x=0; x<12; x++) {
                        g.setColor(new Color(104,22,159,125+5*x));
                        g.drawOval(boi.x-19-x,boi.y-19-x,38+2*x,38+2*x);
                    }
                }
            }
            if(boi2.invinc>0) {
                if(boi2.pval==0) {
                    for(int x=0; x<5; x++) {
                        g.setColor(new Color(90,40,255,50+10*x));
                        g.drawOval(boi2.x-25-x,boi2.y-25-x,50+2*x,50+2*x);
                    }
                } else {
                    for(int x=0; x<12; x++) {
                        g.setColor(new Color(104,22,159,125+5*x));
                        g.drawOval(boi2.x-19-x,boi2.y-19-x,38+2*x,38+2*x);
                    }
                }
            }
            if(countdown>0) {
                g.setColor(new Color(90,90,90,155));
                g.setFont(new Font("Arial",Font.PLAIN,900));
                g.drawString(""+(countdown/100+1),460,750);
            }
            g.setColor(new Color(0,198,229));
            g.setFont(new Font("Arial",Font.PLAIN,24));
            g.drawString(boi.count/100+"",20,40);
            g.setColor(new Color(255,41,41));
            g.drawString(boi2.count/100+"",1380,40);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1440,900);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString("Control:    Player 1  /   Player 2",580,430);
            g.drawString("Move:       WASD    / Arrow keys",580,450);
            g.drawString("Dash:           F        /   Comma",580,470);
            g.drawString("Parry:          G        /    Period",580,490);
            g.drawString("High Jump:  T         /        L",580,510);
        }
    }
    public void mouseClicked(MouseEvent e) {    
        //int x = e.getX();
        //int y = e.getY();
        if(!active) {
            boi.spawn();
            boi2.spawn();
            active=true;
        }
        return;
    }
    public void platCheck(Guy e) {
        if(e.acc>=0&&e.overlaps(p1)) {
            e.sit(p1);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p2)) {
            e.sit(p2);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p3)) {
            e.sit(p3);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p4)) {
            e.sit(p4);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p5)) {
            e.sit(p5);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p6)) {
            e.sit(p6);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p7)) {
            e.sit(p7);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p8)) {
            e.sit(p8);
            e.fli=false;
        } else if(e.acc>=0&&e.overlaps(p9)) {
            e.sit(p9);
            e.fli=false;
        } else if(e.sits(p1)||e.sits(p2)||e.sits(p3)||e.sits(p4)||e.sits(p5)||e.sits(p6)||e.sits(p7)||e.sits(p8)||e.sits(p9)) {
            e.fli=false;
        } else {
            e.fli=true;
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void keyCheck(HashSet<Integer> e) {
        for (Iterator<Integer> it = e.iterator(); it.hasNext();) {
            switch (it.next()) {
                case KeyEvent.VK_A:
                    boi.speed(-7);
                    break;
                case KeyEvent.VK_LEFT:
                    boi2.speed(-7);
                    break;
                case KeyEvent.VK_S:
                    //boi.fall=true;
                    if(boi.fli) {
                        //boi.val=3;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    //boi2.fall=true;
                    if(boi2.fli) {
                        //boi2.val=3;
                    }
                    break;
                case KeyEvent.VK_D:
                    boi.speed(7);
                    break;
                case KeyEvent.VK_RIGHT:
                    boi2.speed(7);
                    break;
                case KeyEvent.VK_F:
                    boi.dash();
                    break;
                case KeyEvent.VK_G:
                    boi.parry();
                    break;
                case KeyEvent.VK_COMMA:
                    boi2.dash();
                    break;
                case KeyEvent.VK_PERIOD:
                    boi2.parry();
                    break;
                case KeyEvent.VK_T:
                    boi.charge();
                    break;
                case KeyEvent.VK_L:
                    boi2.charge();
                    break;
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        if(active) {
            pressedKeys.add(e.getKeyCode());
            if(e.getKeyCode()==KeyEvent.VK_W) {
                boi.jump();
            } else if(e.getKeyCode()==KeyEvent.VK_UP) {
                boi2.jump();
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if(active) {
            int keyCode = e.getKeyCode();
            pressedKeys.remove(keyCode);
            if(keyCode==KeyEvent.VK_S)  {
                boi.val=1;
            }
            if(keyCode==KeyEvent.VK_DOWN) {
                boi2.val=1;
            }
            if(keyCode==KeyEvent.VK_T) {
                boi.release();
            }
            if(keyCode==KeyEvent.VK_L) {
                boi2.release();
            }
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {
        if(active) {
            if(boi.invinc==0&&boi2.invinc==0&&boi.ded.overlaps(boi2.ded)) {
                boi.it=!boi.it;
                boi2.it=!boi2.it;
                boi.inv(100);
                boi2.inv(100);
            }
            if(boi.pp!=null&&boi2.overlaps(boi.pp)) {
                boi2.dd=15;
                if(boi.x>boi2.x) {
                    boi2.mov=-25;
                } else {
                    boi2.mov=25;
                }
                boi2.acc=-13;
                boi2.mLock=true;
                boi2.fli=true;
            }
            if(boi2.pp!=null&&boi.overlaps(boi2.pp)) {
                boi.dd=15;
                if(boi2.x>boi.x) {
                    boi.mov=-25;
                } else {
                    boi.mov=25;
                }
                boi.acc=-13;
                boi.mLock=true;
                boi.fli=true;
            }
            if(countdown>-1) {
                countdown--;
            }
            if(countdown==0) {
                double j=Math.random();
                if(j<0.5) {
                    boi.it=true;
                } else {
                    boi2.it=true;
                }
            }
            boi.update();
            boi2.update();
            platCheck(boi);
            platCheck(boi2);
            keyCheck(pressedKeys);
        }
        repaint();
    }
}

class Fitbox {
    public int[] coords;
    private Color color;

    public Fitbox(int x1,int x2,int y1,int y2, int r, int g, int b) {
        coords = new int[4];
        coords[0]=x1;
        coords[1]=x2;
        coords[2]=y1;
        coords[3]=y2;
        color = new Color(r,g,b);
    }

    public Fitbox(int x1,int x2,int y1,int y2,Color c) {
        color = c;
        coords = new int[4];
        coords[0]=x1;
        coords[1]=x2;
        coords[2]=y1;
        coords[3]=y2;
    }

    public void setLoc(int x1, int x2, int y1, int y2) {
        coords[0]=x1;
        coords[1]=x2;
        coords[2]=y1;
        coords[3]=y2;
    }

    public boolean overlaps(Fitbox b) {
        Rectangle j = new Rectangle(coords[0],coords[2],coords[1]-coords[0],coords[3]-coords[2]);
        Rectangle k = new Rectangle(b.coords[0],b.coords[2],b.coords[1]-b.coords[0],b.coords[3]-b.coords[2]);
        if(j.intersects(k)) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public void show(Graphics g) {
        g.setColor(color);
        g.fillRect(coords[0],coords[2],coords[1]-coords[0],coords[3]-coords[2]);
    }

    public void show(Graphics g,Color c) {
        g.setColor(c);
        g.fillRect(coords[0],coords[2],coords[1]-coords[0],coords[3]-coords[2]);
    }
}

class Guy {
    int x,y,tick;
    boolean fli;
    Fitbox ded;
    int acc;
    int mov;
    boolean djump,dir,mLock,it,dash;
    //boolean fall;
    int val,dcd,dd,invinc,pval,pcd,charge,ready;
    long count;
    ParryBox pp;

    public Guy(int a, int b) {
        x=a;
        y=b;
        ded = new Fitbox(-1,-1,-1,-1,14,68,195);
        fli=false;
        acc=0;
        djump=true;
        //fall=false;
        val=1;
        dcd=0;
        pcd=0;
        charge=0;
        mLock=false;
        ready=0;
        pval=0;
        dir=true;
        dash=false;
        it=false;
        invinc=0;
        tick=0;
        count=0;
        pp=null;
    }

    public Guy(int a, int b, Color c) {
        ded = new Fitbox(-1,-1,-1,-1,c);
        x=a;
        y=b;
        fli=false;
        acc=0;
        djump=true;
        //fall=false;
        val=1;
        dcd=0;
        pval=0;
        pcd=0;
        charge=0;
        tick=0;
        ready=0;
        dir=true;
        dash=false;
        mLock=false;
        it=false;
        pp=null;
    }

    public boolean sits(Fitbox b) {
        if(acc>=0 && (b.coords[2]==ded.coords[3] && (ded.coords[0]<=b.coords[1] && ded.coords[0]>=b.coords[0]))) {
            acc=0;
            fli=false;
            return true;
        } else if(acc>=0 && b.coords[2]==ded.coords[3] && (ded.coords[1]>=b.coords[0] && ded.coords[1]<=b.coords[1])) {
            acc=0;
            fli=false;
            return true;
        } else {
            return false;
        }
    }

    public void dash() {
        if(dcd==0&&pval==0) {
            dash=true;
            djump=true;
            if(it) {
                dcd = 70;
            } else {
                dcd=80;
            }
            dd = 8;
            acc=0;
            mLock=true;
            if(dir) {
                mov = 30;
            } else {
                mov=-30;
            }
        }
    }

    public void parry() {
        if(pcd==0) {
            dash=true;
            pp = new ParryBox(this);
            pval = 40;
            mov=0;
            acc=0;
            mLock=true;
            pcd = 450;
            charge=0;
            ready=0;
            inv(40);
        }
    }

    public void charge() {
        if(!fli && charge==0) {
            charge=1;
        }
    }

    public void release() {
        if(charge>0) {
            acc = -6-charge/5;
            charge=0;
            y-=1;
            fli=true;
        }
    }

    public void inv(int j) {
        invinc = Math.max(invinc,j);
    }

    public void show(Graphics g) {
        if(pp!=null) {
            pp.show(g);
        }
        if(ready>0) {
            int r = ded.getColor().getRed();
            int gr = ded.getColor().getGreen();
            int b = ded.getColor().getBlue();
            int shift = 7*(5-Math.abs(ready-5));
            Color aaa = new Color(r+shift,gr+shift,b+shift);
            ded.show(g,aaa);
            ready--;
        } else {
            ded.show(g);
        }
    }

    public boolean overlaps(Platform why) {
        return ded.overlaps(why);
    }

    public boolean overlaps(ParryBox ez) {
        return ded.overlaps(ez);
    }

    public void sit(Platform p) {
        //if(!fall) {
            y=p.coords[2]-15;
            fli=false;
            djump=true;
            acc=0;
            update();
        //} else {
        //    fli=true;
        //}
    }

    public void jump() {
        if(acc>-12) {
            if(!fli) {
                y-=1;
                fli=true;
                acc=-11;
                charge=0;
                ready=0;
            } else if(djump) {
                acc=-11;
                djump=false;
            }
        }
    }

    public void spawn() {
        fli=false;
        ded.setLoc(x-15,x+15,y-15,y+15);
    }

    public void speed(int vel) {
        if(!mLock) {
            if(vel>0) {
                dir=true;
                mov=vel+2;
            } else {
                dir=false;
                mov=vel-2;
            }
        }
    }
    
    public void update() {
        if(invinc>0) {
            invinc--;
        }
        if(it) {
            count++;
        }
        if(fli&&!dash) {
            y+=acc;
            tick++;
            if(tick%2==0) {
                acc+=val;
                tick=0;
            }
        } 
        if(!fli) {
            acc=0;
            djump=true;
        }
        if(dd>0) {
            dd--;
            if(dd==0&&pval==0) {
                mLock=false;
            }
            if(dd==0) {
                dash=false;
            }
        }
        if(pval>0) {
            pval--;
            if(pval==0) {
                dash=false;
                pp=null;
                if(dd==0) {
                    mLock=false;
                }
            }
        }
        if(!mLock) {
            if(mov>0) {
                mov-=Math.min(5,Math.abs(mov));
            } else if(mov<0) {
                mov+=Math.min(5,Math.abs(mov));
            }
        }
        if(charge>0) {
            if(charge<50) {
                charge++;
                if(charge==50) {
                    ready=10;
                }
            }
            if(!dash) {
                mov/=3;
            }
        }
        x+=mov;
        if(dcd>0) {
            dcd--;
        }
        if(pcd>0) {
            pcd--;
        }
        if(y<0) {
            y+=900;
        }
        if(y>900) {
            y-=900;
        }
        if(x<0) {x+=1440;}
        if(x>1440) {x-=1440;}
        ded.setLoc(x-15,x+15,y-15,y+15);
    }
}

class ParryBox extends Fitbox {
    Guy owner;

    public ParryBox(Guy g) {
        super(g.x-28,g.x+28,g.y-28,g.y+28,new Color(36,77,158,0));
        owner = g;
    }
}

class Platform extends Fitbox {

    public Platform(int x, int y, int length) {
        super(x,x+length,y,y+10,119,21,144);
    }
    public void show(Graphics g) {
        super.show(g);
    }
}