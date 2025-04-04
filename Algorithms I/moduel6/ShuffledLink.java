import javax.swing.text.LayoutQueue;
import java.util.LinkedList;

/**import java.util.LinkedList;
 * Java 的 LinkedList 内部是用一个私有的 Node 类实现的.这个 Node 是封装的，用户无法直接访问
 * 如果你需要对节点进行直接的操作（如自定义快慢指针），建议自己实现链表
 */

public class ShuffledLink {
    private LinkedList<Integer> list;
    public ShuffledLink(LinkedList<Integer> list) {
        this.list = list;
    }

    public LinkedList<Integer> shuffle() {
        return shuffleHelper(list);
    }

    private LinkedList<Integer> shuffleHelper(LinkedList<Integer> list) {
        if(list.size()<=1){
            return list;
        }
        int mid=list.size()/2;
        LinkedList left=new LinkedList();
        LinkedList right=new LinkedList();

        for(int i=0;i<mid;i++){
            left.add(list.removeFirst());//get(i)的时间复杂度是O(n)，而removefirst是o(1)
        }
        for(int i=list.size();i>mid;i--){
            right.add(list.removeFirst());
        }
        left=shuffleHelper(left);
        right=shuffleHelper(right);
        return mergeRandomly(left,right);
    }

    private LinkedList<Integer> mergeRandomly(LinkedList<Integer> left, LinkedList<Integer> right) {
        LinkedList<Integer> result=new LinkedList();
        while(!left.isEmpty() || !right.isEmpty()){
            if(left.isEmpty()){
                result.add(left.removeFirst());
            }else if(right.isEmpty()){
                result.add(right.removeFirst());
            }else{
                if(Math.random()<0.5){
                    result.add(left.removeFirst());
                }else{
                    result.add(right.removeFirst());
                }
            }
        }
        return result;
    }

}
