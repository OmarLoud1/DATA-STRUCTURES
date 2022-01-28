
//implementation of the hashtable needed to find anagrams
public class Hashtable {
    private NodeChain[] table;// the array of entries
    private int size; // the maximum size of the table
    private int full; //how full the table is

    //constructor that makes a table of size 1
    public Hashtable(){
        table = new NodeChain[1];
        size = table.length;
        full = 0;

    }

    //hash function
    private int hash(char c){
      return c % size;
    }

    //returns that there are no more entries in this table
    public boolean isempty(){return full == 0;}

    //inserts a character into the table
    public void add(char c){
        //hash the character
        int index = hash(c);

        //get the value at that index
        NodeChain node = table[index];

        //if there is already a value
        if (node != null) {

            // insert in front
            NodeChain n = new NodeChain(index, c);
            n.next = node;
            table[index] = n;
        }else{// the node is empty and we make a new one with the input character
        node = new NodeChain(index, c);
        table[index] = node; // assign it to that table
        }
        //increments the number of elements
        full ++;

        // according to lecture chaining is supposed to not go above a load size of 1.
        if((1.0*full)/(1.0*size) > 1 ){
            rehash();
        }
    }

    // makes a new table of double the size
    public void rehash(){
        NodeChain[] rehashed = new NodeChain[size*2];
        size *=2;
        // copies all the elements
        for (int i = 0; i < size/2; i++) {
            NodeChain node = table[i];
            while (node != null) { // if the element exists
                NodeChain next = node.next;
                int h = hash(node.c);//hash the char again to find its position in the new table
                //insert in front
                node.next = rehashed[h];
                rehashed[h] = node;
                //iterate
                node = next;
            }
        }
        table = rehashed;
    }

    // remove method to remove the similar characters from the table
    public boolean remove(char c1){
        int h = hash(c1); //hash the letter to find it in the table
        if(table[h]!= null){
            if(table[h].c == c1){ //if first element of chain
                //remove front
                table[h] = table[h].next;
                full --;
                return true;
            }else{ // if in the chain
                NodeChain pointer = table[h].next; //keeps track of the next elem
                NodeChain before = table[h]; // keeps track of the element before
                while(pointer != null && pointer.c != c1){ // iterate through untill the node to remove is found
                    before = pointer;
                    pointer = pointer.next;
                }
                if(pointer.c == c1){ // if the element is found
                    before.next = pointer.next;//remove pointer from the chain
                    full--;
                    return true;
                }
            }
        }
        return false;
    }

}
