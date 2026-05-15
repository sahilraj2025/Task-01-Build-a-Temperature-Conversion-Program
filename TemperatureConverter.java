import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Temperature Converter!");

        while (true) {
            System.out.print("\nEnter the temperature value (or type 'exit' to quit): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            double value;
            try {
                value = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numerical temperature.");
                continue;
            }

            System.out.println("Select the original unit of measurement:");
            System.out.println("1. Celsius (C)");
            System.out.println("2. Fahrenheit (F)");
            System.out.println("3. Kelvin (K)");
            System.out.print("Your choice (1/2/3 or C/F/K): ");

            String unitInput = scanner.next().toUpperCase();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

            if (unitInput.equals("1") || unitInput.equals("C")) {
                celsius = value;
                fahrenheit = (celsius * 9 / 5) + 32;
                kelvin = celsius + 273.15;
                System.out.println("\n--- Converted Temperatures ---");
                System.out.printf("Fahrenheit: %.2f °F\n", fahrenheit);
                System.out.printf("Kelvin: %.2f K\n", kelvin);
                System.out.println("------------------------------");
            } else if (unitInput.equals("2") || unitInput.equals("F")) {
                fahrenheit = value;
                celsius = (fahrenheit - 32) * 5 / 9;
                kelvin = celsius + 273.15;
                System.out.println("\n--- Converted Temperatures ---");
                System.out.printf("Celsius: %.2f °C\n", celsius);
                System.out.printf("Kelvin: %.2f K\n", kelvin);
                System.out.println("------------------------------");
            } else if (unitInput.equals("3") || unitInput.equals("K")) {
                kelvin = value;
                celsius = kelvin - 273.15;
                fahrenheit = (celsius * 9 / 5) + 32;
                System.out.println("\n--- Converted Temperatures ---");
                System.out.printf("Celsius: %.2f °C\n", celsius);
                System.out.printf("Fahrenheit: %.2f °F\n", fahrenheit);
                System.out.println("------------------------------");
            } else {
                System.out.println("Invalid unit selected. Please try again.");
            }
        }

        scanner.close();
    }
}
