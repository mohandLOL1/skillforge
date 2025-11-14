package misc;

public class Validations {

    public boolean email_validate(String email) {
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
        if (atCount == 1 && dotCount >= 1 && atPosition < email.length() - 1 && atPosition > 0 && dotPosition > atPosition + 1) {
            return true;
        }
        return false;
    }

    public boolean name_validate(String name) {
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

    public boolean age_validate(int age) {
        return age >= 13 && age <= 99;
    }

    public static boolean validate_Phone(String number) {

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

    public boolean check_ID(String string_id) {

        int id = Integer.parseInt(string_id);
        return id >= 1000 && id <= 10000;
    }

    public boolean gender_validate(String gender) {

        if (gender == null) {
            return false;
        }
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {

            return true;
        } else {
            return false;
        }
    }

}
