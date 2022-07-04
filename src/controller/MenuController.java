package controller;

import misc.Constants;

import java.util.Scanner;

public class MenuController {

    int options = 4;

    public void printMenu(){
        System.out.println("===== Rail Travel System =====");
        System.out.println("Please choose an option...");
        System.out.println("1. View all Stations on the Network");
        System.out.println("2. View all Routes on the Network");
        System.out.println("3. Plan Journey");
        System.out.println("0. Exit");
    }

    public void printJourneyPrompt(){
        System.out.println("===== Plan Your Journey =====");
        System.out.println("Follow the below instructions to find your personal journey");
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

    public String[] chooseStations(){
        //Instance Vars
        String[] decisions = new String[2];
        Scanner sc = new Scanner(System.in);

        //Prompts
        printJourneyPrompt();

        //First, let's get the starting station
        try{
            System.out.print("Please enter your starting station: ");
            decisions[0] = sc.nextLine();
            System.out.println();
            System.out.print("Please enter your ending station: ");
            decisions[1] = sc.nextLine();
            System.out.println("Calculating...");
        } catch(Exception e) {
            System.out.println(e);
        }

        //Return the stations in the array for processing...
        return decisions;

    }


}
