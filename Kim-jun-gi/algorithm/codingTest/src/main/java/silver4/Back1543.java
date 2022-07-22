package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//문서 검색
public class Back1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();

        int count = 0;     //문서에 있는 문자 개수
        int lastIndex = 0; // 마지막 인덱스

        // 문서에 해당 문자가 있을 경우 인덱스를 구함
        // 해당 인덱스에 문자의 길이를 더한 뒤 다시 인덱스를 구함
        do {
            int startIndex =  doc.indexOf(word,lastIndex);
            if (startIndex == -1) break;
            count++;
            lastIndex = startIndex + word.length();
        } while(lastIndex < doc.length());

        System.out.println(count);
    }
}
