package minesweeper;

import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] coords;
        Minefield minefield = new Minefield(9, getMines());

        do {
            System.out.println(minefield.toString());
            do {
                coords = getCoordinates();
                if (minefield.markCell(coords)) {
                    break;
                }
                System.out.println("There is a number here!");
            } while (true);
        } while (!minefield.allMinesFound());

        System.out.println(minefield.toString());
        System.out.println("Congratulations! You found all mines!");
    }

    /**
     * Asks the user for the number of mines to place on the minefield and validates it for the size of the field
     *
     * @return number of mines
     */
    private static int getMines() {
        int mines;

        do {
            System.out.print("How many mines do you want on the field? ");
            mines = input.nextInt();
            if (mines < 1 || mines > 81) {
                System.out.println("Invalid value. Please enter a number from 1 to 81.");
            }
        } while (mines < 1 || mines > 81);

        input.nextLine();
        return mines;
    }

    private static int[] getCoordinates() {
        int[] cell = new int[2];

        do {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            String[] coords = input.nextLine().split(" ");
            if (coords.length != 2) {
                System.out.println("Invalid number of arguments!");
                continue;
            }

            if (coords[0].matches("[1-9]") && coords[1].matches("[1-9]")) {
                cell[0] = Integer.parseInt(coords[0]) - 1;
                cell[1] = Integer.parseInt(coords[1]) - 1;
            } else {
                System.out.println("Need to enter two numbers between 1 and 9!");
                continue;
            }

            return cell;
        } while (true);
    }
}
