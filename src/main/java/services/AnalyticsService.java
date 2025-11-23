package services;

import courses.*;
import java.io.IOException;
import users.*;
import java.util.ArrayList;
import java.util.Set;

public class AnalyticsService {
    //calculateaveragepercourse
    //etc

    private CourseService courseService;

    public AnalyticsService() throws IOException {
        this.courseService = new CourseService();
    }

    public ArrayList<Double> getAverageCourseScore(String courseID) {

        ArrayList<Double> lessonAverages = new ArrayList<>();

        Course course = courseService.findCourse(courseID);
        ArrayList<Lesson> lessons = courseService.getLessons(courseID);
        ArrayList<CourseEnrollment> courseEnrollments = courseService.getCourseEnrollments(courseID);

        if (courseEnrollments.isEmpty()) {
            return new ArrayList<>();
        }

        if (lessons.isEmpty()) {
            return new ArrayList<>();
        }

        for (Lesson lesson : lessons) {

            double totalScore = 0.0;
            int attemptCount = 0;

            for (CourseEnrollment enrollment : courseEnrollments) {

                ArrayList<StudentQuizAttempt> attempts = enrollment.getQuizAttempts();

                for (StudentQuizAttempt sqa : attempts) {

                    if (lesson.getLessonID().equals(sqa.getLessonID())) {
                        totalScore += sqa.getScore();
                        attemptCount++;
                    }
                }
            }

            if (attemptCount > 0) {
                lessonAverages.add(totalScore / attemptCount);
            } else {

                lessonAverages.add(0.0);
            }
        }

        return lessonAverages;
    }

    public Double getAverageCourseCompletion(String courseID) {

        Double courseAveragescompletion = 0.0;
        int counter = 0;
        Course course = courseService.findCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("course not found");
        }

        ArrayList<CourseEnrollment> courseEnrollments = courseService.getCourseEnrollments(courseID);

        if (courseEnrollments.isEmpty()) {
            return 0.0;
        }

        for (CourseEnrollment ce : courseEnrollments) {
            courseAveragescompletion += ce.getPercent();
            counter++;
        }
        courseAveragescompletion = courseAveragescompletion / counter;
        return courseAveragescompletion;
    }

}
