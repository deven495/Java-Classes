package TreeNode;

import java.util.ArrayList;

public class TreeNode<T> {
    protected T data;
    ArrayList<TreeNode<T>> children;

    TreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

}
