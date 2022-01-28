import java.util.LinkedList;
import java.util.NoSuchElementException;
//author: omar loudghiri oxl51
//An implemaentation of two overlaying Binary Search Trees
public class DoubleTreePhBook {

    NumberNode rootNumber; //root of the number tree where the tree is sorted by number
    NameNode rootName; // root of a tree where it is sorted by name

    //constructor
    public DoubleTreePhBook() {
        rootNumber = null;
        rootName = null;
    }

    //method to insert a name and number into the two trees
    public boolean PhBInsert(String number, String name) {
        NumberNode numNode = numInsert(number, name); //inserts into the number tree
        if (numNode != null) {
            return nameInsert(number, name); // inserts into the name tree
        } else
            return false;

    }

    //method to delete from both trees
    public boolean PhBDelete(String number, String name) {
        if (nameToDelete(number, name)) //if deleted from the name tree
            return numToDelete(number,name); //deletes from the num tree
        else
            return false;
    }

    // method to search by name
    public LinkedList<String> PhBNameSearch(String name1) {
        NameNode trav = rootName; // starts at the root
        NameNode found = null;

        while (trav != null) {
            if (name1.compareTo(trav.name) == 0) { //if the root is the element
                found = trav;
                return found.numbers; // return the list of numbers
            }else if (name1.compareTo(trav.name) < 0) //if smaller go left
                trav = trav.leftName;
            else // else it is bigger and go right
                trav = trav.rightName;
        }
            return null;
    }

    //search by phone number
    public String PhBPhoneSearch(String number1) {
        NumberNode trav = rootNumber; //starts at root
        NumberNode found = null;

        while (trav != null) {
            if (number1.compareTo(trav.number) == 0) {
                found = trav;
                return found.name;
            }
            else if (number1.compareTo(trav.number) < 0)//if smaller go left
                trav = trav.leftNumber;
            else// else it is bigger and go right
                trav = trav.rightNumber;
        }
            return null;
    }

    //helper method that inserts into the number tree
    private NumberNode numInsert(String number, String name) {
        NumberNode parent = null;
        NumberNode trav = rootNumber;//starts at root

        while (trav != null) {
            parent = trav;
            if (number.compareTo(trav.number) < 0)//if smaller go left
                trav = trav.leftNumber;
            else if (number.compareTo(trav.number) > 0)// else it is bigger and go right
                trav = trav.rightNumber;
            else //if same then do not add
                return null;
        }
        //when method reaches the appropriate
        if (parent == null) { //if root is empty
            rootNumber = new NumberNode(name, number);
            return rootNumber;
        } else if (number.compareTo(parent.number) < 0) { // if to add is sammer then add left
            parent.leftNumber = new NumberNode(name, number);
            return parent.leftNumber;
        } else if (number.compareTo(parent.number) > 0) { // else add right
            parent.rightNumber = new NumberNode(name, number);
            return parent.rightNumber;
        } else
            return null;

    }

    //helper method that inserts into the name Tree, same comments from method above apply
    private boolean nameInsert(String number, String name) {
        NameNode parent = null;
        NameNode trav = rootName;

        while (trav != null) {
            parent = trav;
            if (name.compareTo(trav.name) < 0)
                trav = trav.leftName;
            else
                trav = trav.rightName;
        }

        if (parent == null) {
            rootName = new NameNode(name, number);
            return true;
        } else if (name.compareTo(parent.name) < 0) {
            parent.leftName = new NameNode(name, number);
            return true;
        } else if (name.compareTo(parent.name) > 0) {
            parent.rightName = new NameNode(name, number);
            return true;
        } else if (name.compareTo(parent.name) == 0) {
            parent.numbers.add(number);
            return true;
        }
        return false;
    }

    //helper method that finds the element and deletes from the name tree
    private boolean nameToDelete(String number1, String name1) {
        NameNode parent = null;
        NameNode trav = rootName;
        // navigate as long as did not reach end or find the combination to delete
        while (trav != null && trav.name.compareTo(name1) != 0 && !trav.numbers.contains(number1)) {
            parent = trav;
            if (name1.compareTo(trav.name) < 0)
                trav = trav.leftName;
            else
                trav = trav.rightName;
        }//once found
        if (trav == null) {
            return false;
        } else if (!trav.numbers.getLast().equals(trav.numbers.getFirst())) { //if numbers linked list contains more than 1 element
            trav.numbers.remove(number1);//remove only that number and keep name
            return true;
        } else {
            deleteByName(trav, parent); //only deals with nodes that have only one number asscoiated to a name
            return true;
        }

    }

    //helper method that removes the node and replaces its name a number with another one.
    private void deleteByName(NameNode toDelete, NameNode parent) {
        //case 1 and 2, only has one child on either side, replaces the node to delete with that child
        if (toDelete.leftName == null || toDelete.rightName == null) {
            NameNode child = null;
            if (toDelete.rightName != null)
                child = toDelete.rightName;
            else
                child = toDelete.leftName;
            if (rootName == toDelete) {
                rootName = child;
            } else if (toDelete.name.compareTo(parent.name) < 0)
                parent.leftName = child;
            if (toDelete.name.compareTo(parent.name) > 0)
                parent.rightName = child;
        } else { // case three
            NameNode repParent = toDelete;
            //randomly chooses the either replace with the smallest on the right subtree
            if (Math.random() < 0.5) {
                NameNode rep = toDelete.rightName;
                while (rep.leftName != null) {
                    repParent = rep;
                    rep = rep.leftName;
                }
            } else { //or the largest on the left subtree
                NameNode rep = toDelete.leftName;
                while (rep.rightName != null) {
                    repParent = rep;
                    rep = rep.rightName;
                }
                toDelete.name = rep.name;
                toDelete.numbers = rep.numbers;
                deleteByName(rep, repParent);
            }
        }
    }
    //helper method to find the number to delete(essentially the same as the method for name)
    private boolean numToDelete(String number1, String name1) {
        NumberNode parent = null;
        NumberNode trav = rootNumber;

        while (trav != null && trav.name.equals(name1) && !trav.number.equals(number1)) {
            parent = trav;
            if (name1.compareTo(trav.name) < 0)
                trav = trav.leftNumber;
            else
                trav = trav.rightNumber;
        }
        if (trav == null) {
            return false;
        } else {
            deleteByNumber(trav, parent);
            return true;
        }
    }

    //helper method to numToDelete that removes the node and replaces its name a number with another one.
    //same concept as deleteByName.
    private void deleteByNumber(NumberNode toDelete, NumberNode parent) {
        if (toDelete.leftNumber == null || toDelete.rightNumber == null) {
            NumberNode child = null;
            if (toDelete.rightNumber != null)
                child = toDelete.rightNumber;
            else
                child = toDelete.leftNumber;
            if (rootNumber == toDelete) {
                rootNumber = child;
            } else if (toDelete.number.compareTo(parent.number) < 0)
                parent.leftNumber = child;
            if (toDelete.number.compareTo(parent.number) > 0)
                parent.rightNumber = child;
        } else {
            NumberNode repParent = toDelete;

            if (Math.random() < 0.5) {
                NumberNode rep = toDelete.rightNumber;
                while (rep.leftNumber != null) {
                    repParent = rep;
                    rep = rep.leftNumber;
                }
            } else {
                NumberNode rep = toDelete.leftNumber;
                while (rep.rightNumber != null) {
                    repParent = rep;
                    rep = rep.rightNumber;
                }
                toDelete.name = rep.name;
                toDelete.number = rep.number;
                deleteByNumber(rep, repParent);
            }
        }
    }
}
