package ketola.aoc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAOC3 {

    @Test
    void testSolve() throws Exception {
        /*
        Correct solution:
        Part 1: Total is: 159833790
        Part 2: Total is: 89349241

         */
        AOC3.Solution solution = new AOC3().solve();
        assertEquals(159833790, solution.part1());
        assertEquals(89349241, solution.part2());
    }
}
