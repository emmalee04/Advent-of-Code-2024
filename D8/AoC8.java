package D8;
import java.util.*;
import java.io.*;

public class AoC8 {
  public static void main(String[] args) {
    List<String[]> gridd = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File("D8/input8.txt"));
      while (sc.hasNextLine()) {
        gridd.add(sc.nextLine().split(""));
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    Map<Character, List<int[]>> freqs = new HashMap<>();
    char[][] grid = new char[gridd.size()][gridd.get(0).length];
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char c = gridd.get(i)[j].charAt(0);
        grid[i][j] = c;
        if (c != '.') {
          if (!freqs.containsKey(c)) freqs.put(c, new ArrayList<>());
          freqs.get(c).add(new int[]{i,j});
        }
      }
    }

    // part 1
    Set<String> antinodes = new HashSet<>();

    for (List<int[]> freq : freqs.values()) {
      findAntinodes(freq, antinodes, m, n);
    }

    System.out.println(antinodes.size());

    // part 2
    Set<String> antinodes2 = new HashSet<>();
    for (List<int[]> freq : freqs.values()) {
      findAntinodes2(freq, antinodes2, m, n);
    }

    System.out.println(antinodes2.size());
  }

  private static void findAntinodes(List<int[]> coords, Set<String> antinodes, int m, int n) {
    for (int i = 0; i < coords.size(); i++) {
      for (int j = i+1; j < coords.size(); j++) {
        int[] c1 = coords.get(i), c2 = coords.get(j);
        // first
        int x = 2 * c1[0] - c2[0], y = 2 * c1[1] - c2[1];
        if (x >= 0 && x < m && y >= 0 && y < n) {
          antinodes.add(x+","+y);
        }
        // second
        x = 2 * c2[0] - c1[0];
        y = 2 * c2[1] - c1[1];
        if (x >= 0 && x < m && y >= 0 && y < n) {
          antinodes.add(x+","+y);
        }
      }
    }
  }

  private static void findAntinodes2(List<int[]> coords, Set<String> antinodes2, int m, int n) {
    for (int i = 0; i < coords.size(); i++) {
      for (int j = i+1; j < coords.size(); j++) {
        int[] c1 = coords.get(i), c2 = coords.get(j);
        int dx = c1[0] - c2[0], dy = c1[1] - c2[1];
        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        dx /= gcd;
        dy /= gcd;
        addCollinearPoints(c1, -dx, -dy, antinodes2, m, n);
        addCollinearPoints(c2, dx, dy, antinodes2, m, n);
      }
    }
  }

  private static void addCollinearPoints(int[] start, int dx, int dy, Set<String> antinodes, int m, int n) {
    int x = start[0] + dx, y = start[1] + dy;
    while (x >= 0 && x < m && y >= 0 && y < n) {
      antinodes.add(x + "," + y);
      x += dx;
      y += dy;
    }
  }

  private static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }
}
