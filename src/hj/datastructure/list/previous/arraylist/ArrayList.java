package hj.datastructure.list.previous.arraylist;


import hj.datastructure.list.previous.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private E[] test;
    private int size;

    public ArrayList() {
        size = 0;
        test = (E[]) new Object[10];
    }

    public E[] extend(E[] test) {
        return Arrays.copyOf(test, test.length * 2);
    }
    // O(1)

    public void add(E e) {
        if (test.length - 1 == size) {
            test = extend(test);
        }
        test[size++] = e;
    }
    // O(1)

    @Override
    public void add(int index, E e) {

        //for copy
    }

    public void remove(int index) {
        validateIndex(index);                                           // 2

        if (isFull(index)) {                                            // 2
            size--;                                                     // 1
            return;                                                     // 1
        }

        for (int i = index; i < size - 1; i++) {                        // 1 + 1 + (n-1) + (n-1) = 2n
            test[i] = test[i + 1];                                      // 1 + 1 = 2
        }                                                               // => 4n
        // 코드 잘 짜는 법 == 사람이 읽기 쉬운 코드
        // 들여쓰기 2단 초과는 금지 -> 따로 함수 빼세요.
        // 부정은 알아듣기 힘들다.
        // 조건이 명확하지 않으면 힘들다. -> 함수로 만들어서 의미를 부여한다.
        // 함수의 길이는 15줄 내외
        // 클래스 길이는 200줄 내외


        // 테스트 코드 없는 코드는 레거시 코드다.
        // Map은 해롭다. -> 명세 없다. -> 뭐가 있는지 모른다.
        // 결합도를 낮춘다. -> 의존성 주입 최소화
        // 응집도를 올린다. -> 코드가 한 묶음처럼 작동되야 한다.

        // bean 에다가 비즈니스 로직 넣지 말기

    }
    // 4n + 4
    // O(n)

    private void validateIndex(int index) {
        if (size <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    // 1 + 1 = 2
    // O(1)

    private boolean isFull(int index) {
        return index == size - 1;
    }
    // 1 + 1 = 2
    // O(1)

    public E get(int index) {
        validateIndex(index);
        return test[index];
    }
    // 2 + 1 = 3
    // O(1)

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {    // 1 + n(size 만큼) + n = 2n + 1
            if (test[i].equals(e)) {        // 1
                return true;                // 1
            }
        }
        return false;                       // 1
    }
    // 2n + 1 + 1 = 2n + 2
    // O(n)
}
