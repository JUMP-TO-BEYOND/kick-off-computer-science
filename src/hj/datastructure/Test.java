package hj.datastructure;

import java.util.LinkedList;
import java.util.ArrayList;

public class Test {
    private int a;
    public static void main(String[] args) {
        //InnerClassTest.InnerClass.test;
        // -> InnerClass를 static으로 선언하지 않으면 클래스 직접 접근할 수 없음.
        // -> static으로 선언 및 접근 제어자가 public 일 시 접근 가능

        //InnerClassTest.InnerClass

        ArrayList<String> testArrList = new ArrayList<String>();
        testArrList.add("1");
        testArrList.add("2");
        testArrList.add("3");

        LinkedList<String> testLinkedList = new LinkedList<String>(testArrList);


        InnerClassTest innerClassTest = new InnerClassTest();
        innerClassTest.returnInnerClassVariable();
        // InnerClassTest.returnInnerClassVariable();  -> returnInnerClassVariable 메서드가 static인 경우 가능

    }
}
