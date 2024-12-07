package day5;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class PrintQueue {
    int partOne() throws IOException {
        int ans = 0;

        String[] input = Util.InputReader.ReadFile("2024/day5/input.txt");

        List<int[]> rules = new ArrayList<>();

        int i = 0;
        for (; i < input.length; i++) {
            if(input[i].isEmpty()) {
                ++i;
                break;
            }
            rules.add(Stream.of(input[i].split("\\|")).mapToInt(Integer::parseInt).toArray());
        }

        List<Map<Integer, Integer>> updates = new ArrayList<>();

        for (; i < input.length; i++) {
            String[] numbers = input[i].split(",");
            Map<Integer, Integer> mp = new HashMap<>();
            for (int j = 0; j < numbers.length; j++) {
                mp.put(Integer.parseInt(numbers[j]), j);
            }
            updates.add(mp);
        }


        for(Map<Integer, Integer> mp : updates) {
            boolean isGood = true;
            for(int[] rule : rules) {
                if (mp.containsKey(rule[0]) && mp.containsKey(rule[1]) && mp.get(rule[0]) > mp.get(rule[1])) {
                    isGood = false;
                }
            }
            if(isGood) {
                int mid = findMiddleNumber(mp);
                ans += mid;
            }
        }

        return ans;
    }

    int partTwo() throws IOException {
        int ans = 0;

        String[] input = Util.InputReader.ReadFile("2024/day5/input.txt");

        List<int[]> rules = new ArrayList<>();

        int i = 0;
        for (; i < input.length; i++) {
            if(input[i].isEmpty()) {
                ++i;
                break;
            }
            rules.add(Stream.of(input[i].split("\\|")).mapToInt(Integer::parseInt).toArray());
        }

        List<Map<Integer, Integer>> updates = new ArrayList<>();

        for (; i < input.length; i++) {
            String[] numbers = input[i].split(",");
            Map<Integer, Integer> mp = new HashMap<>();
            for (int j = 0; j < numbers.length; j++) {
                mp.put(Integer.parseInt(numbers[j]), j);
            }
            updates.add(mp);
        }
        
        for(Map<Integer, Integer> mp : updates) {
            boolean isGood = true;
            for(int[] rule : rules) {
                if (mp.containsKey(rule[0]) && mp.containsKey(rule[1]) && mp.get(rule[0]) > mp.get(rule[1])) {
                    isGood = false;
                }
            }
            if(isGood) {
                continue;
            }

            do {
                isGood = true;
                for(int[] rule : rules) {
                    if (mp.containsKey(rule[0]) && mp.containsKey(rule[1]) && mp.get(rule[0]) > mp.get(rule[1])) {
                        isGood = false;
                        int left = mp.get(rule[0]);
                        int right = mp.get(rule[1]);
                        mp.put(rule[0], right);
                        mp.put(rule[1], left);
                    }
                }
            } while (!isGood);

            int mid = findMiddleNumber(mp);
            ans += mid;
        }

        return ans;
    }

    public int findMiddleNumber(Map<Integer, Integer> numMap) {
        int middleIndex = numMap.size() / 2;

        for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            if (entry.getValue() == middleIndex) {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException("Middle number not found");

    }

    public static void main(String[] args) throws IOException {
        PrintQueue queue = new PrintQueue();
        System.out.println(queue.partOne());
        System.out.println(queue.partTwo());
    }
}
