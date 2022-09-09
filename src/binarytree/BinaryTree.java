package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree<T extends Comparable> {
    protected class Node {
        public Node left = null, right = null;
        public T value;
        public int height;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getHeight() {
            return height;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int resetHeight() {
            height = func(0);
            return height;
        }

        public int getBalance(){
            if(isLeaf()) return 0;
            if(this.left == null) return -this.right.func(0);
            if(this.right == null) return this.left.func(0);
            return this.left.func(0) - this.right.func(0);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        private int func(int i) {
            i++;
            if (isLeaf()) {
                return i;
            }
            if (left == null) {
                return right.func(i);
            }
            if (right == null) {
                return left.func(i);
            }
            return Math.max(left.func(i), right.func(i));
        }
    }

    protected Node root = null;

    public BinaryTree() {
    }

    public Node search(T value) {
        Node temp = root;
        Node temp2 = temp;
        while (temp != null) {
            temp2 = temp;
            int i = temp.value.compareTo(value);
            if (i > 0) temp = temp.left;
            else if(i < 0) temp = temp.right;
            else break;
        }
        return temp2;
    }

    public Node search(T value, Stack<Node> nodeStack) {
        Node temp = root;
        Node temp2 = null;
        while (temp != null) {
            temp2 = temp;
            nodeStack.add(temp2);
            int i = temp.value.compareTo(value);
            if (i > 0) temp = temp.left;
            else if(i < 0) temp = temp.right;
            else break;
        }
        return temp2;
    }

    public Node searchSuccesor(T value){
        Node node = search(value);
        node = node.right;
        while(true){
            if(node.left != null) node = node.left;
            else break;
        }
        return node;
    }

    public Node searchSuccesor(T value, Stack<Node> nodeStack){
        Node node = search(value, nodeStack);
        node = node.right;
        while(true){
            nodeStack.add(node);
            if(node.left != null) node = node.left;
            else break;
        }
        return node;
    }

    public Node searchSuccesor(Node node, Stack<Node> nodeStack){
        node = node.right;
        while(true){
            nodeStack.add(node);
            if(node.left != null) node = node.left;
            else break;
        }
        return node;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            return;
        }
        Node temp = search(value);
        if (temp.value.compareTo(value) > 0) {
            temp.left = new Node(value);
        } else {
            temp.right = new Node(value);
        }
    }

    public void remove(T value){
        Stack<Node> stack = new Stack<>();
        if(root == null) return;
        Node temp = search(value,stack);
        if(temp == null) return;
        if(! (temp.left == null) && ! (temp.right == null)){
            Node successor = searchSuccesor(temp, stack);
            stack.pop();
            Node parent2 = stack.pop();
            remove(successor,parent2);
            temp.value = successor.value;
        }else {
            stack.pop();
            Node parent = stack.peek();
            remove(temp,parent);
        }


    }

    protected void remove(Node value, Node parent){
        if(root == null) return;
        if(value == null) return;

        Node temp2;
        if(value.left == null) temp2 = value.right;
        else temp2 = value.left;

        if(parent.left == value) parent.left=temp2;
        else parent.right = temp2;

    }

    @Override
    public String toString() {
        List<Node> list = new Search().toList();
        return "BinaryTree{" + list + "}";
    }

    public int getHeight() {
        if (root == null) return 0;
        return root.resetHeight();
    }

    class Search{
        Stack<Node> nodestacks = new Stack<>();
        List<Node> list = new ArrayList<>();
        public List<Node> toList(){
            func(root);
            return list;
        }

        private void func(Node node){
            if(node == null) return;
            list.add(node);
            func(node.left);
            func(node.right);
            return;
        }
    }
}
