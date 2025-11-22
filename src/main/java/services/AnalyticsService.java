package services;

import courses.*;
import users.*;
import java.util.ArrayList;
import java.util.Set;

public class AnalyticsService {
    //calculateaveragepercourse
    //etc

    private CourseService courseService;

    public AnalyticsService(CourseService courseService) {
        this.courseService = courseService;
    }

    public ArrayList<Double> getAverageCourseScore(String courseID) {

        ArrayList<Double> lessonAverages = new ArrayList<>();

        Course course = courseService.findCourse(courseID);
        ArrayList<Lesson> lessons = courseService.getLessons(courseID);
        Set<CourseEnrollment> courseenrollments = course.getCourseEnrollments();

        for (Lesson lesson : lessons) {

            double totalScore = 0.0;
            int attemptCount = 0;

            for (CourseEnrollment enrollment : courseenrollments) {

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

}
