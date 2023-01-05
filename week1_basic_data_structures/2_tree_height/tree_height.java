import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		Node root;
		Node[] nodes;

		private  class Node
		{
			int key;
			List<Node> children;
			Node(){
				children=new ArrayList<>();
			}
			void addChild(Node child){
				this.children.add(child);
			}
		}
		void createTree(){
			nodes= new Node[n];
			for (int i = 0; i < n; i++) {

				nodes[i]=new Node();
				nodes[i].key =i;
			}
			for (int i = 0; i < n; i++) {
				int p= parent[i];
				if(p==-1){
					root=nodes[i];
				}
				else{
					nodes[p].addChild(nodes[i]);
				}
			}
		}
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}



		int computeHeight(Node n) {
			int maxheight=0;
			if(!n.children.isEmpty()){

				for (Node c:n.children) {
					maxheight=Math.max(maxheight,computeHeight(c));
				}
			}
			return maxheight+1;

			// Replace this code with a faster implementation
			/*int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;*/
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		tree.createTree();
		System.out.println(tree.computeHeight(tree.root));
	}
}
