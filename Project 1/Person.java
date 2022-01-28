/** Omar Loudghiri oxl51
 * The tuple person that contains an id and a phone number
 */
public class Person {

    //the name of the person
    private String PersonID;

    //Their phone number
    private String phoneNum;

    /**
     * constructor that attributes an id and a number
     */

    public Person(String id, String num){
        PersonID = id;
        phoneNum = num;
    }

    /**
     * an overriden version of equals in order for the contains method to work when comparing people
     * @param o the object compared
     * @return true if they are equal and false if they arent
     */
    @Override
    public boolean equals(Object o){
        if( !(o instanceof Person) )
            return false;
        else {
            Person o1 = (Person) o;
            //the Id is compared
            return this.getPersonID().equals(o1.getPersonID());
        }
    }

    /**
     * getter method for the ID
     * @return the ID
     */
    public String getPersonID() {
        return PersonID;
    }

    /**
     * getter method for the phone number
     * @return the person's number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

}
