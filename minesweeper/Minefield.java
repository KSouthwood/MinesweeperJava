package minesweeper;

import java.util.Random;

public class Minefield {
    private static Random rnd = null;
    private final char[][] field;

    Minefield() {
        field = new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
    }

    Minefield(int mines) {
        this();
        rnd = new Random();
        initField(mines);
    }

    private void initField(int mines) {
        int mine = 0;
        do {
            int spot = rnd.nextInt(81);
            int row = spot / 9;
            int col = spot % 9;
            if (field[row][col] != 'X') {
                field[row][col] = 'X';
                mine++;
            }
        } while (mine < mines);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                result.append(field[row][col]);
            }
            result.append('\n');
        }
        return String.valueOf(result);
    }
}
