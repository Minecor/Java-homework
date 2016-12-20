package top.mineor.four;

import java.util.Random;

/**
 * Created by mineor on 2016/12/20.
 */
public class MyThread implements Runnable{

    @Override
    public void run() {
        int i = 0;
        Random random = new Random();
        while(i < 100){
            int second = random.nextInt(10);
            try {
                Thread.sleep(second*1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程:" + Thread.currentThread().getName()
                    +"-->第"+(i+1)+"次执行" + ",休眠时间:" + second + "秒");
            i++;
        }
    }
}
