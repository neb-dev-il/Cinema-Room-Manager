package cinema;

import java.util.Objects;
import java.util.Scanner;

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
    private final static Scanner SCANNER = new Scanner(System.in);

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
        chosenRow = SCANNER.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenSeat = SCANNER.nextInt();

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
            actionNumber = SCANNER.nextInt();
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