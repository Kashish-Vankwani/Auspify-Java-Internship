import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Step 1: Generate random number between 1 and 100
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int userGuess = 0;

        System.out.println("=========================================");
        System.out.println("    WELCOME TO NUMBER GUESSING GAME");
        System.out.println("=========================================");
        System.out.println("I have chosen a number between 1 and 100.");
        System.out.println("Can you guess what it is?\n");

        // Step 2 & 3: Game Loop with User Input and Logic Hints
        while (userGuess != targetNumber) {
            System.out.print("Enter your guess (1-100): ");
            
            // Input validation check
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            userGuess = scanner.nextInt();
            attempts++; // Step 4: Count number of attempts

            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Out of bounds! Please guess a number between 1 and 100.");
            } else if (userGuess < targetNumber) {
                System.out.println(">> HINT: Too Low! Try a higher number.\n");
            } else if (userGuess > targetNumber) {
                System.out.println(">> HINT: Too High! Try a lower number.\n");
            } else {
                System.out.println("\n🎉 CONGRATULATIONS! You guessed the correct number!");
                System.out.println("Target Number: " + targetNumber);
                System.out.println("Total Attempts: " + attempts);
                
                // Evaluation Score Criteria (Extra feature for better evaluation)
                if (attempts <= 5) {
                    System.out.println("Performance: Excellent / Master Guesser!");
                } else if (attempts <= 10) {
                    System.out.println("Performance: Good Job!");
                } else {
                    System.out.println("Performance: Keep Practicing!");
                }
            }
        }

        scanner.close();
    }
}