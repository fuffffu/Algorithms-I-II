import java.util.Scanner;

public class RandomWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 1;
        double d = 0;
        String s = null;
        String c = null;
        while (in.hasNext()){
            s = in.next();
            d = Math.random();
            if (d <= 1.0/n) c = s;
        }
        in.close();

        System.out.println(c);
    }
}
