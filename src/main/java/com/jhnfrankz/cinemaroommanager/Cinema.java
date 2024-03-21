package com.jhnfrankz.cinemaroommanager;

import java.util.Scanner;

public class Cinema {

    private static final Scanner scanner = new Scanner(System.in);
    private static int totalNumberOfTickets = 0;
    private static int numberPurchasedTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {

        startCinema();
    }

    private static void startCinema() {
        System.out.println("Enter the number of rows:");
        int numberRow = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        int numberEachRow = Integer.parseInt(scanner.nextLine());
        char[][] x = new char[numberRow][numberEachRow];
        totalNumberOfTickets = numberRow * numberEachRow;

        for (int i = 0; i < numberRow; i++) {
            for (int j = 0; j < numberEachRow; j++) {
                x[i][j] = 'S';
                if (totalNumberOfTickets > 60) {
                    totalIncome += i < numberRow / 2 ? 10 : 8;
                } else {
                    totalIncome += 10;
                }
            }
        }

        chooseOption(x);
    }

    private static void chooseOption(char[][] x) {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 0:
                return;
            case 1:
                showSeats(x);
                chooseOption(x);
                break;
            case 2:
                buyTicket(x);
                chooseOption(x);
                break;
            case 3:
                showStadistics(x);
                chooseOption(x);
        }
    }

    private static void showSeats(char[][] x) {
        System.out.println("\nCinema:");

        for (int j = -1; j < x[0].length; j++) {
            System.out.print(j == -1 ? " " : " " + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < x.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(" " + x[i][j]);
            }
            System.out.println();
        }

    }

    public static void showStadistics(char[][] x) {
        double percent = (double) numberPurchasedTickets / totalNumberOfTickets * 100;

        System.out.printf("Number of purchased tickets: %d\n" +
                        "Percentage: %.2f%%\n" +
                        "Current income: $%d\n" +
                        "Total income: $%d\n", numberPurchasedTickets,
                percent, currentIncome, totalIncome);
    }

    private static void buyTicket(char[][] x) {
        System.out.println("\nEnter a row number:");
        int seatRow = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a seat number in that row:");
        int seatInRow = Integer.parseInt(scanner.nextLine());

        /*if (seatRow > x.length || seatInRow > x[0].length) {
            return;
        }*/

        if (seatRow > x.length || seatInRow > x[0].length ||
                seatRow < 1 || seatInRow < 1) {
            System.out.println("Wrong input!");
            buyTicket(x);
        } else if (x[seatRow - 1][seatInRow - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(x);
        } else {
            int priceTicket = 10;

            if (x.length * x[0].length > 60) {
                boolean isInFront = seatRow <= x.length / 2;

                if (!isInFront) {
                    priceTicket = 8;
                }
            }

            x[seatRow - 1][seatInRow - 1] = 'B';

            System.out.printf("Ticket price: $%d\n", priceTicket);
            currentIncome += priceTicket;
            numberPurchasedTickets++;
        }
    }
}