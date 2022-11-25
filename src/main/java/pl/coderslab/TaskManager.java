package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {


    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    public static void main(String[] args) {
        tasks = loadDataToList(FILE_NAME);
        showOptions(OPTIONS);
        getInfo();
    }

    public static void showOptions(String[] options) {
        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void getInfo() {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            switch (input) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks,getNumberToDelete());
                    System.out.println("Value deleted");
                    break;
                case "list":
                    showList(tasks);
                    break;
                case "exit":
                    saveListToFile(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            showOptions(OPTIONS);
        }
    }

    public static void addTask() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please add task description");
        String description = scan.nextLine();
        System.out.println("Please add task due date");
        String date = scan.nextLine();
        System.out.println("Type 'true' if your task is important or 'false' if not");
        String priority = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = date;
        tasks[tasks.length - 1][2] = priority;
    }
    public static String[][] loadDataToList(String fileName) {
        Path file = Paths.get(fileName);
        if (!Files.exists(file)) {
            System.out.println("File doesn't exists.");
            System.exit(0);
        }

        String[][] tasks = null;
        try {
            List<String> list = Files.readAllLines(file);
            tasks = new String[list.size()][list.get(0).split(",").length];

            for (int i = 0; i < list.size(); i++) {
                String[] task = list.get(i).split(",");
                for (int j = 0; j < task.length; j++) {
                    tasks[i][j] = task[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static int getNumberToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove.");

        String number = scanner.nextLine();
        while (!isNumberGreaterEqualZero(number)) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            scanner.nextLine();
        }
        return Integer.parseInt(number);
    }

    private static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }

    public static void saveListToFile(String fileName, String[][] list) {
        Path dir = Paths.get(fileName);

        String[] lines = new String[tasks.length];
        for (int i = 0; i < list.length; i++) {
            lines[i] = String.join(",", list[i]);
        }

        try {
            Files.write(dir, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void showList(String[][] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < list[i].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }

    }

}


