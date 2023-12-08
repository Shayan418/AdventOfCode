package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CamelCardsPartTwo {

    public int rank(char ch) {
        if (ch == 'J')
            return 1;
        if (ch == 'A')
            return 13;
        if (ch == 'K')
            return 12;
        if (ch == 'Q')
            return 11;
        if (ch == 'T')
            return 10;
        else
            return ch - '0';

    }

    public int charcount(String s, char ch) {
        int count = 0;
        for (Character character : s.toCharArray()) {
            if (character.equals(ch))
                ++count;
        }
        return count;
    }

    public int strength(String s) {
        boolean isj = false;
        int max = 0;
        String maxch = "A";
        for (Character character : s.toCharArray()) {
            if (character.equals('J')) {
                isj = true;
            }else if (charcount(s, character) > max) {
                max = charcount(s, character);
                maxch = character.toString();
            }
        }

        if (isj) {
            s = s.replaceAll("J", maxch);
        }

        HashSet<Character> hs = new HashSet<>();
        for (Character character : s.toCharArray()) {
            hs.add(character);
        }

        if (hs.size() == 1) {
            return 7;
        } else if (hs.size() == 2) {

            char f = s.charAt(0);
            int count = charcount(s, f);

            if (count == 1 || count == 4)
                return 6;

            return 5;

        } else if (hs.size() == 3) {

            for (Character ch : hs) {
                if (charcount(s, ch) == 3)
                    return 4;
            }

            return 3;
        } else if (hs.size() == 4) {
            return 2;
        } else {
            return 1;
        }

    }

    public long parttwo() throws IOException {
        String fileName = System.getProperty("user.dir") + "/2023/day7/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        ArrayList<String[]> data = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            data.add(line.split("\\s+"));
        }
        br.close();

        Collections.sort(data, (d1, d2) -> {
            int s1 = strength(d1[0]);
            int s2 = strength(d2[0]);
            if (s1 != s2)
                return s1 - s2;
            else

                for (int i = 0; i < 5; ++i) {
                    if (rank(d1[0].charAt(i)) != rank(d2[0].charAt(i))) {
                        return rank(d1[0].charAt(i)) - rank(d2[0].charAt(i));
                    }
                }

            return 0;

        });

        int ans = 0;

        for (int i = 0; i < data.size(); ++i) {
            ans = ans + ((i + 1) * Integer.parseInt(data.get(i)[1]));
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        CamelCardsPartTwo ob = new CamelCardsPartTwo();
        System.out.println(ob.parttwo());

    }
}
