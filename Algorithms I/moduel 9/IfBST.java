public class IfBST {
    public boolean isBST(TreeNode root) {
        return isBSThelper(root,Long.MIN_VALUE, Long.MAX_VALUE);//一开始的最大最小值不限，min_value是负无穷，max_value是正无穷
    }

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

    private boolean isBSThelper(TreeNode root, long min, long max) {
        if (root == null) return true;//
        if (root.val <= min || root.val >= max) return false;
        return isBSThelper(root.left, min, root.val) && isBSThelper(root.right, root.val, max);
    }
    //调用的结果应该返回给父节点的调用者
}
