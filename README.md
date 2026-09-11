# The Feline Graph Chronicles

The Feline Graph Chronicles is a Java application with a graphical user interface that solves and visualizes the four graph missions defined in the project statement.

The project implements the following graph algorithms from scratch:

- Breadth-First Search (BFS)
- Depth-First Search (DFS)
- Dijkstra's Algorithm
- Floyd-Warshall Algorithm
- Bellman-Ford Algorithm
- Kruskal's Algorithm
- Union-Find as the supporting data structure for Kruskal

No external graph library is used for the algorithmic core.

---

## Team Members and Responsibilities

### Samuel Tabares — Person 1

Responsible for **Mission 1: Rescue Nina**.

Main contributions:

- Breadth-First Search (BFS).
- Depth-First Search (DFS).
- Iterative DFS using an explicit stack.
- Grid input parsing and validation.
- BFS and DFS path reconstruction.
- Grid visualization.
- Bomb visualization.
- BFS and DFS route visualization.
- Handling unreachable cases.
- Automated tests for Mission 1.

---

### Miguel Angel Quintero — Person 2

Responsible for **Mission 2 and Mission 4**.

#### Mission 2 — Dijkstra

Main contributions:

- Dijkstra's shortest-path algorithm.
- Use of Java's `PriorityQueue`.
- Undirected weighted graph representation.
- Input parsing and validation.
- Shortest-path reconstruction.
- Graph visualization.
- Shortest-path highlighting.
- Handling unreachable destinations.
- Automated tests for Mission 2.

#### Mission 4 — Reconnect Network

Main contributions:

- Kruskal's Minimum Spanning Tree algorithm.
- Custom Union-Find / Disjoint Set structure.
- Path compression.
- Union by size.
- Input parsing and validation.
- Detection of disconnected graphs.
- Minimum Spanning Tree visualization.
- Highlighting selected and discarded edges.
- Automated tests for Mission 4.

---

### Ana Sofia Henao — Person 3

Responsible for **Mission 3: Churun Stash**.

Main contributions:

- Floyd-Warshall adapted to maximize churun.
- Bellman-Ford adapted to maximize churun.
- Cross-checking between Floyd-Warshall and Bellman-Ford.
- Positive-cycle detection.
- Detection of unbounded maximum-churun paths.
- Floyd-Warshall matrix generation.
- Floyd-Warshall matrix visualization.
- Maximum-route reconstruction and highlighting.
- Positive-cycle reconstruction and highlighting.
- Input parsing and validation.
- Automated tests for Mission 3.

---

## Requirements

The project requires:

- **Java 17 or later**
- **Maven**

The graphical interface is implemented using standard Java technologies:

- Java Swing
- Java AWT

No external graph libraries are required.

---

## How to Build and Run

The project uses Maven.

### Build the project

From the root directory of the project, run:

```bash
mvn package
```

This command compiles the project, executes the automated tests, and packages the application.

### Run the application

After packaging the project, run:

```bash
java -jar target/feline-graph-chronicles.jar
```

### Development mode

The application can also be executed during development with:

```bash
mvn compile exec:java
```

### Run only the automated tests

```bash
mvn test
```

---

## Project Structure

The project is organized by mission.

Each mission separates:

- Algorithmic logic.
- Data models.
- Graphical interface.

The shared application components are located in the `app` package.

```text
FelineGraphChronicles/
├── pom.xml
├── README.md
├── AI_USAGE.md
│
└── src/
    ├── main/
    │   └── java/
    │       │
    │       ├── app/
    │       │   ├── ui/
    │       │   │   ├── RoundedButton.java
    │       │   │   └── RoundedPanel.java
    │       │   │
    │       │   ├── vista/
    │       │   │   └── HomePanel.java
    │       │   │
    │       │   ├── Arista.java
    │       │   ├── EntradaInvalidaException.java
    │       │   ├── Main.java
    │       │   ├── MainFrame.java
    │       │   └── Theme.java
    │       │
    │       ├── mision1/
    │       │   ├── algoritmo/
    │       │   │   ├── InputParser.java
    │       │   │   ├── PathFinder.java
    │       │   │   └── SearchRunner.java
    │       │   │
    │       │   ├── modelo/
    │       │   │   ├── GridCase.java
    │       │   │   ├── Point.java
    │       │   │   └── SearchResult.java
    │       │   │
    │       │   └── vista/
    │       │       └── Mission1Panel.java
    │       │
    │       ├── mision2/
    │       │   ├── algoritmo/
    │       │   │   ├── Dijkstra.java
    │       │   │   ├── DijkstraRunner.java
    │       │   │   └── InputParser.java
    │       │   │
    │       │   ├── modelo/
    │       │   │   ├── DijkstraResult.java
    │       │   │   ├── GraphCase.java
    │       │   │   └── UndirectedGraph.java
    │       │   │
    │       │   └── vista/
    │       │       └── Mission2Panel.java
    │       │
    │       ├── mision3/
    │       │   ├── algoritmo/
    │       │   │   ├── BellmanFord.java
    │       │   │   ├── ChurunRunner.java
    │       │   │   ├── FloydWarshall.java
    │       │   │   └── InputParser.java
    │       │   │
    │       │   ├── modelo/
    │       │   │   ├── BellmanFordResult.java
    │       │   │   ├── ChurunResult.java
    │       │   │   ├── DirectedGraph.java
    │       │   │   ├── FloydWarshallResult.java
    │       │   │   └── GraphCase.java
    │       │   │
    │       │   └── vista/
    │       │       └── Mission3Panel.java
    │       │
    │       └── mision4/
    │           ├── algoritmo/
    │           │   ├── InputParser.java
    │           │   ├── Kruskal.java
    │           │   ├── MSTRunner.java
    │           │   └── UnionFind.java
    │           │
    │           ├── modelo/
    │           │   ├── GraphCase.java
    │           │   └── MSTResult.java
    │           │
    │           └── vista/
    │               └── Mission4Panel.java
    │
    └── test/
        └── java/
            ├── mision1/algoritmo/
            │   └── PathFinderTest.java
            │
            ├── mision2/algoritmo/
            │   └── DijkstraTest.java
            │
            ├── mision3/algoritmo/
            │   └── ChurunAlgorithmsTest.java
            │
            └── mision4/algoritmo/
                └── KruskalTest.java
```

---

## Architecture

Each mission is divided into three main packages:

### `algoritmo`

Contains:

- Input parsers.
- Mission runners.
- Graph algorithms.

### `modelo`

Contains:

- Graph representations.
- Test case models.
- Result models.
- Supporting data structures.

### `vista`

Contains:

- The Swing panel for the corresponding mission.
- Graphical visualization logic.

### `app`

Contains components shared by the complete application, including:

- `Main`
- `MainFrame`
- `Theme`
- `Arista`
- `EntradaInvalidaException`
- Reusable graphical components

This organization keeps the algorithmic core independent from the graphical user interface.

---

## Algorithm and Data Structure Decisions

### Mission 1 — BFS and DFS

Mission 1 works on a grid containing Nina, a starting position, and bomb cells.

BFS uses a queue to explore the grid level by level. This makes BFS appropriate for finding the shortest route in an unweighted grid.

DFS uses an **explicit stack** instead of recursion.

The iterative implementation was selected because the mission allows grids containing up to one million cells. Using an explicit stack avoids relying on the limited Java call stack.

The required deterministic DFS exploration order is:

```text
Up
Down
Left
Right
```

Because a stack follows LIFO order, neighbors are pushed in the appropriate reverse order so that the effective exploration follows the required sequence.

Both BFS and DFS reconstruct their routes so that they can also be displayed by the graphical interface.

---

### Mission 2 — Dijkstra

Mission 2 uses Dijkstra's algorithm to find the minimum-cost route in an undirected weighted graph.

The implementation uses:

```java
PriorityQueue
```

The graph is represented with adjacency lists.

The priority queue allows the algorithm to efficiently select the next node with the smallest known distance.

The implementation also ignores obsolete entries from the priority queue instead of requiring an explicit decrease-key operation.

Accumulated distances are stored using:

```java
long
```

The implementation supports:

- Repeated edges.
- Self-loops.
- Zero-weight edges.
- Unreachable destinations.
- Source equal to destination.

---

### Mission 3 — Floyd-Warshall

Floyd-Warshall was adapted to calculate the **maximum amount of churun** instead of the traditional minimum distance.

The algorithm calculates values between every pair of nodes using a matrix.

The diagonal is initialized with:

```text
d[i][i] = 0
```

A special internal value represents pairs of nodes for which no route exists.

When multiple directed edges connect the same pair of nodes, the edge with the maximum weight is kept.

After the main Floyd-Warshall computation, the algorithm detects positive cycles.

A pair of nodes has an unbounded maximum when the first node can reach a positive cycle and that positive cycle can reach the second node.

The graphical matrix represents:

```text
-    No route exists
inf  Maximum churun is unbounded
```

Finite values are displayed as regular numbers.

---

### Mission 3 — Bellman-Ford

Bellman-Ford was also adapted to **maximize churun**.

The algorithm performs repeated relaxation over all directed edges.

After the normal relaxation phase, an additional check detects nodes that can still be improved because of a reachable positive cycle.

The effect of those cycles is propagated through the graph to determine whether the destination is affected.

Bellman-Ford also stores predecessor information that is used for visualization.

When the result is finite, the application can reconstruct and highlight the maximum route.

When the result is infinite, the application can identify and highlight a positive cycle.

Floyd-Warshall and Bellman-Ford are both executed for every Mission 3 test case.

Their final results are compared.

If the algorithms disagree, the application reports a warning.

---

### Mission 4 — Kruskal and Union-Find

Mission 4 uses Kruskal's algorithm to find the Minimum Spanning Tree.

Kruskal sorts the cables by cost and processes them from lowest to highest.

A custom Union-Find structure determines whether two intersections already belong to the same connected component.

The Union-Find implementation includes:

- Path compression.
- Union by size.

This allows Kruskal to efficiently avoid cycles while constructing the MST.

Accumulated costs are stored using:

```java
long
```

The selected MST edges are stored in the result so that the GUI can highlight them.

---

## Node Indexing

Each mission respects the node numbering defined by its corresponding input format.

### Mission 2

Nodes are numbered:

```text
0 ... N - 1
```

### Mission 3

Nodes are numbered:

```text
0 ... N - 1
```

### Mission 4

Intersections are numbered:

```text
1 ... N
```

For this reason, the missions maintain their own graph models and validation rules where necessary.

---

## Graphical User Interface

The graphical interface was developed using **Java Swing and AWT**.

The application contains a main window that provides access to the four missions.

Each mission provides:

- Editable input area.
- `Load Sample` button.
- `Clear` button.
- `Solve mission` button.
- Results area.
- Mission-specific visualization.
- Readable error messages.
- Case selector when multiple test cases are entered.

The `Load Sample` button loads the official sample input for the corresponding mission.

Users can also write or paste any valid custom input directly into the input area.

---

## Visualization

The project does not use an external graph visualization library.

The grids, graphs, arrows, routes, cycles, nodes, weights, and MST edges are drawn directly using **Java Swing/AWT**.

Visualization limits are applied so that large valid inputs can still be processed without requiring the GUI to draw excessively large graphs.

---

### Mission 1 Visualization

The Mission 1 visualization displays:

- Grid.
- Starting position.
- Nina's position.
- Bomb cells.
- BFS route.
- DFS route.

The user can select:

- Both routes.
- BFS only.
- DFS only.

The grid is visualized when its size is at most:

```text
50 x 50
```

If the grid is larger, the algorithms still execute and the numerical result is displayed, but the visualization is omitted.

---

### Mission 2 Visualization

The Mission 2 visualization displays:

- Nodes.
- Edges.
- Edge weights.
- Source.
- Destination.
- Shortest path found by Dijkstra.

The shortest path is visually highlighted.

The graph is visualized when it contains at most:

```text
60 nodes
```

If the destination cannot be reached, the application displays:

```text
Nina is very sad
```

---

### Mission 3 Visualization

The Mission 3 visualization displays:

- Directed graph.
- Edge weights.
- Positive and negative weights.
- Source.
- Destination.
- Maximum route for finite cases.
- Positive cycle for infinite cases.
- Floyd-Warshall matrix.

For a finite result, the maximum route is highlighted.

For an infinite result, the positive cycle responsible for the unbounded result is highlighted.

The graph is visualized when it contains at most:

```text
60 nodes
```

The Floyd-Warshall matrix is displayed for graphs containing up to:

```text
100 nodes
```

The matrix supports horizontal and vertical scrolling.

Its cells use:

```text
-    No route exists
inf  Maximum value is unbounded
```

The matrix continues to be available for graphs between 61 and 100 nodes even though the graph drawing itself is omitted.

---

### Mission 4 Visualization

The Mission 4 visualization displays:

- Intersections.
- Cables.
- Cable costs.
- MST edges.
- Discarded edges.

The edges selected by Kruskal are visually highlighted.

Discarded edges remain visible with a different visual style.

The graph is visualized when both conditions are satisfied:

```text
N <= 100
C <= 300
```

If either limit is exceeded, the algorithm still executes and produces the result, but the graph visualization is omitted.

---

## Required Output Messages

The application preserves the required special output messages.

### Mission 1

When Nina cannot be reached:

```text
Nina is unreachable
```

### Mission 2

When the destination cannot be reached:

```text
Nina is very sad
```

### Mission 3

When no route exists:

```text
Limon blocked the way
```

When a relevant positive cycle makes the maximum churun unbounded:

```text
Infinite churun!
```

### Mission 4

When it is impossible to connect all intersections:

```text
Limon cut too many cables
```

---

## Input and Error Handling

Input is parsed as whitespace-separated tokens.

The parsers therefore tolerate:

- Extra spaces.
- Extra blank lines.
- Trailing spaces.

The parsers also validate mission-specific constraints before executing the algorithms.

Malformed input generates a readable error message.

The graphical interface catches these errors and displays them in a dialog instead of exposing a raw stack trace or terminating the application.

For example:

```text
Se esperaba un numero entero para numero de filas
```

---

## Automated Tests

Automated tests are included for all six required graph algorithms:

- BFS
- DFS
- Dijkstra
- Floyd-Warshall
- Bellman-Ford
- Kruskal

The test suite includes the official sample cases and additional edge cases.

The final test execution produced:

```text
50 tests passed
50 tests total
0 failed
```

The test process finished successfully with:

```text
Process finished with exit code 0
```

Tests can be executed with:

```bash
mvn test
```

---

## Known Limitations

The main limitations of the application are related to visualization rather than algorithm execution.

Large valid inputs are still processed by the algorithms, but graphical visualization is omitted when the corresponding mission exceeds its visualization threshold.

The visualization thresholds are:

```text
Mission 1: 50 x 50 grid
Mission 2: 60 nodes
Mission 3: 60 nodes for graph visualization
Mission 3: 100 nodes for Floyd-Warshall matrix
Mission 4: 100 intersections and 300 cables
```

The graph visualization uses a simple layout designed primarily for clarity in small and medium-sized examples.

Graphs with many edges may therefore contain visual edge crossings.

These limitations do not change the numerical result produced by the algorithms.

---

## Drawing and Graph Libraries

No external graph or graph-visualization library is used.

The project uses Java's standard:

```text
Swing
AWT
Graphics2D
```

for the graphical interface and visualization.

The algorithmic core does not depend on Swing or AWT.

All required graph algorithms were implemented directly in the project.

---

## AI Usage

Artificial intelligence tools were used as support during development, debugging, testing, interface improvement, and documentation.

The complete disclosure is available in:

```text
AI_USAGE.md
```

That document includes:

- AI tools used.
- Parts of the project where AI was used.
- Relevant and decisive prompts.
- Incorrect or suboptimal AI suggestions.
- How those suggestions were identified and corrected.
- Learning obtained by each team member.