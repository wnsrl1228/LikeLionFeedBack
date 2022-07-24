package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연결 요소의 개수
public class Back11724 {
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 정점 개수
        int N = Integer.parseInt(st.nextToken()); // 간선 개수
        p = new int[M+1];
        for (int i = 1; i <= M; i++) {
            p[i] = i;
        }
        int count = M;
        while (N-- > 0){
            st = new StringTokenizer(br.readLine());
            if(union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) M--;
        }
        System.out.println(M);

    }

    private static boolean union(int v1, int v2) {
        int a = find(v1);
        int b = find(v2);
        if(a==b) return false; // 부모가 같은 경우

        p[a] = b;
        return true;
    }

    private static int find(int v) {
        if(p[v] == v) return v;
        return p[v] = find(p[v]);
    }
}

// ===인접 행렬을 쓴 경우===
//package silver2;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//// 연결 요소의 개수
//public class Back11724 {
//    static int N;
//    static int M;
//    static int[][] map;
//    static boolean[] visited;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        M = Integer.parseInt(st.nextToken()); // 정점 개수
//        N = Integer.parseInt(st.nextToken()); // 간선 개수
//        map = new int[M][M];
//        visited = new boolean[M];
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int v1 = Integer.parseInt(st.nextToken())-1;
//            int v2 = Integer.parseInt(st.nextToken())-1;
//            map[v1][v2] = 1;
//            map[v2][v1] = 1;
//        }
//
//        int count = 0;
//        for (int i = 0; i < M; i++) {
//            if(!visited[i]){
//                dfs(i);
//                count++;
//            }
//        }
//        System.out.println(count);
//
//    }
//
//    private static void dfs(int current) {
//        if(visited[current]) return;
//        visited[current] = true;
//
//        for (int i = 0; i < M; i++) {
//            if(map[current][i] != 0){
//                dfs(i);
//            }
//        }
//    }
//}
//

//   ===인접 리스트를 써서 푼 경우===
//package silver2;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//// 연결 요소의 개수
//public class Back11724 {
//    static int N;
//    static int M;
//    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//    static boolean[] visited;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        M = Integer.parseInt(st.nextToken()); // 정점 개수
//        N = Integer.parseInt(st.nextToken()); // 간선 개수
//        visited = new boolean[M+1];
//
//        for (int i = 0; i <= M; i++) {
//            adj.add(new ArrayList<>());
//        }
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int v1 = Integer.parseInt(st.nextToken());
//            int v2 = Integer.parseInt(st.nextToken());
//
//            adj.get(v1).add(v2);
//            adj.get(v2).add(v1);
//        }
//        int count = 0;
//        for (int i = 1; i <= M; i++) {
//            if(visited[i] == false){
//                dfs(i);
//                count++;
//            }
//        }
//        System.out.println(count);
//    }
//
//    private static void dfs(int current) {
//        visited[current] = true;
//        ArrayList<Integer> list = adj.get(current);
//
//        for (Integer v : list) {
//            if(visited[v] == false){
//                dfs(v);
//            }
//        }
//    }
//}
