import java.util.*;

class CheckIsPalindrome
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Input a word : ");
        String inputString = input.nextLine();

        if (IsPalindrome(inputString)) {
            System.out.println("The word is a palindrome.");
        } else {
            System.out.println("The word is not a palindrome.");
        }
        
        input.close();
    }

    public static boolean IsPalindrome(String data)
    {
        data = data.replaceAll("[^a-zA-Z]", "").toLowerCase();
        // int length_data = data.length();

        // int left = 0;
        // int right = data.length() - 1;
        
        // while (left < right) {
        //     if (data.charAt(left) != data.charAt(right)) {
        //         return false;
        //     }
        //     left++;
        //     right--;
        // }
        // return true;
        
        String reverse = "";
        int length = data.length();   
        for ( int i = length - 1; i >= 0; i-- )  
            reverse = reverse + data.charAt(i);  
        if (data.equals(reverse))  
            return true; 
        else  
            return false;  
    }
}