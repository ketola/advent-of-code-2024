package ketola.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2024/day/3
 */
public class AOC3 {

    public static final String INPUT_FILE = "/aoc_3.txt";

    public static void main(String[] args) throws Exception {
        new AOC3().solve();
    }

    public Solution solve() throws Exception {
        String input = Files.readString(Paths.get(AOC3.class.getResource(INPUT_FILE).toURI()));

        Long total = solvePart1(input);
        System.out.println("Part 1: Total is: " + total);

        Long totalAccurate = solvePart2(input);
        System.out.println("Part 2: Total is: " + totalAccurate);

        return new Solution(total, totalAccurate);
    }

    private static Long solvePart1(String input) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher m = pattern.matcher(input);
        return m.results()
                .map(MatchResult::group)
                .map(s -> s.replace("mul(", ""))
                .map(s -> s.replace(")", ""))
                .map(s -> s.split(","))
                .map(List::of)
                .map(li -> li.stream().map(Long::parseLong).toList())
                .map(li -> li.getFirst() * li.getLast())
                .reduce(0L, Long::sum);
    }

    private static Long solvePart2(String input) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)");
        Matcher m = pattern.matcher(input);

        List<String> muls = new ArrayList<>();
        boolean skip = false;
        while (m.find()) {
            String group = m.group();
            if (group.equals("do()")) {
                skip = false;
                continue;
            }
            if (group.equals("don't()")) {
                skip = true;
                continue;
            }

            if (!skip) {
                muls.add(group);
            }
        }

        return muls.stream()
                .map(s -> s.replace("mul(", ""))
                .map(s -> s.replace(")", ""))
                .map(s -> s.split(","))
                .map(List::of)
                .map(li -> li.stream().map(Long::parseLong).toList())
                .map(li -> li.getFirst() * li.getLast())
                .reduce(0L, Long::sum);
    }

    record Solution(Long part1, Long part2) {
    }
}
