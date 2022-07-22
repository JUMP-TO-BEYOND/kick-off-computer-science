package hj.datastructure.list.previous.linkedlist;


import hj.datastructure.list.previous.List;

public class LinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int length;

    public <E> LinkedList(){
        Object dummy = "It's dummy";
        firstNode = new Node((E)dummy);
        lastNode = firstNode;
        length = 0;
    }

    @Override
    public void add(E e) {
        Node newNode = new Node(e);                                 // 1 + 1 = 2
        if(isNotValidIndex(length-1)){                              // 1 + 2 = 3
            firstNode.setNext(newNode);                             // 1
            lastNode = newNode;                                     // 1
        } else {
            lastNode.setNext(newNode);                              // 1
            lastNode = newNode;                                     // 1
        }
        length++;                                                   // 1
    }
    // case 1 : 2 + 3 + 1 + 1 + 1 = 8
    // case 2 : 2 + 3 + 1 + 1 + 1 = 8
    // O(1)

    @Override
    public void add(int index, E e) {
        if(isNotValidIndex(index)){                                 // 2
            throw new IndexOutOfBoundsException();                  // 1
        }

        Node newNode = new Node(e);                                 // 1 + 1 = 2

        if(isNotValidIndex(index-1)){                               // 1 + 2 = 3
            Node currentIndexNode = getNode(index);                 // 1 + 3n + 4 = 3n + 5
            newNode.setNext(currentIndexNode);                      // 1
            firstNode.setNext(newNode);                             // 1
            lastNode = currentIndexNode;                            // 1
        } else {
            Node previousNode = getNode(index-1);             // 1 + 1 + 3n + 4 = 3n + 6
            Node currentIndexNode = previousNode.getNext();         // 1 + 1 = 2
            newNode.setNext(currentIndexNode);                      // 1
            previousNode.setNext(newNode);                          // 1
        }
        length++;                                                   // 1
    }
    // case 1 : 2 + 1 = 3
    // case 2 : 2 + 2 + 3 + 3n + 5 + 1 + 1 + 1 + 1 = 3n + 16
    // case 3 : 2 + 2 + 3 + 3n + 6 + 2 + 1 + 1 + 1 = 3n + 18 (V)
    // O(n)

    @Override
    public void remove(int index) {
        if(isNotValidIndex(index)){                                 // 2
            throw new IndexOutOfBoundsException();                  // 1
        }
        if(index==length-1){                                        // 1 + 1 = 2
            firstNode.setNext(null);                                // 1
            lastNode = firstNode.getNext();                         // 1 + 1 = 2
            length--;                                               // 1
            return;                                                 // 1
        }
        //index 유효성 검사
        if(isNotValidIndex(index-1)){                               // 1 + 2 = 3
            throw new IndexOutOfBoundsException();                  // 1
        }
        Node previousNode = getNode(index-1);                 // 1 + 1 + 3n + 4 = 3n + 6
        Node nextNode = previousNode.getNext().getNext();           // 1 + 1 + 1 = 3   => 수정
        previousNode.setNext(nextNode);                             // 1
        length--;                                                   // 1
        if(nextNode == null){                                       // 1
            lastNode = previousNode;                                // 1
        }
    }
    // case 1 : 2 + 1 = 3
    // case 2 : 2 + 2 + 1 + 2 + 1 + 1 = 9
    // case 3 : 2 + 2 + 3 + 1 = 8
    // case 4 : 2 + 2 + 3 + 3n + 6 + 3 + 1 + 1 + 1 + 1 =  3n + 20 (V)
    // O(n)

    @Override
    public E get(int index) {
        if(isNotValidIndex(index)){                     // 2
            throw new IndexOutOfBoundsException();      // 1
        }
        return getNode(index).getValue();               // 3n + 4 + 1 + 1 = 3n +6
    }
    // case 1 : 2 + 1 = 3
    // case 2 : 2 + 3n +6 = 3n + 8 (V)
    // O(n)

    private Node<E> getNode(int index) {

        int temp = 0;                                   // 1
        Node<E> searchNode = firstNode.getNext();       // 1 + 1 = 2
        while(temp < index){                            // n (index 만큼 반복)
            searchNode = searchNode.getNext();          // 1 + 1 = 2
            temp++;                                     // 1
        }                                               // while문 => n(2+1) = 3n
        return searchNode;                              // 1
    }                                                   // 1 + 2 + 3n + 1 = 3n + 4
                                                        // O(n)

    @Override
    public boolean contains(E e) {
        int tempNum = 0;                                // 1
        Node<E> searchNode = firstNode.getNext();       // 1 + 1 = 2
        while(tempNum < length){                        // n (length 만큼)
            if(searchNode.getValue() == e){             // 1 + 1 = 2
                return true;                            // 1
            }
            searchNode = searchNode.getNext();          // 1 + 1 = 2
            tempNum++;                                  // 1
        }
        return false;                                   // 1
    }
    // 1 + 2 + n( 2 + 2 + 1) + 1 = 5n + 4
    // O(n)

    private boolean isNotValidIndex(int index){
        return index < 0 || index >= length;            // 1 + 1 = 2
    }                                                   //O(1)

    private class Node<E> {
        private Node<E> next;
        private E value;

        public Node(E e){
            setValue(e);
        }               // O(1)

        public Node<E> getNext() {
            return next;
        }       // O(1)

        public void setNext(Node next) {
            this.next = next;                           // O(1)
        }

        public E getValue() {                           // O(1)
            return value;
        }

        public void setValue(E value) {                 // O(1)
            this.value = value;
        }
    }
}
