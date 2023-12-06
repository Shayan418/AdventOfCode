package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
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

                data = new ArrayList<>();
            } else if (!line.equals("") && !line.contains("map")) {
                data.add(Stream.of(line.split("\\s+")).mapToLong(Long::parseLong).toArray());
            }

        }

        br.close();
        return Arrays.stream(source).min().getAsLong();
    }

    public long parttwo() throws IOException {
        String fileName = System.getProperty("user.dir") + "/2023/day5/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        long[] source_temp = Stream.of(line.split("seeds:")[1].trim().split("\\s+")).mapToLong(Long::parseLong)
                .toArray();
        Queue<long[]> source = new LinkedList<>();

        for (int i = 0; i < source_temp.length; i += 2) {
            source.add(new long[] { source_temp[i], source_temp[i + 1] });
        }

        ArrayList<long[]> data = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if ((line.equals("") && data.size() != 0)) {
                // main logic
                Collections.sort(data, (o1, o2) -> Long.compare(o1[1], o2[1]));

                int size = source.size();
                while (size > 0) {
                    long[] curr = source.poll();
                    long start = curr[0];
                    long range = curr[1];

                    boolean finished = false;

                    while (!finished && range > 0) {

                        for (int i = 0; i < data.size(); ++i) {
                            long target_start = data.get(i)[1];
                            long target_range = data.get(i)[2];
                            long target_end = target_start + target_range;
                            
                            // no need to split the range if already fall inside
                            if (target_start <= start && (start + range) < target_end) {
                                source.add(new long[] { data.get(i)[0] + (start - target_start), range });
                                finished = true;
                                break;
                            }
                            // save data inside range back to queue and continue with remaining value
                            else if (target_start <= start && start < target_end) {
                                long newrange = (target_end) - start;
                                source.add(new long[] { data.get(i)[0] + (start - target_start), newrange });

                                start = target_end;
                                range = range - newrange;
                            }
                        }

                        if (!finished) {
                            source.add(new long[] { start, range });
                            finished = true;
                        }

                    }

                    --size;
                }

                data = new ArrayList<>();
            } else if (!line.equals("") && !line.contains("map")) {
                data.add(Stream.of(line.split("\\s+")).mapToLong(Long::parseLong).toArray());
            }

        }

        br.close();

        return source.stream()
                .min((o1, o2) -> Long.compare(o1[0], o2[0]))
                .orElseThrow(() -> new IllegalArgumentException("Queue is empty"))[0];

    }

    public static void main(String[] args) throws IOException {
        IfYouGiveASeedAFertilizer ob = new IfYouGiveASeedAFertilizer();
        System.out.println(ob.partone());
        System.out.println(ob.parttwo());
    }
}
