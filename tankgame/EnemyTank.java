package tankgame;

import java.util.Vector;

/**
 * @author 汪明熙
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable{
    //在敌人坦克类，使用Vector，保存多个Shot
    Vector<Shot> shots = new Vector<>();
    //敌人的集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    //可将Mypanel类中的enemyTanks设置到EnemyTank类当中
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //判断敌方坦克是否重叠
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0://上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this){
                        //敌人坦克上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                         if (this.getX() >= enemyTank.getX()
                         && this.getX() <= enemyTank.getX() + 40
                         && this.getY() >= enemyTank.getY()
                         && this.getY() <= enemyTank.getY() + 60){
                             return true;
                         }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                        }
                        //敌人坦克左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                            if (this.getX() + 40>= enemyTank.getX()
                                    && this.getX() +40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this){
                        //敌人坦克上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }
                        //敌人坦克左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (this.getX() + 60>= enemyTank.getX()
                                    && this.getX() + 60<= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                            if (this.getX() + 60>= enemyTank.getX()
                                    && this.getX() +60 <= enemyTank.getX() + 60
                                    && this.getY() + 40>= enemyTank.getY()
                                    && this.getY() + 40<= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this){
                        //敌人坦克上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60<= enemyTank.getY() + 60){
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }
                        //敌人坦克左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX() + 60
                                    && this.getY() + 60>= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }
                            if (this.getX() + 40>= enemyTank.getX()
                                    && this.getX() +40 <= enemyTank.getX() + 60
                                    && this.getY() + 60>= enemyTank.getY()
                                    && this.getY() + 60<= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this){
                        //敌人坦克上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }
                        //敌人坦克左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 40){
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 60
                                    && this.getY() + 40>= enemyTank.getY()
                                    && this.getY() + 40<= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while(true){
            //敌方坦克无子弹时，再让其发射
            if (isLive && shots.size() < 5){
                Shot s = null;
                //判断坦克方向，创建对应的子弹
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX() + 20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60,getY() + 20,1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20,getY() + 60,2);
                        break;
                    case 3:
                        s = new Shot(getX() ,getY() + 20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();

            }
            //坦克移动
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 10; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moreUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 10; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moreRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moreDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 10; i++) {
                        if (getX() > 0 && !isTouchEnemyTank()) {
                            moreLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //随机改变坦克方向 0-3
            setDirect((int)(Math.random() * 4));
            if (!isLive){
                //当坦克死亡，退出线程
                break;
            }
        }
    }
}
