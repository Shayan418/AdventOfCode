package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GearRatios {

    public int numberExtractor(StringBuilder sb, int idx) {
        while (idx > 0 && Character.isDigit(sb.charAt(idx - 1))) {
            --idx;
        }

        int temp = 0;

        while (idx < sb.length() && Character.isDigit(sb.charAt(idx))) {
            temp = (temp * 10) + sb.charAt(idx) - '0';
            sb.setCharAt(idx, '.');
            ++idx;
        }

        return temp;
    }

    public int partone() throws IOException {
        int ans = 0;
        String fileName = System.getProperty("user.dir") + "/2023/day3/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";

        StringBuilder[] data = new StringBuilder[140];
        int idx = 0;

        while ((line = br.readLine()) != null) {
            data[idx] = new StringBuilder(line);
            ++idx;
        }

        int[] x = new int[] { 1, -1, 0, 0, 1, 1, -1, -1 };
        int[] y = new int[] { 0, 0, 1, -1, -1, 1, -1, 1 };

        int m = data.length;
        int n = data[0].length();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (data[i].charAt(j) != '.' && !Character.isDigit(data[i].charAt(j))) {
                    for (idx = 0; idx < 8; ++idx) {
                        int newx = i + x[idx];
                        int newy = j + y[idx];
                        if (newx >= 0 && newx < m && newy >= 0 && newy < n) {
                            // check number present
                            if (Character.isDigit(data[newx].charAt(newy))) {
                                // System.out.println(newx + " " + newy);
                                // System.out.println(numberExtractor(data[newx], newy));
                                ans += numberExtractor(data[newx], newy);
                            }
                        }
                    }
                }
            }
        }

        br.close();
        return ans;
    }

    public int parttwo() throws IOException {
        int ans = 0;
        String fileName = System.getProperty("user.dir") + "/2023/day3/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";

        StringBuilder[] data = new StringBuilder[140];
        int idx = 0;

        while ((line = br.readLine()) != null) {
            data[idx] = new StringBuilder(line);
            ++idx;
        }

        int[] x = new int[] { 1, -1, 0, 0, 1, 1, -1, -1 };
        int[] y = new int[] { 0, 0, 1, -1, -1, 1, -1, 1 };

        int m = data.length;
        int n = data[0].length();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (data[i].charAt(j) == '*') {
                    int countofAdjNumbers = 0;
                    int ratio = 1;
                    for (idx = 0; idx < 8; ++idx) {
                        int newx = i + x[idx];
                        int newy = j + y[idx];
                        if (newx >= 0 && newx < m && newy >= 0 && newy < n) {
                            if (Character.isDigit(data[newx].charAt(newy))) {
                                ratio = ratio * numberExtractor(data[newx], newy);
                                ++countofAdjNumbers;
                            }
                        }
                    }

                    if (countofAdjNumbers != 2)
                        continue;

                    ans += ratio;
                }
            }
        }

        br.close();
        return ans;
    }

    public static void main(String[] args) throws IOException {
        GearRatios ob = new GearRatios();

        System.out.println(ob.partone());
        System.out.println(ob.parttwo());

    }
}
