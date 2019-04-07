import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UI.clearConsole();
        UI.showRecords();
        String userAction = "";
        while (userAction != "exit") {
            UI.showInitialMessage();
            userAction = in.nextLine();
            UI.chooseAction(userAction);
        }
    }
}
