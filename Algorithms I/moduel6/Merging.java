public class Merging {
    private int[] array1;
    private int[] array2;
    private int[] result;
    private int[] arrayHelper;
    int n;

    public Merging(int[] array1, int[] array2,int n) {
        this.n=n;
        this.array1 = array1;
        this.array2 = array2;
        this.result = new int[2*n];
        this.arrayHelper = new int[n];
    }

    public void merge(){
        int j=0,k=n,z=0;
        System.arraycopy(array1,0,arrayHelper,0,n);
        while(j<array2.length && k < n ){
            if(array2[j]<=arrayHelper[k]){
                result[z++] = array2[j++];
            }else{
                result[z++] = array2[k++];
                /** k++;
                 * z++;
                 */
            }
        }
       while(j<array2.length){
            result[z++] = array2[j++];
        }//while本身就包含了判断if j<array2.length
        while (k < arrayHelper.length) {
            result[z++] = arrayHelper[k++];
        }
   }
}

