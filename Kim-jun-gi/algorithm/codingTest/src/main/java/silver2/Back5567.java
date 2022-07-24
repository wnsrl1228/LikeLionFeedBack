package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 결혼식
public class Back5567 {
    static int[][] map;
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 동기 수
        int M = Integer.parseInt(br.readLine());  // 관계 수
        visited = new boolean[N];
        map = new int[N][N];

        // 이중행렬에 값을 넣어줌
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) -1;
            int v2 = Integer.parseInt(st.nextToken()) -1;
            map[v1][v2] = 1;
            map[v2][v1] = 1;
        }

        // bfs를 사용함
        System.out.println(bfs(0));

    }

    private static int bfs(int current) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(current);
        visited[current] = true;
        int count = 0;
        int result = -1;
        while (!queue.isEmpty()){

            // 큐에 담겨진 친구목록들을 더해줌
            int len = queue.size();
            result+=len;

            // 친구의 친구까지 구했으면 리턴해줌
            if(count == 2){
                return result;
            }
            // 친구의 친구를 한꺼번에 큐에 담아줌
            for (int k = 0; k < len; k++) {
                int v = queue.poll();

                for (int i = 0; i < N; i++) {
                    if(map[v][i] == 1 && !visited[i]){
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            count++;
        }
        // 친구가 없는 경우우
        return 0;
   }
}
