import java.util.LinkedList;

public class NameNode extends Node {
    String name;
    String number;

    int balance;
    int height;

    NameNode parent;
    NameNode left;
    NameNode right;

    public NameNode (String name, String num, NameNode par){


        this.name = name;
        this.number = num;

        height = 1;

        parent = par;
        left = null;
        right = null;
    }
}
