
public class shuntingYard {

    //Conversion Method: infix --> postfix
    public static String fixConversion(String expression){
        //initialize empty string
        String postfix = "";
        //Create empty stack for holding
        Stack operatorStack = new Stack();

        //Loop through each char in expression
        for(char ch : expression.toCharArray()){
            //If char is a letter
            if(Character.isLetter(ch)){
                postfix += ch;
                //if ch is ( pop until not
            } else if (ch == '('){
                operatorStack.push(ch);
                //if ch is ) pop until not
            } else if (ch == ')'){
                //while
                while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix += operatorStack.pop();
                }
                operatorStack.pop();
                //if ch is operator
            } else if (isOperator(ch)){
                //pop operator from stack
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(ch)){
                    postfix += operatorStack.pop();
                }
                operatorStack.push(ch); //Remove ( from stack
            }
        }
        //pop all remaining operator
        while (!operatorStack.isEmpty()){
            postfix += operatorStack.pop();
        }
        //return last
        return postfix;

    }

    //Operator Check
    private static boolean isOperator(char ch){
        //ch equals + or * or !
        return ch == '+' || ch == '*' || ch == '!';
    }

    //Precendence order
    private static int precedence(char operator){
        switch (operator) {
            case '!': return 3; //Highest
            case '*': return 2;
            case '+': return 1; //lowest
            default: return 0; //Non operator
        }
    }
}
