import java.util.*;

public class Permutation {
    private int[] array1;
    private int[] array2;

    public Permutation(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    public boolean checkPermutation() {
        if (array1.length != array2.length) {
            return false;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : array1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(int i:array2) {
            if(!map.containsKey(i)) {
                return false;
            }
            map.put(i, map.get(i) - 1);
            if(map.get(i) < 0) {
                return false;
            }//如果出现次数超出另一个数组就会出错
        }
        return true;
    }//两个数组是排列关系，意味着它们包含 相同的元素，且 每个元素的出现次数相同，尽管顺序可能不同。

}
