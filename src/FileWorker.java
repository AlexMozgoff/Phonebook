import java.io.*;
import java.util.LinkedList;

public class FileWorker {

    public static LinkedList<? extends Human> loadRecords(File file) {
        String textFromFile = readFile(file);
        if (textFromFile != null) {
            String[] splittedText = textFromFile.split(";");
            if (file.getName() == "Employees.txt") {
                return Employee.getEmployeesList(splittedText);
            } else if (file.getName() == "Managers.txt"){
                return Manager.getManagersList(splittedText);
            }
        }
        return null;
    }

    private static String readFile(File file) {
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                br.close();
                return line;
            } else {
                System.out.println("File does not exist");
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    public static void writeRecordToFile(File file, String record) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter out = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(record);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteFile(File file, LinkedList<? extends Human> records) {
        if (file.delete()) System.out.println("File deleted");
        if (records.get(0).getClass().getSimpleName() == "Employee") {
            for (int i = 0; i < records.size(); i++) {
                Employee employee = (Employee) records.get(i);
                employee.addToBook();
            }
        } else {
            for (int i = 0; i < records.size(); i++) {
                Manager manager = (Manager) records.get(i);
                manager.addToBook();
            }

        }
    }
}
