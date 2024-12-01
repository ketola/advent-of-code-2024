package ketola.aoc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAOC1 {

    @Test
    void testSolve() throws Exception {
        /*
        Correct solution:
        Part 1: Sum of distances is: 2192892
        Part 2: Sum of similarity scores: 22962826
         */
        AOC1.Solution solution = new AOC1().solve();
        assertEquals(2192892, solution.part1());
        assertEquals(22962826, solution.part2());
    }
}
