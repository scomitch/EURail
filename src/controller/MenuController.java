package controller;

import misc.Constants;

import java.util.Scanner;

public class MenuController {

    int options = 4;

    public void printMenu(){
        System.out.println("===== Rail Travel System ====");
        System.out.println("Please choose an option...");
        System.out.println("1. View all Stations on the Network");
        System.out.println("2. View all Routes on the Network");
        System.out.println("3. Plan Journey");
        System.out.println("0. Exit");
    }

    public int chooseAction(){
        printMenu();
        //Define some constants for the MainMenu method.
        Scanner sc = new Scanner(System.in);
        int decision;

        //Space
        System.out.println();

        //Loop through the scanner inputs
        do{
            System.out.println(Constants.WHITE + "Please enter a positive number.");
            while(!sc.hasNextInt()){
                System.out.println(Constants.RED + "Must be a number.");
                sc.next();
            }
            decision = sc.nextInt();
        } while(decision < 0);

        return decision;
    }


}
