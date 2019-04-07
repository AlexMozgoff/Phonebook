import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Human implements Comparable<Human> {

    private String surname;
    private String name;
    private int yearOfBirth;
    private String phoneNumber;

    public Human(String surname, String name, int yearOfBirth, String phoneNumber) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int currentYear = gregorianCalendar.get(Calendar.YEAR);
        if (surname.intern() != "")
        {
            this.surname = surname;
        }
        else throw new IllegalArgumentException("You have entered empty surname");
        if (name.intern() != "")
        {
            this.name = name;
        }
        else throw new IllegalArgumentException("You have entered empty name");
        if (yearOfBirth > 1900 && yearOfBirth <= currentYear)
        {
            this.yearOfBirth = yearOfBirth;
        }
        else throw new IllegalArgumentException("You have entered empty year of birth");
        if (phoneNumber.intern() != "")
        {
            this.phoneNumber = phoneNumber;
        }
        else throw new IllegalArgumentException("You have entered empty phone number");
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

    public abstract void addToBook();
}
