import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    final static String FOLDER = "/tmp";

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to LockedMe");
        System.out.println("What would you like to do?");
        System.out.printf("1. See all files"+ "%n" + "2. Manipulate files" + "%n" + "3. Exit Application" + "%n");
        String mainMenuSelection = scanner.nextLine();

        switch (mainMenuSelection) {
            case "1":
                showFiles();
                break;
            case "2":
                operationsMenu();
                break;
            case "3":
                System.out.println("Thanks for using LockedMe");
                System.exit(0);
            default:
                System.out.println("Invalid selection, please try again");
                mainMenu();
                break;
        }

    }

    public static void operationsMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("1. Add a file"+ "%n" + "2. Delete a file" + "%n" + "3. Search for a file" + "%n" + "4. Return to main menu" + "%n");
        String operationsSelection = scanner.nextLine();

        switch (operationsSelection) {
            case "1":
                addFile();
                break;
            case "2":
                deleteFile();
                break;
            case "3":
                searchFiles();
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("Invalid selection, please try again");
                operationsMenu();
                break;
        }

    }

    public static void showFiles()  {
        System.out.println("Showing files in ascending order:");

        File[] files = new File(FOLDER).listFiles();

        Set<String> sorted = new TreeSet<>();

        for (File file: files) {
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("------------------");
        mainMenu();
    }

    public static void addFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide a file path");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File does not exist");
        }



        String newFilePath = FOLDER + "/" + path.getFileName();
        int inc = 0;
        while (Files.exists(Paths.get(newFilePath))) {
            inc++;
            newFilePath = FOLDER + "/" + inc + "_" + path.getFileName();
        } try {
            Files.copy(path, Paths.get(newFilePath));
        } catch(IOException e) {
            System.out.println("Unable to copy file to " + newFilePath);
        }

        System.out.println("newFilePath: " + newFilePath);
        System.out.println("path" + path.toString());
        System.out.println("------------------");
        mainMenu();
    }

    public static void deleteFile() {
        System.out.println("delete");
    }

    public static void searchFiles() {
        System.out.println("search");
    }
}
