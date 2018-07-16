package com.company;

import java.util.Iterator;

/**
 * Created by JoseLuis on 06.07.2018.
 */
public interface FunctionalList<A>{
    Iterator<A> iterator();

    boolean isEmpty();
    A head() throws Exception;
    FunctionalList<A> tail() throws Exception;
    A get(int i) throws Exception;
    FunctionalList<A> update(int i, A e) throws Exception; // creates a new list!
    FunctionalList<A> cons(A e);

}
