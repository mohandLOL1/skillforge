package misc;

public class Validations {

    public static boolean validateEmail(String email) {
        if (email == null || email.contains(" ")) {
            return false;
        }
        int i = 0;
        int dotCount = 0;
        int dotPosition = 0;
        int atCount = 0;
        int atPosition = -1;
        for (i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c == '@') {
                atPosition = i;
                atCount++;
            } else if (c == '.' && atPosition != -1) {
                dotCount++;
                dotPosition = i;
            }
        }
        if (atCount == 1
                && dotCount >= 1
                && dotPosition < email.length() - 1
                && atPosition > 0
                && dotPosition > atPosition + 1
                && (email.length() - 1 - dotPosition) >= 2
                && (email.length() - 1 - dotPosition) <= 3) {
            return true;
        }
        return false;
    }

    public static boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean validateAge(int age) {
        return age >= 13 && age <= 99;
    }

    public static boolean validatePhone(String number) {
        number = number.trim();

        if (number.length() != 12) {
            return false;
        }
        int plusCount = 0;
        char[] chars = number.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '+') {
                plusCount++;
                if (i != 0) {
                    return false;
                }
                continue;
            }
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        if (plusCount > 1) {
            return false;
        }
        return true;
    }

    public static boolean validateStudentID(String studentID){
        if(studentID.charAt(0) == 'S'){
            String numberSubString = studentID.substring(1);
            int ID = Integer.parseInt(numberSubString);
            return ID>=10000 && ID<=20000;
        }
        else{
            return false;
        }
    }
    
    public static boolean validateInstructorID(String InstructorID){
        if(InstructorID.charAt(0) == 'I'){
            String numberSubString = InstructorID.substring(1);
            int ID = Integer.parseInt(numberSubString);
            return ID>=10000 && ID<=20000;
        }
        else{
            return false;
        }
    }
    
    public static boolean validateCourseID(String CourseID){
        if(CourseID.charAt(0) == 'C'){
            String numberSubString = CourseID.substring(1);
            int ID = Integer.parseInt(numberSubString);
            return ID>=10000 && ID<=20000;
        }
        else{
            return false;
        }
    }
    
    
    public static boolean validateLessonID(String LessonID){
        if(LessonID.charAt(0) == 'C'){
            String numberSubString = LessonID.substring(1);
            int ID = Integer.parseInt(numberSubString);
            return ID>=10000 && ID<=20000;
        }
        else{
            return false;
        }
    }
    
    public static boolean validateUsername(String username){
        if(username==null||username.length()<3||username.length()>10)
            
        return false;
        
        if(!Character.isLetter(username.charAt(0)))
            
        return false;  
        
        for(int i=0;i<username.length();i++){
        char c=username.charAt(i);
        if(!Character.isLetterOrDigit(c)&&c!='_')
           return false;
        }
        
        return true;
    }
    
    public static boolean validatePassword(String password){
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        if (password==null || password.length()<8) {
            return false;
        }

        for (char ch:password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpperCase=true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase=true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                if (!Character.isLetterOrDigit(ch)) {
                    hasSpecialChar=true;
                }
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
   
}
