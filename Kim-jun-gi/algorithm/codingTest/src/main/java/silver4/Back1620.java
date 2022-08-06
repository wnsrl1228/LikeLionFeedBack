package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 나는야 포켓몬 마스터 이다솜
public class Back1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String,Integer> poket = new HashMap<>();
        String[] arr = new String[N+1];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            poket.put(s,i+1);
            arr[i+1] = s;
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            // 숫자인 경우
            if(str.chars().allMatch( Character::isDigit )){
                System.out.println(arr[Integer.parseInt(str)]);
            } else {
                System.out.println(poket.get(str));
            }
        }
    }
}
