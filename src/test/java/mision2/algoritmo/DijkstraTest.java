package mision2.algoritmo;

import app.EntradaInvalidaException;
import mision2.modelo.DijkstraResult;
import mision2.modelo.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraTest {

    private static final String SAMPLE_INPUT = """
            3
            2 1 0 1
            0 1 100

            3 3 2 0
            0 1 100
            0 2 200
            1 2 50

            2 0 0 1
            """;

    @Test
    void sampleInput_matchesExpectedOutputExactly() {
        String output =
                new DijkstraRunner().runToText(SAMPLE_INPUT);

        assertEquals(
                "Case #1: 100\n"
                        + "Case #2: 150\n"
                        + "Case #3: Nina is very sad\n",
                output
        );
    }

    @Test
    void directConnection_returnsCost() {
        UndirectedGraph graph =
                new UndirectedGraph(2);

        graph.addEdge(0, 1, 100);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        1
                );

        assertTrue(result.isReachable());
        assertEquals(100L, result.getCost());
    }

    @Test
    void cheaperTwoEdgePath_isSelected() {
        UndirectedGraph graph =
                new UndirectedGraph(3);

        graph.addEdge(0, 1, 100);
        graph.addEdge(0, 2, 200);
        graph.addEdge(1, 2, 50);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        2,
                        0
                );

        assertTrue(result.isReachable());
        assertEquals(150L, result.getCost());
    }

    @Test
    void noConnection_isUnreachable() {
        UndirectedGraph graph =
                new UndirectedGraph(2);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        1
                );

        assertFalse(result.isReachable());
    }

    @Test
    void sourceEqualsDestination_returnsZero() {
        UndirectedGraph graph =
                new UndirectedGraph(5);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        3,
                        3
                );

        assertTrue(result.isReachable());
        assertEquals(0L, result.getCost());
    }

    @Test
    void singleNode_returnsZero() {
        UndirectedGraph graph =
                new UndirectedGraph(1);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        0
                );

        assertTrue(result.isReachable());
        assertEquals(0L, result.getCost());
    }

    @Test
    void duplicatedEdges_usesCheapest() {
        UndirectedGraph graph =
                new UndirectedGraph(2);

        graph.addEdge(0, 1, 100);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 1, 50);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        1
                );

        assertTrue(result.isReachable());
        assertEquals(5L, result.getCost());
    }

    @Test
    void selfLoop_doesNotAffectResult() {
        UndirectedGraph graph =
                new UndirectedGraph(3);

        graph.addEdge(0, 0, 999);
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 10);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        2
                );

        assertTrue(result.isReachable());
        assertEquals(20L, result.getCost());
    }

    @Test
    void differentComponent_isUnreachable() {
        UndirectedGraph graph =
                new UndirectedGraph(4);

        graph.addEdge(0, 1, 5);
        graph.addEdge(2, 3, 5);

        DijkstraResult result =
                Dijkstra.findShortestPath(
                        graph,
                        0,
                        2
                );

        assertFalse(result.isReachable());
    }

    @Test
    void rejectsInvalidN() {
        assertThrows(
                EntradaInvalidaException.class,
                () -> new UndirectedGraph(0)
        );
    }

    @Test
    void rejectsNodeOutOfRange() {
        assertThrows(
                EntradaInvalidaException.class,
                () -> new UndirectedGraph(3)
                        .addEdge(0, 5, 10)
        );
    }

    @Test
    void rejectsNullGraph() {
        assertThrows(
                EntradaInvalidaException.class,
                () -> Dijkstra.findShortestPath(
                        null,
                        0,
                        1
                )
        );
    }

    @Test
    void rejectsSourceOutOfRange() {
        assertThrows(
                EntradaInvalidaException.class,
                () -> Dijkstra.findShortestPath(
                        new UndirectedGraph(2),
                        5,
                        1
                )
        );
    }
}