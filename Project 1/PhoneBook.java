import java.util.Iterator;
import java.util.ListIterator;

/**
 * An Abstract Data Type implementation of a phone book
 * @author Omar Loudghiri
 */
public interface PhoneBook {
    /**
     * A method to return the size of the phonebook
     * @return size integer
     */
    int size();

    /**
     * a method to insert a person into the i th index of the list
     * @param i index
     * @param person the person added
     */
    void insert(int i, Person person);

    /**
     * A method to remove the person at the i th position from the phonebook
     * @param i index
     * @return the person removed
     */
    Person remove(int i);

    /**
     *A method that searches for the person at the input ith index
     * @param i index
     * @return the person at that index
     */
    Person lookup(int i);

    /**
     * a method that returns the Iterator for the list used
     * @return Iterator
     */
    Iterator<Person> getIterator();


}
