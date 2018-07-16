package com.company;

/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

// A simple Java program to introduce a linked list
class Benchmark
{
    private static int[] testArray;
    public static void main(String[] args) throws Exception {
        /* Start with the empty list. */
        LinkedList llist = new LinkedList(new Node(1));

        LinkedList llist2 = llist.cons(new Node(2)).cons(new Node(10)).cons(new Node(4)).cons(new Node(3));
        LinkedList llist3 = llist.cons(new Node(3));

        System.out.println("List 1");
        //llist.printList();
        System.out.println("List 2");
        //llist2.printList();
        System.out.println("List 3");
        //llist3.printList();
        /*Persistence working*/


        //System.out.println(llist2.get(2).getData());

        LinkedList llist4 = llist2.update(1, new Node(6)); // update workinggggg yesss

        System.out.println("List 2");
        //llist2.printList();
        System.out.println("List 4");
        //llist4.printList();
        //llist2.printList();

        int arraySize = 5000000;
        LinkedList llist7;

        //RandomAccessList rlist1 = new RandomAccessList(new Tree(2));
        //RandomAccessList rlist2 = rlist1.cons(4).cons(6).cons(8).cons(20).cons(35).cons(24).cons(98).cons(2).cons(23);
        //rlist1.printList();
        //rlist2.printList();
        //System.out.println("Size  "+rlist2.getSize());
        //System.out.println("-------------------------");



        //System.out.println("-------------------------------SORRRTTTTTINNNNGGGG LINKED LISTTT-------------------");
        //llist2.printList();

        long betterSortLinked = 0;
        long naiveSortLinked = 0;
        long quickSortRandomA = 0;
        int cycles = 10;
        for(int j = 0; j<cycles;j++){


            testArray = new int[arraySize];
            Random rand = new Random();
            for (int i = 0; i < arraySize; i++) {
                testArray[i] = rand.nextInt();
            }

        llist7 = new LinkedList(testArray);
        long startTime = System.currentTimeMillis();
        llist7.quickSort();
        long endTime = System.currentTimeMillis();
        betterSortLinked = betterSortLinked + endTime-startTime;
        System.out.println("Total execution time of better QuickSort on a LinkedList: " + (endTime-startTime) + "ms");



        //llist7 = new LinkedList(testArray);
        //startTime = System.currentTimeMillis();
        //llist7.quickSortNaive();
        //endTime = System.currentTimeMillis();
        //naiveSortLinked = naiveSortLinked + endTime-startTime;
        //System.out.println("Total execution time of Naive QuickSort on a LinkedList: " + (endTime-startTime) + "ms");



        //System.out.println("sorted----------------------------------------------------");
        //llist7.printList();
        //System.out.println("original--------------------------------------------------------------");
        //llist3.printList();
        //System.out.println("-----------------------------SORTING RANDOM ACCESS LISTTTT");
        RandomAccessList rlist3 = new RandomAccessList(testArray);
        //System.out.println("random access list not ordered");
        //rlist3.printList();
        //rlist3.printSkewBinaryNumber();
        //rlist3.printList();

        startTime = System.currentTimeMillis();
        rlist3.quickSort();
        endTime = System.currentTimeMillis();
            quickSortRandomA = quickSortRandomA +endTime-startTime;
        System.out.println("Total execution time of QuickSort on a Random Access list: " + (endTime-startTime) + "ms");
        //System.out.println("hey");
        //rlist3.printList();

        }

        //System.out.println("Average naiveSortLinked: "+ (double)naiveSortLinked/cycles);
        System.out.println("Average betterSortLinked: "+ (double)betterSortLinked/cycles);
        System.out.println("Average quickSortRandomA: "+ (double)quickSortRandomA/cycles);

    }
}