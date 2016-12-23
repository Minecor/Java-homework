package top.mineor.eight;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass {
    private static int[] data = new int[10000000];
    public static Map<String,Integer> threadValue = new HashMap<String,Integer>();
    public static void main(String[] args){
        long startTime = new Date().getTime();
        Thread[] threads = new Thread[5];
        Random random = new Random();
        for(int i = 0; i < data.length; i++)
            data[i] = random.nextInt();
        for(int i = 0; i < 5; i++){
            MyThread task = new MyThread(i*2000,(i+1)*2000,data);
            threads[i] = new Thread(task,"thread"+i);
            threads[i].start();
        }

        try {
            for(int i = 0; i < 5; i++){
                threads[i].join();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 5; i++){
            int value = threadValue.get("thread"+i);
            if(max < value)
                max = value;
        }
        System.out.println("此次一千万个随机数最大值为:"+max);
        long endTime = new Date().getTime();
        System.out.println("执行的总时间为:"+(endTime-startTime));
    }
}
