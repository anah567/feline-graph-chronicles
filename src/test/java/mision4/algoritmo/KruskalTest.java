package mision4.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision4.modelo.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KruskalTest {

    // Sample oficial del enunciado de la Mision 4.
    private static final String SAMPLE_INPUT = """
            1
            4
            5
            1 2 10
            2 3 20
            3 4 30
            4 1 40
            1 3 15
            """;

    @Test
    void sampleInput_matchesExpectedOutputExactly() {

        String output =
                new MSTRunner()
                        .runToText(SAMPLE_INPUT);

        assertEquals(
                "Case #1: 55\n",
                output
        );
    }

    @Test
    void mstCostIs55() {

        List<Arista> edges = List.of(
                new Arista(1, 2, 10),
                new Arista(2, 3, 20),
                new Arista(3, 4, 30),
                new Arista(4, 1, 40),
                new Arista(1, 3, 15)
        );

        MSTResult result =
                Kruskal.findMST(
                        4,
                        edges
                );

        assertTrue(result.isConnected());

        assertEquals(
                55L,
                result.getCost()
        );

        assertEquals(
                3,
                result.getSelectedEdges().size()
        );
    }

    @Test
    void singleNode_isConnected() {

        MSTResult result =
                Kruskal.findMST(
                        1,
                        List.of()
                );

        assertTrue(result.isConnected());

        assertEquals(
                0L,
                result.getCost()
        );

        assertEquals(
                0,
                result.getSelectedEdges().size()
        );
    }

    @Test
    void noCables_isDisconnected() {

        MSTResult result =
                Kruskal.findMST(
                        3,
                        List.of()
                );

        assertFalse(
                result.isConnected()
        );
    }

    @Test
    void insufficientCables_isDisconnected() {

        List<Arista> edges = List.of(
                new Arista(1, 2, 5),
                new Arista(3, 4, 7)
        );

        MSTResult result =
                Kruskal.findMST(
                        4,
                        edges
                );

        assertFalse(
                result.isConnected()
        );

        assertEquals(
                2,
                result.getSelectedEdges().size()
        );
    }

    @Test
    void duplicatedEdges_usesCheapest() {

        List<Arista> edges = List.of(
                new Arista(1, 2, 100),
                new Arista(1, 2, 5),
                new Arista(2, 3, 10)
        );

        MSTResult result =
                Kruskal.findMST(
                        3,
                        edges
                );

        assertTrue(
                result.isConnected()
        );

        assertEquals(
                15L,
                result.getCost()
        );
    }

    @Test
    void selfLoop_doesNotAffectCost() {

        List<Arista> edges = List.of(
                new Arista(1, 1, 999),
                new Arista(1, 2, 10),
                new Arista(2, 3, 20)
        );

        MSTResult result =
                Kruskal.findMST(
                        3,
                        edges
                );

        assertTrue(
                result.isConnected()
        );

        assertEquals(
                30L,
                result.getCost()
        );
    }

    @Test
    void unionFind_connectsComponents() {

        UnionFind uf =
                new UnionFind(5);

        assertTrue(
                uf.union(0, 1)
        );

        assertFalse(
                uf.union(0, 1)
        );

        assertEquals(
                uf.find(0),
                uf.find(1)
        );

        assertFalse(
                uf.find(0) == uf.find(2)
        );

        uf.union(2, 3);
        uf.union(0, 2);

        assertEquals(
                uf.find(1),
                uf.find(3)
        );
    }

    @Test
    void rejectsInvalidN() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> Kruskal.findMST(
                        0,
                        List.of()
                )
        );
    }

    @Test
    void rejectsNullEdgeList() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> Kruskal.findMST(
                        3,
                        null
                )
        );
    }

    @Test
    void rejectsNodeOutOfRange() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> Kruskal.findMST(
                        3,
                        List.of(
                                new Arista(0, 1, 5)
                        )
                )
        );
    }

    @Test
    void rejectsNegativeWeight() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> Kruskal.findMST(
                        2,
                        List.of(
                                new Arista(1, 2, -1)
                        )
                )
        );
    }

    @Test
    void rejectsWeightAboveLimit() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> Kruskal.findMST(
                        2,
                        List.of(
                                new Arista(
                                        1,
                                        2,
                                        1000001
                                )
                        )
                )
        );
    }
}