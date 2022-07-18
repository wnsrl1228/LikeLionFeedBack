package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 촌수계산
public class Back2644 {

    static int[][] family;
    static int person1;
    static int person2;
    static int N;
    static int result = -1;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        family = new int[N+1][N+1];
        visited = new boolean[N+1];

        String[] arr = br.readLine().split(" ");
        person1 = Integer.parseInt(arr[0]);
        person2 = Integer.parseInt(arr[1]);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parents = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            family[parents][child] = 1;
        }

        dfs(person1,0);
        System.out.println(result);

    }
    static void dfs(int current, int score) {

        visited[current] = true;

        if(current == person2) {;
            result = score;
            return;
        }

        for (int i = 1; i < N+1; i++) {
            // p1의 부모
            if(family[i][current] == 1){
                if(!visited[i]){
                    dfs(i,score+1);
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            // p1의 자식을 찿는 경우
            if(family[current][i] == 1){
                if(!visited[i]){
                    dfs(i,score+1);
                }
            }
        }

    }
}
