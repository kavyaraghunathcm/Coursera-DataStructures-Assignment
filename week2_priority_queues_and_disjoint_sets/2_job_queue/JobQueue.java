import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        PriorityQueue<Worker> workers =new PriorityQueue<>(numWorkers, Comparator.comparing(Worker::getPriority).thenComparing(Worker::getKey));
        for (int i = 0; i < numWorkers; i++) {
            workers.add(new Worker(i,0));
        }
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        //long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Worker bestWorker = workers.poll();
            assert bestWorker != null;
            startTime[i]=bestWorker.priority;
            assignedWorker[i]=bestWorker.key;
            bestWorker.setPriority(bestWorker.priority+duration);
            workers.add(bestWorker);
           /* for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }*/
            //assignedWorker[i] = bestWorker;
            //startTime[i] = nextFreeTime[bestWorker];
            //nextFreeTime[bestWorker] += duration;
        }
    }

     class Worker{
        long priority;
        int key;

         public Worker(int key, int priority) {
             this.key=key;
             this.priority=priority;
         }

         public long getPriority() {
             return priority;
         }

         public void setPriority(long priority) {
             this.priority = priority;
         }

         public int getKey() {
             return key;
         }

         public void setKey(int key) {
             this.key = key;
         }
     }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
