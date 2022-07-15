package prg2LV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// 주차 요금 계산
public class PrgParkingFeeCalc {
    static int minTime;
    static int minTimeFee;
    static int unitTime;
    static int unitFee;

    public static void main(String[] args) {
        String[] a = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT",
                "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN",
                "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] b = {180, 5000, 10, 600};
        int[] c = solution(b,a);
    }

    public static int[] solution(int[] fees, String[] records) {
        int[] answer;

        // 변수 지정
        minTime = fees[0];     // 기본 시간 180
        minTimeFee = fees[1];  // 기본 요금 5000
        unitTime = fees[2];    // 단위 시간  10
        unitFee = fees[3];     // 단위 요금  600

        HashMap<Integer,TimeAndValue> map = new HashMap<>();  //[번호판:<시간:계산값>

        for (String record : records) {
            String[] arr = record.split(" ");
            int time = convertHoursToMinutes(arr[0]);      // 시간
            int licensePlate = Integer.parseInt(arr[1]);   // 번호판
            String accessHistory = arr[2];                 // 출입내역

            if (accessHistory.equals("IN")) {

                // 번호판이 이미 있을 경우 == 한번 주차한 경우 == 기존 요금 지켜야 됨
                if (map.containsKey(licensePlate)){
                    map.get(licensePlate).outCheck = false;
                    map.get(licensePlate).time = time;
                } else {
                    // 번호판이 없는 경우 == 최초
                    // map 에 추가
                    TimeAndValue timeAndValue = new TimeAndValue(time, minTimeFee,0, false);
                    map.put(licensePlate, timeAndValue);
                }

            } else if(accessHistory.equals("OUT")) {
                TimeAndValue timeAndValue = map.get(licensePlate);
                timeCalculator(timeAndValue, time);
            }
        }

        // 출입만 한 경우 계산해줌 and 시간에 대한 요금 계산
        int maxTime = 23*60+59;
        for (Integer licensePlate : map.keySet()) {
            if (map.get(licensePlate).outCheck == false) {
                TimeAndValue timeAndValue = map.get(licensePlate);
                timeCalculator(timeAndValue, maxTime);
            }

            // 총 시간으로 요금 계산
            rateCalculator(map.get(licensePlate));
        }

        // 정렬한 뒤 오름차순으로 번호판의 요금을 꺼내줌
        List<Integer> licensePlates = new ArrayList<>(map.keySet());
        licensePlates.sort(Comparator.naturalOrder());
        answer = new int[licensePlates.size()];
        int i = 0;
        for (Integer licensePlate : licensePlates) {
            answer[i++] = map.get(licensePlate).value;
        }

        return answer;
    }

    // 총 시간 더해주는 메서드
    private static void timeCalculator(TimeAndValue timeAndValue, int time) {
        int pastTime = timeAndValue.time;   // 출입 시간
        int totalTime = timeAndValue.totalTime; // 총 시간

        int parkingTime = time - pastTime; // 주차 시간
        timeAndValue.totalTime = totalTime + parkingTime; // 총 시간 증가
        timeAndValue.outCheck = true;
    }

    // 요금 계산 메서드
    private static void rateCalculator(TimeAndValue timeAndValue) {
        int totalTime = timeAndValue.totalTime; // 총 시간

        // 기본 시간이 넘을 경우
        if (totalTime > minTime) {
            // 기본 요금 + (주차시간-기본시간)/단위시간 * 단위 요금
            timeAndValue.value += Math.ceil((totalTime-minTime)/(float)unitTime) * unitFee;
        }
    }

    // 시간을 분으로 변환해주는 메서드
    private static int convertHoursToMinutes(String s) {
        String[] arr = s.split(":");
        int hour = Integer.parseInt(arr[0]);
        int minute = Integer.parseInt(arr[1]);
        return hour*60+minute;
    }
    static class TimeAndValue {
        int time;       // 입출 시간
        int value;      // 총 요금
        int totalTime;  // 총 시간
        boolean outCheck; // true 나갔다, false 들어온 상태
        TimeAndValue(int time, int value,int totalTime, boolean outCheck){
            this.time = time;
            this.value = value;
            this.totalTime = totalTime;
            this.outCheck = outCheck;
        }
    }
}
