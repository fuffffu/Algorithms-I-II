public class bitonicArray {
    int traget;
    int[] bitonic;
    public boolean search(int target,int[] bitonic) {
           int peak=peak(bitonic,0,bitonic.length-1);
        if (binarySearchInc(target, bitonic, 0, peak)) {
            return true;
        }else if(binarySearchDec(target, bitonic, peak, bitonic.length - 1)) {
            return true;
        }else{
            return false;
        }
    }

    //先用二分法找到峰值

    private int peak(int[] bitonic,int left,int right) {
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(bitonic[mid]<bitonic[mid+1]) {
                left=mid+1;
            }else{
                right=mid-1;
            }
        }return -1;

    }

    private boolean binarySearchInc(int target,int[] bitonic,int left,int right) {
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(bitonic[mid]==target) {
                return true;
            }else if(bitonic[mid]>target) { //t在左
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return false;
    }
    public boolean binarySearchDec(int target,int[] bitonic,int left,int right) {
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(bitonic[mid]==target) {
                return true;
            }else if(bitonic[mid]>target) { //t在右边
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return false;
    }
}
