package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Minefield {
    private static Random rnd = null;
    private final char[][] field;
    private static int size;

    Minefield(int fieldSize) {
        size = fieldSize;
        field = new char[fieldSize][fieldSize];
        for (char[] row : field) {
            Arrays.fill(row, '.');
        }
    }

    Minefield(int fieldSize, int mines) {
        this(fieldSize);
        rnd = new Random();
        populateFieldWithMines(mines);
    }

    private void populateFieldWithMines(int mines) {
        int mine = 0;
        do {
            int spot = rnd.nextInt(81);
            int row = spot / size;
            int col = spot % size;
            if (field[row][col] != 'X') {
                field[row][col] = 'X';
                mine++;
            }
        } while (mine < mines);

        determineNeighbors();
    }

    /**
     * Iterate through the field getting the number of mines that surround an empty cell.
     */
    private void determineNeighbors() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (field[row][col] == 'X') {
                    continue;
                }

                int mines = howManyMines(row, col);
                if (mines > 0) {
                    field[row][col] = String.valueOf(mines).charAt(0);
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
        int mines = 0;
        for (int row = Math.max(0, cellRow - 1); row <= Math.min(size - 1, cellRow + 1); row++) {
            for (int col = Math.max(0, cellCol - 1); col <= Math.min(size - 1, cellCol + 1); col++) {
                if (row == cellRow && col == cellCol) { // don't check the cell we're checking around
                    continue;
                }
                if (field[row][col] == 'X') {
                    mines++;
                }
            }
        }
        return mines;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result.append(field[row][col]);
            }
            result.append('\n');
        }
        return String.valueOf(result);
    }
}
