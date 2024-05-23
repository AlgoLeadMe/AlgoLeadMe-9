package Platinum_III.P1605번_반복_부분문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    protected static final int MOD = 100_003;
    protected static String word;
    protected static int[] power;

    public static void main(String[] args) throws IOException {
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        word = br.readLine();

        // 2^0부터 2^(length-1)까지 미리 계산해두기
        power = new int[length + 1];
        power[0] = 1;
        for (int i = 1; i < length; i++)
            power[i] = (power[i - 1] << 1) % MOD;

        // 이분탐색으로 최대길이 찾기
        // mid길이에서 발견 시 길이 더 길게
        // mid길이에서 발견 못하면 길이 더 짧게
        int left = 1;
        int right = length;
        int maxLength = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, word)) {
                maxLength = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 출력
        System.out.println(maxLength);
    }

    public static boolean check(int length, String str) {
        // 해시값이 저장된 index의 위치를 저장할 리스트
        List<Integer>[] table = new ArrayList[MOD];
        for (int i = 0; i < MOD; i++)
            table[i] = new ArrayList<>();

        // index 0 부터 length길이의 초기 hash 생성
        int hash = 0;
        for (int i = 0; i < length; i++)
            hash = ((hash << 1) + str.charAt(i)) % MOD;
        table[hash].add(0);

        // index 1부터 쭉 오른쪽으로 슬라이딩하면서 같은 문자열 찾기
        for (int i = 1; i <= str.length() - length; i++) {
            hash = ((hash + MOD) - (str.charAt(i - 1) * power[length - 1] % MOD)) % MOD; // 음수 방지(+ mod), 첫 문자 빼기
            hash = ((hash << 1) + str.charAt(i + length - 1)) % MOD; // hash에 2를 곱해서 오른쪽으로 이동, 마지막 문자 더하기

            // 해시 중복이 있을때 그 문자열이 같은 문자열인지 확인
            boolean isDuplicate = false;
            for (int idx : table[hash]) {
                if (compare(i, idx, length)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate)
                return true;
            table[hash].add(i);
        }
        return false;
    }

    // subString 후 equals 비교하면 메모리 초과나서 charAt으로 비교
    private static boolean compare(int start1, int start2, int length) {
        for (int i = 0; i < length; i++) {
            if (word.charAt(start1 + i) != word.charAt(start2 + i)) {
                return false;
            }
        }
        return true;
    }
}