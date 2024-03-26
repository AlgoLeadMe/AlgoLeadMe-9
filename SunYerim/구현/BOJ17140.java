import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17140 {
    static int r, c, k;
    static int xLength = 3, yLength = 3;
    static int[][] map2 = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                map2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {
        for (int time = 0; time <= 100; time++) {
            if (map2[r][c] == k) {
                return time;
            }
            calculate();
        }
        return -1;

    }

    private static void calculate() {
        // 여기서 R연산과 C연산 기준을 나눈다.
        if (xLength >= yLength) {
            for (int i = 1; i <= xLength; i++) {
                calculateR(i);
            }
        } else {
            for (int i = 1; i <= yLength; i++) {
                calculateC(i);
            }
        }
    }


    // R연산
    private static void calculateR(int num) {
        // 각 숫자들이 몇 번 나왔는지 갯수를 구해서 map에 담았다가 priorityqueue로 옮겨 정렬 기준에 맞춰 정렬
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>(); // <number, count>

        // 행
        for (int i = 1; i <= yLength; i++) {
            if (map2[num][i] == 0) continue; // 0이면 무시
            map.compute(map2[num][i], (number, count) -> count == null ? 1 : count+1);
        }
        map.forEach((k, v) -> queue.add(new Node(k, v)));

        int i = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            map2[num][i++] = node.number;
            map2[num][i++] = node.count;
        }

        yLength = Math.max(yLength, i);

        // 돌려놓기
        while (i <= 99) {
            map2[num][i++] = 0;
            map2[num][i++] = 0;
        }

    }

    private static void calculateC(int num) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>(); // <number, count>

        for (int i = 1; i <= yLength; i++) {
            if (map2[i][num] == 0) continue; // 0이면 무시
            map.compute(map2[i][num], (number, count) -> count == null ? 1 : count+1);
        }

        map.forEach((k, v) -> queue.add(new Node(k, v)));

        int i = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            map2[i++][num] = node.number;
            map2[i++][num] = node.count;
        }

        xLength = Math.max(xLength, i);

        // 돌려놓기
        while (i <= 99) {
            map2[i++][num] = 0;
            map2[i++][num] = 0;
        }
    }


    static class Node implements Comparable<Node>{
        private int number, count;
        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            // 커지는 순으로 정렬
            if (this.count == o.count) {
                return this.number - o.number;
            }
            return this.count - o.count;
        }
    }
}
