package Platinum_V.P2150번_Strongly_Connected_Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    protected static ArrayList<Integer>[] graph;
    protected static ArrayList<Integer>[] reversedGraph;
    protected static ArrayList<Integer>[] sccGraph;
    protected static Deque<Integer> stack;
    protected static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];
        reversedGraph = new ArrayList[V + 1];
        sccGraph = new ArrayList[V + 1];
        stack = new ArrayDeque<>();
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            reversedGraph[i] = new ArrayList<>();
            sccGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            reversedGraph[v2].add(v1);
        }

        // 정상 그래프에서 DFS
        for (int i = 1; i <= V; i++)
            if (!visited[i])
                DFS(i);

        // 역방향 그래프에서 DFS
        visited = new boolean[V + 1];
        int sccCount = 0;
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                reverseDFS(vertex, ++sccCount);
            }
        }
        sb.append(sccCount).append('\n');

        // 정렬
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        for (int i = 1; i <= sccCount; i++) {
            sccGraph[i].sort((o1, o2) -> o1 - o2);
            map.put(sccGraph[i].get(0), sccGraph[i]);
        }
        map.entrySet().iterator().forEachRemaining(e -> {
            e.getValue().forEach(v -> sb.append(v).append(' '));
            sb.append(-1).append('\n');
        });

        // 출력
        System.out.print(sb.toString());
    }

    protected static void DFS(int currentVertex) {
        visited[currentVertex] = true;
        for (int nextVertex : graph[currentVertex])
            if (!visited[nextVertex])
                DFS(nextVertex);
        stack.push(currentVertex);
    }

    protected static void reverseDFS(int currentVertex, int sccCount) {
        visited[currentVertex] = true;
        for (int nextVertex : reversedGraph[currentVertex])
            if (!visited[nextVertex])
                reverseDFS(nextVertex, sccCount);
        sccGraph[sccCount].add(currentVertex);
    }
}