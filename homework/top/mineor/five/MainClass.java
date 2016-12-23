package top.mineor.five;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass {
    private static int[] data = new int[10000000];
    private static Map<String,Future<Integer>> result = new HashMap<String,Future<Integer>>();
    public static void main(String[] args) {
        long startTime = new Date().getTime();
        Thread[] threads = new Thread[5];
        Random random = new Random();
        for(int i = 0; i < data.length; i++)
            data[i] = random.nextInt();
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++){
            MyTask task = new MyTask(i*2000,(i+1)*2000,data);
            FutureTask<Integer> futureTask  = new FutureTask<Integer>(task);
            threads[i] = new Thread(futureTask,"thread"+i);
            result.put("thread"+i,futureTask);
            threads[i].start();
        }
        try {
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < 5; i++){
                threads[i].join();
            }
            for(int i = 0; i < 5; i++){
                Future<Integer> future = result.get("thread"+i);
                int value = future.get();
                if(max < value)
                    max = value;
            }
            System.out.println("此次一千万个随机数最大值为:"+max);
            long endTime = new Date().getTime();
            System.out.println("执行的总时间为:"+(endTime-startTime));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
