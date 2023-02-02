import BinaryTree.BinaryTreeNode;
import BinaryTree.BinaryUse;

public class prac {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> bt = BinaryUse.takeInputLevelWise2(true, 1, false);
        BinaryUse.printBinaryTree(bt);
        // BinaryUse.removeLeafs(bt);
        BinaryUse.mirror(bt);
        BinaryUse.printBinaryTree(bt);

    }

}