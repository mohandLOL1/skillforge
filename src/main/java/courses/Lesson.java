
package courses;

import misc.Validations;

public class Lesson{
    private String lessonID;
    private String title;
    private String content;
    private String courseID;
    private Quiz quiz;
    
    public Lesson(){
    }
    public Lesson(String lessonID, String title, String content,String courseID){
        this.lessonID = lessonID;
        this.title = title;
        this.content = content;
        this.courseID=courseID;
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
    public String getcourseID(){
        return courseID;
    }
    public void setcourseID(String courseID){
        this.courseID = courseID;
    }
}
