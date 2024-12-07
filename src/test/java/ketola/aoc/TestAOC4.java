package ketola.aoc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAOC4 {

    @Test
    void testSolve() throws Exception {
        /*
        Correct solution:
        Part 1: Total is: 2530
        Part 2: Total is: 1921
         */
        
        AOC4.Solution solution = new AOC4().solve();
        assertEquals(2530, solution.part1());
        assertEquals(1921, solution.part2());
    }
}
