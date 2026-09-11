package mision3.algoritmo;

import app.EntradaInvalidaException;
import mision3.modelo.ChurunResult;
import mision3.modelo.DirectedGraph;
import org.junit.jupiter.api.Test;
import mision3.modelo.FloydWarshallResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChurunAlgorithmsTest {

    private static final String SAMPLE_INPUT = """
            5
            
            3 3 0 2
            0 1 5
            1 2 7
            0 2 3
            
            4 5 0 3
            0 1 1
            1 2 1
            2 1 1
            2 3 1
            0 3 2
            
            4 2 0 3
            0 1 10
            2 3 5
            
            3 3 0 2
            0 1 -5
            1 2 -3
            0 2 -20
            
            5 5 0 4
            0 1 10
            1 4 10
            0 2 1
            2 3 5
            3 2 5
            """;

    @Test
    void sampleInput_matchesExpectedOutputExactly() {

        String output =
                new ChurunRunner()
                        .runToText(SAMPLE_INPUT);

        assertEquals(
                "Case #1: 12\n"
                        + "Case #2: Infinite churun!\n"
                        + "Case #3: Limon blocked the way\n"
                        + "Case #4: -8\n"
                        + "Case #5: 20\n",
                output
        );
    }

    @Test
    void finitePath_bothAlgorithmsAgree() {

        DirectedGraph graph =
                new DirectedGraph(4);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 30);
        graph.addEdge(0, 3, 15);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        3
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        3
                );

        assertTrue(floyd.isFinite());
        assertTrue(bellman.isFinite());

        assertEquals(
                60L,
                floyd.getMaxChurun()
        );

        assertEquals(
                floyd.getMaxChurun(),
                bellman.getMaxChurun()
        );
    }

    @Test
    void unreachableDestination_bothReturnBlocked() {

        DirectedGraph graph =
                new DirectedGraph(4);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        3
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        3
                );

        assertTrue(floyd.isBlocked());
        assertTrue(bellman.isBlocked());
    }

    @Test
    void positiveCycleLeadingToDestination_isInfinite() {

        DirectedGraph graph =
                new DirectedGraph(4);

        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 10);
        graph.addEdge(2, 1, 5);
        graph.addEdge(2, 3, 10);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        3
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        3
                );

        assertTrue(floyd.isInfinite());
        assertTrue(bellman.isInfinite());
    }

    @Test
    void positiveCycleNotLeadingToDestination_isFinite() {

        DirectedGraph graph =
                new DirectedGraph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 4, 20);

        graph.addEdge(0, 2, 5);
        graph.addEdge(2, 3, 10);
        graph.addEdge(3, 2, 10);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        4
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        4
                );

        assertTrue(floyd.isFinite());
        assertTrue(bellman.isFinite());

        assertEquals(
                30L,
                floyd.getMaxChurun()
        );

        assertEquals(
                30L,
                bellman.getMaxChurun()
        );
    }

    @Test
    void unreachablePositiveCycle_isFinite() {

        DirectedGraph graph =
                new DirectedGraph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 4, 10);

        graph.addEdge(2, 3, 50);
        graph.addEdge(3, 2, 50);
        graph.addEdge(3, 4, 10);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        4
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        4
                );

        assertTrue(floyd.isFinite());
        assertTrue(bellman.isFinite());

        assertEquals(
                20L,
                floyd.getMaxChurun()
        );

        assertEquals(
                20L,
                bellman.getMaxChurun()
        );
    }

    @Test
    void sourceEqualsDestination_withoutPositiveCycle_returnsZero() {

        DirectedGraph graph =
                new DirectedGraph(3);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 10);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        0
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        0
                );

        assertTrue(floyd.isFinite());
        assertTrue(bellman.isFinite());

        assertEquals(
                0L,
                floyd.getMaxChurun()
        );

        assertEquals(
                0L,
                bellman.getMaxChurun()
        );
    }

    @Test
    void duplicatedEdges_useBestWeight() {

        DirectedGraph graph =
                new DirectedGraph(2);

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 1, 20);
        graph.addEdge(0, 1, 10);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        1
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        1
                );

        assertEquals(
                20L,
                floyd.getMaxChurun()
        );

        assertEquals(
                20L,
                bellman.getMaxChurun()
        );
    }

    @Test
    void negativeWeights_workCorrectly() {

        DirectedGraph graph =
                new DirectedGraph(3);

        graph.addEdge(0, 1, -10);
        graph.addEdge(1, 2, -20);
        graph.addEdge(0, 2, -50);

        ChurunResult floyd =
                FloydWarshall.findMaximum(
                        graph,
                        0,
                        2
                );

        ChurunResult bellman =
                BellmanFord.findMaximum(
                        graph,
                        0,
                        2
                );

        assertTrue(floyd.isFinite());
        assertTrue(bellman.isFinite());

        assertEquals(
                -30L,
                floyd.getMaxChurun()
        );

        assertEquals(
                -30L,
                bellman.getMaxChurun()
        );
    }

    @Test
    void graphRejectsInvalidNodeCount() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new DirectedGraph(0)
        );
    }

    @Test
    void graphRejectsNodeOutOfRange() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new DirectedGraph(3)
                        .addEdge(
                                0,
                                5,
                                10
                        )
        );
    }

    @Test
    void graphRejectsWeightOutOfRange() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new DirectedGraph(3)
                        .addEdge(
                                0,
                                1,
                                1001
                        )
        );
    }

    @Test
    void floydRejectsNullGraph() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> FloydWarshall.findMaximum(
                        null,
                        0,
                        1
                )
        );
    }

    @Test
    void bellmanRejectsNullGraph() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> BellmanFord.findMaximum(
                        null,
                        0,
                        1
                )
        );
    }

    @Test
    void floydWarshall_keepsFinalMatrix() {

        DirectedGraph graph =
                new DirectedGraph(3);

        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(0, 2, 3);

        FloydWarshallResult result =
                FloydWarshall.solve(
                        graph,
                        0,
                        2
                );

        long[][] matrix =
                result.getMatrix();

        assertEquals(
                5L,
                matrix[0][1]
        );

        assertEquals(
                12L,
                matrix[0][2]
        );

        assertEquals(
                7L,
                matrix[1][2]
        );

        assertTrue(
                result.getResult().isFinite()
        );

        assertEquals(
                12L,
                result.getResult().getMaxChurun()
        );
    }

    @Test
    void floydWarshall_marksUnreachableNodes() {

        DirectedGraph graph =
                new DirectedGraph(3);

        graph.addEdge(0, 1, 5);

        FloydWarshallResult result =
                FloydWarshall.solve(
                        graph,
                        0,
                        2
                );

        long[][] matrix =
                result.getMatrix();

        assertEquals(
                FloydWarshall.NEGATIVE_INFINITY,
                matrix[0][2]
        );

        assertEquals(
                FloydWarshall.NEGATIVE_INFINITY,
                matrix[1][2]
        );

        assertTrue(
                result.getResult().isBlocked()
        );
    }
}