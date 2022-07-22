package hj.datastructure.list;

import java.util.Comparator;

public interface List<E> {

    public E search(E obj, Comparator<? super E> c);

    public void addFirst(E obj);

    public void addLast(E obj);

    public void removeFirst();

    public void removeLast();

    public void removeCurrentNode();

    public void clear();
}
