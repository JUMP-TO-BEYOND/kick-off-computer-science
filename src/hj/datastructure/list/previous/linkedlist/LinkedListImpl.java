package hj.datastructure.list.previous.linkedlist;

public class LinkedListImpl<E> {

    private Node firstNode;

    private int size;

    public <E> LinkedListImpl() {
        size = 0;
        firstNode = new Node();
    }

    public boolean add(E e){
        try {
            Node node = createNode(e);
            if(size == 0){
                firstNode.setNode(node);
            } else {
                getNode(size-1).setNode(node);
            }
            //리스트 크기 증가시키기
            size++;
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public void add(int index, E e){
        Node node = createNode(e);
        if(size == 0){
            firstNode.setNode(node);
            size++;
            return;
        }
        validateIndex(index);
        Node nextNode = getNode(index);
        if(index == 0){
            firstNode.setNode(node);
            node.setNode(nextNode);
        } else {
            Node previousNode = getNode(index-1);
            previousNode.setNode(node);
            node.setNode(nextNode);
        }
        size++;
    }

    public E get(int index){
        validateIndex(index);
        int tempIndex = 0;
        Node tempNode = firstNode.getNode();
        while(index!=tempIndex){
            tempNode = tempNode.getNode();
            tempIndex++;
        }
        return (E)tempNode.getObject();
    }

    public Object remove(){
        firstNode.setNode(getNode(1));
        size--;
        return this;
    }

    public Object remove(int index){
        validateIndex(index);
        if(index==0){
            remove();
        } else if(index+1 == size){
            Node previousNode = getNode(index-1);
            previousNode.setNode(null);
            size--;
        } else {
            Node previousNode = getNode(index-1);
            Node nextNode = getNode(index+1);
            previousNode.setNode(nextNode);
            size--;
        }
        return this;
    }

    private Node createNode(E e){
        Node node = new Node();
        node.setObject(e);
        return node;
    }

    private Node getNode(int index){
        validateIndex(index);
        int tempIndex = 0;
        Node tempNode = firstNode.getNode();
        while(index!=tempIndex){
            tempNode = tempNode.getNode();
            tempIndex++;
        }
        return tempNode;
    }

    private void validateIndex(int index){
        //입력받은 index가 범위 내에 없을 경우 예외처리
        if(size == 0 || index<0 || index>size-1){
            System.out.println("인덱스 오류지요~");
            throw new IndexOutOfBoundsException();
        }
    }

    private class Node {
        Node node;
        Object obj;
        void Node(){}

        public void setNode(Node node){
            this.node = node;
        }

        public Node getNode(){
            return this.node;
        }

        public void setObject(Object obj){
            this.obj = obj;
        }

        public Object getObject(){
            return this.obj;
        }

    }
}
