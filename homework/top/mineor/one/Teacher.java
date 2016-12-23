package top.mineor.one;

import java.util.Random;

/**
 * Created by mineor on 2016/12/20.
 */
public class Teacher {
    static Question question = new Question();
    public static Question getQuestion(){
        Random random = new Random();//设置随机数0-999
        question.setNum1(random.nextInt(1000));
        question.setNum2(random.nextInt(1000));
        return question;
    }
}
