package hj.datastructure.list.previous.linkedlist;

import hj.datastructure.list.previous.List;

public class Main {

    public static void main(String[] args) {
        List<String> test = new LinkedList<String>();
        String testStr = "This is a test 1";
        String testStr2 = "This is a test 2";
        String testStr3 = "This is a test 3";
        String testStr4 = "This is a test 4";
        String testStr5 = "This is a test 5";
        test.add(testStr);
        test.add(testStr2);
        test.add(testStr3);
        test.add(0,testStr4);
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
        test.add(2,testStr5);
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
        System.out.println(test.get(4));
        //test.add(7,testStr3);

        test.remove(1);

        //System.out.println(test.get(-1));
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
        //System.out.println(test.get(1));
        //System.out.println(test.get(2));

     /*   int testInt = 1;
        int testInt2 = 2;
        int testInt3 = 3;

        test.add(testInt);
        test.add(testInt2);
        test.add(testInt3);

        test.remove(1);

        System.out.println(test.get(0));
        System.out.println(test.get(1));*/

        System.out.println();





    }
}
