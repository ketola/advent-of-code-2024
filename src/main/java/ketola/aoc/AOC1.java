package ketola.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://adventofcode.com/2024/day/1
 */
public class AOC1 {

    public static final String INPUT_FILE = "/aoc_1.txt";
    public static final String SEPARATOR = "   ";

    public static void main(String[] args) throws Exception {
        new AOC1().solve();
    }

    public Solution solve() throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get(AOC1.class.getResource(INPUT_FILE).toURI()));

        List<Integer> leftColumn = valuesSorted(allLines, 0);
        List<Integer> rightColumn = valuesSorted(allLines, 1);

        // Part 1:
        List<Integer> distances = IntStream.range(0, leftColumn.size())
                .mapToObj(i -> leftColumn.get(i) - rightColumn.get(i))
                .map(Math::abs)
                .toList();

        Integer sumOfDistances = distances.stream().reduce(0, Integer::sum);
        System.out.println("Part 1: Sum of distances is: " + sumOfDistances);


        // Part 2:
        List<Integer> similarityScores = leftColumn.stream()
                .map(leftColumnValue -> rightColumn.stream()
                        .filter(rightColumnValue -> rightColumnValue.equals(leftColumnValue))
                        .count() * leftColumnValue)
                .map(Long::intValue)
                .toList();
        Integer sumOfSimilarityScores = similarityScores.stream().reduce(0, Integer::sum);

        System.out.println("Part 2: Sum of similarity scores: " + sumOfSimilarityScores);

        return new Solution(sumOfDistances, sumOfSimilarityScores);
    }

    private List<Integer> valuesSorted(List<String> allLines, int column) {
        return allLines.stream()
                .map(line -> line.split(SEPARATOR))
                .map(columns -> columns[column])
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }

    record Solution(Integer part1, Integer part2) {
    }
}
