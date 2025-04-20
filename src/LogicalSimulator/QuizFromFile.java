package LogicalSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuizFromFile {

    private static final int LINE_WRAP = 80; // Line length limit

    // Helper method for line length control
    private static void printWrapped(String text) {
        while (text.length() > LINE_WRAP) {
            int spaceIndex = text.lastIndexOf(" ", LINE_WRAP);
            if (spaceIndex == -1) spaceIndex = LINE_WRAP; // If there is no space, cut completely

            System.out.println(text.substring(0, spaceIndex));
            text = text.substring(spaceIndex).trim(); //continue the rest
        }
        System.out.println(text); //print the rest
    }

    public static QuizResult runQuiz(String filename, Scanner input) {
        System.out.println("\n📜 Starting Quiz from logical question Section...");
        File file = new File(filename);
        Scanner fileScanner = null;
        int score = 0;
        int questionCount = 0;

        try {
            fileScanner = new Scanner(file);

            int lineNum = 0;
            while (fileScanner.hasNextLine()) {
                lineNum++;
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length < 7) {
                    System.err.println("Warning: Skipping malformed line " + lineNum + " in " + filename);
                    continue;
                }

                questionCount++;

                String question = parts[0].trim();
                String optionA = parts[1].trim();
                String optionB = parts[2].trim();
                String optionC = parts[3].trim();
                String optionD = parts[4].trim();
                String correctAnswer = parts[5].trim().toUpperCase();
                String level = parts[6].trim();

                System.out.println("\nQuestion " + questionCount + " (" + level + "):");
                printWrapped(question); // Print the question by splitting it into lines

                System.out.print("A) ");
                printWrapped(optionA);
                System.out.print("B) ");
                printWrapped(optionB);
                System.out.print("C) ");
                printWrapped(optionC);
                System.out.print("D) ");
                printWrapped(optionD);

                System.out.print("Your answer (A/B/C/D): ");
                String userAnswer = "";
                if (input.hasNextLine()) {
                    userAnswer = input.nextLine().trim().toUpperCase();
                } else {
                    System.out.println("No input detected. Skipping question.");
                    continue;
                }

                if (userAnswer.equals(correctAnswer)) {
                    System.out.println("✅ Correct!");
                    score++;
                } else {
                    System.out.println("❌ Wrong! Correct answer was: " + correctAnswer);
                }
            }
            
            //Return results instead of printing them
            System.out.println("📜 File Quiz Section Completed!");
            return new QuizResult("File Quiz ", score, questionCount);

        } catch (FileNotFoundException e) {
        	//Return empty or a custom QuizResult on error
            System.err.println("Error: Quiz file not found! (" + filename + ")");
            return new QuizResult("File Quiz (" + filename + ") - Error", 0, 0);
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }
}
