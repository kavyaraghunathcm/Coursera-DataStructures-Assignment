import java.io.*;
import java.util.*;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    static Random random=new Random();

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurencesRabinKarp(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }


    private static long[] preComputeHashes(String T, int plength, int prime,int x )
    {
        long[] H= new long[(T.length())-plength+1];
        String  S=T.substring(T.length()-plength);
        H[T.length()-plength]=polyHash(S,prime,x);
        long y=1;
        for (int i = 0; i < plength; i++) {
            y=(y*x)%prime;
        }
        for (int i = T.length()-plength-1; i >=0 ; i--) {
            H[i]= ((x*H[i+1]+ T.charAt(i)-y*T.charAt(i+plength))%prime+prime)%prime;
        }
        return  H;
    }

    private static long polyHash(String s, int prime, int x) {
        long hashOfString=0;
        for (int i = s.length()-1; i >=0 ; i--) {
            hashOfString= (hashOfString*x+s.charAt(i))%prime;
        }
        return hashOfString;
    }

    private static List<Integer> getOccurencesRabinKarp(Data input){
        String P = input.pattern, T = input.text;
        List<Integer> positions= new ArrayList<>();
        int plength = P.length(), tlength = T.length();
        int prime=7368787;
        int x= random.nextInt(prime-1);
        long[] H=preComputeHashes(T,plength,prime,x);
        long pHash = polyHash(P,prime,x);
        for (int i = 0; i <=tlength-plength ; i++) {
            if(pHash!=H[i]){
                continue;
            }
            if (P.equals(T.substring(i,i+plength))) {
                positions.add(i);
            }
        }
        return positions;
    }
    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
	    boolean equal = true;
	    for (int j = 0; j < m; ++j) {
		if (s.charAt(j) != t.charAt(i + j)) {
		     equal = false;
 		    break;
		}
	    }
            if (equal)
                occurrences.add(i);
	}
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

