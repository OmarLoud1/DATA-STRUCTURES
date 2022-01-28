import java.util.LinkedList;

public class NameNode {
    String name;
    LinkedList<String> numbers;

    NameNode leftName;
    NameNode rightName;

    public NameNode (String name, String num){
        numbers = new LinkedList<String>();

        this.name = name;
        numbers.add(num);

        leftName = null;
        rightName = null;
    }
}
