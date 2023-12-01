package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trebuchet {
    public static void main(String[] args) {

        String fileName = System.getProperty("user.dir") + "/day1/input";
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< line.length(); ++i){
                    if(line.charAt(i) >= '1' && line.charAt(i) <= '9'){
                        sb.append( line.charAt(i));
                        sb.append( line.charAt(i));
                    }
                }
                ans += (sb.charAt(0) - 48) * 10 + (sb.charAt(sb.length() - 1) - 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ans);
    }
}
