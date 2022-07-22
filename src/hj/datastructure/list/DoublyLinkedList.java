package hj.datastructure.list;

import java.lang.reflect.Type;
import java.util.List;
import java.util.*;

public class DoublyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private Node<E> previous;
        private Node<E> next;
        private E value;

        private Node(E value) {
            this.value = value;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(head);
    }

    @Override
    public boolean contains(Object o) {
        // TODO 형변환, Object 제너릭 체크

        //list의 크기가 0일 때
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> searchNode = head;
        while (!Objects.isNull(searchNode.getNext())) {
            // TODO 값으로 null 들어갈 때
            if (Objects.equals(searchNode.getValue(), o)) {
                return true;
            }
            searchNode = searchNode.getNext();
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    // TODO head, tail 더미로 쓰면???
    // addFirst, addLast가 필요 없음
    @Override
    public boolean add(E e) {
        /* add : 마지막 인덱스에 더하기 */

        //size가 0이면 addFirst(e)
        if (isEmpty()) {
            addFirst(e);
            return true;
        }

        //size가 0이 아니면 마지막 노드에 추가
        Node<E> newNode = new Node<>(e);
        newNode.setPrevious(tail);
        newNode.setNext(null);
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    /*
    add로 처리 가능할 듯?
    private void addLast(E e) {
        Node<E> newNode = new Node<>(tail, null, e);
        tail.next = newNode;
        tail = newNode;
    }
    */

    // head -> ?? (null, value)
    // head = null -> head = value
    // head // newHead -> head
    // 20개 ~ 15줄 이내
    private void addFirst(E e) { // SOLID -> 한 함수는 한가지의 일을 해야한다.
        Node<E> newNode = new Node<>(e);

        if (Objects.isNull(head)) {
            head = newNode;
            tail = head;
            size++;
            return;
        }

        newNode.setNext(head);
        head.setPrevious(newNode);
        head = newNode;

        size++;
    }


    // TODO: 2022-07-20 중복 데이터의 경우, 첫 번째 일치하는 data를 제거한다. 
    @Override
    public boolean remove(Object o) throws RuntimeException {

        //list의 크기가 0일 때
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> searchNode = head;
        int tmpIndex = 0;
        while (tmpIndex <= size - 1) {
            if (searchNode.getValue().equals(o)) {
                //첫 노드 삭제인 경우
                // 메서드로 빼거나, 더미데이터를 이용하거나
                // 들여쓰기 단계도 2까지만
                if (tmpIndex == 0) {
                    removeFirst();
                    return true;
                }

                //마지막 노드 삭제인 경우
                if (tmpIndex == size - 1) {
                    removeLast();
                    return true;
                }
                searchNode.previous.setNext(searchNode.next);
                searchNode.next.setPrevious(searchNode.previous);
                size--;
                return true;
            }
            searchNode = searchNode.next;
            tmpIndex++;
        }

        return false;
    }

    private void removeLast() {
        //크기가 1인 경우
        if (head.next == null) {
            head = null;
            tail = null;
            size--;
            return;
        }

        //크기가 1이상인 경우
        Node<E> previousNode = tail.previous;
        previousNode.next = null;
        tail = previousNode;
        size--;
    }

    private void removeFirst() { // SOLID 원칙 벗아니고 있습니다.
        // 크기가 0일때
        // 더미 써야한다.
        // 추상화 수준을 높여라.
//        if (size == 0) {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        //크기가 1인 경우
        if (size == 1) {
            // reset();
            head = null;
            tail = null;
            size--;
            return;
        }

        //크기가 2이상인 경우
        // removeFromHead();
        Node<E> nextNode = head.next;
        nextNode.previous = null;
        head = nextNode;
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getNode(index).getValue();
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

        validateIndex(index);
        //index == 0 (첫노드 삽입)
        if (index == 0) {
            addFirst(element);
            size++;
            return;
        }

        Node<E> tmpNode = getNode(index);
        Node<E> previousNode = tmpNode.previous;
        Node<E> newNode = new Node<>(element);
        newNode.setPrevious(previousNode);
        newNode.setNext(tmpNode);
        tmpNode.previous = newNode;
        previousNode.next = newNode;
        size++;

    }

    private Node<E> getNode(int index) {
        validateIndex(index);

        //인덱스가 리스트 크기의 절반보다 작으면 head부터 탐색
        if (index < size / 2) {
            Node<E> selectedNode = head;
            int count = 0;
            while (index > count) {
                selectedNode = selectedNode.next;
                count++;
            }
            return selectedNode;
        }

        //인덱스가 리스트 크기의 절반보다 크면 tail부터 탐색
        Node<E> selectedNode = tail;
        int count = size - 1; // TODO count 변수명 수정
        while (index < count) {
            selectedNode = selectedNode.previous;
            count--;
        }
        return selectedNode;
    }

    private void validateIndex(int index) {
        if (!(0 <= index && index < size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    // TODO removeFirst(), removeLast() 리턴값을 E로 바꿔준다.
    // 이유 생각해보기
    @Override
    public E remove(int index) {
        validateIndex(index);
        //첫 노드 삭제인 경우
        if (index == 0) {
            E removeData = head.getValue();
            removeFirst();
            return removeData;
        }

        //마지막 노드 삭제인 경우
        if (index == size - 1) {
            E removeData = tail.getValue();
            removeLast();
            return removeData;
        }

        Node<E> searchNode = getNode(index);
        E removeData = searchNode.getValue();
        searchNode.previous.setNext(searchNode.next);
        searchNode.next.setPrevious(searchNode.previous);
        size--;
        return removeData;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node<E> currentNode = head;
        while (currentNode != null) {
            str.append(currentNode.getValue()).append(",");
            currentNode = currentNode.getNext();
            if(currentNode == null){
                str.deleteCharAt(str.lastIndexOf(","));
                break;
            }
        }
        str.append("]");
        return str.toString();
    }
}
