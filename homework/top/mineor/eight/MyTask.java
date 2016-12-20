package top.mineor.eight;

import java.util.concurrent.Callable;

/**
 * Created by mineor on 2016/12/20.
 */
public class MyTask implements Callable<Integer>{

    private int start;
    private int end;
    private int[] data;

    public MyTask(int start,int end,int[] data){
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        for(int i = start; i < end; i++){
            if(max < data[i])
                max = data[i];
        }
        return max;
    }
}
