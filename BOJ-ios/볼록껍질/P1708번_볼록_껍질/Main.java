package BAEKJOON.Platinum.Platinum_V.P1708번_볼록_껍질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
    public static class Point implements Comparable<Point> {
        long x;
        long y;
        static Point p0;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public static int ccw(Point p, Point q, Point r) {
            long val = (q.y - p.y) * (r.x - q.x) -
                    (q.x - p.x) * (r.y - q.y);
            if (val == 0)
                return 0;
            return (val > 0) ? 1 : 2;
        }

        public static void swap(Point p1, Point p2) {
            long tempX = p1.x;
            long tempY = p1.y;
            p1.x = p2.x;
            p1.y = p2.y;
            p2.x = tempX;
            p2.y = tempY;
        }

        public static long distSq(Point p1, Point p2) {
            return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        }

        public int compareTo(Point p) {
            int o = ccw(p0, this, p);
            if (o == 0)
                return (distSq(p0, p) >= distSq(p0, this)) ? -1 : 1;
            return (o == 2) ? -1 : 1;
        }
    }

    protected static int n;
    protected static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        points = new Point[n];
        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        convexHull();
    }

    public static void convexHull() {
        long minX = points[0].x;
        long minY = points[0].y;
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            long x = points[i].x;
            long y = points[i].y;
            if (x < minX || (x == minX && y < minY)) {
                minX = x;
                minY = y;
                minIndex = i;
            }
        }
        Point.swap(points[0], points[minIndex]);
        Point.p0 = points[0];
        Arrays.sort(points, 1, n);
        int resize = 1;
        for (int i = 1; i < n; i++) {
            while ((i < n - 1) &&
                    Point.ccw(Point.p0, points[i], points[i + 1]) == 0)
                i++;
            points[resize++] = points[i];
        }

        Deque<Point> stack = new ArrayDeque<>();
        stack.addLast(points[0]);
        stack.addLast(points[1]);
        stack.addLast(points[2]);
        for (int i = 3; i < resize; i++) {
            while ((stack.size() > 1) &&
                    Point.ccw(underTheTop(stack), stack.peekLast(), points[i]) != 2)
                stack.pollLast();
            stack.addLast(points[i]);
        }
        System.out.println(stack.size());
    }

    public static Point underTheTop(Deque<Point> stack) {
        Point p = stack.pollLast();
        Point result = stack.peekLast();
        stack.addLast(p);
        return result;
    }
}