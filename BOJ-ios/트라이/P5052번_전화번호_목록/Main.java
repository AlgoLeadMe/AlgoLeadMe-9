package BAEKJOON.Gold.Gold_IV.P5052번_전화번호_목록;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static class Contact {
        Contact[] contacts;
        boolean exists;

        Contact() {
            contacts = new Contact[10];
        }

        public boolean addContacts(String phoneNumber) {
            int digit = phoneNumber.charAt(0) - '0';
            if (this.contacts[digit] == null) {
                this.contacts[digit] = new Contact();
            } else if (this.contacts[digit].exists) {
                return false;
            }
            if (phoneNumber.length() == 1) {
                this.contacts[digit].exists = true;
            } else {
                return this.contacts[digit].addContacts(phoneNumber.substring(1, phoneNumber.length()));
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            Contact root = new Contact();
            int N = Integer.parseInt(br.readLine());
            String[] numbers = new String[N];
            for (int j = 0; j < N; j++)
                numbers[j] = br.readLine();
            Arrays.sort(numbers);
            boolean able = true;
            for (int j = 0; j < N; j++) {
                if (!root.addContacts(numbers[j])) {
                    able = false;
                    break;
                }
            }
            sb.append(able ? "YES" : "NO").append('\n');
        }
        System.out.print(sb.toString());
    }
}