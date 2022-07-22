package hj.datastructure;

public class InnerClassTest {

    private static class InnerClass {
        String test = "test";
    }

    public static String returnInnerClassVariable(){
        InnerClass innerClass = new InnerClass();
        return innerClass.test;
    }
}
