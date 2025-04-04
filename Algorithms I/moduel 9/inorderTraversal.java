import javax.swing.tree.TreeNode;

public class inorderTraversal {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            this.left = null;
            this.right = null;
        }
    }

    public void inorderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;  // 让前驱节点的右子树指向当前节点
                    curr = curr.left;   // 下沉到左子树
                } else {
                    // 如果前驱节点的右子树指向当前节点，说明我们已经遍历过左子树
                    prev.right = null;  // 恢复树结构
                    System.out.print(curr.val + " ");  // 访问当前节点
                    curr = curr.right;  // 转向右子树
                }
            } else {
                //没有左子树意味着是中间结点或者是叶子结点。
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
        }
    }
}
