package ketola.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;
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

        // Part 1:
        Integer sumOfDistances = solvePart1(allLines);
        System.out.println("Part 1: Sum of distances is: " + sumOfDistances);

        // Part 2:
        Integer sumOfSimilarityScores = solvePart2(allLines);
        System.out.println("Part 2: Sum of similarity scores: " + sumOfSimilarityScores);

        return new Solution(sumOfDistances, sumOfSimilarityScores);
    }

    private static Integer solvePart1(List<String> allLines) {
        return allLines.stream()
                .map(line -> line.split(SEPARATOR))
                .map(List::of)
                .map(li -> li.stream().map(Integer::parseInt).toList())
                .gather(
                        Gatherers.fold(
                                ListPair::new,
                                (pair, line) -> pair.add(line.getFirst(), line.getLast())
                        ))
                .findFirst()
                .map(ListPair::sort)
                .map(ListPair::toIntPairs)
                .orElseThrow().stream()
                .map(ip -> ip.one() - ip.two())
                .map(Math::abs)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static Integer solvePart2(List<String> allLines) {
        return allLines.stream()
                .map(line -> line.split(SEPARATOR))
                .map(List::of)
                .map(li -> li.stream().map(Integer::parseInt).toList())
                .gather(
                        Gatherers.fold(
                                ListPair::new,
                                (pair, line) -> pair.add(line.getFirst(), line.getLast())
                        ))
                .findFirst()
                .map(ss -> ss.one.stream().map(leftColumnValue -> ss.two.stream()
                                .filter(rightColumnValue -> rightColumnValue.equals(leftColumnValue))
                                .count() * leftColumnValue).mapToInt(Long::intValue)
                        .sum()).orElseThrow();
    }

    public record IntPair(Integer one, Integer two) {
    }

    public record ListPair(List<Integer> one, List<Integer> two) {
        public ListPair() {
            this(new ArrayList<>(), new ArrayList<>());
        }

        ListPair add(Integer one, Integer two) {
            this.one.add(one);
            this.two.add(two);
            return this;
        }

        ListPair sort() {
            this.one.sort(Integer::compareTo);
            this.two.sort(Integer::compareTo);
            return this;
        }

        List<IntPair> toIntPairs() {
            return IntStream.range(0, one.size())
                    .mapToObj(i -> new IntPair(one.get(i), two.get(i)))
                    .toList();
        }
    }

    record Solution(Integer part1, Integer part2) {
    }
}
