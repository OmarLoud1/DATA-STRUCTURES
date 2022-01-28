import java.util.LinkedList;

public class Test {

    public static void main(String[] args){
        AVLPhBook tree = new AVLPhBook();

        // fill up the tree


        System.out.println("adding ben:2150001 was: "+ tree.PhBInsert("2150001", "ben"));
        System.out.println("adding omar:2166110 was: "+tree.PhBInsert("2166110", "omar"));
        System.out.println("adding omar:2166199 was: "+ tree.PhBInsert("2166199", "omar"));
        System.out.println("adding omar:11609001 was: "+tree.PhBInsert("11609001", "omar"));
        System.out.println("adding adam:2160001 was: "+tree.PhBInsert("2160001", "adam"));
        System.out.println("adding sdam:1160001 was: "+tree.PhBInsert("1160001", "sdam"));
        System.out.println("adding sen:3150001 was: "+tree.PhBInsert("3150001", "sen"));
        System.out.println("adding mark:1166110 was: "+tree.PhBInsert("1166110", "mark"));
        System.out.println("adding ssean:315000111 was: "+tree.PhBInsert("315000111", "ssean"));
        System.out.println("adding mark1:116613210 was: "+tree.PhBInsert("116613210", "mark1"));



        // insert a number that already exists
        System.out.println("adding this person that already exists is: "+ tree.PhBInsert("2160001", "adam1"));

        //return existing numbers
        System.out.println("marks' number is: "+tree.PhBSearch("mark"));

        //searches for a name that has more than one number
        System.out.println("People called omar have those numbers: " +tree.PhBSearch("omar"));

        //Deletes a number from a list
        System.out.println("Deletion is: " +tree.PhBDelete("11609001","omar"));

        System.out.println("People called omar have those numbers: " +tree.PhBSearch("omar"));

        //Deletes a node
        System.out.println("Deletion is: " +tree.PhBDelete("2150001","ben"));

        //Number search
        long num = 3150001;
        System.out.println("the name associated with 3150001 is : "+tree.PhBSearch(num));


    }
}
