package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 바이러스
public class Back2606 {
    static ArrayList<ArrayList<Integer>> adj;
    static int count = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 정점
        int M = Integer.parseInt(br.readLine()); // 정점

        adj = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        dfs(1);
        System.out.println(count-1);

    }
    private static void dfs(int current) {
        visited[current] = true;
        count++;
        ArrayList<Integer> arr = adj.get(current);

        for (Integer v : arr) {
            if(!visited[v]){
                dfs(v);
            }
        }

    }

}
