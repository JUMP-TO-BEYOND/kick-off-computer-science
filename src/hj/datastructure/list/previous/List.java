package hj.datastructure.list.previous;

public interface List<E> {

    void add(E e);

    void add(int index, E e);

    void remove(int index);

    E get(int index);

    boolean contains(E e);

}
