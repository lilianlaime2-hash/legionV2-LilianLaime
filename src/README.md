# README

## Execution Flow
![Diagrama de Flujo](../CAPSTONE/legion/FLOW.png)

1. `controller`: receives CLI args and coordinates the game flow.
2. `validation`: validates syntax and semantic rules.
3. `creation`: builds troops and creates the initial battlefield.
4. `sorting`: sorts troops and applies the final formation.
5. `view`: prints battlefield states and sorting output.

## Package Responsibility
- `controller`
  - `GameController`: main app flow.
- `validation.syntax`
  - `CliParser`, `ParseReport`, `FieldStatus`.
- `validation.semantic`
  - `BattleFieldValidator`.
- `creation`
  - Creation services (`TroopFactory`, `TroopPlacer`).
- `creation.model` and `creation.model.character`
  - Game entities and troop types.
- `sorting` and `sorting.algorithms`
  - Sorting contracts, observer, context, factory, `BattleFieldService`, and algorithm implementations.
- `service`
  - Orchestration service (`GameEngine`).
- `view`
  - Console rendering (`BattleFieldView`).

## Diagrams / Screenshots
![Screenshot from 2026-02-22 23-10-34.png](../Screenshot%20from%202026-02-22%2023-10-34.png)
![Screenshot from 2026-02-22 23-11-18.png](../Screenshot%20from%202026-02-22%2023-11-18.png)
![imagen.png](../ClassDiagram.png)
