import java.util.Scanner;

public class CalculatorDSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        System.out.println("Choose operation:");
        System.out.println("1.Add  2.Subtract  3.Multiply  4.Divide");
        int choice = sc.nextInt();

        double result = 0;

        switch(choice) {
            case 1:
                result = a + b;
                System.out.println("Result = " + result);
                break;
            case 2:
                result = a - b;
                System.out.println("Result = " + result);
                break;
            case 3:
                result = a * b;
                System.out.println("Result = " + result);
                break;
            case 4:
                if(b != 0)
                    System.out.println("Result = " + (a / b));
                else
                    System.out.println("Division by zero not allowed");
                break;
            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}