/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package certification;

/**
 *
 * @author m
 */
public class CertificateRecord {
    
    private String certificateID;
    private String courseID;
    private String issueDate;
    private String studentID;

    public CertificateRecord() {}

    public CertificateRecord(String certificateID,String StudetID,String courseID,String issueDate) {
        this.certificateID = certificateID;
        this.courseID = courseID;
        this.issueDate = issueDate;
        this.studentID=StudetID;
    }

    public String getCertificateID() { 
        return certificateID; 
    }
    
    public String getCourseID() { 
        return courseID; 
    }
    
    public String getIssueDate() { 
        return issueDate; 
    }

    public String getStudentID() {
        return studentID;
    }
}
    
