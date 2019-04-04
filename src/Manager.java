import java.io.File;
import java.util.LinkedList;

public class Manager extends Human implements IReadableHuman {

    private static final File file = new File("Managers.txt");
    private String department;

    public Manager(String surname, String name, int yearOfBirth, String phoneNumber, String department) {
        super(surname, name, yearOfBirth, phoneNumber);
        if (department != "") {
            this.department = department;
        }
        else throw new IllegalArgumentException("You have not entered department");
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addToBook() {
        String record = prepareString();
        FileWorker.writeRecordToFile(file, record);
    }

    private String prepareString() {
        return this.getSurname() + ", "
                + this.getName() + ", "
                + this.getYearOfBirth() + ", "
                + this.getPhoneNumber() + ", "
                + this.getDepartment() + ";";
    }

    public static LinkedList<Manager> getManagersList(String[] splittedText) {
        LinkedList<Manager> managers = new LinkedList<>();
        for (int i = 0; i < splittedText.length; i++) {
            String[] objFields = splittedText[i].split(", ");
            Manager manager = new Manager(
                    objFields[0],
                    objFields[1],
                    Integer.valueOf(objFields[2]),
                    objFields[3],
                    objFields[4]);
            managers.add(manager);
        }
        return managers;
    }
}
