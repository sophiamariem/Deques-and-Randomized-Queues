/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        int capacity = 1;
        this.queue = (Item[]) new Object[capacity];
        this.size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        resizeIfNeeded();
        queue[size] = item;
        this.size++;
    }

    // remove and return a random item
    public Item dequeue() {
        ensureDequeNotEmpty();

        shrinkIfNeeded();

        int rand = getRandomElement();
        Item item = queue[rand];

        queue[rand] = queue[this.size - 1];
        queue[this.size - 1] = null;
        this.size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        ensureDequeNotEmpty();

        int rand = getRandomElement();
        return queue[rand];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("one");
        randomizedQueue.enqueue("two");
        randomizedQueue.enqueue("three");

        int size = randomizedQueue.size();
        if (size == 3) {
            StdOut.println("size correct");
        }
        else {
            StdOut.println("size incorrect");
        }

        randomizedQueue.dequeue();
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());

        StdOut.println("iterator " + randomizedQueue.iterator().next());
    }

    private void ensureDequeNotEmpty() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
    }

    private int getRandomElement() {
        return StdRandom.uniform(this.size);
    }

    private void resizeIfNeeded() {
        if (this.size == queue.length) {
            Item[] expanded = (Item[]) new Object[queue.length * 2];

            for (int i = 0; i < queue.length; i++) {
                expanded[i] = queue[i];
            }

            this.queue = expanded;
        }
    }

    private void shrinkIfNeeded() {
        if (this.size == queue.length / 4) {
            Item[] shrinked = (Item[]) new Object[queue.length / 2];

            for (int i = 0; i < this.size; i++) {
                shrinked[i] = queue[i];
            }

            this.queue = shrinked;
        }
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current = size;
        private int[] shuffled;

        public ArrayIterator() {
            shuffled = new int[current];
            for (int i = 0; i < current; i++) {
                shuffled[i] = i;
            }
            StdRandom.shuffle(shuffled);
        }

        public boolean hasNext() {
            return current > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = queue[shuffled[--current]];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
