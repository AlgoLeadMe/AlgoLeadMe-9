package BAEKJOON.Gold.Gold_III.P2252번_줄_세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    protected static int[] inDegree;
    protected static LinkedList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        graph = new LinkedList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            inDegree[B]++;
        }
        System.out.println(topologySort(N));
    }

    public static String topologySort(int N) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(' ');
            for (int next : graph[now]) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return sb.toString();
    }
}
