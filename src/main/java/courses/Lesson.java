
package courses;

import misc.Validations;

public class Lesson{
    private String lessonID;
    private String title;
    private String content;

    public Lesson(){
    }
    public Lesson(String lessonID, String title, String content){
        this.lessonID = lessonID;
        this.title = title;
        this.content = content;
    }
    public String getLessonID(){
        return lessonID;
    }
    public void setLessonID(String lessonID){
        if(Validations.validateLessonID(lessonID)){
            this.lessonID = lessonID;
           
        }
        else{
            throw new IllegalArgumentException("Invalid lesson ID");
        }
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}