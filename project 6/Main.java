import java.util.Random;
import java.util.Arrays;
public class Main {

    public static void main(String[] args){

        //execute for 10, 100, 1000, 10000, 100000, 1000000
        for( int cap = 10; cap <= 1000000; cap *= 10){

            PhBook phBook = new PhBook(cap);// new phone book
            for (int i = 0; i < cap; i++) {
                //generate random names and numbers
                String name = genName();
                long number = genNum();
                PhBook.Person p = new PhBook.Person(name, number);
                phBook.pb[i] = p;// add all the Person objects to the phone book
            }
            //clone the array of the phone book to remove that complexity from the calculated time
            //in order not to resort a sorted array
            PhBook.Person[] arr1 = phBook.pb.clone();
            PhBook.Person[] arr2 = phBook.pb.clone();

            System.out.println("this is the time spent sorting an array with size: "+cap);

            long start = System.nanoTime();

            phBook.heapSort(arr1);

            long end = System.nanoTime();

            System.out.println();
            System.out.println("Execution time in microseconds of my heapsort: " + (end - start) / 1000 + "ms");
            System.out.println();

            long start1 = System.nanoTime();

            Arrays.sort(arr2);

            long end1 = System.nanoTime();

            System.out.println("Execution time in microseconds of the Java.arrays sort: " + (end1 - start1) / 1000 + "ms");

            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        }
    }

    private static String genName(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
    private static int genNum() {

        Random random = new Random();
        int number = random.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return Integer.parseInt(String.format("%06d", number));
    }
}
