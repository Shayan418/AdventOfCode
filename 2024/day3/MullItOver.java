package day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MullItOver {

    long partOne() throws IOException {
        String fileName = "2024/day3/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        long ans = 0;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length() - 3; i++) {
                if (line.startsWith("mul(", i)){
                    ans += parse(line, i + 3);
                    i += 2;
                }
            }
        }
        return ans;
    }

    public long parse(String input, int idx) {
        if(input.charAt(idx) != '(') {
            return 0;
        }
        ++idx;

        if(idx == input.length() || (input.charAt(idx) < '0' && input.charAt(idx) > '9')) {
            return 0;
        }

        int first = 0;
        int second = 0;

        int temp = idx+1;
        while (temp < input.length() && input.charAt(temp) >= '0' && input.charAt(temp) <= '9'){
            ++temp;
        }
        first = Integer.parseInt(input.substring(idx, temp));
        idx = temp;
        if(idx == input.length() || input.charAt(idx) != ',') {
            return 0;
        }

        ++idx;

        if(idx == input.length() || (input.charAt(idx) < '0' && input.charAt(idx) > '9')) {
            return 0;
        }

        temp = idx+1;
        while (temp < input.length() && input.charAt(temp) >= '0' && input.charAt(temp) <= '9'){
            ++temp;
        }
        second = Integer.parseInt(input.substring(idx, temp));
        idx = temp;

        if(idx < input.length() && input.charAt(idx) == ')'){
            return (long) first * second;
        }

        return 0;
    }

    long partTwo() throws IOException {
        String fileName = "2024/day3/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        long ans = 0;
        boolean enabled = true;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length() - 3; i++) {
                if (line.startsWith("do()", i)) {
                    enabled = true;
                } else if (line.startsWith("don't()", i)) {
                    enabled = false;
                }
                if (enabled && line.startsWith("mul(", i)){
                    ans += parse(line, i + 3);
                    i += 2;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        MullItOver mullItOver = new MullItOver();
        System.out.println(mullItOver.partOne());
        System.out.println(mullItOver.partTwo());
    }
}
