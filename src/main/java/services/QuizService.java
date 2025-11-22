package services;

import courses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuizService {

    private List<Quiz> quizzes;
    private CourseService courseService;

    public QuizService() throws IOException {
        this.courseService = new CourseService();
        this.quizzes = new ArrayList<>();
        reload(); // ensure quizzes are loaded fresh at startup
    }

    /** 
     * Reload courses from database and rebuild quizzes list
     */
    private void reload() throws IOException {
        courseService.reload(); // reloads courses from file
        quizzes.clear();
        loadQuizzes(); // rebuild quizzes list from lessons
    }

    /**
     * Load all quizzes from lessons of all courses
     */
    private void loadQuizzes() {
        List<Course> courses = courseService.returnAllCourses();
        if (courses == null || courses.isEmpty()) return;

        for (Course course : courses) {
            Set<Lesson> lessons = course.getLessons();
            for (Lesson lesson : lessons) {
                if (lesson.getQuiz() != null) {
                    quizzes.add(lesson.getQuiz());
                }
            }
        }
    }

    /**
     * Create a new quiz for a lesson and link it
     */
    public Quiz createQuiz(String lessonID, List<Question> questions) throws IOException {
        reload(); // ensure fresh data
        Lesson lesson = findLessonByID(lessonID);
        if (lesson == null) throw new IllegalArgumentException("Lesson not found");

        Quiz quiz = new Quiz(lessonID);
        for (Question q : questions) {
            quiz.addQuestion(q);
        }

        lesson.setQuiz(quiz);
        quizzes.add(quiz);
        courseService.reload(); // save updated course data
        return quiz;
    }

    /**
     * Find a quiz by lesson ID
     */
    public Quiz findQuizByLessonID(String lessonID) throws IOException {
        reload(); // ensure fresh data
        for (Quiz q : quizzes) {
            if (q.getLessonID().equals(lessonID)) return q;
        }
        return null;
    }
    
    

    /**
     * Create a new student quiz attempt and attach it to the enrollment
     */
    public StudentQuizAttempt createStudentQuizAttempt(String studentID, String lessonID) throws IOException {
        reload();
        Quiz quiz = findQuizByLessonID(lessonID);
        if (quiz == null) throw new IllegalArgumentException("Quiz not found");

        StudentQuizAttempt attempt = new StudentQuizAttempt(studentID, lessonID, quiz.getQuestions());

        // Attach attempt to student enrollment
        CourseEnrollment enrollment = courseService.getStudentEnrollment(studentID, lessonID);
        if (enrollment != null) {
            if (enrollment.getQuizAttempts() == null) {
                enrollment.setQuizAttempts(new ArrayList<>());
            }
            enrollment.getQuizAttempts().add(attempt);
        }

        courseService.reload(); // save changes
        return attempt;
    }

    /**
     * Record an answer for a student's quiz attempt
     */
    public void recordAnswer(StudentQuizAttempt attempt, int questionIndex, int selectedOption) {
        List<Integer> answers = attempt.getStudentAnswers();
        while (answers.size() <= questionIndex) {
            answers.add(-1); // fill with default invalid values if needed
        }
        answers.set(questionIndex, selectedOption);
    }

    /**
     * Assess a student's quiz attempt
     */
    public void assessQuizAttempt(StudentQuizAttempt attempt) throws IOException {
        reload();
        Quiz quiz = findQuizByLessonID(attempt.getLessonID());
        if (quiz == null) throw new IllegalArgumentException("Quiz not found");

        List<Question> questions = quiz.getQuestions();
        int correctCount = 0;
        List<Integer> answers = attempt.getStudentAnswers();

        for (int i = 0; i < questions.size(); i++) {
            int studentAnswer = (i < answers.size()) ? answers.get(i) : -1;
            if (studentAnswer == questions.get(i).getCorrectOptionIndex()) {
                correctCount++;
            }
        }

        int score = (int) ((correctCount * 100.0) / questions.size());
        attempt.setScore(score);
        attempt.setPassed(score >= 60); // configurable pass threshold

        courseService.reload(); // save attempt to enrollment
    }

    /**
     * Remove a quiz from a lesson
     */
    public void removeQuiz(String lessonID) throws IOException {
        reload();
        Quiz quiz = findQuizByLessonID(lessonID);
        if (quiz == null) return;

        quizzes.remove(quiz);
        Lesson lesson = findLessonByID(lessonID);
        if (lesson != null) lesson.setQuiz(null);

        courseService.reload(); // persist changes
    }

    /**
     * Helper: find a lesson by its ID across all courses
     */
    private Lesson findLessonByID(String lessonID) {
        List<Course> courses = courseService.returnAllCourses();
        if (courses == null) return null;

        for (Course course : courses) {
            Set<Lesson> lessons = course.getLessons();
            for (Lesson lesson : lessons) {
                if (lesson.getLessonID().equals(lessonID)) return lesson;
            }
        }
        return null;
    }
}
