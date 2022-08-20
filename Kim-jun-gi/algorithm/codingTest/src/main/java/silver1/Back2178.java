package silver1;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Back2178 {

    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        map = new int[N][M];

        // map 배열에 추가
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++) {
                map[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }

        bfs(0,0);
        System.out.println(map[N-1][M-1]);

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println(" ");
        }
    }

    /**
     * 1 1 1 1 1 1 1
     * 1 0 0 0 0 0 1
     * 1 0 1 1 1 0 1
     * 1 0 1 0 1 0 1
     * 1 1 1 0 1 1 1
     *
     *      * 1 2 3 4  5 6 7
     *      * 2 0 0 0  0 0 8
     *      * 3 0 9 10 11 0 9
     *      * 4 0 8 0  1  0 10
     *      * 5 6 7 0  1  1 11
     *
     */

    public static void bfs(int y, int x) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x,y));

        while (!queue.isEmpty() && map[N-1][M-1] == 1){
            Point point = queue.poll();
            int xx = point.x;
            int yy = point.y;
            for (int i = 0; i < 4; i++) {
                int nextY = dy[i] + yy;
                int nextX = dx[i] + xx;

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
                    continue;
                }
                if (map[nextY][nextX] == 1) {
                    queue.add(new Point(nextX,nextY));
                    map[nextY][nextX] = map[yy][xx] + 1;
                }
            }
        }
    }
}

