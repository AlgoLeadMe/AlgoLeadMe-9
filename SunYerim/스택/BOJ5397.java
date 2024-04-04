// 두 개의 스택 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            String input = br.readLine();
            Stack<Character> lStack = new Stack<>();
            Stack<Character> rStack = new Stack<>();

            for (char ch : input.toCharArray()) {
                switch (ch) {
                    case '<':
                        if (!lStack.isEmpty()) {
                            rStack.push(lStack.pop());
                        }
                        break;
                    case '>':
                        if (!rStack.isEmpty()) {
                            lStack.push(rStack.pop());
                        }
                        break;
                    case '-':
                        // empty 처리 반례 ABC<<<--
                        if (!lStack.isEmpty())
                            lStack.pop();
                        else
                            continue;
                        break;
                    default:
                        lStack.push(ch);
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();

            // lStack의 내용을 StringBuilder에 추가
            for(Character ch : lStack) {
                sb.append(ch);
            }

            // rStack의 내용을 올바른 순서로 추가하기 위해 뒤집음
            while (!rStack.isEmpty()) {
                sb.append(rStack.pop());
            }

            System.out.println(sb.toString());

        }


    }
}