
package certification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import misc.Validations;

public class Certificate {
    private String certificateID;
    private String courseID;
    private String issueDate;
    private String studentID;
    
    public Certificate(String certificateID,String studentID,String courseID, String issueDate) {
        setCertificateID(certificateID);
        this.courseID = courseID;
        this.issueDate = issueDate;
        this.studentID = studentID;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setCertificateID(String certificateID) {
        if (Validations.validatecertificateID(certificateID)) {
            this.certificateID = certificateID;
        } else {
            throw new IllegalArgumentException();
        }
    }
  
}
