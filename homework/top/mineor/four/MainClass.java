package top.mineor.four;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass {
    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            MyThread task = new MyThread();
            Thread thread = new Thread(task,"thread"+i);
            thread.start();
        }
    }
}
