import java.util.PriorityQueue;
import java.util.TreeMap;

public class GeneralizedQueue<T> {
    PriorityQueue<T> pq;
    TreeMap<Integer,T> indexMap;
    private int index;

    public GeneralizedQueue() {
        pq = new PriorityQueue<>();
        indexMap = new TreeMap<>();
        index = 0;
    }

    public void append(T value) {
        pq.offer(value);
        indexMap.put(index++, value);
    }

    public T removeFront() {
        if(pq.isEmpty()) return null;
        T item = pq.poll();
        indexMap.pollFirstEntry();//二叉树里移除第一个
        return item;
    }

    public T get(int i) {
        return indexMap.getOrDefault(i,null);
    }

    public void remove(int i){
        if(indexMap.containsKey(i)){
            T item = indexMap.remove(i);
            pq.remove(item);
        }
    }

}
