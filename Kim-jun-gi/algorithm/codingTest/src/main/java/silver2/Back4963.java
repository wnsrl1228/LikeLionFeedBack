package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 섬의 개수
public class Back4963 {

    /*  dx, dy
        ------------------------------
        |  -1, -1 |  0, -1 |  1, -1  |
        |  -1, 0  |  0, 0  |  1, 0   |
        |  -1, 1  |  0, 1  |  1, 1   |
        ------------------------------
    */
    static int[][] land;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,-1,1,-1,0,1};
    static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int w, h;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 : 0,0 이 나올 때까지 반복
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());  // 너비
            h = Integer.parseInt(st.nextToken());  // 높이

            // 종료
            if(w == 0 || h == 0) return;

            land = new int[h][w];                  // 섬 배열
            visited = new boolean[h][w];           // 방문여부 배열

            // 섬 데이터 받아서 배열에 넣어주기
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    land[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println(findLand());
        }
    }

    public static int findLand(){
        // 좌표 0,0 부터 차례대로 돌면서 섬인 경우를 체크한다.
        // 만약 섬이고, 해당 섬을 방문한 적이 없으면 dfs를 실행한다.
        // dfs가 끝나면 인접한 섬을 다 지나온 것이므로 count++ 해준다.
        int count =0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(land[i][j] == 1 && !visited[i][j]){
                    dfs(i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int y, int x) {
        // 섬에 근접한 섬들들 모두 방문처리하고 종료한다.

        visited[y][x] = true; // 해당 섬 방문처리

        // 근접 좌표를 돌아준다.
        for (int i = 0; i < dx.length; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            // 좌표의 범위 체크
            if (nextY < 0 || nextY >= h || nextX < 0 || nextX >= w) {
                continue;
            }

            // 인접한 좌표가 섬이고 방문한 적이 없는 경우
            if(land[nextY][nextX] == 1 && !visited[nextY][nextX]){
                dfs(nextY, nextX);
            }
        }
    }
}
/*  예시
    1 0 1 0 0
    1 0 0 0 0
    1 0 1 0 1
    1 0 0 1 0
*/
