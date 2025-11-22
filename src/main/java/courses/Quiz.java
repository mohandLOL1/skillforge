package courses;

import java.util.List;
import java.util.ArrayList;

public class Quiz {

    private String lessonID;
    private List<Question> questions;

    public Quiz()
    {
    }
    
    public Quiz(String lessonID, ArrayList<Question> questions) {
        this.lessonID = lessonID;
        this.questions = questions;
    }

    public Quiz(String lessonID) {
        this.lessonID = lessonID;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getNumberOfQuestions() {
        return questions.size();
    }

    public String getLessonID() {
        return lessonID;
    }
}
