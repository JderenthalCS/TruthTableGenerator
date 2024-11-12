import java.util.EmptyStackException;

public class Stack {

    //top = top of stack
    private Node top;

    //Constructor
    public Stack(){
        top = null;
    }

    //isEmpty
    public boolean isEmpty(){
        return top == null;
    }

    //Push
    public void push(char val){
        Node temp = new Node(val);
        temp.next = top;
        top = temp;
    }

    //Pop
    public char pop(){
        if(!isEmpty()){
            char val = top.data;
            top = top.next;
            return val;
        } else {
             return 'e';
        }
    }

    public char peek(){
        if (!isEmpty()){
            return top.data;
        } else {
            throw new EmptyStackException();
        }
    }
}
