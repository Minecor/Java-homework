package top.mineor.two;

/**
 * Created by mineor on 2016/12/20.
 */
public class Point {
    private int x;
    private int y;

    public Point(){
        this(0,0);
    }

    public Point(int x,int y){
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
