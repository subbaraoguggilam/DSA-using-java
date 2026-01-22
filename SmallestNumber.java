public class SmallestNumber {
    public static int findSmallest(int[] arr) {
        // Check if array is empty
        if (arr.length == 0) {
            System.out.println("Array is empty!");
            return Integer.MIN_VALUE; // or throw exception
        }
        
        int smallest = arr[0]; // Assume first element is smallest
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        
        return smallest;
    }
    
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1, 9, 3};
        int smallest = findSmallest(numbers);
        System.out.println("Smallest number: " + smallest); // Output: 1
    }
}
