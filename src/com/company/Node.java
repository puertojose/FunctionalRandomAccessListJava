package com.company;

/**
 * Created by JoseLuis on 06.07.2018.
 */
class Node<T> {
    public T data;
    public Node<T> next;
    Node(T d)  { data = d;  next=null;} // Constructor

    Node(Node<T> node)  { data = node.getData();  next=node.getNext();} // Constructor
    Node()  { data = null;  next=null;} // Constructor
    public Node<T> getNext(){
        return next;
    }

    public void setNext(Node<T> n){
        this.next=n;
    }

    public T getData() {
        return data;
    }

    public void setData(T d) {
        this.data=d;
    }

}
