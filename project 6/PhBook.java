public class PhBook {
    Person[] pb; // array of objects person
    int capacity; // the size of the array
    int pointer; // keeping track of the last empty space of the array

    //constructor
    public PhBook(int capacity){
        pb = new Person[capacity];
        this.capacity = capacity;
        pointer = 0;
    }

    //creates a new entry in the phonebook
    public boolean PhBookInsert(String name, long num){
        if (pointer < capacity) {//if not full
            Person p = new Person(name, num); // makes new object
            pb[pointer] = p; //adds it into the array
            pointer ++;// incrmeent how full the array is
            return true;
        }
        else return false;
    }

    //deletes an element from the array
    // worst case of O(n^2) but keeps the populated portion of the array in one position
    public boolean PhBookDelete(String name, long num){
            Person temp = new Person(name, num); // makes a new element identical to the the one to delete
            for (int i = 0; i < pointer; i++) { // searches for the element
                if (pb[i].compareTo(temp) == 0) {
                    pb[i] = null;// empties its position
                    //shifts all the elements after the one deleted down one position
                    System.arraycopy(pb, i + 1, pb, i, pointer - i);
                    pointer--;
                    return true;
                }
            }return false;
    }

    //heapsort
    public void heapSort(Person[] phb){
        int size = phb.length;
        for(int i = (size/2) - 1; i >= 0; i--){ // starting in the middle element, we change siftdown
            siftDown(phb,i,size);
        }

        for(int i = size -1 ; i >= 0; i-- ){ //extract the root and reorders all the elements switching foirst with last
            Person p = phb[0];
            phb[0] = phb[i];
            phb[i] = p;

            siftDown(phb,0,size);
        }

    }

    private void siftDown(Person[] phb, int root, int size){
        int r = root;//root of the subtree worked on
        int left = (2*r)+1; //left child
        int right = (2*r)+2; // right child

        if(left < size && phb[r].compareTo(phb[left])>0) // if left is bigger than root
            r = left; // left becomes root

        if(right < size && phb[r].compareTo(phb[right])>0) // if right is bigger than root
            r = right;// right becomes root

        if (r != root){ // if the largest element found isnt the root then we make it the root
            Person temp = phb[root];
            phb[root] = phb[r];
            phb[r] = temp;
            siftDown(phb, r, size); // recursively siftsdown starting from the new root

        }
    }


    // nested class person
    public static class Person implements Comparable<Person>{
        long ph_number; //number
        String name; // name of the person

        //constructor
        public Person(String name, long num){
            this.name = name;
            ph_number = num;
        }

        @Override
        public int compareTo(Person o) {
            if(this.name.equals(o.name)) {// if they have equal names
                return (int)(this.ph_number - o.ph_number);//compare numbers
            }
            else
                return this.name.compareTo(o.name);//compare names by value of character

        }
    }

}


