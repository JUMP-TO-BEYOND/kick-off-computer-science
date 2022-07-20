package ys.datastructure.list;

import java.util.Objects;


// feedback: 1. 헤드 , 테일 더미 사용 ( 중간 연산으로만 진행 , Iterator hasNext 구현 )
public class DoublyLinkedList<E> implements List {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private Node<E> previous;
        private Node<E> next;
        private E data;

        public Node(E data) {
            this.data = data;
        }

        private Node<E> getPrevious() {
            return previous;
        }

        private void setPrevious(Node<E> prev) {
            this.previous = prev;
        }

        private Node<E> getNext() {
            return next;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }


    }
    @Override
    public void addHead(Object obj) {
        if(head == null) {
            // feedback: 추상화  
            head = new Node(obj);
            tail = head;
            size++;
            return;
        }
    
        if(head !=null) {
            // feedback: 추상화  
            // feedback: 디스를 쓰는 이유 로컬 스코프 안에서 겹칠 때 this 사용 ( 무지성 사용 반성하기 )
            Node newNode = new Node(obj);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            size++;
        }
    }

    @Override
    public void addMiddle(Object obj, int index) {
        // feedback: SRP 지키기 addMiddle 인데 addHead와 addLast를 고려하고 있음
        if(isFirstIndex(index)) {
            addHead(obj);
            return;
        }
        
        if(isLastIndex(index)) {
            add(obj);
            return;
        }
        Node selectedNode = getNode(index);
        Node prevNode = selectedNode.getPrevious();
        Node newNode = new Node(obj);

        selectedNode.setPrevious(newNode);
        newNode.setNext(selectedNode);
        newNode.setPrevious(prevNode);
        prevNode.setNext(newNode);

        size++;
    }

    @Override
    public void add(Object obj) {
        // feedback: add는 addFirst 와 addMiddle 와 addLast의 상위 개념 ( 하극상 )   

        if(isEmpty()) {
            addHead(obj);
            return;
        }

        Node newNode = new Node(obj);
        Node tempTailNode = tail;
        tempTailNode.setNext(newNode);

        tail = newNode;
        tail.setPrevious(tempTailNode);

        size++;
    }

    @Override
    public void remove(int index) {
        Node selectedNode = getNode(index);
        if(isHead(selectedNode)) {
            removeFirst();
            return;
        }
        if(isTail(selectedNode)) {
            removeLast();
            return;
        }
        // feedback: 변수명 교체 temp 사용 X   
        Node temp2 = selectedNode.getNext();
        Node temp1 = selectedNode.getPrevious();
        temp2.setPrevious(temp1);
        temp1.setNext(temp2);

        size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object get(int index) {
        return getNode(index).data;
    }

    private Node getNode(int index) {
        validateList(index);

        if (isFirstIndex(index)) {
            return head;
        }
        if (isLastIndex(index)) {
            return tail;
        }
        if(isOverMidIndex(index)) {
            return findNodeBack(index);
        }
        return findNodeFront(index);
    }
    private boolean isOverIndex(int index) {
        // feedback: 조건식 그자체를 리턴으로
        return index > size-1;
    }

    private boolean isOverMidIndex(int index) {
        return index > getMidIndex();
    }

    private int getMidIndex() {
        return this.size/2;
    }

    private boolean isFirstIndex (int index) {
        return index == 0;
    }
    private boolean isLastIndex (int index) {
        return index == this.size-1;
    }

    private void removeFirst() {
        if(this.size ==1) {
            this.head = null;
            this.tail = null;
            size = 0;
            return;
        }

        Node tempHead = this.head.next;
        tempHead.previous = null;
        this.head = tempHead;
        size--;
    }

    private void removeLast() {
        Node tempTail = this.tail.previous;
        tempTail.next = null;
        this.tail = tempTail;
        size--;
    }

    private Node findNodeFront(int index) {
        Node returnNode = null;
        for(int i = 0; i < index; i++) {
            returnNode = head.next;
        }
        return returnNode;
    }

    private Node findNodeBack(int index) {
        Node returnNode = null;
        for (int i = size-1; i >= index; i--) {
            returnNode = tail.previous;
        }

        /*
        for ( int i = 0; i < size-index; i++ ) {
            returnNode = tail.previous;
        }
        */
        return returnNode;
    }
    private boolean isEmpty() { // shift + f6
        // feedback: 클래스명에 이미 List라고 써있는데 굳이 메서드명에 한번더 List를 적을 필요 X   
        return size == 0;
    }
    private void validateList(int index) {
        // feedback: validateIndex  
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("비어있습니다.");
        }
        if (isOverIndex(index)) {
            throw new IndexOutOfBoundsException("인덱스가 리스트 사이즈에 초과했습니다.");
        }
    }

    private boolean isHead(Node node) {
        return Objects.isNull(node.getPrevious());
    }
    private boolean isTail(Node node) {
        return Objects.isNull(node.getNext());
    }

    /*
        현진씨 피드백
        Objects.isNull(head);
        ToString(); -> 오브젝트 클래스 안에 있다. String.valuOf랑 차이점

        특정 값을 빼내기 위해서 쓰는 인덱스 돌아가는 for문을 사용
        그 외에는 무지성으로 사용 X

        생성자를 쓰는 이유
        // 생성자 주입 -> 무조건 들어가야한다. , 한 번만
        // setter 주입 -> 내 맘대로 // 여러번 맘대로
        // 생성자 매개변수 안에 자주 바뀌는 속성 데이터를 넣을 필요가 있을까?

        // 더블이다 보니까 서로 항상 연결이 되어있어서 addFirst 랑 addLast를 따로 구현 안해도 된다.
        // 교환이 가능하기 때문에
        // Tail Head 가 더미가 일 경우
        // addMiddle 로직으로 add가 진행된다. ( addFirst , addLast가 필요 없어짐 )
        // hasNext 커스터마이징 해서 해당 Linked List 가 for -each 돌 때 헤드랑 테일 더미 데이터는 빼줄 수 있다.


        // SRP 구현이 쉽지않은 경우 추상화 수준을 높임
        // 추상화 수준 -> 콘크리트하게 코딩을 하지 않고 메서드를 여러개 만들어 높으면 초반엔 추상화가 되어있지 않은 코드라도
        // 인터페이스 생성해서 추상화하기 좀 더 편해진다.

        // 하나의 메서드의 초기에 변수 선언 후 if 절 안에 매번 사용하는 것이 아니라 if 절 안에 스코프에서 변수를 선언해서 사용하도록 진행

        // 함수 길이 15~20자 빡빡하게 ( Clean Code )
        // 추상화 신경
        // 들여쓰기 최대 2

        버퍼를 왜 쓰는지
        JVM 에서는 String 관련영역이 따로 있다.

        ex ) String a = "a";
             String b = "b";
             a == b -> true
             new String으로 피하기
             JVM에서 스태틱 영역과 같이 스트링 영역이 있는 것
             new String으로 하면 객체 영역으로 생성
             객체 유무는 new에 갈린다.

             new String 사용하지 않는 스트링 변수를 자주 사용하면 스트링 영역이 데이터가 증가
             데이터 증가된 후 사용하지 않는 데이터를 지울 때 GC가 실행됨

             GC 실행될 때는 STW ( Stop The World ) 상태 -> JVM이 멈추짐

             그렇기에 StringBuffer 사용


        Clean Code -> 중복 제거
        언제나 무지성으로 코드 작성하지 말기 -> 왜를 생각하자 왜 이렇게 작성헀는지
     */
    

}
