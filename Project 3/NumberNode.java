public class NumberNode {
    String number;
    String name;

    NumberNode leftNumber;
    NumberNode rightNumber;

    public NumberNode (String name, String num){
        this.name = name;
        number = num;

        leftNumber = null;
        rightNumber = null;
    }
}
