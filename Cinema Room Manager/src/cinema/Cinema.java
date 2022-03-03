package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    final static Scanner SCANNER = new Scanner(System.in);

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
        // Write your code here
        setSeatingArrangement();
        CinemaBoxOffice.chooseAction();
    }
}

class CinemaBoxOffice {

    static int chosenRow;
    static int chosenSeat;
    static boolean isCorrectsSeatCoordinates = false;
    static int actionNumber;
    static int ticketPrice;
    static int purchasedTickets = 0;
    static double purchasedTicketsInPercentage = 0;
    static int currentIncome = 0;
    final static int totalIncome = calculateTotalIncome();

    static void calculatePurchasedTickets() {
        ++purchasedTickets;
    }

    static void calculatePercentage() {
        purchasedTicketsInPercentage = (double) Math.round(purchasedTickets * 100) / (Cinema.allSeats);
    }

    static void calculateCurrentIncome() {
        currentIncome += ticketPrice;
    }

    static int calculateTotalIncome() {
        if (Cinema.allSeats <= 60) {
            return Cinema.allSeats * 10;
        } else {
            return (Cinema.firstHalf * 10) + (Cinema.secondHalf * 8);
        }
    }

    static void printStatistics() {
        System.out.printf("\nNumber of purchased tickets: %d", purchasedTickets);
        System.out.printf("\nPercentage: %.2f%s", purchasedTicketsInPercentage, "%");
        System.out.printf("\nCurrent income: $%d", currentIncome);
        System.out.printf("\nTotal income: $%d\n", totalIncome);
    }

    public static void chooseSeat() {

        System.out.println("\nEnter a row number:");
        chosenRow = Cinema.SCANNER.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenSeat = Cinema.SCANNER.nextInt();

    }

    static void printActions() {
        System.out.println();
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
    }

    static void chooseAction() {

        do {
            printActions();
            actionNumber = Cinema.SCANNER.nextInt();
            switch (actionNumber) {
                case 1 -> Cinema.printSeatingArrangement();
                case 2 -> {
                    while (!isCorrectsSeatCoordinates) {
                        chooseSeat();
                        isCorrectsSeatCoordinates = isCorrectInput(chosenRow, chosenSeat);
                    }
                    markSeat(chosenRow, chosenSeat);
                    setTicketPrice(chosenRow);
                    printTicketPrice();
                    calculatePurchasedTickets();
                    calculatePercentage();
                    calculateCurrentIncome();
                    isCorrectsSeatCoordinates = false;
                }
                case 3 -> printStatistics();
            }
        } while (actionNumber != 0);
    }

    static void setTicketPrice(int selectedRow) {

        if ((Cinema.allSeats) <= 60) {
            ticketPrice = 10;
        } else {
            if (selectedRow <= (Cinema.rows / 2)) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
    }

    static void printTicketPrice() {
        System.out.println();
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

    static boolean isCorrectInput(int selectedRow, int selectedSeat) {

        if (selectedRow > Cinema.rows || selectedRow < 0 || selectedSeat > Cinema.seatsInRow || selectedSeat < 0) {
            System.out.println();
            System.out.println("Wrong input!");
        } else if (Objects.equals(Cinema.SeatingArrangement[--selectedRow][--selectedSeat], "B")) {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
        } else {
            isCorrectsSeatCoordinates = true;
        }

        return isCorrectsSeatCoordinates;
    }

    static void markSeat(int selectedRow, int selectedSeat) {
        Cinema.SeatingArrangement[--selectedRow][--selectedSeat] = "B";
    }
}