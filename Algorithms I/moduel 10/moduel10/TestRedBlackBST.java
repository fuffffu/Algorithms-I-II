package moduel10;

public class TestRedBlackBST {

    public static void main(String[] args) {
        // 创建一个新的红黑树
        RedBlackBST<Integer> tree = new RedBlackBST<>();

        // 插入测试数据
        tree.insert(10, 1);
        tree.insert(20, 2);
        tree.insert(15, 3);
        tree.insert(30, 4);
        tree.insert(25, 5);
        tree.insert(5, 6);

        // 打印树结构
        System.out.println("Tree structure after insertions:");
        tree.print();  // 打印树结构

        // 进一步插入一个新元素并观察树变化
        System.out.println("\nInserting 8 into the tree:");
        tree.insert(8, 7);
        tree.print();  // 打印树结构

        // 再插入一些节点
        System.out.println("\nInserting 35 and 40 into the tree:");
        tree.insert(35, 8);
        tree.insert(40, 9);
        tree.print();  // 打印树结构

        // 测试节点插入后，红黑树性质
        System.out.println("\nFinal tree structure:");
        tree.print();
    }
}

