import java.util.Iterator;
import java.util.LinkedList;

/**
 * the Linked list implementation of a phone book
 */
public class PhBLinkedList extends LinkedList<Person> implements PhoneBook {

    /**
     * the constructor which creates a new empty linked list
     */
    public PhBLinkedList(){
        LinkedList<Person> phonebook = new LinkedList<>();
    }

    /**
     * A method to return the size of the phonebook
     * @return size integer
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * a method to insert a person into the i th index of the list
     * @param i      index
     * @param person the person added
     */
    @Override
    public void insert(int i, Person person) {
        this.add(i, person);
    }

    /**
     * A method to remove the person at the i th position from the phonebook
     * @param i index
     * @return the person removed
     */
    @Override
    public Person remove(int i) {
        return super.remove(i);
    }

    /**
     * A method that searches for the person at the input ith index
     * @param i index
     * @return the person at that index
     */
    @Override
    public Person lookup(int i) {
       return this.get(i);
    }

    /**
     * a method that returns the Iterator for the list used
     *
     * @return Iterator
     */
    @Override
    public Iterator<Person> getIterator() {
       return this.iterator();
    }




}
