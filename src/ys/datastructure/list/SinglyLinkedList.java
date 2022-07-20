package ys.datastructure.list;

public class SinglyLinkedList<T> implements List {

    private Node head;
    private int size;


    // feedback#1 노드 클래스 Inner static Class로 생성
    static class Node<T> {
        private T data;
        private Node link;

        private Node ( T obj ) {
            this.data = obj;
        }

    }

    @Override
    public void addHead(Object obj) {

        Node tempHead = new Node(obj);
        tempHead.link = this.head;
        this.head = tempHead;
        // feedback#2 띄어 쓰기 유의하기 ( 한 행동에 대해서 ) */
        size++;
    }

    @Override
    public void addMiddle(Object object, int index) {
        // feedback#3: if문 안에 조건 메서드 처리 (가독성 향상)
        if(isOverSized(index)) {
            // feedback: void 메소드의 경우 예외일 때 Exception throw 처리
            System.out.println("최대 개수가 넘어간 index를 입력했습니다.");
            return;
        }
        // feedback#4: get Method 로 해당 인덱스 노드 찾기 ( 중복 코드 )
        Node newNode = new Node(object);

        Node temp = getNode(index - 1);
        temp.link = newNode;
        newNode.link = getNode(index);
        size++;
    }

    // if 문 안에 메서드명으로 읽는 사람들이 이해하기 쉽도록
    private boolean isOverSized(int index) {
        return index > this.size -1;
    }

    @Override
    public void add(Object object) {

        Node newNode = new Node(object);
        Node lastNode = getNode(size - 1);
        lastNode.link = newNode;
        size++;

    }

    @Override
    public void remove(int index) {

        // get 메서드 사용 후 처리 중복 코드
        if (isOverSized(index)) {
            System.out.println("해당하는 인덱스에 데이터가 없습니다.");
        }
        Node temp1 = getNode(index - 1);
        temp1.link = getNode(index + 1);
        size--;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {

        if (isOverSized(index)) {
            System.out.println("해당하는 인덱스에 데이터가 없습니다.");
            // throw 처리
        }

        return getNode(index).data;
    }

    // feedback: 중복 코드 제거를 위한 인덱스를 통한 노드 찾는 메서드 만들기
    public Node getNode(int index) {
        if (isOverSized(index)) {

        }
        Node temp = head;
        for ( int i = 0; i > index; i++) {
            temp = temp.link;
        }
        return temp;

    }

    /*
     feedback:
         1. get set 무지성 사용 X
         2. void 메서드에서 return 사용 가능
         3. else 사용 되도록 하지 않고 , if return ( Exception 사용 도전 ) 사용 후 로직 진행
     */
}
