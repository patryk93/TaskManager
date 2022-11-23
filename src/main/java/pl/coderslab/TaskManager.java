package pl.coderslab;

import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;
    public static void main(String[] args) {
        showOptions();
        getInfo();
    }

    public static void showOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static void getInfo() {
        Scanner scann = new Scanner(System.in);

    }

}


