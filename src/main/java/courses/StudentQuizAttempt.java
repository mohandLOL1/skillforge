package courses;

import java.util.ArrayList;
import java.util.List;

public class StudentQuizAttempt {

    private String studentID;
    private String lessonID;
    private List<Question> quizQuestions;   // the questions for this attempt
    private List<Integer> studentAnswers;   // stores the selected option index for each question
    private int score;
    private boolean passed;
    
    public StudentQuizAttempt(){
        
    }
    public StudentQuizAttempt(String studentID, String lessonID, List<Question> quizQuestions) {
        this.studentID = studentID;
        this.lessonID = lessonID;
        this.quizQuestions = quizQuestions;
        this.studentAnswers = new ArrayList<>();
        this.score = 0;
        this.passed = false;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public void setQuizQuestions(List<Question> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public List<Question> getQuizQuestions() {
        return quizQuestions;
    }


    public List<Integer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<Integer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
    
    public int getAnswerForQuestion(int index){
        return studentAnswers.get(index);
    }
    
    
    
}