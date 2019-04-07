import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class UI {

    public static LinkedList<Employee> employees;
    public static LinkedList<Manager> managers;

    static {
        employees = (LinkedList<Employee>)FileWorker.loadRecords(new File("Employees.txt"));
        managers = (LinkedList<Manager>)FileWorker.loadRecords(new File("Managers.txt"));
    }

    public static void showInitialMessage() {
        System.out.println("Enter a number to continue or type 'exit' to close\n" +
                "1 - show records\n" +
                "2 - add employee\n" +
                "3 - add manager\n" +
                "4 - delete record\n" +
                "5 - search by surname\n" +
                "6 - search by name\n" +
                "7 - search by phone number\n" +
                "8 - sort by surname\n" +
                "9 - sort by year of birth");
    }

    public static void showRecords() {
        System.out.println("Employees:");
        int count = 0;
        int i;
        if (employees != null) {
            count = employees.size();
            for (i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ": "
                        + employees.get(i).toString());
            }
        }

        System.out.println("\nManagers:");
        if (managers != null) {
            for (i = 0; i < managers.size(); i++) {
                System.out.println((i + count + 1) + ": "
                        + managers.get(i).toString());
            }
        }
        System.out.println();
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }
    }

}
