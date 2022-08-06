package silver4;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// 비밀번호 찿기
public class Back17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String,String> siteInfo = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            siteInfo.put(st.nextToken(), st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            bw.write(siteInfo.get(site));
            bw.newLine();
        }
        bw.flush();
    }
}
