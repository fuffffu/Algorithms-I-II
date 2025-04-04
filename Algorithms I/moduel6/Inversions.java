public class Inversions {
    private int[] array;
    private int[] temp;
    int count;

    public Inversions(int[] array) {
        this.array = array;
        this.temp = new int[array.length];
        this.count = 0;
    }

    public int countInversions(){
        sort(0,array.length-1);//不用先声明变量
        return count;
    }

    public void sort(int left, int right){
        if(left >= right){
            return;
        }
        int mid=left+(right-left)/2;//切记加上left
        //while没有必要和sort一起，会很乱很乱
        sort(left,mid);//因为这里不是二分查找，所以还是要算上mid
        sort(mid+1,right);
        mergeCount(left,mid,right);
    }

    private void mergeCount(int left, int mid , int right){
        int i=left;
        int j=mid+1;
        int k=left;

        while(i<=mid && j<=right){
            if(array[i]<=array[j]){
                temp[k++]=array[i];//临时数组的目的是边排序边统计逆序对，做事的时候甚至帮以后的/别人的需求着想
            }else{
                temp[k++]=array[j];
                count += mid-i+1; //因为是回程时调用mergeCount，所以都是排序好了的状态。
            }
        }
        while(i<=mid){
            temp[k++]=array[i];
        }
        while(j<=right){
            temp[k++]=array[j];
        }
        for(int p=left;p<=right;p++){
            array[p]=temp[p];
        }
    }
}
