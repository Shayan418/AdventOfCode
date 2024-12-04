package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CeresSearch {
    int[] x = new int[]{1,1,1,0,0,-1,-1,-1};
    int[] y = new int[]{-1,0,1,-1,1,-1,0,1};
    char[] word = new char[]{'X', 'M', 'A', 'S'};


    int partOne() throws IOException {
        int ans = 0;
        String fileName = "2024/day4/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        List<String> ceres = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            ceres.add(line);
        }

        char[][] data = new char[ceres.size()][ceres.getFirst().length()];
        for(int i = 0; i < ceres.size(); i++) {
            data[i] = ceres.get(i).toCharArray();
        }

        for(int i = 0; i < ceres.size(); i++) {
            for(int j = 0; j < ceres.getFirst().length(); j++) {
                if (data[i][j] == 'X') {
                    for (int k = 0; k < x.length; k++) {
                        ans += depthSearch(data, i + x[k], j + y[k], k, 1) ? 1 : 0;
                    }
                }
            }
        }
        return ans;
    }

    public boolean depthSearch(char[][] data, int row, int col, int dir, int curr) {
        if(curr == word.length) {
            return true;
        }

        if(row >= 0 && row < data.length && col >= 0 && col < data[0].length && data[row][col] == word[curr]) {
            return depthSearch(data, row + x[dir], col + y[dir], dir, ++curr);
        }

        return false;
    }


    int partTwo() throws IOException {
        int ans = 0;
        String fileName = "2024/day4/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        List<String> ceres = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            ceres.add(line);
        }

        char[][] data = new char[ceres.size()][ceres.getFirst().length()];
        for(int i = 0; i < ceres.size(); i++) {
            data[i] = ceres.get(i).toCharArray();
        }

        for(int i = 0; i < ceres.size(); i++) {
            for(int j = 0; j < ceres.getFirst().length(); j++) {
                if (j+2 < ceres.getFirst().length() && data[i][j] == 'M' && data[i][j+2] == 'M') {
                    ans += depthSearch(data, i + x[2], j + y[2], 2, 2) && depthSearch(data, i + x[0], j + 2 + y[0], 0, 2) ? 1 : 0;
                    ans += depthSearch(data, i + x[7], j + y[7], 7, 2) && depthSearch(data, i + x[5], j + 2 + y[5], 5, 2) ? 1 : 0;
                }
                if (i+2 < ceres.size() && data[i][j] == 'M' && data[i+2][j] == 'M'){
                    ans += depthSearch(data, i + x[2], j + y[2], 2, 2) && depthSearch(data, i + 2 + x[7], j + y[7], 7, 2) ? 1 : 0;
                    ans += depthSearch(data, i + x[0], j + y[0], 0, 2) && depthSearch(data, i + 2 + x[5], j + y[5], 5, 2) ? 1 : 0;
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) throws IOException {
        CeresSearch search = new CeresSearch();
        System.out.println(search.partOne());
        System.out.println(search.partTwo());

    }
}
