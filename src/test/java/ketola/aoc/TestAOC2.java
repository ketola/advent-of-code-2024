package ketola.aoc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAOC2 {

    @Test
    void testSolve() throws Exception {
        /*
        Correct solution:
        Part 1: Number of safe reports: 407
        Part 2: Number of safe reports: 459
         */
        AOC2.Solution solution = new AOC2().solve();
        assertEquals(407, solution.part1());
        assertEquals(459, solution.part2());
    }
}
