import java.util.*;

public class carNumber {
   public int findTaxiNumber(int n) {
       Map<Integer, List<String>> sumOfCubes = new HashMap<>();
       int result=0;
       for(int a=1;a<n;a++){
           for(int b=a+1;b<n;b++){
               int sum=a*a*a+b*b*b;
               String pair=a+"^3"+b+"^3";

               if(sumOfCubes.containsKey(sum)){
                   sumOfCubes.get(sum).add(pair);
               }else{
                   sumOfCubes.put(sum, new ArrayList<>(Arrays.asList(pair)));//a^3 + b^3作为列表的一个元素
               }
           }
       }

       for(List<String> pairs:sumOfCubes.values()){
           if(pairs.size()>1){
               result++;
           }
       }
       return result;
   }
}
