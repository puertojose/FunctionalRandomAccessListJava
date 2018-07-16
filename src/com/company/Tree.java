package com.company;

/**
 * Created by JoseLuis on 06.07.2018.
 */
public class Tree<T> {
    private T data;
    private Tree<T> next;
    private int size;
    Tree<T> left;
    Tree<T> right;

    public Tree() {
        data = null;
        left = null;
        right = null;
        next = null;
    }
    public Tree(T rootData) {
        data = rootData;
        left = null;
        right = null;
        size = 1;
    }
    public Tree(T rootData, Tree<T> l, Tree<T> r ) {
        data = rootData;
        left = l;
        right = r;
        size = 1 + left.getSize()+right.getSize();
    }
    public Tree(int s, Tree<T> t) {
        data = t.getData();
        left = t.left;
        right = t.right;
        size = s;
    }
    public int getSize(){
        return size;
    }
    public Tree<T> getNext(){
        return next;
    }
    public T getData(){
        return data;
    }
    public void setNext(Tree n){
        this.next=n;
    }
    public Tree<T> getNextInTree(){
        if(size==1){return getNext();}
        else{
            return left.getNextInTree();
        }

    }

    public void setData(T data) {
        this.data = data;
    }
}


