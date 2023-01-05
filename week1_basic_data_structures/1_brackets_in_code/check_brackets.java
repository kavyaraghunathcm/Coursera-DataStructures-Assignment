import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        int currPos=1;
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.add(new Bracket(next,position));
                currPos=position+1;
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(!opening_brackets_stack.empty()){
                    Bracket bracket= opening_brackets_stack.pop();
                    if(!bracket.match(next)){
                        System.out.println(position+1);
                        return;
                    }
                    if(!opening_brackets_stack.empty() ) {
                        currPos=opening_brackets_stack.peek().position+1;
                    }
                    else currPos=1;

                }
                else{
                    System.out.println(position+1);
                    return;
                }
            }


        }
        if(opening_brackets_stack.empty()){
            System.out.println("Success");
            return;
        }
        else{
            System.out.println(currPos);
            return;
        }
        // Printing answer, write your code here
    }
}
