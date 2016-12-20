package top.mineor.seven;

import java.util.concurrent.RecursiveTask;

/**
 * Created by mineor on 2016/12/20.
 */
public class MyTask extends RecursiveTask<Integer> {
    private static final int HOLD = 200000;//阈值
    private int start;
    private int end;
    private int[] data;

    public MyTask(int start,int end,int[] data){
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        boolean flag = (end-start) <= HOLD;
        if(flag){
            int max = Integer.MIN_VALUE;
            for(int i = start; i < end; i++){
                if(MainClass.data[i] > max)
                    max = data[i];
            }
            return max;
        }
        else{
            int mid = (start + end) / 2;
            MyTask leftTask = new MyTask(start,mid,data);
            MyTask rightTask = new MyTask(mid,end,data);
            leftTask.fork();
            rightTask.fork();
            int leftMaxValue = leftTask.join();
            int rightMaxValue = rightTask.join();
            return leftMaxValue > rightMaxValue ? leftMaxValue : rightMaxValue;
        }
    }
}
