package top.mineor.seven;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by mineor on 2016/12/20.
 */
public class MainClass{
    public static int[] data = new int[10000000];
    public static void main(String[] args){
        long startTime = new Date().getTime();
        Random random = new Random();
        for(int i = 0; i < data.length; i++)
            data[i] = random.nextInt();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask task = new MyTask(0,data.length,data);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println("此次一万个随机数最大值为:"+result.get());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        long endTime = new Date().getTime();
        System.out.println("执行的总时间为:"+(endTime-startTime));
    }
}
