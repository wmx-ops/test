package tankgame;

/**
 * @author 汪明熙
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    boolean isLive = true;
    public void moreUp(){
        y -= 4;
    }
    public void moreRight(){
        x += 4;
    }
    public void moreDown(){
        y += 4;
    }
    public void moreLeft(){
        x -= 4;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
