/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import certification.Certificate;
import certification.CreatePdfCertification;
import courses.Course;
import courses.Lesson;
import courses.Question;
import courses.Quiz;
import courses.StudentQuizAttempt;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import services.CourseService;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.QuizService;
import services.UserService;
import users.User;

/**
 *
 * @author amr
 */
public class StudentDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentDashboard.class.getName());
    private User log;
    private CourseService cs;
    private QuizService qs;
    private Quiz quiz;
    private int currentIndex = 0;
    private StudentQuizAttempt attempt;
    private String quizLessonID;

    /**
     * Creates new form StudentDashboard
     */
    public StudentDashboard(User user) {
        initComponents();
        try {
            cs = new CourseService();
            qs = new QuizService();
        } catch (IOException e) {

            logger.log(java.util.logging.Level.SEVERE, null, e);
        }
        setTitle("Student Dashboard");
        setSize(720, 550);
        setLocationRelativeTo(null);
        this.log = user;
        jTextField1.setText(user.getUsername());
        jTextField2.setText(user.getEmail());
        jTextField3.setText(user.getID());
    }

    public void loadTable() throws IOException {
        DefaultTableModel m = (DefaultTableModel) All_Courses.getModel();
        m.setRowCount(0);

        ArrayList<Course> available = cs.getApprovedCourses();
        for (Course c : available) {
            m.addRow(new Object[]{c.getCourseID(), c.getTitle(), c.getDescription(), c.getInstructorID()});
        }
    }

    public void loadEnrolledCourses() throws IOException {
        DefaultTableModel m = (DefaultTableModel) Selected_Courses.getModel();
        m.setRowCount(0);

        CourseService service = new CourseService();
        Set<Course> enrolled = service.enrolledcourses(log.getID());

        if (enrolled.isEmpty()) {
            return;
        }

        for (Course c : enrolled) {
            m.addRow(new Object[]{c.getCourseID(), c.getTitle(), c.getDescription(), c.getInstructorID()});
        }
    }

    private void loadLessonsIntoTable(String courseID) {
        try {
            DefaultTableModel model = (DefaultTableModel) Lessons.getModel();
            model.setRowCount(0);

            CourseService service = new CourseService();
            ArrayList<Lesson> lessons = service.getLessons(courseID);
            Set<String> completed = service.completedLessons(log.getID(), courseID);

            for (Lesson L : lessons) {
                model.addRow(new Object[]{L.getLessonID(), L.getTitle(), completed.contains(L.getLessonID()) ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    
    
    private void showQuestion() {
        var q = quiz.getQuestions().get(currentIndex);

        Question.setText(q.getQuestionText());
        A.setText(q.getOptions().get(0));
        B.setText(q.getOptions().get(1));
        C.setText(q.getOptions().get(2));
        D.setText(q.getOptions().get(3));

        buttonGroup1.clearSelection();

        if (currentIndex == quiz.getQuestions().size() - 1) {
            Next.setEnabled(false);
            Submit.setEnabled(true);
        } else {
            Next.setEnabled(true);
            Submit.setEnabled(false);
        }
    }
    
    private void loadQuiz(String lessonID) {
        try {
            qs = new services.QuizService();
            quiz = qs.findQuizByLessonID(lessonID);
            attempt = qs.createStudentQuizAttempt(log.getID(), lessonID);
            quizLessonID = lessonID;
            currentIndex = 0;
            showQuestion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load quiz data.");
        }
    }

    private void loadcertificatesIntoTable(String StudentID) {
        try {
            DefaultTableModel model = (DefaultTableModel) certification.getModel();
            model.setRowCount(0);

            UserService service = new UserService();
            ArrayList<Certificate> list = service.getCertificatesByStudentID(log.getID());

            for (Certificate Cert : list) {
                model.addRow(new Object[]{Cert.getCertificateID(), Cert.getCourseID(), Cert.getIssueDate()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        View_available_courses = new javax.swing.JButton();
        View_enrolled_courses = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        CertificateEarned = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        Logout = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        All_Courses = new javax.swing.JTable();
        EnrollCourse = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Selected_Courses = new javax.swing.JTable();
        Viewlesson = new javax.swing.JToggleButton();
        Generate_Certificate = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Lessons = new javax.swing.JTable();
        ViewContent = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        TakeQuiz = new javax.swing.JToggleButton();
        Back = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Content = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        Question = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        a = new javax.swing.JRadioButton();
        b = new javax.swing.JRadioButton();
        c = new javax.swing.JRadioButton();
        d = new javax.swing.JRadioButton();
        A = new javax.swing.JTextField();
        B = new javax.swing.JTextField();
        C = new javax.swing.JTextField();
        D = new javax.swing.JTextField();
        Next = new javax.swing.JButton();
        Submit = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        Download_certification = new javax.swing.JToggleButton();
        View_certification = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        certification = new javax.swing.JTable();

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Enroll  Course");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 645, 0);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("welcome ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 710, 90);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        View_available_courses.setBackground(new java.awt.Color(153, 153, 153));
        View_available_courses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        View_available_courses.setText("View available  courses");
        View_available_courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_available_coursesActionPerformed(evt);
            }
        });

        View_enrolled_courses.setBackground(new java.awt.Color(153, 153, 153));
        View_enrolled_courses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        View_enrolled_courses.setText("View enrolled  courses");
        View_enrolled_courses.setMinimumSize(new java.awt.Dimension(183, 27));
        View_enrolled_courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_enrolled_coursesActionPerformed(evt);
            }
        });

        Home.setBackground(new java.awt.Color(153, 153, 153));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        CertificateEarned.setBackground(new java.awt.Color(153, 153, 153));
        CertificateEarned.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CertificateEarned.setText("Certificate Earned");
        CertificateEarned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CertificateEarnedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(View_available_courses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(View_enrolled_courses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CertificateEarned, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Home)
                .addGap(60, 60, 60)
                .addComponent(View_available_courses)
                .addGap(59, 59, 59)
                .addComponent(View_enrolled_courses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(CertificateEarned)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 90, 200, 430);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Username");

        jTextField1.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Email");
        jLabel2.setToolTipText("");

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ID");

        jTextField3.setEditable(false);

        Logout.setBackground(new java.awt.Color(102, 102, 102));
        Logout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField3)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(Logout)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        All_Courses.setBackground(new java.awt.Color(204, 204, 204));
        All_Courses.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        All_Courses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseID", "Title", "Description", "InstructorID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        All_Courses.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(All_Courses);
        if (All_Courses.getColumnModel().getColumnCount() > 0) {
            All_Courses.getColumnModel().getColumn(0).setMinWidth(60);
            All_Courses.getColumnModel().getColumn(0).setPreferredWidth(60);
            All_Courses.getColumnModel().getColumn(0).setMaxWidth(60);
            All_Courses.getColumnModel().getColumn(1).setMinWidth(140);
            All_Courses.getColumnModel().getColumn(1).setMaxWidth(140);
            All_Courses.getColumnModel().getColumn(3).setMinWidth(70);
            All_Courses.getColumnModel().getColumn(3).setPreferredWidth(70);
            All_Courses.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        EnrollCourse.setBackground(new java.awt.Color(102, 102, 102));
        EnrollCourse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EnrollCourse.setText("Enroll Course");
        EnrollCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrollCourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EnrollCourse)
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EnrollCourse)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        Selected_Courses.setBackground(new java.awt.Color(204, 204, 204));
        Selected_Courses.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Selected_Courses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseID", "Title", "Description", "InstructorID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Selected_Courses);
        if (Selected_Courses.getColumnModel().getColumnCount() > 0) {
            Selected_Courses.getColumnModel().getColumn(0).setMinWidth(60);
            Selected_Courses.getColumnModel().getColumn(0).setPreferredWidth(60);
            Selected_Courses.getColumnModel().getColumn(0).setMaxWidth(60);
            Selected_Courses.getColumnModel().getColumn(1).setMinWidth(140);
            Selected_Courses.getColumnModel().getColumn(1).setMaxWidth(140);
            Selected_Courses.getColumnModel().getColumn(3).setMinWidth(70);
            Selected_Courses.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        Viewlesson.setBackground(new java.awt.Color(102, 102, 102));
        Viewlesson.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Viewlesson.setText("View lesson");
        Viewlesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewlessonActionPerformed(evt);
            }
        });

        Generate_Certificate.setBackground(new java.awt.Color(102, 102, 102));
        Generate_Certificate.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        Generate_Certificate.setText("Generate Certificate");
        Generate_Certificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Generate_CertificateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Viewlesson)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Generate_Certificate)
                .addGap(15, 15, 15))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Viewlesson)
                    .addComponent(Generate_Certificate))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("tab3", jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        Lessons.setBackground(new java.awt.Color(204, 204, 204));
        Lessons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "LessonID", "Title", "Completed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Lessons);

        ViewContent.setBackground(new java.awt.Color(102, 102, 102));
        ViewContent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ViewContent.setText("View Content");
        ViewContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewContentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ViewContent)
                .addGap(22, 386, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewContent)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel8);

        TakeQuiz.setBackground(new java.awt.Color(102, 102, 102));
        TakeQuiz.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TakeQuiz.setText("Take Quiz");
        TakeQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TakeQuizActionPerformed(evt);
            }
        });

        Back.setBackground(new java.awt.Color(102, 102, 102));
        Back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Content.setEditable(false);
        Content.setColumns(20);
        Content.setRows(5);
        jScrollPane5.setViewportView(Content);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addComponent(TakeQuiz)
                .addGap(17, 17, 17))
            .addComponent(jScrollPane5)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TakeQuiz)
                    .addComponent(Back))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("tab5", jPanel3);

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        Question.setEditable(false);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Question :");

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));

        buttonGroup1.add(a);

        buttonGroup1.add(b);

        buttonGroup1.add(c);

        buttonGroup1.add(d);

        A.setEditable(false);

        B.setEditable(false);

        C.setEditable(false);

        D.setEditable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(c, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(a, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(A, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(B)
                    .addComponent(C)
                    .addComponent(D))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a)
                    .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b)
                    .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c)
                    .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d)
                    .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Question, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Question, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Next.setBackground(new java.awt.Color(102, 102, 102));
        Next.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Submit.setBackground(new java.awt.Color(102, 102, 102));
        Submit.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Submit)))
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit)
                    .addComponent(Next))
                .addGap(0, 54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel10);

        Download_certification.setBackground(new java.awt.Color(102, 102, 102));
        Download_certification.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Download_certification.setText("Download certification");
        Download_certification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Download_certificationActionPerformed(evt);
            }
        });

        View_certification.setBackground(new java.awt.Color(102, 102, 102));
        View_certification.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        View_certification.setText("View certification");
        View_certification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_certificationActionPerformed(evt);
            }
        });

        certification.setBackground(new java.awt.Color(204, 204, 204));
        certification.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "certificate ID", "course ID", "issue date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(certification);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(View_certification)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Download_certification)
                .addGap(14, 14, 14))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Download_certification)
                    .addComponent(View_certification))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("tab7", jPanel9);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(200, 60, 510, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void View_available_coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_available_coursesActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        try {
            loadTable();
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_View_available_coursesActionPerformed

    private void View_enrolled_coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_enrolled_coursesActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        try {
            loadEnrolledCourses();
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_View_enrolled_coursesActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_HomeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void ViewlessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewlessonActionPerformed
        // TODO add your handling code here:
        int row = Selected_Courses.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a course first !");
            return;
        }

        String courseID = Selected_Courses.getValueAt(row, 0).toString();

        loadLessonsIntoTable(courseID);

        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_ViewlessonActionPerformed

    private void EnrollCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrollCourseActionPerformed
        // TODO add your handling code here:

        int row = All_Courses.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a course first !");
            return;
        }

        String courseID = All_Courses.getValueAt(row, 0).toString();
        String studentID = log.getID();

        try {
            CourseService service = new CourseService();
            Set<Course> enrolled = service.enrolledcourses(studentID);

            if (service.studentInCourse(studentID, courseID)) {
                JOptionPane.showMessageDialog(this, "You are already enrolled in this course.");
                return;
            }

            service.enrollStudent(studentID, courseID);
            JOptionPane.showMessageDialog(this, "Enrolled successfully!");

            loadEnrolledCourses();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_EnrollCourseActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
            new Login().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LogoutActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void ViewContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewContentActionPerformed
        int row = Lessons.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lesson first !");
            return;
        }

        String lessonID = Lessons.getValueAt(row, 0).toString();

        String content = cs.getLessonContent(lessonID);

        Content.setText(content);

        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_ViewContentActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_BackActionPerformed

    private void TakeQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TakeQuizActionPerformed
        // TODO add your handling code here:
        int row = Lessons.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lesson first !");
            return;
        }

        String lessonID = Lessons.getValueAt(row, 0).toString();
        
        loadQuiz(lessonID);
    
       jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_TakeQuizActionPerformed

    private void CertificateEarnedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CertificateEarnedActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
        loadcertificatesIntoTable(log.getID());
    }//GEN-LAST:event_CertificateEarnedActionPerformed

    private void Download_certificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Download_certificationActionPerformed
        // TODO add your handling code here:
        int row = certification.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select Certificate first !");
            return;
        }

        String certificateID = certification.getValueAt(row, 0).toString();
        File source = new File("certificates/" + certificateID + ".pdf");

        if (!source.exists()) {
            JOptionPane.showMessageDialog(this, "Certificate file not found!");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(certificateID + ".pdf"));

        int option = chooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File dest = chooser.getSelectedFile();
            try {
                Files.copy(source.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "Downloaded successfully !");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_Download_certificationActionPerformed

    private void View_certificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_certificationActionPerformed
        // TODO add your handling code here:
        int row = certification.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select Certificate first !");
            return;
        }

        String certificateID = certification.getValueAt(row, 0).toString();

        File file = new File("certificates/" + certificateID + ".pdf");

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Certificate file not found!");
            return;
        }

        try {
            Desktop.getDesktop().open(file);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot open file: " + ex.getMessage());
        }
    }//GEN-LAST:event_View_certificationActionPerformed

    private void Generate_CertificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Generate_CertificateActionPerformed
        int row = Selected_Courses.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select Course first !");
            return;
        }
        try {
            String studentID = log.getID();
            String courseID = Selected_Courses.getValueAt(row, 0).toString();

            UserService service = new UserService();

            Certificate cert = service.generateCertificate(studentID, courseID);

            Certificate c = new Certificate(cert.getCertificateID(), cert.getStudentID(), cert.getCourseID(), cert.getIssueDate());

            service.addCertificateToStudent(cert.getCertificateID(), studentID, courseID, cert.getIssueDate());

            String pdfPath = CreatePdfCertification.createPDF(cert);

            JOptionPane.showMessageDialog(this, "Certificate Generated !", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_Generate_CertificateActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:

        int selected = -1;

        if (a.isSelected()) {
            selected = 0;
        } else if (b.isSelected()) {
            selected = 1;
        } else if (c.isSelected()) {
            selected = 2;
        } else if (d.isSelected()) {
            selected = 3;
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please choose an answer first!");
            return;
        }

        try {
            qs.recordAnswer(attempt, currentIndex, selected);
            double score = qs.assessQuizAttempt(attempt);

            if (attempt.isPassed()) {
                JOptionPane.showMessageDialog(this, "You Passed! Score: " + score + "%");
            } else {
                JOptionPane.showMessageDialog(this, "Failed! Score: " + score + "%");
            }

            jTabbedPane1.setSelectedIndex(3);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error submitting quiz.");
        }
    }//GEN-LAST:event_SubmitActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        
        int selected = -1;

        if (a.isSelected()) {
            selected = 0;
        } else if (b.isSelected()) {
            selected = 1;
        } else if (c.isSelected()) {
            selected = 2;
        } else if (d.isSelected()) {
            selected = 3;
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please choose an answer first!");
            return;
        }

        try {
            qs.recordAnswer(attempt, currentIndex, selected);
            currentIndex++;
            showQuestion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving answer.");
        }
    }//GEN-LAST:event_NextActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A;
    private javax.swing.JTable All_Courses;
    private javax.swing.JTextField B;
    private javax.swing.JToggleButton Back;
    private javax.swing.JTextField C;
    private javax.swing.JButton CertificateEarned;
    private javax.swing.JTextArea Content;
    private javax.swing.JTextField D;
    private javax.swing.JToggleButton Download_certification;
    private javax.swing.JToggleButton EnrollCourse;
    private javax.swing.JToggleButton Generate_Certificate;
    private javax.swing.JButton Home;
    private javax.swing.JTable Lessons;
    private javax.swing.JButton Logout;
    private javax.swing.JButton Next;
    private javax.swing.JTextField Question;
    private javax.swing.JTable Selected_Courses;
    private javax.swing.JButton Submit;
    private javax.swing.JToggleButton TakeQuiz;
    private javax.swing.JToggleButton ViewContent;
    private javax.swing.JButton View_available_courses;
    private javax.swing.JToggleButton View_certification;
    private javax.swing.JButton View_enrolled_courses;
    private javax.swing.JToggleButton Viewlesson;
    private javax.swing.JRadioButton a;
    private javax.swing.JRadioButton b;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton c;
    private javax.swing.JTable certification;
    private javax.swing.JRadioButton d;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
