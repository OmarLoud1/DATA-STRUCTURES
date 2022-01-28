// Main class
public class Main {

    // removes spaces and makes all the characters lower case, method from the java linrary
    public static char[] input(String s){
        s = s.replaceAll("\\s", "");
        return s.toLowerCase().toCharArray();

    }

    //compares the two strings
    public static boolean compare(String s1, String s2){
        Hashtable t1 = new Hashtable();//creates a hash table for one string
        for(char c : input(s1)){//adds all elements
            t1.add(c);
        }

        for(char c : input(s2)){//for all the chars in the second string
            if(!t1.remove(c))// remove them from the table, only if they exist
                return false;//if the char doesnt exist then they are not anagrams
        }
        return t1.isempty();// of the table was fully emptied then they are anagrams.

    }

    public static void main(String[] args){
        String s1 = args[0];// the first arg is the first
        String s2 = args[1];

        System.out.println("the fact that "+ s1+ " and "+ s2+" are anagrams is: "+compare(s1,s2));

    }
}
