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
import java.util.ArrayList;
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
            setSize(900, 650);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // DATASETS
            DefaultCategoryDataset quizDataset = new DefaultCategoryDataset();
            DefaultCategoryDataset courseCompletionDataset = new DefaultCategoryDataset();

            // Fetch lessons
            ArrayList<Lesson> lessons = courseService.getLessons(courseID);

            // Fetch analytics
            ArrayList<Double> quizAverages = analyticsService.getAverageCourseScore(courseID);
            double overallCompletion = analyticsService.getAverageCourseCompletion(courseID);

            // Build quiz dataset
            for (int i = 0; i < lessons.size(); i++) {
                Lesson lesson = lessons.get(i);
                quizDataset.addValue(quizAverages.get(i), "Average Score", lesson.getTitle());
            }

            // Build overall course completion dataset (single bar)
            courseCompletionDataset.addValue(overallCompletion, "Completion", "Course Completion");

            // CHARTS
            JFreeChart quizChart = ChartFactory.createLineChart(
                    "Quiz Average per Lesson",
                    "Lesson",
                    "Average Score",
                    quizDataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            var plot = quizChart.getCategoryPlot();
            var renderer = (org.jfree.chart.renderer.category.LineAndShapeRenderer) plot.getRenderer();

// Thicker line
            renderer.setSeriesStroke(
                    0,
                    new java.awt.BasicStroke(3.0f) // thickness
            );

// Optional: make data points visible
            renderer.setDefaultShapesVisible(true);

// Optional: improve rendering quality
            quizChart.setAntiAlias(true);
            plot.setOutlineVisible(false);

            JFreeChart completionChart = ChartFactory.createBarChart(
                    "Overall Course Completion",
                    "Course",
                    "Completion %",
                    courseCompletionDataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            var quizAxis = quizChart.getCategoryPlot().getRangeAxis();
            quizAxis.setRange(0, 100);
            quizAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());

            var completionAxis = completionChart.getCategoryPlot().getRangeAxis();
            completionAxis.setRange(0, 100);
            completionAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());

            // LAYOUT
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new ChartPanel(quizChart));
            panel.add(new ChartPanel(completionChart));

            add(panel);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "An error occurred while generating charts:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChartFrame("COURSE123").setVisible(true);
        });
    }
}
