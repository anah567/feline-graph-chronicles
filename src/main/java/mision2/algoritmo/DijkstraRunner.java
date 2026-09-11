package mision2.algoritmo;

import mision2.modelo.DijkstraResult;
import mision2.modelo.GraphCase;

import java.util.ArrayList;
import java.util.List;

public final class DijkstraRunner {

    public static final class CaseOutcome {
        public final GraphCase testCase;
        public final DijkstraResult result;
        public final String line;

        CaseOutcome(
                GraphCase testCase,
                DijkstraResult result,
                String line) {

            this.testCase = testCase;
            this.result = result;
            this.line = line;
        }
    }

    public List<CaseOutcome> run(String rawInput) {
        List<GraphCase> cases =
                new InputParser(rawInput).parseAll();

        List<CaseOutcome> outcomes = new ArrayList<>();

        int caseNumber = 1;

        for (GraphCase testCase : cases) {

            DijkstraResult result =
                    Dijkstra.findShortestPath(
                            testCase.getGraph(),
                            testCase.getSource(),
                            testCase.getDestination()
                    );

            String line;

            if (!result.isReachable()) {
                line = "Case #" + caseNumber
                        + ": Nina is very sad";
            } else {
                line = "Case #" + caseNumber
                        + ": " + result.getCost();
            }

            outcomes.add(
                    new CaseOutcome(
                            testCase,
                            result,
                            line
                    )
            );

            caseNumber++;
        }

        return outcomes;
    }

    public String runToText(String rawInput) {
        StringBuilder output = new StringBuilder();

        for (CaseOutcome outcome : run(rawInput)) {
            output.append(outcome.line).append('\n');
        }

        return output.toString();
    }
}