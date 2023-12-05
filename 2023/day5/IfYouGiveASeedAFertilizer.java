package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class IfYouGiveASeedAFertilizer {

    public long partone() throws IOException {
        String fileName = System.getProperty("user.dir") + "/2023/day5/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        long[] source = Stream.of(line.split("seeds:")[1].trim().split("\\s+")).mapToLong(Long::parseLong).toArray();

        ArrayList<long[]> data = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if ((line.equals("") && data.size() != 0)) {
                // main logic

                for (int i = 0; i < source.length; ++i) {
                    for (long[] d : data) {
                        if (source[i] >= d[1] && source[i] < d[1] + d[2]) {
                            source[i] = d[0] + (source[i] - d[1]);
                            break;
                        }
                    }
                }

                for (long[] d : data) {
                    for (long l : d) {
                        System.out.print(l + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                data = new ArrayList<>();
            } else if (!line.equals("") && !line.contains("map")) {
                data.add(Stream.of(line.split("\\s+")).mapToLong(Long::parseLong).toArray());
            }

        }

        br.close();
        return Arrays.stream(source).min().getAsLong();
    }

    public static void main(String[] args) throws IOException {
        IfYouGiveASeedAFertilizer ob = new IfYouGiveASeedAFertilizer();
        System.out.println(ob.partone());
    }
}
