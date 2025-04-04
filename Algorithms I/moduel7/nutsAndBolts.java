import java.util.*;

public class nutsAndBolts {
    private int[] nuts;
    private int[] bolts;
    private Map<Integer,Integer> pairs;

    public nutsAndBolts(int[] nuts, int[] bolts) {
        this.nuts = nuts;
        this.bolts = bolts;
        this.pairs = new HashMap<Integer,Integer>();
    }

    private void shuffle(int[] array){
        Random rand = new Random();
        for(int i=array.length-1; i>0; i++){
            int j=rand.nextInt(i+1);
            swap(array,i,j);
        }
    }

    private void match(int low, int high) {
        if (low >= high) return;

        // 以low为基准找到匹配的boltindex，同时进行大概的排序
        int pivotBoltIndex = partition(bolts, low, high, nuts[low]);
        // 以刚才找到的boltindex作为基准再找刚才的low，目的是为了让nuts内部也大概排序
        int pivotNutIndex = partition(nuts, low, high, bolts[pivotBoltIndex]);

        // Step 3: Store the match
        pairs.put(nuts[pivotNutIndex], bolts[pivotBoltIndex]);

        // Step 4: 因为基本思路还是以螺母为基准找对应的螺帽，所以match递归扮演遍历螺母的作用。所以传入的是螺母的index
        match(low, pivotNutIndex - 1);
        match(pivotNutIndex + 1, high);
        /**递分治算法特别适合“比较类问题”，因为每次都能利用基准值快速排除一部分不相关的数据。时间复杂度是O(nlogn)，显然更高效。
         * 遍历无法直接把问题分为左右两部分，也就无法像递归那样只处理一个缩小的问题子集。
         */
    }

    private int partition(int[] array, int low, int high,int pivotNut){
        int i=low;
        int j=high;
        while(i<=j){
            if(array[i]<pivotNut){
                i++;
            }else if(array[j]>pivotNut){
                j++;
            }else if(array[i]==pivotNut){
                pairs.put(array[i],pivotNut);
                break;
            }else if(array[j]==pivotNut){
                pairs.put(array[j],pivotNut);
                break;
            }else {
                swap(array,i,j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void swap(int[]array,int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}


