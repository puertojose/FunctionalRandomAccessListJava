package com.company;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by JoseLuis on 06.07.2018.
 */
public class RandomAccessList implements FunctionalList, Iterable {

    private Tree head;
    private Tree current;
    private int count=0;
    private int miscLevel=0;
    private String treeLevel="";
    private int level= 0;


    /*Pointers used to quickSort :)*/
    private int pivot;
    private int r;
    private int i;
    private int j;
    private Tree previous;

    public RandomAccessList(Tree head) {
        this.head = head;
    }



    public RandomAccessList(int[] nodesArray) {
        if(nodesArray.length==0)head=null;
        else {
            head = new Tree(nodesArray[0]);
            for(int i = 1 ;i < nodesArray.length; i++){
                if(this.head.getNext()!=null&&this.head.getSize()==this.head.getNext().getSize()){

                    Tree t = new Tree(this.head.getSize()+this.head.getNext().getSize()+1,new Tree(nodesArray[i],this.head,this.head.getNext()));
                    t.setNext(this.head.getNext().getNext());
                    head=t;
                }else{
                    Tree t = new Tree(nodesArray[i]);
                    t.setNext(head);
                    head=t;
                }
            }
        }
    }

    public Tree lookupTree(int w, int i, Tree t) throws Exception {
        if(w==1 && i==0 && t.getSize()==1){
            return t;
        }else if(w==1 && t.getSize()==1){
            throw new Exception("Current tree has only one node, not possible");
        }else if(i==0){
            return t;
        }else {
            if(i<=t.getSize()/2){
                return lookupTree(w/2,i-1,t.left);
            }else{
                return lookupTree(w/2,i-1-w/2,t.right);
            }
        }
    }
    public Tree lookup(int i, Tree current) throws Exception {
        if(current==null || current.getSize()==0 ){
            throw new Exception("Current tree has is empty, not possible");
        }
        else{
            if(i<current.getSize()){
                return lookupTree(current.getSize(),i,current);
            }else{
                return lookup(i-current.getSize(),current.getNext());
            }
        }

    }
    public Tree updateTree(int w, int i, Object y, Tree t) throws Exception {
        if(w==1 && i==0 && t.getSize()==1){
            return new Tree(y);
        }else if(w==1 && t.getSize()==1){
            throw new Exception("Current tree has only one node, not possible");
        }else if(i==0){
            return new Tree(y,t.left,t.right);
        }else {
            if(i<=t.getSize()/2){
                return new Tree(t.getData(),updateTree(w/2,i-1,y,t.left),t.right);
            }else{
                return new Tree(t.getData(),t.left,updateTree(w/2,i-1-w/2,y,t.right));
            }
        }
    }
    @Override
    public RandomAccessList update(int i, Object y) throws Exception {
        Tree current = head;
        for(int j=0 ;j<count;j++){
            current = head;
            current.getNext();
        }
        if(current.getSize()==0){
            throw new Exception("Current tree is empty, not possible");
        }
        else if (i < current.getSize()) {
            count = 0;
            Tree t = new Tree(updateTree(current.getSize(), i, y, current));
            t.setNext(current.getNext());
            return new RandomAccessList(t);
        } else {
            count++;
            update(i - current.getSize(), y);
        }

        return null;
    }



    @Override
    public Iterator iterator() {
        return toCollection().iterator();
    }

    private BeanContextMembershipEvent toCollection() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public Object head() throws Exception {
        if(head==null){
            throw new Exception("Can't head because the List is already empty");
        }else if (this.head.getSize()==1){
            Tree t = head;
            this.head = head.getNext();
            return t;
        }else {
            Tree t = head;
            this.head = head.left;
            this.head.setNext(head.right);
            this.head.getNext().setNext(t.getNext());
            return t.getData();
        }
    }

    @Override
    public RandomAccessList tail() throws Exception {
        if(head==null){
            throw new Exception("Can't tail because the List is already empty");
        }else if (this.head.getSize()==1){
            return new RandomAccessList(this.head.getNext());
        }else {
            Tree t = new Tree(head);
            this.head = head.left;
            this.head.setNext(head.right);
            this.head.getNext().setNext(t.getNext());
            return new RandomAccessList(t);
        }
    }

    @Override
    public Object get(int i) throws Exception {
        return lookup(i,this.head);
    }




    @Override
    public RandomAccessList cons(Object elem) {
        if(this.head.getNext()!=null&&this.head.getSize()==this.head.getNext().getSize()){
            Tree t = new Tree(this.head.getSize()+this.head.getNext().getSize()+1,new Tree(elem,this.head,this.head.getNext()));
            t.setNext(this.head.getNext().getNext());
            return new RandomAccessList(t);
        }else{
            Tree t = new Tree(elem);
            t.setNext(head);
            return new RandomAccessList(t);
        }
    }

    private void printSide(Tree t){
        if(t!=null){
            level++;
            System.out.println(treeLevel+t.getData()+"  level ->"+level);
            treeLevel = " -left-> ";
            printSide(t.left);
            treeLevel = " -right-> ";
            printSide(t.right);

        }
;

        }


    public void printList() {
        Tree t = head;
        while (t != null) {
            miscLevel++;
            System.out.println(t.getData() + " Tree No :"+miscLevel);
            treeLevel = " -left-> ";
            printSide(t.left);
            treeLevel = " -right-> ";
            printSide(t.right);
            t = t.getNext();
        }

        miscLevel = 0;
        treeLevel = "";
    }

    public int getSize(){
        Tree t = head;
        int totalSize = 0;
        while(t!=null){
            totalSize = totalSize +t.getSize();
            t=t.getNext();
        }
        return totalSize;
    }

    public void printSkewBinaryNumber(){
        Tree t = head;

        int i = 0;
        while(t!=null){
            System.out.println(
                    ( (Math.log(t.getSize()+1)/Math.log(2))
                            -1));
            t=t.getNext();
        }


    }

    public void quickSort() throws Exception {
        quickSort(0,getSize()-1);
    }

    public void quickSort(int size) throws Exception {
        quickSort(0,size);
    }

    public void quickSort(int first, int end) throws Exception {
        //if (first == null || first.getNext()==null || first == end || end.getData() == null || end == null) {
        int p;
        if (first<end) {
            p=partition(first, end);// make the calculation in the global pointers
            quickSort(first,p-1);
            quickSort(p+1,end);
        } else {
        }
    }

    private int partition(int start, int end) throws Exception {
        Tree t = (Tree)get(end);
        pivot = (int)t.getData();
        r = end;
        i = start-1;
        j = start;
        //previous = null;

        for(j=start;j<=end-1;j++){
            t = (Tree) get(j);
            if((int)t.getData()<=pivot){
                i++;
                swap(i,j);
            }
        }
        swap(i+1,r);
        return i+1;

    }


    private void swap(int a, int b) throws Exception {
        Tree ta = (Tree)get(a);
        Object aux  = (int)ta.getData();
        Tree tb = (Tree)get(b);
        ta.setData(tb.getData());
        tb.setData(aux);
    }
}
