package binarytree;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class AVLTree<T extends Comparable> extends BinaryTree<T>{
    protected void rotateLeft(Node target ,Node root, boolean right){
        Node temp = target.right;
        target.setRight(temp.left);
        temp.setLeft(target);
        if(root != null){
            if(right){
                root.setRight(temp);
            }else{
                root.setLeft(temp);
            }
        }else{
            this.root = temp;
        }
    }

    protected void rotateRight(Node target ,Node root, boolean right){
        Node temp = target.left;
        target.setLeft(temp.right);
        temp.setRight(target);
        if(root != null){
            if(right){
                root.setRight(temp);
            }else{
                root.setLeft(temp);
            }
        }
    }

    @Override
    public void add(T value) {
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            root = new Node();
            root.value = value;
            return;
        }
        Node temp = search(value, stack);
        if (temp.value.compareTo(value) > 0) {
            temp.left = new Node(value);
        } else {
            temp.right = new Node(value);
        }

        standardize(stack);

    }

    @Override
    public void remove(T value) {
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

        standardize(stack);
    }

    private void standardize(Stack<Node> stack){
        while(!stack.empty()){
            Node temp1 = stack.pop();

            boolean bl,bl1;

            int cp = temp1.getBalance();

            if(cp < -1) bl = false;
            else if(cp > 1) bl = true;
            else {
                continue;
            }

            Node temp2;

            if(bl) temp2 = temp1.left;
            else temp2 = temp1.right;

            cp = temp2.getBalance();

            if(cp == -1) bl1 = false;
            else bl1 = true;

            if(bl == bl1){
                Node parent;
                boolean right;
                try{
                    parent = stack.pop();
                    right = parent.right == temp1;
                }catch (EmptyStackException e){
                    parent = null;
                    right = true;
                }
                if(bl) {
                    rotateRight(temp1,parent,right);
                }
                else rotateLeft(temp1,parent,right);
            }else{
                Node parent;
                boolean right;

                parent = temp1;
                right = parent.right == temp2;
                if(bl) rotateRight(temp2,parent,right);
                else rotateLeft(temp2,parent,right);

                parent = stack.peek();
                right = parent.right == temp1;
                if(bl1) rotateRight(temp1,parent,right);
                else rotateLeft(temp1,parent,right);
            }
        }
    }

    @Override
    public String toString() {
        List<Node> list = new Search().toList();
        return "AVLTree{" + list + "}";
    }
}
