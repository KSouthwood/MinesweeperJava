package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Minefield minefield = new Minefield(getMines());
        System.out.println(minefield.toString());
    }

    /**
     * Asks the user for the number of mines to place on the minefield and validates it for the size of the field
     *
     * @return number of mines
     */
    private static int getMines() {
        Scanner input = new Scanner(System.in);
        int mines;

        do {
            System.out.println("How many mines do you want on the field? ");
            mines = input.nextInt();
            if (mines < 1 || mines > 81) {
                System.out.println("Invalid value. Please enter a number from 1 to 81.");
            }
        } while (mines < 1 || mines > 81);

        return mines;
    }
}
