package ua.nure.gunko.practice2;

public interface Array extends Iterable<Object>,ListIterable {
    void add(Object e);
    void clear();
    boolean remove(Object o);
    Object[] toArray();
    int size();
    boolean contains(Object o);
    boolean containsAll(Array c);
}
