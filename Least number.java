import java.util.*;

public class LeastNumber {
    public static void main(String[] args) {
        int[] arr = {12, 5, 8, 2, 15, 3};

        int min = arr[0];   // assume first element is smallest

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.println("Least number in the array is: " + min);
    }
}