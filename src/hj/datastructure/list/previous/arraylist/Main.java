package hj.datastructure.list.previous.arraylist;


import hj.datastructure.list.previous.List;

public class Main {

    public static void main(String[] args) {
       List<String> list = new ArrayList<String>();

        list.add("first");
        list.add("second");
        list.add("third");

        list.remove(1);


        System.out.println(list.get(1));

        List test = new ArrayList();

        test.add("first");
        test.add("second");
        test.add("third");

        test.add("4");
        test.add("5");
        test.add("6");

        test.add("7");
        test.add("8");
        test.add("9");

        test.add("10");
        test.add("11");
        test.add("12");

        test.remove(1);

        System.out.println(test.get(1));

    }
}
