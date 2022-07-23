package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// DFS아 BFS
public class Back1260 {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static StringBuffer sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점
        int M = Integer.parseInt(st.nextToken()); // 간선
        int V = Integer.parseInt(st.nextToken()); // 시작점

        adj = new ArrayList<>();


        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }
        for (ArrayList<Integer> integers : adj) {
            Collections.sort(integers);
        }

        visited = new boolean[N+1];
        sb = new StringBuffer();
        dfs(V);
        visited = new boolean[N+1];
        sb.append("\n");
        bfs(V);
        System.out.println(sb);

    }
    private static void dfs(int current) {
        visited[current] = true;
        sb.append(current+" ");
        ArrayList<Integer> arr = adj.get(current);

        for (Integer v : arr) {
            if(!visited[v]){
                dfs(v);
            }
        }

    }
    private static void bfs(int current) {
        visited[current] = true;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(current);

        while(!queue.isEmpty()){
            current = queue.poll();
            sb.append(current+" ");

            ArrayList<Integer> arr = adj.get(current);

            for (Integer integer : arr) {
                if(!visited[integer]){
                    queue.add(integer);
                    visited[integer] = true;
                }
            }
        }
    }


}
