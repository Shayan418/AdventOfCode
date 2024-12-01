package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HistorianHysteria {
    public int partOne(){
        String fileName = "2024/day1/input.txt";
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" {3}");
                left.add(Integer.parseInt(numbers[0]));
                right.add(Integer.parseInt(numbers[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(left);
        Collections.sort(right);
        int ans = 0;
        for(int i = 0; i < left.size(); i++){
            ans += Math.abs(left.get(i) - right.get(i));
        }
        return ans;
    }

    public int partTwo(){
        String fileName = "2024/day1/input.txt";
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> right = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" {3}");
                left.add(Integer.parseInt(numbers[0]));
                right.put(Integer.parseInt(numbers[1]), right.computeIfAbsent(Integer.parseInt(numbers[1]), _ -> 0) + 1);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(left);
        int ans = 0;
        for (Integer integer : left) {
            ans += integer * right.getOrDefault(integer, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        HistorianHysteria ob = new HistorianHysteria();
        System.out.println(ob.partOne());
        System.out.println(ob.partTwo());
    }
}
