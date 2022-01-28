import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Demo class that contains a method to remove duplicates and the main tester method
 */

public class Demo {

    /**Runtime is O(N) because the worst case is the iterator runs through either list1 or list2 completely
     * and that the loop runs through all the elements
     * This method removes all the duplicates from any abstract list input.
     * @param list1 list of person with an initial list
     *
     */
    public static void removeDuplicates(PhoneBook list1, PhoneBook list2){

        Iterator<Person> pointer1 = list1.getIterator();
        Iterator<Person> pointer2 = list2.getIterator();
        //for all the elements of list1, we remove any of those from list 2

        while(pointer1.hasNext() && pointer2.hasNext()){
            if(pointer1.next().equals(pointer2.next())){
                pointer2.remove();
            }
            else{
                pointer1.next();
                pointer2.next();
            }

        }

    }
    public static void main(String[] Args){
        //creates a set of ten Person objects two of which are duplicates
        Person a = new Person("a", "2160000");
        Person b = new Person("b", "2160001");
        Person c = new Person("c", "2160002");
        Person d = new Person("d", "2160003");
        Person e = new Person("e", "2160004");
        Person f = new Person("f", "2160005");
        Person g = new Person("g", "2160006");
        Person h = new Person("h", "2160007");
        Person i = new Person("i", "2160000");
        Person j = new Person("j", "2160001");

        //ArrayLists are resized as elements are added so it is more efficient to add them in a way in which index are
        // organized

        PhBArrayList arrayListPhB = new PhBArrayList();
        arrayListPhB.insert(0,a);
        arrayListPhB.insert(1,b);
        arrayListPhB.insert(2,c);
        arrayListPhB.insert(3,d);
        arrayListPhB.insert(4,e);
        arrayListPhB.insert(5,f);
        arrayListPhB.insert(6,g);
        arrayListPhB.insert(7,h);




        //It is easier to add elements in order to Linked lists because each element is linked to the one after it
        // so inserting elements in order is important

        PhBArrayList linkedPhB = new PhBArrayList();
        linkedPhB.insert(0,a);
        linkedPhB.insert(1,b);
        linkedPhB.insert(2,c);
        linkedPhB.insert(3,d);
        linkedPhB.insert(4,e);
        linkedPhB.insert(5,f);
        linkedPhB.insert(6,g);
        linkedPhB.insert(7,h);
        linkedPhB.insert(8,i);
        linkedPhB.insert(9,j);


        System.out.println("Linked List phone book:");
        //Prints all the elements in the list. O(N) because the worst case is the function running through all the elements once
        for(Person p: linkedPhB){
            System.out.println(p.getPersonID() + ": " + p.getPhoneNum());
        }

        System.out.println("\n"+ "Array List phone book:");
        //Prints all the elements in the list. O(N) because the worst case is the function running through all the elements once
        for(Person p: arrayListPhB){
            System.out.println(p.getPersonID() + ": " + p.getPhoneNum());
        }

        removeDuplicates(arrayListPhB, linkedPhB);

        System.out.println("\n"+"First list with no duplicates from the second phone book:");
        //Prints all the elements in the list. O(N) because the worst case is the function running through all the elements once
        for(Person p: linkedPhB){
            System.out.println(p.getPersonID() + ": " + p.getPhoneNum());
        }











    }
}
