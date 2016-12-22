package top.mineor.nine;

/**
 * Created by mineor on 2016/12/20.
 */
public class TestMain {
    private int mm;
    private int nn;
    public TestMain(int mm){
        this.mm = mm;
        new TestInnerClass(22);
    }

    class TestInnerClass{
        private int mm;
        public TestInnerClass(int mm){
            this.mm = mm;
            print();
        }
        public void print(){
            System.out.println(this.mm);
        }
    }

    public static void main(String[] args){
        new TestMain(11);
    }
}
