package cinema;

import java.util.Scanner;

class Cinema {

    private final static Scanner SCANNER = new Scanner(System.in);

    final static int rows = getRows();
    final static int seatsInRow = getSeatsInRow();
    final static int firstHalf = (rows / 2) * seatsInRow;
    final static int secondHalf = (rows - (rows / 2)) * seatsInRow;
    final static int allSeats = rows * seatsInRow;

    static String[][] SeatingArrangement = new String[rows][seatsInRow];

    private static int getRows() {
        System.out.println("Enter the number of rows:");
        return SCANNER.nextInt();
    }

    private static int getSeatsInRow() {
        System.out.println("Enter the number of seats in each row:");
        return SCANNER.nextInt();
    }

    static void setSeatingArrangement() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                SeatingArrangement[i][j] = "S";
            }
        }
    }

    static void printSeatingArrangement() {

        System.out.println("\nCinema:");

        for (int i = 0; i <= seatsInRow; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();

        int rowsCounter = 1;

        for (int i = 0; i < rows; i++) {
            System.out.print(rowsCounter + " ");
            for (int j = 0; j < seatsInRow; j++) {
                System.out.print(SeatingArrangement[i][j] + " ");
            }
            ++rowsCounter;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        setSeatingArrangement();
        CinemaBoxOffice.chooseAction();
    }
}