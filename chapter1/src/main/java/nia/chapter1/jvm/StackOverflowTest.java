package nia.chapter1.jvm;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/11 10:51
 * @since 1.0
 */
public class StackOverflowTest {

    public static void main(String[] args) {
        new StackOverflowTest().recursionCall(1);
    }

    public void recursionCall(int i) {
        System.out.println("call times:"+i);
//        try{
            recursionCall(++i);
//        }catch (StackOverflowError e){
//            throw new StackOverflowError(e.getMessage()+"call times:"+i);
//        }
    }

}
