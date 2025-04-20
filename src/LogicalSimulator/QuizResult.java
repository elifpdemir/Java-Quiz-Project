package LogicalSimulator;

// This class holds the results of a quiz section.
public class QuizResult {
    private final String sectionName;
    private final int score;
    private final int totalQuestions;
    private final long totalTimeSeconds; // if time is not being kept, it can be -1 or another indicator

    // Constructor for sections with time tracking
    public QuizResult(String sectionName, int score, int totalQuestions, long totalTimeSeconds) {
        this.sectionName = sectionName;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.totalTimeSeconds = totalTimeSeconds;
    }

    // Constructor for sections without time tracking
    public QuizResult(String sectionName, int score, int totalQuestions) {
        this(sectionName, score, totalQuestions, -1); // Mark as no duration
    }

    // Getters (To read the results from outside)
    public String getSectionName() { return sectionName; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public long getTotalTimeSeconds() { return totalTimeSeconds; }

    // A simple toString to print the result 
    @Override
    public String toString() {
        String result = String.format("📊 Section: %s\n   Score: %d/%d",
                                      sectionName, score, totalQuestions);
        if (totalTimeSeconds >= 0) {
            result += String.format("\n   Total Time: %d seconds", totalTimeSeconds);
            if (totalQuestions > 0) {
                result += String.format("\n   Average Time: %.2f seconds/question", (double)totalTimeSeconds / totalQuestions);
            }
        }
        return result;
    }
}
