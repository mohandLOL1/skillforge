package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {

    public ChartFrame(String courseID) {
        setTitle("Analytics Dashboard - Course: " + courseID);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create datasets
        DefaultCategoryDataset completionDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset quizDataset = new DefaultCategoryDataset();

        // Dummy data
        String[] lessons = {"Lesson 1", "Lesson 2", "Lesson 3"};
        int[] completionPercent = {80, 50, 100};
        double[] quizAvg = {75.5, 60.0, 92.3};

        for (int i = 0; i < lessons.length; i++) {
            completionDataset.addValue(completionPercent[i], "Completion %", lessons[i]);
            quizDataset.addValue(quizAvg[i], "Quiz Average", lessons[i]);
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
    }

    // Test run
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChartFrame("COURSE123").setVisible(true);
        });
    }
}
