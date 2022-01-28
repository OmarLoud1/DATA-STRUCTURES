import java.io.File;
import java.util.Scanner;
//HW2 Omar Loudghiri oxl51 CSDS 233
public class A2P1 {

    public static boolean inMatrix(int[ ] [ ] matrix, int x){

        int i = matrix.length - 1; //starts the array from the bottom
        int k = 0;//starts at the left

        //traverses the array until reaching either the top or right end
        while(i >= 0 && k < matrix.length){
            if(matrix[i][k] == x) //if the value is found return true
                return true;
            else if(matrix[i][k] < x){ // if the value is smaller then it is possible to find the result in this row
                k++; // moves right
            }
            else if(matrix[i][k] > x){ // if the value at this position is bigger then all other on this row will be bigger
                i--;//moves up
            }
        }
        return false;//if reached either end without finding the result then the number isn't in the array


    }

    public static void main(String[ ] args) {
        //1. Read from a file and fill a local two-dimensional array
        int dim = Integer.parseInt(args[0]); //the first input is the dimension
        int[][] arr = new int[dim][dim]; // creates an array of those dimensions

        try {
            File file = new File(args[1]); //opens the text file
            Scanner sc = new Scanner(file);
            while(sc.hasNextInt()){
               for(int i = 0; i < dim; i++){
                   for (int j = 0 ; j < dim ; j++){
                       arr[i][j] = sc.nextInt(); // adds all elements to th array
                   }
               }
            }
        }catch (Exception e){
            System.out.println("Input a valid file"); // if the file doesn't open
        }

        //2. invoke inMatrix and report the time difference
        long start = System.nanoTime();//starts counting time

        System.out.println("result of inMatrix is: "+ inMatrix(arr,1 )); // finding an element

        long end = System.nanoTime(); // stops counting

        float run = end - start; // calculates the time

        //4. print result in a digestible way
        System.out.println("it took " + run/1000000 + " ms to run inMatrix on an 2d array of dimension :"+ arr.length);


    }


// method for testing purposes, generates an array of the input size
    public static int[][] generate(int size){
        int[][] arr1 = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arr1[i][j] = j+i;
            }
        }
        return arr1;
    }

/* This is the code I used to test the runtime of my method
    ////// TESTING ///////////////////////////////////////////////
    int [][] arr1 = generate(25121);
    arr1[0][25120] = -1;
    long start1 = System.nanoTime();//starts counting time

        System.out.println("result of inMatrix is: "+ inMatrix(arr1, -1 )); // finding an element


    long end1 = System.nanoTime(); // stops counting

        int [][] arr1 = generate(1500);
        arr1[0][1499] = -1;
        System.out.println("result of inMatrix is: "+ inMatrix(arr1, -1)); // finding an element

    float run1 = end1 - start1; // calculates the time
        System.out.println("it took " + run1/1000000 + " ms to run inMatrix on an 2d array of dimension: "+ 25121);
*/


}

