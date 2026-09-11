package mision4.algoritmo;

import mision4.modelo.GraphCase;
import mision4.modelo.MSTResult;

import java.util.ArrayList;
import java.util.List;

public final class MSTRunner {

    public static final class CaseOutcome {

        public final GraphCase testCase;
        public final MSTResult result;
        public final String line;

        CaseOutcome(
                GraphCase testCase,
                MSTResult result,
                String line) {

            this.testCase = testCase;
            this.result = result;
            this.line = line;
        }
    }

    public List<CaseOutcome> run(String rawInput) {

        List<GraphCase> cases =
                new InputParser(rawInput).parseAll();

        List<CaseOutcome> outcomes =
                new ArrayList<>();

        int caseNumber = 1;

        for (GraphCase testCase : cases) {

            MSTResult result =
                    Kruskal.findMST(
                            testCase.getN(),
                            testCase.getEdges()
                    );

            String line;

            if (!result.isConnected()) {

                line = "Case #"
                        + caseNumber
                        + ": Limon cut too many cables";

            } else {

                line = "Case #"
                        + caseNumber
                        + ": "
                        + result.getCost();
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

        StringBuilder output =
                new StringBuilder();

        for (CaseOutcome outcome : run(rawInput)) {

            output.append(outcome.line)
                    .append('\n');
        }

        return output.toString();
    }
}