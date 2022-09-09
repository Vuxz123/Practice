import binarytree.AVLTree;
import binarytree.BinaryTree;

public class Main {
    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>(3);
//        list.set(0,0);
//        list.set(1,1);
//        list.set(2,2);
//        System.out.println("" + list + list.getLength());
//        list.remove(1);
//        System.out.println("" + list + list.getLength());
//        list.pushBack(3);
//        System.out.println("" + list + list.getLength());
//        list = new LinkedList<>();
//        System.out.println("" + list + list.getLength());
//        list.pushBack(0);
//        System.out.println("" + list + list.getLength());
//        list.pushBack(0);
//        list.pushBack(0);
//        list.pushBack(0);
//        list.pushBack(0);
//        System.out.println("" + list + list.getLength());

        BinaryTree<Integer> binaryTree = new AVLTree<>();
        System.out.println(binaryTree.getHeight());
        System.out.println(binaryTree);
        System.out.println();

        binaryTree.add(5);
        binaryTree.add(8);
        binaryTree.add(10);
        binaryTree.add(9);
        binaryTree.add(11);
        binaryTree.add(4);
        binaryTree.add(20);
        binaryTree.add(30);
        binaryTree.add(7);
        binaryTree.add(6);
        binaryTree.add(3);
        System.out.println(binaryTree.getHeight());
        System.out.println(binaryTree);
        System.out.println();

        System.out.println(binaryTree.searchSuccesor(8));
        System.out.println();

        binaryTree.remove(8);
        System.out.println(binaryTree.getHeight());
        System.out.println(binaryTree);
        System.out.println();


    }
}