package prg3LV;

import java.util.ArrayList;

public class ChuseokTraffic {

    public static void main(String[] args) {
        String[] a = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] b = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(a));
        System.out.println(solution(b));

    }
    public static int solution(String[] lines) {

        float[] reqTime = new float[lines.length];
        float[] proTime = new float[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] temp = lines[i].split(" ");

            String[] time = temp[1].split(":");
            reqTime[i] = secondCalc(time[0],time[1]) + Float.parseFloat(time[2]);   // 요청시간 초로 변환

            proTime[i] = Float.parseFloat(temp[2].substring(0,temp[2].length()-1)); // s 제거와 float로 형변환
        }

        ArrayList<Float> point = new ArrayList<>(); // 요청시간 시작 지점과 끝 지점을 포인트로 잡음
        for (int i = 0; i < reqTime.length; i++) {

            point.add(reqTime[i]-proTime[i]+0.001f); // 시작 시점
            point.add(reqTime[i]);            // 종료 지점
        }

        int maxCount = 0;

        for (int i = 0; i < point.size(); i++) {
            int tempCount = 0;

            float start = point.get(i); //요청시간
            float end = start + 1;      // 요청시간 + 1초 의 범위

            // 1초의 범위 안에 요청 개수 찾음
            for (int j = 0; j < point.size(); j+=2) {
                float pointStart = point.get(j);
                float pointEnd = point.get(j+1);

//                System.out.println("start : end = " + start +" : " +end);
//                System.out.println("pointStart : pointEnd = " + pointStart +" : " +pointEnd);

                // 요청이 1초 범위 안에 드는 경우의 수 3가지
                //  PointStart <= 요청시작지점 < PointEnd
                // PointStart <= 요청 끝지점 < PointEnd
                // 요청시작지점 < PointStart and  PointEnd < 요청끝지점
                if((start <= pointStart && pointStart < end) ||
                        (start <= pointEnd && pointEnd < end) ||
                        (start > pointStart && pointEnd > end)){
                    tempCount++;
                }

            }

            if(maxCount < tempCount){
                maxCount = tempCount;
            }

        }
        return maxCount;
    }

    // 시랑 분을 초로 변환해줌
    public static int secondCalc(String hour, String minute){
        return Integer.parseInt(hour)*60*60 + Integer.parseInt(minute)*60;
    }
}
