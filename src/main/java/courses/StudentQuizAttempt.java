package courses;

import java.util.HashMap;
import java.util.Map;

public class StudentQuizAttempt {
    private String studentID;
    private String lessonID;
    private Quiz quiz;
    private Map<Question, Integer> studentAnswers; // student answer for each question
    private int score;
    private boolean passed;

    public StudentQuizAttempt(String studentID, String lessonID, Quiz quiz, Map<Question, Integer> studentAnswers) {
        this.quiz = quiz;
        this.studentID = studentID;
        this.lessonID = lessonID;
        this.studentAnswers = studentAnswers;
        this.score = 0;
        this.passed = false;
    }

    public void recordAnswer(Question question, int selectedOption) {
        studentAnswers.put(question, selectedOption);
    }
    
     public void calculateScore(Quiz quiz) {
        score = 0;
        for (Map.Entry<Question, Integer> entry : studentAnswers.entrySet()) {
            Integer correctOption = quiz.getCorrectAnswers().get(entry.getKey());
            if (correctOption != null && correctOption.equals(entry.getValue())) {
                score++;
            }
        }
        passed = score >= quiz.getNumberOfQuestions(); // or set your passing threshold
    }

   
    public int getScore() { return score; }
    public boolean isPassed() { return passed; }
}