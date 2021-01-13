import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to LockedMe");
        System.out.println("What would you like to do?");
        System.out.printf("1. See all files"+ "%n" + "2. Manipulate files" + "%n" + "3. Exit Application" + "%n");
        String mainMenuSelection = scanner.nextLine();

        switch (mainMenuSelection) {
            case "1":
                readFiles();
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

    public static void readFiles()  {
        System.out.println("readFiles");
    }

    public static void addFile() {
        System.out.println("add");
    }

    public static void deleteFile() {
        System.out.println("delete");
    }

    public static void searchFiles() {
        System.out.println("search");
    }
}
