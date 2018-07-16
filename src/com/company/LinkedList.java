package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by JoseLuis on 06.07.2018.
 */
public class LinkedList implements FunctionalList {
    private Node head;  // head of list
    private Node last;

    /*Pointers used to quickSort :)*/
    private Node pivot;
    private Node r;
    private Node i;
    private Node j;
    private Node previous;

    public LinkedList(int[] nodesArray) {
        if(nodesArray.length==0)head=null;
        else {
            head = new Node(nodesArray[0]);
            Node n = head;
            for(int i = 1 ;i < nodesArray.length; i++){
                n.setNext(new Node(nodesArray[i]));
                n = n.getNext();
            }
        }
    }

    public LinkedList() {
        this.head=null;
    }
    public LinkedList(Node h) {
        if(h==null)head=null;
        else head = h;
    }

    public LinkedList(Node h, Node headOfList) {
        head = h;
        head.setNext(headOfList);
        //headOfList.setPrev(head);
    }
    public void printList()
    {
        Node n = head;
        while (n != null)
        {
            System.out.println(n.getData()+" ");
            n = n.getNext();
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public Node head() {
        Node n = head;
        this.head = head.getNext();
        return n;
    }

    public Node last(){
        Node n = head;
        while (n.getNext() != null)
        {
            //System.out.println(n.getData()+" ");
            n = n.getNext();
        }
        return n;
    }
    public Node beforeLast(){
        Node n = head;
        while (n.getNext().getNext() != null)
        {
            //System.out.println(n.getData()+" ");
            n = n.getNext();
        }
        return n;
    }


    @Override
    public LinkedList tail() {
        return new LinkedList(head.getNext());
    }

    @Override
    public Node get(int i) {
        Node n = head;
        int index = 0;
            while (n != null)
            {
                if(index == i){
                    //System.out.println(n.);
                    return n;
                }
            n = n.getNext();
            index++;
            }
        return null;
    }


    @Override
    public LinkedList update(int i, Object e)  throws Exception{

    if(i == 0){
        return new LinkedList((Node)e,this.head.getNext());
    }
        Node newNode = (Node) e;
        newNode.setNext(get(i).getNext());

        LinkedList newList = new LinkedList(newNode);
        while(i-1>=0){
            Node newHead = new Node(get(i-1).getData());
            newHead.setNext(newList.head);
            newList.head=newHead;
            i--;
        }
        return newList;
    }
    @Override
    public LinkedList cons(Object e) {
        return new LinkedList((Node)e, head);
    }



    public int getSize(){
        Node n = head;
        int totalSize = 0;
        while (n != null)
        {
            totalSize++;
            n = n.getNext();
        }
        return totalSize;
    }
    public Node quickSort() {
        return quickSort(head,last());
    }

    public Node quickSort(Node first, Node end) {
        //if (first == null || first.getNext()==null || first == end || end.getData() == null || end == null) {
        if (end.getNext()!=first&&first!=end&&end.getData()!=null) {
            partition(first, end);// make the calculation in the global pointers
            quickSort(first,previous);
            quickSort(pivot.getNext(),end);
            return first;
        } else {
            return first;
        }
    }

    private void partition(Node start, Node end){
        pivot = end;
        r = end;
        i = new Node();
        i.setNext(start);
        j = start;
        previous = null;

        while(j != null && j != end){
            if((int)j.getData()<(int)end.getData()){
                i = i.getNext();
                swap(i,j);
            }
            j=j.getNext();
        }
        swap(i.getNext(),r);
        previous = i;
        pivot = i.getNext();
    }


    private void swap(Node a, Node b){
        Object aux = a.getData();
        a.setData(b.getData());
        b.setData(aux);
    }


    public void quickSortNaive() throws Exception {
        quickSortNaive(0,getSize()-1);
    }
    public void quickSortNaive(int first, int end) throws Exception {
        //if (first == null || first.getNext()==null || first == end || end.getData() == null || end == null) {
        int p;
        if (first<end) {
            p=partitionNaive(first, end);// make the calculation in the global pointers
            quickSortNaive(first,p-1);
            quickSortNaive(p+1,end);
        } else {
        }
    }

    private int partitionNaive(int start, int end) throws Exception {
        Node n = (Node)get(end);
        int piv = (int)n.getData();
        int j = start;
        int i = start-1;
        //previous = null;

        for(j=start;j<=end-1;j++){
            n = (Node) get(j);
            if((int)n.getData()<=piv){
                i++;
                swap(i,j);
            }
        }
        swap(i+1,end);
        return i+1;
    }


    private void swap(int a, int b) throws Exception {
        Node na = get(a);
        Object aux  = (int)na.getData();
        Node nb = get(b);
        na.setData(nb.getData());
        nb.setData(aux);
    }


/*
    public LinkedList quickSort(Node head) {

        //Base Case
        if(head == null || head.getNext() == null)
            return new LinkedList(head);
        //Partition Strategy
        //Chose first element as pivot and move all elements smaller than the pivot at the end of LL
        //So the order will be pivot, elements smaller than or equal to pivot, elements larger than pivot
        //Example: 9,13,10,6,9,8,11 => 9,13,10,9,11,6,8  and the method will return a pointer to 11
        Node partitionedElement = partition(head);

        //The elements to the right of pivot were all smaller than pivot after partioned
        //Example: LeftPartition  = 6->8->null
        Node leftPartition = partitionedElement.getNext();

        //The elements to the left of pivot were all large , so they go in right partition
        //Example: rightPartition = 9->13->10->9->11->null
        Node rightPartition = head;
        partitionedElement.setNext(null);

        //But there can be edge cases
        //Example: 3,5,3,4,5-null => after partition , list is unchanged and last element 5 is returned
        //in this case leftPartition: 3->null and rightPartition 5,3,4,5-null
        if(leftPartition == null){
            leftPartition = head;
            rightPartition = head.getNext();
            head.setNext(null);
        }

        //Now Recursively sort
        rightPartition = quickSort(rightPartition).head;
        leftPartition = quickSort(leftPartition).head;

        //After sorting append rightPartition to leftPartition
        Node iterator = leftPartition;
        while(iterator.getNext()!=null)
            iterator = iterator.getNext();
        iterator.setNext(rightPartition);

        return new LinkedList(leftPartition);
    }

    private Node partition(Node head){
        //Base case
        if(head.getNext().getNext() == null){

            if((int)head.getNext().getData()>(int)head.getData())
                return head.getNext();
            else
                return head;
        }

        else{
            Node i = head.getNext();
            Node pivot = head;
            Node lastElementSwapped = ((int)pivot.getNext().getData()>=(int)pivot.getData())?pivot.getNext():pivot;

            while(i!=null && i.getNext() !=null){

                if((int)i.getNext().getData() >= (int)pivot.getData()){
                    if(i.getNext() == lastElementSwapped.getNext()){
                        lastElementSwapped = lastElementSwapped.getNext();
                    }
                    else{
                        Node temp = lastElementSwapped.getNext();
                        lastElementSwapped.setNext(i.getNext());
                        i.setNext(i.getNext().getNext());
                        lastElementSwapped = lastElementSwapped.getNext();
                        lastElementSwapped.setNext(temp);
                    }
                }

                i = i.getNext();

            }
            return lastElementSwapped;
        }

    }*/
}

