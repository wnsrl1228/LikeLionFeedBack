package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 트리의 부모 찾기
public class Back11725 {
    static ArrayList<ArrayList<Integer>> adj;
    static int N;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 정점
        result = new int[N+1];
        adj = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }



        StringBuilder sb = new StringBuilder();
        bfs(1);

        for (int i = 2; i <= N ; i++) {
            sb.append(result[i]+"\n");
//            System.out.println(result[i]);
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

    private static void bfs(int current) {
        boolean[] visited = new boolean[N+1];
        visited[current] = true;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(current);

        while(!queue.isEmpty()){
            current = queue.poll();

            ArrayList<Integer> arr = adj.get(current);

            for (Integer integer : arr) {
                if(!visited[integer]){
                    result[integer] = current;
                    queue.add(integer);
                    visited[integer] = true;
                }
            }
        }
    }

}

