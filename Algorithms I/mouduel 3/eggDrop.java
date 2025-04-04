import java.util.Random;

public class eggDrop {
    int n;
    Random rand =new Random();
    int T=rand.nextInt(n);


    public int version0(){
        for(int i=0;i<n;i++){
            if(i==T){
                return i;
            }
        }
        return -1;
    }

    public int version1(){
        int eggNum =(int) (Math.log(n) / Math.log(2));
        int bottom=1;
        int top=n;
        while(bottom<=top && eggNum>0){
            int mid=(top+bottom)/2;
            if(mid==T){
                eggNum--;
                return mid;
            }else if(mid>T){
                eggNum--;
                top=mid-1;
            }else{
                bottom=mid+1;
            }
        }
        return -1;
    }

    public int version2(){
        int eggNum =(int) (Math.log(T) / Math.log(2));
        int bottom=1;
        int top=n;
        int count=0;
        while(bottom<=top && eggNum>0 && count<2*eggNum){
            count++;
            int mid=(top+bottom)/2;
            if(mid==T){
                eggNum--;
                return mid;
            }else if(mid>T){
                eggNum--;
                top=mid-count;
            }else{
                bottom=mid+count;
            }
        }
        return -1;
    }

    public int version3(){
        int eggNum = 2;
        int count=1;
        int next;
        while(count<=2*n && eggNum>1) {
            count++;
            for (int i = 1; i <= n; i += i) {
                if (i >= T) {
                    eggNum--;
                    next = i;}
            }
        }
        for(int i=1;i<next;i++){                                                                                                                                                                  t;i++){
            count++;
            if(i==T){
            eggNum--;
            return i;
            }
        }
            return -1;}}





    public int version4(int c){
        int eggNum = 2;
        int bottom=1;
        int top=n;
        int count=0;
        int sum=1;
        while(bottom<=top && eggNum>0 && count<c*T){
            int mid=(top+bottom)/2;
            count++;
            if(mid==T){
                eggNum--;
                return mid;
            }else if(mid>T){
                eggNum--;
                for(int i=sum;i<mid;i++){
                if (mid == T) {
                    eggNum--;
                    return sum;
                }
            }
            }else{
                bottom=mid+sum;
                sum+=sum;
            }
        }
        return -1;
    }

}
