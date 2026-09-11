package mision4.modelo;

import app.Arista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MSTResult {

    private final boolean connected;
    private final long cost;
    private final List<Arista> selectedEdges;

    private MSTResult(
            boolean connected,
            long cost,
            List<Arista> selectedEdges) {

        this.connected = connected;
        this.cost = cost;

        this.selectedEdges =
                Collections.unmodifiableList(
                        new ArrayList<>(selectedEdges)
                );
    }

    public static MSTResult disconnected(
            List<Arista> selectedEdges) {

        return new MSTResult(
                false,
                0L,
                selectedEdges
        );
    }

    public static MSTResult connected(
            long cost,
            List<Arista> selectedEdges) {

        return new MSTResult(
                true,
                cost,
                selectedEdges
        );
    }

    public boolean isConnected() {
        return connected;
    }

    public long getCost() {
        return cost;
    }

    public List<Arista> getSelectedEdges() {
        return selectedEdges;
    }
}