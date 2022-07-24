package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유기농 배추
public class Back1012 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int M;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        while (T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken()); // 세로
            int K = Integer.parseInt(st.nextToken()); // 배추 개수

            map = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 1){
                        dfs(i,j);
                        count++;
                    }
                }
            }
            System.out.println(count);

        }



    }

    private static void dfs(int y, int x) {
        map[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M){
                continue;
            }

            if(map[nextY][nextX] == 1){
                dfs(nextY, nextX);
            }
        }
    }
}
