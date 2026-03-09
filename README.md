# README

Lilian Laime Lucero

## Project Overview
This project simulates troop creation, validation, battlefield placement, sorting, and final deployment by orientation.

## Project Phases
![ProjectPhases](ClassDIagramPicture/ProjectPhases.jpg)

## Three-Phase Summary
### Phase 1: Validation (Syntax + Semantics)
- `CliParser` validates input format (`algorithm`, `type`, `orientation`, `units`, `field size`) and parses values into domain types.
- `ParseReport` stores both parsed config (`GameConfig`) and status per field (`VALID`, `INVALID`, `NOT_PRESENT`).
- `BattleFieldValidator` checks business rules:
  - troops must fit battlefield area (`N x N`),
  - troop types must fit line constraints.

### Phase 2: Initial Battlefield State
- `TroopFactory` creates troop instances.
- `BattleField` is created as an empty matrix.
- `TroopPlacer.placeRandom(...)` places troops randomly.
- `BattleFieldView` prints the initial state.

### Phase 3: Sorting + Final State
- Troops are extracted from matrix to `List<Character>`.
- Sorting is delegated through `SortStrategy` (Strategy pattern).
- `ConsoleSortObserver` prints visible sorting steps.
- `TroopPlacer.placeSorted(...)` places sorted troops back by orientation.
- `GameEngine` measures total sorting time.

## Class Diagrams
![Phase1](ClassDIagramPicture/1raPhase.png)
![Phase2](ClassDIagramPicture/2ndPhase.png)
![Phase3](ClassDIagramPicture/3thPhase.png)
![GeneralClassDiagram](ClassDIagramPicture/GeneralClassDiagram.png)

## Sorting Algorithms
![BubbleSort](AlgortthmsPictures/Bubble%20Sort.png)
![InsertionSort](AlgortthmsPictures/insertion-sort.png)
![MergeSort](AlgortthmsPictures/MergueSort.png)
![QuickSort](AlgortthmsPictures/QUickSort.png)

Sources:
https://www.geeksforgeeks.org/dsa/bubble-sort-algorithm/

## Execution Screenshots
![Screenshot-1](Screenshot%20from%202026-03-08%2020-30-32.png)
![Screenshot-2](Screenshot%20from%202026-03-08%2020-30-38.png)
![Screenshot-3](Screenshot%20from%202026-03-08%2020-30-43.png)

## Applied Principles and Patterns
- Patterns:
  - `Factory`: `TroopFactory`
  - `Strategy`: `SortStrategy` + algorithm implementations
- Data structures:
  - `HashMap`: parser values and validation statuses
  - `int[]`: troop counts
  - `List<Character>`: sortable troop sequence
  - `Cell[][]`: battlefield matrix
- OOP:
  - Abstraction, Inheritance, Polymorphism, Encapsulation
  - Enums: `Algorithm`, `Orientation`, `TroopType`, `FieldStatus`
  - Composition/Aggregation in battlefield and orchestration classes
- SOLID (current status):
  - `SRP`: mostly applied
  - `OCP`: partial (strong in strategy, weaker in switch-based factories)
  - `LSP`: applied in `Character` hierarchy and sorting strategies
  - `ISP`: minimal but adequate for current scope
  - `DIP`: partial (stronger in sorting abstractions)
- Exception handling:
  - `try/catch` in parser and observer
  - `IllegalArgumentException` for invalid enum abbreviations
- Enums and interfaces also were using
