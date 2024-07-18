package BAEKJOON.Silver.Silver_I.P11277번_2_SAT_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    protected static int N;
    protected static int M;
    protected static boolean[] booleanState;
    protected static boolean isfTrue = false;
    protected static List<int[]> clauses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        booleanState = new boolean[N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            clauses.add(new int[] { i, j });
        }
        dfs(1);
        System.out.println(isfTrue ? 1 : 0);
    }

    public static void dfs(int depth) {
        // 각 변수 x1 ~ xn
        // (false false false ...) ~ (true true true ...)
        if (isfTrue) {
            return;
        }
        if (depth == N + 1) {
            isfTrue = checkClauses();
            return;
        }
        booleanState[depth] = false;
        dfs(depth + 1);
        booleanState[depth] = true;
        dfs(depth + 1);
    }

    public static boolean checkClauses() {
        for (int[] clause : clauses) {
            boolean l = (clause[0] > 0) ? booleanState[clause[0]] : !booleanState[-clause[0]];
            boolean r = (clause[1] > 0) ? booleanState[clause[1]] : !booleanState[-clause[1]];
            // 절 하나가 false이면 해당 식은 false
            if (!(l || r)) {
                return false;
            }
        }
        return true;
    }
}
