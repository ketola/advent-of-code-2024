package ketola.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * https://adventofcode.com/2024/day/4
 */
public class AOC4 {

    public static final String INPUT_FILE = "/aoc_4.txt";

    public static void main(String[] args) throws Exception {
        new AOC4().solve();
    }

    public Solution solve() throws Exception {
        char[][] allLines = Files.readAllLines(Paths.get(AOC2.class.getResource(INPUT_FILE).toURI()))
                .stream()
                .map(line -> line.toCharArray())
                .toList().toArray(new char[0][]);

        long part1 = solvePart1(allLines);
        System.out.println("Part 1: Total is: " + part1);

        long part2 = solvePart2(allLines);
        System.out.println("Part 2: Total is: " + part2);

        return new Solution(part1, part2);
    }

    private static long solvePart1(char[][] allLines) {
        long count = 0;
        for (int i = 0; i < allLines.length; i++) {
            for (int j = 0; j < allLines[i].length; j++) {
                count = count + getStrings(i, j, allLines).stream()
                        .filter(s -> s.equals("XMAS") || s.equals("SAMX"))
                        .count();
            }
        }
        return count;
    }

    private static long solvePart2(char[][] allLines) {
        long count = 0;
        for (int i = 0; i < allLines.length; i++) {
            for (int j = 0; j < allLines[i].length; j++) {
                 count += getIntersectingLines(i, j, allLines).stream()
                        .allMatch(s -> s.equals("MAS") || s.equals("SAM"))? 1 : 0;
            }
        }
        return count;
    }

    private static List<String> getStrings(int i, int j, char[][] allLines) {
        return List.of(
                getHorizontalString(i, j, allLines),
                getVerticalString(i, j, allLines),
                getDiagonalRightString(i, j, allLines),
                getDiagonalLeftString(i, j, allLines)
        );
    }

    private static char getChar(int i, int j, char[][] allLines) {
        try {
            return allLines[i][j];
        } catch (IndexOutOfBoundsException e) {
            return ' ';
        }
    }

    private static String getHorizontalString(int i, int j, char[][] allLines) {
        return new String(new char[]{getChar(i, j, allLines), getChar(i, j + 1, allLines), getChar(i, j + 2, allLines), getChar(i, j + 3, allLines)});
    }

    private static String getVerticalString(int i, int j, char[][] allLines) {
        return new String(new char[]{getChar(i, j, allLines), getChar(i + 1, j, allLines), getChar(i + 2, j, allLines), getChar(i + 3, j, allLines)});
    }

    private static String getDiagonalRightString(int i, int j, char[][] allLines) {
        return new String(new char[]{getChar(i, j, allLines), getChar(i + 1, j + 1, allLines), getChar(i + 2, j + 2, allLines), getChar(i + 3, j + 3, allLines)});
    }

    private static String getDiagonalLeftString(int i, int j, char[][] allLines) {
        return new String(new char[]{getChar(i, j, allLines), getChar(i + 1, j - 1, allLines), getChar(i + 2, j - 2, allLines), getChar(i + 3, j - 3, allLines)});
    }

    private static List<String> getIntersectingLines(int i, int j, char[][] allLines) {
        String intersectingLine1 = new String(new char[]{getChar(i, j, allLines), getChar(i + 1, j + 1, allLines), getChar(i + 2, j + 2, allLines)});
        String intersectingLine2 = new String(new char[]{getChar(i, j + 2, allLines), getChar(i + 1, j + 1, allLines), getChar(i + 2, j, allLines)});

        return List.of(intersectingLine1, intersectingLine2);
    }

    record Solution(Long part1, Long part2) {
    }
}
