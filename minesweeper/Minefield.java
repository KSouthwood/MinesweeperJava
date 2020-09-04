package minesweeper;

public class Minefield {
    private final char[][] field;

    Minefield() {
        field = new char[][]{{'.', 'X', '.', '.', '.', 'X', '.', '.', 'X'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', '.', 'X', '.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', 'X', '.', '.', 'X', '.'},
                {'X', '.', '.', '.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                {'.', '.', 'X', '.', 'X', '.', '.', '.', '.'},
                {'.', 'X', '.', '.', '.', '.', 'X', '.', '.'}};
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
