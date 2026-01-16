import java.util.Arrays;

public class RotateArray {

    static void rotateclockwise(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        // Handle cases where k > n
        k = k % n;

        int i, j;

        // Reverse last k elements
        for (i = n - k, j = n - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // Reverse first n-k elements
        for (i = 0, j = n - k - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // Reverse the entire array
        for (i = 0, j = n - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;

        System.out.println("Original Array: " + Arrays.toString(arr));
        rotateclockwise(arr, k);
        System.out.println("Rotated Array:  " + Arrays.toString(arr));
    }
}
