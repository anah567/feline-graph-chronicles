package mision1.algoritmo;

import mision1.modelo.GridCase;
import mision1.modelo.SearchResult;

import java.util.ArrayList;
import java.util.List;

public final class SearchRunner {

    private final PathFinder solver = new PathFinder();

    public static final class CaseOutcome {
        public final GridCase testCase;
        public final SearchResult result;
        public final String line;

        CaseOutcome(GridCase testCase, SearchResult result, String line) {
            this.testCase = testCase;
            this.result = result;
            this.line = line;
        }
    }

    public List<CaseOutcome> run(String rawInput) {
        List<GridCase> cases = new InputParser(rawInput).parseAll();
        List<CaseOutcome> outcomes = new ArrayList<>();

        int caseNumber = 1;

        for (GridCase testCase : cases) {
            SearchResult result = solver.solve(testCase);

            String line;

            if (!result.isReachable()) {
                line = "Case #" + caseNumber + ": Nina is unreachable";
            } else {
                line = "Case #" + caseNumber
                        + ": BFS " + result.getBfsMoves()
                        + " DFS " + result.getDfsMoves();
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