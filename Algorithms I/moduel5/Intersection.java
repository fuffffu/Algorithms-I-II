import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection {
    private Point[] pointsA;
    private Point[] pointsB;

    public Intersection(Point[] pointsA, Point[] pointsB) {
        this.pointsA = Arrays.copyOf(pointsA, pointsA.length);
        this.pointsB = Arrays.copyOf(pointsB, pointsB.length);//如果直接相等而不是copy进来会让我们的修改影响到原数组
    }

    public int getBoth(){
        //TimeSort是一种优化的归并排序，时间复杂度为nlogn
        Arrays.sort(pointsA,(p1,p2) -> {
            if(p1.x != p2.x) return Integer.compare(p1.x, p2.x); //如果两个点的 x 坐标不同， 比较 x 坐标大小
            return Integer.compare(p1.y, p2.y);// 如果 x 坐标相同，比较 y 坐标大小。
        });
        //sort函数第二个参数传入比较器，这里传入Lambda 表达式，它实现了 Comparator<Point> 接口。定义如何比较这两个对象
        Arrays.sort(pointsB,(p1,p2) -> {
            if(p1.x != p2.x) return Integer.compare(p1.x, p2.x);
            return Integer.compare(p1.y, p2.y);
        });
        //为了后续使用双指针法高效地找到两个数组的交集，必须先对两个数组进行排序。
        //直接 return 是为了提高效率，一旦 x 坐标不同，就不需要再比较 y 坐标，立即确定排序顺序。
        //return 在 Lambda 中：仅用于返回比较结果给 Arrays.sort，不影响外部方法的执行流程。

        int i = 0, j = 0, count = 0;
        //双指针时间复杂度为n
        while(i < pointsA.length && j < pointsB.length) {
            Point p1 = pointsA[i];
            Point p2 = pointsB[j];
            if(p1.x == p2.x && p1.y == p2.y) {
                count++;
                i++;
                j++;
            }else if(p1.x <= p2.x || (p1.x == p2.x && p1.y <= p2.y)) {
                i++;
            }else{
                j++;
            }
        }
        return count;
    }
}
