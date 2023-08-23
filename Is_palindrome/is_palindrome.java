import java.util.*;

class CheckIsPalindrome
{
    public static void main(String[] args) 
    {
        // Input data
        Scanner input = new Scanner(System.in);
        System.out.print("Input a word : ");
        String inputString = input.nextLine();

        // check is polindrom using function
        if (IsPalindrome(inputString)) {
            System.out.println("The word is a palindrome.");
        } else {
            System.out.println("The word is not a palindrome.");
        }
        
        input.close();
    }

    public static boolean IsPalindrome(String data)
    {
        // define user input and clean data from characters other than letters and lowercase data
        data = data.replaceAll("[^a-zA-Z]", "").toLowerCase();
        // defines a variable for checking palindrome data
        String reverse = "";
        int length = data.length();   
        // checks whether the reverse variable is the same as the user input data
        for ( int i = length - 1; i >= 0; i-- )  
            reverse = reverse + data.charAt(i);  
        if (data.equals(reverse))  
            return true; 
        else  
            return false;  
    }
}