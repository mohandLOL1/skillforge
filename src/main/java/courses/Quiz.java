package courses;


import java.util.HashMap;
import java.util.Map;

public class Quiz {

    private String lessonID;
    private Map<Question, Integer> questions;

    public Quiz(){
    }

    public Quiz(String lessonID, Map<Question, Integer> questions) {
        this.lessonID = lessonID;
        this.questions = new HashMap<>();
    }

}
