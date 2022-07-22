package prg3LV;


public class Network {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n,computers));
    }
    static int[][] com;

    static int N;
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        com = computers;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(computers[i][j] == 1){
                    dfs(i,j);
                    answer++;
                }
            }
        }
        return answer;
    }

    private static void dfs(int y, int x) {
        com[y][x] = 0;
        com[x][y] = 0;

        for (int i = 0; i < N; i++) {
            if (com[y][i] == 1){
                dfs(y,i);
            }
            if(com[x][i] == 1){
                dfs(x,i);
            }
        }
    }
}
