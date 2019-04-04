import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.LinkedList;

public class FileWorker<T> {

    private static final File employeeFile = new File("Employees.txt");
    private static final File managerFile = new File("Managers.txt");

    public static LinkedList<?> loadRecords(File file) {
        String textFromFile = readFile(file);
        if (textFromFile != null) {
            String[] splittedText = textFromFile.split(";");
            if (file.getName() == "Employees.txt") {
                return Employee.getEmployeesList(splittedText);
            } else {
                return Manager.getManagersList(splittedText);
            }
        }
        else return null;
    }

    private static String readFile(File file) {
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                return String.valueOf(line);
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

    public static void rewriteFile(File file, LinkedList<?> records) {
        try {   
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < records.size(); i++) {
                fw.write(records.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
