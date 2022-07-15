package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

// 스택 수열
public class Back1874 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();

        int currentValue = 1; // [1,2,3,4,5 ...]
        while(N-- > 0) {
            int num = Integer.parseInt(br.readLine()); // [4,3,6 ... ]
            // 4인데 1인 경우, 1,2,3,4 스택에 추가
            if (currentValue <= num) {
                while (currentValue <= num) {
                    stack.push(currentValue++);
                    sb.append("+").append("\n");
                }
                stack.pop();
            } else {
                // 1인데 4인 경우
                if(stack.isEmpty()) {
                    System.out.println("NO");
                    return;
                }
                int a = stack.pop();
                if (a != num ) {
                    System.out.println("NO");
                    return;
                }
            }
            sb.append("-").append("\n");
            // 3인데 5인 경우
        }
        System.out.println(sb);
    }

}
