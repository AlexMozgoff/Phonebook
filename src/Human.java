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
        if (surname.intern() != "" && !hasDigits(surname.intern()) && !surname.intern().contains(" "))
        {
            this.surname = surname;
        }
        else throw new IllegalArgumentException("You have entered wrong surname");
        if (name.intern() != "" && !hasDigits(name.intern()) && !name.intern().contains(" "))
        {
            this.name = name;
        }
        else throw new IllegalArgumentException("You have entered wrong name");
        if (yearOfBirth > 1900 && yearOfBirth <= currentYear - 18)
        {
            this.yearOfBirth = yearOfBirth;
        }
        else throw new IllegalArgumentException("You have entered wrong year of birth");
        if (phoneNumber.intern() != "" &&
                hasDigits(phoneNumber.intern()) &&
                !hasLetters(phoneNumber.intern()) &&
                !phoneNumber.intern().contains(" "))
        {
            this.phoneNumber = phoneNumber;
        }
        else throw new IllegalArgumentException("You have entered wrong phone number");
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

    private boolean hasDigits(String str) {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < digits.length; i++) {
            if (str.contains(digits[i])) return true;
        }
        return false;
    }

    private boolean hasLetters(String str) {
        String[] alphabet = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р",
                "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};
        for (int i = 1; i < str.length(); i++) {
            if (str.contains(alphabet[i])) return true;
        }
        return false;
    }
}
