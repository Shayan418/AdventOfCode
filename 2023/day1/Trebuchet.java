package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Trebuchet {

    public int partone() {

        String fileName = "2023/day1/input";
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length(); ++i) {
                    if (line.charAt(i) >= '1' && line.charAt(i) <= '9') {
                        sb.append(line.charAt(i));
                        sb.append(line.charAt(i));
                    }
                }
                ans += (sb.charAt(0) - 48) * 10 + (sb.charAt(sb.length() - 1) - 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    public int partwo() {
        String fileName = "2023/day1/input";
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            HashMap<String, Integer> mp = new HashMap<>();
            mp.put("one", 1);
            mp.put("two", 2);
            mp.put("three", 3);
            mp.put("four", 4);
            mp.put("five", 5);
            mp.put("six", 6);
            mp.put("seven", 7);
            mp.put("eight", 8);
            mp.put("nine", 9);

            while ((line = br.readLine()) != null) {
                int f = firstint(line, mp) * 10 + lastint(line, mp);
                ans += f;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    public int firstint(String str, HashMap<String, Integer> mp) {
        int ret = 0;

        for (int i = 0; i < str.length(); ++i) {
            if (Character.isDigit(str.charAt(i))) {
                ret = str.charAt(i) - '0';
                break;
            }
            
            if (i <= str.length() - 3) {
                ret = mp.getOrDefault(str.substring(i, i + 3), 0);
                if (ret != 0)
                    break;
            }

            if (i <= str.length() - 4) {
                ret = mp.getOrDefault(str.substring(i, i + 4), 0);
                if (ret != 0)
                    break;
            }

            if (i <= str.length() - 5) {
                ret = mp.getOrDefault(str.substring(i, i + 5), 0);
                if (ret != 0)
                    break;
            }
        }

        return ret;
    }

    public int lastint(String str, HashMap<String, Integer> mp) {
        int ret = 0;

        for (int i = str.length() - 1; i >=0;  --i) {
            if (Character.isDigit(str.charAt(i))) {
                ret = str.charAt(i) - '0';
                break;
            }
            
            if (i <= str.length() - 3) {
                ret = mp.getOrDefault(str.substring(i, i + 3), 0);
                if (ret != 0)
                    break;
            }

            if (i <= str.length() - 4) {
                ret = mp.getOrDefault(str.substring(i, i + 4), 0);
                if (ret != 0)
                    break;
            }

            if (i <= str.length() - 5) {
                ret = mp.getOrDefault(str.substring(i, i + 5), 0);
                if (ret != 0)
                    break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Trebuchet ob = new Trebuchet();
        System.out.println(ob.partone());
        System.out.println(ob.partwo());
    }
}
