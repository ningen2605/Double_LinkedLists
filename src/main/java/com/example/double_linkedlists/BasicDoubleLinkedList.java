package com.example.double_linkedlists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
/**
 * Generic double-linked list that relies on references to the first element and last elemtns of the list.
 * @author Ricardo Abuabara
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
        protected Node first;
        protected Node last;
        public int size;

        public BasicDoubleLinkedList() {
            size = 0;
            first = null;
            last = null;
        }

        /**
         * Node class builder to contain data and links to other nodes.
         */
        public class Node {
            protected T data;
            protected int size;
            protected Node prev, next;

            public Node(T data) {
                this.prev = null;
                this.next = null;
                this.data = data;
                this.size = 0;
            }

        }

        /**
         * Method to return the value of the variable to keep track of the size
         *
         * @return the size of linked list
         */
        public int getSize() {
            return size;
        }

        /**
         * Method to return the last element of the linked list
         *
         * @return the last element of linked list
         */
        public T getLast() {
            if (size == 0) return null;
            return last.data;
        }

        /**
         * Method to return the first element of the linked list
         *
         * @return the first element of linked list
         */
        public T getFirst() {
            if (size == 0) return null;
            return first.data;
        }

        /**
         * adds element to the front of the list
         *
         * @param data - the data for the Node in the linked list
         * @return the reference to the current object
         */
        public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
            Node newFirst = new Node(data);
            if (size == 0) {
                first = newFirst;
                last = first;
            } else {
                first.prev = newFirst;
                newFirst.next = first;
                first = newFirst;
            }
            size++;
            return this;
        }

    /** returns the Node looped through the List
     *
     * @return  Node
     * @throws UnsupportedOperationException
     */
    public ListIterator<T> iterator() throws UnsupportedOperationException {
            return new NodeIterator();
        }


        /**
         * adds element to the back of the list
         *
         * @param data - the data for the Node in the linked list
         * @return the reference to the current object
         */
        public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
            Node newLast = new Node(data);
            if (size == 0) {
                first = newLast;
                last = first;
            } else {
                last.next = newLast;
                newLast.prev = last;
                last = newLast;
            }
            size++;
            return this;
        }

        public class NodeIterator implements ListIterator<T> {
            protected Node curr = first;
            protected Node last;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public boolean hasPrevious() {
                return last != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    last = curr;
                    curr = curr.next;
                    return last.data;
                }
                try {
                    throw new NoSuchElementException("No next elements available");
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public T previous(){
                if (hasPrevious()) {
                    curr = last;
                    last = last.prev;
                    return curr.data;
                }
                try {
                    throw new NoSuchElementException("No previous elements available");
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public int nextIndex(){
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public int previousIndex() {
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void remove() {
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void set(T data) {
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void add(T data) {
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * Returns an arraylist for all the elements in the list starting from first to last
         * @return arraylist of elements in the list
         */
        public ArrayList<T> toArrayList() {
            ArrayList<T> list = new ArrayList<>();
            Node curr = first;

            while (curr != null) {
                list.add(curr.data);
                curr = curr.next;
            }
            return list;
        }

        /**
         * Removes and return the first element from the list.
         * @return data element or null
         */
        public T retrieveFirstElement() {
            if (size == 0){
                return null;
            }
            T front = first.data;
            if (size == 1) {
                first = null;
                last = null;
            }
            else {
                first = first.next;
                first.prev = null;
            }
            size--;
            return front;
        }
        /**
         * Removes and return the last element from the list.
         * @return data element or null
         */
        public T retrieveLastElement() {
            if (size == 0){
                return null;
            }
            T back = last.data;
            if (size == 1) {
                first = null;
                last = null;
            }
            else {
                last = last.prev;
                last.prev = null;
            }
            size--;
            return back;
        }

        /**
         * Removes the first instance of data targeted from the list
         * @param targetData - data to be removed
         * @param comparator - the comparator of equality between elements
         * @return reference to the current object
         */
        public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
            Node curr = first;
            while (curr != null) {
                if (comparator.compare(targetData, curr.data) == 0) {
                    if (curr == first) {
                        first = first.next;
                    } else if (curr == last) {
                        curr.prev.next = curr.next;
                    }
                    size--;
                    return this;
                }
                curr = curr.next;
            }
            return this;
        }
}
