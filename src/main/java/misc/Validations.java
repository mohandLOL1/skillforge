package misc;

public class Validations {

    public static boolean validateEmail(String email) {
        if (email == null || email.contains(" ")) {
            return false;
        }
        int i = 0;
        int dotCount = 0;
        int atCount = 0;
        int atPosition = -1;
        for (i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (c == '@') {
                atPosition = i;
                atCount++;
            } else if (c == '.' && atPosition != -1) {
                dotCount++;
            }
        }
        if (atCount == 1 && dotCount == 1 && atPosition == email.length() - 4 && atPosition > 0) {
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

        if (number.trim().length() != 12) {
            return false;
        } else {
            char[] char_number = number.toCharArray();
            for (char c : char_number) {
                if (c == '+') {
                    continue;
                }
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
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
    
    public static boolean validateInstructorID(String studentID){
        if(studentID.charAt(0) == 'S'){
            String numberSubString = studentID.substring(1);
            int ID = Integer.parseInt(numberSubString);
            return ID>=10000 && ID<=20000;
        }
        else{
            return false;
        }
    }
    
    public static boolean validateUsername(String username){
        return true;
    }
    
    public static boolean validatePasswordHash(String password){
        return true;
    }
   
}
