package top.mineor.five;

/**
 * Created by mineor on 2016/12/20.
 */
public class MyThread implements Runnable{
    private int start;
    private int end;
    private int[] data;

    public MyThread(int start,int end,int[] data){
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    public void run() {
        int max = Integer.MIN_VALUE;
        for(int i = start; i < end; i++){
            if(max < data[i])
                max = data[i];
        }
        MainClass.threadValue.put(Thread.currentThread().getName(),max);
    }
}
