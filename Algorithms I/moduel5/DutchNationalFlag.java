public class DutchNationalFlag {
    private String[] bucket;
    public DutchNationalFlag(String[] bucket) {
        this.bucket = bucket;
    }//白蓝红 红白蓝 红蓝 白红 蓝红 蓝白

    public void swap(){
        int low=0;
        int mid=0;
        int high=bucket.length-1;
        while(mid<=high){
            String color=color(mid);//好神奇，让mid作为核心处理数组
            if(color.equals("Red")){
                swap(low,mid);
                low++;
                mid++;
            }else if(color.equals("White")){
                mid++;
            }else if(color.equals("Blue")){
                swap(mid,high);
                high--;
            }
        }
    }

    private void swap(int a,int b){
        String temp=bucket[a];
        bucket[a]=bucket[b];
        bucket[b]=temp;
    }

    public String color(int index){
        if(index < 0 || index >= bucket.length){
            throw new IndexOutOfBoundsException();
        }
        return bucket[index];
    }
    
}
