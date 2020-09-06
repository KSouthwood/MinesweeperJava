package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Minefield {
    private Random rnd = null;
    private final char[][] field;
    private final boolean[][] mines;
    private final int size;
    private int minesMarked;
    private int minesCorrect;
    private int minesPlaced;
    private int cellsToClear;
    private boolean steppedOnMine;
    private boolean firstMove;

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
        minesPlaced = numOfMines;
        minesCorrect = 0;
        minesMarked = 0;
        cellsToClear = (fieldSize * fieldSize) - numOfMines;
        steppedOnMine = false;
        firstMove = true;
        populateFieldWithMines(numOfMines);
    }

    private void populateFieldWithMines(int numOfMines) {
        for (int i = 0; i < numOfMines;) {
            int spot = rnd.nextInt(81);
            int row = spot / size;
            int col = spot % size;
            if (!mines[row][col]) {
                mines[row][col] = true;
                i++;
            }
        }
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
    private char howManyMines(int cellRow, int cellCol) {
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
        return numOfMines == 0 ? '/' : String.valueOf(numOfMines).charAt(0);
    }

    private void revealMines() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (mines[row][col]) {
                    field[row][col] = 'X';
                }
            }
        }
    }

    public void markCell(int row, int col) {
        if (field[row][col] == '.') {
            field[row][col] = '*';
            minesMarked++;
            if (mines[row][col]) {
                minesCorrect++;
            }
            return;
        }

        if (field[row][col] == '*') {
            field[row][col] = '.';
            minesMarked--;
            if (mines[row][col]) {
                minesCorrect--;
            }
            return;
        }

        System.out.println("Cell already cleared. Try again.");
    }

    public void clearCell(int row, int col) {
        if (firstMove) {
            while (mines[row][col]) {
                mines[row][col] = false;
                populateFieldWithMines(1);
            }
            firstMove = false;
        }

        if (mines[row][col]) {
            steppedOnMine = true;
            revealMines();
            return;
        }

        if (!(field[row][col] == '.' || field[row][col] == '*')) {
            System.out.println("Cell already cleared.");
            return;
        }

        if (field[row][col] == '*') {
            minesMarked--;
        }

        field[row][col] = howManyMines(row, col);
        cellsToClear--;
        if (field[row][col] == '/') {
            clearZeroCell(row, col);
        }
    }

    private void clearZeroCell(int cellRow, int cellCol) {
        for (int row = Math.max(0, cellRow - 1); row <= Math.min(size - 1, cellRow + 1); row++) {
            for (int col = Math.max(0, cellCol - 1); col <= Math.min(size - 1, cellCol + 1); col++) {
                if (row == cellRow && col == cellCol) {
                    continue;
                }

                if (field[row][col] != '.' && field[row][col] != '*') {
                    continue;
                }

                if (field[row][col] == '*') {
                    minesMarked--;
                }

                field[row][col] = howManyMines(row, col);
                cellsToClear--;
                if (field[row][col] == '/') {
                    clearZeroCell(row, col);
                }
            }
        }
    }

    public boolean allMinesFound() {
        return (minesMarked == minesCorrect && minesCorrect == minesPlaced) || cellsToClear == 0;
    }

    public boolean isSteppedOnMine() {
        return steppedOnMine;
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
