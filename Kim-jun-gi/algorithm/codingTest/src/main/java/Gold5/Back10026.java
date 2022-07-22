package Gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 적록색약
public class Back10026 {

    static int N;
    static boolean[][] visited;
    static char[][] rgb;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        rgb = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                rgb[i][j] = str.charAt(j);
            }
        }

        int count =0;
        int count1 = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    dfs(i,j);
                    count++;
                }
            }
        }
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    dfs2(i,j);
                    count1++;
                }
            }
        }
        System.out.println(count+" "+count1);
    }
    static void dfs2(int y,int x){
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {

            int nextY = dy[i] + y;
            int nextX = dx[i] + x;

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N){
                continue;
            }
            if (!(rgb[y][x]== 'B')){
                if (!(rgb[nextY][nextX] == 'B') && !visited[nextY][nextX]) {
                    dfs2(nextY,nextX);
                }
            }else {
                if (rgb[nextY][nextX] =='B' && !visited[nextY][nextX]) {
                    dfs2(nextY,nextX);
                }
            }
        }
    }

    static void dfs(int y,int x){
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {

            int nextY = dy[i] + y;
            int nextX = dx[i] + x;

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N){
                continue;
            }
            if (rgb[y][x] == rgb[nextY][nextX] && !visited[nextY][nextX]) {
                dfs(nextY,nextX);
            }
        }
    }
}
