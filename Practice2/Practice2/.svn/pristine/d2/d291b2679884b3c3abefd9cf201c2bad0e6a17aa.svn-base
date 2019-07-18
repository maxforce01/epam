package ua.nure.gunko.practice2;


import java.util.Iterator;
public class ArrayImpl implements  Array {
    private Object[] collection = {};



    @Override
    public void add(Object e) {
        Object[] coll_2 = new Object[this.size() + 1];
        for (int i = 0; i < this.size(); i++) {
            coll_2[i] = collection[i];
        }
        coll_2[coll_2.length-1] = e;
        collection = coll_2;
    }

    @Override
    public void clear() {
        collection = new Object[]{};
    }


    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (collection[i].equals(o)) {
                Object[] arr = new Object[this.size()-1];
                System.arraycopy(collection, 0, arr, 0, i);
                System.arraycopy(collection, i + 1, arr, i, arr.length - i);
                collection = arr;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            array[i] = collection[i];
        }
        return array;
    }

    @Override
    public int size() {
        return collection.length;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (collection[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Array c) {
        Object[] arr = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            if(!this.contains(arr[i]))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < size(); i++) {
            result.append(String.valueOf(collection[i])).append(", ");
        }
        result.replace(result.length() - 2, result.length(), "]");
        return result.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }
    private class ListIteratorImpl extends IteratorImpl implements ListIterator {
        @Override
        public boolean hasPrevious() {
            return getIndex()>=0;
        }

        @Override
        public Object previous() {
            setStatus(true);
            return collection[decIndex()];
        }

        @Override
        public void set(Object e) {
            if(!getStatus())
            {
                throw new IllegalStateException();
            }
            collection[getIndex()] = e;
            setStatus(false);
        }

        @Override
        public void remove() {
            if(!getStatus())
            {
                throw new IllegalStateException();
            }
            collection[getIndex()] = new Object();
            ArrayImpl.this.remove(collection[getIndex()]);
            decIndex();
            setStatus(false);
        }
    }
    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = -1;
        private boolean deleted = false;
   
        int getIndex()
        {
            return currentIndex;
        }
        boolean getStatus()
        {
            return deleted;
        }
        
        int decIndex()
        {
            return currentIndex--;
        }
        int incIndex()
        {
           return  currentIndex++;
        }
        boolean setStatus(boolean flag)
        {
           return deleted = flag;
        }
        public boolean hasNext() {
            return currentIndex < collection.length-1;
        }

        public Object next() {
            deleted = true;
            return collection[++currentIndex];
        }
       
        public void remove() {
            if(!deleted)
                throw new IllegalStateException();
            collection[currentIndex] = new Object();
            ArrayImpl.this.remove(collection[currentIndex]);
            currentIndex -= 1;
            deleted = false;
        }
    }

}

