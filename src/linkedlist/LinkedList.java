package linkedlist;

import java.util.Objects;

public class LinkedList<T> {
    private class Node {
        private T value;
        public Node prev;
        public Node next;

        public Node() {
            this.value = null;
            this.prev = null;
            this.next = null;
        }

        public Node(Node next) {
            this.prev = null;
            this.next = next;
        }

        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LinkedList.Node)) return false;
            Node node = (Node) o;
            return value.equals(node.value) && prev.equals(node.prev) && next.equals(node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, prev, next);
        }
    }

    private int length;
    private Node head = null;
    private Node tail = null;

    public LinkedList(){}

    public LinkedList(int length) {
        func(length);
    }



    public T last(){
        return tail.value;
    }

    public T first(){
        return head.value;
    }

    public void pushBack(T value){
        if(length == 0){
            func(1);
            head.value = value;
            return;
        }
        tail = insert(tail);
        tail.value = value;
        length++;
    }

    public T get(int index){
        if(length <= index){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node temp = head;
        for(int i = -1; i < index - 1; i ++){
            temp = temp.next;
        }
        return temp.value;
    }

    public void set(int index, T value){
        if(length <= index){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node temp = head;
        for(int i = -1; i < index - 1; i ++){
            temp = temp.next;
        }
        temp.value = value;
    }

    public int getLength() {
        return length;
    }

    public void remove(int index){
        Node temp = head;
        for(int i = -1; i < index - 1; i ++){
            temp = temp.next;
        }
        Node prev = temp.prev, next = temp.next;
        prev.setNext(next);
        next.setPrev(prev);
        length--;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node temp = head;
        while(temp != null){
            builder.append(temp).append(" ");
            temp = temp.getNext();
        }
        return builder.toString();
    }

    private Node insert(Node prev){
        Node temp = new Node(prev, null);
        prev.setNext(temp);
        return temp;
    }

    private void func(int length){
        this.length = length;
        if(length != 0){
            head = tail = new Node();
            Node prev = head;
            for(int i = 1; i < length; i ++){
                tail = prev = insert(prev);
            }
        }
    }

}
