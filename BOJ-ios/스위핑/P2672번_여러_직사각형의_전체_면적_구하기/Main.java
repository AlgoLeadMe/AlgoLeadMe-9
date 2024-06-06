import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static class RectangleEvent implements Comparable<RectangleEvent> {
        int x;
        int yUp;
        int yDown;
        int chk;

        public RectangleEvent(int x, int yDown, int yUp, int chk) {
            this.x = x;
            this.yUp = yUp;
            this.yDown = yDown;
            this.chk = chk;
        }

        @Override
        public int compareTo(RectangleEvent o) {
            if (this.x == o.x)
                return this.chk - o.chk;
            return this.x - o.x;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RectangleEvent that = (RectangleEvent) obj;
            return this.x == that.x && this.yUp == that.yUp && this.yDown == that.yDown && this.chk == that.chk;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, yUp, yDown, chk);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        List<RectangleEvent> events = new ArrayList<>();
        int[] sweep = new int[20001];
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int xStart = (int) (Double.parseDouble(st.nextToken()) * 10);
            int yDown = (int) (Double.parseDouble(st.nextToken()) * 10);
            int xEnd = xStart + (int) (Double.parseDouble(st.nextToken()) * 10);
            int height = (int) (Double.parseDouble(st.nextToken()) * 10);
            int yUp = yDown + height;
            events.add(new RectangleEvent(xStart, yDown, yUp, 1));
            events.add(new RectangleEvent(xEnd, yDown, yUp, -1));
        }
        Collections.sort(events);
        int area = 0;
        int x = 0;
        for (RectangleEvent event : events) {
            int count = 0;
            for (int i = 0; i < 20001; i++)
                if (sweep[i] > 0)
                    count++;
            area += count * (event.x - x);
            for (int i = event.yDown; i < event.yUp; i++) {
                if (event.chk == 1)
                    sweep[i]++;
                else
                    sweep[i]--;
            }
            x = event.x;
        }
        System.out.println(area % 100 == 0 ? (int) (area * 0.01) : String.format("%.2f", area * 0.01));
    }
}
