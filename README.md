# Data_structures_and_algorithms in Java
Academic projects of algorithms implementation in java. All data structures used are implemented from scratch as classes with their according methods:

Node / LinkedList / HashMap / Priority Queue / Stack / Tuples

## Algorithms/Data - structures

- **Bin Packing** — greedy and greedy decreasing implementation. Allocates folders into bins of certain capacity using a custom priority queue and
  linked data structures.
- **2D Tree** — structure used to organize and search for points in a space with 2 dimensions. Supports point insertion,
  search and rectangular range search.
- **Thiseas Pathfinding** — maze pathfinding using custom stack and
  queue implementations.


## Requirements

- Java 17
- Apache Maven

## Project setup

1. Clone this repository 
2. Edit configuration for each algorithm main, that points to src/main/resources/.. and the according algorithm
3. The first argument is the path to the input file.
4. Open `pom.xml` and load the project as a Maven project.
5. Run each algorithm


## Project Structure

```text
src/main/java/
├── com/algorithms/
│   ├── binpacking/
│   ├── kdtree/
│   └── thiseas/
├── com/data_structures/
└── com/loaders/

src/main/resources/
├── binpacking/
├── kdtree/
└── thiseas/
```

