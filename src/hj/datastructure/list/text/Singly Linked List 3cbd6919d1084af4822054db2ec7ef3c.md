# Singly Linked List

### **포인터로 연결 리스트 만들기**

```java
public class LinkedList<E>{
	class Node<E>{
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
}
```

head는 머리 노드를 참조하는 변수 ( 머리 노드 그 자체가 아님! )
해당 리스트에 head가 null을 참조하고 있으면 비어있는 것
keyword : 참조

1. 연결 리스트가 비어 있는지 판단하는 방법
    
    ```java
    head == null
    ```
    
2. 꼬리 노드인지 판단하는 방법
- Node<E>형의 변수 p가 리스트의 노드 중 하나를 가리킬 때 변수 p가 가리키는 노드가 연결 리스트의 꼬리 노드인지 판단하는 방법
    
    ```java
    p.next == null
    ```
    

### **search 메서드**

- 알고리즘 : 선형 검색
- 머리 노드부터 검색

조건

1. 검색 조건을 만족하는 노드를 찾지 못하고 꼬리 노드를 지나가기 직전인 경우
2. 검색 조건을 만족하는 노드를 찾는 경우

```java
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
```

### addFirst 메서드

- 머리에 노드를 삽입하는 메서드

```java
public void addFirst(E obj){
	Node<E> newNode = new Node<E>(obj, head);
	head = selectedNode = newNode;
}

// 책 코드
public void addFirst(E obj){
	Node<E> ptr = head;               // 삽입 전의 머리 노드
	head = crnt = new Node<E>(obj, ptr);
}
```

### addLast 메서드

- 리스트 꼬리에 노드를 삽입하는 메서드

조건

1. 리스트가 비어 있는 경우
    1. 머리에 노드를 삽입하는 작업과 같음.
    2. addFirst 메서드로 처리
2. 리스트가 비어 있지 않은 경우
    1. 리스트 꼬리에 노드 삽입

```java
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

// 책 코드
public void addLast(E obj){
	if(head == null){
		addFirst(obj);
	} else {
		Node<E> ptr = head;
		while (ptr.next != null){
			ptr = ptr.next;
		}
		ptr.next = crnt = new Node<E>(obj, null);
	}
}
```

### removeFirst 메서드

- 리스트가 비어 있지 않은 경우에만 삭제

```java
public void removeFirst(){
	if(head != null){
		head = slectedNode = head.next;
	}
}
```

### removeLast 메서드

- 리스트가 비어 있지 않은 경우에만 삭제

조건

1. 리스트가 비어 있는 경우
2. 리스트에 노드가 1개만 있는 경우
3. 리스트에 노드가 2개 이상 있는 경우

```java
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

// 책 코드
public void removeLast(){
	if(head != null){
		if(head.next == null){
			removeFirst();
		} else {
			Node<E> ptr = head;
			Node<E> pre = head;
			
			while(ptr.next != null){
				pre = ptr;
				ptr = ptr.next;
			}
			pre.next = null;
			crnt = pre;
		}
	}
}
```

### remove 메서드

- 리스트가 비어 있지 않은 경우에만 삭제

조건

1. 리스트가 비어 있는 경우
2. p가 머리 노드인 경우
3. p가 머리 노드가 아닌 경우

**노드 p 삭제**

```java
public void remove(Node p){
	if(head == null) return;

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
	selectedNode = currentScanningNode**;**
}

// 책 코드
public void remove(Node p){
	if(head != null) {
		if(p == head){
			removeFirst();
		} else {
			Node<E> ptr = head;
			
			while(ptr.next != p){
				ptr = ptr.next;
				if(ptr == null) return;
			}
			ptr.next = p.next;
			crnt = ptr;
		}
	}
}
```

**선택 노드를 삭제**

```java
public void removeCurrentNode(){
	remove(selectedNode);
}
```

**모든 노드를 삭제**

```java
public void clear(){
	while(head != null){
		removeFirst();
	}
	selectedNode = null; // 이거 필요한가?
}
```