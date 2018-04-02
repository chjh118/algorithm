import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /**
     * Time complexity: n * log(n)
     * Space complexity: n
     */

    private static final int MAX_XY = 100000;
    private int[] parent;

    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    private void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine().trim());
        parent = new int[MAX_XY + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokens = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            if (find(x) != find(y)) {
                merge(x, y);
            }
        }
        bw.write(String.valueOf(getGroupCount()));
        br.close();
        bw.close();
    }

    private int find(int x) {
        if (parent[x] == 0) {
            parent[x] = x;
            return x;
        }
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void merge(int x, int y) {
        x = find(x);
        y = find(y);
        parent[x] = y;
    }

    private int getGroupCount() {
        int cnt = 0;
        boolean[] existGroup = new boolean[MAX_XY + 1];
        for (int i = 1; i <= MAX_XY; i++) {
            if (parent[i] == 0) {
                continue;
            }
            find(i);
        }
        for (int i = 1; i <= MAX_XY; i++) {
            if (parent[i] == 0 || existGroup[parent[i]]) {
                continue;
            }
            existGroup[parent[i]] = true;
            cnt++;
        }
        return cnt;
    }

}
