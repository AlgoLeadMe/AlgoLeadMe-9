import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
    static int r, c;
    static int startX, startY, endX, endY;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] waterVisited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayDeque<Node> water;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        waterVisited = new boolean[r][c];
        water = new ArrayDeque<Node>();

        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (map[i][j] == 'D') {
                    endX = i;
                    endY = j;
                } else if (map[i][j] == '*') {
                    water.add(new Node(i, j, 0));
                }
            }
        }

        bfs(startX, startY);
    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>(); // 두더지가 가는 경로만 담는다
        queue.add(new Node(x, y, 0)); // 출발지점 넣고
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            // 물을 퍼뜨린 후 고슴도치 이동 -> 고슴도치도 물은 통과 못한다.
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Node curr = queue.poll();
                int currX = curr.x;
                int currY = curr.y;
                int currCnt = curr.count;
                if (map[currX][currY] == '*')
                    continue;

                // 기저
                if (map[currX][currY] == 'D') {
                    System.out.println(currCnt);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currX;
                    int ny = dy[i] + currY;
                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[nx][ny]) {
                        // 고슴도치가 가려는 자리에 물이나 돌이 있으면
                        if (map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
                        visited[nx][ny] = true;

                        queue.add(new Node(nx, ny, currCnt+1));

                    }
                }

            }
            // 우선 물부터 퍼뜨린다. -> 물은 돌과 소굴이 있는 자리, D로는 퍼질 수 없다.
            int waterSize = water.size();
            for (int i = 0; i < waterSize; i++) {
                Node waters = water.poll();
                int waterX = waters.x;
                int waterY = waters.y;
                //int waterCnt = waters.count;
                // 가려는 곳이 돌이 아니고 비버의 굴이 아닐때
                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + waterX;
                    int ny = dy[j] + waterY;
                    if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                        // 돌이아니고 굴이 아니여야함
                        if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || waterVisited[nx][ny]) continue;
                        // 갈 수 있는 곳이라면

                        map[nx][ny] = '*';
                        waterVisited[nx][ny] = true;
                        water.add(new Node(nx, ny, 0));


                    }
                }
            }
        }

        System.out.println("KAKTUS");




    }

    static class Node {
        private int x, y, count;
        public Node (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}