package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitForIt {
    public int partone() throws IOException {
        int ans = 1;
        String fileName = System.getProperty("user.dir") + "/2023/day6/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        int[] time = Stream.of(line.split("Time:")[1].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        line = br.readLine();
        int[] distance = Stream.of(line.split("Distance:")[1].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < time.length; ++i) {
            int t = time[i];
            int d = distance[i];

            int count = 0;
            for (int buttonpressed = 0; buttonpressed < t; ++buttonpressed) {
                if (((t - buttonpressed) * buttonpressed) > d) {
                    ++count;
                }
            }
            ans *= count;
        }

        br.close();
        return ans;
    }

    public long parttwo() throws IOException {
        String fileName = System.getProperty("user.dir") + "/2023/day6/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
        String line = br.readLine();
        String time = Stream.of(line.split("Time:")[1].trim().split("\\s+")).collect(Collectors.joining(""));
        line = br.readLine();
        String distance = Stream.of(line.split("Distance:")[1].trim().split("\\s+")).collect(Collectors.joining(""));
        
        long t = Long.parseLong(time);
        long d = Long.parseLong(distance);

        long ans = 0;
        for (long buttonpressed = 0; buttonpressed < t; ++buttonpressed) {
            if (((t - buttonpressed) * buttonpressed) > d) {
                ++ans;
            }
        }
        br.close();
        return ans;
    }

    public static void main(String[] args) throws IOException {
        WaitForIt ob = new WaitForIt();
        System.out.println(ob.partone());
        System.out.println(ob.parttwo());
    }
}
