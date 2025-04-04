import java.util.PriorityQueue;

public class dynamicMedian<Key extends Comparable<Key>> {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public dynamicMedian() {
        maxHeap = new PriorityQueue<>((a,b) -> b.compareTo(a));
        minHeap = new PriorityQueue<>();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty() && maxHeap.isEmpty();
    }

    public void insert(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()) {
            //peak是找出规定 poll是找出堆顶同时删除 offer等同于add
            maxHeap.offer(num);
        }else{
            minHeap.offer(num);
        }
        //不平衡调整，奇数情况下max会比min多一个，但是多两个就是不平衡。不平衡就是谁多了把谁放在另一边
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public int findMedian() {
        return maxHeap.peek();
    }//奇数和偶数（即正常的相等）都是放在maxHeap，默认的，不需要加if讨论。好帅的设计、

    public void removeMedian() {
        maxHeap.poll();
        if(maxHeap.size() < minHeap.size() ) {
            maxHeap.offer(minHeap.poll());
        }
    }

}