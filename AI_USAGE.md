# AI_USAGE.md

This document describes the use of Artificial Intelligence tools during the
development of **The Feline Graph Chronicles**.

AI was used as a support tool for understanding requirements, reviewing code,
detecting errors, generating and reviewing tests, improving the graphical
interface, and documenting the project.

AI-generated suggestions were reviewed and tested before being incorporated
into the final implementation. Some suggestions were incorrect or suboptimal
and had to be corrected manually, as documented below.

---

# Person 1 — Samuel Tabares

## Mission 1 — BFS and DFS

### AI Tool Used

**Claude (Anthropic)** was used as a support tool during the development of
Mission 1 and parts of the general project structure.

It was mainly used for:

- Reviewing the Mission 1 requirements.
- Implementing and reviewing BFS and DFS.
- Checking the deterministic DFS exploration order.
- Reviewing the input parser.
- Creating and reviewing automated tests.
- Reviewing the Swing grid visualization.
- Checking the official sample input and output.
- Detecting edge cases and malformed-input situations.
- Supporting the organization of the general project structure.

---

### Decisive Prompts

#### Prompt 1

> "Implement BFS and DFS for Mission 1 following the project statement
> exactly. The grid contains bombs, DFS must be iterative, and the exploration
> order must be Up, Down, Left, Right. The output must match the official
> example exactly."

This prompt was important because Mission 1 requires deterministic DFS
behavior.

A different neighbor order can produce a different traversal even when the
algorithm still reaches the destination.

---

#### Prompt 2

> "Verify the BFS and DFS results using the official sample from the project
> statement before considering the implementation correct."

This verification was important because the first DFS implementation compiled
and executed successfully but did not reproduce the expected DFS result.

Testing against the official example exposed the problem.

---

#### Prompt 3

> "Organize the project so the algorithms are independent from the GUI. Keep
> algorithm, model, and view responsibilities separated, and do not use Swing
> inside the algorithmic package."

This prompt helped establish the separation between:

```text
algoritmo
modelo
vista
```

and prevented the graph algorithms from depending directly on the graphical
interface.

---

### Incorrect or Suboptimal AI Outputs and Corrections

#### 1. DFS produced an incorrect traversal

An initial iterative DFS implementation produced:

```text
Case #1: BFS 18 DFS 28
```

instead of the expected:

```text
Case #1: BFS 18 DFS 32
```

The implementation was reviewed to determine why the traversal differed.

The moment at which cells were marked as visited affected which branch of the
DFS claimed a cell first.

The DFS logic was corrected so that the iterative implementation reproduced the
required deterministic behavior.

After the correction, the official sample produced:

```text
Case #1: BFS 18 DFS 32
```

---

#### 2. Stack order required additional correction

DFS uses a stack, which follows:

```text
LIFO — Last In, First Out
```

Because of this behavior, simply pushing the neighbors in the desired
exploration order does not necessarily make DFS visit them in that same order.

The implementation was reviewed and the neighbors were pushed in the
appropriate reverse order so that the effective exploration became:

```text
Up → Down → Left → Right
```

This behavior was then verified using the official sample and automated tests.

---

### What Was Learned

Mission 1 helped reinforce the difference between BFS and DFS and how their
data structures directly affect graph traversal.

It also showed that DFS behavior depends not only on using a stack, but also on
details such as:

- Neighbor insertion order.
- LIFO behavior.
- The moment a cell becomes visited.

Another important lesson was understanding why an iterative DFS is appropriate
for this project.

Since the mission allows very large grids, using an explicit stack avoids
depending on Java's recursive call stack and reduces the risk of stack overflow.

---

# Person 2 — Miguel Angel Quintero

## Mission 2 — Dijkstra

## Mission 4 — Kruskal and Union-Find

### AI Tool Used

**Claude (Anthropic)** was used as a support tool during the development of
Missions 2 and 4.

It was mainly used for:

- Reviewing the requirements of Missions 2 and 4.
- Implementing and reviewing Dijkstra's algorithm.
- Reviewing the use of `PriorityQueue`.
- Checking repeated edges and self-loops.
- Checking unreachable destinations.
- Implementing and reviewing Kruskal's algorithm.
- Reviewing the custom Union-Find implementation.
- Checking path compression.
- Checking union by size.
- Reviewing input parsing and validation.
- Creating and reviewing automated tests.
- Reviewing graph visualization.
- Reviewing MST visualization.
- Verifying special output messages.
- Checking the official samples.

---

### Decisive Prompts

#### Prompt 1

> "Review the Dijkstra implementation according to the project statement. It
> must use PriorityQueue, support repeated edges and self-loops, and use long
> for accumulated distances."

This prompt helped verify that the implementation satisfied the requirements of
Mission 2 instead of only implementing a basic version of Dijkstra.

---

#### Prompt 2

> "Review the Kruskal and Union-Find implementation. Verify that Union-Find
> uses path compression and union by size and that Kruskal correctly detects
> when the graph cannot be fully connected."

This prompt was important because the project explicitly requires an efficient
Union-Find implementation.

It also helped verify the disconnected-network case.

---

#### Prompt 3

> "Test the official Mission 4 sample step by step and verify the exact MST
> cost before considering the mission complete."

This prompt was important because an incorrect manual calculation of the MST
cost was initially suggested.

Reviewing the selected edges exposed the mistake.

---

### Incorrect or Suboptimal AI Outputs and Corrections

#### 1. Incorrect Mission 4 MST cost

During the review of the official Mission 4 sample, an AI-assisted calculation
initially stated that the MST cost was:

```text
45
```

This result was incorrect.

The edges selected by Kruskal were reviewed individually:

```text
1 - 2 = 10
1 - 3 = 15
3 - 4 = 30
```

Therefore:

```text
10 + 15 + 30 = 55
```

The correct result is:

```text
Case #1: 55
```

The value was later confirmed using the implementation and automated tests.

---

#### 2. Incorrect unreachable message in Mission 2 visualization

During the integration and review of the Mission 2 GUI, the visualization
temporarily displayed:

```text
Limon blocked the way
```

for an unreachable destination.

That message belongs to Mission 3 and was therefore incorrect for Mission 2.

The project requirements were reviewed again and the message was corrected to:

```text
Nina is very sad
```

The final Mission 2 output and visualization now use the correct message.

---

### What Was Learned

Mission 2 helped reinforce why Dijkstra benefits from a priority queue.

Instead of repeatedly searching all nodes for the smallest tentative distance,
the `PriorityQueue` provides an efficient way to obtain the next candidate.

The mission also helped reinforce the importance of using `long` for
accumulated distances when graph paths may contain many weighted edges.

Mission 4 helped clarify how Kruskal constructs a Minimum Spanning Tree by
processing edges from lowest to highest cost while avoiding cycles.

The implementation of Union-Find helped reinforce the purpose of:

```text
Path compression
Union by size
```

These optimizations make repeated connectivity checks more efficient.

The incorrect manual MST calculation also demonstrated the importance of
checking AI-assisted calculations against the actual algorithm and automated
tests.

---

# Person 3 — Ana Sofia Henao

## Mission 3 — Floyd-Warshall and Bellman-Ford

### AI Tool Used

**ChatGPT (OpenAI)** was used as a support tool during the development,
debugging, testing, visualization, and final review of Mission 3.

It was mainly used for:

- Understanding the Mission 3 requirements step by step.
- Reviewing the structure of the Mission 3 code.
- Implementing and reviewing Floyd-Warshall.
- Adapting Floyd-Warshall to maximize churun.
- Implementing and reviewing Bellman-Ford.
- Adapting Bellman-Ford to maximize churun.
- Understanding positive-cycle detection.
- Determining when a positive cycle affects the destination.
- Cross-checking Floyd-Warshall and Bellman-Ford.
- Reviewing the Floyd-Warshall matrix.
- Distinguishing unreachable and unbounded matrix entries.
- Reviewing maximum-route reconstruction.
- Reviewing positive-cycle visualization.
- Creating and reviewing automated tests.
- Checking visualization thresholds.
- Testing official samples.
- Reviewing the final GUI behavior.
- Reviewing the final project documentation.

---

### Decisive Prompts

#### Prompt 1

> "Explain Mission 3 step by step and determine exactly when a positive cycle
> should make the answer Infinite churun!. A positive cycle should only affect
> the result if it is reachable from the source and can reach the destination."

This prompt was important because simply detecting a positive cycle somewhere
in the graph is not enough.

The cycle must be relevant to the requested source-to-destination path.

---

#### Prompt 2

> "Review Floyd-Warshall according to the project statement. The matrix must
> distinguish between a pair with no route and a pair whose maximum churun is
> infinite because of a positive cycle. Display '-' for no route and 'inf' for
> an unbounded value."

This prompt helped clarify that:

```text
-
```

and:

```text
inf
```

represent completely different states.

---

#### Prompt 3

> "Run Floyd-Warshall and Bellman-Ford for every case and compare their final
> results. Keep enough information to visualize the maximum route when the
> answer is finite and a positive cycle when the answer is infinite."

This prompt helped satisfy the requirement that both algorithms independently
solve Mission 3 and that their answers are compared.

It also connected the algorithmic results with the visualization requirements.

---

### Incorrect or Suboptimal AI Outputs and Corrections

#### 1. Incomplete representation of `inf` in the Floyd-Warshall matrix

An earlier version of the Mission 3 solution calculated the Floyd-Warshall
numeric matrix but did not fully represent which source-destination pairs were
unbounded because of positive cycles.

This was insufficient for the required matrix visualization.

The logic was reviewed and corrected.

A pair `(i, j)` is unbounded when there exists a node `k` such that:

```text
i can reach k
k belongs to a positive cycle
k can reach j
```

In terms of the Floyd-Warshall result, this means that `k` must satisfy the
positive-cycle condition and must be reachable between the corresponding pair.

The final matrix displays:

```text
-    No route exists
inf  Maximum churun is unbounded
```

Finite maximum values remain numeric.

---

#### 2. Mission 3 visualization thresholds were initially mixed

During the GUI review, the graph visualization and the Floyd-Warshall matrix
were initially treated as if they had the same size threshold.

This did not satisfy the project requirements.

The limits were separated:

```text
Graph visualization:       N <= 60
Floyd-Warshall matrix:     N <= 100
```

This means that a case with, for example:

```text
61 nodes
```

is still solved normally.

The graph drawing is omitted, but the Floyd-Warshall matrix can still be
displayed.

This behavior was tested after the correction.

---

### What Was Learned

Mission 3 helped clarify that detecting a positive cycle is not enough to
declare a result infinite.

The cycle must satisfy both conditions:

```text
The source can reach the cycle.
The cycle can reach the destination.
```

This was one of the most important concepts learned during the implementation.

The mission also helped reinforce the difference between Floyd-Warshall and
Bellman-Ford.

Floyd-Warshall works with information between all pairs of nodes, while
Bellman-Ford works from a particular source.

Using both algorithms for the same mission also showed how independent
implementations can be used to validate each other.

Another important concept was understanding the difference between:

```text
No route exists
```

and:

```text
The maximum value is unbounded
```

An unreachable pair is represented with:

```text
-
```

while a pair affected by a relevant positive cycle is represented with:

```text
inf
```

Finally, connecting the algorithmic results to the GUI helped reinforce that
the program must preserve enough information not only to calculate an answer,
but also to explain it visually through the maximum route or positive cycle.

---

# General Integration and Final Review

Artificial Intelligence was also used as support during the integration and
final review of the complete project.

The final review included:

- Checking the project package structure.
- Reviewing the separation between algorithm, model, and view.
- Checking the four mission panels.
- Reviewing input parsers.
- Reviewing exact special output messages.
- Checking official sample inputs and outputs.
- Reviewing graph visualization.
- Reviewing visualization limits.
- Testing unreachable cases.
- Testing disconnected graphs.
- Testing positive cycles.
- Testing malformed input.
- Reviewing automated tests.
- Reviewing the README.
- Reviewing this AI usage document.

The final automated test execution produced:

```text
50 tests passed
50 tests total
0 failed
```

The process finished successfully with:

```text
Process finished with exit code 0
```

---

# Examples of AI Verification During the Project

The development process showed that AI-generated answers cannot be assumed to
be correct automatically.

Some important examples were:

```text
Mission 1:
Initial DFS behavior did not reproduce the required traversal.
It was corrected and verified against the official sample.

Mission 2:
The unreachable visualization temporarily used the Mission 3 message.
It was corrected to "Nina is very sad".

Mission 3:
The Floyd-Warshall matrix initially required additional logic to distinguish
unreachable pairs from unbounded pairs affected by positive cycles.

Mission 4:
An AI-assisted manual calculation initially reported an MST cost of 45.
The correct value was verified as 55.
```

These situations were corrected by comparing the suggestions with the project
statement, executing the program, and running automated tests.

---

# Final Reflection

AI was used as a **support tool**, not as a replacement for understanding or
verification.

The team reviewed the generated suggestions, compared them with the project
requirements, executed the algorithms, and corrected the implementation when
necessary.

The most important lesson from using AI during this project was that generated
code and explanations must always be validated.

The final implementation was verified using:

1. The requirements from the project statement.
2. Official sample inputs and outputs.
3. Additional edge cases.
4. Automated tests.
5. Manual execution through the graphical interface.

The final project therefore contains the reviewed and corrected implementation,
not simply the initial output generated by the AI tools.