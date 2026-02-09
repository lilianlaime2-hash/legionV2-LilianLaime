package model;

import sort.InsertionSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BattleField {

    private Cell fieldMatrix[][];

    public BattleField (int rows, int columns){

        fieldMatrix = new Cell[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                fieldMatrix[i][j] = new Cell(i, j);
            }
        }
    }

    public void placeCharacter (Character character, int row, int column){
        Cell cell = getCell(row, column);
        cell.setCharacter(character);
    }

    public Cell getCell (int rows, int columns){
        return fieldMatrix[rows][columns];
    }

    public void sortMatrix(Comparator<Character> comparator, Orientation orientation){
        List<Character> troops = new ArrayList<>();

        for (int i = 0; i < fieldMatrix.length; i++){
            for (int j = 0; j < fieldMatrix[i].length; j++ ){
                Cell cell = fieldMatrix[i][j];
                if (!cell.isEmpty()){
                    troops.add(cell.getCharacter());
                }
            }
        }

        InsertionSort<Character> insertionSort = new InsertionSort<>();
        insertionSort.sort(troops, comparator);

        fillSorted(troops, orientation);
    }

    private void fillSorted(List<Character> troops, Orientation orientation) {
        if (troops.isEmpty()) {
            return;
        }

        TroopType prevType = troops.get(0).getType();
        int line = 0;
        int position = 0;
        int size = fieldMatrix.length;

        clearMatrix();

        for (Character troop : troops){

            if (troop.getType() != prevType){
                line++;
                position = 0;
                prevType = troop.getType();
            }

            int row = 0;
            int col = 0;

            switch (orientation){
                case EAST:
                    row = (size - 1) - position;
                    col = line;
                    break;
                case WEST:
                    col = (size - 1) - line;
                    row = (size - 1) - position;
                    break;
                case NORTH:
                    row = (size - 1) - line;
                    col = position;
                    break;
                case SOUTH:
                    row = line;
                    col = position;
                    break;
            }

            if (row < 0 || row >= size || col < 0 || col >= size) {
                position++;
                continue;
            }

            fieldMatrix[row][col].setCharacter(troop);
            position++;
        }
    }

    private void clearMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                fieldMatrix[i][j].setCharacter(null);
            }
        }
    }

    public void showBattleField(){
        for (int i = 0; i < fieldMatrix.length ; i++){
            for (int j = 0; j < fieldMatrix[i].length; j++){
                if (this.getCell(i, j).isEmpty()){
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + getCell(i,j).getCharacter() + " ");
                }
            }
            System.out.println();
        }
    }
}