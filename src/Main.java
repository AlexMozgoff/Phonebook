import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UI.clearConsole();
        UI.employees = (LinkedList<Employee>)FileWorker.loadRecords(new File("Employees.txt"));
        UI.managers = (LinkedList<Manager>)FileWorker.loadRecords(new File("Managers.txt"));
        UI.showRecords();
        String userAction = "";
        while (userAction != "exit") {
            in.reset();
            UI.showInitialMessage();
            userAction = in.nextLine();
            UI.chooseAction(userAction);
        }
    }
}
