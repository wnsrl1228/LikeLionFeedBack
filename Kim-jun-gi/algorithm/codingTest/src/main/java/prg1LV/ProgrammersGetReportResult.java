package prg1LV;

import java.lang.reflect.Array;
import java.util.*;
//신고 결과 받기
public class ProgrammersGetReportResult {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int[] a = solution(id_list,report,k);
        for (int s : a) {
            System.out.println("s = " + s);
        }
    }
    public static int[] solution(String[] id_list, String[] report, int k) {
        // 인덱스는 유저, 값은 신고처리된 수
        int[] answer = new int[id_list.length];

        HashMap<String,HashSet<String>> reportMap = new HashMap<>();  // 트롤 : [신고자1, 신고자2, 신고자2]
        HashMap<String,Integer> indexMap = new HashMap<>();  // 유저 인덱스 저장

        // 유저 인덱스 넣어주고, map 초기화
        for (int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], new HashSet<>());
            indexMap.put(id_list[i],i);
        }

        // report를 map에 넣어줌
        for (String s : report) {
            String[] str = s.split(" ");
            reportMap.get(str[1]).add(str[0]);
        }

        // 신고자가 k명이 넘을 경우 해당 신고자들의 index에 1씩 더해줌
        for (String s : reportMap.keySet()) {
            if(reportMap.get(s).size() >= k){
                for (String s1 : reportMap.get(s)) {
                    answer[indexMap.get(s1)]++;
                }
            }
        }

        return answer;
    }

}
//  다수의 유저에 신고 가능
// k번 이상 신고당하면 신고한 유저에게 알람감 -> 신고 안 한 유저에게는 안 감

//  신고당한 -> 신고한 , 신고한 , 신고한
//  신고한의 인덱스를 알아야됨
//  answer [0,1,2,3]
