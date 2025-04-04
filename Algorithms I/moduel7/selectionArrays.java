public class selectionArrays {
    private int[] array1;
    private int[] array2;
    private int[] array3;
    int k=0;

    public selectionArrays(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
        this.array3 = new int[array1.length + array2.length];
    }

    public selectionArrays(int[] array1, int[] array2, int k) {
        this.array1 = array1;
        this.array2 = array2;
        this.array3 = new int[array1.length + array2.length];
        this.k=k;
    }

    public int getK(int[] array1,int start1,int[] array2,int start2,int k) {
        if (start1 >= array1.length) return array2[start2 + k - 1]; //假设一个数组已经耗尽/为空，我们直接从另一个数组找。这里的k由于递归不是固定的k
        if (start2 >= array2.length) return array1[start1 + k - 1];//当数组越界时，避免访问无效位置.在逻辑中表示该数组已经没有更小的元素，指导算法排除其他数组的部分范围。
        if (k == 1) return Math.min(array1[start1], array2[start2]);
        int mid1 = (start1 + k / 2 - 1 < array1.length) ? array1[start1 + k / 2 - 1] : Integer.MAX_VALUE;//是 Java 标准库中定义的常量，它表示 int 类型的最大值.2147483647
        int mid2 = (start2 + k / 2 - 1 < array2.length) ? array2[start2 + k / 2 - 1] : Integer.MAX_VALUE;//


        if (mid1 < mid2) return getK(array1, start1 + k / 2, array2, start2, k - k / 2); //排除前k/2的元素后找第k-k/2个元素
        else return getK(array1, start1, array2, start2 + k / 2, k - k / 2);

    }

}
