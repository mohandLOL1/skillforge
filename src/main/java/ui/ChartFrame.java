package ui;

import courses.Course;
import courses.Lesson;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import services.AnalyticsService;
import services.CourseService;

public class ChartFrame extends JFrame {

    private AnalyticsService analyticsService;
    private CourseService courseService;

    public ChartFrame(String courseID) {

        try {
            courseService = new CourseService();
            analyticsService = new AnalyticsService();

            setTitle("Analytics Dashboard - Course: " + courseID);
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create datasets
            DefaultCategoryDataset completionDataset = new DefaultCategoryDataset();
            DefaultCategoryDataset quizDataset = new DefaultCategoryDataset();

            Course course = courseService.findCourse(courseID);
            Set<Lesson> lessons = course.getLessons();

            int[] completionPercent = {80, 50, 100};
           // ArrayList<Double> quizAvg = analyticsService.getAverageCourseScore(courseID);

            ArrayList<Lesson> lessonList = new ArrayList<>(lessons);

            for (int i = 0; i < lessonList.size(); i++) {
                Lesson lesson = lessonList.get(i);

                // Use lesson.getName() or any string that identifies the lesson
                completionDataset.addValue(completionPercent[i], "Completion %", lesson.getTitle());
               // quizDataset.addValue(quizAvg.get(i), "Quiz Average", lesson.getTitle());
            }

            // Create charts
            JFreeChart completionChart = ChartFactory.createBarChart(
                    "Lesson Completion",
                    "Lesson",
                    "Completion %",
                    completionDataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            JFreeChart quizChart = ChartFactory.createLineChart(
                    "Quiz Average per Lesson",
                    "Lesson",
                    "Average Score",
                    quizDataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            // Add charts to panels
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new ChartPanel(completionChart));
            panel.add(new ChartPanel(quizChart));

            add(panel);
        } catch (Exception e) {
            // Print the stack trace so you can see what went wrong
            e.printStackTrace();

            // Optional: show a dialog to the user
            JOptionPane.showMessageDialog(this,
                    "An error occurred while generating charts:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Test run
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChartFrame("COURSE123").setVisible(true);
        });
    }
}
