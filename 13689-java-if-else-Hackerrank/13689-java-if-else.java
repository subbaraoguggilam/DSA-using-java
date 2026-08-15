import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); 
        scanner.close();
        
        if (n%2 != 0){
            System.out.println("Weird");
        }else if(n>=2 && n<=5){
            System.out.println("Not Weird");
        }else if(n>=6 && n<=20){
            System.out.println("Weird");
        }else{
            System.out.println("Not Weird");
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna