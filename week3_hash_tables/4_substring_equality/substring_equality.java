import java.util.*;
import java.io.*;

public class substring_equality {
	public class Solver {
		private String s;
		private long[][] H;
		int m1= 1000000007;int m2 = 1000000009;
		Random random=new Random(5000000);
		int x;
		long[] xpowersm1;

		long[] xpowersm2;

		public Solver(String s) {
			this.s = s;
			x= random.nextInt(1000000000);
			preComputeHash();
			computepowers();
		}
		private void computepowers(){
			xpowersm1=new long[s.length()+1];
			xpowersm2=new long[s.length()+1];
			xpowersm1[0]=1;
			xpowersm2[0]=1;
			for (int i = 1; i <=s.length() ; i++) {
				xpowersm1[i]=(xpowersm1[i-1]*x)%m1;
				xpowersm2[i]=(xpowersm2[i-1]*x)%m2;
			}
		}
		public boolean ask(int a, int b, int l) {
			long H1al=(H[0][a+l]-xpowersm1[l]*H[0][a])%m1 +m1;
			long H1bl=(H[0][b+l]-xpowersm1[l]*H[0][b])%m1 +m1;
			long H2al=(H[1][a+l]-xpowersm2[l]*H[1][a])%m2 +m2;
			long H2bl=(H[1][b+l]-xpowersm2[l]*H[1][b])%m2 +m2;
			return  H1al%m1==H1bl%m1 && H2al%m2==H2bl%m2 ;
			//return s.substring(a, a + l).equals(s.substring(b, b + l));
		}

		public long[][] preComputeHash(){
			H =new long[2][s.length()+1];
			long[] h1=H[0];long[] h2=H[1];
			h1[0]=0;h2[0]=0;
			for (int i = 1; i <=s.length() ; i++) {
				h1[i]=(((x * h1[i - 1] + s.charAt(i-1)))%m1+m1)%m1;
				h2[i]=(((x * h2[i - 1] + s.charAt(i-1)))%m2+m2)%m2;
			}
			return H;
		}
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();
			out.println(solver.ask(a, b, l) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}

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
}
