package ketola.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * https://adventofcode.com/2024/day/2
 */
public class AOC2 {

    public static final String INPUT_FILE = "/aoc_2.txt";
    public static final String SEPARATOR = " ";

    public static void main(String[] args) throws Exception {
        new AOC2().solve();
    }

    public Solution solve() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get(AOC2.class.getResource(INPUT_FILE).toURI()));

        List<List<Integer>> levelsList = allLines.stream()
                .map(l -> l.split(SEPARATOR))
                .map(List::of)
                .map(li -> li.stream().map(Integer::parseInt).toList())
                .toList();

        long safeReports = levelsList.stream()
                .filter(AOC2::isSafe)
                .count();
        System.out.println("Part 1: Number of safe reports is : " + safeReports);

        long safeReportsWithDampener = levelsList.stream()
                .filter(AOC2::isSafeWithDampener)
                .count();
        System.out.println("Part 2: Number of dampened safe reports is : " + safeReportsWithDampener);

        return new Solution(safeReports, safeReportsWithDampener);
    }

    /**
     * Tries to remove one level at a time and check if the remaining are safe.
     *
     * @param levels
     * @return
     */
    private static boolean isSafeWithDampener(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> dampened = new ArrayList<>(levels);
            dampened.remove(i);
            boolean isSafe = isSafe(dampened);
            if (isSafe) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSafe(List<Integer> levels) {
        Integer previous = null;
        Boolean increasing = null;

        for (Integer level : levels) {
            if (previous != null) {
                if (Math.abs(previous - level) > 3 || previous - level == 0) {
                    return false;
                }

                if (increasing != null && increasing != previous < level) {
                    return false;
                }

                increasing = previous < level;
            }
            previous = level;
        }

        return true;
    }

    record Solution(Long part1, Long part2) {
    }
}
