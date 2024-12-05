package D5;
import java.util.*;
import java.io.*;

public class AoC5 {
  public static void main(String[] args) {
    List<String> orderings = new ArrayList<>();
    List<List<Integer>> updates = new ArrayList<>();

    try {
      Scanner sc = new Scanner(new File("D5/input5.txt"));
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.length() == 5) {
          orderings.add(line);
        } else if (line != "") {
          String[] numss = line.split(",");
          List<Integer> nums = new ArrayList<>();
          for (String n : numss) nums.add(Integer.parseInt(n));
          updates.add(nums);
        }
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    // part 1 & 2
    int sum = 0;
    int sum2 = 0;
    for (List<Integer> update : updates) {
      if (isValidUpdate(update, orderings)) sum += update.get(update.size()/2);
      else {
        fixUpdate(update, orderings);
        sum2 += update.get(update.size()/2);
      }
    }

    System.out.println(sum);
    System.out.println(sum2);
  }

  private static boolean isValidUpdate(List<Integer> update, List<String> orderings) {
    Map<Integer, Integer> positionMap = new HashMap<>();
      for (int i = 0; i < update.size(); i++) {
          positionMap.put(update.get(i), i);
      }

      for (String order : orderings) {
          int n1 = Integer.parseInt(order.substring(0, 2));
          int n2 = Integer.parseInt(order.substring(3));
          if (positionMap.containsKey(n1) && positionMap.containsKey(n2)) {
              if (positionMap.get(n1) > positionMap.get(n2)) {
                  return false; // Rule violated
              }
          }
      }

      return true;
  }

  private static void fixUpdate(List<Integer> update, List<String> orderings) {
    while (!isValidUpdate(update, orderings)) {
      Map<Integer, Integer> positionMap = new HashMap<>();
      for (int i = 0; i < update.size(); i++) {
          positionMap.put(update.get(i), i);
      }

      for (String order : orderings) {
          int n1 = Integer.parseInt(order.substring(0, 2));
          int n2 = Integer.parseInt(order.substring(3));
          if (positionMap.containsKey(n1) && positionMap.containsKey(n2)) {
              if (positionMap.get(n1) > positionMap.get(n2)) {
                  int i = positionMap.get(n1);
                  int j = positionMap.get(n2);
                  positionMap.put(n1, j);
                  positionMap.put(n2, i);
              }
          }
      }
      int[] res = new int[positionMap.size()];
      for (int n : positionMap.keySet()) {
        res[positionMap.get(n)] = n;
      }
      
      update.clear();
      for (int n : res) update.add(n);
    }
  }
}
