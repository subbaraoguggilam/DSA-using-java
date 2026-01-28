public class AverageOfArray {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int n = arr.length;

        int sum = 0;

        // Calculate sum
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Calculate average
        double average = (double) sum / n;

        System.out.println("Average of array elements: " + average);
    }
}