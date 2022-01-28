import java.util.LinkedList;
import java.util.NoSuchElementException;
//author: omar loudghiri oxl51
//An implemaentation of two overlaying  AVL Trees
// it is 80% similar to the Double tree from assignment 3 the slight difference is the node balancing
public class AVLPhBook {

    NumberNode rootNumber; //root of the number tree where the tree is sorted by number
    NameNode rootName; // root of a tree where it is sorted by name

    //constructor
    public AVLPhBook() {
        rootNumber = null;
        rootName = null;
    }

    //method to insert a name and number into the two trees
    //O(log n) because the inner methods are the same complexity
    public boolean PhBInsert(String number, String name) {
        NumberNode numNode = numInsert(number, name); //inserts into the number tree
        if (numNode != null) {
            return nameInsert(number, name); // inserts into the name tree
        } else
            return false;

    }

    //method to delete from both trees
    //O(log n) because the inner methods are the same complexity
    public boolean PhBDelete(String number, String name) {
        if (nameToDelete(name)) //if deleted from the name tree
            return numToDelete(number,name); //deletes from the num tree
        else
            return false;
    }

    //search method using the number, overloaded
    //O(log n) because the inner method is the same complexity
    public String PhBSearch(Long number){
        return PhBPhoneSearch(number.toString());
    }

    //search method using name, overloaded
    //O(log n) because the inner method is the same complexity
    public String PhBSearch(String name){
        return PhBNameSearch(name);
    }



    // method to search by name
    //O(log n) because looks in an AVL tree
    public String PhBNameSearch(String name1) {
        NameNode trav = rootName; // starts at the root
        NameNode found = null;

        while (trav != null) {
            if (name1.compareTo(trav.name) == 0) { //if the root is the element
                found = trav;
                return found.number; // return the list of numbers
            }else if (name1.compareTo(trav.name) < 0) //if smaller go left
                trav = trav.left;
            else // else it is bigger and go right
                trav = trav.right;
        }
            return null;
    }

    //search by phone number " Couldn't be called PhBSearch because
    //O(log n) because looks in an AVL tree
    public String PhBPhoneSearch(String number1) {
        NumberNode trav = rootNumber; //starts at root
        NumberNode found = null;

        while (trav != null) {
            if (number1.compareTo(trav.number) == 0) {
                found = trav;
                return found.name;
            }
            else if (number1.compareTo(trav.number) < 0)//if smaller go left
                trav = trav.left;
            else// else it is bigger and go right
                trav = trav.right;
        }
            return null;
    }

    //helper method that inserts into the number tree
    //O(log n) because searches in an AVL tree and then inserts
    private NumberNode numInsert(String number, String name) {
        NumberNode parent = null;
        NumberNode trav = rootNumber;//starts at root

        while (trav != null) {
            parent = trav;
            if (number.compareTo(trav.number) < 0)//if smaller go left
                trav = trav.left;
            else if (number.compareTo(trav.number) > 0)// else it is bigger and go right
                trav = trav.right;
            else //if same then do not add
                return null;
        }
        //when method reaches the appropriate
        if (parent == null) { //if root is empty
            rootNumber = new NumberNode(name, number, null);
            return rootNumber;
        } else if (number.compareTo(parent.number) < 0) { // if to add is same then add left
            parent.left = new NumberNode(name, number, parent);
            parent.height +=1;
            balance(parent);
            return parent.left;
        } else if (number.compareTo(parent.number) > 0) { // else add right
            parent.right = new NumberNode(name, number, parent);
            parent.height +=1;
            balance(parent);
            return parent.right;
        } else
            return null;

    }

    //helper method that inserts into the name Tree, same comments from method above apply
    //O(log n) because searches in an AVL tree and then inserts
    private boolean nameInsert(String number, String name) {
        NameNode parent = null;
        NameNode trav = rootName;

        while (trav != null) {
            parent = trav;
            if (name.compareTo(trav.name) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }

        if (parent == null) {
            rootName = new NameNode(name, number, null);
            return true;
        } else if (name.compareTo(parent.name) < 0) {
            parent.left = new NameNode(name, number, parent);
            parent.height +=1;
            balance(parent);
            return true;
        } else if (name.compareTo(parent.name) > 0) {
            parent.right = new NameNode(name, number, parent);
            parent.height +=1;
            balance(parent);
            return true;
        } else
        return false;
    }

    //helper method that finds the element and deletes from the name tree
    //O(log n) because searches in an AVL tree and then inserts
    private boolean nameToDelete(String name1) {
        NameNode parent = null;
        NameNode trav = rootName;
        // navigate as long as did not reach end or find the name to delete
        while (trav != null && trav.name.compareTo(name1) != 0) {
            parent = trav;
            if (name1.compareTo(trav.name) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }//once found
        if (trav == null) {
            return false;
        } else {
            deleteByName(trav, parent);//only deals with nodes that have only one number asscoiated to a name
            if(parent != null)
                balance(parent);
            return true;
        }

    }

    //helper method that removes the node and replaces its name a number with another one.
    //O(1) because only replaces node values
    private void deleteByName(NameNode toDelete, NameNode parent) {
        //case 1 and 2, only has one child on either side, replaces the node to delete with that child
        if (toDelete.left == null || toDelete.right == null) {
            NameNode child = null;
            if (toDelete.right != null)
                child = toDelete.right;
            else
                child = toDelete.left;
            if (rootName == toDelete) {
                rootName = child;
            } else if (toDelete.name.compareTo(parent.name) < 0)
                parent.left = child;
            if (toDelete.name.compareTo(parent.name) > 0)
                parent.right = child;
        } else { // case three
            NameNode repParent = toDelete;
            //randomly chooses the either replace with the smallest on the right subtree
            if (Math.random() < 0.5) {
                NameNode rep = toDelete.right;
                while (rep.left != null) {
                    repParent = rep;
                    rep = rep.left;
                }
            } else { //or the largest on the left subtree
                NameNode rep = toDelete.left;
                while (rep.right != null) {
                    repParent = rep;
                    rep = rep.right;
                }
                toDelete.name = rep.name;
                toDelete.number = rep.number;
                deleteByName(rep, repParent);
            }
        }
    }
    //helper method to find the number to delete(essentially the same as the method for name)
    ////O(log n) because searches in an AVL tree and then inserts
    private boolean numToDelete(String number1, String name1) {
        NumberNode parent = null;
        NumberNode trav = rootNumber;

        while (trav != null && trav.name.equals(name1) && !trav.number.equals(number1)) {
            parent = trav;
            if (name1.compareTo(trav.name) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
        if (trav == null) {
            return false;
        } else {
            deleteByNumber(trav, parent);
            if(parent != null)
                balance(parent);
            return true;
        }
    }

    //helper method to numToDelete that removes the node and replaces its name a number with another one.
    //same concept as deleteByName.
    //O(1) constant because does one operation per call, no iteration
    private void deleteByNumber(NumberNode toDelete, NumberNode parent) {
        if (toDelete.left == null || toDelete.right == null) {
            NumberNode child = null;
            if (toDelete.right != null)
                child = toDelete.right;
            else
                child = toDelete.left;
            if (rootNumber == toDelete) {
                rootNumber = child;
            } else if (toDelete.number.compareTo(parent.number) < 0)
                parent.left = child;
            if (toDelete.number.compareTo(parent.number) > 0)
                parent.right = child;
        } else {
            NumberNode repParent = toDelete;

            if (Math.random() < 0.5) {
                NumberNode rep = toDelete.right;
                while (rep.left != null) {
                    repParent = rep;
                    rep = rep.left;
                }
            } else {
                NumberNode rep = toDelete.left;
                while (rep.right != null) {
                    repParent = rep;
                    rep = rep.right;
                }
                toDelete.name = rep.name;
                toDelete.number = rep.number;
                deleteByNumber(rep, repParent);
            }
        }
    }

    // helper method to get the value of the balance / imbalance
    //O(1)
    private int getBalance(Node node){
        int left = node.left != null ? node.left.height : 0;
        int right = node.right != null ? node.right.height:0;

        return left - right;
    }

    //method to check the height and balance the number tree
    //O(1) because performs permutations on a node that is already found
    private void balance(Node node){
        int balance = getBalance(node);

        // if the left child creates the imbalance
        if(balance > 1 ) {
            if(node.number.compareTo(node.left.number) > 0)//case 3a in the slides, added to the left of the left child
                rotationRight(node);
            else if(node.number.compareTo(node.left.number) < 0){//case 3c, added to the right of the left child
                //left-right double rotation
                node.left = rotationLeft(node.left);
                rotationRight(node);
            }

        } // imbalance of the right
        else if(balance < -1 ){
            if(node.number.compareTo(node.left.number) < 0 ) //case 3b added to the right of the right child
                rotationLeft(node);
            else if(node.number.compareTo(node.left.number) > 0 ){ // case 3d added to the left of the right child
                //right-left rotation
                node.right = rotationRight(node.left);
                rotationLeft(node);
            }
        }
    }

    //method to find the height of the node we want to balance
    //O(1)
    private void setHeight(Node node){
        node.height = Math.max(node.left.height, node.right.height) + 1;
    }

    //method for right rotation
    //O(1) because only changes node values
    private Node rotationRight(Node node){
        //perform a right rotation
        Node newParent = node.left;
        Node newLeft = newParent.right;
        newParent.right = node;
        node.left = newLeft;

        //reset the heights of the new nodes
        setHeight(newParent);
        setHeight(node);
        return newParent;
    }

    //method for left rotation
    //O(1) because only changes the values of nodes
    private Node rotationLeft(Node node){
        //rotate left
        Node newParent = node.right;
        Node newRight = newParent.left;
        newParent.left = node;
        node.right = newRight;

        //reset the heights of the new nodes
        setHeight(newParent);
        setHeight(node);
        return newParent;
    }



}
