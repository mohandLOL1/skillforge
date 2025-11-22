package courses;
        
import java.util.List;
import java.util.Objects;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex; // 0-based index of correct answer
    
    public Question(){
    }

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question q = (Question) o;
        return correctOptionIndex == q.correctOptionIndex &&
               Objects.equals(questionText, q.questionText) &&
               Objects.equals(options, q.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, options, correctOptionIndex);
    }
}