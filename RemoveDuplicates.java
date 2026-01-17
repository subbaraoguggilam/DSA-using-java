import java.util.Arrays;

public class RemoveDuplicates {
    public static int removeDuplicatesSorted(int[] arr) {
        if (arr.length == 0) return 0;
        
        Arrays.sort(arr); // Sort first
        int uniqueIndex = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[uniqueIndex]) {
                uniqueIndex++;
                arr[uniqueIndex] = arr[i];
            }
        }
        return uniqueIndex + 1; // Length of array without duplicates
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 1, 5};
        int newLength = removeDuplicatesSorted(arr);
        
        System.out.print("Array without duplicates: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i] + " ");
        }
        // Output: 1 2 3 4 5
    }
}
