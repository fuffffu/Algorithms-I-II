import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class decimalDominants {
    private int[] array;
    private Map<Integer,Integer> constituency;
    private int count=0;

    public decimalDominants(int[] array) {
        this.array = array;
        constituency = new HashMap<Integer,Integer>();
    }

    public int countDominants() {
        for (int num : array) {
            if (constituency.containsKey(num)) {
                constituency.put(num, constituency.get(num) + 1);
            } else {
                if (constituency.size() < 9) {
                    constituency.put(num, 1);
                } else {
                    // 减少计数，并移除计数为 0 的候选
                    for (int key : new HashSet<>(constituency.keySet())) { //constituency只是统计出现最多的几个候选人，并不一定真的大于n/10，所以后面需要再同统计一遍真实的频率
                        constituency.put(key, constituency.get(key) - 1); //这里削弱行为是为了让足够有竞争力的对手保留下来，排除掉濒临淘汰的
                        if (constituency.get(key) == 0) {
                            constituency.remove(key);
                        }
                    }
                }
            }
        }

        Map<Integer, Integer> actualCounts = new HashMap<>();
        for (int num : array) {
            if (constituency.containsKey(num)) {
                actualCounts.put(num, constituency.getOrDefault(num, 0) + 1);
            }
        }
        for (int key : constituency.keySet()) {
            if (actualCounts.get(key) > array.length / 10) count++;
        }
        return count;
    }

}
