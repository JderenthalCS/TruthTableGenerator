public class GenerateTable {

    //Generates Truth Table
    public static void generateTable(String expression) {
        //Converts infix to postfix
        String postfix = shuntingYard.fixConversion(expression);

        //Identify variables
        boolean[] variablesPresent = new boolean[26]; //Array to keep variables in order
        int numVar = 0; //counter for num of variables

        //iterate through each ch in expression
        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch)) { // if character is variable
                int index = ch - 'A'; //Calculate index (A=0, B=1)
                if (!variablesPresent[index]) { //If variable hasn't been counted
                    variablesPresent[index] = true; //Mark as true
                    numVar++; //Increment count
                }
            }
        }

        //Creating array to store variables
        char[] variables = new char[numVar];
        int varIndex = 0;//index for variable array

        //fill array with variable names
        for (int i = 0; i < 26; i++) {
            if (variablesPresent[i]) { //If a variable is present
                variables[varIndex++] = (char) (i + 'A'); //Add it to variable array
            }
        }

        //Display header
        for (char var : variables) {
            System.out.print(var + " | "); //display | after each variable
        }
        System.out.println("Result");
        System.out.println("-".repeat(numVar * 4 + 7)); //display lines


        int rows = (int) Math.pow(2, numVar); //total rows in truth table

        //Iterate over all possible combo's
        for (int i = 0; i < rows; i++) {
            boolean[] values = new boolean[26]; //array to store T/F

            //Assign T/F to each variables
            for (int j = 0; j < numVar; j++) {
                //Find if T/F
                values[variables[j] - 'A'] = (i / (int) Math.pow(2, numVar - j - 1)) % 2 != 0;
                //Print T if true, F if false
                System.out.print((values[variables[j] - 'A'] ? "T" : "F") + " | ");
            }

            //result equals postfix expression within truth table
            boolean result = evaluatePostfix(postfix, values);
            //Print T/F for result
            System.out.println(result ? "T" : "F");
        }
    }

    private static boolean evaluatePostfix(String postfix, boolean[] values) {
        Stack stack = new Stack(); //Stack to hold temp results

        for (char ch : postfix.toCharArray()) {
            if (Character.isLetter(ch)) {

                //Checks if variable is T/F
                stack.push(values[ch - 'A'] ? 'T' : 'F');
            } else {
                //Declaring op1 and op2 as variable boolean
                boolean op1, op2;
                switch (ch) {
                    case '!': // NOT
                        op1 = stack.pop() == 'T';
                        //If is not op1 is true, push T, else push F
                        stack.push(!op1 ? 'T' : 'F');
                        break;
                    case '*': // AND
                        op2 = stack.pop() == 'T';
                        op1 = stack.pop() == 'T';
                        //If both op1 and op2 are true, push T, else push F
                        stack.push(op1 && op2 ? 'T' : 'F');
                        break;
                    case '+': // OR
                        op2 = stack.pop() == 'T';
                        op1 = stack.pop() == 'T';
                        //If op1 or op2 are true, push T, else push F
                        stack.push(op1 || op2 ? 'T' : 'F');
                        break;
                }
            }
        }
        return stack.pop() == 'T';
    }
}