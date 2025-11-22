package courses;
        
import java.util.List;
import java.util.Objects;

public class Question {
    private String questionText;
    private List<String> options;
 
    
    public Question(){
    }

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;

    }

    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
   


}