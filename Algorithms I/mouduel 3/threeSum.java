import java.util.Arrays;

public class threeSum {
    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        int count = 0;

        for (int i = 0; i < N - 2; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = N - 1;
            while (left < right) {
                int sum = a[i] + a[left] + a[right];
                if (sum == 0) {
                    count++;
                    left++;
                    right--;
                    while (left < right && a[left] == a[left + 1]) {
                        left++;
                    }
                    while (left < right && a[right] == a[right - 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}







