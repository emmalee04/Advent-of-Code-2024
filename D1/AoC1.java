import java.util.*;
import java.io.*;

public class AoC1 {
  public static void main(String[] args) {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();
    Map<Integer, Integer> freq2 = new HashMap<>();

    try {
      Scanner sc = new Scanner(new File("input1.txt"));
      while (sc.hasNextLine()) {
        String[] nums = sc.nextLine().split("   ");
        int num1 = Integer.parseInt(nums[0]), num2 = Integer.parseInt(nums[1]);
        l1.add(num1);
        l2.add(num2);
        freq2.put(num2, freq2.getOrDefault(num2, 0)+1);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    // part 1
    Collections.sort(l1);
    Collections.sort(l2);

    long sum = 0;
    for (int i = 0; i < l1.size(); i++) {
      sum += Math.abs(l1.get(i) - l2.get(i));
    }

    System.out.println("Part 1: " + sum);

    // part 2
    long freq = 0;
    for (int n : l1) {
      if (freq2.containsKey(n)) {
        freq += n * freq2.get(n);
      }
    }

    System.out.println("Part 2: " + freq);
  }
}
