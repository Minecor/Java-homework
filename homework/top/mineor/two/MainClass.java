package top.mineor.two;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass {
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new PrintFrame();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
