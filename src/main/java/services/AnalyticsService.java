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

        return new ArrayList<>();

}
}

