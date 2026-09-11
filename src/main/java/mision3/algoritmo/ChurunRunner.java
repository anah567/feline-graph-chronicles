package mision3.algoritmo;

import mision3.modelo.ChurunResult;
import mision3.modelo.FloydWarshallResult;
import mision3.modelo.GraphCase;
import mision3.modelo.BellmanFordResult;

import java.util.ArrayList;
import java.util.List;

public final class ChurunRunner {

    public static final class CaseOutcome {

        public final GraphCase testCase;
        public final ChurunResult result;
        public final FloydWarshallResult floydWarshallResult;
        public final BellmanFordResult bellmanFordResult;
        public final String line;
        public final boolean algorithmsMatch;

        CaseOutcome(
                GraphCase testCase,
                ChurunResult result,
                FloydWarshallResult floydWarshallResult,
                BellmanFordResult bellmanFordResult,
                String line,
                boolean algorithmsMatch) {

            this.testCase = testCase;
            this.result = result;
            this.floydWarshallResult =
                    floydWarshallResult;
            this.bellmanFordResult =
                    bellmanFordResult;
            this.line = line;
            this.algorithmsMatch =
                    algorithmsMatch;
        }
    }

    public List<CaseOutcome> run(String rawInput) {

        List<GraphCase> cases =
                new InputParser(rawInput).parseAll();

        List<CaseOutcome> outcomes =
                new ArrayList<>();

        int caseNumber = 1;

        for (GraphCase testCase : cases) {

            FloydWarshallResult floydWarshallResult =
                    FloydWarshall.solve(
                            testCase.getGraph(),
                            testCase.getSource(),
                            testCase.getDestination()
                    );

            ChurunResult floydResult =
                    floydWarshallResult.getResult();

            BellmanFordResult bellmanFordResult =
                    BellmanFord.solve(
                            testCase.getGraph(),
                            testCase.getSource(),
                            testCase.getDestination()
                    );

            ChurunResult bellmanResult =
                    bellmanFordResult.getResult();

            boolean algorithmsMatch =
                    resultsMatch(
                            floydResult,
                            bellmanResult
                    );

            String line =
                    buildOutput(
                            caseNumber,
                            floydResult
                    );

            outcomes.add(
                    new CaseOutcome(
                            testCase,
                            floydResult,
                            floydWarshallResult,
                            bellmanFordResult,
                            line,
                            algorithmsMatch
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

            if (!outcome.algorithmsMatch) {

                output.append(
                        "WARNING: Floyd-Warshall and Bellman-Ford do not match"
                ).append('\n');
            }
        }

        return output.toString();
    }

    private boolean resultsMatch(
            ChurunResult floyd,
            ChurunResult bellman) {

        if (floyd.getStatus()
                != bellman.getStatus()) {

            return false;
        }

        if (floyd.isFinite()
                && floyd.getMaxChurun()
                != bellman.getMaxChurun()) {

            return false;
        }

        return true;
    }

    private String buildOutput(
            int caseNumber,
            ChurunResult result) {

        if (result.isBlocked()) {

            return "Case #"
                    + caseNumber
                    + ": Limon blocked the way";
        }

        if (result.isInfinite()) {

            return "Case #"
                    + caseNumber
                    + ": Infinite churun!";
        }

        return "Case #"
                + caseNumber
                + ": "
                + result.getMaxChurun();
    }
}