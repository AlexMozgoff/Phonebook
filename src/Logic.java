import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Logic {

    private static Scanner in;

    public static void chooseAction(String userAnswer) {
        UI.clearConsole();
        switch (userAnswer) {
            case "1":
                break;
            case "2":
                addEmployee();
                UI.clearConsole();
                break;
            case "3":
                addManager();
                UI.clearConsole();
                break;
            case "4":
                deleteRecord();
                UI.clearConsole();
                break;
            case "5":
                searchBySurname();
                break;
            case "6":
                searchByName();
                break;
            case "7":
                searchByPhone();
                break;
            case "8":
                sortBySurname(UI.employees);
                sortBySurname(UI.managers);
                break;
            case "9":
                sortByYear();
                break;
            case "exit":
                FileWorker.rewriteFile(new File("Employees.txt"), UI.employees);
                FileWorker.rewriteFile(new File("Managers.txt"), UI.managers);
                System.exit(0);
        }
        UI.showRecords();
    }

    public static void addEmployee() {
        in = new Scanner(System.in);
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
        UI.employees.add(employee);

    }

    public static void addManager() {
        in = new Scanner(System.in);
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
        UI.managers.add(manager);
    }

    public static void deleteRecord() {
        in = new Scanner(System.in);
        System.out.println("Enter the index of the row to be deleted");
        int index = in.nextInt();

        if (UI.employees.size() >= index) {
            UI.employees.remove(index - 1);
            FileWorker.rewriteFile(new File("Employees.txt"), UI.employees);
        } else if (UI.managers.size() >= index - UI.employees.size()) {
            UI.managers.remove(index - UI.employees.size() - 1);
            FileWorker.rewriteFile(new File("Managers.txt"), UI.managers);
        } else throw new IndexOutOfBoundsException("Wrong index has been entered");
    }

    public static void searchBySurname() {
        in = new Scanner(System.in);
        System.out.println("Enter surname");
        String surname = in.nextLine();
        for (int i = 0; i < UI.employees.size(); i++) {
            if (UI.employees.get(i).getSurname().equals(surname)) {
                System.out.println(UI.employees.get(i).toString());
            }
        }
        for (int i = 0; i < UI.managers.size(); i++) {
            if (UI.managers.get(i).getSurname().equals(surname)) {
                System.out.println(UI.managers.get(i).toString());
            }
        }
        System.out.println();
    }

    public static void searchByName() {
        in = new Scanner(System.in);
        System.out.println("Enter name");
        String name = in.nextLine();
        for (int i = 0; i < UI.employees.size(); i++) {
            if (UI.employees.get(i).getName().equals(name)) {
                System.out.println(UI.employees.get(i).toString());
            }
        }
        for (int i = 0; i < UI.managers.size(); i++) {
            if (UI.managers.get(i).getName().equals(name)) {
                System.out.println(UI.managers.get(i).toString());
            }
        }
        System.out.println();
    }

    public static void searchByPhone() {
        in = new Scanner(System.in);
        System.out.println("Enter phone number");
        String phone = in.nextLine();
        for (int i = 0; i < UI.employees.size(); i++) {
            if (UI.employees.get(i).getPhoneNumber().equals(phone)) {
                System.out.println(UI.employees.get(i).toString());
            }
        }
        for (int i = 0; i < UI.managers.size(); i++) {
            if (UI.managers.get(i).getPhoneNumber().equals(phone)) {
                System.out.println(UI.managers.get(i).toString());
            }
        }
        System.out.println();
    }

    public static void sortBySurname(LinkedList<? extends Human> employees) {
        Collections.sort(employees);
    }

    public static void sortByYear() {
        Comparator<Human> yearComparator = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if (o1.getYearOfBirth() > o2.getYearOfBirth()) return 1;
                else if (o1.getYearOfBirth() < o2.getYearOfBirth()) return -1;
                else return 0;
            }
        };

        Collections.sort(UI.employees, yearComparator);
        Collections.sort(UI.managers, yearComparator);
    }

}
