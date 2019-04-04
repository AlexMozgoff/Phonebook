import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Runtime.getRuntime;

public class Main {
        private static Scanner in = new Scanner(System.in);
        private static LinkedList<Employee> employees;
        private static LinkedList<Manager> managers;
    public static void main(String[] args) {
        clearConsole();
        employees = (LinkedList<Employee>)FileWorker.loadRecords(new File("Employees.txt"));
        managers = (LinkedList<Manager>)FileWorker.loadRecords(new File("Managers.txt"));
        showRecords(employees, managers);
        String userAction = "";
        while (userAction != "exit") {
            showInitialMessage();
            userAction = new Scanner(System.in).nextLine();
            chooseAction(userAction);
        }
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

    public static void chooseAction(String userAnswer) {
        switch (userAnswer) {
            case "1": clearConsole(); showRecords(employees, managers); break;
            case "2": clearConsole(); addEmployee(); clearConsole(); break;
            case "3": clearConsole(); addManager(); clearConsole(); break;
            case "4": clearConsole(); deleteRecord(); clearConsole(); break;
            case "5": clearConsole(); /*searchSurname();*/ clearConsole(); break;
            case "6": clearConsole(); /*searchName();*/ clearConsole(); break;
            case "7": clearConsole(); /*searchPhone();*/ clearConsole(); break;
            case "8": clearConsole(); /*sortBySurname();*/ clearConsole(); break;
            case "9": clearConsole(); /*sortByYear();*/ clearConsole(); break;
            case "exit": System.exit(0);
        }
    }

    public static void showRecords(LinkedList<Employee> employees, LinkedList<Manager> managers) {
        System.out.println("Employees:");
        int count = 0;
        if (employees != null) {
            count = employees.size();
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ": "
                        + employees.get(i).getSurname() + ", "
                        + employees.get(i).getName() + ", "
                        + employees.get(i).getYearOfBirth() + ", "
                        + employees.get(i).getPhoneNumber() + ", "
                        + employees.get(i).getManager() + ";");
            }
        }

        System.out.println("\nManagers:");
        if (managers != null) {
            for (int i = 0; i < managers.size(); i++) {
                System.out.println((i + count + 1) + ": "
                        + managers.get(i).getSurname() + ", "
                        + managers.get(i).getName() + ", "
                        + managers.get(i).getYearOfBirth() + ", "
                        + managers.get(i).getPhoneNumber() + ", "
                        + managers.get(i).getDepartment() + ";");
            }
        }
        System.out.println("\n");
    }

    public static void addEmployee() {
        System.out.println("Enter employee surname");
        String surname = in.nextLine();
        System.out.println("Enter employee name");
        String name = in.nextLine();
        System.out.println("Enter employee year of birth");
        int yearOfBirth = in.nextInt();
        System.out.println("Enter employee phone number");
        String phoneNumber = in.next();
        System.out.println("Enter employee manager name");
        String manager = in.next();

        Employee employee = new Employee(surname, name, yearOfBirth, phoneNumber, manager);
        employee.addToBook();
        employees = (LinkedList<Employee>) FileWorker.loadRecords(new File("Employees.txt"));

    }

    public static void addManager() {
        System.out.println("Enter manager surname");
        String surname = in.nextLine();
        System.out.println("Enter manager name");
        String name = in.nextLine();
        System.out.println("Enter manager year of birth");
        int yearOfBirth = in.nextInt();
        System.out.println("Enter manager phone number");
        String phoneNumber = in.next();
        System.out.println("Enter manager department name");
        String department = in.next();

        Manager manager = new Manager(surname, name, yearOfBirth, phoneNumber, department);
        manager.addToBook();
        managers = (LinkedList<Manager>)FileWorker.loadRecords(new File("Managers.txt"));
    }

    public static void deleteRecord() {
        System.out.println("Enter the index of the row to be deleted");
        int index = in.nextInt();
        if (employees.size() >= index) {
            employees.remove(index - 1);
            deleteRecordFromFile(new File("Employees.txt"), index - 1);
        }
        else if (managers.size() >= index - employees.size()) {
            managers.remove(index - employees.size() - 1);
            deleteRecordFromFile(new File("Managers.txt"), (index - employees.size() - 1));
        }
        else throw new IndexOutOfBoundsException("Wrong index has been entered");
    }

    private static void deleteRecordFromFile(File file, int index) {
        LinkedList<?> recordsFromFile = FileWorker.loadRecords(file);
        recordsFromFile.remove(index);
        FileWorker.rewriteFile(file, recordsFromFile);
    }



    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }
    }
}
