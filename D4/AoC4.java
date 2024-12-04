package D4;

import java.util.*;
import java.io.*;

public class AoC4 {
  public static void main(String[] args) {
    List<char[]> gridd = new ArrayList<>();
    int n = 0;
    try {
      Scanner sc = new Scanner(new File("input4.txt"));
      while (sc.hasNextLine()) {
        char[] chars = sc.nextLine().toCharArray();
        n = chars.length;
        gridd.add(chars);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    int m = gridd.size();
    char[][] grid = new char[m][n];
    for (int i = 0; i < m; i++) {
      grid[i] = gridd.get(i);
    }

    // part 1

    int count = 0;

    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 'X') {
          for (int[] d : dir) {
            boolean isValid = true;
            char[] rest = {'M', 'A', 'S'};
            for (int c = 0; c < 3; c++) {
              int x = i+(c+1)*d[0], y = j+(c+1)*d[1];
              if (!(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == rest[c])) {
                isValid = false;
                break;
              }
            }
            if (isValid) count++;
          }
        }
      }
    }

    System.out.println(count);

    // part 2
    int count2 = 0;

    //  M   M     S   M     M   S     S   S
    //    A         A         A         A
    //  S   S     S   M     M   S     M   M
    int[][] dir2 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    char[] rotate = {'M', 'S', 'S', 'M'};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 'A') {
          for (int start = 0; start < 4; start++) {
            boolean isValid = true;
            for (int r = 0; r < 4; r++) {
              int[] d = dir2[(start + r) % 4];
              int x = i + d[0], y = j + d[1];
              if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != rotate[r]) {
                isValid = false;
                break;
              }
            }
            if (isValid) {
              count2++;
              break;
            }
          }
        }
      }
    }

    System.out.println(count2);
  }
}
