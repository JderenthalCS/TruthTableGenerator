import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a boolean expression: ");
        String expression = scanner.nextLine();

        GenerateTable.generateTable(expression);
    }
}