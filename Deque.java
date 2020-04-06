/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        // empty constructor
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateItem(item);

        Node newNode = new Node(item);
        size++;

        if (first == null) {
            first = newNode;
            last = first;
        }
        else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);

        Node newNode = new Node(item);
        size++;

        if (last == null) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        ensureDequeNotEmpty();

        Item item = first.item;
        first = first.next;
        this.size--;

        if (this.size == 0) {
            last = null;
        }
        else {
            first.previous = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        ensureDequeNotEmpty();

        Item item = last.item;
        last = last.previous;
        this.size--;

        if (this.size == 0) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("first");
        deque.addLast("last");
        deque.addFirst("trump_first");
        deque.addLast("trump_last");

        int size = deque.size();
        if (size == 4) {
            StdOut.println("size correct");
        }
        else {
            StdOut.println("size incorrect");
        }

        String result = deque.removeLast();
        StdOut.println("last element removed correctly: " + result.equals("trump_last"));

        result = deque.removeFirst();
        StdOut.println("first element removed correctly: " + result.equals("trump_first"));

        size = deque.size();
        if (size == 2) {
            StdOut.println("size correct");
        }
        else {
            StdOut.println("size incorrect");
        }

        StdOut.println("next correct: " + deque.iterator().next().equals("first"));

        deque.removeFirst();
        deque.removeLast();
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void ensureDequeNotEmpty() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
    }

    private class Node {
        private final Item item;
        private Node next;
        private Node previous;

        private Node(Item item) {
            this.item = item;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}