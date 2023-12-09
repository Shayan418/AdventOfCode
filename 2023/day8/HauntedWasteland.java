package day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HauntedWasteland {
    public long partone() throws IOException {

        Scanner sc = new Scanner(new FileInputStream("2023/day8/input.txt"));
        String intel = sc.nextLine();
        String line = sc.nextLine();
        HashMap<String, String[]> data = new HashMap<>();
        while (sc.hasNext()) {
            line = sc.nextLine();
            String[] temp = line.split("[\\s=(),]+");
            data.put(temp[0], new String[] { temp[1], temp[2] });
        }
        sc.close();

        int ans = 0;
        String curr = "AAA";
        int idx = 0;
        while (!curr.equals("ZZZ")) {
            if (idx == intel.length())
                idx = 0;
            curr = intel.charAt(idx) == 'L' ? data.get(curr)[0] : data.get(curr)[1];
            ++idx;
            ++ans;
        }

        return ans;
    }

    public long lcm(ArrayList<Integer> a) {
        long max = Collections.max(a);
        long lcm = max * 1L;

        while (true) {
            final long currlcm = lcm;
            if (a.stream().allMatch(num -> currlcm % num == 0)) {
                return lcm;
            }
            lcm += max;
        }
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public long lcmfast(ArrayList<Integer> a) {
        long lcm = a.get(0);
        for (int i = 0; i < a.size(); ++i) {
            long gcdTemp = gcd(lcm, a.get(i));
            lcm = (lcm * a.get(i)) / gcdTemp;
        }
        return lcm;
    }

    public long parttwo() throws IOException {

        Scanner sc = new Scanner(new FileInputStream("2023/day8/input.txt"));
        String intel = sc.nextLine();
        String line = sc.nextLine();
        HashMap<String, String[]> data = new HashMap<>();
        while (sc.hasNext()) {
            line = sc.nextLine();
            String[] temp = line.split("[\\s=(),]+");
            data.put(temp[0], new String[] { temp[1], temp[2] });
        }
        sc.close();

        ArrayList<String> origins = new ArrayList<>();
        ArrayList<Integer> answers = new ArrayList<>();

        for (String s : data.keySet()) {
            if (s.charAt(2) == 'A')
                origins.add(s);
        }

        for (String s : origins) {
            int count = 0;
            String curr = s;
            int idx = 0;
            while (curr.charAt(2) != 'Z') {
                if (idx == intel.length())
                    idx = 0;
                curr = intel.charAt(idx) == 'L' ? data.get(curr)[0] : data.get(curr)[1];
                ++idx;
                ++count;
            }
            answers.add(count);
        }
        System.out.println(answers);
        long ans = lcmfast(answers);

        return ans;
    }

    public static void main(String[] args) throws IOException {
        HauntedWasteland ob = new HauntedWasteland();
        System.out.println(ob.partone());
        System.out.println(ob.parttwo());
    }

}
