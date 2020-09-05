package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Minefield {
    private static Random rnd = null;
    private char[][] field;
    private boolean[][] mines;
    private static int size;
    private int minesMarked;
    private int minesCorrect;
    private int minesPlaced;

    Minefield(int fieldSize) {
        size = fieldSize;
        field = new char[fieldSize][fieldSize];
        for (char[] row : field) {
            Arrays.fill(row, '.');
        }
        mines = new boolean[fieldSize][fieldSize];
        for (boolean[] row : mines) {
            Arrays.fill(row, false);
        }
    }

    Minefield(int fieldSize, int numOfMines) {
        this(fieldSize);
        rnd = new Random();
        this.minesPlaced = numOfMines;
        this.minesCorrect = 0;
        this.minesMarked = 0;
        populateFieldWithMines(numOfMines);
    }

    private void populateFieldWithMines(int numOfMines) {
        int minesPlaced = 0;
        do {
            int spot = rnd.nextInt(81);
            int row = spot / size;
            int col = spot % size;
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        } while (minesPlaced < numOfMines);

        determineNeighbors();
    }

    /**
     * Iterate through the field getting the number of mines that surround an empty cell.
     */
    private void determineNeighbors() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (mines[row][col]) {  // don't count the number of mines around a mine
                    continue;
                }

                int numOfMines = howManyMines(row, col);
                if (numOfMines > 0) {
                    field[row][col] = String.valueOf(numOfMines).charAt(0);
                }
            }
        }
    }

    /**
     * Determine how many mines surround a specified cell. Ensures we don't go out of bounds while checking.
     *
     * @param cellRow row of field
     * @param cellCol column of field
     * @return number of mines around specified cell
     */
    private int howManyMines(int cellRow, int cellCol) {
        int numOfMines = 0;
        for (int row = Math.max(0, cellRow - 1); row <= Math.min(size - 1, cellRow + 1); row++) {
            for (int col = Math.max(0, cellCol - 1); col <= Math.min(size - 1, cellCol + 1); col++) {
                if (row == cellRow && col == cellCol) { // don't check the cell we're checking around
                    continue;
                }
                if (mines[row][col]) {
                    numOfMines++;
                }
            }
        }
        return numOfMines;
    }

    public boolean markCell(int[] cell) {
        int row = cell[1];
        int col = cell[0];
        if (Character.isDigit(field[row][col])) {
            return false;
        }

        if (field[row][col] == '.') {
            field[row][col] = '*';
            minesMarked++;
            if (mines[row][col]) {
                minesCorrect++;
            }
            return true;
        }

        if (field[row][col] == '*') {
            field[row][col] = '.';
            minesMarked--;
            if (mines[row][col]) {
                minesCorrect--;
            }
            return true;
        }

        return false; // somethings wrong if we reach here, the field char is something besides a digit, '.' or '*'
    }

    public boolean allMinesFound() {
        return minesMarked == minesCorrect && minesCorrect == minesPlaced;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n |123456789|\n-|---------|\n");
        for (int row = 0; row < size; row++) {
            result.append(row + 1);
            result.append("|");
            for (int col = 0; col < size; col++) {
                result.append(field[row][col]);
            }
            result.append("|\n");
        }
        result.append("-|---------|");
        return String.valueOf(result);
    }
}
