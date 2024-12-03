import java.util.*;
import java.io.*;

public class AoC2 {
  public static void main(String[] args) {
    int count = 0;
    int dampenCount = 0;
    try {
      Scanner sc = new Scanner(new File("input2.txt"));
      while (sc.hasNextLine()) {
        List<Integer> levels = new ArrayList<>();
        String[] ls = sc.nextLine().split(" ");
        for (String s : ls) levels.add(Integer.parseInt(s));
        if (isValid(levels)) {
          count++;
          dampenCount++;
        } else {
          boolean isoneValid = false;
          for (int i = 0; i < levels.size(); i++) {
            int num = levels.remove(i);
            if (isValid(levels)) isoneValid = true;
            levels.add(i, num);
          }
          if (isoneValid) dampenCount++;
        }
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    // part 1
    System.out.println(count);

    // part 2
    System.out.println(dampenCount);
  }

  public static boolean isValid(List<Integer> levels) {
    int count = 0;
    if (levels.get(1) > levels.get(0)) {
      boolean isValid = true;
      for (int i = 1; i < levels.size(); i++) {
        int diff = levels.get(i) - levels.get(i-1);
        if (diff > 3 || diff < 1) isValid = false;
      }
      if (isValid) count++;
    } else if (levels.get(1) < levels.get(0)) {
      boolean isValid = true;
      for (int i = 1; i < levels.size(); i++) {
        int diff = levels.get(i-1) - levels.get(i);
        if (diff > 3 || diff < 1) isValid = false;
      }
      if (isValid) count++;
    }
    return count != 0;
  }
}
