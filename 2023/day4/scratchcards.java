package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class scratchcards {

    public int partone() throws IOException {
        int ans = 0;
        String fileName = System.getProperty("user.dir") + "/2023/day4/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";

        while ((line = br.readLine()) != null) {
            int curr = 0;
            String winning = line.split("\\|")[0].split(":")[1].trim();
            String target = line.split("\\|")[1].trim();
 
            HashSet<String> hs = new HashSet<>(Arrays.asList(winning.split("\\s+")));
            for(String s: target.split("\\s+")){
                if(hs.contains(s)){
                    curr = (curr == 0) ? 1 : curr * 2;
                }
            }

            ans += curr;
        }

        br.close();
        return ans;
    }

    public int parttwo() throws IOException {
        int ans = 0;
        String fileName = System.getProperty("user.dir") + "/2023/day4/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        int[] instances = new int[500]; 
        Arrays.fill(instances, 1);
        int idx = 1;
        while ((line = br.readLine()) != null) {
            int curr = 0;
            String winning = line.split("\\|")[0].split(":")[1].trim();
            String target = line.split("\\|")[1].trim();
            
            HashSet<String> hs = new HashSet<>(Arrays.asList(winning.split("\\s+")));
            for(String s: target.split("\\s+")){
                if(hs.contains(s)){
                    ++curr;
                }
            }

            while (curr > 0) {
                instances[idx + curr] += instances[idx];
                --curr;
            }

            ans += instances[idx];
            ++idx;
        }

        br.close();
        return ans;
    }

    public static void main(String[] args) throws IOException {
        scratchcards ob = new scratchcards();
        System.out.println(ob.partone());
        System.out.println(ob.parttwo());
    }
}
