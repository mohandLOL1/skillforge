package courses;

import java.util.HashMap;
import java.util.Map;

public class Quiz {

    private String lessonID;
    private Map<Question, Integer> correctAnswers; // stores selected option index per question

    public Quiz(String lessonID) {
        this.lessonID = lessonID;
        this.correctAnswers = new HashMap<>();
    }

    public String getLessonID() {
        return lessonID;
    }

    public void addQuestion(Question question, int correctOptionIndex) {
        correctAnswers.put(question, correctOptionIndex);
    }

    public Map<Question, Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public int getNumberOfQuestions() {
        return correctAnswers.size();
    }

}
