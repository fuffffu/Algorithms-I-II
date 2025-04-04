import java.util.PriorityQueue;
import java.util.Random;


public class RandomizedPriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public RandomizedPriorityQueue(int capacity) {
        pq = (Key[]) new Object[capacity+1];
        N = 0;
    }
    public Key Sample(){
        Random rand = new Random();
        if(N == 0) return null;
        int i = rand.nextInt(N)+1;
        Key n=pq[i];
        delRandom(i);
        return n;
    }

    public void delRandom(int i) {
        if (N == 0) return;
        exch(i,N);
        pq[N--]=null;//原堆顶被删除
        skin(i);//堆底被沉
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void skin(int k) {
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);//上面的大的沉下去了
            k=j; //现在轮到下一个
        }
    }

    private boolean less(int i, int j) {
        return ((Comparable<Key>)pq[i]).compareTo(pq[j])<0;
    }

}
