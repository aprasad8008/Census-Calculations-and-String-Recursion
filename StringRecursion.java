/* modified by: Anusha Prasad
 * email: apsmileyface@gmail.com
 *
 * A class that contains recursive methods that operate on strings.
 */
public class StringRecursion
{
    //print the individual characters in the string str
    public static void printWithSpaces (String str)
    {
        if (str.equals("") || str == null)
        {
            System.out.println();
            return;
        }
        System.out.print(str.charAt(0) + " ");
        printWithSpaces(str.substring(1));
    }
    
    //return the string that is formed by “weaving” together the characters in the strings str1 and str2 to create a single string
    public static String weave (String str1, String str2)
    {
        String str = "";
        if (str1 == null || str2 == null)
        {
            throw new IllegalArgumentException();
        }
        if (str1.equals(""))
        {
            return str2;
        }
        else if (str2.equals(""))
        {
            return str1;
        }
       
        str += str1.charAt(0) + "" + str2.charAt(0) + weave(str1.substring(1), str2.substring(1));
        return str;
    }
    
    //find and return the index of the first occurrence of the character ch in the string str
    public static int indexOf (char ch, String str)
    {
        if (str == null || str.equals(""))
        {
            return -1;
        }
        else if (str.charAt(0) == ch)
        {
            return 0;
        }
        return 1 + indexOf(ch, str.substring(1));
    }
    
    //main method for testing the different methods 
    public static void main (String[] args)
    {
        System.out.println("Test for printing spaces between characters in the string: ");
        printWithSpaces("space");
        printWithSpaces("apple");
        System.out.println();
        System.out.println("Test for weaving characters of seperate strings: ");
        System.out.println(weave("apple", "a"));
        System.out.println(weave("hello", ""));
        System.out.println(weave("hello", "world"));
        System.out.println();
        System.out.println("Test for getting the first occurence of a character in a string: ");
        System.out.println(indexOf('b', "Rabbit"));
    }
                           
                           
        
    
   
        
        
}