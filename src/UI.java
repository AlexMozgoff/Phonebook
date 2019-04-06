import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class UI {

    private static Scanner in;
    public static LinkedList<Employee> employees;
    public static LinkedList<Manager> managers;

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
            case "1":
                clearConsole();
                showRecords();
                break;
            case "2":
                clearConsole();
                addEmployee();
                clearConsole();
                showRecords();
                break;
            case "3":
                clearConsole();
                addManager();
                clearConsole();
                showRecords();
                break;
            case "4":
                clearConsole();
                deleteRecord();
                clearConsole();
                showRecords();
                break;
            case "5":
                clearConsole();
                searchBySurname();
                showRecords();
                break;
            case "6":
                clearConsole();
                searchByName();
                showRecords();
                break;
            case "7":
                clearConsole();
                searchByPhone();
                showRecords();
                break;
            case "8":
                clearConsole();
                sortBySurname(employees);
                sortBySurname(managers);
                showRecords();
                break;
            case "9":
                clearConsole();
                sortByYear();
                showRecords();
                break;
            case "exit":
                System.exit(0);
        }
    }

    public static void showRecords() {
        System.out.println("Employees:");
        int count = 0;
        if (employees != null) {
            count = employees.size();
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ": "
                        + employees.get(i).toString());
            }
        }

        System.out.println("\nManagers:");
        if (managers != null) {
            for (int i = 0; i < managers.size(); i++) {
                System.out.println((i + count + 1) + ": "
                        + managers.get(i).toString());
            }
        }
        System.out.println("\n");
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
        employees.add(employee);

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
        managers.add(manager);
    }

    public static void deleteRecord() {
        in = new Scanner(System.in);
        System.out.println("Enter the index of the row to be deleted");
        int index = in.nextInt();

        if (employees.size() >= index) {
            employees.remove(index - 1);
            FileWorker.rewriteFile(new File("Employees.txt"), employees);
        } else if (managers.size() >= index - employees.size()) {
            managers.remove(index - employees.size() - 1);
            FileWorker.rewriteFile(new File("Managers.txt"), managers);
        } else throw new IndexOutOfBoundsException("Wrong index has been entered");
    }

    public static void searchBySurname() {
        in = new Scanner(System.in);
        System.out.println("Enter surname");
        String surname = in.nextLine();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getSurname().equals(surname)) {
                System.out.println(employees.get(i).toString() + "\n");
            }
        }
        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getSurname().equals(surname)) {
                System.out.println(managers.get(i).toString() + "\n");
            }
        }
    }

    public static void searchByName() {
        in = new Scanner(System.in);
        System.out.println("Enter name");
        String name = in.nextLine();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                System.out.println(employees.get(i).toString() + "\n");
            }
        }
        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getName().equals(name)) {
                System.out.println(managers.get(i).toString() + "\n");
            }
        }
    }

    public static void searchByPhone() {
        in = new Scanner(System.in);
        System.out.println("Enter phone number");
        String phone = in.nextLine();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getPhoneNumber().equals(phone)) {
                System.out.println(employees.get(i).toString());
            }
        }
        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getPhoneNumber().equals(phone)) {
                System.out.println(managers.get(i).toString());
            }
        }
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

        Collections.sort(employees, yearComparator);
        Collections.sort(managers, yearComparator);
    }

    /*private LinkedList<? extends Human> sort(LinkedList<T> records) {
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                if (((Human)records.get(i)).getYearOfBirth() > ((Human)records.get(j)).getYearOfBirth()) {
                    var temp = "te";
                    (Human)records.get(i) = (Human)records.get(j);
                    ((Human)records.get(j)).setYearOfBirth(temp);
                }
            }
        }
        return records;
    }*/


    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }
    }

}
