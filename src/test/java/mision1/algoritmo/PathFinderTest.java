package mision1.algoritmo;

import app.EntradaInvalidaException;
import mision1.modelo.GridCase;
import mision1.modelo.SearchResult;
import mision1.modelo.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathFinderTest {

    private static final String SAMPLE_INPUT = """
            10 10
            9
            0 1 2
            1 1 2
            2 2 2 9
            3 2 1 7
            5 3 3 6 9
            6 4 0 1 2 7
            7 3 0 3 8
            8 2 7 9
            9 3 2 3 4
            0 0
            9 9
            0 0
            """;

    @Test
    void sampleInput_matchesExpectedOutputExactly() {
        String output =
                new SearchRunner().runToText(SAMPLE_INPUT);

        assertEquals(
                "Case #1: BFS 18 DFS 32\n",
                output
        );
    }

    @Test
    void startEqualsDestination_returnsZero() {
        GridCase testCase =
                new GridCase(
                        5,
                        5,
                        Set.of(),
                        new Point(2, 2),
                        new Point(2, 2)
                );

        SearchResult result =
                new PathFinder().solve(testCase);

        assertTrue(result.isReachable());
        assertEquals(0, result.getBfsMoves());
        assertEquals(0, result.getDfsMoves());
    }

    @Test
    void destinationWalledOff_isUnreachable() {
        Set<Point> bombs = Set.of(
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 2),
                new Point(2, 1)
        );

        GridCase testCase =
                new GridCase(
                        3,
                        3,
                        bombs,
                        new Point(0, 0),
                        new Point(1, 1)
                );

        SearchResult result =
                new PathFinder().solve(testCase);

        assertFalse(result.isReachable());
    }

    @Test
    void bombAtStart_isUnreachable() {
        GridCase testCase =
                new GridCase(
                        3,
                        3,
                        Set.of(new Point(0, 0)),
                        new Point(0, 0),
                        new Point(2, 2)
                );

        SearchResult result =
                new PathFinder().solve(testCase);

        assertFalse(result.isReachable());
    }

    @Test
    void bombAtDestination_isUnreachable() {
        GridCase testCase =
                new GridCase(
                        3,
                        3,
                        Set.of(new Point(2, 2)),
                        new Point(0, 0),
                        new Point(2, 2)
                );

        SearchResult result =
                new PathFinder().solve(testCase);

        assertFalse(result.isReachable());
    }

    @Test
    void dfsCanBeLongerThanBfs() {
        GridCase testCase =
                new GridCase(
                        4,
                        4,
                        Set.of(),
                        new Point(0, 0),
                        new Point(3, 3)
                );

        SearchResult result =
                new PathFinder().solve(testCase);

        assertTrue(result.isReachable());
        assertEquals(6, result.getBfsMoves());
        assertTrue(
                result.getDfsMoves()
                        >= result.getBfsMoves()
        );
    }

    @Test
    void parserReadsTwoCases() {
        String input = """
                1 1
                0
                0 0 0 0
                2 2
                0
                0 0 1 1
                0 0
                """;

        List<GridCase> cases =
                new InputParser(input).parseAll();

        assertEquals(2, cases.size());
        assertEquals(1, cases.get(0).getRows());
        assertEquals(2, cases.get(1).getRows());
    }

    @Test
    void parserRejectsMalformedInput() {
        String input = "10 abc\n";

        assertThrows(
                EntradaInvalidaException.class,
                () -> new InputParser(input).parseAll()
        );
    }
}