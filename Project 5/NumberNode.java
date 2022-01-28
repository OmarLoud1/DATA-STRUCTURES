public class NumberNode extends Node {
    String number;
    String name;

    int balance;
    int height;

    NumberNode parent;
    NumberNode left;
    NumberNode right;


    public NumberNode (String name, String num, NumberNode par){
        this.name = name;
        number = num;

        height = 1;

        parent = par;
        left = null;
        right = null;
    }
}
