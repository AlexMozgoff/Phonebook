import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userAction = "";
        UI.clearConsole();
        UI.showRecords();
        while (userAction != "exit") {
            UI.showInitialMessage();
            userAction = in.nextLine();
            Logic.chooseAction(userAction);
        }
    }
}
