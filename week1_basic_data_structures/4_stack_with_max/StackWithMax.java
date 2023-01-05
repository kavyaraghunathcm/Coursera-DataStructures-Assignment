import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        int max=-1;
        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                if(stack.isEmpty()){
                    max=value;
                    stack.push(value);
                }
                else if(value>max){
                    stack.push(2*value-max);
                    max=value;
                }
                else {
                    stack.push(value);
                }
            } else if ("pop".equals(operation)) {
                if(!stack.isEmpty()){
                   int t= stack.pop();
                   if(t>max){
                       max=2*max-t;
                   } else if (stack.isEmpty()) {
                       max=-1;
                   }
                }
            } else if ("max".equals(operation)) {
                if(max!=-1){
                System.out.println(max);}
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
