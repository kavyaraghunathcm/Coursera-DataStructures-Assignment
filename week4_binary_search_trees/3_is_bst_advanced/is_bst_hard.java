import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;
        List<Integer> list;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
        boolean solve(){
            if (tree.length==0){
                return true;
            }
            list = new ArrayList<Integer>();
            list=  inOrder( list,0);
            return isBinarySearchTree();
        }
        private List<Integer> inOrder(List<Integer> result, int i) {
            if(i==-1|| list==null)
                return result;
            Node node = tree[i];
            result =inOrder(result, node.left);
            assert result != null;
            if(node.left!=-1 && tree[node.left].key==node.key){
                return null;
            }
            result.add(node.key );
            result=inOrder(result,node.right);
            return result;
        }


        boolean isBinarySearchTree() {
            if (list==null){
                return false;
            }
            // Implement correct algorithm here
            for (int i = 0; i < list.size()-1 ; i++) {
                if(list.get(i)>list.get(i+1)){
                    return false;
                }
                else if(list.get(i)==list.get(i+1)){

                }
            }
            return  true;
        }


    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
