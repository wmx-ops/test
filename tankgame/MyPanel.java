package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

/**
 * @author 汪明熙
 * @version 1.0
 */
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero = null;
    //定义敌人坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象的Vector,用于恢复敌人坦克坐标和方向
    Vector<Node> nodes = new Vector<>();
    int enemyTankSize = 6;
    //定义一个Vector存放炸弹
    //当子弹击中坦克时就加入一个Bomb对象到bombs中
    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public MyPanel(String key) {
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("文件不存在，只能开始新的游戏");
            key = "1";
        }
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(700,100);//初始化自己的坦克
        switch (key){
            case "1":
                //重新游戏
                //初始化敌人坦克
                for (int i = 0; i < enemyTankSize ; i++) {
                    Recorder.setAllEnemyTankNum(0);
                    //创建一个敌人坦克
                    EnemyTank enemyTank = new EnemyTank(100 *(i + 1),0);
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20,enemyTank.getY() + 60,enemyTank.getDirect());
                    //加入enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //继续上局游戏
                //初始化敌人坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建一个敌人坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20,enemyTank.getY() + 60,enemyTank.getDirect());
                    //加入enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("你的输入有误。。。");
        }

        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb01.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb02.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb03.png"));
    }

    //敌方是否击中我方坦克
    public void hitHero(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (hero.isLive && shot.isLive){
                    hitTank(shot,hero);
                }
            }
        }
    }
    //我方是否击中敌方坦克
    public void hitEnemyTank(){
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }
    //判断是否击中坦克方法
        public  void hitTank(Shot s,Tank Tank){
        switch (Tank.getDirect()){
            case 0:
            case 2:
                    if (s.x > Tank.getX() && s.x < Tank.getX() + 40 &&
                     s.y > Tank.getY() && s.y < Tank.getY() + 60){
                        s.isLive = false;
                        Tank.isLive = false;
                        //当我的子弹击中敌人坦克后，将enemyTank从Vector去除
                        enemyTanks.remove(Tank);
                        //当我方击毁一个敌人坦克时addEnemyTankNum++
                        if (Tank instanceof EnemyTank){
                            Recorder.addAllEnemyTankNum();
                        }
                        Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                        bombs.add(bomb);
                    }
                    break;
            case 1:
            case 3:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 60 &&
                        s.y > Tank.getY() && s.y < Tank.getY() + 40){
                    s.isLive = false;
                    Tank.isLive = false;
                    enemyTanks.remove(Tank);
                    //当我方击毁一个敌人坦克时addEnemyTankNum++
                    if (Tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                }
                break;
           }
        }
    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g){
        //画出玩家总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,0);//画出一个向上的敌方坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "",1080,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色
        showInfo(g);
        //画出自己的坦克
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        //画出hero射击的子弹
        for (int i = 0; i < hero.shots.size(); i++) {
           Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive){
                g.draw3DRect(shot.x,shot.y,2,2,false);
            }else {
                hero.shots.remove(shot);
            }
        }

        //如果bombs集合中有对象，就把炸弹画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombs.get(i);
            //根据当前bombs对象的life值去画出对应的图片
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x, bomb.y,60,60,this);
            } else if (bomb.life > 3) {
                g.drawImage(image2,bomb.x, bomb.y,60,60,this);
            }else{
                g.drawImage(image3,bomb.x, bomb.y,60,60,this);
            }
            //减少炸弹的生命值
            bomb.lifeDown();
            //炸弹生命值为0，就从bombs的集合中删除
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }
        //画出敌人坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否存活
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画出敌人坦克所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出每颗子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }
    public void drawTank(int x, int y, Graphics g,int direct,int type){
        switch(type){
            case 0://敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1://我的坦克
                g.setColor(Color.yellow);
                break;
        }
        switch(direct){
            case 0://上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fill3DRect(x + 10,y + 10,20,40,false);
                g.fillOval(x + 10,y + 20,20,20);
                g.drawLine(x + 20,y + 30,x + 20,y);
                break;
            case 1://右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x ,y + 30,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.drawLine(x + 30,y + 20,x + 60,y + 20);
                break;
            case 2://下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fill3DRect(x + 10,y + 10,20,40,false);
                g.fillOval(x + 10,y + 20,20,20);
                g.drawLine(x + 20,y + 30,x + 20,y+60);
                break;
            case 3://左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x ,y + 30,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.drawLine(x + 30,y + 20,x,y + 20);
                break;


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    //按键监听
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirect(0);
            if (hero.getY() > 0) {
                hero.moreUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moreRight();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moreDown();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moreLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    //每一百毫秒重新绘制
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断坦克是否被击中
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
