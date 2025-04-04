import java.util.HashMap;
import java.util.Random;

public class fourSum {
    private int[] array;

    public fourSum(int[] nums) {
        this.array = nums;
    }

    /**
     * 解决 4-SUM 问题：
     * 给定数组array，判断是否存在四个不同的下标 i, j, k, l 使得
     * array[i] + array[j] = array[k] + array[l]
     * 算法思路：
     *   遍历所有两数组合，对每个数对 (i,j) 计算其和 sum，
     *   将 sum 映射到该数对 (i,j) 存入 HashMap 中。
     *   如果在放入时发现该和已经存在，则检查之前保存的数对和当前数对是否没有公共下标，
     *   如果满足，则找到了满足条件的四个数。
     */
    public void findFourSum() {
        int n = array.length;
        // 用于存储“数对和”与数对 (i,j) 之间的映射
        HashMap<Integer, Pair> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = array[i] + array[j];
                if (map.containsKey(sum)) {
                    Pair p = map.get(sum);
                    // 确保四个下标互不相同
                    if (p.i != i && p.i != j && p.j != i && p.j != j) {
                        System.out.println("找到了满足条件的四个数：");
                        System.out.println("下标： " + p.i + ", " + p.j + ", " + i + ", " + j);
                        System.out.println("对应的值： " + array[p.i] + ", " + array[p.j] + ", " + array[i] + ", " + array[j]);
                        return;
                    }
                } else {
                    // 如果该和还没有出现，则存入当前的数对
                    map.put(sum, new Pair(i, j));
                }
            }
        }
        System.out.println("没有找到满足 4-SUM 条件的四个数。");
    }

    // 内部类，用于存储数对的两个下标
    private class Pair {
        int i, j;
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // 主方法，测试代码
    public static void main(String[] args) {
        // 示例输入数组
        int[] nums = {3, 4, 7, 1, 2, 9, 8};
        fourSum fs = new fourSum(nums);

        // 如果需要对数组排序，可以调用下面的排序代码：
        // Integer[] numsInteger = new Integer[nums.length];
        // for (int i = 0; i < nums.length; i++) {
        //     numsInteger[i] = nums[i];
        // }
        // fs.sort(numsInteger);
        // for (Integer num : numsInteger) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();

        // 执行 4-SUM 检查
        fs.findFourSum();
    }
}
