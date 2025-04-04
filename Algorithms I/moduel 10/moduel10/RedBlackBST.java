package moduel10;

public class RedBlackBST<Key extends Comparable<Key>> {

    private class Node {
        Key key;
        int value;
        Node left, right;

        // 另一个构造方法，支持初始化子节点（比如用于旋转时）
        public Node(Key key, int value) {
            this.key = key;
            this.value = value | 1;
            this.left = null;
            this.right = null;
        }

    }

    private class Key implements Comparable<Key> {
        private int value;

        public Key(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Key other) {
            return Integer.compare(this.value, other.value);
        }
    }

    private static final int RED = 1;
    private static final int BLACK = 0;

    private Node root;
    public RedBlackBST() {
        root = null;
    }


    private Node insert(Node node,Key key, int value) {
        if (node == null) return new Node(key, value | RED );
        //在空地创造新节点，|强制改成Red

        int cmp=key.compareTo(node.key);//为什么要进行key的对比
        if (cmp < 0) node.left = insert(node.left,key,value);
        else if (cmp > 0) node.right = insert(node.right,key,value);
        else node.value = value; //如果已经存在这个值，那就进行value的更新
        //外层调用insert主函数的时候如果存在root直接进行比较和递归，会为新插入结点自动找一个null的位置

        if(isRed(node.right) && !isRed(node.left)) node=rotateLeft(node);//右倾左旋
        if(isRed(node.left) && isRed(node.left.left)) node=rotateRight(node);//左左右旋
        if(isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

    private Node rotateLeft(Node node) {
        assert isRed(node.right);//左旋是因为出现了右倾。
        Node x=node.right;
        node.right=x.left;
        x.left=node;
        x.value = (x.value & ~1) | (node.value & 1);
        setRed(node);
        return x;//x变成新的根了
    }


    private Node rotateRight(Node node) {
        assert isRed(node.left);
        Node x=node.left;
        node.left=x.right;
        x.right=node;
        x.value = (x.value & ~1) | (node.value & 1);
        setRed(node);
        return x;
    }

    private void flipColors(Node node) {
        assert !isRed(node);
        assert isRed(node.left);
        assert isRed(node.right);
        setRed(node);
        setBlack(node.left);
        setBlack(node.right);
    }

    private void setRed(Node node){
        if(node != null) node.value |= 1;
    }
    private void setBlack(Node node){
        if(node != null) node.value &= ~1;
    }

    private boolean isRed(Node node){
        return node !=null &&  (node.value & 1) == 1;
    }

    private boolean isBlack(Node node){
        return node !=null && (node.value & 1) == 0;
    }

    // 插入主方法
    public void insert(Key key, int value) {
        root = insert(root, key, value);
        if (root != null) root.value &= ~1;  // 保证根节点始终是黑色
    }

    // 打印树结构的辅助方法
    private void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.key + " (" + (isRed(node) ? "RED" : "BLACK") + ")");
            //输出节点的值和颜色
            printTree(node.right);
        }
    }

    // 打印整个树
    public void print() {
        printTree(root);
    }

    // 测试代码
    public static void main(String[] args) {
        RedBlackBST<Integer> tree = new RedBlackBST<>();
        tree.insert(10, 1);
        tree.insert(20, 2);
        tree.insert(15, 3);
        tree.insert(30, 4);
        tree.insert(25, 5);
        tree.insert(5, 6);

        tree.print();  // 打印树结构
    }
}
