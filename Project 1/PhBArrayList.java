import java.util.ArrayList;
import java.util.Iterator;

/**Omar Loudghiri oxl51
 * the array list implementation of the phone book
 */
public class PhBArrayList extends ArrayList<Person> implements PhoneBook {

    /**
     * constructor that creates a new empty array list
     */
    public PhBArrayList(){
        ArrayList<Person> phoneBook = new ArrayList<>();
    }

    /**
     * A method to return the size of the phonebook
     *
     * @return size integer
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * a method to insert a person into the i th index of the list
     *
     * @param i      index
     * @param person the person added
     */
    @Override
    public void insert(int i, Person person) {
        this.add(i, person);
    }

    /**
     * A method to remove the person at the i th position from the phonebook
     *
     * @param i index
     * @return the person removed
     */
    @Override
    public Person remove(int i) {
        return super.remove(i);
    }

    /**
     * A method that searches for the person at the input ith index
     *
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
