package day6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GuardGallivantParallel {
    private final ExecutorService executorService;
    int[] x = new int[]{-1, 0, 1, 0};
    int[] y = new int[]{0, 1, 0, -1};

    public GuardGallivantParallel(int threads) {
        this.executorService = Executors.newFixedThreadPool(threads);
    }

    int partTwo(String[] input) {

        int ans = 0;
        int directrion = -1;
        int row = -1;
        int col = -1;
        for (int i = 0; i < input.length && directrion == -1; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                directrion = switch (input[i].charAt(j)) {
                    case '^' -> 0;
                    case '>' -> 1;
                    case 'v' -> 2;
                    case '<' -> 3;
                    default -> directrion;
                };
                if (directrion != -1) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        List<Future<Boolean>> results = new ArrayList<>();
        boolean[][] visited = new boolean[input.length][input[0].length()];
        travel(input, visited, directrion, row, col);

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if (input[i].charAt(j) == '.' && visited[i][j]) {
                    //detectCycleIterative(input, new boolean[input.length][input[0].length()][4], directrion, row, col, i, j) ? 1 : 0;
                    int finalDirectrion = directrion;
                    int finalRow = row;
                    int finalCol = col;
                    int finalI = i;
                    int finalJ = j;
                    results.add(executorService.submit(() -> detectCycleIterative(input, new boolean[input.length][input[0].length()][4], finalDirectrion, finalRow, finalCol, finalI, finalJ)));
                }
            }
        }

        for (Future<Boolean> future : results) {
            try {
                if (future.get()) {
                    ans++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ans;

    }

    boolean detectCycleIterative(String[] input, boolean[][][] visited, int direction, int row, int col, int i, int j) {
        int prevRow = row;
        int prevCol = col;

        while (true){
            int newRow = prevRow + x[direction];
            int newCol = prevCol + y[direction];

            if (visited[prevRow][prevCol][direction]) {
                return true;
            }

            visited[prevRow][prevCol][direction] = true;

            if(!(newRow >= 0 && newRow < input.length && newCol >= 0 && newCol < input[0].length())) {
                return false;
            }

            if (input[newRow].charAt(newCol) == '#' || (newRow == i && newCol == j)) {
                direction = (direction + 1) % 4;
            } else {
                prevRow = newRow;
                prevCol = newCol;
            }
        }
    }


    void travel(String[] input, boolean[][] visited, int direction, int row, int col) {
        visited[row][col] = true;

        int newRow = row + x[direction];
        int newCol = col + y[direction];

        if(!(newRow >= 0 && newRow < input.length && newCol >= 0 && newCol < input[0].length())) {
            return;
        }

        if (input[newRow].charAt(newCol) == '#'){
            direction = (direction + 1) % 4;
            travel(input, visited, direction, row, col);
        } else {
            travel(input, visited, direction, newRow, newCol);
        }
    }


    public static void main(String[] args) throws IOException {
        String[] input = Util.InputReader.ReadFile("2024/day6/input.txt");
        GuardGallivantParallel gallivant = new GuardGallivantParallel(8);
        System.out.println(gallivant.partTwo(input));
        gallivant.executorService.shutdown();
    }
}
