package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CubeConundrum {

    public boolean valid(String c, Integer n) {
        switch (c) {
            case "red":
                return (n <= 12) ? true : false;
            case "green":
                return (n <= 13) ? true : false;
            case "blue":
                return (n <= 14) ? true : false;
            default:
                break;
        }
        return false;
    }

    public int partone() {
        String fileName = System.getProperty("user.dir") + "/2023/day2/input";
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll(";", ",");
                String[] aux = line.split(":");
                int gameid = Integer.parseInt(aux[0].split(" ")[1]);
                aux[0] = "";
                line = String.join("", aux).trim();
                aux = line.split(",");
                boolean game = true;
                for (String s : aux) {
                    s = s.trim();
                    String[] temps = s.split(" ");
                    if (!valid(temps[1], Integer.parseInt(temps[0]))) {
                        game = false;
                        break;
                    }
                }
                if (game) {
                    ans += gameid;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    public int partwo() {
        String fileName = System.getProperty("user.dir") + "/2023/day2/input";
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                HashMap<String, Integer> mp = new HashMap<>();
                mp.put("red", 0);
                mp.put("green", 0);
                mp.put("blue", 0);

                line = line.replaceAll(";", ",");
                String[] aux = line.split(":");
                aux[0] = "";
                line = String.join("", aux).trim();
                aux = line.split(",");
                for (String s : aux) {
                    s = s.trim();
                    String[] temps = s.split(" ");
                    mp.put(temps[1], Math.max(mp.get(temps[1]), Integer.parseInt(temps[0])));
                }
                ans += mp.get("red") * mp.get("blue") * mp.get("green");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    public static void main(String[] args) {
        CubeConundrum ob = new CubeConundrum();
        System.out.println(ob.partone());
        System.out.println(ob.partwo());
    }
}
