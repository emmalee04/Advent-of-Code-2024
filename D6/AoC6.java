package D6;
import java.util.*;
import java.io.*;

public class AoC6 {
  public static void main(String[] args) {
    List<String[]> gridd = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File("D6/input6.txt"));
      while (sc.hasNextLine()) {
        gridd.add(sc.nextLine().split(""));
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    int x = -1, y = -1;
    char[][] grid = new char[gridd.size()][gridd.get(0).length];
    int m = grid.length, n = grid[0].length;
    List<int[]> obs = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = gridd.get(i)[j].charAt(0);
        if (grid[i][j] == '^') {
          x = i;
          y = j;
        } else if (grid[i][j] == '#') {
          obs.add(new int[]{i, j});
        }
      }
    }

    // part 1
    int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int dirCounter = 0;
    int count = 1;
    while (x > 0 && x < m-1 && y > 0 && y < n-1) {
      int nextX = x+dir[dirCounter][0], nextY = y+dir[dirCounter][1];
      if (grid[nextX][nextY] != '#') {
        if (grid[nextX][nextY] == '.') count++;
        grid[nextX][nextY] = '^';
        grid[x][y] = 'X';
        x = nextX;
        y = nextY;
      } else {
        dirCounter = (dirCounter+1)%4;
      }
    }

    System.out.println(count);

    // part 2
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = gridd.get(i)[j].charAt(0);
        if (grid[i][j] == '^') {
          x = i;
          y = j;
        }
      }
    }
    System.out.println(countLoopPositions(grid, new int[]{x, y}));
  }

  private static int countLoopPositions(char[][] grid, int[] start) {
    int count = 0;
    int m = grid.length, n = grid[0].length;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // Up, Right, Down, Left

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '.') {
          grid[i][j] = '#';
          if (causesLoop(grid, directions, start)) {
              count++;
          }
          // Reset the grid
          grid[i][j] = '.';
        }
      }
    }
    return count;
  }

  private static boolean causesLoop(char[][] grid, int[][] directions, int[] start) {
    int x = start[0], y = start[1], dir = 0;
    Set<String> visited = new HashSet<>();

    while (x > 0 && x < grid.length - 1 && y > 0 && y < grid[0].length - 1) {
        String state = x + "," + y + "," + dir;
        if (visited.contains(state)) {
            return true; // Loop detected
        }
        visited.add(state);
        int nextX = x + directions[dir][0];
        int nextY = y + directions[dir][1];

        if (grid[nextX][nextY] == '#') {
            dir = (dir + 1) % 4; // Turn right
        } else {
            x = nextX;
            y = nextY;
        }
    }
    return false;
  }
}
