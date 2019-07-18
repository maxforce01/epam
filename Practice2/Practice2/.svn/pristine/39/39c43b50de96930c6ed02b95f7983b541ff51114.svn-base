package ua.nure.gunko.practice2;

import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
    
        System.out.println("==== Part1");
        Array list = new ArrayImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list.toString());
        list.remove("E");
        System.out.println(list.toString());
        System.out.println(list.contains("B"));
        System.out.println(list.contains("A"));
        Array c = new ArrayImpl();
        c.add("D");
        c.add("K");
        System.out.println(list.containsAll(c));



        System.out.println("==== Part2");
        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(list);
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
        try {
            it.remove();
        } catch (IllegalStateException ex) {
            System.out.println(ex.getClass());
        }
        System.out.println("==== Part3");
        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListIterator lit = ((ListIterable)list).listIterator();
        while (lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }
        System.out.println();

        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");
        }
        System.out.println();
        list = new ArrayImpl();
        lit = ((ListIterable)list).listIterator();
        System.out.println(lit.hasNext());
        System.out.println(lit.hasPrevious());
        list.add("Element");
        System.out.println(lit.next());
        System.out.println(lit.hasNext());
        System.out.println(lit.hasPrevious());


    }
}
