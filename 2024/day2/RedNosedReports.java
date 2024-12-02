package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class RedNosedReports {

    int partOne() throws IOException {
        int ans = 0;
        String fileName = "2024/day2/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int[] source;

        while ((line = br.readLine()) != null) {
            source = Stream.of(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            if ((increasing(source) || decreasing(source)) && difference(source)) {
                ans++;
            }

        }
        return ans;
    }

    public boolean increasing(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean decreasing(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean difference(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (Math.abs(array[i] - array[i - 1]) > 3) {
                return false;
            }
        }
        return true;
    }

    int partTwo() throws IOException {
        int ans = 0;
        String fileName = "2024/day2/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int[] source;

        while ((line = br.readLine()) != null) {
            source = Stream.of(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            if ((increasing(source) || decreasing(source)) && difference(source)) {
                ans++;
            } else {
                for (int i = 0; i < source.length; ++i) {
                    if ((increasingSkip(source, i) || decreasingSkip(source, i)) && differenceSkip(source, i)) {
                        ans++;
                        break;
                    }
                }
            }

        }
        return ans;
    }

    public boolean increasingSkip(int[] array, int skipIndex) {
        for (int i = 1; i < array.length; ++i) {
            if ((skipIndex == 0 && i == 1) || i == skipIndex) continue;
            if (array[i] <= array[i - 1 == skipIndex ? i - 2 : i - 1]) {
                return false;
            }
        }
        return true;
    }


    public boolean decreasingSkip(int[] array, int skipIndex) {
        for (int i = 1; i < array.length; ++i) {
            if ((skipIndex == 0 && i == 1) || i == skipIndex) continue;
            if (array[i] >= array[i - 1 == skipIndex ? i - 2 : i - 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean differenceSkip(int[] array, int skipIndex) {
        for (int i = 1; i < array.length; ++i) {
            if ((skipIndex == 0 && i == 1) || i == skipIndex) continue;
            if (Math.abs(array[i] - array[i - 1 == skipIndex ? i - 2 : i - 1]) > 3) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        RedNosedReports report = new RedNosedReports();
        System.out.println(report.partOne());
        System.out.println(report.partTwo());
    }
}
