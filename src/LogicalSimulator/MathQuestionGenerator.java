package LogicalSimulator;

import java.util.Random;
import java.util.Scanner;

public class MathQuestionGenerator {
    // QUESTION_COUNT can remain constant
    private static final int QUESTION_COUNT = 5;
    // correctAnswers and totalTime should not be static, they should be method specific

    public static QuizResult askMathQuestions(Scanner scanner) {
        // Local variables for the method to keep score and time within itself
        int localCorrectAnswers = 0;
        long localTotalTime = 0;
       

        Random random = new Random();
        System.out.println("\n🔢 Starting Random Math Questions Section...");
        System.out.println("The timer is starting... Please solve the questions as soon as possible.");
         // Time is kept separate for each question.

        for (int i = 1; i <= QUESTION_COUNT; i++) {
            char operator = generateRandomOperator(random);
            int num1, num2;

            if (operator == '/') {
                num2 = random.nextInt(9) + 1;
                int factor = random.nextInt(10) + 1;
                num1 = num2 * factor;
            } else {
                num1 = random.nextInt(100);
                num2 = random.nextInt(30);
            }

            int correctAnswer = calculateAnswer(num1, num2, operator);

            System.out.println("\nQuestion " + i + ": What is " + num1 + " " + operator + " " + num2 + "?");
            System.out.print("Your answer: ");

            int userAnswer = Integer.MIN_VALUE; // initial value
            long startTime = System.currentTimeMillis();
            long endTime = startTime; // initial value

            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                endTime = System.currentTimeMillis();
                scanner.nextLine(); // Consume newline left-over
            } else {
                 System.out.println("Invalid input detected. Skipping question.");
                 if(scanner.hasNextLine()) scanner.nextLine(); // clear incorrect data
                 continue; // Skip the question
            }


            long duration = (endTime - startTime) / 1000;
            localTotalTime += duration;

            if (userAnswer == correctAnswer) {
                System.out.println("✅ Correct! (" + duration + " seconds)");
                localCorrectAnswers++;
            } else {
                System.out.println("❌ Incorrect. The correct answer was " + correctAnswer + " (" + duration + " seconds)");
            }
        }

        System.out.println("🔢 Math Section Completed!");
        // scanner.close(); // DO NOT CLOSE the scanner here! it will close in Main.

        return new QuizResult("Random Math", localCorrectAnswers, QUESTION_COUNT, localTotalTime);
    }

    private static char generateRandomOperator(Random random) {
        char[] operators = {'+', '-', '*', '/'};
        return operators[random.nextInt(operators.length)];
    }

    private static int calculateAnswer(int a, int b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> (b != 0) ? a / b : 0; // check division by zero
            default -> 0;
        };
    }
}

