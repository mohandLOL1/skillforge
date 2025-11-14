
package misc;


public class Validations {
    
     public static boolean validate_Email(String email) {

        int atCount = 0;
        int atPosition = -1;
        boolean dot = false;
        if (email.contains(" "))
          throw new IllegalArgumentException("Email cannot contain spaces");
        
        for(int i = 0 ; i <email.length() ; i++)
        {
            char c = email.charAt(i);
            if(c == '@')
            {
                atCount++;
                atPosition = i;
            }
            if (c == '.' && atPosition != -1 && i > atPosition + 1)
            {
                dot = true;
            }}
            
            if(atCount == 1 && atPosition > 0 && dot &&atPosition < email.length() - 4 )
            {
                return true;
            }
        throw new IllegalArgumentException("Something wrong in Email");
    }
     public static void validate_Name(String Name){
         
       char[]name=Name.toCharArray();
     
      for(int i=0;i<Name.length();i++){
        if(!Character.isLetter(name[i]))
             throw new IllegalArgumentException("Name must be letters only");
            }
    }
     public static boolean validate(String number) {

        if (number.trim().length() != 12) 
           throw new IllegalArgumentException("Phone must be 12 number");
        else {
            char[] char_number = number.toCharArray();
            for (char c : char_number) {
                if(c == '+') continue;
                if (!Character.isDigit(c))
                  throw new IllegalArgumentException("Phone must be numbers only");
            }
        }
        return true;
    }
}
