import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    final static String FOLDER = "/tmp/";


    public static void main(String[] args) {
        welcomeScreen();
        mainMenu();
    }

    public static void welcomeScreen() {
        System.out.println("Welcome to LockedMe");
        System.out.println("Developer: Shadman Ahmed");
        System.out.println("------------------");
    }


    public static void mainMenu() {
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
        System.out.printf("1. Add a file"+ "%n" + "2. Delete a file" + "%n" + "3. Search for a file" + "%n" + "4. Return to main menu" + "%n");
        String operationsSelection = scanner.nextLine();
        switch (operationsSelection) {
            case "1":
                System.out.println("Please provide a file path");
                String addFilePath = scanner.nextLine();
                addFile(addFilePath);
                break;
            case "2":
                System.out.println("please enter file name");
                String deleteFileName = scanner.nextLine();
                deleteFile(deleteFileName);
                break;
            case "3":
                System.out.println("please enter file name");
                String searchFileName = scanner.nextLine();
                searchFiles(searchFileName);
                break;
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("Invalid selection, please try again");
                operationsMenu();
                break;
        }

    }

    public static Set<String> getFiles() {
        File[] files = new File(FOLDER).listFiles();

        Set<String> sorted = new TreeSet<>();

        for (File file: files) {
            sorted.add(file.getName());
        }
        return (sorted);
    }

    public static void showFiles()  {
        System.out.println("Showing files in ascending order:");
        getFiles().forEach(System.out::println);
        System.out.println("------------------");
        mainMenu();
    }

    public static void addFile(String filePath) {
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
            System.out.println("Copied to: " + newFilePath);
        } catch(IOException e) {
            System.out.println("Unable to copy file to " + newFilePath);
        }
        System.out.println("------------------");
        operationsMenu();
    }

    public static boolean[] searchLogic(String fileName) {
        final boolean[] exists = {false};

        getFiles().forEach((x) -> {
            if (x.toLowerCase(Locale.ROOT).equals(fileName.toLowerCase(Locale.ROOT))) {
                exists[0] = true;
            }
        });
        return (exists);
    }

    public static void deleteFile(String fileName) {
        final boolean[] exists = searchLogic(fileName);

        File file = new File(FOLDER + fileName );

        if (exists[0]) {
            file.delete();
            System.out.println("File Deleted " +fileName);
        } else {
            System.out.println(fileName + " doesn't exist");
        }
        operationsMenu();
    }

    public static void searchFiles(String fileName) {
        final boolean[] exists = searchLogic(fileName);
        if (exists[0]) {
            System.out.println(fileName + " exists");
        } else {
            System.out.println(fileName + " doesn't exist");
        }
        operationsMenu();
    }
}
