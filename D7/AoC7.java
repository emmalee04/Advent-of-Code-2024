package D7;
import java.util.*;
import java.io.*;

public class AoC7 {
  public static void main(String[] args) {
    Map<Long, List<Integer>> eqns = new HashMap<>();
    try {
      Scanner sc = new Scanner(new File("D7/input7.txt"));
      while (sc.hasNextLine()) {
        String[] inputs = sc.nextLine().split(": ");
        long res = Long.parseLong(inputs[0]);
        String[] numss = inputs[1].split(" ");
        List<Integer> nums = new ArrayList<>();
        for (String s : numss) {
          nums.add(Integer.parseInt(s));
        }
        eqns.put(res, nums);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    // part 1
    long totalCalibration1 = 0;
    for (long res : eqns.keySet()) {
      List<Integer> nums = eqns.get(res);
      if (isValid(res, nums, 0, nums.get(0))) totalCalibration1 += res;
    }
    System.out.println(totalCalibration1);

    // part 2
    long totalCalibration2 = 0;
    for (long res : eqns.keySet()) {
      List<Integer> nums = eqns.get(res);
      if (isValid2(res, nums, 0, nums.get(0))) totalCalibration2 += res;
    }
    System.out.println(totalCalibration2);
  }

  private static boolean isValid(long res, List<Integer> nums, int index, long currRes) {
    if (index == nums.size()-1) return currRes == res;
    int nextNum = nums.get(index+1);
    if (isValid(res, nums, index+1, currRes + nextNum)) return true;
    if (isValid(res, nums, index+1, currRes * nextNum)) return true;
    return false;
  }

  private static boolean isValid2(long res, List<Integer> nums, int index, long currRes) {
    if (index == nums.size()-1) return currRes == res;
    int nextNum = nums.get(index+1);
    if (isValid2(res, nums, index+1, currRes + nextNum)) return true;
    if (isValid2(res, nums, index+1, currRes * nextNum)) return true;
    if (isValid2(res, nums, index+1, Long.parseLong(currRes + "" + nextNum))) return true;
    return false;
  }
}
