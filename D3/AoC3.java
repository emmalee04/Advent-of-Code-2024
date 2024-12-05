import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class AoC3 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File("D3/input3.txt"));
      while (sc.hasNextLine()) {
        list.add(sc.nextLine());
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

    String inputs = "";
    for (String l : list) inputs += l;

    String regex = "mul\\((-?\\d+),(-?\\d+)\\)";
    int res = 0;

    // part 1
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inputs);
    while (matcher.find()) {
      int a = Integer.parseInt(matcher.group(1)); // First number
      int b = Integer.parseInt(matcher.group(2)); // Second number
      res += a * b;
    }

    System.out.println(res);

    // part 2
    String enableRegex = "do\\(\\)";
    String disableRegex = "don't\\(\\)";

    Pattern enablePattern = Pattern.compile(enableRegex);
    Pattern disablePattern = Pattern.compile(disableRegex);
    Pattern mulPattern = Pattern.compile(regex);

    int res2 = 0;

    Matcher matcher2 = Pattern.compile(enableRegex + "|" + disableRegex + "|" + regex).matcher(inputs);
    boolean isEnabled = true;
    while (matcher2.find()) {
      String match = matcher2.group();
      if (enablePattern.matcher(match).matches()) {
        isEnabled = true;
      } else if (disablePattern.matcher(match).matches()) {
        isEnabled = false;
      } else if (isEnabled && mulPattern.matcher(match).matches()) {
        // Extract numbers if enabled
        Matcher mulMatcher = mulPattern.matcher(match);
        if (mulMatcher.matches()) {
            int a = Integer.parseInt(mulMatcher.group(1));
            int b = Integer.parseInt(mulMatcher.group(2));
            res2 += a * b;
        }
      }
    }

    System.out.println(res2);
  }

}

