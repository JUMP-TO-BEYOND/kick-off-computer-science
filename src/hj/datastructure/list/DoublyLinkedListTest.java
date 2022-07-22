package hj.datastructure.list;

import java.util.*;
import java.util.List;

public class DoublyLinkedListTest {

    public static void main(String[] args) {

        List<String> testList = new DoublyLinkedList<String>();

        //1. 자료 구조가 비어있는 경우
        // Read
        //testList.get(0);
        // Update
        //testList.add(1,"empty list test");
        // Delete
        //testList.remove("empty list test");
        //testList.remove(0);

        //2. 자료 구조에 단 하나의 요소가 들어있을 때
        // Create
        testList.add("first element");
        // Read
        System.out.println(testList.get(0));
        // Update
        testList.add("second element");
        System.out.println(testList);
        testList.clear();
        testList.add("first element");
        testList.add(0, "set first element");
        System.out.println(testList);
        // Delete
        testList.clear();
        testList.add("first element");
        testList.remove("first element");
        System.out.println(testList);
        testList.add("first element");
        testList.remove(0);
        System.out.println(testList);

        //3. 자료 구조의 첫 번째 요소를 제거하거나 추가할 때
        testList.add("first element");
        testList.add("second element");
        System.out.println(testList);
        testList.remove(0);
        System.out.println(testList);
        testList.add(0, "set first element");
        System.out.println(testList);
        testList.clear();
        System.out.println(testList);

        //4. 자료 구조의 마지막 요소를 제거하거나 추가할 때
        testList.add("first element");
        testList.add("second element");
        System.out.println(testList);
        // Delete
        testList.remove(1);
        System.out.println(testList);
        testList.add("second element");
        System.out.println(testList);
        boolean test1 = testList.remove("first element");
        System.out.println(test1);
        System.out.println(testList);
        boolean test2 = testList.remove("first element");
        System.out.println(test2);
        System.out.println(testList);
        testList.add("second element");
        System.out.println(testList);
        testList.clear();
        System.out.println(testList);

        //5. 자료 구조의 중간 부분을 처리할 때
        testList.add("first element");
        testList.add("second element");
        System.out.println(testList);
        testList.add(1,"insert element test");
        System.out.println(testList);
        System.out.println(testList.get(1));
        testList.remove(1);
        System.out.println(testList);
        testList.clear();
        System.out.println(testList);


    }
}
