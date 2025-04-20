package LogicalSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApp {

    public static void main(String[] args) {
        System.out.println("🚀 Welcome to the Logical Simulator Quiz App! 🚀");

        // Create a single Scanner to be used throughout the entire application
        Scanner mainScanner = new Scanner(System.in);

        //A list to store the results
        List<QuizResult> allResults = new ArrayList<>();

        // --- Part 1: Quiz from File ---
        //Call the method and add the returned result to the list
        QuizResult fileResult = QuizFromFile.runQuiz("src/LogicalSimulator/questions.txt", mainScanner);
        allResults.add(fileResult);

        System.out.println("\n---------------------------------------------\n"); // Bölümler arası ayraç

        // --- Chapter 2: Random Math Questions ---
        // Call the method and add the returned result to the list
        QuizResult mathResult = MathQuestionGenerator.askMathQuestions(mainScanner);
        allResults.add(mathResult);

        System.out.println("\n=============================================");
        System.out.println(" R E S U L T S   S U M M A R Y");
        System.out.println("=============================================");

        int grandTotalScore = 0;
        int grandTotalQuestions = 0;

        // Print all stored results
        for (QuizResult result : allResults) {
            System.out.println(result.toString()); // Uses the toString method on QuizResult
            System.out.println("---");
            // Calculate grand totals (if significant)
            grandTotalScore += result.getScore();
            grandTotalQuestions += result.getTotalQuestions();
        }

        if (grandTotalQuestions > 0) {
             System.out.println("🏆 Overall Performance:");
             System.out.printf("   Total Correct Answers: %d\n", grandTotalScore);
             System.out.printf("   Total Questions Asked: %d\n", grandTotalQuestions);
             System.out.printf("   Overall Accuracy: %.2f%%\n",
                              (double) grandTotalScore * 100 / grandTotalQuestions);
        }


        System.out.println("\n🏁 Application Finished. Goodbye! 🏁");

        // Close the main Scanner when the application is finished
        mainScanner.close();
    }
}	