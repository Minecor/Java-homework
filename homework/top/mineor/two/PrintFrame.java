package top.mineor.two;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mineor on 2016/12/20.
 */
public class PrintFrame extends JFrame{

    private static Point startPoint = new Point();
    private static Point endPoint = new Point();
    private static JPanel panel = new JPanel();

    /**
     * 框架的初始化
     * @throws Exception
     */
    public PrintFrame() throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);
        this.setLocation(screenSize.width/4,screenSize.height/4);
        this.setTitle("画圆工具");
        //panel.setBackground(Color.BLUE);
        this.add(panel);
        addMyMouseListener(panel);
        this.setVisible(true);
    }

    /**
     * 添加鼠标监听事件
     * @param parentComponent
     */
    public void addMyMouseListener(JComponent parentComponent){
        parentComponent.addMouseListener(new MouseAdapter() {
            /**
             * 鼠标按下事件
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                startPoint.setX(e.getX());
                startPoint.setY(e.getY());
            }

            /**
             * 鼠标松开事件
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                endPoint.setX(e.getX());
                endPoint.setY(e.getY());
                //System.out.println(startPoint.getX()+" "+startPoint.getY() +"," + endPoint.getX()+" "+endPoint.getY());
                printCrcle(panel.getGraphics(),startPoint,endPoint);
            }
        });
    }

    /**
     * 画圆函数
     * @param g
     * @param startPoint
     * @param endPoint
     */
    public void printCrcle(Graphics g,Point startPoint,Point endPoint){
        g.setColor(Color.blue);

        int width = endPoint.getX()-startPoint.getX();
        int height = endPoint.getY()-startPoint.getY();
        //System.out.println(width + " ||" + height);
        int size = (int)Math.sqrt(Math.abs(width)/2 * Math.abs(width)/2 + Math.abs(height)/2 * Math.abs(height)/2);//计算半径
        //System.out.println("size = " +  size);
        int xCoordinate = (endPoint.getX()+startPoint.getX())/2; //计算圆心位置
        int yCoordinate = (endPoint.getY()+startPoint.getY())/2; //计算圆心位置
        //System.out.println(xCoordinate + "\\" + yCoordinate);
        g.fillOval(xCoordinate-size,yCoordinate-size,size*2,size*2);
    }

    public static void main(String[] args){
        try {
            new PrintFrame();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
