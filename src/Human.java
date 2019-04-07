import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Human implements Comparable<Human> {

    private String surname;
    private String name;
    private int yearOfBirth;
    private String phoneNumber;

    public Human(String surname, String name, int yearOfBirth, String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        inputCheck(surname, name, yearOfBirth, phoneNumber);
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setSurname(String surname) {
        if (surname == "") {
            System.out.println("You have entered an empty string");
        }
        else {
            this.surname = surname;
        }
    }

    public void setName(String name) {
        if (name == "") {
            System.out.println("You have entered an empty string");
        }
        else {
            this.name = name;
        }
    }

    public void setYearOfBirth(int yearOfBirth) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = gc.get(Calendar.YEAR);

        if (yearOfBirth < 1900 || yearOfBirth > year) {
            System.out.println("You have entered wrong year of birth");
        }
        else {
            this.yearOfBirth = yearOfBirth;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == "") {
            System.out.println("You have entered empty phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    private void inputCheck(String surname, String name, int yearOfBirth, String phoneNumber) {
        GregorianCalendar gc = new GregorianCalendar();
        int currentYear = gc.get(Calendar.YEAR);

        if (surname.intern() == "") {
            throw new IllegalArgumentException("You have not entered surname");
        }

        if (name.intern() == "") {
            throw new IllegalArgumentException("You have not entered name");
        }

        if (phoneNumber.intern() == "") {
            throw new IllegalArgumentException("You have not entered phone number");
        }

        if (yearOfBirth < 1900 || yearOfBirth > currentYear) {
            throw new IllegalArgumentException("You have entered wrong year");
        }
    }

    public abstract void addToBook();
}
