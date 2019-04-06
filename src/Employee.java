import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class Employee extends Human implements Comparable<Human> {

    private static final File file = new File("Employees.txt");
    private String manager;

    public Employee(String surname, String name, int yearOfBirth, String phoneNumber, String manager) {
        super(surname, name, yearOfBirth, phoneNumber);
        if (manager.intern() != "") {
            this.manager = manager;
        } else throw new IllegalArgumentException("You have not entered manager name");
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String toString() {
        return this.getSurname() + ", "
                + this.getName() + ", "
                + this.getYearOfBirth() + ", "
                + this.getPhoneNumber() + ", "
                + this.getManager() + ";";
    }

    @Override
    public void addToBook() {
        String record = toString();
        FileWorker.writeRecordToFile(file, record);
    }

    public static LinkedList<Employee> getEmployeesList(String[] splittedText) {
        LinkedList<Employee> employees = new LinkedList<>();
        for (int i = 0; i < splittedText.length; i++) {
            String[] objFields = splittedText[i].split(", ");
            Employee employee = new Employee(
                            objFields[0],
                            objFields[1],
                            Integer.valueOf(objFields[2]),
                            objFields[3],
                            objFields[4]);
            employees.add(employee);
        }
        return employees;
    }

    /*public static void sortBySurname(LinkedList<Employee> employees) {
        Collections.sort(employees);
    }*/

    @Override
    public int compareTo(Human employee) {
        int result = this.getSurname().compareTo(employee.getSurname());
        return result;
    }
}

