package BAEKJOON.Gold.Gold_I.P2042번_구간_합_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    protected static long[] A;
    protected static long[] BIT;
    protected static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new long[N + 1];
        BIT = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(br.readLine());
            update(i, A[i]);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                long diff = c - A[b];
                A[b] = c;
                update(b, diff);
            } else {
                sb.append(sum((int) c) - sum(b - 1)).append('\n');
            }
        }
        System.out.print(sb.toString());
    }

    public static void update(int i, long diff) {
        while (i <= N) {
            BIT[i] += diff;
            i += (i & -i);
        }
    }

    public static long sum(int i) {
        long ans = 0;
        while (i > 0) {
            ans += BIT[i];
            i -= (i & -i);
        }
        return ans;
    }
}