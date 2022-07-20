package ys.datastructure.list;

public class Example {

    public static void main(String[] args) {
        List linkedListA = new SinglyLinkedList();
        linkedListA.addHead("하하2");
        linkedListA.addHead("하하1");
//        linkedListA.addMiddle("하하인덱스" ,2);


        System.out.println("linkedListA.size() = " + linkedListA.size());

        for ( int i = 0; i < linkedListA.size()-1; i++) {
            System.out.println(linkedListA.get(i));
        }

        /*
            이중 연결 리스트 구현
         */

        List doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addHead("첫번째 데이터");
        doublyLinkedList.addHead("첫번째 두번째");
        doublyLinkedList.addMiddle("중간 데이터" , 1);
        doublyLinkedList.add("마지막 데이터");

        /** 자료 구조가 비어있는 경우 */
        System.out.println("자료 구조가 비어있는 경우");
        List emptyDoublyLinkedList = new DoublyLinkedList();
//        emptyDoublyLinkedList.remove(0);
//        emptyDoublyLinkedList.get(0);
        /**  자료 구조에 단 하나의 요소가 들어있을 때 */
        System.out.println("자료 구조에 단 하나의 요소가 들어있을 때");

        List oneElementDoublyLinkedList = new DoublyLinkedList();
//        oneElementDoublyLinkedList.add("단 하나 요소");
//        oneElementDoublyLinkedList.addMiddle("중간" , 4);
//        oneElementDoublyLinkedList.remove(2);
//        oneElementDoublyLinkedList.get(1);

        /** 자료 구조의 첫 번째 요소를 제거하거나 추가할 때 */

        System.out.println("자료 구조의 첫 번째 요소를 제거하거나 추가할 때");
        List firstAddOrRemoveDoublyLinkedList = new DoublyLinkedList();

        firstAddOrRemoveDoublyLinkedList.addHead("첫번째 요소");
        firstAddOrRemoveDoublyLinkedList.remove(0);

        for ( int i =0 ; i < firstAddOrRemoveDoublyLinkedList.size(); i++) {
            System.out.println("firstAddOrRemoveDoublyLinkedList = " + firstAddOrRemoveDoublyLinkedList.get(i));
        }
        /** 자료 구조의 마지막 요소를 제거하거나 추가할 때 */
        System.out.println("자료 구조의 마지막 요소를 제거하거나 추가할 때");
        List lastAddOrRemoveDoublyLinkedList = new DoublyLinkedList();

        /** 자료 구조의 중간 부분을 처리할 때 */
        System.out.println("자료 구조의 중간 부분을 처리할 때");
        List processMiddleDoublyLinkedList = new DoublyLinkedList();


        doublyLinkedList.remove(0);

        for ( int i = 0 ; i < doublyLinkedList.size(); i++) {
            System.out.println(doublyLinkedList.get(i));
        }
    }
}
