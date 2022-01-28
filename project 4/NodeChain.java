//Entry Class, not named entry because I misundurstood the meaning of hw at first.
public class NodeChain {
    public int index; // where it in the hashtable
    public final char c; // the character it stores


    public NodeChain next;//chaining to deal with collisions

    //constructor that takes the initial index and the final character.
    public NodeChain(int i, char character){
        index = i;
        c = character;
        next = null;
    }
}
