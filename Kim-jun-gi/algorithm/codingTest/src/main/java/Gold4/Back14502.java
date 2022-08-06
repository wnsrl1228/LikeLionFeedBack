package Gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 연구소
public class Back14502 {
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static boolean[][] visited;

    static int N;
    static int M;
    static int maxCount = 0; // 결과값 - 최대 안전구역
    static int count;        // 임시 안전구역

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 세로
        M = Integer.parseInt(st.nextToken());  // 가로
        map = new int[N][M];                   // 지도

        // 지도의 값을 입력받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /* 부르트포스 방법
        1. 지도에 3개의 벽이 생길 수 있는 모든 경우의 수를 구해줌 (조합)
        ex) 1 1 1  1 1 0  1 1 0  1 1 0  1 1 0  1 1 0  1 1 0  1 0 1
            0 0 0  1 0 0  0 1 0  0 0 1  0 0 0  0 0 0  0 0 0  1 0 0
            0 0 0  0 0 0  0 0 0  0 0 0  1 0 0  0 1 0  0 0 1  0 0 0

        2. 벽이 3개 생길 때 마다 감염에 의한 안전구역을 체크해줌
        3. 이를 반복하여 최대 안전구역을 구해준다.
        */

        // 3개의 벽을 설치한다.
        backtracking(0,0,0);

        //최대의 안전 구역 출력
        System.out.println(maxCount);

    }

    public static void backtracking(int y, int x, int w) {

        // 벽이 3개가 전부 설치된 경우
        if(w == 3){

            // 이제 지도에서 2를 찾아서 dfs 돌아 감염구역으로 만들어준다.
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 2){
                        dfs(i,j);
                    }
                }
            }

            // 안전구역을 모두 체크해줌
            safeZoneCounting();

            // 기존의 안전 구역보다 많으면 값을 갱신시켜준다.
            if(count > maxCount){
                maxCount = count;
            }

            return;
        }

        // 열이 map을 초과한 경우 행을 하나 올려줌
        if(x == M){
            y = y +1;
            x = 0;
        }

        // 벽을 3개씩 세우는 작업 , 2차원 배열의 조합 , 백트래킹
        for (int i = y; i < N; i++) {
            for (int j = x; j < M; j++) {
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    backtracking(i,j+1,w+1);
                    map[i][j] = 0;
                }

                // 행이 올라갈 때마다 열을 0으로 변경시켜줘야 된다.
                if(j == M-1){
                    x = 0;
                }
            }
        }


    }

    // 바이러스를 퍼트리는 작업
    public static void dfs(int y, int x) {

        // 감염구역을 체크해준다
        visited[y][x] = true;

        // 근접 좌표를 돌아준다.
        for (int i = 0; i < dx.length; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            // 좌표의 범위 체크
            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
                continue;
            }

            // 0인 구역이면서 방문한 적이 없는 구역일 경우
            if(map[nextY][nextX] == 0 && !visited[nextY][nextX]){
                dfs(nextY, nextX);
            }
        }
    }

    // 안전 구역 체크
    public static void safeZoneCounting(){
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 방문하지 않았고 0인 경우 안전구역이므로 count++을 해준다.
                if(!visited[i][j] && map[i][j] == 0){
                    count++;
                }
            }
        }
    }
}