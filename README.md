# Queues
Write a generic data type for a deque and a randomized queue.

The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators.


## Dequeue

A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. Create a generic data type Deque that implements the following API:


## Randomized queue

A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random among items in the data structure. Create a generic data type RandomizedQueue that implements the following API:


## Client

Write a client program Permutation.java that takes an integer k as a command-line argument; reads a sequence of strings from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. Print each item from the sequence at most once.

Command-line argument.

You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input. Note that you are not given n.

Performance requirements. 

The running time of Permutation must be linear in the size of the input. You may use only a constant amount of memory plus either one Deque or RandomizedQueue object of maximum size at most n. (For an extra challenge and a small amount of extra credit, use only one Deque or RandomizedQueue object of maximum size at most k.)

***

Full specification found here:
https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php

See also:

https://lift.cs.princeton.edu/java/linux/ for libs and steps to install
***

javac-algs4 Permutation.java Deque.java RandomizedQueue.java

***