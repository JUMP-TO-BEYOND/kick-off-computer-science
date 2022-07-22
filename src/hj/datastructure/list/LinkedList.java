package hj.datastructure.list;

import java.util.Comparator;

public class LinkedList<E> implements List<E> {
    private static class Node<E>{
        E data;          // 값 value
        Node<E> next;    // 다음 노드를 가리키는 포인터

        Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
    }
    private Node<E> head;            // 머리 노드
    private Node<E> selectedNode;    // 선택 노드

    public LinkedList(){
        head = selectedNode = null;
    }

    public E search(E obj, Comparator<? super E> c){
        Node<E> currentScanningNode = head;

        while(currentScanningNode != null){
            if(c.compare(obj, currentScanningNode.data) == 0){
                selectedNode = currentScanningNode;
                return currentScanningNode.data;
            }
            currentScanningNode = currentScanningNode.next;
        }
        return null;
    }
    public void addFirst(E obj){
        Node<E> newNode = new Node<E>(obj, head);
        head = selectedNode = newNode;
    }

    public void addLast(E obj){
        if(head == null){
            addFirst(obj);
            return;
        }

        Node<E> currentScanningNode = head;
        while(currentScanningNode.next != null){
            currentScanningNode = currentScanningNode.next;
        }
        currentScanningNode.next = selectedNode = new Node<E>(obj, null);
    }

    public void removeFirst(){
        if(head != null){
            head = head.next;
        }
    }

    public void removeLast(){
        if(head == null) return;

        if(head.next == null) {
            removeFirst();
            return;
        }

        Node<E> currentScanningNode = head;
        Node<E> previousNode = null;
        while(currentScanningNode.next != null){
            previousNode = currentScanningNode;
            currentScanningNode = currentScanningNode.next;
        }
        previousNode.next = null;
        selectedNode = previousNode;
    }

    public void remove(Node p){
        if(head == null) return; // 비어있는 것을 알려줘야 됨..(exception 처리 필요)

        // 삭제할 노드가 머리 노드인 경우
        if(head == p) {
            removeFirst();
            return;
        }

        Node<E> currentScanningNode = head;
        while(currentScanningNode.next != p){
            currentScanningNode = currentScanningNode.next;
            if(currentScanningNode == null) return;
        }
        currentScanningNode.next = p.next;
        selectedNode = currentScanningNode;
    }

    public void removeCurrentNode(){
        remove(selectedNode);
    }

    public void clear(){
        head = null;
        /*while(head != null){
            removeFirst();
        }
        selectedNode = null; // 이거 필요한가?*/
    }

}
